package thepirates.shopinfo.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
public class BusinessTime {
    @Id @GeneratedValue
    @Column(name="business_time_id")
    private Long id;
    private String day;
    private LocalTime open;
    private LocalTime close;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shop_id")
    private Shop shop;

    public void setShop(Shop shop) {
        this.shop = shop;
        shop.getBusinessTimes().add(this);
    }

    @Builder
    private BusinessTime(Long id, String day, LocalTime open, LocalTime close) {
        this.id = id;
        this.day = day;
        this.open = open;
        this.close = close;
    }
}