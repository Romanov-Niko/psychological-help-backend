package com.kpi.repository;

import com.kpi.domain.AppointmentReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentReviewRepository extends JpaRepository<AppointmentReview, Integer> {}
