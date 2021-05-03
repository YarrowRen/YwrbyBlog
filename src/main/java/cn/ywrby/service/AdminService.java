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

    List<Blog> findAllBlogs();

    List<Tag> findAllTags();

    void saveTag(String tagName);

    void delTag(int tagId);

    Tag findTagById(int tagId);

    void tagReEdit(int tagId, String tagName);
}
