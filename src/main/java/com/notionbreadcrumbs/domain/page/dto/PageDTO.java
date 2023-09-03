package com.notionbreadcrumbs.domain.page.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageDTO {

    private Long pageId;
    private String title;
    private String content;
    private List<SubPageDTO> subPages;
    private List<SubPageDTO> breadcrumbs;

}
