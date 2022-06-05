package com.kzhou.wordcount.sink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;
import org.apache.flink.util.Collector;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/5/12 10:35
 **/
public class RedisSinkDemo2 {

    public static void main(String[] args) {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        List<String> inputData = new ArrayList<>();
        inputData.add("hello,world");
        inputData.add("hello,spark");
        inputData.add("hello,flink");
        DataStream<String> inputDataStream = environment.fromCollection(inputData);
        DataStream<Tuple2<String, Integer>> mapDataStream = inputDataStream.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] split = value.split(",");
                for (String s:split){
                    out.collect(new Tuple2<>(s,1));
                }
            }
        });
        FlinkJedisPoolConfig jedisPoolConfig =
                new  FlinkJedisPoolConfig.Builder().setHost("localhost")
                        .setPort(6379).build();

        // sink输出到redis
        mapDataStream.addSink(new RedisSink<Tuple2<String,Integer>>(jedisPoolConfig,new MyRedisMapper()));
    }

    public static class MyRedisMapper implements RedisMapper<Tuple2<String,Integer>>{

        @Override
        public RedisCommandDescription getCommandDescription() {
            return new RedisCommandDescription(RedisCommand.HSET,"sensor_temp");
        }

        @Override
        public String getKeyFromData(Tuple2<String, Integer> stringIntegerTuple2) {
            return stringIntegerTuple2._1;
        }

        @Override
        public String getValueFromData(Tuple2<String, Integer> stringIntegerTuple2) {
            return String.valueOf(stringIntegerTuple2._2);
        }

    }
}
