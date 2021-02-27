import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckingAccountTest {

    CheckingAccount checkingAccount;

    @BeforeEach
    void setUp() {
        System.out.println("Test starts!");
        checkingAccount = new CheckingAccount();
        checkingAccount.setInfo(100, 1234567L, "Ozzy"); //initialize test account
    }

    @AfterEach
    void tearDown() {
        checkingAccount = null;
        System.out.println("Each test Ends here");
    }

    @Test
    void deposite() {
        assertEquals(200, checkingAccount.deposite(100));
    }

    @Test
    void withdraw() {
        assertEquals(80, checkingAccount.withdraw(20));
    }

    @Test
    void purchase() {
        assertEquals(-65, checkingAccount.purchase("Shoes", 130));
    }
}