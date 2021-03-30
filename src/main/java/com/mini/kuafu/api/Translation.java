package com.mini.kuafu.api;

import com.mini.kuafu.service_client.BaiduServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kenzo
 * @create 2021-02-04 17:48
 */
@RestController
public class Translation {
    private final BaiduServer baiduServer;

    public Translation(BaiduServer baiduServer) {
        this.baiduServer = baiduServer;
    }

    @PostMapping("/translation")
    public Object translation() {
        return baiduServer.translation("苹果");
    }

}
