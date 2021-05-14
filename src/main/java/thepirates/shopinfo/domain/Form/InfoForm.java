package thepirates.shopinfo.domain.Form;

import lombok.*;

import java.util.List;

//상세 정보 조회 때, 응답을 위한 형태

@Getter @Builder
public class InfoForm {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;

    private List<InfoBusinessDayForm> infoBusinessDayForms;
}