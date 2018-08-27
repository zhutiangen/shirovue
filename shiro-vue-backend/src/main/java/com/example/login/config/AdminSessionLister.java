package com.example.login.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminSessionLister implements SessionListener{
    private Logger logger = LoggerFactory.getLogger(AdminSessionLister.class);
    @Override
    public void onStart(Session session) {
        logger.info("session onstart:{}", "id" + session.getId() + "host:" + session.getHost());
    }

    @Override
    public void onStop(Session session) {
        logger.info("session onStop:{}", "id" + session.getId() + "host:" + session.getHost());
    }

    @Override
    public void onExpiration(Session session) {
        logger.info("session onExpiration:{}", "id" + session.getId() + "host:" + session.getHost());
    }
}
