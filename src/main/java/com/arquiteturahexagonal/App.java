package com.arquiteturahexagonal;

import com.arquiteturahexagonal.framework.adapters.input.stdin.RouterViewCLIAdapter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String... args) {
        var cli = new RouterViewCLIAdapter();
        var type = "CORE";
        System.out.println(cli.obtainRelatedRouters(type));
    }
}
