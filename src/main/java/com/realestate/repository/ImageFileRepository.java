package com.realestate.repository;

import com.realestate.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageFileRepository extends JpaRepository<Image, String> {
}
