
This project is a kind of a pacman game, and like any pacman game, there are fruits that need to be eaten by the pacmans.\
But what distinguishes this game is that the game background is a map that represents a real map which you can find on https://www.google.com/earth/.

(The game background map)
![alt text](https://github.com/maayanbuzaglo/OopNavigtion/blob/master/pictures/Ariel1.png)

The pacmans and the fruits in the game represent real coordinates on Earth which are the real location on the map in the background.\
Every pacman has `speed` and `radius` and every fruit has `weight`.\
You can decide where the pacmans and the fruits will be located by a mouse click on the map and also change their data. or, you can read a game from a csv file which already have all this.\
You also can make a game and save it by creating a csv file and open it later.
### The purpose of the game is to make the pacmans eat all the fruits on the map in the shortest time possible.
Another option is to make a kml file from your game, open it on https://www.google.com/earth/, see that the pacmans and the fruits are located as in the game map, play it and see how the fruits are eaten by the pacmans according to the order of eating in the game.

***Learn more about Global's coordinate system here: https://en.wikipedia.org/wiki/Geographic_coordinate_system.***

We will talk about the main classes.
## Map:
A class that represents a map that contains a map image file and all the necessary parameters of its alignment to a global coordinate system. The class enables conversion of global representation coordinates to the pixel in the image and vice versa. It also has a function which changes the coordinates according to the screen size.

## Pixel:
A class that represents a pixel in the game.

## Pacman:
A class that represents a "robot" with a location, orientation and ability to move (at a defined speed). It has the option to read a csv file of pacmans (it helps the same function on Game class).

## Fruit:
A class that represents a "target" in a known geographic location (without movement). It has the option to read a csv file of fruits (it helps the same function on Game class).

## Game:
A class that includes a collection of fruit and a collection of robots, the class has the ability to build from a csv file, and save its information to such a file.

## Line:
A class that represents a line between 2 points.

## Path:
A class that represents a track consisting of a collection of points.

## SaveData:
A class that represents the data of the pacman should eat next, the fruit should be eaten next, and the time it took.

## ShortestPathAlgo:
A class that gets Game and receives the optimal path (shortest) so that all fruits will be "eaten" as quickly as possible. This is the main algorithmic class and includes calculating "fruit tracks" for each of the pacmans. The purpose of the algorithm is to minimize the amount of time it takes for all the pacmans to eat all the fruits.\
The algorithm works as long as there are fruits on the map. It checks every time that it takes every pacman to eat any fruit on the map, takes the smallest time for each one so there is one fruit per pacman. Then, checks out the smallest time of all the Pacmans. This pacman is the pacman which starts to eat. Then, removes the eaten fruit, move the pacman's coordinate to the coordinate of the eaten fruit, and repeat the algorithm.

## MyFrame:
A class that represents the game frame.
A graphical class that allows robots and fruits to be displayed on the map, displaying the activity of algorithms, saving data, and performing a reconstruction of data from csv files or creating a game by selecting robots and fruits and positioning them on the map.




# Hope you'll enjoy :+1:
