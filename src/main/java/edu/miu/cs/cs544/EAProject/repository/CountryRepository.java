package edu.miu.cs.cs544.EAProject.repository;

import edu.miu.cs.cs544.EAProject.domain.CountryRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryRegion, String> {
}
