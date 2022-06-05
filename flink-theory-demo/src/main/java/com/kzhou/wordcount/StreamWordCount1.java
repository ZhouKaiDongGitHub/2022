package com.kzhou.wordcount;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @Description Flink流处理
 * @Author ZhouKaiDong
 * @Date 2022/5/2 18:08
 **/
public class StreamWordCount1 {
    public static void main(String[] args) throws Exception{

        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
        executionEnvironment.setParallelism(4);
        // executionEnvironment.setParallelism(4); // 默认计算机的cpu核数

        String path = ClassLoader.getSystemClassLoader().getResource("hello.txt").getPath();

        /**
         * 运行时架构分析：
         * JobManager TaskManager ResourceManager Dispatcher
         * TaskManager slots Parallelism
         *
         *  Flink程序由3个部分组成：
         *  Source Transformation Sink
         */
        DataStream<String> stringDataStreamSource =
                executionEnvironment.readTextFile(path);
        DataStream<Tuple2<String, Integer>> sum = stringDataStreamSource
                .flatMap(new WordCount1.MyFlatMapFunction())
                .keyBy(0)
                .sum(1);
        sum.print();

        executionEnvironment.execute(); // 启动流的环境
    }
}
