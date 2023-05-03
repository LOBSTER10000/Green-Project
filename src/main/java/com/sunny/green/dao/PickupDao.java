package com.sunny.green.dao;

import com.sunny.green.vo.PickupAddressVo;
import com.sunny.green.vo.PickupCategoryVo;
import com.sunny.green.vo.PickupImgVo;
import com.sunny.green.vo.PickupInfoVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PickupDao {

    public int pickupAddressSave(PickupAddressVo pickupAddressVo);
    public int pickupInfoSave(PickupInfoVo pickupInfoVo);
    public int pickupCategorySave(PickupCategoryVo pickupCategoryVo);
    public int pickupImgSave(PickupImgVo pickupImgVo);
    public int pickupImgUpdate(PickupImgVo pickupImgVo);
}
