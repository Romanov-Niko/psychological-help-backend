package com.kpi.repository;

import com.kpi.domain.FileDB;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FileDBRepository extends JpaRepository<FileDB, Integer> {}
