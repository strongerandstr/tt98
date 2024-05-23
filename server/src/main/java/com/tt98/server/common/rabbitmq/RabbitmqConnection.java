package com.tt98.server.common.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

import java.util.concurrent.TimeoutException;

public class RabbitmqConnection {
    private Connection connection;

    public RabbitmqConnection(String host, int port, String userName, String password, String virtualhost){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualhost);

        try {
            connection = connectionFactory.newConnection();
        } catch (IOException | TimeoutException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public void close(){
        try {
            connection.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
