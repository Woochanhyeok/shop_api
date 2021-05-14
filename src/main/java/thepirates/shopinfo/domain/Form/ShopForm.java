package thepirates.shopinfo.domain.Form;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

//점포 추가할 때, 요청으로 들어올 형태

@Getter
@Builder
public class ShopForm {

    private String name;
    private String owner;
    private String description;
    private int level;
    private String address;
    private String phone;

    private List<BusinessTimeForm> businessTimes;

}
