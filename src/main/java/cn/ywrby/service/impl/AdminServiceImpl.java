package cn.ywrby.service.impl;

import cn.ywrby.domain.Blog;
import cn.ywrby.domain.Tag;
import cn.ywrby.domain.User;
import cn.ywrby.mapper.BlogMapper;
import cn.ywrby.mapper.TagMapper;
import cn.ywrby.mapper.UserMapper;
import cn.ywrby.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台管理Service层
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        //利用用户名和密码查找是否存在指定用户并返回
        User user = userMapper.findUserByUsernameAndPassword(username, password);
        return user;
    }

}
