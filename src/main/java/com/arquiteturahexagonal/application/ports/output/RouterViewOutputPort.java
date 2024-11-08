package com.arquiteturahexagonal.application.ports.output;

import com.arquiteturahexagonal.domain.Router;

import java.util.List;

public interface RouterViewOutputPort {
    List<Router> fetchRouters();
}
