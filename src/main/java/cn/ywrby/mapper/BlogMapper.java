package cn.ywrby.mapper;

import cn.ywrby.domain.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper {

    public List<Blog> findAll();

    void delete(int blogId);

    void insert(Blog blog);

    void insertBlogTag(@Param("blogId") int blogId,@Param("tagId") int tagId);

    Blog findBlogById(int blogId);
}
