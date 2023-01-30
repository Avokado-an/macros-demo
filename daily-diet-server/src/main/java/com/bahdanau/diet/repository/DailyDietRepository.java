package com.bahdanau.diet.repository;

import com.bahdanau.diet.entity.DailyDiet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyDietRepository extends MongoRepository<DailyDiet, String> {
    Optional<DailyDiet> findAllByUserEmailAndDocumentedDate(String email, LocalDate documentedDay);

    @org.springframework.data.mongodb.repository.Query("{ 'documentedDate' : { $gte: ?0, $lte: ?1 }, 'userEmail': { $eq: ?2 } }")
    List<DailyDiet> findAllInDateRangeForUser(LocalDate startDate, LocalDate endDate, String userEmail);
}
