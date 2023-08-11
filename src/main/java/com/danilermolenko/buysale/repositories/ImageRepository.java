package com.danilermolenko.buysale.repositories;

import com.danilermolenko.buysale.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
