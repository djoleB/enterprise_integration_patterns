package eips.message.dynamicrouter;

import org.apache.camel.builder.RouteBuilder;

public class DynamicRouterRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("direct:dynamicRouter")
                .dynamicRouter(method(MyDynamicRouterBean.class, "route"));

    }
}
