package cn.ywrby.service;


import cn.ywrby.domain.Blog;
import cn.ywrby.domain.Tag;
import cn.ywrby.domain.User;

import java.util.List;

/**
 * 后台管理Service层
 */
public interface AdminService {
    /**
     * 用户后台登录功能
     * @param username 用户名
     * @param password 用户密码
     * @return 获取到的用户对象
     */
    User login(String username, String password);
}
