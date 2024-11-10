package com.arquiteturahexagonal.application.usecase;

import com.arquiteturahexagonal.domain.entity.Router;
import com.arquiteturahexagonal.domain.vo.Network;
import com.arquiteturahexagonal.domain.vo.RouterId;

public interface RouterNetworkUseCase {
    Router addNetworkToRouter(RouterId routerId, Network network);
}

