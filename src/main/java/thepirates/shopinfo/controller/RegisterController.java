package thepirates.shopinfo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import thepirates.shopinfo.domain.Form.HolidayForm;
import thepirates.shopinfo.domain.Form.ShopForm;
import thepirates.shopinfo.service.HolidayService;
import thepirates.shopinfo.service.ShopService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegisterController {

    private final ShopService shopService;
    private final HolidayService holidayService;

    @PostMapping("/register/shop")
    public Long registerShop(@RequestBody ShopForm shopForm){
        return shopService.save(shopForm);
    }

    @PostMapping("/register/holiday")
    public int registerHoliday(@RequestBody HolidayForm holidayForm) {
        return holidayService.registerHolidays(holidayForm.getId(),holidayForm.getHolidays());
    }
}