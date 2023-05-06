package com.sunny.green.service;

import com.sunny.green.dao.PickupDao;
import com.sunny.green.vo.PickupAddressVo;
import com.sunny.green.vo.PickupCategoryVo;
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
            String dbImgDir = "/img/pickupUpload/"; // DB 업로드 디렉토리 경로

            String originalFileName = file.getOriginalFilename();  // 기존 파일 이름

            int result = originalFileName.lastIndexOf(".");
            int length = originalFileName.length();


            // 기존 파일 확장자
            String extension = originalFileName.substring(result, length);
            String uuid = UUID.randomUUID().toString();
            String newName = uuid + extension;   // 저장 파일 새로운 이름

            String realPath = dbImgDir + newName;  // DB 저장 파일 경로
            String savePath = pickupImgDir + newName;  // 실제 저장 파일 경로

            try (FileOutputStream fos = new FileOutputStream(savePath)) {
                fos.write(file.getBytes());
                // ImgVo에 저장
                PickupImgVo pickupImgVo = PickupImgVo.builder()
                        .pu_img_origin_name(originalFileName)
                        .pu_img_save_name(newName)
                        .pu_img_path(realPath)
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

    // category list 값 수정
    @Override
    public List<PickupCategoryVo> pickupCategorySet(List<Map<String, String>> items) {
        List<PickupCategoryVo> list = new ArrayList<>();

        for (Map<String, String> item :items) {
            String category = item.get("categoryVal");
            int count = Integer.parseInt(item.get("countVal"));
            int category_no = 0;
            int pu_category_count = 0;

            if (Objects.equals(category, "일반세탁기")) {
                category_no = 1;
            } else if (Objects.equals(category, "드럼세탁기")) {
                category_no = 2;
            } else if (Objects.equals(category, "탈수기")) {
                category_no = 3;
            } else if (Objects.equals(category, "가정용냉장고")) {
                category_no = 4;
            } else if (Objects.equals(category, "김치냉장고")) {
                category_no = 5;
            } else if (Objects.equals(category, "와인냉장고")) {
                category_no = 6;
            } else if (Objects.equals(category, "업소용냉장고")) {
                category_no = 7;
            } else if (Objects.equals(category, "텔레비전(CRT)")) {
                category_no = 8;
            } else if (Objects.equals(category, "텔레비전(LCD,PDP)")) {
                category_no = 9;
            } else if (Objects.equals(category, "프로젝션 TV")) {
                category_no = 10;
            } else if (Objects.equals(category, "에어컨실내기")) {
                category_no = 11;
            } else if (Objects.equals(category, "에어컨실외기")) {
                category_no = 12;
            } else if (Objects.equals(category, "일체형에어컨")) {
                category_no = 13;
            } else if (Objects.equals(category, "태양광패널")) {
                category_no = 14;
            } else if (Objects.equals(category, "전자레인지")) {
                category_no = 15;
            } else if (Objects.equals(category, "오븐")) {
                category_no = 16;
            } else if (Objects.equals(category, "컴퓨터본체")) {
                category_no = 17;
            }
            pu_category_count = count;
            PickupCategoryVo pickupCategoryVo = new PickupCategoryVo();
            pickupCategoryVo.setCategory_no(category_no);
            pickupCategoryVo.setPu_category_count(pu_category_count);

            list.add(pickupCategoryVo);
        }
        return list;
    }

    @Override
    public int pickupCategoryInsert(List<PickupCategoryVo> items, int infoNo) {
        int result = 0;
        for (PickupCategoryVo item : items) {
            item.setPu_no(infoNo);
            result = pickupDao.pickupCategorySave(item);
        }
        return result;
    }

    // info 테이블에 pickup정보 저장
    @Override
    public int pickupInfo(PickupInfoVo pickupInfoVo) {
        int infoResult = pickupDao.pickupInfoSave(pickupInfoVo);
        return infoResult;
    }

    @Override
    public List<PickupImgVo> pickupImgView(List<Integer> pu_img_no) {
        List<PickupImgVo> pickupImg = new ArrayList<>();
            for (Integer img :pu_img_no) {
                PickupImgVo pickupImgVo = new PickupImgVo();
                pickupImgVo = pickupDao.pickupImgView(img);
                pickupImg.add(pickupImgVo);
            }
        return pickupImg;
    }
}
