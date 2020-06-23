package com.example.rocketmq.commonmq.consumer;

import com.example.rocketmq.commonmq.MessageEvent;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * @ClassName ConsumerService
 * @Description: TODO
 * @Author liupantao
 * @Date 2020/6/23
 * @Version V1.0
 **/

/**
* 监听消息进行消费
*/
@Component
public class ConsumerService {

    @Autowired

    @EventListener(condition = "#event.msgs[0].topic=='user-topic' && #event.msgs[0].tags=='white'")
    public void rocketmqMsgListener(MessageEvent event ) {
        System.out.println("-->user-监听器->>");
        try {
            List<MessageExt> msgs = event.getMsgs();
            DefaultMQPushConsumer consumer = event.getConsumer();
            for (MessageExt msg : msgs) {
                System.err.println("消费消息:"+new String(msg.getBody()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
