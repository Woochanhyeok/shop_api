package thepirates.shopinfo.domain.Form;

import lombok.Builder;
import lombok.Getter;

//상세 정보 조회, 삭제할 때 요청으로 들어올 형태 (id)

@Getter
@Builder
public class IdForm {
    private Long id;
}
