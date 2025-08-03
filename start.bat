@echo off
echo ========================================
echo    KBJE - Gestor de Categorias JSON
echo ========================================
echo.
echo Iniciando el sistema...
echo.

REM Verificar si Java está instalado
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java no está instalado o no está en el PATH
    echo Por favor instala Java 17 o superior
    pause
    exit /b 1
)

REM Verificar si Maven está instalado
mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Maven no está instalado o no está en el PATH
    echo Por favor instala Maven 3.6 o superior
    pause
    exit /b 1
)

echo Compilando el proyecto...
mvn clean compile
if %errorlevel% neq 0 (
    echo ERROR: Error al compilar el proyecto
    pause
    exit /b 1
)

echo.
echo Iniciando la aplicación...
echo.
echo La aplicación estará disponible en: http://localhost:8080
echo.
echo Presiona Ctrl+C para detener el servidor
echo.

mvn spring-boot:run

pause 