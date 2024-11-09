package com.arquiteturahexagonal.domain.specification;

public class CIDRSpecification extends AbstractSpecification<Integer> {
    final static public int MINIMUM_ALLOWED_CIDR = 8;
    @Override
    public boolean isSatisfiedBy(Integer cidr) {
        return cidr > MINIMUM_ALLOWED_CIDR;
    }
}
