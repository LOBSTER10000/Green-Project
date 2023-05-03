package com.sunny.green.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PickupCategoryVo {
    private int pu_no;
    private int category_no;
    private int pu_category_count;


}
