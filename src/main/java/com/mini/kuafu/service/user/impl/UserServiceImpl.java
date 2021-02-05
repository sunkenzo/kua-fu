package com.mini.kuafu.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mini.kuafu.service.user.dao.UserMapper;
import com.mini.kuafu.service.user.domain.User;
import com.mini.kuafu.service_client.UserService;
import org.springframework.stereotype.Service;

/**
 * @author kenzo
 * @create 2021-02-05 13:40
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
