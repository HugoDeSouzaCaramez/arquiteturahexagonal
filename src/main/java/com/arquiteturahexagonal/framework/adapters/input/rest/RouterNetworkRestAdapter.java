package com.arquiteturahexagonal.framework.adapters.input.rest;

import com.arquiteturahexagonal.application.usecase.RouterNetworkUseCase;
import com.arquiteturahexagonal.framework.adapters.input.RouterNetworkAdapter;

public class RouterNetworkRestAdapter extends RouterNetworkAdapter {

    public RouterNetworkRestAdapter(RouterNetworkUseCase routerNetworkUseCase) {
        this.routerNetworkUseCase = routerNetworkUseCase;
    }
}

