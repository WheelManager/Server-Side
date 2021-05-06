package com.hackdead.wheelmanager.service.impl;

import com.hackdead.wheelmanager.entities.User;
import com.hackdead.wheelmanager.repository.IUserRepository;
import com.hackdead.wheelmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class UserServiceImpl implements IUserService{
    @Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional
    public User save(User user) throws Exception{
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) throws Exception {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() throws Exception {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) throws Exception {
        return userRepository.findById(id);
    }
    @Override
    public List<User> findByUsername(String username) throws Exception{
        return userRepository.findByUsername(username);
    }
}
