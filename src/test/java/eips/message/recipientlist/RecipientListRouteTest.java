package eips.message.recipientlist;

import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class RecipientListRouteTest extends CamelTestSupport {

    @Test
    public void testRecipientList_GivenHeaderWithRecipientList_ShouldSendToAllEndpoints() throws InterruptedException {
        MockEndpoint mockOne = getMockEndpoint("mock:one");
        mockOne.expectedMessageCount(1);
        mockOne.expectedBodiesReceived("test body");

        MockEndpoint mockTwo = getMockEndpoint("mock:two");
        mockTwo.expectedMessageCount(1);
        mockTwo.expectedBodiesReceived("test body");

        MockEndpoint mockThree = getMockEndpoint("mock:three");
        mockThree.expectedMessageCount(1);
        mockThree.expectedBodiesReceived("test body");

        Exchange exchange = new DefaultExchange(context);
        exchange.getIn().setBody("test body");
        exchange.getIn().setHeader("recipientList", "mock:one,mock:two,mock:three");

        template.send("direct:dynamicList", exchange);

        assertMockEndpointsSatisfied();
    }

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new RecipientListRoute();
    }
}