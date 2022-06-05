package com.kzhou.wordcount.source;

import com.kzhou.wordcount.SensorReading;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Description Flink的Source各数据来源
 * @Author ZhouKaiDong
 * @Date 2022/5/2 18:08
 **/
public class StreamDataSource3 {
    public static void main(String[] args) throws Exception{
        // 根据系统环境，获取到Flink的执行环境
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        // 设置每台服务器的最多subTask是4
        environment.setParallelism(8);
        // 构建数据源3
        DataStream<SensorReading> dataStream = environment
                .addSource(new MySourceFunction());

        dataStream.print();
        // 启动执行环境
        environment.execute();
    }

    public static class MySourceFunction implements SourceFunction<SensorReading> {
        private volatile boolean running = true;
        @Override
        public void run(SourceContext<SensorReading> sourceContext) throws Exception {
            Random random = new Random();
            HashMap<String, Double> stringDoubleHashMap = new HashMap<>();
            for (int i = 0; i < 10; i++) {
                stringDoubleHashMap.put("sensor"+(i+1), 60+random.nextGaussian() *20);
            }
            while (running){
                for (String sensorId : stringDoubleHashMap.keySet()) {
                    Double newTemp = stringDoubleHashMap.get(sensorId) + random.nextGaussian();
                    stringDoubleHashMap.put(sensorId,newTemp);
                    sourceContext.collect(new SensorReading(sensorId,System.currentTimeMillis(),newTemp));
                }
            }
            TimeUnit.SECONDS.sleep(1);
        }

        @Override
        public void cancel() {
            this.running = false;
        }
    }
}
