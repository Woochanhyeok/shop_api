package thepirates.shopinfo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Holiday {

    @Id @GeneratedValue
    @Column(name="holiday_id")
    private Long id;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shop_id")
    private Shop shop;

    public void setShop(Shop shop) {
        this.shop = shop;
    }
    @Builder
    private Holiday(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
    }
}