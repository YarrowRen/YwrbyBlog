package cn.ywrby.controller;

import cn.ywrby.domain.Blog;
import cn.ywrby.domain.Tag;
import cn.ywrby.domain.User;
import cn.ywrby.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /**
     * 用户登录界面
     * @return
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
     */
    @RequestMapping("/blogs")
    public ModelAndView blogsEdit(){
        ModelAndView modelAndView=new ModelAndView();
        //获取全部博客
        List<Blog> blogList=adminService.findAllBlogs();
        //将博客数据传入
        modelAndView.addObject("blogList",blogList);
        //跳转页面
        modelAndView.setViewName("admin/blogs-edit");
        return modelAndView;
    }

    /**
     * 标签管理界面
     */
    @RequestMapping("/tags")
    public ModelAndView tagsEdit(){
        ModelAndView modelAndView=new ModelAndView();
        List<Tag> tagList=adminService.findAllTags();
        modelAndView.addObject("tagList",tagList);
        modelAndView.setViewName("admin/tags-edit");
        return modelAndView;
    }

    @RequestMapping("/tagSaveUI")
    public ModelAndView tagSaveUI(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("admin/tag-save");
        return modelAndView;
    }

    @RequestMapping("/tagSave")
    public String tagSave(String tagName){
        adminService.saveTag(tagName);
        return "redirect:/admin/tags";
    }

    @RequestMapping("/delTag/{tagId}")
    public String delTag(@PathVariable(value = "tagId",required = true) int tagId){
        adminService.delTag(tagId);
        return "redirect:/admin/tags";
    }

    @RequestMapping("/editTagUI/{tagId}")
    public ModelAndView editTagUI(@PathVariable(value = "tagId",required = true) int tagId){
        ModelAndView modelAndView=new ModelAndView();
        Tag tag=adminService.findTagById(tagId);
        modelAndView.addObject("tag",tag);
        modelAndView.setViewName("admin/tag-edit");
        return modelAndView;
    }

    @RequestMapping("/tagReEdit")
    public String tagReEdit(int tagId,String tagName){
        adminService.tagReEdit(tagId,tagName);
        return "redirect:/admin/tags";
    }


}
