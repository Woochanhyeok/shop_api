package thepirates.shopinfo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import thepirates.shopinfo.domain.Holiday;
import thepirates.shopinfo.domain.Shop;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class HolidayRepository {

    private final EntityManager em;

    public void save (Holiday holiday) {
        if (holiday.getId() == null) {
            em.persist(holiday);
        } else {
            em.merge(holiday);
        }
    }
}
