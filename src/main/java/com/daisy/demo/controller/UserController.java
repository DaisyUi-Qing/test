package com.daisy.demo.controller;

import com.daisy.demo.dto.UserDTO;
import com.daisy.demo.entity.User;
import com.daisy.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author dxm
 */
@RestController
@RequestMapping("/user")
public record UserController(UserService userService) {


    @PostMapping
    public void add() {
        userService.add();

    }

    @GetMapping
    public Object getAll(@RequestBody UserDTO user){
        user.setPageNum(user.getPageNum()==0?1:user.getPageNum());
        user.setPageSize(user.getPageSize()==0?10:user.getPageSize());
        user.setStart((user.getPageNum()-1) * user.getPageSize());
        List<User> all = userService.all(user);
        int count = userService.count(user);

        return Map.of("data",all,
                "total",count,
                "pageNum",user.getPageNum(),
                "pageSize",user.getPageSize());

    }




}
