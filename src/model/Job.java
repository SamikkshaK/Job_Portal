package model;

public class Job {
    private int jobId;
    private int employerId;
    private String title;
    private String description;
    private String qualifications;
    private String location;

    public Job(int jobId, int employerId, String title, String description, String qualifications, String location) {
        this.jobId = jobId;
        this.employerId = employerId;
        this.title = title;
        this.description = description;
        this.qualifications = qualifications;
        this.location = location;
    }

    public Job(int employerId, String title, String description, String qualifications, String location) {
        this(0, employerId, title, description, qualifications, location);
    }

    public int getJobId() { return jobId; }
    public int getEmployerId() { return employerId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getQualifications() { return qualifications; }
    public String getLocation() { return location; }
}
