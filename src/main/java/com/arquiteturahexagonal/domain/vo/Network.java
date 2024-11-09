package com.arquiteturahexagonal.domain.vo;
//
public record Network(IP address, String name, int cidr) {
    public Network {
        if (cidr < 1 || cidr > 32) {
            throw new IllegalArgumentException("Invalid
                    CIDR
                    value");
        }
    }
}
