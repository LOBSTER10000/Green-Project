package com.sunny.green.dao;

import com.sunny.green.vo.ExchangeVo;
import com.sunny.green.vo.ProductWithImgVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExchangeDao {

    public List<ProductWithImgVo> selectProductAll();

    public ProductWithImgVo selectProOne(int pro_num);

    public int insertExchange(ExchangeVo exchangeVo);

    public ExchangeVo selectExchangeOne(int ex_num);

    public List<ExchangeVo> selectExchangeId(String user_id);
}
