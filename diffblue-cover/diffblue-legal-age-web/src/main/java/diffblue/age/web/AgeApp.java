package diffblue.age.web;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.resource.ServerResource;
import org.restlet.routing.Router;

public class AgeApp extends ServerResource {
    public static void main(String[] args) throws Exception {
        var router = new Router();
        router.attach("/age", AgeResource.class);

        // Create Component
        var component = new Component();
        component.getServers().add(Protocol.HTTP, 8182);
        component.getDefaultHost().attach(router);
        component.start();
    }
}
