# CSE201 AP-Project: Stick Hero

## **Team Members:**
- Naman Birla - 2022310
- Pranav Bharadwaj - 2022363

## **How to run the program:**
1. Open the folder consisting `pom.xml` file in the command line.
2. Run the command `mvn validate` to validate the pom project.
3. Run the command `mvn clean javafx:run` to run the project.


## Two design patterns were involved in the making of the project:

1. **Singleton Pattern:**
   - Only one instance of the pause menu is required, and hence, the Singleton pattern was used.

2. **Flyweight Pattern:**
   - The Flyweight pattern was employed for the player class.
   - Each instance of the player class has a unique character associated with every instance of player.
  