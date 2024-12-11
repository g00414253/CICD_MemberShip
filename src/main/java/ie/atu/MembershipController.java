package ie.atu;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/membership")
public class MembershipController {
    private List<Membership> membershipList = new ArrayList<>();

    @PostMapping("/addMembership/{memberID}/{amount}")
    public Membership updateMembership(@PathVariable int memberID, @PathVariable double amount) {
        // Map payment amount to membership duration
        int months = mapAmountToMonths(amount);

        if (months > 0) {
            Membership membership = new Membership();
            membership.setMembershipID(membershipList.size() + 1);
            membership.setMemberID(memberID);
            membership.setMembershipStatus("Active");
            membership.setMembershipDuration(months);
            membership.setStartDate(LocalDate.now().toString());
            membership.setEndDate(LocalDate.now().plusMonths(months).toString());

            membershipList.add(membership);
            return membership;
        } else {
            throw new RuntimeException("Invalid payment amount: cannot create membership.");
        }
    }

    @GetMapping("/getMembership/{memberID}")
    public Membership getMembershipByMemberID(@PathVariable int memberID) {
        return membershipList.stream()
                .filter(membership -> membership.getMemberID() == memberID)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Membership not found for Member ID: " + memberID));
    }

    private int mapAmountToMonths(double amount) {
        if (amount == 50) return 3;
        if (amount == 100) return 6;
        if (amount == 200) return 12;

        if (amount >= 20) {
            return (int) (amount / 20);
        }
        return 0;
    }
}