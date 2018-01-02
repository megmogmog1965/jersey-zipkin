package main;

import app.MyApplication;
import client.MyClient;
import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        URI uri = URI.create("http://localhost:8080/myapp/");
        ResourceConfig rc = ResourceConfig.forApplicationClass(MyApplication.class);
        rc.packages(true, "resource");

        // run a jersey server.
        HttpServer httpServer = JdkHttpServerFactory.createHttpServer(uri, rc);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> httpServer.stop(0)));

        // run a client thread.
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(() -> MyClient.callClient(), 5, 5, TimeUnit.SECONDS);
    }
}
