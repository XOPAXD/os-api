package com.jhone.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jhone.os.domain.OS;


@Repository
public interface OsRepository extends JpaRepository<OS, Integer>{

}
