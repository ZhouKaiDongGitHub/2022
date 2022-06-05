package com.kzhou.wordcount.window;

import com.kzhou.wordcount.SensorReading;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/5/17 9:22
 **/
public class TimeWindowApi1 {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(1);

        DataStreamSource<String> inputDataStream = environment.socketTextStream("localhost", 7777);
        DataStream<SensorReading> mapDataStream = inputDataStream.map((MapFunction<String, SensorReading>) value -> {
            String[] split = value.split(",");
            return new SensorReading(split[0], Long.valueOf(split[1]), Double.valueOf(split[2]));
        });
        // 任何聚合操纵前都需要进行key的Hash划分，以提高其算力
        SingleOutputStreamOperator<Tuple2<String, Double>> aggregateDataStream = mapDataStream
                .keyBy("sensorId")
                .timeWindow(Time.seconds(15))
                // AggregateFunction需要复写的方法有：
                //   createAccumulator：创建一个新的累加器，开始一个新的聚合。累加器是正在运行的聚合的状态。
                //   add：将给定的输入添加到给定的累加器，并返回新的累加器值。
                //   getResult：从累加器获取聚合结果。
                //   merge：合并两个累加器，返回合并后的累加器的状态。
                .aggregate(new AggregateFunction<SensorReading, Tuple2<Integer, Double>, Tuple2<String, Double>>() {

                    @Override
                    public Tuple2<Integer, Double> createAccumulator() {
                        return new Tuple2<Integer,Double>(0,Double.valueOf(0));
                    }

                    @Override
                    public Tuple2<Integer, Double> add(SensorReading value, Tuple2<Integer, Double> accumulator) {
                        accumulator.f0 = accumulator.f0+1; // 次数加1
                        accumulator.f1 = accumulator.f1+value.getNumber2(); // 温度累加
                        return accumulator;
                    }

                    @Override
                    public Tuple2<String, Double> getResult(Tuple2<Integer, Double> accumulator) {
                        return new Tuple2<String,Double>(accumulator.f0+"",accumulator.f1/accumulator.f0);
                    }

                    @Override
                    public Tuple2<Integer, Double> merge(Tuple2<Integer, Double> a, Tuple2<Integer, Double> b) {
                        return new Tuple2<>(a.f0+b.f0,a.f1+b.f1);
                    }
                });
        aggregateDataStream.print("aggregateDataStream");

        environment.execute();

    }
}
