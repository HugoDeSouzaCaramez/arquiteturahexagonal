package com.arquiteturahexagonal.application.ports.output;

import com.arquiteturahexagonal.domain.entity.Router;

import java.util.List;

public interface RouterViewOutputPort {
    List<Router> fetchRouters();
}
