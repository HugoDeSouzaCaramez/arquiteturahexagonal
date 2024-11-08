package com.arquiteturahexagonal.domain;

import java.util.ArrayList;
import java.util.List;

//Router entity class
public class Router {
    private final RouterType type;
    private final RouterId id;
    public Router(RouterType type, RouterId id) {
        this.type = type;
        this.id = id;
    }
    public static List<Router> checkRouter(
            RouterType type, List<Router> routers) {
        var routersList = new ArrayList<Router>();
        routers.forEach(router -> {
            if(router.type == type ){
                routersList.add(router);
            }
        });
        return routersList;
    }
}

