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

    public RouterNetworkCLIAdapter(RouterNetworkUseCase routerNetworkUseCase) {
        this.routerNetworkUseCase = routerNetworkUseCase;
    }

    @Override
    public Router processRequest(Object requestParams) {
        var params = stdinParams(requestParams);
        router = this.addNetworkToRouter(params);
        ObjectMapper mapper = new ObjectMapper();
        try {
            var routerJson = mapper.writeValueAsString(RouterJsonFileMapper.toJson(router));
            System.out.println(routerJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return router;
    }
}

