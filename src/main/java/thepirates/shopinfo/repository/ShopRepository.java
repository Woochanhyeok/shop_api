package thepirates.shopinfo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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



    public void delete (Shop shop) {
        em.createQuery("delete from Shop s where s.id = :id")
                .setParameter("id", shop.getId())
                .executeUpdate();
    }

    public Shop findById(Long id) {
        return em.find(Shop.class, id);
    }

    //level을 기준으로 오름차순으로 정렬해서 전체 Shop 리스트 반환
    public List<Shop> findAllOrderByLevel() {
        return em.createQuery("select s from Shop s order by s.level", Shop.class)
                .getResultList();
    }

}
