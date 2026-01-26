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

    public void updateMenu(Long menuId, String name, Integer price) {
        //상품 수정
        int updated = menuMapper.update(menuId, name, price);
        if (updated == 0) {
            throw new IllegalStateException("메뉴 수정 실패");
        }
    }

    public void deleteMenu(Long menuId) {
        //상품 삭제
        int deleted = menuMapper.delete(menuId);
        if (deleted == 0) {
            throw new IllegalStateException("메뉴 삭제 실패");
        }
    }
}
