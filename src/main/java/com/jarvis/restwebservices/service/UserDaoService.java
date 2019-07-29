package com.jarvis.restwebservices.service;

import com.jarvis.restwebservices.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int usersCount = 0;

    static {

        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "John", new Date()));
        users.add(new User(3, "Ram", new Date()));
        usersCount = 3;
    }

    public List<User> findAll()
    {
        return users;
    }

    public User save(User user)
    {
        if(user != null && user.getId() == null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){
        for(User user :users){
            if(user.getId() == id)
                return user;
        }
        return null;
    }

}
