package lv.javaguru.java3.jms;

/**
 * Created by Anna on 14.12.2015.
 */
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener {
    Logger logger = Logger.getLogger(RabbitMqListener.class);

    @RabbitListener(queues = "queue1")
    public void processQueue1(String message) {
        logger.info("Received from queue 1: " + message);
    }

    @RabbitListener(queues = "queue2")
    public void processQueue2(String message) {
        logger.info("Received from queue 2: " + message);
    }

}
