package cn.ywrby.service;

import cn.ywrby.domain.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> findAllBlogs(Integer page, Integer pageSize);

    void delBlog(int blogId);

    int save(String title, String content);

    void saveBlogTag(int blogId, int id);

    Blog findBlogById(int blogId);
}
