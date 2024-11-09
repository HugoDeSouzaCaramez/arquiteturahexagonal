package com.arquiteturahexagonal.domain.service;

public class NetworkOperation {
    public void createNewNetwork(Router router, IP
            address, String name, int cidr) {
        var availabilitySpec = new
                NetworkAvailabilitySpecification(address, name,
                cidr);
        var cidrSpec = new CIDRSpecification();
        var routerTypeSpec = new
                RouterTypeSpecification();
        var amountSpec = new
                NetworkAmountSpecification();
        if(cidrSpec.isSatisfiedBy(cidr))
            throw new IllegalArgumentException("CIDR is
                    below
                    "+CIDRSpecification.MINIMUM_ALLOWED_CIDR);
        if(availabilitySpec.isSatisfiedBy(router))
            throw new IllegalArgumentException("Address
                    already exist");
        if(amountSpec.and(routerTypeSpec).isSatisfiedBy
                (router)) {
            Network network =
                    router.createNetwork(address, name, cidr);
            router.addNetworkToSwitch(network);
        }
    }
}
