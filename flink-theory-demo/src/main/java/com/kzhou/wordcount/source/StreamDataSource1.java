package com.kzhou.wordcount.source;

import com.kzhou.wordcount.WordCount1;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description Flink的Source各数据来源
 * @Author ZhouKaiDong
 * @Date 2022/5/2 18:08
 **/
public class StreamDataSource1 {
    public static void main(String[] args) throws Exception{
        // 根据系统环境，获取到Flink的执行环境
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        // 设置每台服务器的最多subTask是4
        environment.setParallelism(4);
        // 构建数据源1
        List<String> sourceList = new ArrayList();
        sourceList.add("hello world");
        sourceList.add("hello flink");
        sourceList.add("how are you");
        sourceList.add("hello java");
        DataStream<String> dataStream = environment.fromCollection(sourceList);
        DataStream<Tuple2<String, Integer>> sum =
                dataStream.flatMap(new WordCount1.MyFlatMapFunction())
                .keyBy(0)
                .sum(1);
        sum.print();
        // 启动执行环境
        environment.execute();
    }
}
