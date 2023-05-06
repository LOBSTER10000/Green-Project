package com.sunny.green.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PickupInfoVo {
    private int pu_no; // 예약번호

    private int pu_address_no; //주소번호

    private String user_id;

    private int house_no;

    private String pu_elevator;

    private String pu_day; // 예약요청날짜

    private String pu_date; // 접수날짜

    private String pu_img;

    private String pu_memo;


}
