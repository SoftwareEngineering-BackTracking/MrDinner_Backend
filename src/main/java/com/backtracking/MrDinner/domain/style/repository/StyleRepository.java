package com.backtracking.MrDinner.domain.style.repository;

import com.backtracking.MrDinner.global.enumpackage.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<StyleList, Style> {
}
