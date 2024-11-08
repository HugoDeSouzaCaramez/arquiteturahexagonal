package com.arquiteturahexagonal.application.usecase;

import com.arquiteturahexagonal.domain.Router;

import java.util.List;
import java.util.function.Predicate;

public interface RouterViewUseCase {
    List<Router> getRouters(Predicate<Router> filter);
}
