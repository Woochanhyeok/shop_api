package thepirates.shopinfo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Shop {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shop_id")
    private Long id;

    private String name;
    private String owner;
    private String description;
    private int level;
    private String address;
    private String phone;

    @OneToMany(cascade = CascadeType.ALL)
    private List<BusinessTime> businessTimes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Holiday> holidays = new ArrayList<>();

    //== holiday 설정 ==
//    public void setHolidays(List<Holiday> holidays) {
//        this.holidays = holidays;
//    }

    @Builder
    private Shop(Long id, String name, String owner, String description,
                  int level, String address, String phone) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.level = level;
        this.address = address;
        this.phone = phone;
    }


}
