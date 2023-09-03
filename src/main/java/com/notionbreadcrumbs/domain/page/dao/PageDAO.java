package com.notionbreadcrumbs.domain.page.dao;

import com.notionbreadcrumbs.domain.page.dto.PageDTO;
import com.notionbreadcrumbs.domain.page.dto.SubPageDTO;
import com.notionbreadcrumbs.domain.page.mapper.PageMapper;
import com.notionbreadcrumbs.domain.page.mapper.SubPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PageDAO {
    private final JdbcTemplate jdbcTemplate;

    public PageDTO findById(Long pageId) {
        // 페이지 기본 정보 조회
        String pageQuery = "SELECT * FROM pages WHERE id = ?";
        PageDTO pageDTO = jdbcTemplate.queryForObject(pageQuery, new Object[]{pageId}, new PageMapper());

        if (pageDTO != null) {
            // 서브페이지 조회
            String subPagesQuery = "SELECT id, title FROM pages WHERE parent_id = ?";
            List<SubPageDTO> subPages = jdbcTemplate.query(subPagesQuery, new Object[]{pageId}, new SubPageMapper());
            pageDTO.setSubPages(subPages);

            // 브로드크럼스 조회
            String breadcrumbsQuery = "WITH RECURSIVE BreadcrumbHierarchy AS (" +
                    "SELECT id, title, parent_id FROM pages WHERE id = ?" +
                    " UNION ALL " +
                    "SELECT p.id, p.title, p.parent_id FROM pages p " +
                    "INNER JOIN BreadcrumbHierarchy b ON p.id = b.parent_id" +
                    ")" +
                    "SELECT id, title FROM BreadcrumbHierarchy";
            List<SubPageDTO> breadcrumbs = jdbcTemplate.query(breadcrumbsQuery, new Object[]{pageId}, new SubPageMapper());
            pageDTO.setBreadcrumbs(breadcrumbs);
        }

        return pageDTO;
    }
}
