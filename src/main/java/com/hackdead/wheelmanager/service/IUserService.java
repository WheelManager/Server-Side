package com.hackdead.wheelmanager.service;

import com.hackdead.wheelmanager.entities.User;
import java.util.List;

public interface IUserService extends CrudService<User>{
    public List<User> findByUsername(String username) throws Exception;
}
