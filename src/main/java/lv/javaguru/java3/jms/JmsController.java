package lv.javaguru.java3.jms;

/**
 * Created by Anna on 14.12.2015.
 */
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JmsController {
    Logger logger = Logger.getLogger(JmsController.class);

    @Autowired
    AmqpTemplate template;

    @RequestMapping("/call_queue1")
    @ResponseBody
    String queue1() {
        logger.info("Emit to queue1");
        template.convertAndSend("queue1","Message to the first queue");
        return "Emit to queue1";
    }

    @RequestMapping("/call_queue2")
    @ResponseBody
    String queue2() {
        logger.info("Emit to queue2");
        template.convertAndSend("queue2","Message to the second queue");
        return "Emit to queue2";
    }
}
