package ru.romanov.StoreTZ.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.romanov.StoreTZ.entities.UserEntity;
import ru.romanov.StoreTZ.repositories.UserRepository;
import ru.romanov.StoreTZ.security.UserEntityDetails;

import java.util.Optional;

@Service
public class UserEntityDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserEntityDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //Вот как раз тот метод который будет
    //Загружать по имени пользователя
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Оборачиваем в optional потому что он может быть а может и нет
        Optional<UserEntity> user = userRepository.findByUserName(username);
        //Если пользователь не пришел выбрасываем исключение
        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found!");
        //Если таки пришел то получаем
        //И оборачиваем в Details
        return new UserEntityDetails(user.get());
    }
}
