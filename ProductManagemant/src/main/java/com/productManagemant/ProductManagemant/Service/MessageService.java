package com.productManagemant.ProductManagemant.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
@Value("${twilio.phone.whatsappno}")
private String fromWhatsappNo;
    @Value("${twilio.phone.number}")
    private String fromNumber;

    public void sendMessage(String toNumber, String messageBody, String type) {
        PhoneNumber ph1,ph2;
        if(type == "whatsapp"){ //if ("whatsapp".equals(type)) {
            ph1=new PhoneNumber("whatsapp:"+toNumber);
            ph2=new PhoneNumber(fromWhatsappNo);
        }else{
            ph1=new PhoneNumber(toNumber);
            ph2=new PhoneNumber(fromNumber);
        }
        Message message = Message.creator(
                ph1,
                ph2,
                messageBody
        ).create();
        System.out.println("Message sent: " + message.getSid());
    }
}
