package com.zikkey.ulearnhelper.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "course", uniqueConstraints = {
        @UniqueConstraint(name = "uc_course_name", columnNames = {"name"})
})
public class Course extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Module> modules = new HashSet<>();

    public void addModule(Module module) {
        modules.add(module);
        module.setCourse(this);
    }

    public void removeModule(Module module) {
        modules.remove(module);
        module.setCourse(null);
    }

}
