package ie.atu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MembershipControllerTest {

    private MembershipController membershipController;
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setUp() {
        rabbitTemplate = mock(RabbitTemplate.class);
        membershipController = new MembershipController(rabbitTemplate);
    }

    @Test
    void testAddMembership_ValidInput() {
        Membership result = membershipController.updateMembership(1, 100);

        assertNotNull(result);
        assertEquals(1, result.getMemberID());
        assertEquals("Active", result.getMembershipStatus());
        assertEquals(6, result.getMembershipDuration());

        verify(rabbitTemplate, times(1)).convertAndSend(
                eq(RabbitMQConfig.EXCHANGE),
                eq(RabbitMQConfig.ROUTING_KEY),
                any(MembershipEvent.class)
        );
    }

    @Test
    void testAddMembership_InvalidAmount() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            membershipController.updateMembership(1, 10); // Invalid amount
        });

        // Assert that the correct exception is thrown
        assertEquals("Invalid payment amount: cannot create membership.", exception.getMessage());

        // Verify that convertAndSend was never called
        verify(rabbitTemplate, never()).convertAndSend(
                anyString(),
                anyString(),
                any(MembershipEvent.class)
        );
    }

    @Test
    void testGetMembershipByMemberID_ExistingMember() {
        membershipController.updateMembership(1, 100);

        Membership result = membershipController.getMembershipByMemberID(1);

        assertNotNull(result);
        assertEquals(1, result.getMemberID());
        assertEquals("Active", result.getMembershipStatus());
    }

    @Test
    void testGetMembershipByMemberID_NonexistentMember() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            membershipController.getMembershipByMemberID(99);
        });

        assertEquals("Membership not found for Member ID: 99", exception.getMessage());
    }
}
