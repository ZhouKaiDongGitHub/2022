package com.kzhou.wordcount.transformation;

import com.kzhou.wordcount.SensorReading;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/5/10 10:29
 **/
public class AggregateTransformation1 {
    public static void main(String[] args) throws Exception{

        // 聚合transformation需要在keyBy基础上进行 包括 min max sum minBy maxBy
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(1);

        String path = ClassLoader.getSystemClassLoader().getResource("sensor.txt").getPath();
        DataStream<String> inputDataStream = environment.readTextFile(path);

        DataStream<SensorReading> mapDataStream = inputDataStream.map(new MapFunction<String, SensorReading>() {
            @Override
            public SensorReading map(String s) throws Exception {
                String[] split = s.split(",");
                return new SensorReading(split[0], Long.valueOf(split[1]), Double.valueOf(split[2]));
            }
        });

        KeyedStream<SensorReading, Tuple> keyedStream = mapDataStream.keyBy("sensorId");

        DataStream<SensorReading> outputDataStream = keyedStream.max("number2");
        DataStream<SensorReading> outputDataStream2 = keyedStream.reduce(new ReduceFunction<SensorReading>() {
            @Override
            public SensorReading reduce(SensorReading sensorReading, SensorReading t1) throws Exception {
                return new SensorReading(sensorReading.getSensorId(), t1.getNumber(), Math.max(sensorReading.getNumber2(), t1.getNumber2()));
            }
        });

        // outputDataStream.print("outputDataStream");
        outputDataStream2.print("outputDataStream2");
        environment.execute();
    }
}
