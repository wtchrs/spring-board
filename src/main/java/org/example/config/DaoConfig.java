package org.example.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.example.dao.BoardDao;
import org.example.dao.PostDao;
import org.example.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/board?characterEncoding=utf8");
        ds.setUsername("board");
        ds.setPassword("board");
        ds.setInitialSize(2);
        ds.setMaxActive(10);
        ds.setTestWhileIdle(true);
        ds.setMinEvictableIdleTimeMillis(60000 * 3);
        ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
        return ds;
    }

    @Bean
    public UserDao userDao() {
        return new UserDao(dataSource());
    }

    @Bean
    public BoardDao boardDao() {
        return new BoardDao(dataSource());
    }

    @Bean
    public PostDao postDao() {
        return new PostDao(dataSource());
    }
}
