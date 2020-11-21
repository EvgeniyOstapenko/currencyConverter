package com.example.currencyConverter.repos;

import com.example.currencyConverter.domain.Request;
import com.example.currencyConverter.util.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepo extends JpaRepository<Request, Long> {

    @Query(value = "SELECT DISTINCT request.user_id FROM request WHERE request.money >= ?", nativeQuery = true)
    List<Long> findUsersWithSpecialAmountOfMoneyInRequests(Integer param);

    @Query(value = "SELECT target_currency FROM request GROUP BY target_currency ORDER BY COUNT(target_currency)  DESC", nativeQuery = true)
    List<Currency> findPopularCurrencyInRequests();
}
