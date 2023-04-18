package com.sunny.green.dao;

import com.sunny.green.vo.PickupAddressVo;
import com.sunny.green.vo.PickupInfoVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PickupDao {

    public int pickupInfoSave(PickupAddressVo pickupAddressVo);
    public int pickupInfoSave2(PickupInfoVo pickupInfoVo);

}
