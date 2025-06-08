package model;

import java.time.LocalDateTime;

public class Application {
    private int applicationId;
    private int jobId;
    private int jobSeekerId;
    private String status;
    private LocalDateTime appliedDate;

    public Application(int applicationId, int jobId, int jobSeekerId, String status, LocalDateTime appliedDate) {
        this.applicationId = applicationId;
        this.jobId = jobId;
        this.jobSeekerId = jobSeekerId;
        this.status = status;
        this.appliedDate = appliedDate;
    }

    public Application(int jobId, int jobSeekerId) {
        this(0, jobId, jobSeekerId, "Applied", LocalDateTime.now());
    }

    public int getApplicationId() { return applicationId; }
    public int getJobId() { return jobId; }
    public int getJobSeekerId() { return jobSeekerId; }
    public String getStatus() { return status; }
    public LocalDateTime getAppliedDate() { return appliedDate; }
}
