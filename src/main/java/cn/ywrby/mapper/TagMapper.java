package cn.ywrby.mapper;

import cn.ywrby.domain.Tag;

import java.util.List;

public interface TagMapper {

    List<Tag> findAll();

    void save(Tag tag);

    void delete(int tagId);

    Tag findTagById(int tagId);

    void update(Tag tag);

    Tag findTagByTagName(String tagName);

    void deleteBlogTagByTagId(int tagId);
}
