package com.nicekkong.jpa.domain;

import org.springframework.context.event.EventListener;

public class PostListner {

    /**
     * 이벤트가 발생했을 때 해야하는 Job
     * @param event
     */
    @EventListener
    public void onApplicationEvent(PostPublishedEvent event) {
        System.out.println("-------------------------------------");
        System.out.println(event.getPost() + " is published~!!");
        System.out.println("-------------------------------------");
    }
}
