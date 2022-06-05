package com.kzhou.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/5/19 11:32
 **/
@Configuration
public class DbConfig {

    @Bean
    public SimpleDriverDataSource dataSource (){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("11111");
        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/api?useUnicode=true&amp;characterEncoding=UTF-8");
        return dataSource;
    }

    public ComboPooledDataSource dataSourceC3p0() throws Exception{
        // c3p0连接池的参数非常多，绝大部分使用默认值即可
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/api?useUnicode=true&amp;characterEncoding=UTF-8");
        dataSource.setUser("root");
        dataSource.setPassword("11111");
        dataSource.setMinPoolSize(5); // 连接池中保留的最小连接数
        dataSource.setMaxPoolSize(30);// 连接池中保留的最大连接数。Default: 15
        dataSource.setInitialPoolSize(10);// 初始化时获取的连接数，取值应在minPoolSize与maxPoolSize之间。Default: 3
        dataSource.setMaxIdleTime(60);// 最大空闲时间,60秒内未使用则连接被丢弃。若为0则永不丢弃。Default: 0
        dataSource.setAcquireIncrement(3);// 当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。Default: 3
        return dataSource;
    }

  /*  public SqlSessionFactory sqlSessionFactory(){
        SqlSessionFactoryBean
    }*/

    public static void main(String[] args) {
        System.out.println(190000000031987331L % 64);
    }
}
