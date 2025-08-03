# KBJE - Gestor Automático de Categorías JSON

Un sistema completo que se alimenta automáticamente de archivos JSON, detectando cambios en tiempo real y actualizando la información de categorías y subcategorías.

## 🚀 Características

- **Detección Automática**: Monitorea archivos JSON en tiempo real
- **Actualización Automática**: Se adapta automáticamente a cambios en los archivos
- **Sistema de Login Seguro**: Autenticación básica pero flexible
- **Interfaz Moderna**: Diseño limpio con Tailwind CSS
- **Backend Robusto**: Java con Spring Boot
- **Estructura Automática**: Se adapta a cualquier estructura JSON
- **Sin Hardcoding**: Toda la información viene de los archivos JSON
- **Base de Conocimientos**: Ideal para clínicas y centros de llamadas

## 🛠️ Tecnologías Utilizadas

- **Backend**: Java 17, Spring Boot 3.2.0
- **Frontend**: HTML5, CSS3 (Tailwind), JavaScript
- **Monitoreo**: Apache Commons IO FileAlterationMonitor
- **JSON**: Jackson Databind
- **Templates**: Thymeleaf

## 📁 Estructura del Proyecto

```
KBJE/
├── src/
│   └── main/
│       ├── java/com/kbje/
│       │   ├── JsonCategoryManagerApplication.java
│       │   ├── controller/
│       │   │   └── MainController.java
│       │   └── service/
│       │       ├── CategoryService.java
│       │       └── JsonFileWatcherService.java
│       └── resources/
│           ├── templates/
│           │   ├── index.html
│           │   └── category-detail.html
│           └── application.properties
├── pom.xml
└── README.md
```

## 🚀 Instalación y Uso

### Prerrequisitos

- Java 17 o superior
- Maven 3.6 o superior

### Pasos de Instalación

#### Opción 1: Ejecución Local

1. **Clonar o descargar el proyecto**

   ```bash
   # Si tienes Git
   git clone <tu-repositorio>
   cd KBJE
   ```

2. **Compilar el proyecto**

   ```bash
   mvn clean compile
   ```

3. **Ejecutar la aplicación**

   ```bash
   mvn spring-boot:run
   ```

4. **Acceder a la aplicación**
   - Abre tu navegador
   - Ve a: `http://localhost:8080`
   - Serás redirigido a la página de login

#### Opción 2: Con Docker

1. **Construir y ejecutar con Docker Compose**

   ```bash
   docker-compose up --build
   ```

2. **Acceder a la aplicación**
   - Abre tu navegador
   - Ve a: `http://localhost:8080`

#### Opción 3: Solo Docker

1. **Construir la imagen**

   ```bash
   docker build -t kbje-app .
   ```

2. **Ejecutar el contenedor**

   ```bash
   docker run -p 8080:8080 -v $(pwd):/app kbje-app
   ```

### 🔐 Autenticación

El sistema incluye un sistema de login básico pero seguro:

**Credenciales de Demo:**

- **Administrador**: `admin` / `admin123`
- **Agente**: `agent` / `agent123`

**Características de Seguridad:**

- Contraseñas encriptadas con BCrypt
- Sesiones seguras
- Protección CSRF (deshabilitada para simplificar)
- Redirección automática a login

## 📊 Cómo Funciona

### 1. Monitoreo de Archivos

El sistema monitorea automáticamente todos los archivos `.json` en el directorio raíz del proyecto.

### 2. Estructura de Categorías

Cada archivo JSON se convierte en una categoría:

- **Nombre de categoría**: Nombre del archivo (sin extensión)
- **Subcategorías**: Cada clave de nivel superior en el JSON
- **Contenido**: El valor de cada subcategoría

### 3. Actualización Automática

- ✅ Nuevos archivos JSON se detectan automáticamente
- ✅ Modificaciones en archivos existentes se reflejan inmediatamente
- ✅ Eliminación de archivos se procesa automáticamente

## 🚀 Despliegue y CI/CD

### GitHub Actions

El proyecto incluye un pipeline de CI/CD configurado con GitHub Actions que:

- ✅ Compila el proyecto automáticamente
- ✅ Ejecuta tests
- ✅ Genera artifacts para despliegue
- ✅ Se ejecuta en cada push y pull request

### Docker

El proyecto está containerizado para facilitar el despliegue:

- **Dockerfile**: Configuración para construir la imagen
- **docker-compose.yml**: Orquestación de servicios
- **Volúmenes**: Para persistir archivos JSON

### Despliegue en Producción

1. **Clonar el repositorio**
2. **Configurar variables de entorno**
3. **Ejecutar con Docker Compose**
4. **Configurar proxy reverso (opcional)**

## 📝 Formato de Archivos JSON

### Estructura Básica

```json
{
  "Subcategoria1": {
    "descripcion": "Información de la subcategoría",
    "datos": "Contenido adicional"
  },
  "Subcategoría2": "Contenido simple",
  "Subcategoría3": ["Lista de elementos", "Más elementos"]
}
```

### Ejemplo Real

```json
{
  "CallFlow": {
    "NewPatient": {
      "steps": ["Paso 1: Saludar al paciente", "Paso 2: Verificar información"]
    },
    "ScheduleAppointment": {
      "description": "Proceso de programación"
    }
  }
}
```

## 🎨 Características de la Interfaz

### Diseño

- **Colores**: Blanco suave, azul eco, bordes suaves
- **Tipografía**: Moderna y legible
- **Responsive**: Se adapta a diferentes tamaños de pantalla
- **Animaciones**: Transiciones suaves y efectos hover

### Funcionalidades

- **Vista General**: Muestra todas las categorías disponibles
- **Detalles**: Vista detallada de cada categoría con sus subcategorías
- **Contenido Expandible**: Click para ver/ocultar contenido JSON
- **Actualización Automática**: Se refresca cada 30 segundos

## 🔧 Configuración

### application.properties

```properties
# Puerto del servidor
server.port=8080

# Directorio de monitoreo
json.watcher.directory=.

# Intervalo de monitoreo (ms)
json.watcher.polling-interval=1000
```

### Personalización

- Cambia `server.port` para usar un puerto diferente
- Modifica `json.watcher.directory` para monitorear otro directorio
- Ajusta `json.watcher.polling-interval` para cambiar la frecuencia de monitoreo

## 📈 API Endpoints

### REST API

- `GET /api/categories` - Lista todas las categorías
- `GET /api/categories/{name}` - Obtiene una categoría específica
- `GET /api/files` - Lista archivos JSON monitoreados
- `GET /api/status` - Estado del sistema

### Páginas Web

- `GET /` - Página principal
- `GET /category/{name}` - Detalle de categoría

## 🚀 Despliegue

### Desarrollo Local

```bash
mvn spring-boot:run
```

### Producción

```bash
mvn clean package
java -jar target/json-category-manager-1.0.0.jar
```

### Docker (Opcional)

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/json-category-manager-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

## 🔍 Monitoreo y Logs

### Logs del Sistema

- Los cambios en archivos se registran en la consola
- Errores de parsing JSON se muestran claramente
- Estado del sistema visible en tiempo real

### Indicadores Visuales

- Contador de categorías activas
- Contador de archivos monitoreados
- Indicador de estado del sistema

## 🛡️ Características de Seguridad

- **Validación JSON**: Verifica que los archivos sean JSON válido
- **Manejo de Errores**: Errores gracefully sin afectar el sistema
- **Logs Seguros**: No expone información sensible en logs

## 🔄 Actualizaciones Automáticas

### Detección de Cambios

- ✅ Nuevos archivos JSON
- ✅ Modificaciones en archivos existentes
- ✅ Eliminación de archivos
- ✅ Cambios en estructura JSON

### Procesamiento

- Análisis automático de estructura JSON
- Extracción de subcategorías
- Actualización de interfaz en tiempo real

## 📱 Compatibilidad

- **Navegadores**: Chrome, Firefox, Safari, Edge
- **Sistemas**: Windows, macOS, Linux
- **Dispositivos**: Desktop, Tablet, Mobile (responsive)

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para detalles.

## 🆘 Soporte

Si encuentras algún problema o tienes preguntas:

1. Revisa los logs del sistema
2. Verifica que los archivos JSON sean válidos
3. Asegúrate de que Java 17 esté instalado
4. Contacta al equipo de desarrollo

---

**KBJE - Sistema Automático de Gestión de Categorías JSON** 🚀
