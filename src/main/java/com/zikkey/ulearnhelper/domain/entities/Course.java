package com.zikkey.ulearnhelper.domain.entities;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "course")
public class Course extends BaseEntity {
    public String name;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Module> modules;
}
