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
    public Optional<List<Restaurant>> showNewRestaurantsWithPaging(@Param("city") String city, @Param("lower") int lower,@Param("upper") int upper);

	@Query(value="SELECT rs.* from dineout.restaurant rs JOIN (\r\n"
			+ "select b.resto_id,count(*) ,ROW_NUMBER() OVER (ORDER BY count(*) DESC) AS intRow from dineout.booking b join dineout.restaurant r on b.resto_id=r.resto_id where lower(city)=lower(:city) group by b.resto_id ) as tmp\r\n"
			+ "on rs.resto_id = tmp.resto_id \r\n"
			+ "AND tmp.intRow Between :lower and :upper Order by tmp.intRow",nativeQuery=true)
	Optional<List<Restaurant>> showPopularRestaurantsWithPaging(String city, int lower, int upper);

}
