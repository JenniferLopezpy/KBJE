@echo off
echo ========================================
echo KBJE - Verificacion del Entorno
echo ========================================

echo.
echo Verificando Java...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Java no esta instalado o no esta en el PATH
    echo Por favor instala Java 17 desde: https://adoptium.net/
    pause
    exit /b 1
) else (
    echo [OK] Java encontrado
    java -version
)

echo.
echo Verificando Maven...
mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Maven no esta instalado o no esta en el PATH
    echo.
    echo Para instalar Maven:
    echo 1. Descarga Maven desde: https://maven.apache.org/download.cgi
    echo 2. Extrae en C:\Program Files\Apache\maven
    echo 3. Agrega C:\Program Files\Apache\maven\bin al PATH
    echo 4. Reinicia la terminal
    echo.
    echo O usa el instalador de Chocolatey:
    echo choco install maven
    pause
    exit /b 1
) else (
    echo [OK] Maven encontrado
    mvn -version
)

echo.
echo ========================================
echo [OK] Entorno listo para desarrollo
echo ========================================
echo.
echo Para ejecutar el proyecto:
echo mvn spring-boot:run
echo.
echo Para compilar:
echo mvn clean compile
echo.
pause 