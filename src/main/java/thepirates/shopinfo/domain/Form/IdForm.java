package thepirates.shopinfo.domain.Form;

import lombok.Getter;
import lombok.Setter;

//상세 정보 조회, 삭제할 때 요청으로 들어올 형태 (id)

@Getter @Setter
public class IdForm {
    private Long id;
}