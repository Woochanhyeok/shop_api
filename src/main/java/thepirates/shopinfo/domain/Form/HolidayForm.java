package thepirates.shopinfo.domain.Form;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class HolidayForm {
    private Long id;
    private List<LocalDate> holidays;
}
