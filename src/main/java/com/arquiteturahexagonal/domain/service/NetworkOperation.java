package com.arquiteturahexagonal.domain.service;

import com.arquiteturahexagonal.domain.entity.Router;
import com.arquiteturahexagonal.domain.specification.CIDRSpecification;
import com.arquiteturahexagonal.domain.specification.NetworkAmountSpecification;
import com.arquiteturahexagonal.domain.specification.NetworkAvailabilitySpecification;
import com.arquiteturahexagonal.domain.specification.RouterTypeSpecification;
import com.arquiteturahexagonal.domain.vo.Network;

public class NetworkOperation {

    public static Router createNewNetwork(Router router, Network network) {
        var availabilitySpec = new NetworkAvailabilitySpecification(network.address(), network.name(), network.cidr());
        var cidrSpec = new CIDRSpecification();
        var routerTypeSpec = new RouterTypeSpecification();
        var amountSpec = new NetworkAmountSpecification();

        if(cidrSpec.isSatisfiedBy(network.cidr()))
            throw new IllegalArgumentException("CIDR is below "+CIDRSpecification.MINIMUM_ALLOWED_CIDR);

        if(!availabilitySpec.isSatisfiedBy(router))
            throw new IllegalArgumentException("Address already exist");

        if(amountSpec.and(routerTypeSpec).isSatisfiedBy(router)) {
            Network newNetwork = router.createNetwork(network.address(), network.name(), network.cidr());
            router.addNetworkToSwitch(newNetwork);
        }
        return router;
    }
}