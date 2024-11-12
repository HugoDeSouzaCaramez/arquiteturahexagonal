package com.arquiteturahexagonal;

import com.arquiteturahexagonal.application.ports.input.RouterNetworkInputPort;
import com.arquiteturahexagonal.application.ports.output.RouterNetworkOutputPort;
import com.arquiteturahexagonal.application.usecase.RouterNetworkUseCase;
import com.arquiteturahexagonal.framework.adapters.input.RouterNetworkAdapter;
import com.arquiteturahexagonal.framework.adapters.input.rest.RouterNetworkRestAdapter;
import com.arquiteturahexagonal.framework.adapters.input.stdin.RouterNetworkCLIAdapter;
import com.arquiteturahexagonal.framework.adapters.output.file.RouterNetworkFileAdapter;
import com.arquiteturahexagonal.framework.adapters.output.h2.RouterNetworkH2Adapter;
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
    private RouterNetworkOutputPort outputPort;

    public static void main(String... args)  {
        var adapter = "cli";
        if(args.length>0) {
            adapter = args[0];
        }
        new App().setAdapter(adapter);
    }

    void setAdapter(String adapter) {
        switch (adapter) {
            case "rest" -> {
                outputPort = RouterNetworkH2Adapter.getInstance();
                usecase = new RouterNetworkInputPort(outputPort);
                inputAdapter = new RouterNetworkRestAdapter(usecase);
                rest();
            }
            default -> {
                outputPort = RouterNetworkFileAdapter.getInstance();
                usecase = new RouterNetworkInputPort(outputPort);
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