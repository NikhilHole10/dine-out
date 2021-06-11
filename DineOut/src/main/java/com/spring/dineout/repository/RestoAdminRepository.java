package com.spring.dineout.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.dineout.model.Restaurant;
import com.spring.dineout.model.User;


@Repository
public interface RestoAdminRepository extends JpaRepository<Restaurant,Long> {
	Optional<Restaurant> findByUserId(Long UserId);

	
	@Query(value="SELECT resto_id,address,city,contact_no,meal_type,owner_name,resto_name,resto_status,user_id FROM \r\n"
			+ "( SELECT *,ROW_NUMBER() OVER (ORDER BY resto_id ASC) AS intRow FROM dineout.restaurant  WHERE lower(city) = lower(:city) AND resto_status=1) as TMP\r\n"
			+ "WHERE TMP.intRow between :lower and :upper",nativeQuery=true)
    public Optional<List<Restaurant>> showRestaurantsWithPaging(@Param("city") String city, @Param("lower") int lower,@Param("upper") int upper);

}
