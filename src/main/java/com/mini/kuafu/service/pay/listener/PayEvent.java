package com.mini.kuafu.service.pay.listener;

import org.springframework.context.ApplicationEvent;

/**
 * @author kenzo
 * @create 2021-06-29 16:21
 */
public class PayEvent extends ApplicationEvent {

    private Long userId;


    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public PayEvent(Object source, long userId) {
        super(source);
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PayEvent{" +
                "userId=" + userId +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
