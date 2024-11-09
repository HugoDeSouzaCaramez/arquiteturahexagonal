package com.arquiteturahexagonal.domain.specification;

public abstract class AbstractSpecification<T> implements
        Specification<T> {
    public abstract boolean isSatisfiedBy(T t);
    public Specification<T> and(final Specification<T>
                                        specification) {
        return new AndSpecification<T>(this,
                specification);
    }
}
