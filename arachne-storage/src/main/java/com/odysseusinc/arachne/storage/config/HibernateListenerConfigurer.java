package com.odysseusinc.arachne.storage.config;

import com.odysseusinc.arachne.storage.service.JcrContentIntegrityService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.DeleteEventListener;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class HibernateListenerConfigurer {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    
    @Autowired
    private JcrContentIntegrityService jcrContentIntegrityService;

    @PostConstruct
    public void registerListeners() {

        EventListenerRegistry registry = getEventRegistry();

        DeleteEventListener deleteEntityListener = jcrContentIntegrityService.getHibernateDeleteEventListener();
        registry.getEventListenerGroup(EventType.DELETE).appendListener(deleteEntityListener);
    }

    private EventListenerRegistry getEventRegistry() {
        SessionFactoryImpl sessionFactoryImpl = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        return sessionFactoryImpl.getServiceRegistry().getService(EventListenerRegistry.class);
    }
}
