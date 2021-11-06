package eips.message.dynamicrouter;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class DynamicRouterRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new DynamicRouterRoute();
    }

    @Test
    public void testDynamicRouterGivenMock() throws InterruptedException {

        assertMockEndpoints(1, 0, 0);

        template.send("direct:dynamicRouter", exchange -> exchange.getIn().setBody("mock"));

        assertMockEndpointsSatisfied();
    }

    @Test
    public void testDynamicRouterGivenDirect() throws InterruptedException {

        assertMockEndpoints(0, 1, 0);

        template.send("direct:dynamicRouter", exchange -> exchange.getIn().setBody("direct"));

        assertMockEndpointsSatisfied();
    }

    @Test
    public void testDynamicRouterGivenSeda() throws InterruptedException {

        assertMockEndpoints(0, 0, 1);

        template.send("direct:dynamicRouter", exchange -> exchange.getIn().setBody("seda"));

        assertMockEndpointsSatisfied();
    }

    private void assertMockEndpoints(int mock, int direct, int seda) {
        MockEndpoint mockDynamicEndpoint = getMockEndpoint("mock:dynamicRouter");
        mockDynamicEndpoint.expectedMessageCount(mock);

        MockEndpoint mockDirectDynamicEndpoint = getMockEndpoint("mock:directDynamicRouter");
        mockDirectDynamicEndpoint.expectedMessageCount(direct);

        MockEndpoint mockSedaDynamicEndpoint = getMockEndpoint("mock:sedaDynamicRouter");
        mockSedaDynamicEndpoint.expectedMessageCount(seda);
    }

}