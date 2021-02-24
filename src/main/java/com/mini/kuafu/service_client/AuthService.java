package com.mini.kuafu.service_client;

/**
 * @author kenzo
 * @create 2021-02-06 10:29
 */
public interface AuthService {

   String login(String username, String password);

   void logout(String sessionId);
}
