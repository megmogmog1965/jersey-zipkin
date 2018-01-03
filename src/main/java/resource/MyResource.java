package resource;

import zipkin.ZipkinFeature;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("")
public class MyResource {

    /**
     * /front --> /mid --> /back
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("front")
    public Response front() {
        // create a JAX-RS feature.
        Feature tracingFeature = ZipkinFeature.create("client");

        // http client: GET /mid
        Response r = ClientBuilder.newClient()
                .register(tracingFeature)
                .target("http://localhost:8080/myapp/mid")
                .request()
                .get();

        return Response.fromResponse(r).build();
    }

    /**
     * /mid --> /back
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("mid")
    public Response mid() {
        // create a JAX-RS feature.
        Feature tracingFeature = ZipkinFeature.create("client");

        // http client: GET /back
        Response r = ClientBuilder.newClient()
                .register(tracingFeature)
                .target("http://localhost:8080/myapp/back")
                .request()
                .get();

        return Response.fromResponse(r).build();
    }

    /**
     * /back
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("back")
    public String back() {
        return "hello";
    }
}
