package com.notionbreadcrumbs.domain.page.controller;

import com.notionbreadcrumbs.domain.page.dto.PageDTO;
import com.notionbreadcrumbs.domain.page.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/page")
@RequiredArgsConstructor
public class PageController {

    private final PageService pageService;

    @GetMapping("/{id}")
    public ResponseEntity<PageDTO> getPage(@PathVariable Long id){
        PageDTO response = pageService.findById(id);
        return ResponseEntity.ok(response);
    }

}
