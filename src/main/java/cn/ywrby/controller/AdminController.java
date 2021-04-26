package cn.ywrby.controller;

import cn.ywrby.domain.Blog;
import cn.ywrby.domain.User;
import cn.ywrby.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired(required = false)
    private AdminService adminService;

    @GetMapping
    public ModelAndView loginPage(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("admin/login");
        return modelAndView;
    }

    /**
     * 实现用户登录操作,并在每次用户登录时
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

    @RequestMapping("/blogs")
    public ModelAndView blogsEdit(){
        ModelAndView modelAndView=new ModelAndView();
        List<Blog> blogList=adminService.findAllBlogs();
        modelAndView.addObject("blogList",blogList);
        modelAndView.setViewName("admin/blogs-edit");
        return modelAndView;
    }

    @RequestMapping("/tags")
    public ModelAndView tagsEdit(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("admin/tags-edit");
        return modelAndView;
    }


}
