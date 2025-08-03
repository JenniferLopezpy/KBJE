#!/bin/bash

echo "========================================"
echo "   KBJE - Gestor de Categorias JSON"
echo "========================================"
echo ""
echo "Iniciando el sistema..."
echo ""

# Verificar si Java está instalado
if ! command -v java &> /dev/null; then
    echo "ERROR: Java no está instalado o no está en el PATH"
    echo "Por favor instala Java 17 o superior"
    exit 1
fi

# Verificar si Maven está instalado
if ! command -v mvn &> /dev/null; then
    echo "ERROR: Maven no está instalado o no está en el PATH"
    echo "Por favor instala Maven 3.6 o superior"
    exit 1
fi

echo "Compilando el proyecto..."
mvn clean compile
if [ $? -ne 0 ]; then
    echo "ERROR: Error al compilar el proyecto"
    exit 1
fi

echo ""
echo "Iniciando la aplicación..."
echo ""
echo "La aplicación estará disponible en: http://localhost:8080"
echo ""
echo "Presiona Ctrl+C para detener el servidor"
echo ""

mvn spring-boot:run 