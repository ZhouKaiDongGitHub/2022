package com.kzhou.wordcount;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @Description Flink批处理
 * @Author ZhouKaiDong
 * @Date 2022/5/2 17:47
 **/
public class WordCount1 {
    public static void main(String[] args) throws Exception{
        // 1.创建执行环境
        ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();
        // 2.从文件读取数据
        DataSource<String> stringDataSource =
                executionEnvironment.readTextFile("D:\\code1\\learning\\2022\\flink-theory-demo\\src\\main\\resources\\hello.txt");
        // 3.对数据进行处理，按空格分词进行处理
        DataSet<Tuple2<String, Integer>> sum = stringDataSource
                .flatMap(new MyFlatMapFunction())
                .groupBy(0) // 按照第一个位置进行分组
                .sum(1);// 将第二个位置上的值进行求和

        sum.print();
    }

    public static class MyFlatMapFunction implements FlatMapFunction<String, Tuple2<String,Integer>>{

        @Override
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            String[] words = s.split(" ");
            for (String word : words) {
                Tuple2<String, Integer> stringIntegerTuple2 = new Tuple2<>(word, 1);
                collector.collect(stringIntegerTuple2);
            }
        }
    }

}
