package com.arquiteturahexagonal.application.ports.output;

import com.arquiteturahexagonal.domain.entity.Router;
import com.arquiteturahexagonal.domain.vo.RouterId;

/**
 * Esta porta de saída informa que o aplicativo pretende obter e manter dados de fontes externas.
 * O sistema hexagon não sabe se a fonte externa é um banco de dados, um arquivo plano ou outro sistema.
 * Aqui, apenas declaramos a intenção de obter dados de fora.
 * */
public interface RouterNetworkOutputPort {
    Router fetchRouterById(RouterId routerId);

    boolean persistRouter(Router router);
}
