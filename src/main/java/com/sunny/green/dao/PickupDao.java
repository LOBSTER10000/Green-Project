package com.sunny.green.dao;

import com.sunny.green.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PickupDao {

    public int pickupAddressSave(PickupAddressVo pickupAddressVo);
    public int pickupInfoSave(PickupInfoVo pickupInfoVo);
    public int pickupCategorySave(PickupCategoryVo items);
    public int pickupImgSave(PickupImgVo pickupImgVo);



    // pick_up List  --- info table 간단하게 부르기
    public List<PickupDetailVo> rsList();
    // pick_up List 검색 ---- 그중에 검색
    List<PickupDetailVo> rsList2(PageVo search, String searchType, String keyword);

    // rs_info 상세정보 --- 예약자 관련 table 전체 불러옴
    public PickupDetailVo rs_info(int pu_no,int pu_address_no);

    public int pickupImgUpdate(PickupImgVo pickupImgVo);
    public PickupImgVo pickupImgView(Integer pu_no);

}
