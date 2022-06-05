package com.kzhou.wordcount.transformation;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/5/10 10:29
 **/
public class BaseTransformation1 {
    public static void main(String[] args) throws Exception{
        // 基础的transformation操作 map flatMap filter
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(1);

        String path = ClassLoader.getSystemClassLoader().getResource("sensor.txt").getPath();
        DataStream<String> inputDataStream = environment.readTextFile(path);

        DataStream<Integer> mapDataStream = inputDataStream.map(new MapFunction<String, Integer>() {
            @Override
            public Integer map(String s) throws Exception {
                return s.length();
            }
        });

       DataStream<String> flatMapDataStream = inputDataStream.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String s, Collector<String> collector) throws Exception {
                String[] split = s.split(",");
                for (String s1 : split) {
                    collector.collect(s1);
                }
            }
        });

        DataStream<String> filterDataStream = inputDataStream.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String s) throws Exception {
                return s.startsWith("sensor_1");
            }
        });

        mapDataStream.print("map");
        flatMapDataStream.print("flatMap");
        filterDataStream.print("filter");

        environment.execute();
    }
}
