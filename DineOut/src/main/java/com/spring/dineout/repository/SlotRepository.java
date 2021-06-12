package com.spring.dineout.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.dineout.model.Slots;

@Repository
public interface SlotRepository extends JpaRepository<Slots, Long> {
	Optional<Slots> findBySlotId(Long slotId);


		
	  @Query (nativeQuery = true , value="SELECT * FROM slots where slot_date =:slotDate  AND time_slot = :slotName AND resto_id=:restoId")
	  Optional<Slots> checkSlots(@Param("restoId") Long restoId ,@Param("slotDate") String slotDate,@Param("slotName") String slotName);

}
