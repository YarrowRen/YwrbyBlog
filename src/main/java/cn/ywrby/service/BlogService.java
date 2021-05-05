package cn.ywrby.service;

import cn.ywrby.domain.Blog;

import java.util.List;

/**
 * 博客管理Service层，处理对博客的相关操作
 */
public interface BlogService {
    /**
     * 查询全部博客并返回
     * @param page 分页起始页
     * @param pageSize 分页大小
     * @return 全部博客列表
     */
    List<Blog> findAllBlogs(Integer page, Integer pageSize);

    /**
     * 根据博客ID删除博客
     * @param blogId　博客ID
     */
    void delBlog(int blogId);

    /**
     * 保存博客信息
     * @param title 博客标题
     * @param content 博客正文内容
     * @return 新建博客的ID
     */
    int save(String title, String content);

    /**
     * 保存博客与标签的关系
     * @param blogId 博客ID
     * @param tagList 标签列表
     */
    void saveBlogTag(int blogId, String[] tagList);

    /**
     * 利用博客ID找到指定博客
     * @param blogId 博客ID
     * @return 找到的博客
     */
    Blog findBlogById(int blogId);

    /**
     * 保存重新编辑后的博客
     * @param blogId 博客ID
     * @param title 博客标题
     * @param content 博客正文内容
     */
    void blogReEdit(int blogId, String title, String content);
}
