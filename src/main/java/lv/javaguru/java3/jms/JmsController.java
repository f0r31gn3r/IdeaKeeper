package lv.javaguru.java3.jms;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Anna on 14.12.2015.
 */

@Controller
@RequestMapping("/amq")
public class JmsController {
    Logger logger = Logger.getLogger(JmsController.class);

    @Autowired
    AmqpTemplate template;

    @RequestMapping("/call_queue1")
    @ResponseBody
    String queue1() {
        logger.info("Emit to queue1");
        sendEmailBySelenide();

        //some problems with ports occured
        //sendEmail();

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

    //test try
    private static void sendEmail(){
        String to = "anna_2302@inbox.lv"; // sender email
        String from = "sender@abc.com"; // receiver email
        String host = "127.0.0.1"; // mail server host
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties); // default session

        try {
            MimeMessage message = new MimeMessage(session); // email message
            message.setFrom(new InternetAddress(from)); // setting header fields
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Test Mail"); // subject line // actual mail body
            message.setText("Test Text"); // Send message
            Transport.send(message);
            System.out.println("Email Sent successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private static void sendEmailBySelenide(){
        open("https://mail.inbox.lv/");
        $("#imapuser").setValue("anna_test");
        $("#pass").setValue("testik123");
        $("#btn_sign-in").click();
        $("#mail-menu_li_compose").$("a").click();
        $("#suggest-to").setValue("anna_test@inbox.lv");
        $("#subject").setValue("JMS testing!!!");
        $("#toolbar_secondary_btn_send").click();
        $("#btn_return-to-folder").click();
        sleep(3000);
        close();
    }
}
