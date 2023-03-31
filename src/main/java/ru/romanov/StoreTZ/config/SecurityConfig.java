package ru.romanov.StoreTZ.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.romanov.StoreTZ.services.UserEntityDetailsService;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserEntityDetailsService userEntityDetailsService;

    @Autowired
    public SecurityConfig(UserEntityDetailsService userEntityDetailsService) {
        this.userEntityDetailsService = userEntityDetailsService;
    }

    //Метод настраивает логику аутентификации
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userEntityDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        //Как видно spring не очень рад
        //что мы не шифруем пароль
        return NoOpPasswordEncoder.getInstance();
    }
}
