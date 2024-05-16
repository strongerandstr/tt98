package com.tt98.server.service.impl;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Connection;
import com.tt98.server.common.util.SpringUtil;
import com.tt98.server.service.RabbitmqService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitmqServiceImpl implements RabbitmqService {
    @Autowired
    private NotifyService notifyService;

    public boolean enabled() {
        return "true".equalsIgnoreCase(SpringUtil.getConfig("rabbitmq.switchFlag"));
    }


    @Override
    public void publishMsg(String exchange, BuiltinExchangeType exchangeType, String toutingKey, String message) throws IOException, TimeoutException {
        try {
            // 创建连接
            RabbitmqConnection rabbitmqConnection = RabbitmqConnectionPool.getConnection();
            Connection connection = rabbitmqConnection.getConnection();
            // 创建消息通道

            // 声明exchange中的消息为可持久化，不自动删除

            // 发布消息
        } catch (){

        }
    }

    @Override
    public void consumeMsg(String exchange, String queue, String routingKey) throws IOException, TimeoutException {

    }

    @Override
    public void processConsumerMsg() {

    }
}
