package com.sunny.green.service;

import com.sunny.green.vo.PickupAddressVo;
import com.sunny.green.vo.PickupCategoryVo;
import com.sunny.green.vo.PickupImgVo;
import com.sunny.green.vo.PickupInfoVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface PickupService {

    List<Integer> pickupImg(List<MultipartFile> files);
    int pickupImgInfoNo(List<Integer> imgNo, int infoNo);
    int pickupAddress(PickupAddressVo pickupAddressVo);
    List<PickupCategoryVo> pickupCategorySet(List<Map<String, String>> items);
    int pickupCategoryInsert(List<PickupCategoryVo> items, int infoNo);
    int pickupInfo(PickupInfoVo pickupInfoVo);
    List<PickupImgVo> pickupImgView(List<Integer> pu_img_no);


}

