package eips.message.resequencer;

import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.util.List;

public class ResequenceRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new ResequenceRoute();
    }

    @Test
    public void testResequenceRoute() throws InterruptedException {
        MockEndpoint mockEndpoint = getMockEndpoint();
        mockEndpoint.expectedBodiesReceived(List.of(1, 2, 3));

        produceMessages("direct:resequence");

        mockEndpoint.assertIsSatisfied();
    }

    @Test
    public void testResequenceRoute_Reverse() throws InterruptedException {
        MockEndpoint mockEndpoint = getMockEndpoint();
        mockEndpoint.expectedBodiesReceived(List.of(3, 2, 1));

        produceMessages("direct:resequenceReverse");

        mockEndpoint.assertIsSatisfied();
    }

    @Test
    public void testResequenceRoute_UsingBody() throws InterruptedException {
        MockEndpoint mockEndpoint = getMockEndpoint();
        mockEndpoint.expectedBodiesReceived(List.of(1, 2, 3));

        produceMessages("direct:resequenceBody");

        mockEndpoint.assertIsSatisfied();
    }

    private MockEndpoint getMockEndpoint() {
        MockEndpoint mockEndpoint = getMockEndpoint("mock:result");
        mockEndpoint.expectedMessageCount(3);
        return mockEndpoint;
    }

    private void produceMessages(String s) {
        Exchange exchangeOne = new DefaultExchange(new DefaultCamelContext());
        exchangeOne.getIn().setHeader("order", 1);
        exchangeOne.getIn().setBody(1);
        Exchange exchangeTwo = new DefaultExchange(new DefaultCamelContext());
        exchangeTwo.getIn().setHeader("order", 2);
        exchangeTwo.getIn().setBody(2);
        Exchange exchangeThree = new DefaultExchange(new DefaultCamelContext());
        exchangeThree.getIn().setHeader("order", 3);
        exchangeThree.getIn().setBody(3);

        template.send(s, exchangeTwo);
        template.send(s, exchangeOne);
        template.send(s, exchangeThree);
    }
}