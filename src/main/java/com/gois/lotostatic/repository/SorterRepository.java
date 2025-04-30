package com.gois.lotostatic.repository;

import com.gois.lotostatic.model.Sorter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SorterRepository extends JpaRepository<Sorter, Long> {

    boolean existsByConcourse(double concourse);

    @Query("SELECT MAX(s.concourse) FROM Sorter s")
    Optional<Integer> findMaxConcourse();
}
