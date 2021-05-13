package thepirates.shopinfo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import thepirates.shopinfo.domain.Shop;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShopRepository {

    private final EntityManager em;

    public void save (Shop shop) {
        if (shop.getId() == null) {
            em.persist(shop);
        } else {
            em.merge(shop);
        }
    }

    public Shop findById(Long id) {
        return em.find(Shop.class, id);
    }

    public List<Shop> findAll () {
        return em.createQuery("select s from Shop s", Shop.class)
                .getResultList();
    }

}
