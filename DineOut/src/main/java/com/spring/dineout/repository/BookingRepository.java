package com.spring.dineout.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.dineout.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
	Optional<Booking> findByBookingId(Long userId);
	
	 @Query("SELECT SUM(bookedSeats) FROM Booking  WHERE restoId = :restoId AND  bookedDate = :bookedDate AND status=1")
	    public Optional<Integer> findAvailability(@Param("restoId") long restoId,@Param("bookedDate") Date	 bookedDate);

	 @Query(value="SELECT * FROM booking  WHERE resto_id = :restoId AND status=0",nativeQuery=true)
	    public Optional<List<Booking>> getPendingApproval(@Param("restoId") long restoId);


}
