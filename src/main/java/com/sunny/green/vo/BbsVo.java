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
public class BbsVo {
    private int board_num;
    private String board_title;
    private String board_content;
    private String user_id;
    private LocalDateTime board_date;
    private String comment_exist;
    private int board_hit;


}
