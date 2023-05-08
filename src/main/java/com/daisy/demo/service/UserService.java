package com.daisy.demo.service;

import com.daisy.demo.dto.UserDTO;
import com.daisy.demo.entity.User;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dxm
 */
@Service
public interface UserService{


    void add() ;

    List<User> all(UserDTO user);

    int count(UserDTO user);
}
