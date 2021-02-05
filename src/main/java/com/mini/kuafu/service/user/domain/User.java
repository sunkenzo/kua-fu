package com.mini.kuafu.service.user.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.mini.kuafu.service.config.Tables;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@TableName(Tables.USER)
public class User {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @NotNull
    private String userName;

    @TableLogic
    private Short deleted;

    private Date createTime;

    @TableField(update = "now()")
    private Date updateTime;

}