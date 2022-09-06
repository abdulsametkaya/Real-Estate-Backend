package com.realestate.repository;

import com.realestate.domain.Property;
import com.realestate.domain.enums.PropertyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {


    //Kullanıcı id'sine ait tüm property'leri getiren sorgu
    @Query("FROM Property p WHERE p.user.id  = :id")
     List<Property> findAllByUserId(Long id);

    //Kullanıcı id'sine ait  property'i getiren sorgu
    @Query("FROM Property p WHERE p.id = :id")
    Property findPropertyUserId(Long id);


    //User add likes
    @Modifying
    @Query("UPDATE Property u SET u.likes = ?1 WHERE u.id = ?2")
    public void updateLike(Integer likes,Long id);

    // Get likes
    @Query("SELECT p.likes FROM Property p  WHERE p.id = :id")
    Integer getLikes(Long id);

    // Search Property by user
    @Query("SELECT p FROM Property p WHERE p.title = ?1 OR p.type = ?2 OR p.bedrooms = ?3 OR p.bathrooms = ?4 OR p.country = ?5 " +
            "OR p.city = ?6 OR p.district = ?7 OR p.status = ?8 OR p.price >= ?9 AND p.price <= ?10")
    List<Property> searchRepo(String title,String type,Integer bedrooms,Integer bathrooms,String country,String city,String district,PropertyStatus status,Double priceMin,Double priceMax);




}

