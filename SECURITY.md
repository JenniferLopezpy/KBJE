# Seguridad - KBJE

## 🔐 Sistema de Autenticación

### Características de Seguridad

- **Encriptación de Contraseñas**: BCrypt con salt automático
- **Sesiones Seguras**: Configuradas automáticamente por Spring Security
- **Protección CSRF**: Deshabilitada para simplificar (configurable)
- **Redirección Segura**: Login automático para rutas protegidas
- **Logout Seguro**: Invalida sesiones correctamente

### Usuarios Predefinidos

#### Administrador

- **Usuario**: `admin`
- **Contraseña**: `admin123`
- **Rol**: `ADMIN`
- **Permisos**: Acceso completo al sistema

#### Agente

- **Usuario**: `agent`
- **Contraseña**: `agent123`
- **Rol**: `AGENT`
- **Permisos**: Acceso de lectura a la base de conocimientos

### Configuración de Seguridad

#### Archivo: `SecurityConfig.java`

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Configuración de rutas protegidas
    // Configuración de usuarios
    // Configuración de encriptación
}
```

#### Rutas Protegidas

- `/` - Página principal (requiere autenticación)
- `/category/*` - Detalles de categorías (requiere autenticación)
- `/api/*` - APIs (excepto `/api/status`)

#### Rutas Públicas

- `/login` - Página de login
- `/css/**` - Recursos estáticos
- `/js/**` - Recursos estáticos
- `/images/**` - Recursos estáticos
- `/api/status` - Estado del sistema

### Personalización

#### Agregar Nuevos Usuarios

1. **Modificar SecurityConfig.java**:

```java
@Bean
public UserDetailsService userDetailsService() {
    UserDetails newUser = User.builder()
        .username("nuevo_usuario")
        .password(passwordEncoder().encode("nueva_contraseña"))
        .roles("AGENT")
        .build();

    return new InMemoryUserDetailsManager(admin, agent, newUser);
}
```

#### Cambiar Contraseñas

1. **Generar hash BCrypt**:

```java
BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
String hashedPassword = encoder.encode("nueva_contraseña");
```

2. **Actualizar en SecurityConfig.java**

#### Configurar Base de Datos

Para producción, se recomienda usar una base de datos:

1. **Agregar dependencia JPA**:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

2. **Crear entidad Usuario**:

```java
@Entity
public class Usuario {
    @Id
    private String username;
    private String password;
    private String role;
    // getters y setters
}
```

3. **Implementar UserDetailsService personalizado**

### Variables de Entorno

#### Producción

```bash
ADMIN_USERNAME=admin_produccion
ADMIN_PASSWORD=contraseña_segura
```

#### Docker

```yaml
environment:
  - ADMIN_USERNAME=admin
  - ADMIN_PASSWORD=admin123
```

### Mejoras de Seguridad Recomendadas

1. **Habilitar CSRF**:

```java
.csrf(csrf -> csrf.enable())
```

2. **Configurar Headers de Seguridad**:

```java
.headers(headers -> headers
    .frameOptions().deny()
    .contentTypeOptions().and()
    .httpStrictTransportSecurity(hstsConfig -> hstsConfig
        .maxAgeInSeconds(31536000)
        .includeSubdomains(true)
    )
)
```

3. **Implementar Rate Limiting**:

```java
@Bean
public RateLimiter rateLimiter() {
    return RateLimiter.create(100.0); // 100 requests per second
}
```

4. **Logging de Seguridad**:

```java
logging.level.org.springframework.security=DEBUG
```

### Monitoreo

#### Logs de Seguridad

- Intentos de login fallidos
- Logouts exitosos
- Accesos a rutas protegidas

#### Métricas

- Usuarios activos
- Intentos de autenticación
- Tiempo de sesión promedio

### Troubleshooting

#### Problema: No puedo acceder al sistema

**Solución**: Verificar credenciales y que el usuario esté configurado

#### Problema: Error de CSRF

**Solución**: Verificar configuración CSRF o deshabilitar temporalmente

#### Problema: Sesión expirada

**Solución**: Configurar tiempo de sesión en application.properties

### Contacto

Para reportar vulnerabilidades de seguridad, contactar al equipo de desarrollo.
