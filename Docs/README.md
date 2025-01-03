## POO_03 - BOMBING SNAKE

In this game you will guide the snake in a limited space to collect food while avoiding bombs and the snake's own body which grows as it eats.
This project was developed by *Beatriz Almeida* (*a044416*@umaia.pt) and *Carolina Fernandes* (*a044897*@umaia.pt) for POO 2024⁄25.

### IMPLEMENTED FEATURES

- **Movement** - The snake moves in the four cardinal directions (up, down, left, right) using the w,s,a and d keys.
- **Food Collection** - The snake grows in length each time it eats food, and the player's score increases accordingly.
- **Collisions** - The game ends if the snake collides with the walls or its own tail.
- **Dynamic Game Speed** - As the player collects more food, the game speed increases, making it progressively more challenging.

### PLANNED FEATURES

- **Bombs** - The game ends if the snake collides with the bombs.


### THE CREATION OF FOOD AND BOMB OBJECTS

**Problem in Context**

In the initial game design, creating Food and Bomb objects was repetitive and scattered throughout the code. This duplication made the code harder to maintain and increased the chance of mistakes if the creation logic needed changes. Additionally, the Map class had too many responsibilities, managing both game logic and object creation, which goes against the **Single Responsibility Principle**.

**The Pattern**

We used the **Factory Method** pattern to centralize the creation process. This pattern encapsulates object creation in dedicated methods, making it easier to maintain and adapt. It also separates the object creation logic from the Map class.

**Implementation**

The following figure shows how the Factory Method pattern was mapped to the design:

![image](https://github.com/user-attachments/assets/d5b3837b-45fd-4c68-b254-147663d8b630)


The relevant code implementation can be found in:

- [Map.createFood()]

- [Map.createBomb()]

**Consequences**
  Benefits:
  - Less duplication, making code easier to maintain.
  - Easier to add new object.
  Disadvantages:
  - Slightly more complex code due to additional methods for object creation.
------
### SNAKE MOVEMENT AND DIRECTION LOGIC
**Problem in Context**

Originally, snake movement was controlled by separate methods like moveSnakeUp() and moveSnakeDown(). This approach led to scattered movement logic and made it harder to prevent errors, such as the snake immediately reversing direction.

**The Pattern**

The **Strategy Pattern** was implemented to manage movement. This pattern separates movement behaviors into strategies, which can be dynamically changed during gameplay.

**Implementation**

The Strategy pattern is applied as shown in the diagram:
![image](https://github.com/user-attachments/assets/bb1283d7-2ba2-4281-92a4-19d2f02eddd0)

**Consequences**

  Benefits:
  - Centralized movement logic.
  - Safer gameplay, with rules to prevent reversing direction.
  - Easier to add new movement features without changing existing code.
  Disadvantages:
  - Slightly more complex code due to the use of strategies.
    
------

### Snake's Body Growth and Collision Detection

**Problem Context**
The snake's body was managed as a simple list of positions, and collision detection was done using loops in the update() method, concentrating many responsibilities in a single function.

**Pattern Used**
The **Composite Pattern** was used to treat the snake's body as a composed entity. This makes managing the body and collision detection easier, keeping the design cleaner and more modular.

**Implementation**

In the code:

Snake.update() adds new positions to the body and maintains the correct size.
Snake.death() checks for collisions with the snake's own body.

The diagram reflects the composition of the snake's body (a list of Position).

![image](https://github.com/user-attachments/assets/3aec4f12-623e-4db9-90b0-4f4ba39992e4)


**Consequences**
  Benefits:
  - Easier management of the body and collision detection.
  - Scalable for future improvements (e.g., obstacles or power-ups).
  Disadvantages:
  - More memory usage due to storing multiple positions.


### TESTING

- Screenshot of coverage report.
![image](https://github.com/user-attachments/assets/df8d43e8-991f-424e-ad51-120632767375)

- Link to mutation testing report.

### SELF-EVALUATION

> Our group work was well divided, with each member. We made sure our contribute was equal.

**Example**:

- Beatriz Almeida: 50%
- Carolina Fernandes: 50%
