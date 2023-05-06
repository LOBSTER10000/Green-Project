package com.sunny.green.dao;

import com.sunny.green.vo.AdminVo;
import com.sunny.green.vo.PickupPageVo;
import com.sunny.green.vo.ProImgVo;
import com.sunny.green.vo.ProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminDao {
    public int insertPro(ProductVo pro);

    public ProductVo selectPro(int proNum);

    public ProImgVo selectImg(int proNum);

    public int insertProImg(ProImgVo proImgVo);

    public List<ProductVo> selectProAll();

    public int deletePro(int pro_num);

    public int deletePro_img(int pro_num);

    public int updatePro(ProductVo productVo);

    // rslist
    public List<PickupPageVo> rsList();

    public int insertAdmin(AdminVo vo);

    public AdminVo selectAdmin(AdminVo vo);

    public AdminVo selectAdmin2(String user_id);
}