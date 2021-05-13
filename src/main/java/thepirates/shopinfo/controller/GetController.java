package thepirates.shopinfo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import thepirates.shopinfo.domain.ListForm;
import thepirates.shopinfo.domain.Shop;
import thepirates.shopinfo.service.ShopService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GetController {

    private final ShopService shopService;

    @PostMapping("/get/list")
    public List<ListForm> getList() {

        return shopService.findAllList();
    }

}
