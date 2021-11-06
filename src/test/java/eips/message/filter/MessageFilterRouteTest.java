package eips.message.filter;

import eips.message.filter.MessageFilterRoute;
import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class MessageFilterRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new MessageFilterRoute();
    }

    @Test
    public void testFilterRoute_givenWidget() throws Exception {
        MockEndpoint mockEndpoint = getMockEndpoint("mock:widget");
        mockEndpoint.expectedBodiesReceived("Widget body!");
        mockEndpoint.expectedMessageCount(1);

        Exchange exchange = new DefaultExchange(new DefaultCamelContext());
        exchange.getIn().setBody("Widget body!");
        exchange.getIn().setHeader("type", "widget");

        template.send("direct:filterRoute", exchange);

        assertMockEndpointsSatisfied();
    }

    @Test
    public void testFilterRoute_givenGadget() throws Exception {
        MockEndpoint mockEndpoint = getMockEndpoint("mock:widget");
        mockEndpoint.expectedMessageCount(0);

        Exchange exchange = new DefaultExchange(new DefaultCamelContext());
        exchange.getIn().setBody("Gadget body!");
        exchange.getIn().setHeader("type", "gadget");

        template.send("direct:filterRoute", exchange);

        assertMockEndpointsSatisfied();
    }

    @Test
    public void testFilterWithBeanRoute() throws Exception {
        MockEndpoint mockEndpoint = getMockEndpoint("mock:widget");
        mockEndpoint.expectedBodiesReceived("Widget body!");
        mockEndpoint.expectedMessageCount(1);

        Exchange exchange = new DefaultExchange(new DefaultCamelContext());
        exchange.getIn().setBody("Widget body!");
        exchange.getIn().setHeader("type", "widget");

        template.send("direct:filterWithBean", exchange);

        assertMockEndpointsSatisfied();
    }

    @Test
    public void testFilterWithBeanRoute_givenGadget() throws Exception {
        MockEndpoint mockEndpoint = getMockEndpoint("mock:widget");
        mockEndpoint.expectedMessageCount(0);

        Exchange exchange = new DefaultExchange(new DefaultCamelContext());
        exchange.getIn().setBody("Gadget body!");
        exchange.getIn().setHeader("type", "gadget");

        template.send("direct:filterWithBean", exchange);

        assertMockEndpointsSatisfied();
    }
}
