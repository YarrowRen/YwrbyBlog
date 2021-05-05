package cn.ywrby.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 博客文章实体类
 */
@Data
@Slf4j
public class Blog {
    private int id;           //文章ID，唯一标识符
    private String title;     //文章标题
    private String content;   //文章文本内容
    private int views;        //浏览次数
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate; //发布时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;  //更新时间
}
