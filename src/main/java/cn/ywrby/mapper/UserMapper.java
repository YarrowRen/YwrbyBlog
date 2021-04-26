package cn.ywrby.mapper;

import cn.ywrby.domain.User;

import java.util.List;

public interface UserMapper {
    /**
     * 获取所有用户的数据
     * @return 所有用户数据组成的集合
     */
    public List<User> findAll() ;

    public User findUserByUsernameAndPassword(String username,String password);
}
