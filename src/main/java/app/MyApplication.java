package app;

import zipkin.ZipkinFeature;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Feature;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Pure JAX-RS Application implementation.
 *
 * @deprecated YOU SHOUD USE org.glassfish.jersey.server.ResourceConfig FOR JERSEY.
 */
@ApplicationPath("")
public class MyApplication extends Application {

    @Override
    public Set<Object> getSingletons() {
        // create a JAX-RS feature.
        Feature tracingFeature = ZipkinFeature.create("server");

        return new LinkedHashSet<>(Arrays.asList(tracingFeature));
    }
}