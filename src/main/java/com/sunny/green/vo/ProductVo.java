package com.sunny.green.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVo {

    private int pro_num;

    private String pro_name;

    private String pro_content;

    private String pro_from;
    private String pro_img;

    private int pro_point;

    private String user_id;


    private ProImgVo proImgVo;


}
