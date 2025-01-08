package ie.atu;

public class MembershipEvent {
    private int memberID;
    private String status;
    private int membershipDuration;
    private String startDate;
    private String endDate;

    public MembershipEvent(int memberID, String status, int membershipDuration, String startDate, String endDate) {
        this.memberID = memberID;
        this.status = status;
        this.membershipDuration = membershipDuration;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMembershipDuration() {
        return membershipDuration;
    }

    public void setMembershipDuration(int membershipDuration) {
        this.membershipDuration = membershipDuration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "MembershipEvent{" +
                "memberID=" + memberID +
                ", status='" + status + '\'' +
                ", membershipDuration=" + membershipDuration +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
