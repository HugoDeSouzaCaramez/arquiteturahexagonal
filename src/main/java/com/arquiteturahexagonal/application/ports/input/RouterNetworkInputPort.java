package com.arquiteturahexagonal.application.ports.input;

import com.arquiteturahexagonal.application.ports.output.RouterNetworkOutputPort;
import com.arquiteturahexagonal.application.usecase.RouterNetworkUseCase;
import com.arquiteturahexagonal.domain.entity.Router;
import com.arquiteturahexagonal.domain.service.NetworkOperation;
import com.arquiteturahexagonal.domain.vo.Network;
import com.arquiteturahexagonal.domain.vo.RouterId;


/**
 * O método addNetworkToRouter , que retorna um objeto Router , é o único método público que é exposto pela porta de
 * entrada. Tornamos todos os outros métodos privados porque eles não devem ser usados fora do contexto desta
 * porta de entrada. A porta de entrada inicia seu trabalho usando RouterId para recuperar um objeto Router ; então, ela
 * cria um novo objeto Network naquele objeto Router . Lembre-se, o objeto Network compreende os atributos address,
 * name e CIDR, conforme expresso na forma escrita do caso de uso. O método fetchRouter tentará obter um objeto
 * Router passando um ID RouterId para o método fetchRouterById da porta de saída . É quando a porta de entrada
 * precisará coordenar uma chamada externa que será realizada por um adaptador de saída que implementa a porta de
 * saída.
 * */
public class RouterNetworkInputPort implements RouterNetworkUseCase {
    private final RouterNetworkOutputPort
            routerNetworkOutputPort;
    public RouterNetworkInputPort(RouterNetworkOutputPort
                                          routerNetworkOutputPort){
        this.routerNetworkOutputPort =
                routerNetworkOutputPort;
    }
    @Override
    public Router addNetworkToRouter(RouterId routerId, Network network) {
        var router = fetchRouter(routerId);
        return createNetwork(router, network);
    }
    private Router fetchRouter(RouterId routerId) {
        return
                routerNetworkOutputPort.fetchRouterById(routerId);
    }
    private Router createNetwork(Router router, Network network) {
        var newRouter = NetworkOperation.createNewNetwork(router, network);
        return persistNetwork(router) ? newRouter : router;
    }
    private boolean persistNetwork(Router router) {
        return routerNetworkOutputPort.persistRouter(router);
    }
}

