package cn.ywrby.service.impl;

import cn.ywrby.domain.Blog;
import cn.ywrby.domain.User;
import cn.ywrby.mapper.BlogMapper;
import cn.ywrby.mapper.UserMapper;
import cn.ywrby.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private BlogMapper blogMapper;

    @Override
    public User login(String username, String password) {
        User user = userMapper.findUserByUsernameAndPassword(username, password);
        return user;
    }

    @Override
    public List<Blog> findAllBlogs() {
        List<Blog> blogList = blogMapper.findAll();
        return blogList;
    }
}
