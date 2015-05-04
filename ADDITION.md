Slogo Addition
Estimation
This feature will take less than 45 minutes to complete. I’ll have to update a properties file for the new commands, two new command classes, maybe create a new file to process TurtleUpdates, and the turtle class to give it said processor.
Review
It took about an hour to do the feature. I got it more or less right (apart from some GUI issues with the front end code, the turtle is not centered in the origin for some reason). I modified 7 files: Turtle (to add a fence method to the interface), TurtleMultiple (to comply with interface), TurtleSingle (to comply with interface and add logic for the commands), two new command files Window and Fence, modified the command properties file, and added some get dimension methods for the front end.
Analysis
The good: the communication to the front end from the back end is very clearly due to the strict use of the observer pattern. The front end code could be improved (it was a mess trying to find the dimensions of the screen), and the commands require useless empty constructors. If I was unfamiliar with the code, I would have trouble getting the dimensions, but the way the backend informs the front end of changes is very clear. Poorly documented, though.
