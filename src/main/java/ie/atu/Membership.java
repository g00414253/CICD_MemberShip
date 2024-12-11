package ie.atu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membership {
    private int membershipID;
    private int memberID;
    private String membershipStatus;
    private int membershipDuration;
    private String startDate;
    private String endDate;
}
