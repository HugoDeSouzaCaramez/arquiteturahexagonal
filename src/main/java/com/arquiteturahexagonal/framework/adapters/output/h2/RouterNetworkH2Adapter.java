package com.arquiteturahexagonal.framework.adapters.output.h2;

import com.arquiteturahexagonal.application.ports.output.RouterNetworkOutputPort;
import com.arquiteturahexagonal.domain.entity.Router;
import com.arquiteturahexagonal.domain.vo.RouterId;

/**
 * Ele usa um banco de dados H2 na memória para configurar
 * todas as tabelas e relacionamentos necessários. Esta implementação do adaptador nos mostra como adaptar os
 * dados do modelo de domínio a um banco de dados relacional.
 *
 *
 * O primeiro método que substituímos é fetchRouterById, onde recebemos routerId para buscar um roteador do banco de dados H2
 * usando nossa referência de gerenciador de entidade. Não podemos usar a classe de entidade de domínio Router para mapear
 * diretamente para o banco de dados. Além disso, não podemos usar a entidade de banco de dados como uma entidade de domínio.
 * É por isso que usamos o método toDomain em fetchRouterById para mapear dados do banco de dados H2 para o domínio.
 *
 *
 * Fazemos o mesmo procedimento de mapeamento, usando o método toH2 em persistRouter para convertêlo de uma entidade de modelo de domínio em uma entidade de banco de dados H2. O método setUpH2Database inicia
 * o banco de dados quando o aplicativo inicia. Para criar apenas uma instância do adaptador H2,
 * definimos um singleton usando o método getInstance
 *
 * O campo de instância é usado para fornecer um objeto singleton do adaptador de saída H2. Observe
 * que o construtor chama o método setUpH2Database para criar uma conexão de banco de dados
 * usando EntityManagerFactory.
 * */
public class RouterNetworkH2Adapter implements RouterNetworkOutputPort {

    private static RouterNetworkH2Adapter instance;

    @PersistenceContext
    private EntityManager em;

    private RouterNetworkH2Adapter(){
        setUpH2Database();
    }

    @Override
    public Router fetchRouterById(RouterId routerId) {
        var routerData = em.getReference(RouterData.class, routerId.getUUID());
        return RouterH2Mapper.toDomain(routerData);
    }

    @Override
    public boolean persistRouter(Router router) {
        var routerData = RouterH2Mapper.toH2(router);
        em.persist(routerData);
        return true;
    }

    private void setUpH2Database() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("inventory");
        EntityManager em = entityManagerFactory.createEntityManager();
        this.em = em;
    }

    public static RouterNetworkH2Adapter getInstance() {
        if (instance == null) {
            instance = new RouterNetworkH2Adapter();
        }
        return instance;
    }
}
