package cn.wenqi.storm;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

/**
 * 用于处理来自kafka的数据
 *
 * @author wenqi
 * @since v1.0.0
 */

public class MyBolt implements IRichBolt {

    private OutputCollector outputCollector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
      this.outputCollector=outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        String content=tuple.getString(0);
        System.out.println("content is :"+ content);
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
//        outputFieldsDeclarer.declare(new Fields("log"));

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
