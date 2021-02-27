import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

//@TestMethodOrder(MethodOrderer.MethodName.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    void deposite() {
        System.out.println("Deposite test");
        assertEquals(200, checkingAccount.deposite(100));
    }

    @Test
    @Order(2)
    void withdraw() {
        System.out.println("Withdraw test");
        assertEquals(80, checkingAccount.withdraw(20));
    }

    @Test
    @Order(3)
    void purchase() {
        System.out.println("Purchase test");
        assertEquals(-65, checkingAccount.purchase("Shoes", 130));
    }
}