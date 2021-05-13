package thepirates.shopinfo.controller;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import thepirates.shopinfo.domain.BusinessTime;
import thepirates.shopinfo.domain.HolidayForm;
import thepirates.shopinfo.domain.Shop;
import thepirates.shopinfo.domain.ShopForm;
import thepirates.shopinfo.service.HolidayService;
import thepirates.shopinfo.service.ShopService;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegisterController {

    private final ShopService shopService;
    private final HolidayService holidayService;

    @PostMapping("/register/shop")
    public Long registerShop(@RequestBody ShopForm shopForm){
        log.info("JSON DATA : " + shopForm.getPhone());
        log.info("BUSINESS TIMES : " + shopForm.getBusinessTimes().size());
        shopForm.getBusinessTimes().forEach(businessTimeForm ->
                log.info("Data : " + businessTimeForm.getDay() + " " + businessTimeForm.getOpen() + " " + businessTimeForm.getClose()));

        Shop shop = Shop.builder()
                .name(shopForm.getName())
                .owner(shopForm.getOwner())
                .description(shopForm.getDescription())
                .level(shopForm.getLevel())
                .address(shopForm.getAddress())
                .phone(shopForm.getPhone())
                .build();
        log.info("SHOP : " + shop.getBusinessTimes().size());
        shopForm.getBusinessTimes().forEach(businessTimeForm -> shop.getBusinessTimes().add(BusinessTime.builder()
                                                                                                .day(businessTimeForm.getDay())
                                                                                                .open(LocalTime.parse(businessTimeForm.getOpen()))
                                                                                                .close(LocalTime.parse(businessTimeForm.getClose())).build()
        ));
        return shopService.save(shop);
    }

    @PostMapping("/register/holiday")
    public int registerHoliday(@RequestBody HolidayForm holidayForm) {
        return holidayService.registerHolidays(holidayForm.getId(),holidayForm.getHolidays());
    }
}