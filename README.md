# 🍔 Restaurant WebApp (Foody)

A full-stack restaurant web application where you can order food from the comfort of your home, built with **Java Spring
Boot** for the backend and **Angular** for the frontend.

## In this lecture

- Setup Security in Spring Project.
- Create **Roles** Entity to store user roles.
- Enable URL & Method level security.
- Define custom **UserDetailsService** that uses data jpa to fetch user details from db.

# 🛠 How to set up spring security

## ✅ Requirements

- Java 17+ (or Java 11+ with proper dependencies)
- Spring Boot 3.x
- Gradle or Maven
- Spring Security

---

### 1. Add Dependencies

**Maven:**

```xml
<dependencies>
    <!-- Spring Boot Starter Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
</dependencies>
```

**Gradle:**

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-security'
}
```

---

### 2. Create the `SecurityConfig` Class

```java
package com.example.demo.config;

import com.example.demo.filter.CustomAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService; // Custom implementation of UserDetailsService.

    @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/public/**").permitAll()
                .anyRequest().authenticated()
            )
                .userDetailsService(userDetailsService) // Setting custom userDetailsService.
                .httpBasic(Customizer.withDefaults()); // Enabling basic auth.

        return http.build();
    }
}
```

---

## 🔗 Course Details

- Complete Playlist: [Foody Backend Project](https://www.youtube.com/playlist?list=PL5DyztRVgtRXELM94Wcb2zOmvE5ycLVGS)
- Channel : [CodeNCode](https://www.youtube.com/@codencode)

---

Made with ❤️ by CodeNCode (AKA Waqar Ahmad)

