package com.sunny.green.domain;

import com.sunny.green.vo.PageVo;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class Criteria {

    private int pageNum;

    private int countPerPage;

    private String keyword;
    private String condition;
    public Criteria(){
        this.pageNum = 1;
        this.countPerPage = 10;
    }
}
