package edu.miu.cs.cs544.EAProject.service;

import edu.miu.cs.cs544.EAProject.domain.StateProvince;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateService extends JpaRepository<StateProvince, Integer> {
}
