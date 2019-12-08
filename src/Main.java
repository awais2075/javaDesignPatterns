import _interface.SignInListener;
import ui.Dashboard;
import ui.SignIn;
import ui.SignInUi;
import util.Utils;

import javax.swing.*;
import java.awt.*;

public class Main {


    public static void main(String[] args) {


        /*SignIn logIn = new SignIn();
        User user = User.getInstance();
        user.setEmail("awais2075@gmail.com");
        user.setPassword("abcd1234");
        if (logIn.isUserExists("awais2075@gmail.com", "adbcd1234")) {
            Utils.printInfo(user.getFirstName() + "\t" + user.getLastName());
        } else {
            Utils.printError("User Doesn't Exist");
        }*/

        /**
         * Singleton
         * Strategy
         * Facade (all the interface callbacks are on this pattern i.e. to hide complexity)
         * Template
         *Proxy (SignOut Button (SigInFrame) & SignIn Button (SignOut Frame) are using  functionality of another classes)
        *Decorator (BaseUi inheriting in every Ui class is on this pattern)
         *  */
        new SignIn();
    }

}
