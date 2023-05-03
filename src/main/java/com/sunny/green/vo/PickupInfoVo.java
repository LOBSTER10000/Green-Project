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
    private int pu_no;

    private int pu_address_no;

    private String user_id;

    private int house_no;

    private String pu_elevator;

    private String pu_day;

    private String pu_img;

    private String text_memo;
}
