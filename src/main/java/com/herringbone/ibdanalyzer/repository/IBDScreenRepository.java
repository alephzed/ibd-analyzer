package com.herringbone.ibdanalyzer.repository;

import com.herringbone.ibdanalyzer.entity.IBDScreen;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface IBDScreenRepository extends MongoRepository<IBDScreen, String> {

    Optional<IBDScreen> findByScreenTypeAndScreenDate(String screenType, LocalDate screenDate);
}
