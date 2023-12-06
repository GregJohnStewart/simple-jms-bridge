package org.acme;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "amq-processing")
public interface RouteConfigs {

    RouteConfig consuming();
    RouteConfig producing();

    interface RouteConfig {
        AmqServerConfig server();
        String address();
    }

    interface AmqServerConfig {
        String host();
        int port();
        String user();
        String pass();
    }

}