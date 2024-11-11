package com.arquiteturahexagonal.framework.adapters.input.stdin;
//
import com.arquiteturahexagonal.application.ports.input.RouterViewInputPort;
import com.arquiteturahexagonal.application.usecase.RouterNetworkUseCase;
import com.arquiteturahexagonal.application.usecase.RouterViewUseCase;
import com.arquiteturahexagonal.domain.entity.Router;
import com.arquiteturahexagonal.domain.vo.RouterType;
import com.arquiteturahexagonal.framework.adapters.input.RouterNetworkAdapter;
import com.arquiteturahexagonal.framework.adapters.output.file.RouterViewFileAdapter;

import java.util.List;

public class RouterNetworkCLIAdapter extends RouterNetworkAdapter {
    private RouterViewUseCase routerViewUseCase;
    public RouterNetworkCLIAdapter(
            RouterNetworkUseCase routerNetworkUseCase){
        this.routerNetworkUseCase = routerNetworkUseCase;
    }
    public List<Router> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(
                Router.filterRouterByType(RouterType.valueOf(type)));
    }
    private void setAdapters(){
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }
}

