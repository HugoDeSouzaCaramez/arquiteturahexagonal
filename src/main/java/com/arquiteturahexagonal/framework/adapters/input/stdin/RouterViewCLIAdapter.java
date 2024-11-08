package com.arquiteturahexagonal.framework.adapters.input.stdin;
//
import com.arquiteturahexagonal.application.ports.input.RouterViewInputPort;
import com.arquiteturahexagonal.application.usecase.RouterViewUseCase;
import com.arquiteturahexagonal.domain.Router;

public class RouterViewCLIAdapter {
    private RouterViewUseCase routerViewUseCase;
    public RouterViewCLIAdapter(){
        setAdapters();
    }
    public List<Router> obtainRelatedRouters(String type) {
        RelatedRoutersCommand relatedRoutersCommand =
                new RelatedRoutersCommand(type);
        return routerViewUseCase.getRelatedRouters(relatedRoutersCommand);
    }
    private void setAdapters(){
        this.routerViewUseCase = new RouterViewInputPort()
                (RouterViewFileAdapter.getInstance());
    }
}

