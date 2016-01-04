package lv.javaguru.java3.core.dto.message;

/**
 * Created by Anna on 04.01.2016.
 */
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class MessageDTO implements Serializable{

    private String messageBody;

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }



}
