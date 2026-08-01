package com.inventary.india.repository;

import com.inventary.india.model.AdminDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminServiceRepository extends JpaRepository<AdminDetail, Long> {
}
