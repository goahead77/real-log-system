package cn.wenqi.storm;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * 用于处理来自kafka的数据
 *
 * @author wenqi
 * @since v1.0.0
 */

public class MyBolt extends BaseBasicBolt {

    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        String topic=tuple.getString(0);
        String value=tuple.getString(4);
        String result= JSON.toJSONString( new Msg(topic,value));
        System.out.println("result is:"+result);
        basicOutputCollector.emit(new Values(result));
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //see https://github.com/apache/storm/blob/master/docs/storm-kafka-client.md
        outputFieldsDeclarer.declare(new Fields("topic", "partition", "offset", "key", "value"));

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class Msg{
        private String topic;
        private String value;
    }
}
