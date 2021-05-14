package thepirates.shopinfo.domain.Form;

import lombok.Builder;
import lombok.Getter;

//상세 정보에 들어갈 영업 요일+시간+상태

@Getter
@Builder
public class InfoBusinessDayForm {
    private String day;
    private String open;
    private String close;
    private String status;
}