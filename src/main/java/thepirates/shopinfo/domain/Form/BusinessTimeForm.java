package thepirates.shopinfo.domain.Form;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BusinessTimeForm {

    private String day;
    private String open;
    private String close;

}
