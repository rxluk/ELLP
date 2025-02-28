package com.app.ellp.Config;

import com.app.ellp.Security.SecurityFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@SecurityScheme(
        name = "Bearer Auth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    private static final String[] PERMIT_ALL = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/h2-console/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Rotas permitidas a todos
                        .requestMatchers(PERMIT_ALL).permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register/user").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register/colaborador").permitAll()

                        // Rotas acessíveis apenas por ADMIN
                        .requestMatchers(HttpMethod.GET, "/getByEmail/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/getById/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/getAll").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/deleteById/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/updateById/**").hasRole("ADMIN")

                        // Endpoints do UserController
                        .requestMatchers(HttpMethod.GET, "/get/{login}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/get/all").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/delete/{login}").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/put/{login}").authenticated()
                        .requestMatchers(HttpMethod.GET, "/get/all-with-password").authenticated()

                        // Endpoints do FamiliaController
                        .requestMatchers(HttpMethod.GET, "/familia/{id}").authenticated()           // Buscar família por ID
                        .requestMatchers(HttpMethod.GET, "/familia").authenticated()               // Buscar todas as famílias
                        .requestMatchers(HttpMethod.POST, "/familia").authenticated()              // Criar nova família
                        .requestMatchers(HttpMethod.PUT, "/familia/{id}").authenticated()          // Atualizar família
                        .requestMatchers(HttpMethod.DELETE, "/familia/{id}").authenticated()       // Deletar família

                        // Endpoints do ResponsavelController
                        .requestMatchers(HttpMethod.GET, "/responsavel").authenticated()           // Buscar todos os responsáveis
                        .requestMatchers(HttpMethod.GET, "/responsavel/{id}").authenticated()      // Buscar responsável por ID
                        .requestMatchers(HttpMethod.POST, "/responsavel").authenticated()         // Criar responsável
                        .requestMatchers(HttpMethod.PUT, "/responsavel/{id}").authenticated()     // Atualizar responsável
                        .requestMatchers(HttpMethod.DELETE, "/responsavel/{id}").authenticated()  // Deletar responsável

                        // Endpoints do AlunoController
                        .requestMatchers(HttpMethod.GET, "/aluno").authenticated()               // Buscar todos os alunos
                        .requestMatchers(HttpMethod.GET, "/aluno/{id}").authenticated()          // Buscar aluno por ID
                        .requestMatchers(HttpMethod.POST, "/aluno").authenticated()              // Criar aluno
                        .requestMatchers(HttpMethod.PUT, "/aluno/{id}").authenticated()          // Atualizar aluno
                        .requestMatchers(HttpMethod.DELETE, "/aluno/{id}").authenticated()       // Deletar aluno

                        // Endpoints do EscolaController
                        .requestMatchers(HttpMethod.GET, "/escola").authenticated()                  // Buscar todas as escolas
                        .requestMatchers(HttpMethod.GET, "/escola/{id}").authenticated()             // Buscar escola por ID
                        .requestMatchers(HttpMethod.POST, "/escola").authenticated()                // Criar escola
                        .requestMatchers(HttpMethod.PUT, "/escola/{id}").authenticated()             // Atualizar escola
                        .requestMatchers(HttpMethod.DELETE, "/escola/{id}").authenticated()          // Deletar escola
                        .requestMatchers(HttpMethod.GET, "/escola/nome/{nome}").authenticated()      // Buscar escola pelo nome

                        // Endpoints do DisciplinaController
                        .requestMatchers(HttpMethod.GET, "/disciplina").authenticated()                   // Buscar todas as disciplinas
                        .requestMatchers(HttpMethod.GET, "/disciplina/{id}").authenticated()              // Buscar disciplina por ID
                        .requestMatchers(HttpMethod.POST, "/disciplina").authenticated()                 // Criar disciplina
                        .requestMatchers(HttpMethod.PUT, "/disciplina/{id}").authenticated()              // Atualizar disciplina
                        .requestMatchers(HttpMethod.DELETE, "/disciplina/{id}").authenticated()           // Deletar disciplina
                        .requestMatchers(HttpMethod.GET, "/disciplina/nome/{nome}").authenticated()       // Buscar disciplina pelo nome

                        // Endpoints do NotaController
                        .requestMatchers(HttpMethod.GET, "/nota").authenticated()                  // Buscar todas as notas
                        .requestMatchers(HttpMethod.GET, "/nota/{id}").authenticated()             // Buscar nota por ID
                        .requestMatchers(HttpMethod.POST, "/nota").authenticated()                // Criar nova nota
                        .requestMatchers(HttpMethod.PUT, "/nota/{id}").authenticated()            // Atualizar nota por ID
                        .requestMatchers(HttpMethod.DELETE, "/nota/{id}").authenticated()         // Deletar nota por ID

                        // Qualquer outra rota requer autenticação
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("API ELLP")
                        .version("1.0.0")
                        .description("API Documentation for ELLP Application"));
    }
}