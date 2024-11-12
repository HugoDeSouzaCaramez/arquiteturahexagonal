package com.arquiteturahexagonal.domain.entity;

import com.arquiteturahexagonal.domain.vo.IP;
import com.arquiteturahexagonal.domain.vo.Network;
import com.arquiteturahexagonal.domain.vo.RouterId;
import com.arquiteturahexagonal.domain.vo.RouterType;

import java.util.List;
import java.util.function.Predicate;

//Router entity class
public class Router {

    private RouterType routerType;
    private RouterId routerId;
    private Switch networkSwitch;

    public Router(){

    }

    public Router(RouterType routerType, RouterId routerId) {
        this.routerType = routerType;
        this.routerId = routerId;
    }

    public Router(RouterType routerType, RouterId routerId, Switch networkSwitch) {
        this.routerType = routerType;
        this.routerId = routerId;
        this.networkSwitch = networkSwitch;
    }

    public boolean isType(RouterType type){
        return this.routerType == type;
    }

    public void addNetworkToSwitch(Network network){
        this.networkSwitch = networkSwitch.addNetwork(network, this);
    }

    public Network createNetwork(IP address, String name, int cidr){
        return new Network(address, name, cidr);
    }

    public List<Network> retrieveNetworks(){
        return networkSwitch.getNetworks();
    }

    public RouterType getRouterType() {
        return routerType;
    }

    public RouterId getRouterId() {
        return routerId;
    }

    public Switch getNetworkSwitch() {
        return networkSwitch;
    }

    @Override
    public String toString() {
        return "Router{" +
                "type=" + routerType +
                ", id=" + routerId +
                ", networkSwitch=" + networkSwitch +
                '}';
    }
}