package com.sunny.green.vo;


import org.springframework.context.annotation.Configuration;


@Configuration

public class PageVo {
    private int page;           // 현재 페이지 번호
    private int recordPerPage;  // 페이지당 출력할 데이터 개수
    private int pageSize;       // 화면 하단에 출력할 페이지 개수
    private String keyword;     // 검색 키워드
    private String searchType;  // 검색 유형

    public PageVo(int page, int recordPerPage, int pageSize, String keyword, String searchType) {
        this.page = page;
        this.recordPerPage = recordPerPage;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.searchType = searchType;
    }

    public PageVo() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRecordPerPage() {
        return recordPerPage;
    }

    public void setRecordPerPage(int recordPerPage) {
        this.recordPerPage = recordPerPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}
