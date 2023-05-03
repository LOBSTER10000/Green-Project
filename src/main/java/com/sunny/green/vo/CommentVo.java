package com.sunny.green.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentVo {
    private int com_num;
    private String com_content;
    private LocalDateTime com_date;
    private String user_id;
    private int board_num;
}