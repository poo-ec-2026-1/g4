@echo off
setlocal

call compile.bat
if errorlevel 1 exit /b 1

if not exist database mkdir database
java -cp "build;lib\sqlite-jdbc-3.36.0.3.jar" br.com.g4.orcamentos.MainApp
