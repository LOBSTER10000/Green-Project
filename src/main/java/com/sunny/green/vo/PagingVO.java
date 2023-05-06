package com.sunny.green.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagingVO {
    private int startRow;
    private int endRow;

//    startRow = 시작글 번호(쿼리에서 활용)
//
//    endRow = 마지막 글 번호(쿼리에서 활용)

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지사이즈 */
    private int pageSize = 10; //한페이지에 나오는 게시물 개수

    private int pageGroupSize = 3; // 페이지 번호 몇개 보여줄 것인지
}
