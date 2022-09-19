import menus.mainMenu.MainMenu;


public class Main {
    public static void main(String[] args) {
        /**
         * About the Main.java class
         * If the Main.java menu only calls MainMenu() then just delete this file and start directly on MainMenu();
         * To fix this, we can utilize the Main class to verify that any file or folder required to run the app is there
         * before running the code.
         *
         * Note:
         * If we did something similar during our code quality lectures,
         * but the Project.java was our real entry point over there.
         */
        new MainMenu();
    }
}
