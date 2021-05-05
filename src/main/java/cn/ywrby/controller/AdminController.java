package cn.ywrby.controller;

import cn.ywrby.domain.Blog;
import cn.ywrby.domain.Tag;
import cn.ywrby.domain.User;
import cn.ywrby.service.AdminService;
import cn.ywrby.service.BlogService;
import cn.ywrby.service.TagService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 博客后台管理控制
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired(required = false)
    private AdminService adminService;

    @Autowired(required = false)
    private TagService tagService;

    @Autowired(required = false)
    private BlogService blogService;

    /**
     * 用户登录界面UI
     */
    @GetMapping
    public ModelAndView loginPage(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("admin/login");
        return modelAndView;
    }

    /**
     * 实现用户登录操作
     * @param username 用户名
     * @param password 密码
     * @param session HttpSession会话
     * @return 重定向到指定页面（密码正确到主页，不正确返回登陆页面）
     */
    @RequestMapping("/login")
    public ModelAndView login(String username, String password, HttpSession session){
        ModelAndView modelAndView=new ModelAndView("admin/login");
        //调用服务层方法检查数据库是否存在该用户
        User user=adminService.login(username,password);
        //不为空表示用户密码正确，向会话中写入对象，并记录登录信息，重定向到主页
        if(user!=null){
            //写入数据
            session.setAttribute("user",user);
            modelAndView.setViewName("admin/back-index");
            modelAndView.addObject("user",user);
            return modelAndView;
        }
        //否则表示用户名/密码错误，重定向到登录页面重新登录
        return modelAndView;
    }

    /**
     * 用户全部博客管理
     * @param page 分页起始页
     * @param pageSize 分页大小
     */
    @RequestMapping("/blogs")
    public ModelAndView blogsEdit(@RequestParam(required=true, defaultValue = "1")Integer page, @RequestParam(required=false,defaultValue="8")Integer pageSize){
        ModelAndView modelAndView=new ModelAndView();
        //获取全部博客
        List<Blog> blogList=blogService.findAllBlogs(page,pageSize);
        //获取分页数据
        PageInfo<Blog> info=new PageInfo<>(blogList);
        //将博客数据传入
        modelAndView.addObject("blogList",blogList);
        modelAndView.addObject("pageInfo",info);
        //跳转页面
        modelAndView.setViewName("admin/blogs-edit");
        return modelAndView;
    }


    /**
     * 标签管理展示界面
     * @param page 分页起始页
     * @param pageSize 分页大小
     */
    @RequestMapping("/tags")
    public ModelAndView tagsList(@RequestParam(required=true, defaultValue = "1")Integer page, @RequestParam(required=false,defaultValue="8")Integer pageSize){
        ModelAndView modelAndView=new ModelAndView();
        //获取所有标签数据
        List<Tag> tagList=tagService.findAllTags(page,pageSize);

        //获取分页信息对象
        PageInfo<Tag> info=new PageInfo<Tag>(tagList);

        //存入数据
        modelAndView.addObject("pageInfo",info);
        modelAndView.addObject("tagList",tagList);
        //跳转页面
        modelAndView.setViewName("admin/tags-edit");
        return modelAndView;
    }

    /**
     * 新建标签界面
     */
    @RequestMapping("/tagSaveUI")
    public ModelAndView tagSaveUI(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("admin/tag-save");
        return modelAndView;
    }

    /**
     * 保存标签/新建标签
     * @param tagName 标签名称
     */
    @RequestMapping("/tagSave")
    public String tagSave(String tagName){
        tagService.saveTag(tagName);  //调用tagService保存指定标签名
        return "redirect:/admin/tags";
    }

    /**
     * 删除指定标签
     * @param tagId 标签ID
     */
    @RequestMapping("/delTag/{tagId}")
    public String delTag(@PathVariable(value = "tagId",required = true) int tagId){
        tagService.delTag(tagId);
        //重定向到标签展示界面，以刷新数据库
        return "redirect:/admin/tags";
    }

    /**
     * 标签编辑界面
     * @param tagId 编辑标签的ID
     * @return
     */
    @RequestMapping("/editTagUI/{tagId}")
    public ModelAndView editTagUI(@PathVariable(value = "tagId",required = true) int tagId){
        ModelAndView modelAndView=new ModelAndView();
        //获取指定标签
        Tag tag=tagService.findTagById(tagId);
        //写入标签数据
        modelAndView.addObject("tag",tag);
        //跳转至编辑界面
        modelAndView.setViewName("admin/tag-edit");
        return modelAndView;
    }

    /**
     * 重新编辑标签功能
     * @param tagId 标签ID
     * @param tagName　重编辑后的标签名
     * @return
     */
    @RequestMapping("/tagReEdit")
    public String tagReEdit(int tagId,String tagName){
        tagService.tagReEdit(tagId,tagName);
        return "redirect:/admin/tags";
    }


    /**
     * 删除指定博客
     * @param blogId 博客ID
     * @return
     */
    @RequestMapping("/delBlog/{blogId}")
    public String delBlog(@PathVariable(value = "blogId",required = true) int blogId){
        blogService.delBlog(blogId);
        //重定向到博客展示界面，以刷新数据库
        return "redirect:/admin/blogs";
    }

    /**
     * 跳转到新建博客页面
     * @return
     */
    @RequestMapping("/blogSaveUI")
    public ModelAndView blogSaveUI(){
        ModelAndView modelAndView=new ModelAndView();
        List<Tag> tagList = tagService.findAllTags(); //获取全部标签信息
        //传入标签信息
        modelAndView.addObject("tagList",tagList);
        //跳转到指定视图
        modelAndView.setViewName("admin/blog-save");
        return modelAndView;
    }

    /**
     * 博客保存功能
     * @param title 博客标题
     * @param content 博客正文内容
     * @param tagList 博客的标签列表
     */
    @RequestMapping("/blogSave")
    public String blogSave(String title,String content,String[] tagList){
        //保存博客基本信息
        int blogId=blogService.save(title,content);
        //保存博客的标签信息
        blogService.saveBlogTag(blogId,tagList);
        //重定向到博客展示页面，以刷新数据库
        return "redirect:/admin/blogs";
    }

    /**
     * 重新编辑博客界面
     * @param blogId 博客ID
     * @return
     */
    @RequestMapping("/editBlogUI/{blogId}")
    public ModelAndView editBlogUI(@PathVariable(value = "blogId",required = true) int blogId){
        ModelAndView modelAndView=new ModelAndView();
        //根据博客ID获取博客基本信息
        Blog blog=blogService.findBlogById(blogId);
        //获取全部标签列表
        List<Tag> tagList = tagService.findAllTags();
        //传入数据
        modelAndView.addObject("tagList",tagList);
        modelAndView.addObject("blog",blog);
        //跳转到编辑博客视图
        modelAndView.setViewName("admin/blog-edit");
        return modelAndView;
    }

    /**
     * 重新编辑博客后保存
     * @param blogId 博客ID
     * @param title 博客标题
     * @param content 博客正文内容
     * @param tagList 博客的标签列表
     */
    @RequestMapping("/blogReEdit")
    public String blogReEdit(int blogId,String title,String content,String[] tagList){
        //保存重新编辑后的博客基本信息
        blogService.blogReEdit(blogId,title,content);
        //保存重编辑后的博客标签信息
        blogService.saveBlogTag(blogId,tagList);
        return "redirect:/admin/blogs";
    }

}
