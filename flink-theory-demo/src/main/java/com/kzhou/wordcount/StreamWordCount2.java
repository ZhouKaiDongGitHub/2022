package com.kzhou.wordcount;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @Description Flink流处理
 * @Author ZhouKaiDong
 * @Date 2022/5/2 18:08
 **/
public class StreamWordCount2 {
    public static void main(String[] args) throws Exception{

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 从服务器host的port端口监听并拿到数据
        ParameterTool parameterTool = ParameterTool.fromArgs(args);
        String host = parameterTool.get("host");
        int port = parameterTool.getInt("port");

        // 启动main方法需要指定启动参数 --host localhost --port 7777
        DataStream<String> stringDataStreamSource = env.socketTextStream(host, port);

        // cmd -->>> nc -l -p 7777
        DataStream<Tuple2<String, Integer>> sum = stringDataStreamSource
                .flatMap(new WordCount1.MyFlatMapFunction())
                .keyBy(0)
                .sum(1);
        sum.print(); // 打印到控制台

        env.execute(); // 启动流的环境
    }
}
