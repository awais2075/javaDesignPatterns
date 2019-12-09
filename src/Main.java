import ui.frame.SignInFrame;

public class Main {


    public static void main(String[] args) {



        /**
         * Singleton
         * Strategy
         * Facade (all the interface callbacks are on this pattern i.e. to hide complexity)
         * Template
         *Proxy (SignOut Button (SigInFrame) & SignInFrame Button (SignOut Frame) are using  functionality of another classes)
        *Decorator (BaseUi inheriting in every Ui class is on this pattern)
         *  */
        new SignInFrame();
    }

}
