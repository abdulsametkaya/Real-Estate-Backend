package com.realestate.repository;

import com.realestate.domain.PropertyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyDetailRepository extends JpaRepository<PropertyDetail, Long> {

        Boolean existsByTitle(String title);

}
