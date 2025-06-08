package model;

public class Resume {
    private int resumeId;
    private String fileName;
    private String fileType;
    private byte[] data;
    private int userId;

    public Resume(int resumeId, String fileName, String fileType, byte[] data, int userId) {
        this.resumeId = resumeId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.userId = userId;
    }

    public int getResumeId() { return resumeId; }
    public String getFileName() { return fileName; }
    public String getFileType() { return fileType; }
    public byte[] getData() { return data; }
    public int getUserId() { return userId; }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
