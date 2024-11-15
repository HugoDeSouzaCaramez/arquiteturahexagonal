package com.arquiteturahexagonal;

import com.arquiteturahexagonal.application.ports.input.RouterNetworkInputPort;
import com.arquiteturahexagonal.application.ports.output.NotifyEventOutputPort;
import com.arquiteturahexagonal.application.ports.output.RouterNetworkOutputPort;
import com.arquiteturahexagonal.application.usecase.RouterNetworkUseCase;
import com.arquiteturahexagonal.framework.adapters.input.RouterNetworkAdapter;
import com.arquiteturahexagonal.framework.adapters.input.rest.RouterNetworkRestAdapter;
import com.arquiteturahexagonal.framework.adapters.input.stdin.RouterNetworkCLIAdapter;
import com.arquiteturahexagonal.framework.adapters.input.websocket.NotifyEventWebSocketAdapter;
import com.arquiteturahexagonal.framework.adapters.output.file.RouterNetworkFileAdapter;
import com.arquiteturahexagonal.framework.adapters.output.h2.RouterNetworkH2Adapter;
import com.arquiteturahexagonal.framework.adapters.output.kafka.NotifyEventKafkaAdapter;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    private RouterNetworkAdapter inputAdapter;
    private RouterNetworkUseCase usecase;
    private RouterNetworkOutputPort routerOutputPort;
    private NotifyEventOutputPort notifyOutputPort;

    public static void main(String... args) throws IOException, InterruptedException {
        var adapter = "rest";
        if(args.length>0) {
            adapter = args[0];
        }
        new App().setAdapter(adapter);
    }

    void setAdapter(String adapter) throws IOException, InterruptedException {
        switch (adapter) {
            case "rest" -> {
                routerOutputPort = RouterNetworkH2Adapter.getInstance();
                notifyOutputPort = NotifyEventKafkaAdapter.getInstance();
                usecase = new RouterNetworkInputPort(routerOutputPort, notifyOutputPort);
                inputAdapter = new RouterNetworkRestAdapter(usecase);
                rest();
                NotifyEventWebSocketAdapter.startServer();
            }
            default -> {
                routerOutputPort = RouterNetworkFileAdapter.getInstance();
                usecase = new RouterNetworkInputPort(routerOutputPort);
                inputAdapter = new RouterNetworkCLIAdapter(usecase);
                cli();
            }
        }
    }

    private void cli() {
        Scanner scanner = new Scanner(System.in);
        inputAdapter.processRequest(scanner);
    }

    private void rest() {
        try {
            System.out.println("REST endpoint listening on port 8080...");
            var httpserver = HttpServer.create(new InetSocketAddress(8080), 0);
            inputAdapter.processRequest(httpserver);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}