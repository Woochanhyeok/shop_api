package thepirates.shopinfo.domain.Form;

import lombok.Builder;
import lombok.Getter;

//전체 조회 때, 응답을 위한 형태

@Getter
@Builder
public class ListForm {

    private String name;
    private String description;
    private int level;
    private String businessStatus;
}
