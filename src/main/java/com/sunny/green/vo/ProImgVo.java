package com.sunny.green.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProImgVo {
    private int pro_img_no;

    private int pro_num;

    private String user_id;

    private String pro_img_save_name;

    private String pro_img_path;
}
