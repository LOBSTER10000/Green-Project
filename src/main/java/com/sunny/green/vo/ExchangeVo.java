package com.sunny.green.vo;




import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeVo {

    private int ex_num;

    private int pro_num;

    private String user_id;

    private String user_name;

    private String user_email;

    private String pro_name;

    private int user_point;

    private int pay_point;

    private int remain_point;
    private Timestamp ex_date;

    private String ex_uuid_num;

    private String pro_img_save_name;

    private UserVo userVo;


}
