package com.arquiteturahexagonal;

import com.arquiteturahexagonal.domain.entity.Router;
import com.arquiteturahexagonal.domain.specification.CIDRSpecification;
import com.arquiteturahexagonal.domain.vo.IP;
import com.arquiteturahexagonal.domain.vo.Network;
import com.arquiteturahexagonal.domain.vo.RouterId;
import com.arquiteturahexagonal.framework.adapters.output.file.RouterNetworkFileAdapter;

public class AddNetworkSteps {

    RouterId routerId;

    Router router;

    RouterNetworkFileAdapter routerNetworkFileAdapter =  RouterNetworkFileAdapter.getInstance();

    Network network = new Network(new IP("20.0.0.0"), "Marketing", 8);
    /**
     * Depois de preparar os recursos que precisamos testar, podemos começar implementando o primeiro passo do nosso teste
     * */
    @Given("I provide a router ID and the network details")
    public void obtain_routerId() {
        this.routerId = RouterId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003");
    }

    /**
     * A anotação @Given descreve a recuperação de RouterId. Podemos usar esse ID para buscar um roteador
     * */
    @When("I found the router")
    public void lookup_router() {
        router = routerNetworkFileAdapter.fetchRouterById(routerId);
    }

    /**
     * Usando RouterNetworkFileAdapter e RouterId, recuperamos um objeto Router . Em seguida,
     * podemos verificar se o objeto Network atende aos requisitos desejados antes de adicioná-lo ao roteador
     * */
    @And("The network address is valid and doesn't already exists")
    public void check_address_validity_and_existence() {
        var availabilitySpec = new NetworkAvailabilitySpecification(network.getAddress(), network.getName(), network.getCidr());
        if(!availabilitySpec.isSatisfiedBy(router))
            throw new IllegalArgumentException("Address already exist");
    }

    /**
     * Para garantir que a rede seja válida, devemos aplicar as regras de NetworkAvailabilitySpecification
     * Em seguida, precisamos verificar o CIDR da rede
     * */
    @Given("The CIDR is valid")
    public void check_cidr() {
        var cidrSpec = new CIDRSpecification();
        if(cidrSpec.isSatisfiedBy(network.getCidr()))
            throw new IllegalArgumentException("CIDR is below "+CIDRSpecification.MINIMUM_ALLOWED_CIDR);
    }

    /**
     * Como último passo de verificação, devemos aplicar as regras de CIDRSpecification. Se tudo estiver bem, então
     * podemos adicionar a rede ao roteador
     * */
    @Then("Add the network to the router")
    public void add_network() {
        router.addNetworkToSwitch(network);
    }
}
