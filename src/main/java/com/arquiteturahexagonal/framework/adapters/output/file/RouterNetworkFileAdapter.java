package com.arquiteturahexagonal.framework.adapters.output.file;

import com.arquiteturahexagonal.application.ports.output.RouterNetworkOutputPort;
import com.arquiteturahexagonal.domain.entity.Router;
import com.arquiteturahexagonal.domain.entity.Switch;
import com.arquiteturahexagonal.domain.vo.*;

import java.util.ArrayList;
import java.util.List;


/**
 * RouterNetworkFileAdapter usa uma estrutura de dados com suporte de arquivo JSON
 * */
public class RouterNetworkFileAdapter implements RouterNetworkOutputPort {

    private static RouterNetworkFileAdapter instance;
    private List<RouterJson> routers;
    private InputStream resource;
    private ObjectMapper objectMapper;

    @Override
    public Router fetchRouterById(RouterId routerId) {
        var router = new Router();
        for(RouterJson routerJson: routers){
            if(routerJson.getRouterId().equals(routerId.getUUID())){
                router = RouterJsonFileMapper.toDomain(routerJson);
                break;
            }
        }
        return router;
    }

    @Override
    public boolean persistRouter(Router router) {
        var routerJson = RouterJsonFileMapper.toJson(router);
        try {
            String localDir = Paths.get("").toAbsolutePath().toString();
            File file = new File(localDir + "/inventory.json");
            file.delete();
            objectMapper.writeValue(file, routerJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void readJsonFile(){
        try {
            this.routers = objectMapper.
                    readValue(
                            resource,
                            new TypeReference<List<RouterJson>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private RouterNetworkFileAdapter() {
        this.objectMapper = new ObjectMapper();
        this.resource = getClass().
                getClassLoader().
                getResourceAsStream("inventory.json");
        readJsonFile();
    }

    public static RouterNetworkFileAdapter getInstance() {
        if (instance == null) {
            instance = new RouterNetworkFileAdapter();
        }
        return instance;
    }
}