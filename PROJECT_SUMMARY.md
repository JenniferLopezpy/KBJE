# KBJE - Base de Conocimientos para Clínica

## 📋 Resumen del Proyecto

**KBJE** es una aplicación web moderna diseñada específicamente para clínicas y centros de llamadas, que proporciona una base de conocimientos automática y segura para que los agentes encuentren información rápidamente.

## 🎯 Propósito

- **Base de Conocimientos Automática**: Se alimenta automáticamente de archivos JSON
- **Sistema de Login Seguro**: Autenticación básica pero robusta
- **Interfaz Intuitiva**: Diseño moderno y responsive
- **Actualización en Tiempo Real**: Detecta cambios automáticamente
- **Ideal para Call Centers**: Información organizada y fácil de encontrar

## 🚀 Características Principales

### ✅ Sistema de Autenticación

- Login seguro con BCrypt
- Usuarios predefinidos (admin/agent)
- Sesiones seguras
- Logout automático

### ✅ Gestión Automática de Contenido

- Monitoreo de archivos JSON en tiempo real
- Categorías y subcategorías automáticas
- Actualización instantánea al modificar archivos
- Sin necesidad de reiniciar la aplicación

### ✅ Interfaz Moderna

- Diseño con Tailwind CSS
- Colores suaves (blanco, azul)
- Responsive design
- Navegación intuitiva

### ✅ Backend Robusto

- Java 17 + Spring Boot 3.2.0
- Arquitectura modular
- Manejo de errores
- APIs RESTful

## 🛠️ Tecnologías

- **Backend**: Java 17, Spring Boot, Spring Security
- **Frontend**: HTML5, Tailwind CSS, JavaScript
- **Monitoreo**: Apache Commons IO
- **JSON**: Jackson Databind
- **Templates**: Thymeleaf
- **Containerización**: Docker
- **CI/CD**: GitHub Actions

## 📁 Estructura del Proyecto

```
KBJE/
├── src/main/java/com/kbje/
│   ├── JsonCategoryManagerApplication.java
│   ├── config/
│   │   ├── SecurityConfig.java
│   │   └── WebConfig.java
│   ├── controller/
│   │   ├── LoginController.java
│   │   ├── MainController.java
│   │   └── CustomErrorController.java
│   └── service/
│       ├── CategoryService.java
│       └── JsonFileWatcherService.java
├── src/main/resources/
│   ├── templates/
│   │   ├── login.html
│   │   ├── index.html
│   │   ├── category-detail.html
│   │   └── error.html
│   └── application*.properties
├── .github/workflows/ci.yml
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

## 🔐 Seguridad

### Usuarios Predefinidos

- **Admin**: `admin` / `admin123`
- **Agente**: `agent` / `agent123`

### Características de Seguridad

- Contraseñas encriptadas con BCrypt
- Sesiones seguras
- Protección de rutas
- Logout seguro

## 🚀 Despliegue

### Opción 1: Local

```bash
mvn spring-boot:run
```

### Opción 2: Docker

```bash
docker-compose up --build
```

### Opción 3: GitHub Actions

- Automático en cada push
- Build y test automáticos
- Artifacts generados

## 📊 Funcionalidades

### 1. Monitoreo Automático

- Detecta nuevos archivos JSON
- Actualiza contenido modificado
- Elimina categorías de archivos borrados

### 2. Estructura Automática

- Cada archivo JSON = Categoría
- Claves de nivel superior = Subcategorías
- Contenido dinámico sin hardcoding

### 3. Interfaz Intuitiva

- Grid de categorías
- Detalles de subcategorías
- Búsqueda y navegación
- Información del usuario

### 4. APIs RESTful

- `/api/categories` - Lista de categorías
- `/api/categories/{name}` - Detalle de categoría
- `/api/files` - Archivos monitoreados
- `/api/status` - Estado del sistema

## 🎨 Diseño

### Paleta de Colores

- **Smooth Blue**: `#3B82F6`
- **Echoing Blue**: `#1E40AF`
- **Soft White**: `#F8FAFC`

### Características de UX

- Diseño limpio y moderno
- Bordes suaves
- Animaciones sutiles
- Responsive design

## 📈 Escalabilidad

### Arquitectura Modular

- Servicios separados
- Configuración por perfiles
- Fácil extensión

### Configuración por Entorno

- `application-dev.properties` - Desarrollo
- `application-prod.properties` - Producción
- `application-docker.properties` - Docker

## 🔧 Configuración

### Variables de Entorno

```bash
ADMIN_USERNAME=admin
ADMIN_PASSWORD=admin123
SPRING_PROFILES_ACTIVE=prod
```

### Docker

```yaml
environment:
  - ADMIN_USERNAME=admin
  - ADMIN_PASSWORD=admin123
  - SPRING_PROFILES_ACTIVE=docker
```

## 📝 Formato JSON

### Estructura Esperada

```json
{
  "Subcategoria1": {
    "titulo": "Información importante",
    "descripcion": "Detalles del procedimiento",
    "contacto": "teléfono o email"
  },
  "Subcategoria2": {
    "pasos": ["Paso 1", "Paso 2", "Paso 3"],
    "notas": "Información adicional"
  }
}
```

## 🚀 Roadmap

### Versión Actual (1.0.0)

- ✅ Sistema de login básico
- ✅ Monitoreo automático de archivos
- ✅ Interfaz moderna
- ✅ APIs RESTful
- ✅ Docker support
- ✅ CI/CD pipeline

### Próximas Versiones

- 🔄 Base de datos para usuarios
- 🔄 Búsqueda avanzada
- 🔄 Filtros por categoría
- 🔄 Exportación de datos
- 🔄 Notificaciones en tiempo real
- 🔄 Dashboard de administración

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature
3. Commit tus cambios
4. Push a la rama
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT.

## 📞 Contacto

Para soporte técnico o preguntas sobre el proyecto, contactar al equipo de desarrollo.

---

**KBJE** - Base de Conocimientos para Clínica
_Sistema automático, seguro y moderno_
