package thepirates.shopinfo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thepirates.shopinfo.domain.Holiday;
import thepirates.shopinfo.domain.Shop;
import thepirates.shopinfo.repository.ShopRepository;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final HolidayService holidayService;

    @Transactional
    public Long save(Shop shop) {
        shopRepository.save(shop);
        return shop.getId();
    }


    public List<Shop> findAll() {
        return shopRepository.findAll();
    }
}
