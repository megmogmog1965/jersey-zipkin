package client;

import zipkin.ZipkinFeature;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.Response;

public class MyClient {

    public static void callClient() {
        // create a JAX-RS feature.
        Feature tracingFeature = ZipkinFeature.create("client");

        // call client http request.
        Response r = ClientBuilder.newClient()
                .register(tracingFeature)
                .target("http://localhost:8080/myapp/hello")
                .request()
                .get();

        // logging.
        System.out.println(r.getStatus() + ", " + r.readEntity(String.class));
    }
}
