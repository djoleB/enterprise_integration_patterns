package eips.message.contentbasedrouter;

import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class ContentBasedRouterRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new ContentBasedRouterRoute();
    }

    @Test
    public void testContentBasedRouter_GivenWidget_shouldRouteToWidget() throws Exception {
        assertMockEndpoints(1, 0, 0);

        Exchange exchange = new DefaultExchange(new DefaultCamelContext());
        exchange.getIn().setHeader("type", "widget");

        template.send("direct:contentBasedRouter", exchange);

        assertMockEndpointsSatisfied();
    }

    @Test
    public void testContentBasedRouter_GivenGadget_shouldRouteToGadget() throws Exception {
        assertMockEndpoints(0, 1, 0);

        Exchange exchange = new DefaultExchange(new DefaultCamelContext());
        exchange.getIn().setHeader("type", "gadget");

        template.send("direct:contentBasedRouter", exchange);

        assertMockEndpointsSatisfied();
    }

    @Test
    public void testContentBasedRouter_GivenInvalidType_shouldRouteToOther() throws Exception {
        assertMockEndpoints(0, 0, 1);

        Exchange exchange = new DefaultExchange(new DefaultCamelContext());
        exchange.getIn().setHeader("type", "other");

        template.send("direct:contentBasedRouter", exchange);

        assertMockEndpointsSatisfied();
    }

    private void assertMockEndpoints(int widgetNumber, int gadgetNumber, int otherNumber) {
        MockEndpoint mockWidget = getMockEndpoint("mock:widget");
        mockWidget.expectedMessageCount(widgetNumber);
        MockEndpoint mockGadget = getMockEndpoint("mock:gadget");
        mockGadget.expectedMessageCount(gadgetNumber);
        MockEndpoint mockOther = getMockEndpoint("mock:other");
        mockOther.expectedMessageCount(otherNumber);
    }
}