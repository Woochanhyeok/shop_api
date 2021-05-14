package thepirates.shopinfo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import thepirates.shopinfo.domain.Form.IdForm;
import thepirates.shopinfo.domain.Form.InfoForm;
import thepirates.shopinfo.domain.Form.ListForm;
import thepirates.shopinfo.service.ShopService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GetController {

    private final ShopService shopService;

    @GetMapping("/get/list")
    public List<ListForm> getList() {
        return shopService.findAllList();
    }

    @GetMapping("/get/info")
    public InfoForm getInfo(@RequestBody IdForm idForm) {
        return shopService.getInfo(idForm);
    }

    @DeleteMapping("/delete")
    public Long deleteShop(@RequestBody IdForm idForm) {
        return shopService.delete(shopService.findOne(idForm.getId()));
    }

}
