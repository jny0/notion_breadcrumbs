package com.notionbreadcrumbs.domain.page.mapper;

import com.notionbreadcrumbs.domain.page.dto.SubPageDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubPageMapper implements RowMapper<SubPageDTO> {
    @Override
    public SubPageDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return SubPageDTO.builder()
                .pageId(resultSet.getLong("id"))
                .title(resultSet.getString("title"))
                .build();
    }
}
