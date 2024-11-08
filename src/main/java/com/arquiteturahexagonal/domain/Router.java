package com.arquiteturahexagonal.domain;

//Router entity class
public class Router {
    private final RouterType type;
    private final RouterId id;
    public Router(Type type, RouterId id) {
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

