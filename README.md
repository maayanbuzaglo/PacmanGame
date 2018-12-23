
This project is a kind of a pacman game, and like any pacman game, there are fruits that need to be eaten by the Pacmans.\
But what distinguishes this game is that the game background is a map that represents real map which you can find on https://www.google.com/earth/.

(The game background map)
![alt text](https://github.com/maayanbuzaglo/OopNavigtion/blob/master/pictures/Ariel1.png)

The pacmans and the fruits in the game represents real coordinates on Earth which are the real location on the map in the background.\
Every pacman has `speed` and `radius`.\
Every fruit has `weight`.\
You can decide where the pacmans and the fruits will be located by a mouse click on the map and also change their data. or, you can read a game from a csv file which already have all this.\
You also can make a game and save it by creating a scv file and open it later.\
### The purpose of the game is to make the pacmans eat all the fruits on the map in the shortest time possible.\
Another option is to make a kml file from your game, open it on https://www.google.com/earth/, see that the pacmans and the fruits located as in the game map, play it and see how the fruits are eaten by the pacmans according to the order of eating in the game.\
***Learn more about Global's coordinate system here: https://en.wikipedia.org/wiki/Geographic_coordinate_system.***

packages:

# Geom:
A package of geometry that includes points, lines, paths, circles, squares, etc..

# Coords:
A package that enables the conversion of coordinates from global coordinates to ordinals and the return.

# Gis:
Geographic - geometric information, divided into layers, including reference to time, place of text, color..

# Algorithms:
General algorithms such as: selection within rectangle, selection of distance, displacement, duplication, deletion, conversion of coordinates.

# File_format:
A package that allows you to save and restore geographic information, text format, and kml.
