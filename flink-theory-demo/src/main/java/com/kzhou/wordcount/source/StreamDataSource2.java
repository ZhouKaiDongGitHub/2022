package com.kzhou.wordcount.source;

import com.kzhou.wordcount.WordCount1;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * @Description Flink的Source各数据来源
 * @Author ZhouKaiDong
 * @Date 2022/5/2 18:08
 **/
public class StreamDataSource2 {
    public static void main(String[] args) throws Exception{
        // 根据系统环境，获取到Flink的执行环境
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        // 设置每台服务器的最多subTask是4
        environment.setParallelism(8);
        // 构建数据源2
        Properties properties = new Properties();
        properties.setProperty("","");
        properties.setProperty("","");
        properties.setProperty("","");
        properties.setProperty("","");
        properties.setProperty("","");

        DataStream<String> dataStream = environment
                .addSource(new FlinkKafkaConsumer<String>("",new SimpleStringSchema(),properties));

        DataStream<Tuple2<String, Integer>> sum =
                dataStream.flatMap(new WordCount1.MyFlatMapFunction())
                .keyBy(0)
                .sum(1);
        sum.print();
        // 启动执行环境
        environment.execute();
    }
}
