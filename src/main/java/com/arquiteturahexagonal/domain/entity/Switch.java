package com.arquiteturahexagonal.domain.entity;

public class Switch {
    private final SwitchType type;
    private final SwitchId switchId;

    private final List<Network> networkList;
    private final IP address;
    public Switch (SwitchType switchType, SwitchId
            switchId, List<Network> networks, IP address) {
        this.switchType = switchType;
        this.switchId = switchId;
        this.networks = networks;
        this.address = address;
    }
    public Switch addNetwork(Network network, Router router)
    {
        List<Network> newNetworks =
                new ArrayList<>(router.retrieveNetworks());
        newNetworks.add(network);
        return new Switch(
                this.switchType,
                this.switchId,
                newNetworks,
                this.address);
    }
    public List<Network> getNetworks() {
        return networks;
    }
}
