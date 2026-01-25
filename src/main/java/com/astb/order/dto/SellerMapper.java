package com.astb.order.dto;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerMapper {

    // 가게 총 판매금액
    Long selectStoreTotalSales(Long storeId);
    // 메뉴별 통계
    List<SellerSalesDTO> selectMenuSalesByStore(Long storeId);

}
