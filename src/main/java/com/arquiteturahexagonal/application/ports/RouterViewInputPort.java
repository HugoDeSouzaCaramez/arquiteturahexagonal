package com.arquiteturahexagonal.application.ports;
//
import com.arquiteturahexagonal.application.usecase.RouterViewUseCase;
import com.arquiteturahexagonal.domain.Router;

import java.util.List;
import java.util.function.Predicate;

public class RouterViewInputPort implements RouterViewUseCase {
    private RouterViewOutputPort routerListOutputPort;
    public RouterViewInput
    Port(RouterViewOutputPort routerViewOutputPort) {
        this.routerListOutputPort = routerViewOutputPort;
    }
    @Override
    public List<Router> getRouters(Predicate<Router> filter) {
        var routers = routerListOutputPort.fetchRouters();
        return Router.retrieveRouter(routers, filter);
    }
}
