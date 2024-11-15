package com.arquiteturahexagonal.domain.specification;

import com.arquiteturahexagonal.domain.entity.Router;
import com.arquiteturahexagonal.domain.vo.RouterType;

public final class RouterTypeSpecification extends AbstractSpecification<Router> {

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router.getRouterType().equals(RouterType.EDGE) || router.getRouterType().equals(RouterType.CORE);
    }
}
