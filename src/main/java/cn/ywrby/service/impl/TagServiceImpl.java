package cn.ywrby.service.impl;

import cn.ywrby.domain.Blog;
import cn.ywrby.domain.Tag;
import cn.ywrby.domain.User;
import cn.ywrby.mapper.TagMapper;
import cn.ywrby.service.TagService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tagService")
public class TagServiceImpl implements TagService {
    @Autowired(required = false)
    private TagMapper tagMapper;

    @Override
    public List<Tag> findAllTags(Integer page,Integer pageSize) {
        //获取分页插件对象
        PageHelper.startPage(page,pageSize);

        List<Tag> tagList=tagMapper.findAll();
        return tagList;
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
        //首先通过标签名称检查标签是否已存在
        Tag resultTag=tagMapper.findTagByTagName(tagName);
        //标签不存在的情况下添加该标签
        if(resultTag==null){
            tagMapper.save(tag);
        }
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

    @Override
    public Tag findTagByTagName(String tagName) {
        Tag tag = tagMapper.findTagByTagName(tagName);
        return tag;
    }
}
