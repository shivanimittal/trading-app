package com.trading.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trading.security.model.PositionBook;
import com.trading.security.model.PositionBookKey;

@Repository
public interface PositionBookRepository extends JpaRepository<PositionBook, PositionBookKey> {
	
}
