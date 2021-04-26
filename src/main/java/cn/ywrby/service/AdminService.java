package cn.ywrby.service;


import cn.ywrby.domain.Blog;
import cn.ywrby.domain.User;

import java.util.List;

public interface AdminService {
    User login(String username, String password);

    List<Blog> findAllBlogs();
}
