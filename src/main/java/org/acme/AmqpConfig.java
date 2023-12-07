package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.jms.ConnectionFactory;
import org.apache.qpid.jms.JmsConnectionFactory;

public class AmqpConfig {

    @Inject
    RouteConfigs routeConfig;

//    @Produces
//    AMQPConnectionDetails amqpConnection() {
//        return new AMQPConnectionDetails("amqp://localhost:5672");
//    }

    private ConnectionFactory getConnectionFactory(RouteConfigs.AmqServerConfig config){
        return new JmsConnectionFactory(
                config.user(),
                config.pass(),
                "amqp://" + config.host() + ":" + config.port()
        );
    }

    @ApplicationScoped
    @Named("consumingConnectionFactory")
    @Produces
    public ConnectionFactory consumingConnectionFactory(){
        return this.getConnectionFactory(this.routeConfig.consuming().server());
    }
    @ApplicationScoped
    @Named("producingConnectionFactory")
    @Produces
    public ConnectionFactory producingConnectionFactory(){
        return this.getConnectionFactory(this.routeConfig.producing().server());
    }

}
