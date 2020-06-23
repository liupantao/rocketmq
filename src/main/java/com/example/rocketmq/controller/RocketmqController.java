package com.example.rocketmq.controller;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RocketmqController
 * @Description: TODO
 * @Author liupantao
 * @Date 2020/6/22
 * @Version V1.0
 **/
@RestController
public class RocketmqController {

    @Autowired(required = false)
    private DefaultMQProducer defaultMQProducer;

    @RequestMapping("/pushMsg/{name}")
    public String pushMsg(@PathVariable("name")String name) throws  Exception{

        Message msg = new Message("TopicTest", "tags1", name.getBytes(RemotingHelper.DEFAULT_CHARSET));
       // 发送消息到一个Broker
        SendResult sendResult = defaultMQProducer.send(msg);
        // 通过sendResult返回消息是否成功送达
        System.out.printf("%s%n", sendResult);
        return "success";
    };



    @RequestMapping("/pushMsgOne/{name}")
    public String pushMsgOne(@PathVariable("name")String name) throws  Exception{

        Message msg = new Message("TopicOne", "tags1", name.getBytes(RemotingHelper.DEFAULT_CHARSET));
        // 发送消息到一个Broker
        SendResult sendResult = defaultMQProducer.send(msg);
        // 通过sendResult返回消息是否成功送达
        System.out.printf("%s%n", sendResult);
        return "success";
    };
    @RequestMapping("/pushMsgUser/{name}")
    public String pushMsgUser(@PathVariable("name")String name) throws  Exception{
        Message msg =null;
        if(name.contains("008")){
            msg = new Message("order-topic", "white", "CODE12345",name.getBytes(RemotingHelper.DEFAULT_CHARSET));

        }else{

            msg = new Message("user-topic", "white", name.getBytes(RemotingHelper.DEFAULT_CHARSET));
        }
        // 发送消息到一个Broker
        SendResult sendResult = defaultMQProducer.send(msg);
        // 通过sendResult返回消息是否成功送达
        System.out.println("send msg -->> "+sendResult);
        return "success";
    };

}
