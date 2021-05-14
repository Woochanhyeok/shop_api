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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="holiday_id")
    private Long id;
    private LocalDate date;
    
    @Builder
    private Holiday(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
    }
}