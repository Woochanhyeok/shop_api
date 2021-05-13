package thepirates.shopinfo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thepirates.shopinfo.domain.BusinessTime;
import thepirates.shopinfo.domain.Holiday;
import thepirates.shopinfo.domain.ListForm;
import thepirates.shopinfo.domain.Shop;
import thepirates.shopinfo.repository.ShopRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
@Slf4j
public class ShopService {

    private final ShopRepository shopRepository;
    private final HolidayService holidayService;

    @Transactional
    public Long save(Shop shop) {
        shopRepository.save(shop);
        return shop.getId();
    }
    //calendar 클래스로 받아온 요일값은 int형이므로 문자열로 변환해주는 함수
    public String convertDayOfWeek (int num) {
        String dayOfWeek ="";
        switch(num) {
            case 1:
                dayOfWeek = "Sonday";
                break;
            case 2:
                dayOfWeek = "Monday";
                break;
            case 3:
                dayOfWeek = "TuesDay";
                break;
            case 4:
                dayOfWeek = "Wednesday";
                break;
            case 5:
                dayOfWeek = "Thursday";
                break;
            case 6:
                dayOfWeek = "Friday";
                break;
            case 7:
                dayOfWeek = "Saturday";
        }
        return dayOfWeek;
    }
    //모든 Shop 데이터를 ListForm의 List 형태로 변환 후 반환
    public List<ListForm> findAllList() {
        //캘린더 객체 생성해서 현재 요일 가져옴
        Calendar cal = Calendar.getInstance();
        String dayOfWeek = convertDayOfWeek(cal.get(Calendar.DAY_OF_WEEK));

        List<ListForm> listForms = new ArrayList<>();
        List<Shop> shops = shopRepository.findAllOrderByLevel();

        for(Shop shop : shops) {
            List<BusinessTime> businessTimes = shop.getBusinessTimes();
            List<Holiday> holidays = shop.getHolidays();
            String status = "CLOSE";

            //open, close 시간 확인
            log.info("Businesstime Size : " + businessTimes.size());
            for(BusinessTime businessTime : businessTimes) {
                //해당 요일의 businessTime만 확인
                log.info("businesstime get day : " + businessTime.getDay());
                log.info("dayofweek : " + dayOfWeek);
                if(businessTime.getDay().equals(dayOfWeek)) {
                    LocalTime open = businessTime.getOpen();
                    LocalTime close = businessTime.getClose();
                    LocalTime now = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));

                    log.info("Open : " + open);
                    log.info("Now : " + now);
                    log.info("Close : " + close);
                    log.info("IF : " + now.isBefore(open));
                    log.info("IF2 : " + now.isAfter(close));
                    //현재 시간이 businessTime 안쪽일 경우
                    if(open.isBefore(now) && now.isBefore(close))
                        status = "OPEN";
                }
            }
            //휴무인지 확인
            for(Holiday holiday : holidays) {
                if(holiday.getDate().isEqual(LocalDate.now())) {
                    status = "HOLIDAY";
                }
            }
            log.info("STATUS : " + status);


            ListForm listForm = ListForm.builder()
                    .name(shop.getName())
                    .description(shop.getDescription())
                    .businessStatus(status)
                    .build();
            listForms.add(listForm);
        }

        return listForms;
    }
}
