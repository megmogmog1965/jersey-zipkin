package zipkin;

import brave.Tracing;
import brave.jaxrs2.TracingFeature;
import brave.sampler.Sampler;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.urlconnection.URLConnectionSender;
import javax.ws.rs.core.Feature;

public class ZipkinFeature {

    public static Feature create(String localServiceName) {
        // create a zipkin reporter.
        AsyncReporter<Span> asyncReporter = AsyncReporter
                .builder(URLConnectionSender.create("http://localhost:9411/api/v1/spans"))
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
