package cn.ywrby.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/test")
@Controller("testController")
public class TestController {

    @RequestMapping("/test1")
    public ModelAndView testView1(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("archive");
        return modelAndView;
    }

    @RequestMapping("/test2")
    public ModelAndView testView2(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("blog");
        return modelAndView;
    }

    @RequestMapping("/test3")
    public ModelAndView testView3(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("tags");
        return modelAndView;
    }

    @RequestMapping("/test4")
    public ModelAndView testView4(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("admin/login");
        return modelAndView;
    }
}
