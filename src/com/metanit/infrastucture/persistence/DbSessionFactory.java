package com.metanit.infrastucture.persistence;

import com.metanit.application.interfaces.IDbSessionFactory;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DbSessionFactory implements IDbSessionFactory {
    private EntityManagerFactory sessionFactory;

    @Override
    public EntityManagerFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = Persistence.createEntityManagerFactory("csvproject");
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
