package com.hiring_platform.Hiring.Platform.service;


import com.hiring_platform.Hiring.Platform.dto.*;
import com.hiring_platform.Hiring.Platform.entity.Applicants;
import com.hiring_platform.Hiring.Platform.entity.Job;
import com.hiring_platform.Hiring.Platform.entity.Notification;
import com.hiring_platform.Hiring.Platform.entity.Utilities;
import com.hiring_platform.Hiring.Platform.exception.JobPortalException;
import com.hiring_platform.Hiring.Platform.repository.JobRepository;
import com.hiring_platform.Hiring.Platform.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpli implements JobService {

    @Autowired
    public JobRepository jobRepository;
    @Autowired
    public NotificationService notificationService;


    @Override
    public JobDTO postJob(JobDTO jobDTO) throws JobPortalException {
        if(jobDTO.getId() == 0){
            jobDTO.setId(Utilities.getNextSequence("jobs"));
            jobDTO.setPostTime(LocalDateTime.now());
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setAction("Job Posted Successfully");
            notificationDTO.setMessage("Job Posted Successfully for "+jobDTO.getJobTitle()
                    +" at "+jobDTO.getCompany());
            notificationDTO.setUserId(jobDTO.getPostedBy());
            notificationDTO.setRoute("posted-jobs/"+jobDTO.getId());
            try {
                notificationService.sendNotifcation(notificationDTO);
            } catch (JobPortalException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            Job job = jobRepository.findById(jobDTO.getId()).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));
        if(job.getJobStatus().equals(JobStatus.DRAFT) || jobDTO.getJobStatus().equals(JobStatus.CLOSED))
            jobDTO .setPostTime(LocalDateTime.now());
        }

        //repo saving toEntity then return it toDTO
        return jobRepository.save(jobDTO.toEntity()).toDTO(); // returning and save to the
    }

    @Override
    public List<JobDTO> getAllJobs() {
        return jobRepository.findAll().stream().map((x)->x.toDTO()).toList();
    }


//. jobRepository.findAll()
//Calls the MongoDB repository to fetch all Job entities from the "jobs" collection.
//Returns a List<Job>.
//2. .stream()
//Converts the List<Job> into a stream (a Java 8+ feature for processing collections).
//3. .map((x) -> x.toDTO())
//For each Job object x in the stream, it:
//Calls x.toDTO() to convert the Job entity into a JobDTO object.
@Override
public JobDTO getJob(Long id) throws JobPortalException {
    return jobRepository.findById(id).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"))
            .toDTO(); // toDTO() converts the Job entity into a
    // JobDTO, which is a clean object to send in the API response.
}

    @Override
    public void applyJob(Long id, ApplicantsDTO applicantsDTO) throws JobPortalException {
      Job job = jobRepository.findById(id).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));
      List<Applicants> applicants = job.getApplicants();
      if(applicants == null) applicants = new ArrayList<>();
      if(applicants.stream().filter
              ((x)->x.getApplicantId() == applicantsDTO.getApplicantId()).toList().size()>0)
          throw new JobPortalException("JOB_ALREADY_APPLIED"); // we have already a person who applied for that job
        applicantsDTO.setApplicationStatus(ApplicationStatus.APPLIED);  // set the status in  app.dto
        applicants.add(applicantsDTO.toEntity()); // now add the app.dto into applicants list
        job.setApplicants(applicants);// now setting the application in job
        jobRepository.save(job); // and now add job into repository




    }

    @Override
    public List<JobDTO> getJobsPostedBy(Long id) {
        return jobRepository.findByPostedBy(id).stream().map((x)->x.toDTO()).toList();
        // return the postedjobs find by id make it stream convert the data into Dto then into list for
    }

    @Override
    public void changeAppStatus(Application application) throws JobPortalException {
        Job job = jobRepository.findById(application.getId()).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));
        List<Applicants> applicants = job.getApplicants().stream().map((x)->{
            if(application.getApplicantId() == x.getApplicantId()){
                x.setApplicationStatus(application.getApplicationStatus());
                if(application.getApplicationStatus().equals(ApplicationStatus.INTERVIEWING))
                    x.setInterviewTime(application.getInterviewTime());
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setAction("Interview Scheduled");
                notificationDTO.setMessage("Interview Scheduled for Job Id :"+application.getId());
                notificationDTO.setUserId(application.getApplicantId());
                notificationDTO.setRoute("/job-history");
                try {
                    notificationService.sendNotifcation(notificationDTO);
                } catch (JobPortalException e) {
                    throw new RuntimeException(e);
                }
            }
            return x;
        }).toList();
        job.setApplicants(applicants);
        jobRepository.save(job);

    }



}

