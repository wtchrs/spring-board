package org.example.dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.example.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> userMapper = (rs, rowNum) -> {
        User user = new User(rs.getString("ID"), rs.getString("PASSWORD"), rs.getString("NICKNAME"),
                             rs.getTimestamp("REGDATE").toLocalDateTime());
        user.setNo(rs.getLong("NO"));
        return user;
    };

    public UserDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User selectByNo(Long no) {
        List<User> results = jdbcTemplate.query("select * from USER where NO = ?", userMapper, no);
        return results.isEmpty() ? null : results.get(0);
    }

    public User selectById(String id) {
        List<User> results = jdbcTemplate.query("select * from USER where ID = ?", userMapper, id);
        return results.isEmpty() ? null : results.get(0);
    }

    public List<User> selectAll() {
        return jdbcTemplate.query("select * from USER", userMapper);
    }

    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from USER", Integer.class);
    }

    public void insert(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt =
                    con.prepareStatement("insert into USER(ID, PASSWORD, NICKNAME, REGDATE) values (?, ?, ?, ?)",
                                         new String[]{"NO"});
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getNickname());
            pstmt.setTimestamp(4, Timestamp.valueOf(user.getRegisterDateTime()));
            return pstmt;
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        user.setNo(keyValue.longValue());
    }

    public void update(User user) {
        jdbcTemplate.update("update USER set NICKNAME = ?, PASSWORD = ? where ID = ?",
                            user.getNickname(), user.getPassword(), user.getId());
    }
}
