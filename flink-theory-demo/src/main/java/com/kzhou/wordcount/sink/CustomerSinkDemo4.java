package com.kzhou.wordcount.sink;

import com.kzhou.wordcount.SensorReading;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/5/12 10:35
 **/
public class CustomerSinkDemo4 {

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

        // sink输出到ElasticSearch
        mapDataStream.addSink(new MyJdbcSink());
    }


    public static class MyJdbcSink extends RichSinkFunction<SensorReading> {

        private Connection connection;
        private PreparedStatement preparedStatement;

        @Override
        public void open(Configuration parameters) throws Exception {
            // 打开链接

        }

        @Override
        public void close() throws Exception {
            // 关闭链接
        }

        @Override
        public void invoke(SensorReading value, Context context) throws Exception {
            // 执行sql调用操作
        }
    }
}
