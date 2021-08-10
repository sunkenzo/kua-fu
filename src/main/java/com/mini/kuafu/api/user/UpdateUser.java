package com.mini.kuafu.api.user;

import com.mini.kuafu.common.response.ApiResult;
import com.mini.kuafu.dto.UserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kenzo
 * @create 2021-08-10 17:16
 */
@RestController
@Log4j2
public class UpdateUser {
    static final String PATH = "/user/update";

    @PostMapping(PATH)
    private ApiResult process(@RequestBody @Validated(UserDto.Update.class) UserDto userDto) {
        log.debug(userDto);
        return ApiResult.ok();
    }
}
