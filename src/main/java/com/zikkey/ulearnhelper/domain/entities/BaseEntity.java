package com.zikkey.ulearnhelper.domain.entities;

import org.hibernate.annotations.*;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity extends Object{
    @Id
    @Type(type = "pg-uuid")
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    public UUID id;
}
