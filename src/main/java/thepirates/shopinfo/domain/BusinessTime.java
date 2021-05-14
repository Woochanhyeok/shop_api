package thepirates.shopinfo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class BusinessTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="business_time_id")
    private Long id;
    private String day;
    private String open;
    private String close;

    @Builder
    private BusinessTime(Long id, String day, String open, String close) {
        this.id = id;
        this.day = day;
        this.open = open;
        this.close = close;
    }
}