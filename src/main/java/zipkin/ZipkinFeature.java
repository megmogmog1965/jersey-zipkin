package zipkin;

import brave.Tracing;
import brave.jaxrs2.TracingFeature;
import brave.sampler.Sampler;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.urlconnection.URLConnectionSender;
import javax.ws.rs.core.Feature;
import java.util.Optional;

public final class ZipkinFeature {

    /**
     * create JAX-RS feature for Zipkin distributed tracing.
     *
     * @param localServiceName Service name for Zipkin Web UI.
     * @return A JAX-RS feature.
     */
    public static Feature create(String localServiceName) {
        // read zipkin server hostname/port from environment variables.
        String zipkinHost = Optional.ofNullable(System.getenv("ZIPKIN_HOST")) // from "ZIPKIN_HOST" environment variable.
                .orElse("localhost"); // ==> default value.
        String zipkinPort = Optional.ofNullable(System.getenv("ZIPKIN_PORT")) // from "ZIPKIN_PORT" environment variable.
                .orElse("9411"); // ==> default value.

        // create a zipkin reporter.
        AsyncReporter<Span> asyncReporter = AsyncReporter
                .builder(URLConnectionSender.create("http://" + zipkinHost + ":" + zipkinPort + "/api/v1/spans"))
                .build();

        // create a zipkin tracing.
        Tracing tracing = Tracing.newBuilder()
                .reporter(asyncReporter)
                .localServiceName(localServiceName)
                .sampler(Sampler.ALWAYS_SAMPLE)
                .build();

        // create a JAX-RS feature.
        Feature tracingFeature = TracingFeature.create(tracing);

        return tracingFeature;
    }
}