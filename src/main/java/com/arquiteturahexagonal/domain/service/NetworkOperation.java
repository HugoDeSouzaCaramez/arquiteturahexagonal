package com.arquiteturahexagonal.domain.service;

//
public class NetworkOperation {
    final private int MINIMUM_ALLOWED_CIDR = 8;
    public void createNewNetwork(Router router, IP
            address, String name, int cidr) {
        if(cidr < MINIMUM_ALLOWED_CIDR)
            throw new IllegalArgumentException("CIDR is below "+MINIMUM_ALLOWED_CIDR);
        if(isNetworkAvailable(router, address))
            throw new IllegalArgumentException("Address already exist");
                    Network =
                            router.createNetwork(address,name,cidr);
        router.addNetworkToSwitch(network);
    }
}

