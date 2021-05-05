package cn.ywrby.service;

import cn.ywrby.domain.Tag;

import java.util.List;

/**
 * 标签Service层
 */
public interface TagService {

    /**
     * 查询所有标签信息并返回
     * @param page 分页起始页
     * @param pageSize 分页大小
     * @return 获取到的所有标签列表
     */
    List<Tag> findAllTags(Integer page, Integer pageSize);

    /**
     * 查询所有标签信息并返回
     * @return 获取到的所有标签列表
     */
    List<Tag> findAllTags();

    /**
     * 保存/新建标签
     * @param tagName 标签名称
     */
    void saveTag(String tagName);

    /**
     * 根据标签ID删除指定标签
     * @param tagId 标签ID
     */
    void delTag(int tagId);

    /**
     * 利用标签ID查找指定标签
     * @param tagId 标签ID
     * @return 指定ID的标签
     */
    Tag findTagById(int tagId);

    /**
     * 重新编辑标签信息
     * @param tagId 标签ID
     * @param tagName　标签名称
     */
    void tagReEdit(int tagId, String tagName);

    Tag findTagByTagName(String tagName);
}
