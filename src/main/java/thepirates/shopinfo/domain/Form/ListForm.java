package thepirates.shopinfo.domain.Form;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ListForm {

    private String name;
    private String description;
    private int level;
    private String businessStatus;
}
