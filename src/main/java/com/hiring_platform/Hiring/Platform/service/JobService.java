package com.hiring_platform.Hiring.Platform.service;


import com.hiring_platform.Hiring.Platform.dto.ApplicantsDTO;
import com.hiring_platform.Hiring.Platform.dto.Application;
import com.hiring_platform.Hiring.Platform.dto.JobDTO;
import com.hiring_platform.Hiring.Platform.exception.JobPortalException;

import java.util.List;

public interface JobService {
   public JobDTO postJob(JobDTO jobDTO) throws JobPortalException;

   public List<JobDTO> getAllJobs();

   public JobDTO getJob(Long id) throws JobPortalException;

    public void applyJob(Long id, ApplicantsDTO applicantsDTO) throws  JobPortalException;

   public List<JobDTO> getJobsPostedBy(Long id);

   public void changeAppStatus(Application application) throws JobPortalException;


}
