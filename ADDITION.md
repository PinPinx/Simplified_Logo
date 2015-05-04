###Readme:
To open the turtle view: Right click the turtle window and choose the relevant item in the context menu. This view shows the images of all the turtles currently on the screen (active or not), labelled with its corresponding id. Clicking an image changes the image of the turtle and updates its representation in the view as well.

###Estimation:
I expect to modify 1 file (TurtleWindow) only, because it currently has all the resources I need to construct a view for all turtle images:
* a Map mapping from the turtle ID to its actual image representation;
* a context menu which pops up on clicking (where I can place the option to open the view);
* support for choosing an image file to change the image of any turtle.  
This should not take more than half an hour.

###Review:
The addition did take around half an hour, requires modification of TurtleWindow only, and I got it right on the first try.

###Analysis:
I could finish with such ease thanks more to my being intimately familiar with our project, rather than our good design or documentation (without the former one would probably have a much harder time). I remember feeling pretty good about our overall design for SLogo, but this exercise proved that going into smaller details, there is definitely room for improvement. I winced more than once as I went through the class TurtleWindow (which I did not participate heavily in the writing of), because there is still duplicated code, types that could be more general (e.g. Map instead of HashMap) and obscure methods that could have been refactored.
