package com.arquiteturahexagonal.framework.adapters.output.h2.data;


import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import java.io.Serializable;
import java.util.UUID;

/**
 * Lembre-se, nosso modelo de domínio vem primeiro, então não queremos acoplar o sistema com tecnologia de
 * banco de dados. É por isso que precisamos criar uma classe RouterData ORM para mapear diretamente para os
 * tipos de banco de dados.
 * */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "routers")
@SecondaryTable(name = "switches")
@MappedSuperclass
@Converter(name="uuidConverter", converterClass = UUIDTypeConverter.class)
public class RouterData implements Serializable {

    @Id
    @Column(name="router_id",
            columnDefinition = "uuid",
            updatable = false )
    @Convert("uuidConverter")
    private UUID routerId;

    @Embedded
    @Enumerated(EnumType.STRING)
    @Column(name="router_type")
    private RouterTypeData routerType;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(table = "switches",
            name = "router_id",
            referencedColumnName = "router_id")
    private SwitchData networkSwitch;
}