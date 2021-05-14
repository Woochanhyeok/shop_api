package thepirates.shopinfo.domain.Form;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ShopForm {

    private String name;
    private String owner;
    private String description;
    private int level;
    private String address;
    private String phone;

    private List<BusinessTimeForm> businessTimes;

    @Builder
    public ShopForm(String name, String owner, String description, int level, String address, String phone, List<BusinessTimeForm> businessTimeForms) {
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.level = level;
        this.address = address;
        this.phone = phone;
        this.businessTimes = businessTimeForms;
    }

}
