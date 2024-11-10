package com.arquiteturahexagonal;

import com.arquiteturahexagonal.domain.entity.Router;
import com.arquiteturahexagonal.domain.specification.CIDRSpecification;
import com.arquiteturahexagonal.domain.vo.IP;
import com.arquiteturahexagonal.domain.vo.Network;
import com.arquiteturahexagonal.domain.vo.RouterId;

public class AddNetworkSteps {

    RouterId routerId;

    Router router;

    RouterNetworkFileAdapter routerNetworkFileAdapter =  RouterNetworkFileAdapter.getInstance();

    Network network = new Network(new IP("20.0.0.0"), "Marketing", 8);

    @Given("I provide a router ID and the network details")
    public void obtain_routerId() {
        this.routerId = RouterId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003");
    }

    @When("I found the router")
    public void lookup_router() {
        router = routerNetworkFileAdapter.fetchRouterById(routerId);
    }

    @And("The network address is valid and doesn't already exists")
    public void check_address_validity_and_existence() {
        var availabilitySpec = new NetworkAvailabilitySpecification(network.getAddress(), network.getName(), network.getCidr());
        if(!availabilitySpec.isSatisfiedBy(router))
            throw new IllegalArgumentException("Address already exist");
    }

    @Given("The CIDR is valid")
    public void check_cidr() {
        var cidrSpec = new CIDRSpecification();
        if(cidrSpec.isSatisfiedBy(network.getCidr()))
            throw new IllegalArgumentException("CIDR is below "+CIDRSpecification.MINIMUM_ALLOWED_CIDR);
    }

    @Then("Add the network to the router")
    public void add_network() {
        router.addNetworkToSwitch(network);
    }
}
