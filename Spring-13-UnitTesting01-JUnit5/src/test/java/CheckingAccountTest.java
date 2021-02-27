import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

//@TestMethodOrder(MethodOrderer.MethodName.class) // runs order of @Test method name
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //runs @Test s  in order with @Order(1), @Order(2)
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

    @Test
    @Order(4)
    void withdraw_branch(){
        assertThrows(IllegalArgumentException.class, ()->{
            checkingAccount.withdraw_branch(600, false);
        });//arg: exception type, executable (ex. lambda)

    }
}