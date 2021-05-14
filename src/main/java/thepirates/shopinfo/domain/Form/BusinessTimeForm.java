package thepirates.shopinfo.domain.Form;

import lombok.Builder;
import lombok.Getter;

//점포 등록할 때, BusinessTime을 받아오는 형태

@Getter
@Builder
public class BusinessTimeForm {

    private String day;
    private String open;
    private String close;

}
