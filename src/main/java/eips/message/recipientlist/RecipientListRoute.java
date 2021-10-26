package eips.message.recipientlist;

import org.apache.camel.builder.RouteBuilder;

public class RecipientListRoute extends RouteBuilder {
    @Override
    public void configure() {

        //STATIC Recipient list
        from("direct:staticList")
                .recipientList(constant("seda:x,seda:y,seda:z"));

        //DYNAMIC Recipient list
        from("direct:dynamicList")
                .log("Recipient List: ${header.recipientList}")
                .recipientList(header("recipientList"));

        //Delimiter implementation
        from("direct:staticListWithDelimiter")
                .recipientList(constant("seda:x;seda:y;seda:z"), ";");

        //Parallel processing with timeout
        from("direct:parallelProcessingWithTimeout")
                .recipientList(constant("seda:x;seda:y;seda:z"), ";")
                .parallelProcessing().timeout(1000);

        //Stop on exception
        from("direct:stopOnException")
                .recipientList(constant("seda:x;seda:y;seda:z"), ";")
                .stopOnException();

        //Ignore invalid endpoints
        from("direct:ignoreInvalidEndpoints")
                .recipientList(constant("seda:x;seda:y;seda:z"), ";")
                .ignoreInvalidEndpoints();
    }
}
