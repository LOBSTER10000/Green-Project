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
public class NoticeVo {
    private int notice_num;
    private String notice_title;
    private String notice_content;
    private String user_id;
    private LocalDateTime notice_date;

}
