package eips.message.contentbasedrouter;

import org.apache.camel.builder.RouteBuilder;

public class ContentBasedRouterRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("direct:contentBasedRouter")
                .choice()
                .when(header("type").isEqualTo("widget"))
                    .to("mock:widget")
                .when(header("type").isEqualTo("gadget"))
                    .to("mock:gadget")
                .otherwise()
                    .to("mock:other");

    }
}
