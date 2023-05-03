package com.sunny.green.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PickupImgVo {
    private int pu_img_no;
    private int pu_no;
    private String pu_img_origin_name;
    private String pu_img_save_name;
    private String pu_img_path;
}
