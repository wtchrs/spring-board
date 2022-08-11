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

public class DefaultBoardDao implements BoardDao {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Board> boardMapper = (rs, rowNum) -> {
        Board board = new Board(rs.getString("TITLE"), rs.getTimestamp("OPENDATE").toLocalDateTime());
        board.setNo(rs.getLong("NO"));
        return board;
    };

    public DefaultBoardDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Board selectByNo(Long no) {
        List<Board> results = jdbcTemplate.query("select * from BOARD where NO = ?", boardMapper, no);
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Board selectByTitle(String title) {
        List<Board> results = jdbcTemplate.query("select * from BOARD where TITLE = ?", boardMapper, title);
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<Board> selectAll() {
        return jdbcTemplate.query("select * from BOARD", boardMapper);
    }

    @Override
    public void insert(Board board) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("insert into BOARD(TITLE, OPENDATE) values (?, ?)",
                                                        new String[]{"NO"});
            ps.setString(1, board.getTitle());
            ps.setTimestamp(2, Timestamp.valueOf(board.getOpenDate()));
            return ps;
        }, keyHolder);
        Number no = keyHolder.getKey();
        board.setNo(no.longValue());
    }

    @Override
    public void update(Board board) {
        jdbcTemplate.update("update BOARD set TITLE = ? where NO = ?", board.getTitle(), board.getNo());
    }
}
