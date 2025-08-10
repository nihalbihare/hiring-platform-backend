package com.hiring_platform.Hiring.Platform.repository;

import com.hiring_platform.Hiring.Platform.dto.JobDTO;
import com.hiring_platform.Hiring.Platform.entity.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobRepository extends MongoRepository<Job , Long> { //Represents the
    // MongoDB document stored in the "jobs" collection. and Long is ID type in the document
    public List<Job> findByPostedBy(Long postedBy); // find the list of jobs and if we get any jobs return it
}
