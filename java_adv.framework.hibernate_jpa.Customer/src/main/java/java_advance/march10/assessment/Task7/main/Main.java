package java_advance.march10.assessment.Task7.main;

public class Main {

    public static void main(String[] args) {

        JavaApp app = new JavaApp();

        app.addCustomer();

        app.searchCustomer(301);

        app.updateWallet(301,2000);

        app.deleteCustomer(301);
    }
}