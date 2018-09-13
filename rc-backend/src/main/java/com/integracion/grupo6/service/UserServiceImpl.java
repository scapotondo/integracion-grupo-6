package com.integracion.grupo6.service;

import com.integracion.grupo6.adapter.UserAdapter;
import com.integracion.grupo6.domain.User;
import com.integracion.grupo6.domain.UserRole;
import com.integracion.grupo6.dto.UserDTO;
import com.integracion.grupo6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserAdapter userAdapter;

    @Override
    public void create(UserDTO user) {
        User newUser = new User();
        newUser.setFullName(user.getFullName());
        newUser.setPassword(user.getPassword());
        newUser.setUserRole(userRoleService.findByName(user.getUserRole()));
        newUser.setUsername(user.getUsername());
        userRepository.save(newUser);
    }

    @Override
    public void update(UserDTO user) {
        User newUser = userRepository.findByUsername(user.getUsername());
        newUser.setFullName(user.getFullName());
        newUser.setPassword(user.getPassword());
        newUser.setUserRole(userRoleService.findByName(user.getUserRole()));
        newUser.setUsername(user.getUsername());
    }

    @Override
    public void delete(Long id) {
        User user = findById(id);
        if(user != null){
            userRepository.delete(user);
        }
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
    public List<UserDTO> findAll() {
        List<UserDTO> users = new ArrayList<>();
        for (User user: userRepository.findAll()) {
            users.add(userAdapter.toUserDTO(user));
        }

        return users;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDTO findDTOByUsername(String username) {
        return userAdapter.toUserDTO(findByUsername(username));
    }

}
