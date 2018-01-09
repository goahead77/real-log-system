package cn.wenqi.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author wenqi
 * @since v1.0.0
 */
//@Component
public class LogListener {

    @KafkaListener(topics = "log")
    public void listen(ConsumerRecord<String, String> cr) throws Exception {
        System.out.println("成功接收到来自flume发送到kafka的日志信息:"+cr.value());
    }

}
