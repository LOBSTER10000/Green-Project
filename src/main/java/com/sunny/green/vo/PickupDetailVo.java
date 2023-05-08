package com.sunny.green.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PickupDetailVo {

    //info table
    private int pu_no; // 예약번호

    private int pu_address_no; //주소번호

    private String user_id;

    private int house_no;

    private String pu_elevator;

    private String pu_day; // 예약요청날짜

    private LocalDateTime pu_date; // 접수날짜

    private String pu_img;

    private String pu_memo;

    //address table

    private String pu_address_name;

    private String pu_address_tel;

    private int pu_address_zip;

    private String pu_address1;

    private String pu_address2;

    private String pu_address3;

    private String pu_address4;

    private int give_point;
    // img table

    private String pu_img_origin_name;
    private String pu_img_save_name;
    private String pu_img_path;






}
