package ru.romanov.StoreTZ.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.romanov.StoreTZ.entities.UserEntity;
import ru.romanov.StoreTZ.services.UserEntityDetailsService;
@Component
public class UserValidator implements Validator {
    private final UserEntityDetailsService userEntityDetailsService;

    @Autowired
    public UserValidator(UserEntityDetailsService userEntityDetailsService) {
        this.userEntityDetailsService = userEntityDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserEntity user = (UserEntity) target;
        //TODO переделать на optional
        try {
            userEntityDetailsService.loadUserByUsername(user.getUserName());
        }catch (UsernameNotFoundException ignored){
            return; //все ок пользователь не найдет
        }

        errors.rejectValue("username","","User with this name already exists.");
    }
}
