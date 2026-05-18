param(
    [Parameter(Mandatory = $true)]
    [string] $FilePath
)

$ErrorActionPreference = "Stop"

$workspace = (Resolve-Path -LiteralPath (Join-Path $PSScriptRoot "..")).Path
Set-Location $workspace

$outPath = Join-Path $workspace "out"
if (Test-Path -LiteralPath $outPath) {
    $resolvedOut = (Resolve-Path -LiteralPath $outPath).Path
    if (-not $resolvedOut.StartsWith($workspace)) {
        throw "Caminho de saida fora do workspace: $resolvedOut"
    }
    Remove-Item -LiteralPath $resolvedOut -Recurse -Force
}

$arquivosJava = Get-ChildItem -Recurse -Filter *.java |
    Where-Object {
        $_.FullName -notlike "*\out\*" -and
        $_.FullName -notlike "*\.codex-compile-check\*"
    } |
    ForEach-Object { $_.FullName }

javac -encoding UTF-8 -d out $arquivosJava

if ($LASTEXITCODE -ne 0) {
    exit $LASTEXITCODE
}

$packageLine = Select-String -Path $FilePath -Pattern '^\s*package\s+([^;]+);' | Select-Object -First 1
$classLine = Select-String -Path $FilePath -Pattern 'public\s+class\s+([A-Za-z_][A-Za-z0-9_]*)' | Select-Object -First 1

if ($null -eq $classLine) {
    throw "Nao foi encontrada uma classe publica em: $FilePath"
}

$packageName = if ($packageLine) { $packageLine.Matches[0].Groups[1].Value } else { "" }
$className = $classLine.Matches[0].Groups[1].Value
$qualifiedName = if ($packageName) { "$packageName.$className" } else { $className }

Write-Host "Executando: $qualifiedName"
Write-Host ""

java -cp out $qualifiedName
