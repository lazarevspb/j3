package lesson7;


public class ClassUnderTest {
    @AfterSuite(priority = 1)
    public static void methodAfterSuiteSuite() {
        System.out.println("methodAfterSuiteSuite summoned");
    }

    @BeforeSuite(priority = 0)
    public static void methodBeforeSuite() {
        System.out.println("methodBeforeSuite summoned");
    }

    @Test(priority = 1)
   static void justATestableMethod() {
        System.out.println("justATestableMethod summoned");
    }

    @Test(priority = 2)
    static  void justATestableMethodTwo() {
        System.out.println("justATestableMethodTwo summoned");
    }
}
