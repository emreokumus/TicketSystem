package com.ticketsystem.accountservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration //Spring'in anlayacağı bir configuration dosyası olabilmesi için ekledik.
@EnableCassandraRepositories //Cassandra repositorylerini enable eder yani instance'larını yaratacak.
public class CassandraConfiguration extends AbstractCassandraConfiguration { //Bu class'ın içine gidersek default değerleride görebiliriz.
    //Bir takım configurationlar default olarak auto configuration şeklinde çalışıyor.
    //Burada olmazsa olmaz keyspacename'i bana söyle diyor gerisi default değerlerle çalışıyor.

    //değerleri .yml içinden çekeriz çünkü direkt burada yazarsak keyspace name değiştiğinde koduda değiştirmemiz deploy etmemiz gerekecekti.
    @Value("${accountservice.cassandra.keyspace.name}")
    private String keyspaceName;
    @Value("${accountservice.cassandra.contact.point}")
    private String contactPoint;
    @Value("${accountservice.cassandra.port}")
    private int port;
    @Value("${accountservice.cassandra.username}")
    private String userName;
    @Value("${accountservice.cassandra.password}")
    private String password;

    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }
    @Override
    protected int getPort() {
        return port;
    }
    @Override
    protected String getContactPoints() {
        return contactPoint;
    }
    //Schemamızı auto-create etsin mi? Default değeri NONE
    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[] {"com.ticketsystem.accountservice"};
    }

    //username and password
    @Override
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cassandraClusterFactoryBean=super.cluster();
        cassandraClusterFactoryBean.setUsername(userName);
        cassandraClusterFactoryBean.setPassword(password);
        return cassandraClusterFactoryBean;
    }
}
