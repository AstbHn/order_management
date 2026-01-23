package com.astb.order.service;

import com.astb.order.domain.menu.Menu;
import com.astb.order.domain.menu.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuMapper menuMapper;

    public void registerMenu(Menu menu) {
        //상품 등록
        menuMapper.insertMenu(menu);
    }

    public List<Menu> findByStoreId(Long storeId) {
        // 가게 아이디를 이용한 메뉴 조회
        return menuMapper.findByStoreId(storeId);
    }
}
