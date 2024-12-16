package com.droid.E_commerce.app.service;

import com.droid.E_commerce.app.entity.User;
import com.droid.E_commerce.app.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(String.format("User with id %s not found", id))
                );
    }

    @Transactional
    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userRepository.findById(user.getId())
                .orElseThrow(
                        () -> new RuntimeException("User with id %s not found".formatted(user.getId()))
                );

        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("User with id %s not found".formatted(id))
                );

        userRepository.deleteById(id);
    }

    @Override
    public boolean verifyCredentials(String email, String password) {
        User user = userRepository.findByEmail(email);

        return Objects.equals(user.getPassword(), password);
    }
}
