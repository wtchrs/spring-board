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
                new Post(rs.getLong("BOARD_NO"), rs.getLong("AUTHOR_NO"), rs.getString("TITLE"), rs.getString("CONTENT"),
                         rs.getTimestamp("DATE").toLocalDateTime());
        post.setNo(rs.getLong("NO"));
        post.setViews(rs.getLong("VIEWS"));
        return post;
    };

    public PostDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Post> selectByBoard(Long boardNo) {
        return jdbcTemplate.query("select * from POST where BOARD_NO = ?", postMapper, boardNo);
    }

    public List<Post> selectByAuthor(Long authorNo) {
        return jdbcTemplate.query("select * from POST where AUTHOR_NO = ?", postMapper, authorNo);
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

    public void insert(Post post) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into POST (BOARD_NO, AUTHOR_NO, TITLE, CONTENT, POSTDATE, VIEWS) values (?, ?, ?, ?, ?, ?)");
            pstmt.setLong(1, post.getBoardNo());
            pstmt.setLong(2, post.getAuthorNo());
            pstmt.setString(3, post.getTitle());
            pstmt.setString(4, post.getContent());
            pstmt.setTimestamp(5, Timestamp.valueOf(post.getDate()));
            pstmt.setLong(6, post.getViews());
            return pstmt;
        }, keyHolder);
        Number no = keyHolder.getKey();
        post.setNo(no.longValue());
    }

    public void update(Post post) {
        jdbcTemplate.update("update POST set BOARD_NO = ?, TITLE = ?, CONTENT = ? where NO = ?",
                            post.getBoardNo(), post.getTitle(), post.getContent(), post.getNo());
    }

    public void increaseViews(Long no) {
        jdbcTemplate.update("update POST set VIEWS = VIEWS + 1 where NO = ?", no);
    }
}
