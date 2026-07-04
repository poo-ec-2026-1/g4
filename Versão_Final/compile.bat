@echo off
setlocal

where javac >nul 2>nul
if errorlevel 1 (
  echo JDK nao encontrado. Instale o JDK 8 ou superior e confirme que javac esta no PATH.
  exit /b 1
)

if not exist build mkdir build
if not exist lib mkdir lib

if not exist lib\sqlite-jdbc-3.36.0.3.jar (
  echo Baixando driver SQLite JDBC...
  powershell -ExecutionPolicy Bypass -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.36.0.3/sqlite-jdbc-3.36.0.3.jar' -OutFile 'lib\sqlite-jdbc-3.36.0.3.jar'"
)

javac -encoding UTF-8 -cp "lib\sqlite-jdbc-3.36.0.3.jar;src" -d build src\br\com\g4\orcamentos\MainApp.java

if errorlevel 1 (
  echo Falha na compilacao.
  exit /b 1
)

echo Compilacao concluida.
