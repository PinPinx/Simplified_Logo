SLogo Design
=====

Introduction
===

Our program is an IDE that supports users to write SLogo programs. The program needs to provide user interface for
users to enter commands, parse the command and modify a display based on the command. The top level architecture of our program
follows the Model-View-Controller architectural pattern. The pattern allows us to divide the program into three parts. The 
model represents the data structures for the different types of display objects (like turtle objects, and command objects). The
view is composed of the different graphical windows that will be used as user interfaces. The controller will co-ordinate 
command updates from the view to the model. These three parts of the program will provide APIs to interface with each other 
with minimal reveal of internal variables. Using this well defined interface, our goal is to have flexibility when extending 
one or more of the three parts without affecting the other parts. 

Overview
===


####Backend - intro
At a high level, we have a Model and View class, each with various components. The Model class consists of a Turtle, Variables, CommandHistory, (XMLWriter), UserDefinedCommand, and Parser classes. The Turtle’s State component contains the turtle information that is relevant to the front end for drawing, and it has protected methods for altering its state based on the outcome of commands. Variables contains a collection of the active Variable instances in the user programming environment, and it has protected methods to add or subtract Variable instances. 

Before continuing the description of the main model classes, it’s necessary to understand our architecture for receiving, parsing, and executing commands. First, the user inputs the command to the front end and sends it, and the front end calls Model.parseCommand(Str input). This method makes a call to the Parser object that lives in Model, giving the Parser both the string input as well as a copy of the current variables in Variables. 

####Backend - parser design
The Parser is responsible for returning a correct Command object (or throwing an exception if the input is malformatted). We will use the command pattern to accomplish this, and to encapsulate the request for a command from the execution of the command itself. The Command interface specifies one method, execute(). A class would be created for each command, and each class would implement Command (ex: Move implements Command). The Parser would be responsible for creating a tree of Executable commands. While primitive commands like move and turn would be leaf nodes in this tree, control flow commands like for loops, if statements, and while loops would be parent nodes that have a List of Executable children. The Parser would create this tree, return the root of the tree to Model, and Model would perform a preorder traversal of the tree, calling the execute method on each node. Model would act as the invoker, and parser would be the client. At a later time, perhaps we could eliminate the large number of Command implementation classes and replace them with lambda expressions.

Consequences of this design: If a command is responsible for writing its own execute method, then the receiver of the command would need to be either the Turtle’s State or Variables, depending on the command type. Either way, a command needs read access of Variables in case the command contains a variable. One solution to this would be to have both the turtle’s State and the Variables object to be passed into the execute method as parameters (ex: execute(state s, variables v)). This would be bad since commands like move would not use the reference to variables, and variable declarations would not use the reference to the turtle’s state. Another option is give the Parser a reference to State and Variables, and then the Parser can create Command objects and pass an individual command the reference it needs in the object’s constructor.

####Backend - Variables
Variables in SLogo could be (but are not limited to) ints, doubles, or Strings. So a there’s a Variable abstract object, Variable, and it supports a number of operations. A Variable has a property on which we may perform bindings to the front end (see section: VariablesWindow).

The Variables object then must have a Collection of Variable’s, and protected methods in order to add or subtract variables from the collection. When a variable is declared by a command (ex: “:a = 10”). the collection of variables must be searched to see if “a” is already a variable. If not, then add a new variable. If so, then edit the existing value of “a.” Since the Parser needs access to the variables’ value’s (for instance, to parse a command like [MoveForward by “a”] where “a” is a variable), the Variables class must support some sort of read/get method. This method returns a non-destructible copy/map/something that can be given to the Parser can correctly identify whether “a” is a variable, and if so, if it’s the right type (ex: if “a” were a String, the Parser should throw an error at [MoveForward by “a”]). 

####Backend - CommandHistory
The CommandHistory stores a collection of Command tree roots. Each Command tree must have a String representation. As the project scales, it may be necessary to go back or undo commands. Thus, the CommandHistory serves an important role in storing these commands, should the command interface ever add an “undo” method (or other common command pattern features). The CommandHistory, no matter its Collection’s implementation, should support a number of methods that allow references to past commands (ex: Stack’s pop method).

####View
The View class consists of a TurtleWindow, VariablesWindow, CommandHistoryWindow, and UserDefinedCommandsWindow. Within the View, there is a method to display an error message, a menu bar, and a command text box as well. The TurtleWindow is where the turtle and drawing happen. Every View component implements the same interface, which consists of a update and display method. The display creates JavaFX objects necessary to display the component, and the update method is necessary for the following observer relationships. There are a number of observer relationships with the View components. The Observable-Observer relationships: Turtle-TurtleWindow, Variables-VariablesWindow, CommandHistory-CommandHistoryWindow, and UserDefinedCommands-UserDefinedCommandsWindow. 

####Frontend - TurtleWindow
First, notice that the TurtleWindow observes the Turtle, so any update to the Turtle’s State will be drawn in a public update method of TurtleWindow. For instance, when the turtle’s State changes its Point2D, the line between the old point and the new point can be drawn. When the turtle’s State changes its Angle, then the front end turtle image can rotate. DISADVANTAGES OF THIS: Note that the update method has to handle two (and more) types of changes: one to the turtle’s coordinates, one to the turtle’s angle (and possibly more). So does the update method then have to figure out what changed? ALTERNATIVE APPROACH: instead of writing our own update method for the TurtleWindow, we could try doing property bindings. For instance, the Angle object could have a DoubleProperty myAngle. We could bind that property to the frontend turtle’s ImageView rotate property. For the coordinates, we could create a Point2D alternative implementation that has two DoubleProperty’s myX, myY. Then we could bind those properties to the the frontend turtle’s ImageView x and y properties. These binding are observer relationships, but we don’t have to write an update method that has to figure out which thing changed.

####Frontend - VariablesWindow
The VariablesWindow displays all the active variables in the backend Variables class (which is itself, a collection of Variable objects). This also follows the observer pattern. Whenever the list of Variable objects changes within Variables, the VariablesWindow’s update method is called. This seems straightforward. There’s also an option to edit the frontend’s VariablesWindow and edit a variable’s value. To do this, we would have a bidirectional binding on the value of the Variable (in the backend) with the displayed variable in the frontend.
 
####Frontend - CommandHistoryWindow
The CommandHistoryWindow observes CommandHistory. Whenever a new command is issued to the backend, its string is added visually here. When a visual command is double clicked, CommandHistoryWindow calls Model.parseCommand on the command’s string. Alternative: when a command is double clicked, the visual command is connected to the Command tree in the backend, and executes the tree (thus skipping another parse call).


User Interface
===

The layout of the user interface will be as follows:


Text commands are entered via a command box at the bottom of the screen. If the command is valid, it will be executed by the turtle(s) in the turtle window. If it is not, a pop-up message box will display the error and nothing in the current workspace will change.
The right pane displays the current workspace. User-defined variables and commands are displayed and made clickable to edit – a valid edit will be reflected on the screen, otherwise a pop-up error message box will appear. A drop-down menu for command history below displays the list of executed commands in reverse chronological order.
Our program window includes a menu bar at the top, with clickable tabs:
* *Help* loads up a HTML help page.
* *Preferences* allows the user to select display preferences, which will potentially include:
* Language
* Background color
* Turtle image
* Line color and thickness
* There may also be tab options to allow the user to save the current workspace, or reload a saved workspace.

Design Details
===

>-Executable:
This is our interface for any command. It has a single method, execute(). There are six different types of commands, as listed on the 308 webpage. Each one has a abstract class (eg Control, TurtleCommand). A turtle command is simple (like mv 10), and it changes the state of the turtle. A control command (like for loop, if statement, etc.), instead takes other commands as arguments. 

>-Parser:
The parser takes the user-inputted string command and creates a tree of Executable objects. 
A turtle command (eg mv 10) is a leaf node. Once it executes, there’s nothing more to it. A control object, like a for loop, can act as a parent node. All Executables (commands) that are contained within the forloop/ifstatement/ifelse/etc. with be added as children. For example, a for loop containing seven mv 10 turtle-commands would be a loop parent node with seven turtle-command children. When the loop node is reached, its execute method is to the effect of: for a certain number of iterations, for child in children, child.execute().

>-Model:
The model receives this tree of Executable’s, with a reference to the root. To execute it, it simply runs root.execute(). This will run a preorder traversal of the tree and will execute each command object in order.


>-View:
The View is responsible for setting up and displaying the GUI for our SLogo program. 
There is a single public method, update(), which updates the current display window based on an ViewUpdate object generated by the Model (or Controller?).
The View has dropdown menus for display preferences (background color/image, line color, line thickness, turtle image), which are updated internally based on user input.

####API Between Model and View

public class Model(){

	private Model();
	public getInstance();
	public parse(String str); throws many exceptions, listed below
	public changeLang(String str); throws lang not found exception
}

####API within model and view

Interface Command(){
public void execute(Turtle turt, Vars var){} throws turt or var not found exception
}

Public class TurtleView(){
	protected void setColor(); throws invalid color exception
	protected void setImage(); throws image file not found exception
	protected void setBackground();
}

public class messageBox(){
	public void display(str);
}

Many classes with observable pattern: Have private notify() and public update() methods; these relationships will be defined more clearly when coding begins.

####API Example Code
User enters “forward 50”. 
View calls Model.parse(“forward 50”).
Model calls Parser.parse(“forward 50”) ← potentially other parameters to this method, like giving the Parser read access
Parser checks for errors in the command. Parser creates Command tree. In this case, it’s very simple. Just a root with one child (MoveForward child). Parser returns root to the Model.
Model takes root returned by Parser, calls root.execute(). This will execute the entire tree (even though we only have a little tree).
Inside the MoveForward Command, execute is called by root’s execute method. MoveForward Command has a reference to the Turtle, and can read its state. It reads Turtle’s angle, determines the appropriate new coordinate displacement for the Turtle, and then makes a call to the Turtle internal API to incorporate this displacement. Turtle state is therefore changed.

####Exceptions that will be thrown during run time will be

>- Invalid syntax exception
>- Invalid value for variable exception
>- Language file not found exception
>- User defined command not found exception

Design Considerations:
===

The easiest way to break the program down into packages was to follow the Model-View-Controller pattern. But, After 
discussing the pattern in detail, we figured out that the controller part only adds to the complexity of our program 
because it creates a need for multi way communication with three classes, and for that cost we could saw that it wasn’t 
going to help us clean up the code in the other classes to a great detail. We finally decided to modify the pattern by 
taking out the controller part. We decided to create two singleton classes that will act as the highest level layers 
for the model and the view, and the previous jobs of the controller were now going to be divided between these two top 
level classes. And because they are singleton classes we could access their API from anywhere in our code, giving us 
the maximum flexibility with the rest of our design. 

For the relationship between the different view classes and the model classes they correspond to, we wanted to use the 
Observer design pattern with our view classes registered as observers of the model classes which are the observables. 
Registering the view classes as observers of the model classes requires passing instances of the view classes into the 
model classes. We considered the different options of how to pass the view class instances to their respective 
observables. One option was to add get methods in our top level view class that returns the respective observer 
classes. Another option was to set up these observer-observable relationship in class constructors. After discussing 
possible pros and cons for each approach, we think setting up the relationships in the constructors is a better 
approach to follow. There is no additional hidden dependency compared to a getter and setter, and the relationship is 
closed; it can’t be changed by other programmers calling the get methods from other classes.
Even after deciding to follow the observer pattern for the view and model class relationships, we noted that we were 
also required to support editing of the different model classes(variables and user defined commands)  through the view 
classes. To support this feature, we needed to take observer pattern one more step further and consider binding 	
relationships. We pondered on how binding analogous view and model classes will help us update the view classes only through 
modifications to model classes and how it will help us filter invalid edits and keep the view classes identical to their 
analogous model classes. 

We want all of our classes to be as opaque as possible, and make any dependencies clear. However, in order for our 
parser to recognize references to variables, it must have access to a list of variables. This requires some read-only 
access to vars from the parser. This seemed like the only design that kept the structure of our command objects simple.
Therefore, the parser will have read-access to the variables. This also provides an interface for other classes to 
access the vars, but this cannot be avoided.

Team Responsiblities
===

Lien and Negatu: Front-end/view

Keighn and Danny: Back-end/model
