package com.sunny.green.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminVo {

    private String admin_id;
    private String admin_pass;
    private String user_id;

    private String user_pass;

    private int admin_role;
}
