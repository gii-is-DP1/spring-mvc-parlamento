package com.japarejo.springmvc;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationListener implements ApplicationListener<AbstractAuthenticationEvent> {

    public void onApplicationEvent(AbstractAuthenticationEvent event) {
        System.out.println(event.toString());
    }
}