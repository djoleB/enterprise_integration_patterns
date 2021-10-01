package eips.message.splitter;

import org.apache.camel.builder.RouteBuilder;

import static org.apache.camel.LoggingLevel.INFO;

public class MessageSplitRoute extends RouteBuilder {
    @Override
    public void configure() {

        from("direct:splitterExample")
                .split(body().tokenize(","))
                    .log(INFO, this.log,"Split index: ${exchangeProperty.CamelSplitIndex}," +
                            "size: ${exchangeProperty.CamelSplitSize}, " +
                            "completed: ${exchangeProperty.CamelSplitComplete}, " +
                            "body: ${body}")
                    .to("mock:split")
                .end();


    }
}
