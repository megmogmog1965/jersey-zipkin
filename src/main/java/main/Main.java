package main;

import app.MyApplication;
import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.URI;

public class Main {

    public static void main(String[] args) {
        // register JAX-RS Application class.
        ResourceConfig rc = ResourceConfig.forApplicationClass(MyApplication.class);

        // register a path of REST resources.
        rc.packages(true, "resource");

        // run a jersey server.
        URI uri = URI.create("http://localhost:8080/myapp/");
        HttpServer httpServer = JdkHttpServerFactory.createHttpServer(uri, rc);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> httpServer.stop(0)));
    }
}
