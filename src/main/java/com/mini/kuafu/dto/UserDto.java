package com.mini.kuafu.dto;

import com.mini.kuafu.common.validator.Mobile;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author kenzo
 * @create 2021-08-10 16:52
 */
@Data
@ToString
public class UserDto {

    @NotNull(message = "用户ID不能为空", groups = Update.class)
    private Long id;

    @NotNull(message = "用户名不能为空", groups = {Save.class, Update.class})
    @Length(min = 3, max = 25, message = "用户名 3-25 个字符", groups = {Save.class, Update.class})
    private String userName;

    @NotNull(message = "手机号不能为空", groups = {Save.class, Update.class})
    @Mobile(groups = {Save.class, Update.class})
    private String mobile;

    public interface Save {}
    public interface Update {}
}
