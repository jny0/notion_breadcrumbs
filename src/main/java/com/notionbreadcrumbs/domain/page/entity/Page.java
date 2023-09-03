package com.notionbreadcrumbs.domain.page.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    @Id
    private Long id;
    private String title;
    private String content;
    private Long parentId;
}
