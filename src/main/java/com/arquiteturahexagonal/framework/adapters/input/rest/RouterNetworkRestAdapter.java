package com.arquiteturahexagonal.framework.adapters.input.rest;

import com.arquiteturahexagonal.application.usecase.RouterNetworkUseCase;
import com.arquiteturahexagonal.domain.entity.Router;
import com.arquiteturahexagonal.framework.adapters.input.RouterNetworkAdapter;
import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class RouterNetworkRestAdapter extends RouterNetworkAdapter {

    public RouterNetworkRestAdapter(RouterNetworkUseCase routerNetworkUseCase) {
        this.routerNetworkUseCase = routerNetworkUseCase;
    }

    /**
     * When implementing a REST adapter, the processRequest method receives an Object type parameter
     * that is always cast to an HttpServer type.
     */
    @Override
    public Router processRequest(Object requestParams){
        Map<String, String> params = new HashMap<>();
        if(requestParams instanceof HttpServer) {
            var httpserver = (HttpServer) requestParams;
            httpserver.createContext("/network/add", (exchange -> {
                if ("GET".equals(exchange.getRequestMethod())) {
                    var query = exchange.getRequestURI().getRawQuery();
                    httpParams(query, params);
                    router = this.addNetworkToRouter(params);
                    ObjectMapper mapper = new ObjectMapper();
                    var routerJson = mapper.writeValueAsString(RouterJsonFileMapper.toJson(router));
                    exchange.getResponseHeaders().set("Content-Type", "application/json");
                    exchange.sendResponseHeaders(200, routerJson.getBytes().length);
                    OutputStream output = exchange.getResponseBody();
                    output.write(routerJson.getBytes());
                    output.flush();
                } else {
                    exchange.sendResponseHeaders(405, -1);
                }
                exchange.close();
            }));
            httpserver.setExecutor(null);
            httpserver.start();
        }
        return router;
    }
}

