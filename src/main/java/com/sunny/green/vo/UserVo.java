package com.sunny.green.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserVo {
       private String user_id;

       private String user_pass;

       private String user_name;

       private String user_email;

       private String user_tel;

       private String zip_code;

       private String address1;

       private String address2;

       private String address3;

       private String address4;

       private int user_point;


}
