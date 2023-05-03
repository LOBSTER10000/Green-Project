package com.sunny.green.service;

import com.sunny.green.vo.PickupAddressVo;
import com.sunny.green.vo.PickupInfoVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PickupService {

    List<Integer> pickupImg(List<MultipartFile> files);
    int pickupImgInfoNo(List<Integer> imgNo, int infoNo);
    int pickupAddress(PickupAddressVo pickupAddressVo);
    int pickupInfo(PickupInfoVo pickupInfoVo);
}

