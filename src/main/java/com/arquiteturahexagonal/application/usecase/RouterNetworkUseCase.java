package com.arquiteturahexagonal.application.usecase;

import com.arquiteturahexagonal.domain.entity.Router;
import com.arquiteturahexagonal.domain.vo.Network;
import com.arquiteturahexagonal.domain.vo.RouterId;


/**
 * A assinatura do método getRouter é simples. Ele recebe RouterId e retorna um objeto Router.
 * Precisamos desse comportamento para permitir que o aplicativo frontend exiba um roteador
 * */
public interface RouterNetworkUseCase {

    Router addNetworkToRouter(RouterId routerId, Network network);

    Router getRouter(RouterId routerId);
}


