package senai499.com.br.saep.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import senai499.com.br.saep.repository.UsuariosRepository;

@Configuration
public class SecurityConfig {
    @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
    @Bean UserDetailsService userDetailsService(UsuariosRepository usuarios){return email -> usuarios.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Conta não encontrada"));}
    @Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth -> auth.requestMatchers("/","/sobre","/servicos","/cadastro","/login","/css/**","/img/**","/webjars/**","/error").permitAll().requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").usernameParameter("email").passwordParameter("senha").defaultSuccessUrl("/agendamentos",true).permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/?sair").permitAll()).build();
    }
}
