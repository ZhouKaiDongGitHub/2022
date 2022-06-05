package com.kzhou.wordcount.sink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.internals.KeyedSerializationSchemaWrapper;
import org.apache.flink.util.Collector;
import scala.Tuple2;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/5/12 10:35
 **/
public class KafkaSinkDemo1 {

    public static void main(String[] args) {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        List<String> inputData = new ArrayList<>();
        inputData.add("hello,world");
        inputData.add("hello,spark");
        inputData.add("hello,flink");
        DataStream<String> inputDataStream = environment.fromCollection(inputData);
        DataStream<Tuple2<String, Integer>> mapDataStream = inputDataStream.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] split = value.split(",");
                for (String s:split){
                    out.collect(new Tuple2<>(s,1));
                }
            }
        });
        Properties properties = new Properties();
        properties.setProperty("","");
        properties.setProperty("","");
        properties.setProperty("","");
        properties.setProperty("","");
        properties.setProperty("","");
        // sink输出到kafka
        mapDataStream.addSink(
                new FlinkKafkaProducer<Tuple2<String,Integer>>("topicId",
                        new KeyedSerializationSchemaWrapper<>(new SerializationSchema<Tuple2<String, Integer>>() {
                            @Override
                            public byte[] serialize(Tuple2<String, Integer> element) {
                                return element.toString().getBytes(StandardCharsets.UTF_8);
                            }
                        }),properties));
    }
}
