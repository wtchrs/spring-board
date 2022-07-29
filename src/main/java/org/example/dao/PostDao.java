package org.example.dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.example.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

public class PostDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Post> postMapper = (rs, rowNum) -> {
        Post post =
                new Post(rs.getString("BOARD"), rs.getString("TITLE"), rs.getString("CONTENT"), rs.getString("AUTHOR"),
                         rs.getTimestamp("DATE").toLocalDateTime());
        post.setNo(rs.getLong("NO"));
        post.setViews(rs.getLong("VIEWS"));
        return post;
    };

    public PostDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Post> selectByBoard(String board) {
        return jdbcTemplate.query("select * from POST where BOARD = ?", postMapper, board);
    }

    public List<Post> selectByTitle(String title) {
        return jdbcTemplate.query("select * from POST where instr(TITLE, ?) > 0", postMapper, title);
    }

    public List<Post> selectByContent(String content) {
        return jdbcTemplate.query("select * from POST where instr(CONTENT, ?) > 0", postMapper, content);
    }

    public List<Post> selectByTitleAndContent(String contains) {
        return jdbcTemplate.query("select * from POST where instr(TITLE, ?) > 0 or instr(CONTENT, ?) >0", postMapper,
                                  contains, contains);
    }

    public List<Post> selectByAuthor(String author) {
        return jdbcTemplate.query("select * from POST where AUTHOR = ?", postMapper, author);
    }

    public void insert(Post post) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into POST (BOARD, TITLE, CONTENT, AUTHOR, POSTDATE, VIEWS) values (?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, post.getBoard());
            pstmt.setString(2, post.getTitle());
            pstmt.setString(3, post.getContent());
            pstmt.setString(4, post.getAuthor());
            pstmt.setTimestamp(5, Timestamp.valueOf(post.getDate()));
            pstmt.setLong(6, post.getViews());
            return pstmt;
        }, keyHolder);
        Number no = keyHolder.getKey();
        post.setNo(no.longValue());
    }

    public void update(Post post) {
        jdbcTemplate.update("update POST set BOARD = ?, TITLE = ?, CONTENT = ? where NO = ?",
                            post.getBoard(), post.getTitle(), post.getContent(), post.getNo());
    }

    public void increaseViews(Long no) {
        jdbcTemplate.update("update POST set VIEWS = VIEWS + 1 where NO = ?", no);
    }
}
