package com.arquiteturahexagonal.application.ports.input;

import com.arquiteturahexagonal.application.ports.output.RouterNetworkOutputPort;
import com.arquiteturahexagonal.application.usecase.RouterNetworkUseCase;
import com.arquiteturahexagonal.domain.entity.Router;
import com.arquiteturahexagonal.domain.service.NetworkOperation;
import com.arquiteturahexagonal.domain.vo.Network;
import com.arquiteturahexagonal.domain.vo.RouterId;


/**
 * O método addNetworkToRouter , que retorna um objeto Router , é o único método público que é exposto pela porta de
 * entrada. Tornamos todos os outros métodos privados porque eles não devem ser usados fora do contexto desta
 * porta de entrada. A porta de entrada inicia seu trabalho usando RouterId para recuperar um objeto Router ; então, ela
 * cria um novo objeto Network naquele objeto Router . Lembre-se, o objeto Network compreende os atributos address,
 * name e CIDR, conforme expresso na forma escrita do caso de uso. O método fetchRouter tentará obter um objeto
 * Router passando um ID RouterId para o método fetchRouterById da porta de saída . É quando a porta de entrada
 * precisará coordenar uma chamada externa que será realizada por um adaptador de saída que implementa a porta de
 * saída.
 *
 *
 * Se tudo correr bem, a porta de entrada receberá o objeto Router desejado e poderá criar um objeto de rede
 * e adicioná-lo ao roteador informado. Neste ponto, a porta de entrada está interagindo com um serviço de
 * Domínio chamado createNewNetwork. Este serviço funciona sob as restrições impostas pelas regras de
 * negócios do hexágono de Domínio. Finalmente, a porta de entrada coordena a persistência de toda a
 * operação por meio do método persistRouter da porta de saída.
 *
 *
 * Esta porta de entrada não contém nada específico para o domínio do problema. Sua principal preocupação
 * é manipular dados orquestrando chamadas internas com serviços de Domínio e chamadas externas com portas de saída.
 * A porta de entrada define a ordem de execução da operação e fornece ao hexágono de domínio dados em um
 * formato que ele entende.
 *
 *
 * No início deste capítulo, aprendemos como os casos de uso estabelecem as ações
 * necessárias para realizar algo útil na aplicação. Entre essas ações, pode haver situações
 * que exijam que interajamos com sistemas externos.
 *
 * Portanto, o motivo para criar e utilizar portas de saída será derivado das atividades executadas pelos casos de uso.
 * No código, a referência para uma porta de saída não aparecerá na declaração de interface do caso de
 * uso. O uso de portas de saída é explicitado quando implementamos o caso de uso com uma porta de
 * entrada. Foi o que fizemos quando implementamos RouterNetworkUseCase e declaramos uma RouterNetworkOutputPort
 * atributo no início de RouterNetworkInputPort
 *
 *
 * Você pode estar se perguntando quando e como a instância para uma porta de saída é criada. O exemplo
 * anterior mostra uma abordagem, onde o construtor de porta de entrada recebe uma referência para um objeto de porta de saída.
 * Este objeto será uma implementação fornecida por um adaptador de saída
 *
 * Entre as operações definidas por um caso de uso e implementadas por uma porta de entrada, algumas operações
 * são responsáveis por obter dados de ou persistir dados para fontes externas. É aí que as portas de saída entram:
 * para fornecer os dados necessários para cumprir a meta do caso de uso.
 *
 *
 * Da mesma forma que uma meta de caso de uso é usada para representar a intenção de um pedaço de software,
 * sem dizer como essa intenção será realizada, as portas de saída fazem a mesma coisa ao representar que tipo de
 * dados o aplicativo precisa, sem precisar saber como esses dados serão obtidos. As portas de saída, junto com as
 * portas de entrada e os casos de uso, são os componentes de arquitetura hexagonal que dão suporte ao esforço
 * de automação que caracteriza o hexágono do aplicativo. E
 * */
public class RouterNetworkInputPort implements RouterNetworkUseCase {
    private final RouterNetworkOutputPort
            routerNetworkOutputPort;
    public RouterNetworkInputPort(RouterNetworkOutputPort
                                          routerNetworkOutputPort){
        this.routerNetworkOutputPort =
                routerNetworkOutputPort;
    }
    @Override
    public Router addNetworkToRouter(RouterId routerId, Network network) {
        var router = fetchRouter(routerId);
        return createNetwork(router, network);
    }
    private Router fetchRouter(RouterId routerId) {
        return
                routerNetworkOutputPort.fetchRouterById(routerId);
    }
    private Router createNetwork(Router router, Network network) {
        var newRouter = NetworkOperation.createNewNetwork(router, network);
        return persistNetwork(router) ? newRouter : router;
    }
    private boolean persistNetwork(Router router) {
        return routerNetworkOutputPort.persistRouter(router);
    }
}

