package com.kzhou.wordcount.transformation;

import com.kzhou.wordcount.SensorReading;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Collections;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/5/10 10:29
 **/
public class HighTransformation1 {
    public static void main(String[] args) throws Exception{
        // 高级的transformation主要包括 Split+Select  Connect+CoMap  Union
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(4);
        // 获取输入流
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
        // 将流转进行标签为高温流和低温流
        SplitStream<SensorReading> splitDataStream = mapDataStream.split(new OutputSelector<SensorReading>() {
            @Override
            public Iterable<String> select(SensorReading sensorReading) {
                if (sensorReading.getNumber2() >= 30) {
                    return Collections.singletonList("high");
                } else {
                    return Collections.singletonList("low");
                }
            }
        });
        // 分别取出其中的两条流
        DataStream<SensorReading> highDataStream = splitDataStream.select("high");
        DataStream<SensorReading> lowDataStream = splitDataStream.select("low");
        DataStream<SensorReading> allDataStream = splitDataStream.select("high", "low");
        highDataStream.print("high");
        lowDataStream.print("low");
        allDataStream.print("all");

        // 启动执行环境
        environment.execute();
    }
}
