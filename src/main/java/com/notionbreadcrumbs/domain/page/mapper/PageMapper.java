package com.notionbreadcrumbs.domain.page.mapper;

import com.notionbreadcrumbs.domain.page.dto.PageDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PageMapper implements RowMapper<PageDTO> {
    @Override
    public PageDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return PageDTO.builder()
                .pageId(resultSet.getLong("id"))
                .title(resultSet.getString("title"))
                .content(resultSet.getString("content"))
                .build();
    }
}