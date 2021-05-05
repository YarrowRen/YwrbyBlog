package cn.ywrby.service.impl;

import cn.ywrby.domain.Blog;
import cn.ywrby.domain.Tag;
import cn.ywrby.mapper.BlogMapper;
import cn.ywrby.mapper.TagMapper;
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

    @Autowired(required = false)
    private TagMapper tagMapper;

    @Override
    public List<Blog> findAllBlogs(Integer page,Integer pageSize) {
        //获取分页插件对象
        PageHelper.startPage(page,pageSize);
        //查询所有博客信息
        List<Blog> blogList = blogMapper.findAll();
        return blogList;
    }

    @Override
    public void delBlog(int blogId) {
        //删除博文的标签信息
        blogMapper.deleteBlogTag(blogId);
        //删除博文信息
        blogMapper.delete(blogId);
    }

    @Override
    public int save(String title, String content) {
        //新建博客对象并存入指定数据
        Blog blog=new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        //新建博客时，创建时间与更新时间一致
        Date date=new Date();
        blog.setReleaseDate(date);
        blog.setUpdateDate(date);
        //初始化查看数据
        blog.setViews(0);

        //调用持久层将博客对象写入数据库
        blogMapper.insert(blog);
        //返回数据插入后获取到的ID
        return blog.getId();
    }

    @Override
    public void saveBlogTag(int blogId, String[] tagList) {
        //首先删除原先的博客与标签关系
        blogMapper.deleteBlogTag(blogId);
        //再通过列表获取新标签并和博客ID写入中间表
        for(String tagName:tagList){
            //利用标签名称查询指定标签
            Tag tag=tagMapper.findTagByTagName(tagName);
            //利用持久层插入博客与标签的信息
            blogMapper.insertBlogTag(blogId,tag.getId());
        }
    }

    @Override
    public Blog findBlogById(int blogId) {
        Blog blog=blogMapper.findBlogById(blogId);
        return blog;
    }

    @Override
    public void blogReEdit(int blogId, String title, String content) {
        //利用ID先获取指定博客对象
        Blog blog = blogMapper.findBlogById(blogId);
        blog.setTitle(title);
        blog.setContent(content);
        //修改更新时间
        Date date=new Date();
        blog.setUpdateDate(date);
        //更新数据库中的信息
        blogMapper.update(blog);
    }
}
