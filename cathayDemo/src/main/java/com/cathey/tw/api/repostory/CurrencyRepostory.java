package com.cathey.tw.api.repostory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cathey.tw.api.model.Currency;

public interface CurrencyRepostory extends JpaRepository<Currency, Long> {
	Optional<Currency> findByCode(String code);
}
