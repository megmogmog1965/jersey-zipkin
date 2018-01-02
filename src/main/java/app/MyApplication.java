package app;

import zipkin.ZipkinFeature;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Feature;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@ApplicationPath("")
public class MyApplication extends Application {

    @Override
    public Set<Object> getSingletons() {
        // create a JAX-RS feature.
        Feature tracingFeature = ZipkinFeature.create("server");

        // logging.
        System.out.println("getSingletons");

        return new LinkedHashSet<>(Arrays.asList(tracingFeature));
    }
}