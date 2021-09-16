package com.herringbone.ibdanalyzer;

import com.herringbone.ibdanalyzer.entity.Customer;
import com.herringbone.ibdanalyzer.entity.IBDScreen;
import com.herringbone.ibdanalyzer.entity.IBDStock;
import com.herringbone.ibdanalyzer.model.IBDScreenDto;
import com.herringbone.ibdanalyzer.model.IBDStockDto;
import com.herringbone.ibdanalyzer.repository.CustomerRepository;
import com.herringbone.ibdanalyzer.repository.IBDScreenRepository;
import com.herringbone.ibdanalyzer.repository.IBDStockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class IbdAnalyzerApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private IBDStockRepository ibdStockRepository;

    @Autowired
    private IBDScreenRepository ibdScreenRepository;

    public static void main(String[] args) {
        SpringApplication.run(IbdAnalyzerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        ibdStockRepository.deleteAll();
        ibdScreenRepository.deleteAll();
        IBDStockDto stockDto = IBDStockDto.builder().symbol("TBIO").price(29.27).priceChg(-5.06)
                .pricePctChg(14.70).compRating(95).rsRating(92).volume(2336)
                .volumePctChg(69).screenDate(LocalDate.now().minusDays(1)).build();

        saveStock(stockDto);

        stockDto = IBDStockDto.builder().symbol("TBIO").price(29.50).priceChg(6.06)
                .pricePctChg(4.70).compRating(92).rsRating(91).volume(2335)
                .volumePctChg(65).screenDate(LocalDate.now()).build();

        saveStock(stockDto);
        IBDStockDto stockDto1 = IBDStockDto.builder().symbol("AAPL").price(131.50).priceChg(7.39)
                .pricePctChg(4.80).compRating(89).rsRating(88).volume(5003)
                .volumePctChg(64).screenDate(LocalDate.now()).build();
        saveStock(stockDto1);

        IBDScreenDto ibdScreenDto = IBDScreenDto.builder().screenDate(LocalDate.now())
                .screenType("IBD50").stocks(List.of(stockDto, stockDto1)).build();
        saveScreen(ibdScreenDto);

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }

        IBDStock stock = ibdStockRepository.findBySymbol(stockDto.getSymbol()).orElseThrow(() -> new RuntimeException("Unable to find"));
        log.info(stock.toString());

        IBDScreen ibdScreen = ibdScreenRepository.findByScreenTypeAndScreenDate("IBD50", LocalDate.now()).orElseThrow(() ->
                new RuntimeException("Unable to find Screen Type"));;
        log.info(ibdScreen.toString());
    }

    private void saveStock(IBDStockDto stockDto) {
        IBDStock ibdStock;
        ibdStock = ibdStockRepository.findBySymbol(stockDto.getSymbol())
                .orElse(IBDStock.builder().symbol(stockDto.getSymbol())
                        .build());

        mapStock(stockDto, ibdStock);

        ibdStockRepository.save(ibdStock);
    }

    private void saveScreen(IBDScreenDto screenDto) {
        screenDto.getStocks().stream().forEach( s -> {
            ibdStockRepository.findBySymbol(s.getSymbol());
        });
        List<IBDStock> ibdStocks = screenDto.getStocks().stream().map(s -> ibdStockRepository.findBySymbol(s.getSymbol()).orElse(null))
                .collect(Collectors.toList());
        IBDScreen ibdScreen = IBDScreen.builder()
                .screenId(UUID.randomUUID())
                .screenDate(LocalDate.now())
                .screenType(screenDto.getScreenType())
                .stocks(ibdStocks)
                .build();
        ibdScreenRepository.save(ibdScreen);
    }

    private void mapStock(IBDStockDto stockDto, IBDStock ibdStock) {
        ibdStock.getCompRatings().add(stockDto.getCompRating());
        ibdStock.getPrices().add(stockDto.getPrice());
        ibdStock.getPriceChgs().add(stockDto.getPriceChg());
        ibdStock.getPricePctChgs().add(stockDto.getPricePctChg());
        ibdStock.getRsRatings().add(stockDto.getRsRating());
        ibdStock.getVolumes().add(stockDto.getVolume());
        ibdStock.getVolumePctChgs().add(stockDto.getVolumePctChg());
    }
}
