package thepirates.shopinfo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thepirates.shopinfo.Exception.OpenEqualsCloseException;
import thepirates.shopinfo.domain.BusinessTime;
import thepirates.shopinfo.domain.Form.*;
import thepirates.shopinfo.domain.Holiday;
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
    private final BusinessTimeService businessTimeService;
    private final String[] week = {"Sonday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};


    @Transactional
    public Long save(ShopForm shopForm) {

        //받아온 ShopForm으로 Shop 엔티티 생성
        Shop shop = Shop.builder()
                .name(shopForm.getName())
                .owner(shopForm.getOwner())
                .description(shopForm.getDescription())
                .level(shopForm.getLevel())
                .address(shopForm.getAddress())
                .phone(shopForm.getPhone())
                .build();
        shopRepository.save(shop);
        //ShopForm의 BusinessTimes 리스트로 BusinessTimes 생성
        shopForm.getBusinessTimes().forEach(businessTimeForm -> {
            //open 시간과 close 시간이 같을 경우 예외 처리
            if(businessTimeForm.getOpen().equals(businessTimeForm.getClose()))
                throw new OpenEqualsCloseException("open and close are the same");
            businessTimeService.save(shop.getId(), BusinessTime.builder()
                    .day(businessTimeForm.getDay())
                    .open(businessTimeForm.getOpen())
                    .close(businessTimeForm.getClose())
                    .build());
        });
        return shop.getId();
    }


    @Transactional
    public Long delete(Shop shop) {
        shopRepository.delete(shop);
        return shop.getId();
    }

    public Shop findOne(Long id) {
        return shopRepository.findById(id);
    }

    //현재 영업 상태를 반환해주는 함수 (해당 점포, 요일, 오늘을 기준으로 몇일 뒤인지)
    public String getStatus(Shop shop, String dayOfWeek, int dayCount) {

        List<BusinessTime> businessTimes = shop.getBusinessTimes();
        List<Holiday> holidays = shop.getHolidays();
        //영업 상태 초기값은 CLOSE로 설정
        String status = "CLOSE";

        //=== open, close 시간 확인 ===
        for(BusinessTime businessTime : businessTimes) {
            //해당 요일의 businessTime만 확인
            if(businessTime.getDay().equals(dayOfWeek)) {
                // hh:mm 형태를 hhmm형태로 변환
                String []open_arr = businessTime.getOpen().split(":");
                String []close_arr = businessTime.getClose().split(":");
                int open = Integer.parseInt(open_arr[0]+open_arr[1]);
                int close = Integer.parseInt(close_arr[0]+close_arr[1]);
                int now = Integer.parseInt(LocalTime.now().format(DateTimeFormatter.ofPattern("HHmm")));
                //현재 시간이 businessTime 안쪽일 경우
                if(open < now && now < close)
                    status = "OPEN";
            }
        }

        //=== 휴무인지 확인 ===
        for(Holiday holiday : holidays) {
            if(holiday.getDate().isEqual(LocalDate.now().plusDays(dayCount))) {
                status = "HOLIDAY";
            }
        }
        return status;
    }


    //모든 Shop 데이터를 ListForm의 List 형태로 변환 후 반환
    public List<ListForm> findAllList() {
        List<ListForm> listForms = new ArrayList<>();
        List<Shop> shops = shopRepository.findAllOrderByLevel();
        //캘린더 객체 생성해서 현재 요일 가져옴
        Calendar cal = Calendar.getInstance();
        String dayOfWeek = week[cal.get(Calendar.DAY_OF_WEEK)-1];

        for(Shop shop : shops) {
            ListForm listForm = ListForm.builder()
                    .name(shop.getName())
                    .description(shop.getDescription())
                    .businessStatus(getStatus(shop,dayOfWeek, 0))
                    .build();
            listForms.add(listForm);
        }
        return listForms;
    }


    //id 받아서 상세 정보 반환
    public InfoForm getInfo(IdForm idForm) {
        Shop shop = shopRepository.findById(idForm.getId());


        String dayOfWeek = "";
        Calendar cal = Calendar.getInstance();
        //현재 가게의 영업 시간 가져옴
        List<BusinessTime> businessTimes = shop.getBusinessTimes();

        //오늘과 가장 가까운 영업 요일 찾기 (오늘이 주말일 수도 있으니)
        int index = 0;
        int weekNum = cal.get(Calendar.DAY_OF_WEEK)-1;
        List<InfoBusinessDayForm> infoBusinessDayForms = new ArrayList<>();
        //현재 요일부터 일주일 순회
        int count = 0;
        for(int i=weekNum; i<week.length; i++) {
            dayOfWeek = week[i];

            for(BusinessTime businessTime : businessTimes) {
                if(businessTime.getDay().equals(dayOfWeek)) {
                    InfoBusinessDayForm infoBusinessDayForm = InfoBusinessDayForm.builder().day(businessTime.getDay())
                            .open(businessTime.getOpen())
                            .close(businessTime.getClose())
                            .status(getStatus(shop,dayOfWeek,count))
                            .build();
                    infoBusinessDayForms.add(infoBusinessDayForm);
                    count++;
                }
            }
            //businesstime이 3개 담기면 종료
            if(count == 3) {
                break;
            }
            //토요일까지 오면 그 다음 일요일은 index가 0이므로 0으로 순환
            if(i == Calendar.SATURDAY - 1)
                i = 0;
        }

        InfoForm infoForm = InfoForm.builder()
                .id(shop.getId())
                .name(shop.getName())
                .description(shop.getDescription())
                .address(shop.getAddress())
                .phone(shop.getPhone())
                .infoBusinessDayForms(infoBusinessDayForms)
                .build();

        return infoForm;
    }
}