package thepirates.shopinfo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thepirates.shopinfo.domain.Holiday;
import thepirates.shopinfo.domain.Shop;
import thepirates.shopinfo.repository.HolidayRepository;
import thepirates.shopinfo.repository.ShopRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HolidayService {

    private final HolidayRepository holidayRepository;
    private final ShopRepository shopRepository;

    @Transactional
    public Long save(Holiday holiday) {
        holidayRepository.save(holiday);
        return holiday.getId();
    }

    @Transactional
    public int registerHolidays(Long id, List<LocalDate> holidays) {
        Shop shop = shopRepository.findById(id);

        holidays.forEach(holidayDate -> {
            Holiday holiday = Holiday.builder().date(holidayDate).build();
            shop.getHolidays().add(holiday);
            holiday.setShop(shop);
            save(holiday);
        });
        return holidays.size();
    }

}
