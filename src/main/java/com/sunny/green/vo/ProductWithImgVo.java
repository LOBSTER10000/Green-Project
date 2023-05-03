package com.sunny.green.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductWithImgVo {

    private int pro_num;

    private String pro_name;

    private String pro_content;

    private String pro_from;

    private int pro_point;

    private String user_id;

    private int pro_img_no;

    private String pro_img_save_name;

    private String pro_img_path;
}
