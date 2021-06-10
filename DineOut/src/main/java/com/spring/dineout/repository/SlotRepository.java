package com.spring.dineout.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.dineout.model.Slots;

@Repository
public interface SlotRepository extends JpaRepository<Slots, Long> {
	Optional<Slots> findBySlotId(Long slotId);
}
