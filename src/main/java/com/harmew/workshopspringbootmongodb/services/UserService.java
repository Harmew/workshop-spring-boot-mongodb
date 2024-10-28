package com.harmew.workshopspringbootmongodb.services;

import com.harmew.workshopspringbootmongodb.domain.User;
import com.harmew.workshopspringbootmongodb.dto.UserDTO;
import com.harmew.workshopspringbootmongodb.repositories.UserRepository;
import com.harmew.workshopspringbootmongodb.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = this.repository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    public User insert(User user) {
        return repository.insert(user);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public User update(User user) {
        User newUser = findById(user.getId());
        updateData(newUser, user);
        return repository.save(newUser);

    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

    public void updateData(User newUser, User user) {
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
    }
}
