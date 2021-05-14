package thepirates.shopinfo.domain.Form;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

//휴무 등록할 때 요청으로 들어올 형태

@Getter
@Builder
public class HolidayForm {
    private Long id;
    private List<LocalDate> holidays;
}
