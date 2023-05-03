package com.sunny.green.service;

import com.sunny.green.vo.PickupAddressVo;
import com.sunny.green.vo.PickupInfoVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PickupService {

    int pickupImg(List<MultipartFile> files, int info_no);
    int pickupAddress(PickupAddressVo pickupAddressVo);
    int pickupInfo(PickupInfoVo pickupInfoVo);
}
