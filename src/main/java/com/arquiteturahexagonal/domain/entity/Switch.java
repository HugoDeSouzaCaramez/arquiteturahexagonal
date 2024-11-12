package com.arquiteturahexagonal.domain.entity;

import com.arquiteturahexagonal.domain.vo.IP;
import com.arquiteturahexagonal.domain.vo.Network;
import com.arquiteturahexagonal.domain.vo.SwitchId;
import com.arquiteturahexagonal.domain.vo.SwitchType;

import java.util.ArrayList;
import java.util.List;

public class Switch {

    private SwitchId switchId;
    private SwitchType switchType;
    private List<Network> networks;
    private IP address;

    public Switch (SwitchId switchId, SwitchType switchType, List<Network> networks, IP address){
        this.switchId = switchId;
        this.switchType = switchType;
        this.networks = networks;
        this.address = address;
    }

    public Switch addNetwork(Network network, Router router){
        List<Network> newNetworks = new ArrayList<>();

        router.retrieveNetworks().forEach(net ->{
            newNetworks.add(net);
        });

        newNetworks.add(network);
        return new Switch(this.switchId, this.switchType, newNetworks, this.address);
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public SwitchId getSwitchId() {
        return switchId;
    }

    public SwitchType getSwitchType() {
        return switchType;
    }

    public IP getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Switch{" +
                "switchType=" + switchType +
                ", switchId=" + switchId +
                ", networks=" + networks +
                ", address=" + address +
                '}';
    }
}