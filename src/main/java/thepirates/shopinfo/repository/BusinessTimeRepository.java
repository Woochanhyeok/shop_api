package thepirates.shopinfo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import thepirates.shopinfo.domain.BusinessTime;
import thepirates.shopinfo.domain.Holiday;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class BusinessTimeRepository {

    private final EntityManager em;

    public void save (BusinessTime businessTime) {
        if (businessTime.getId() == null) {
            em.persist(businessTime);
        } else {
            em.merge(businessTime);
        }
    }
}
