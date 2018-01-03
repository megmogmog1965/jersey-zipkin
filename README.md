# jersey-zipkin

example project for Jersey (JAX-RS2) + Zipkin.

## usage

Install docker.

* [docker]

Install docker-compose.

* [docker-compose]

Run [Zipkin] by using [docker-zipkin].

```
git clone https://github.com/openzipkin/docker-zipkin.git
cd docker-zipkin
docker-compose up -d
```

Open this project as a maven project with IDE.

* [IntelliJ IDEA]
* [NetBeans]

Choose ``main/Main.java``, then run ``main.Main`` class.
You can access jersey server to register [Zipkin] tracing spans.

* http://localhost:8080/myapp/front

You can see [Zipkin] tracing logs.

* http://localhost:9411


[Jersey]:https://jersey.github.io/
[Zipkin]:https://zipkin.io/
[docker-zipkin]:https://github.com/openzipkin/docker-zipkin
[docker]:https://www.docker.com/
[docker-compose]:https://docs.docker.com/compose/
[IntelliJ IDEA]:https://www.jetbrains.com/idea/
[NetBeans]:https://ja.netbeans.org/
