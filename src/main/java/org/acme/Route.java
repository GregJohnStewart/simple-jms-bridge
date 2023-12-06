package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class Route  extends RouteBuilder {

    @Inject
    RouteConfigs routeConfig;

    @Override
    public void configure() throws Exception {
        // produces messages to kafka
        from("jms:queue://{{amq-processing.consuming.address}}?connectionFactory=#consumingConnectionFactory")
                .routeId("BridgeRoute")
                .to("jms:queue://{{amq-processing.producing.address}}?connectionFactory=#producingConnectionFactory")
                .log("Message sent: \"${body}\"");
    }
}