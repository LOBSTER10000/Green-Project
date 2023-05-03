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
import java.text.SimpleDateFormat;
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
    public int pickupImg(List<MultipartFile> files, int info_no) {

        for (MultipartFile file : files) {

            String pickupImgDir = "src/main/resources/static/img/pickupUpload/"; // 업로드 디렉토리 경로

            String originalFileName = file.getOriginalFilename();  // 기존 파일 이름

            int result = originalFileName.lastIndexOf(".");
            int length = originalFileName.length();

            Random random = new Random();
            int randomNo = random.nextInt(10000);

            // 기존 파일 확장자
            String extension = originalFileName.substring(result, length);
            String uuid = UUID.randomUUID().toString();
            String realPath = pickupImgDir + uuid + originalFileName;
            //파일명에 붙는 현재 시간
            Date currentDate = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
            String nowDate = simpleDateFormat.format(currentDate);

            String newFileName = info_no + "_" + nowDate + "_" + randomNo + extension;  // 저장 파일 새로운 이름
            String savePath = pickupImgDir + newFileName;  // 저장 파일 경로

            try (FileOutputStream fos = new FileOutputStream(realPath)) {
                fos.write(file.getBytes());
                System.out.println(savePath);
                System.out.println(newFileName);
                System.out.println(originalFileName);
                System.out.println(realPath);
                // ImgVo에 저장
                PickupImgVo pickupImgVo = new PickupImgVo();
                        pickupImgVo.setPu_no(info_no);
                        pickupImgVo.setPu_img_origin_name(originalFileName);
                        pickupImgVo.setPu_img_save_name(newFileName);
                        pickupImgVo.setPu_img_path(savePath);
                pickupDao.pickupImgSave(pickupImgVo);   // DB에 이미지 저장
            } catch (IOException e) {
                // 파일 저장 실패 시 예외 처리
                e.printStackTrace();
                System.out.println("실패함");
            }
        }

        return 1;
    }

    // address 테이블에 pickup정보 저장
    @Override
    public int pickupAddress(PickupAddressVo pickupAddressVo) {
        int result = pickupDao.pickupAddressSave(pickupAddressVo);
        return result;
    }

    // info 테이블에 pickup정보 저장
    @Override
    public int pickupInfo(PickupInfoVo pickupInfoVo) {
        int result = pickupDao.pickupInfoSave(pickupInfoVo);
        return result;
    }
}
