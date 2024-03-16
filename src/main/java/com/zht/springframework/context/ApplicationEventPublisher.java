package com.zht.springframework.context;


// 被观察者
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
