package com.metanit.application.interfaces;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;

public interface IDbSessionFactory {
    EntityManagerFactory getSessionFactory();
}
