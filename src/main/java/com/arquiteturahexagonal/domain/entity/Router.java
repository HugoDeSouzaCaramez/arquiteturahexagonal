package com.arquiteturahexagonal.domain.entity;

import com.arquiteturahexagonal.domain.RouterId;
import com.arquiteturahexagonal.domain.RouterType;

import java.util.function.Predicate;

//Router entity class
public class Router {
    private final RouterType routerType;
    private final RouterId routerid;

    private Switch networkSwitch;
    public Router(RouterType, RouterId routerid) {
        this.routerType = routerType;
        this.routerid = routerid;
    }
    public static Predicate<Router>
    filterRouterByType(RouterType routerType) {
        return routerType.equals(RouterType.CORE)
                ? Router.isCore() :
                Router.isEdge();
    }
    public static Predicate<Router> isCore() {
        return p -> p.getRouterType() == RouterType.CORE;
    }
    public static Predicate<Router> isEdge() {
        return p -> p.getRouterType() == RouterType.EDGE;
    }
    public void addNetworkToSwitch(Network network) {
        this.networkSwitch =
                networkSwitch.addNetwork(network, this);
    }
    public Network createNetwork(IP address, String name,
                                 int cidr) {
        return new Network(address, name, cidr);
    }
    public List<Network> retrieveNetworks() {
        return networkSwitch.getNetworks();
    }
    public RouterType getRouterType() {
        return routerType;
    }

    public void addNetworkToSwitch(Network network) {
        this.networkSwitch = networkSwitch.addNetwork(network,
                this);
    }
    public Network createNetwork(IP address, String name, long
            cidr) {
        return new Network(address, name, cidr);
    }

    @Override
    public String toString() {
        return "Router{" +
                "type=" + routerType +
                ", id=" + routerid +
                '}';
    }
}

