package com.astb.order.service;

import com.astb.order.dto.SellerMapper;
import com.astb.order.dto.SellerSalesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerMapper sellerMapper;

    // 점주 가게별 총 판매금액
    public long getStoreTotalSales(Long storeId) {
        Long total = sellerMapper.selectStoreTotalSales(storeId);
        return total == null ? 0L : total;
    }

    // 가게별 메뉴 판매 통계
    public List<SellerSalesDTO> getMenuSalesByStore(Long storeId) {
        return sellerMapper.selectMenuSalesByStore(storeId);
    }
}
