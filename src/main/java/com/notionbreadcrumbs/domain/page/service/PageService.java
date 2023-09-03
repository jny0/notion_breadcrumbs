package com.notionbreadcrumbs.domain.page.service;

import com.notionbreadcrumbs.domain.page.dao.PageDAO;
import com.notionbreadcrumbs.domain.page.dto.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageService {
    private final PageDAO pageDAO;

    public PageDTO findById(Long id){
        return pageDAO.findById(id);
    }
}
