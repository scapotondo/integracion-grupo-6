package com.integracion.grupo6.service;

import com.integracion.grupo6.domain.User;
import com.integracion.grupo6.dto.UserDTO;
import com.integracion.grupo6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(UserDTO user) {
        User newUser = new User();
        newUser.setFullName(user.getFullName());
        newUser.setPassword(user.getPassword());
        newUser.setUserRole(user.getUserRole());
        newUser.setUsername(user.getUsername());

        return userRepository.save(newUser);
    }

    @Override
    public User update(UserDTO user) {
        User newUser = findByUsername(user.getUsername());
        newUser.setFullName(user.getFullName());
        newUser.setPassword(user.getPassword());
        newUser.setUserRole(user.getUserRole());
        newUser.setUsername(user.getUsername());

        return null;
    }

    @Override
    public User delete(Long id) {
        User user = findById(id);
        if(user != null){
            userRepository.delete(user);
        }
        return user;
    }

    @Override
    public User findById(Long id) throws EntityNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            return user.get();
        } else {
            throw new EntityNotFoundException("No se encontro al usuario con id " + id);
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
