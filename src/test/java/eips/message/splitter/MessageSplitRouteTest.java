package eips.message.splitter;

import eips.message.filter.MessageFilterRoute;
import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.List;

public class MessageSplitRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new MessageSplitRoute();
    }

    @Test
    public void testFilterRoute_givenWidget() throws Exception {
        MockEndpoint mockEndpoint = getMockEndpoint("mock:split");
        mockEndpoint.expectedMessageCount(8);

        Exchange exchange = new DefaultExchange(new DefaultCamelContext());
        exchange.getMessage().setBody(List.of("1,2,3,4,5,6,7,8"));

        template.send("direct:splitterExample", exchange);

        assertMockEndpointsSatisfied();
    }
}