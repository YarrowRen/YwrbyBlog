package cn.ywrby.service.impl;

import cn.ywrby.domain.Blog;
import cn.ywrby.domain.Tag;
import cn.ywrby.domain.User;
import cn.ywrby.mapper.BlogMapper;
import cn.ywrby.mapper.TagMapper;
import cn.ywrby.mapper.UserMapper;
import cn.ywrby.service.AdminService;
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

    @Autowired(required = false)
    private BlogMapper blogMapper;

    @Autowired(required = false)
    private TagMapper tagMapper;

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

    @Override
    public List<Tag> findAllTags() {
        List<Tag> tagList=tagMapper.findAll();
        return tagList;
    }

    @Override
    public void saveTag(String tagName) {
        Tag tag=new Tag();
        tag.setTagName(tagName);
        tagMapper.save(tag);
    }

    @Override
    public void delTag(int tagId) {
        tagMapper.delete(tagId);
    }

    @Override
    public Tag findTagById(int tagId) {
        Tag tag=tagMapper.findTagById(tagId);
        return tag;
    }

    @Override
    public void tagReEdit(int tagId, String tagName) {
        Tag tag=new Tag();
        tag.setId(tagId);
        tag.setTagName(tagName);
        tagMapper.update(tag);
    }
}
