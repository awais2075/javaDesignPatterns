# javaDesignPatterns



### Singleton
 This pattern involves a single class which is responsible to create an object while making sure that only single object gets created
 Database class is designed using this pattern as in whole project only one instance of database connection is involved to run queries


### Strategy
 In Strategy pattern, a class behavior or its algorithm can be changed at run time
 SignInListener (interface) is designed on this pattern (SignInPanel & SignUpPanel are implementing this interface)
 this interface has two methods to implement 1. onSignUp 2. onSignOut
 both methods have different implementation in both classes i.e. SignInPanel & SignUpPanel


### Facade
 Facade pattern hides the complexities of the system and provides an interface to the client using which the client can access the system
 All the click callbacks e.g. SignIn, SignOut etc. are based on this pattern, as it is hiding the complexity

### Template
 In Template pattern, an abstract class exposes defined way(s)/template(s) to execute its methods
 BasePanel is an abstract class containing methods 1. initUi  2. addListeners 3. addWidgetsToPanel
 this BasePanel class is being inherited by all the Panels made in this project and following the above 3 methods
 defined in BasePanel i.e. using as a TEMPLATE


### Proxy
 In proxy pattern, a class represents functionality of another class.
 When SignUp Button in SignInPanel is triggered it will open SignUp Frame
 When SignIn Button in SignUpPanel is triggered it will open SignIn Frame
in both cases functionality of each other classes are consumed

 ### Decorator
 Decorator pattern allows a user to add new functionality to an existing object without altering its structure
 Inheriting BasePanel in all panels and consume it according to requirement comes under this pattern

