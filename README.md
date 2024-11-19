# User vs Computer play Reversi

## Members:  Elpida Stasinou, Dimitris Tzovanis


Computer plays Reversi with minimax algorithm (a-b) for acceleration, with variable depth depth. 
Depending on the depth it can calculate better moves but takes more time to calculate the movement. Results are displayed when the game is over.

#### Warning: if the depth is too large, for example 11 or more, then the program will take too long to calculate the movement of the opponent computer

### Artificial intelligence Used:
The program uses minimax algorithm with a-b sawing where depending on the depth it looks at the possible moves and judges 
using heuristic evaluate function. In the player class, there is the minimax method which takes as input the board and depending on the depth creates 
for each possible move of the board (the flip and isvalidmove function) that is positive a child respectively. 
This process is done as many times as the depth; finally when we get to the leaf nodes we give scores by evaluating and applying the minmax procedure with sawing a-b 
where the nodes that have a small enough score below max and a large enough score below min are deleted.


### The game runs as follows:
 
It first asks the user to provide the search depth of the minimax algorithm that the computer will play against.
If this number is positive it proceeds, otherwise it asks for input again. It then asks the user whether or not they want to play first.
If they answers yes then play as black otherwise as white. The computer respectively takes the other color. 
If they answer yes, they play first, and the computer second as min, otherwise they play second the computer as max. 
The user is then asked to give the exact position of their move: row and column. 
The input is checked if it is within the boundaries of the table (dashboard).
If the move can be made, then it is refreshed and the table is displayed with X and O instead of black and white.
But if the move cannot be made (Move out of bounds or position taken), then again it asks the user for input. 
The last case is when the user cannot play because the board is full or does not change the opponent's pieces where a corresponding message is displayed and the other player plays.
In case, neither of the 2 players can't play because the board is full or no one can change the opponent's symbol, the game ends, 
the respective scores are calculated and displayed.
