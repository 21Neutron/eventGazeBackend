package com.group9.eventgaze.repository;

import com.group9.eventgaze.entity.Publishers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishersRepo extends JpaRepository<Publishers,Long> {
}
