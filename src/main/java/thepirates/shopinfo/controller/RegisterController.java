package thepirates.shopinfo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import thepirates.shopinfo.domain.BusinessTime;
import thepirates.shopinfo.domain.Form.HolidayForm;
import thepirates.shopinfo.domain.Shop;
import thepirates.shopinfo.domain.Form.ShopForm;
import thepirates.shopinfo.service.BusinessTimeService;
import thepirates.shopinfo.service.HolidayService;
import thepirates.shopinfo.service.ShopService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegisterController {

    private final ShopService shopService;
    private final HolidayService holidayService;
    private final BusinessTimeService businessTimeService;

    @PostMapping("/register/shop")
    public Long registerShop(@RequestBody ShopForm shopForm){

        //받아온 ShopForm으로 Shop 엔티티 생성
        Shop shop = Shop.builder()
                .name(shopForm.getName())
                .owner(shopForm.getOwner())
                .description(shopForm.getDescription())
                .level(shopForm.getLevel())
                .address(shopForm.getAddress())
                .phone(shopForm.getPhone())
                .build();
        Long shopId = shopService.save(shop);
        //ShopForm의 BusinessTimes 리스트로 BusinessTimes 엔티티 생성
        shopForm.getBusinessTimes().forEach(businessTimeForm -> {
            businessTimeService.save(shopId, BusinessTime.builder()
                    .day(businessTimeForm.getDay())
                    .open(businessTimeForm.getOpen())      //문자열로 들어오는 시간 LocalTime으로 파싱
                    .close(businessTimeForm.getClose())
                    .build());
        });
        log.info("SHOP : " + shop.getBusinessTimes().size());
        return shopId;
    }

    @PostMapping("/register/holiday")
    public int registerHoliday(@RequestBody HolidayForm holidayForm) {
        return holidayService.registerHolidays(holidayForm.getId(),holidayForm.getHolidays());
    }
}