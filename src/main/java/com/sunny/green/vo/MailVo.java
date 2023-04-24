package com.sunny.green.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailVo {

    private int mail_num;

    private String mail_receiver;

    private String mail_title;

    private String mail_content;

    private LocalDateTime mail_date;
}