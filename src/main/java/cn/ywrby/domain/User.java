package cn.ywrby.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
@Data
public class User {
    private int id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private int permission;
    private String avator;
    private Date registDate;
}
