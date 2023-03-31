package ru.romanov.StoreTZ.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.StoreTZ.entities.UserEntity;
import ru.romanov.StoreTZ.repositories.UserRepository;
import ru.romanov.StoreTZ.util.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findOne(int id) {
        Optional<UserEntity> foundedUser = userRepository.findById(id);
        return foundedUser.orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void save(UserEntity user) {
        enrichUser(user);

        userRepository.save(user);
    }

    @Transactional
    public void update(int id, UserEntity updatedUser) {
        updatedUser.setUserId(id);
        userRepository.save(updatedUser);
    }

    @Transactional
    public void delete(int id) {
      userRepository.deleteById(id);
    }

    private void enrichUser(UserEntity user) {
        user.setUserId(user.getUserId());
    }
}
