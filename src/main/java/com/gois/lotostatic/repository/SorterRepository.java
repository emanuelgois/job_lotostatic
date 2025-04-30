package com.gois.lotostatic.repository;

import com.gois.lotostatic.model.Sorter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SorterRepository extends JpaRepository<Sorter, Long> {

    boolean existsByConcourse(double concourse);
}
