package com.kzhou.wordcount.sink;

import com.kzhou.wordcount.SensorReading;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/5/12 10:35
 **/
public class EsSinkDemo3 {

    public static void main(String[] args) {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(4);
        String path = ClassLoader.getSystemClassLoader().getResource("sensor.txt").getPath();
        DataStream<String> inputDataStream = environment.readTextFile(path);
        // 通过Map转化为SensorReading对象
        DataStream<SensorReading> mapDataStream = inputDataStream.map(new MapFunction<String, SensorReading>() {
            @Override
            public SensorReading map(String value) throws Exception {
                String[] split = value.split(",");
                return new SensorReading(split[0], Long.valueOf(split[1]), Double.valueOf(split[2]));
            }
        });
        // 添加Es的配置
        ArrayList<HttpHost> httpHosts = new ArrayList<>();
        httpHosts.add(new HttpHost("localhost", 9200));

        // sink输出到ElasticSearch
        mapDataStream.addSink(new ElasticsearchSink.Builder<SensorReading>(httpHosts, new MyElasticSinkFunction()).build());
    }

    public static class MyElasticSinkFunction implements ElasticsearchSinkFunction<SensorReading>{

        @Override
        public void process(SensorReading sensorReading, RuntimeContext runtimeContext, RequestIndexer requestIndexer) {
            HashMap<String, String> dataSource = new HashMap<>();
            dataSource.put("id", sensorReading.getSensorId());
            dataSource.put("ts", sensorReading.getNumber().toString());
            dataSource.put("temp", sensorReading.getNumber2().toString());
            IndexRequest indexRequest = Requests.indexRequest().index("sensor").type("readingData").source(dataSource);
            requestIndexer.add(indexRequest);
        }
    }

}
