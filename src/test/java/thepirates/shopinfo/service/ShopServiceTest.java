package thepirates.shopinfo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import thepirates.shopinfo.Exception.OpenEqualsCloseException;
import thepirates.shopinfo.domain.Form.*;
import thepirates.shopinfo.domain.Shop;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ShopServiceTest {

    @Autowired ShopService shopService;
    @Autowired HolidayService holidayService;

    @BeforeEach
    public void set() {
        List<BusinessTimeForm> businessTimeForms = new ArrayList<>();
        businessTimeForms.add(BusinessTimeForm.builder().day("Monday").open("13:00").close("23:00").build());
        businessTimeForms.add(BusinessTimeForm.builder().day("Tuesday").open("13:00").close("23:00").build());
        businessTimeForms.add(BusinessTimeForm.builder().day("Wednesday").open("09:00").close("18:00").build());
        businessTimeForms.add(BusinessTimeForm.builder().day("Thursday").open("09:00").close("23:00").build());
        businessTimeForms.add(BusinessTimeForm.builder().day("Friday").open("09:00").close("23:00").build());

        ShopForm shopForm = ShopForm.builder()
                .name("인어수산")
                .owner("장인어")
                .description("인천소래포구 종합어시장 갑각류센터 인어수산")
                .level(2)
                .address("인천광역시 남동구 논현동 680-1 소래포구 종합어시장 1층 1호")
                .phone("010-1111-2222")
                .businessTimes(businessTimeForms)
                .build();
        shopService.save(shopForm);

        businessTimeForms = new ArrayList<>();
        businessTimeForms.add(BusinessTimeForm.builder().day("Monday").open("13:00").close("23:00").build());
        businessTimeForms.add(BusinessTimeForm.builder().day("Tuesday").open("13:00").close("23:00").build());
        businessTimeForms.add(BusinessTimeForm.builder().day("Wednesday").open("09:00").close("18:00").build());
        businessTimeForms.add(BusinessTimeForm.builder().day("Thursday").open("09:00").close("23:00").build());
        businessTimeForms.add(BusinessTimeForm.builder().day("Friday").open("09:00").close("23:00").build());

        shopForm = ShopForm.builder()
                .name("해적수산")
                .owner("박해적")
                .description("노량진 시장 광어, 참돔 등 싱싱한 고퀄 활어 전문 횟집")
                .level(1)
                .address("서울 동작구 노량진동 13-8 노량진수산시장 활어 001")
                .phone("010-1234-1234")
                .businessTimes(businessTimeForms)
                .build();
        shopService.save(shopForm);
    }

    @Test
    public void 점포_영업_시작시간_종료시간_동일() throws  Exception {
        //given
        List<BusinessTimeForm> businessTimeForms = new ArrayList<>();
        businessTimeForms.add(BusinessTimeForm.builder().day("Monday").open("13:00").close("13:00").build());
        businessTimeForms.add(BusinessTimeForm.builder().day("Tuesday").open("13:00").close("23:00").build());
        businessTimeForms.add(BusinessTimeForm.builder().day("Wednesday").open("09:00").close("18:00").build());
        businessTimeForms.add(BusinessTimeForm.builder().day("Thursday").open("09:00").close("23:00").build());
        businessTimeForms.add(BusinessTimeForm.builder().day("Friday").open("09:00").close("23:00").build());

        ShopForm shopForm = ShopForm.builder()
                .name("인어수산")
                .owner("장인어")
                .description("인천소래포구 종합어시장 갑각류센터 인어수산")
                .level(3)
                .address("인천광역시 남동구 논현동 680-1 소래포구 종합어시장 1층 1호")
                .phone("010-1111-2222")
                .businessTimes(businessTimeForms)
                .build();

        //then
        Assertions.assertThrows(OpenEqualsCloseException.class, () -> {
            shopService.save(shopForm);
        });
    }

    @Test
    public void 점포_휴무일_등록_실패() throws Exception {
        //given
        List<LocalDate> holidays = new ArrayList<>();
        holidays.add(LocalDate.of(2021, 05, 15));
        holidays.add(LocalDate.of(2021, 05, 16));

        //when
        int length = holidayService.registerHolidays(100L, holidays);

        //then
        Assertions.assertEquals(length,0);
    }

    @Test
    public void 점포_목록_조회() throws Exception {
        //when
        List<ListForm> listForms = shopService.findAllList();

        //then
        Assertions.assertEquals(listForms.get(0).getName(), "해적수산");
        Assertions.assertEquals(listForms.get(1).getName(), "해적수산");
    }

    @Test
    public void 점포_상세_조회() throws Exception {
        //given
        IdForm idForm = new IdForm();
        idForm.setId(1L);
        String name = shopService.getInfo(idForm).getName();

        //then
        Assertions.assertEquals(name, "인어수산");
    }

    @Test
    public void 점포_삭제() throws Exception {
        //given
        Shop shop = shopService.findOne(1L);

        //when
        shopService.delete(shop);

        //then
        Assertions.assertNull(shopService.findOne(1L));
    }
}