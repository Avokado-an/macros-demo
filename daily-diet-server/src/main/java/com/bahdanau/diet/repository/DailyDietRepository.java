package com.bahdanau.diet.repository;

import com.bahdanau.diet.entity.DailyDiet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailyDietRepository extends MongoRepository<DailyDiet, String> {
    DailyDiet findAllByUserIdAndDocumentedDate(String email, LocalDate documentedDay);

    @org.springframework.data.mongodb.repository.Query("{ 'documentedDate' : { $gte: ?0, $lte: ?1 }, 'userId': { $eq: ?2 } }")
    List<DailyDiet> findAllInDateRangeForUser(LocalDate startDate, LocalDate endDate, String userId);
}
