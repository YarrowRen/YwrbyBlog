package cn.ywrby.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
@Data
public class User {
    private int id;          //用户ID
    private String nickname; //用户昵称
    private String username; //用户登录名
    private String password; //用户密码
    private String email;    //用户邮箱
    private int permission;  //用户执行权限
    private String avator;   //用户头像
    private Date registDate; //用户创建日期
}
