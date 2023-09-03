package com.notionbreadcrumbs.domain.page.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubPageDTO {
    private Long pageId;
    private String title;
}
