package eips.message.resequencer;

import org.apache.camel.builder.RouteBuilder;

public class ResequenceRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("direct:resequence")
                .resequence(header("order"))
                .to("mock:result");

        from("direct:resequenceReverse")
                .resequence(header("order")).reverse()
                .to("mock:result");

        from("direct:resequenceBody")
                .resequence(body())
                .to("mock:result");

    }
}
