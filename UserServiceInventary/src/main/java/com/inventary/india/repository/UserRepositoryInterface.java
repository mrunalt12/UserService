package com.inventary.india.repository;

import com.inventary.india.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryInterface extends JpaRepository<UserDetails, Long> {
}
