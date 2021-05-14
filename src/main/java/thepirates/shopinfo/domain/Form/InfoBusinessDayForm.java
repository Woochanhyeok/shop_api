package thepirates.shopinfo.domain.Form;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InfoBusinessDayForm {
    private String day;
    private String open;
    private String close;
    private String status;
}