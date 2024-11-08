package com.arquiteturahexagonal.application.usecase;

public interface RouterViewUseCase {
    List<Router> getRouters(Predicate<Router> filter);
}
