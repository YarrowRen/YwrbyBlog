package cn.ywrby.service;


import cn.ywrby.domain.Blog;
import cn.ywrby.domain.Tag;
import cn.ywrby.domain.User;

import java.util.List;

/**
 * 后台管理Service层
 */
public interface AdminService {
    User login(String username, String password);

}
