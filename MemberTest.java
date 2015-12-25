import static org.junit.Assert.*;

public class MemberTest {

    @org.junit.Test
    public void testFlight() throws Exception {
        Member m = new Member(1, "test", "test address", 20);
        try {
            m.flight(-100);
            fail("no exception was thrown");
        }
        catch (IllegalArgumentException e){
            assertEquals("argument must be non-negative val.", e.getMessage());
        }
    }
}