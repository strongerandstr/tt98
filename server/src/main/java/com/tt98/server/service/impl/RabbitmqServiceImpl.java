package com.tt98.server.service.impl;

import com.github.hui.quick.plugin.qrcode.util.json.JsonUtil;
import com.rabbitmq.client.*;
import com.tt98.pojo.Enum.NotifyTypeEnum;
import com.tt98.pojo.entity.UserFootDO;
import com.tt98.server.common.CommonConstants;
import com.tt98.server.common.rabbitmq.RabbitmqConnection;
import com.tt98.server.common.rabbitmq.RabbitmqConnectionPool;
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
    public void consumerMsg(String exchange, String queueName, String routingKey) throws IOException, TimeoutException {
        try{
            // 创建连接
            RabbitmqConnection rabbitmqConnection = RabbitmqConnectionPool.getConnection();
            Connection connection = rabbitmqConnection.getConnection();
            // 创建消息信道
            final Channel channel = connection.createChannel();
            // 消息队列
            channel.queueDeclare(queueName, true, false, false, null);
            // 绑定队列到交换机
            channel.queueBind(queueName, exchange, routingKey);

            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException{
                    String message = new String(body, "UTF-8");
                    log.info("Consumer msg: {}", message);

                    // 获取Rabbitmq消息，并保存到DB
                    // 说明：这里仅作为示例，如果有多种类型的消息，可以根据消息判定，简单的用 if...else 处理，复杂的用工厂 + 策略模式
                    notifyService.saveArticleNotify(JsonUtil.toObj(message, UserFootDO.class), NotifyTypeEnum.PRAISE);
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            };
            // 取消自动ack
            channel.basicConsume(queueName, false, consumer);
            channel.close();
            RabbitmqConnectionPool.returnConnection(rabbitmqConnection);

        } catch (InterruptedException | IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processConsumerMsg() {
        log.info("Begin to processConsumerMsg.");

        Integer stepTotal = 1;
        Integer step = 0;

        // TODO: 这种方式非常 Low，后续会改造成阻塞 I/O 模式
        while (true) {
            step++;
            try {
                log.info("processConsumerMsg cycle.");
                consumerMsg(CommonConstants.EXCHANGE_NAME_DIRECT, CommonConstants.QUERE_NAME_PRAISE,
                        CommonConstants.QUERE_KEY_PRAISE);
                if (step.equals(stepTotal)) {
                    Thread.sleep(10000);
                    step = 0;
                }
            } catch (Exception e) {

            }
        }


    }
}
