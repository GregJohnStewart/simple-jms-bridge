package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class AmqpConfig {

    @Inject
    RouteConfigs routeConfig;

//    @Produces
//    AMQPConnectionDetails amqpConnection() {
//        return new AMQPConnectionDetails("amqp://localhost:5672");
//    }

    private ActiveMQConnectionFactory getConnectionFactory(RouteConfigs.AmqServerConfig config){
        return new ActiveMQConnectionFactory(
                config.host() + ":" + config.port(),
                config.user(),
                config.pass()
        );
    }

    @ApplicationScoped
    @Named("consumingConnectionFactory")
    @Produces
    public ActiveMQConnectionFactory consumingConnectionFactory(){
        return this.getConnectionFactory(this.routeConfig.consuming().server());
    }
    @ApplicationScoped
    @Named("producingConnectionFactory")
    @Produces
    public ActiveMQConnectionFactory producingConnectionFactory(){
        return this.getConnectionFactory(this.routeConfig.producing().server());
    }

}
