package com.tt98.server.service.impl;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.tt98.server.common.util.SpringUtil;
import com.tt98.server.service.NotifyService;
import com.tt98.server.service.RabbitmqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
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
            Channel channel = connection.createChannel();
            // 声明exchange中的消息为可持久化，不自动删除
            channel.exchangeDeclare(exchange, exchangeType, true, false, null);

            // 发布消息
            channel.basicPublish(exchange, toutingKey, null, message.getBytes());
            log.info("Publish msg: {}", message);
            channel.close();
            RabbitmqConnectionPool.returnConnection(rabbitmqConnection);
        } catch (InterruptedException | IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void consumeMsg(String exchange, String queue, String routingKey) throws IOException, TimeoutException {

    }

    @Override
    public void processConsumerMsg() {

    }
}
