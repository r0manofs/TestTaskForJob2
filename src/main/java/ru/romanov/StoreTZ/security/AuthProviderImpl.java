package ru.romanov.StoreTZ.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.romanov.StoreTZ.services.UserEntityDetailsService;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final UserEntityDetailsService userDetailsService;

    @Autowired
    public AuthProviderImpl(UserEntityDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        //Получаем пришедший пароль
        String password = authentication.getCredentials().toString();

        //Сравниваем с тем что в БД
        if (!password.equals(userDetails.getPassword()))
            //Если не совпал - исключение
            throw new BadCredentialsException("Incorrect password!");
        //Пустой лист потому что там должна быть информация о правах пользователя
        //У нас ее пока нет
        return new UsernamePasswordAuthenticationToken(userDetails, password,
                Collections.emptyList());
    }

    //Для какого объекта этобудет работать
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
