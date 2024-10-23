package com.group9.eventgaze.repository;

import com.group9.eventgaze.entity.Events;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepo extends MongoRepository<Events, ObjectId> {

}
