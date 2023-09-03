package com.notionbreadcrumbs.domain.page.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class PageNotFoundException extends RuntimeException{
    public PageNotFoundException(){
        super("존재하지 않는 페이지입니다.");
    }
}
