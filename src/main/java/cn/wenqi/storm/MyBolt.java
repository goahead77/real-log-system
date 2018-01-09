package cn.wenqi.storm;

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
        String content=tuple.getString(0);
        System.out.println("value is :"+ content);
        basicOutputCollector.emit(new Values(content));
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
}
