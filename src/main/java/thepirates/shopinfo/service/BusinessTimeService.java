package thepirates.shopinfo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thepirates.shopinfo.domain.BusinessTime;
import thepirates.shopinfo.domain.Shop;
import thepirates.shopinfo.repository.BusinessTimeRepository;
import thepirates.shopinfo.repository.ShopRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusinessTimeService {

    private final BusinessTimeRepository businessTimeRepository;
    private final ShopRepository shopRepository;

    @Transactional
    public Long save(Long id, BusinessTime businessTime) {
        Shop shop = shopRepository.findById(id);
        shop.getBusinessTimes().add(businessTime);
        businessTime.setShop(shop);
        businessTimeRepository.save(businessTime);
        return businessTime.getId();
    }
}
