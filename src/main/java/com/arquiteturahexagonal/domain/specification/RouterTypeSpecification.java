package com.arquiteturahexagonal.domain.specification;

public class RouterTypeSpecification extends AbstractSpecification<Router> {
    @Override
    public boolean isSatisfiedBy(Router router) {
        return
                router.getRouterType().equals(RouterType.EDGE) ||
                        router.getRouterType().equals(RouterType.CORE);
    }
}

