package eips.message.filter;

import org.apache.camel.builder.RouteBuilder;

public class MessageFilterRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("direct:filterRoute")
                .filter(simple("${header.type} == 'widget'"))
                    .to("mock:widget")
                .end();

        from("direct:filterWithBean")
                .filter().method(TypeChecker.class, "isWidget")
                .to("mock:widget")
                .end();
    }

}
