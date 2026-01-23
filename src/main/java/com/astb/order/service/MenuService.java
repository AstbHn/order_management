package com.astb.order.service;

import com.astb.order.domain.menu.Menu;
import com.astb.order.domain.menu.MenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuMapper menuMapper;

    public void registerMenu(Menu menu) {
        menuMapper.insertMenu(menu);
    }
}
