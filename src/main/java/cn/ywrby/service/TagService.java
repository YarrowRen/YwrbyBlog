package cn.ywrby.service;

import cn.ywrby.domain.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAllTags(Integer page, Integer pageSize);
    List<Tag> findAllTags();

    void saveTag(String tagName);

    void delTag(int tagId);

    Tag findTagById(int tagId);

    void tagReEdit(int tagId, String tagName);

    Tag findTagByTagName(String tagName);
}
