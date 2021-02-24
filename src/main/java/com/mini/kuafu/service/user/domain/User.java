package com.mini.kuafu.service.user.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.mini.kuafu.service.config.Tables;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@TableName(Tables.USER)
public class User {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String account;

    private String userName;

    private String password;

    private LocalDate birthday;

    @TableLogic
    private Short deleted;

    private LocalDateTime createTime;

    @TableField(update = "now()")
    private LocalDateTime updateTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", deleted=" + deleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}