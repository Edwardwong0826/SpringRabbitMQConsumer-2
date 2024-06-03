package com.test.springrabbitmq.delay;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

// this application is dedicated for listen delay message by using plugin
// in order for plugin to works for delay message we need to install the //https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/releases
// and put into the rabbitMQ container (if we are using docker desktop)
@Slf4j
@Component
public class DelayPluginConsumer {

    @RabbitListener(queues = "queue.test.delay")
    public void processDeadLetterMessage(String dataString, Message message, Channel channel) throws IOException {

        // listen dead message queue
        log.info("delay message using plugin received : {}", dataString);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

    }

}
