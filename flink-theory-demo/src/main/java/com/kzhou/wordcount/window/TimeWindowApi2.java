package com.kzhou.wordcount.window;

import com.kzhou.wordcount.SensorReading;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/5/17 9:22
 **/
public class TimeWindowApi2 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(1);

        DataStreamSource<String> inputDataStream = environment.socketTextStream("localhost", 7777);
        DataStream<SensorReading> mapDataStream = inputDataStream.map((MapFunction<String, SensorReading>) value -> {
            String[] split = value.split(",");
            return new SensorReading(split[0], Long.valueOf(split[1]), Double.valueOf(split[2]));
        });
        // 任何聚合操纵前都需要进行key的Hash划分，以提高其算力
        DataStream<Tuple2<Integer, Double>> windowDataStream = mapDataStream
                .keyBy("sensorId")
                .timeWindow(Time.seconds(15), Time.seconds(5))
                .apply(new WindowFunction<SensorReading, Tuple2<Integer, Double>, Tuple, TimeWindow>() {
                    @Override
                    public void apply(Tuple tuple, TimeWindow timeWindow, Iterable<SensorReading> iterable, Collector<Tuple2<Integer, Double>> collector) throws Exception {
                        long end = timeWindow.getEnd();
                        Double tmp=0.00;
                        Integer number=0;
                        for (SensorReading sensorReading : iterable) {
                            tmp += sensorReading.getNumber2();
                            number++;
                        }
                        Tuple2<Integer,Double> result  = new Tuple2<>();
                        result.f0 = number;
                        result.f1 = tmp/number;
                        collector.collect(result);
                    }
                });
        windowDataStream.print("windowDataStream");

        environment.execute();

    }
}
