package com.example.login.service;

import com.example.login.mapper.UserMapper;
import com.example.login.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public List getAll() {
        return  userMapper.selectAll();
    }

    public User getOneById(String id) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("u_id", id);
        return userMapper.selectOneByExample(example);
    }

    public User getOneByUserName(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        return userMapper.selectOneByExample(example);
    }

    public void save(User user) {
        userMapper.insert(user);
    }

    public void delete(String id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
