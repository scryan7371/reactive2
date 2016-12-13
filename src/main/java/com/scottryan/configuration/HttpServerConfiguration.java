package com.scottryan.configuration;

import com.scottryan.handlers.PersonHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.RequestPredicates;
import org.springframework.web.reactive.function.RouterFunction;
import org.springframework.web.reactive.function.RouterFunctions;
import reactor.ipc.netty.http.HttpServer;

/**
 * Configuration of the HTTP Server
 */
@Configuration
public class HttpServerConfiguration {

    @Bean
    RouterFunction<?> router(PersonHandler handler) {
        return RouterFunctions.route(
                RequestPredicates.GET("/people")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::all)
                .and(RouterFunctions.route(
                        RequestPredicates.GET("/people/{id}"), handler::byId
                ));
    }

    @Bean
    HttpServer server(RouterFunction<?> router) {
        HttpHandler handler = RouterFunctions.toHttpHandler(router);
        HttpServer httpServer = HttpServer.create(8085);
        httpServer.start(new ReactorHttpHandlerAdapter(handler));
        return httpServer;
    }
}
