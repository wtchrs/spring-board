package org.example.dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.example.model.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

public class BoardDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Board> boardMapper = (rs, rowNum) -> {
        Board board = new Board(rs.getString("TITLE"), rs.getTimestamp("OPENDATE").toLocalDateTime());
        board.setNo(rs.getLong("NO"));
        return board;
    };

    public BoardDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Board selectByTitle(String title) {
        var results = jdbcTemplate.query("select * from BOARD where TITLE = ?", boardMapper, title);
        return results.isEmpty() ? null : results.get(0);
    }

    public List<Board> selectAll() {
        return jdbcTemplate.query("select * from BOARD", boardMapper);
    }

    public void insert(Board board) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement("insert into BOARD(TITLE, OPENDATE) values (?, ?)",
                                                           new String[]{"NO"});
            pstmt.setString(1, board.getTitle());
            pstmt.setTimestamp(2, Timestamp.valueOf(board.getOpenDate()));
            return pstmt;
        }, keyHolder);
        Number no = keyHolder.getKey();
        board.setNo(no.longValue());
    }
}
