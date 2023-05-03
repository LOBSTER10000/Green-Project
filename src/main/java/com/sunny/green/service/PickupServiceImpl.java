package com.sunny.green.service;

import com.sunny.green.dao.PickupDao;
import com.sunny.green.vo.PickupAddressVo;
import com.sunny.green.vo.PickupImgVo;
import com.sunny.green.vo.PickupInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PickupServiceImpl implements PickupService {


    private final PickupDao pickupDao;

    // 파일 저장 주소경로
//    @Value("${file.dir.pickupImg}")
//    private String pickupImgDir;

    // pickup 예약 이미지 저장
    @Override
    public List<Integer> pickupImg(List<MultipartFile> files) {

        List<Integer> list = new ArrayList<Integer>();

        for (MultipartFile file : files) {

            String pickupImgDir = "src/main/resources/static/img/pickupUpload/"; // 업로드 디렉토리 경로

            String originalFileName = file.getOriginalFilename();  // 기존 파일 이름

            int result = originalFileName.lastIndexOf(".");
            int length = originalFileName.length();


            // 기존 파일 확장자
            String extension = originalFileName.substring(result, length);
            String uuid = UUID.randomUUID().toString();
            String realPath = uuid + "_" + originalFileName;   // 저장 파일 새로운 이름
            String savePath = pickupImgDir + realPath + extension;  // 저장 파일 경로

            try (FileOutputStream fos = new FileOutputStream(savePath)) {
                fos.write(file.getBytes());
                // ImgVo에 저장
                PickupImgVo pickupImgVo = PickupImgVo.builder()
                        .pu_img_origin_name(originalFileName)
                        .pu_img_save_name(realPath)
                        .pu_img_path(savePath)
                        .build();
                pickupDao.pickupImgSave(pickupImgVo);
                int pu_img_no = pickupImgVo.getPu_img_no(); // insert 이후 생성된 pu_img_no 값을 얻는다.
                list.add(pu_img_no); // 반환값을 list에 넣는다.
            } catch (IOException e) {
                // 파일 저장 실패 시 예외 처리
                e.printStackTrace();
                System.out.println("실패함");
            }
        }

        return list;
    }

    @Override
    public int pickupImgInfoNo(List<Integer> imgNo, int infoNo) {
        int imgResult = 0;
        for (int aa : imgNo) {
            PickupImgVo pickupImgVo1 = PickupImgVo.builder()
                    .pu_img_no(aa)
                    .pu_no(infoNo)
                    .build();
            imgResult = pickupDao.pickupImgUpdate(pickupImgVo1);
        }
        return imgResult;
    }

    // address 테이블에 pickup정보 저장
    @Override
    public int pickupAddress(PickupAddressVo pickupAddressVo) {
        int addressResult = pickupDao.pickupAddressSave(pickupAddressVo);
        return addressResult;
    }

    // info 테이블에 pickup정보 저장
    @Override
    public int pickupInfo(PickupInfoVo pickupInfoVo) {
        int infoResult = pickupDao.pickupInfoSave(pickupInfoVo);
        return infoResult;
    }
}
