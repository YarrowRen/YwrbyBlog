package cn.ywrby.service.impl;

import cn.ywrby.domain.Blog;
import cn.ywrby.mapper.BlogMapper;
import cn.ywrby.service.BlogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("blogService")
public class BlogServiceImpl implements BlogService {
    @Autowired(required = false)
    private BlogMapper blogMapper;

    @Override
    public List<Blog> findAllBlogs(Integer page,Integer pageSize) {
        //获取分页插件对象
        PageHelper.startPage(page,pageSize);

        List<Blog> blogList = blogMapper.findAll();
        return blogList;
    }

    @Override
    public void delBlog(int blogId) {
        blogMapper.delete(blogId);
    }

    @Override
    public int save(String title, String content) {
        Blog blog=new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        Date date=new Date();
        blog.setReleaseDate(date);
        blog.setUpdateDate(date);
        blog.setViews(0);

        blogMapper.insert(blog);
        return blog.getId();
    }

    @Override
    public void saveBlogTag(int blogId, int tagId) {
        blogMapper.insertBlogTag(blogId,tagId);
    }

    @Override
    public Blog findBlogById(int blogId) {
        Blog blog=blogMapper.findBlogById(blogId);
        return blog;
    }
}
