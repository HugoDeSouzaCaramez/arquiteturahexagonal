package com.arquiteturahexagonal.framework.adapters.input;

import com.arquiteturahexagonal.application.usecase.RouterNetworkUseCase;
import com.arquiteturahexagonal.domain.entity.Router;
import com.arquiteturahexagonal.domain.vo.IP;
import com.arquiteturahexagonal.domain.vo.Network;
import com.arquiteturahexagonal.domain.vo.RouterId;

import java.util.Map;

public abstract class RouterNetworkAdapter {
    protected Router router;
    protected RouterNetworkUseCase routerNetworkUseCase;
    public Router addNetworkToRouter(Map<String, String> params){
        var routerId = RouterId.withId(params.get("routerId"));
        var network = new Network(IP.fromAddress(
                params.get("address")),
                params.get("name"),
                Integer.valueOf(params.get("cidr")));
        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }
    public abstract Router processRequest(
            Object requestParams);
}
