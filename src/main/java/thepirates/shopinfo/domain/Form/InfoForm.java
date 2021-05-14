package thepirates.shopinfo.domain.Form;

import lombok.*;

import java.util.List;

@Getter @Builder
public class InfoForm {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;

    private List<InfoBusinessDayForm> infoBusinessDayForms;
}