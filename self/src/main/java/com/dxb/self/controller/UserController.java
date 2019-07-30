package com.dxb.self.controller;


import com.dxb.self.entity.User;
import com.dxb.self.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dxb
 * @since 2019-07-26
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/set")
    @Transactional
    public List<User> setUsers() {
        User user = new User();
        user.setAge(new Random().nextInt(100));
        user.setEmail("ds1@ss.com");
        userService.save1(user);
        User user2 = new User();
        user2.setAge(new Random().nextInt(100));
        user2.setEmail("ds2@ss.com");
        userService.save2(user2);
//        测试事务
//        int i = 1 / 0;
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user2);
        return list;
    }

}
