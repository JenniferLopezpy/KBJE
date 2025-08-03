# Guía de Instalación - KBJE

## 📋 Prerrequisitos

### 1. Instalar Java 17 o superior

#### Windows:

1. Descarga Java desde: https://adoptium.net/
2. Ejecuta el instalador
3. Asegúrate de que Java esté en el PATH del sistema
4. Verifica la instalación: `java -version`

#### macOS:

```bash
# Usando Homebrew
brew install openjdk@17

# O descarga desde: https://adoptium.net/
```

#### Linux (Ubuntu/Debian):

```bash
sudo apt update
sudo apt install openjdk-17-jdk
```

### 2. Instalar Maven 3.6 o superior

#### Windows:

1. Descarga Maven desde: https://maven.apache.org/download.cgi
2. Extrae en `C:\Program Files\Apache\maven`
3. Agrega `C:\Program Files\Apache\maven\bin` al PATH
4. Verifica: `mvn -version`

#### macOS:

```bash
# Usando Homebrew
brew install maven
```

#### Linux:

```bash
sudo apt install maven
```

## 🚀 Instalación Rápida

### Opción 1: Usando los scripts (Recomendado)

#### Windows:

```cmd
start.bat
```

#### Linux/macOS:

```bash
./start.sh
```

### Opción 2: Manual

1. **Compilar el proyecto:**

   ```bash
   mvn clean compile
   ```

2. **Ejecutar la aplicación:**

   ```bash
   mvn spring-boot:run
   ```

3. **Acceder a la aplicación:**
   - Abre tu navegador
   - Ve a: `http://localhost:8080`

## 🔧 Verificación de Instalación

### Verificar Java:

```bash
java -version
```

Debería mostrar algo como:

```
openjdk version "17.0.x" 2023-xx-xx
OpenJDK Runtime Environment (build 17.0.x+xx-xx)
OpenJDK 64-Bit Server VM (build 17.0.x+xx-xx, mixed mode, sharing)
```

### Verificar Maven:

```bash
mvn -version
```

Debería mostrar algo como:

```
Apache Maven 3.9.x (xxxxx)
Maven home: /usr/share/maven
Java version: 17.0.x, vendor: Eclipse Adoptium
```

## 🛠️ Solución de Problemas

### Error: "Java no está instalado"

1. Descarga e instala Java 17 desde https://adoptium.net/
2. Asegúrate de que esté en el PATH del sistema
3. Reinicia la terminal/consola

### Error: "Maven no está instalado"

1. Descarga Maven desde https://maven.apache.org/download.cgi
2. Configura las variables de entorno
3. Reinicia la terminal/consola

### Error: "Puerto 8080 en uso"

1. Cambia el puerto en `src/main/resources/application.properties`:
   ```properties
   server.port=8081
   ```
2. O detén otros servicios que usen el puerto 8080

### Error: "Permisos denegados" (Linux/macOS)

```bash
chmod +x start.sh
```

## 📱 Acceso a la Aplicación

Una vez que la aplicación esté ejecutándose:

1. **Página Principal:** http://localhost:8080
2. **API REST:** http://localhost:8080/api/categories
3. **Estado del Sistema:** http://localhost:8080/api/status

## 🔄 Actualización Automática

El sistema detectará automáticamente:

- ✅ Nuevos archivos JSON
- ✅ Modificaciones en archivos existentes
- ✅ Eliminación de archivos

## 📊 Monitoreo

- Los logs aparecen en la consola
- El estado del sistema se muestra en la interfaz web
- Los errores se registran automáticamente

---

**¡Listo! Tu sistema KBJE está funcionando** 🎉
