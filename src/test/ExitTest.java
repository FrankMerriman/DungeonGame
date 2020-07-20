package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import unsw.dungeon.*;

public class ExitTest {

    @Test
    public void testExit() {
        Dungeon dungeon = new Dungeon(5, 5);

        Player player = new Player(dungeon, 1, 1);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        Exit exit = new Exit(1, 2);
        dungeon.addEntity(exit);
        dungeon.addGoal(exit);

        exit.attach(dungeon); //set dungeon to observe exit

        //move player onto exit
        player.moveDown();
        assertTrue(player.getX() == 1 && player.getY() == 2);

        //player shouldn't move since they triggered all goals
        assertTrue(dungeon.getCompletion());
        player.moveDown();
        assertTrue(player.getX() == 1 && player.getY() == 2);

    }

    //either get two treasure or reach exit
    @Test
    public void testTwoTreasureORExit() {
        Dungeon dungeon = new Dungeon(3, 2);
        Player player = new Player(dungeon, 0, 0);
        Treasure treasure = new Treasure(1, 0);
        Treasure treasure2 = new Treasure(2, 0);
        Exit exit = new Exit(0, 1);

        dungeon.setPlayer(player);
        dungeon.addEntity(player);

        dungeon.addEntity(treasure);
        treasure.attach(dungeon);

        dungeon.addEntity(treasure2);
        treasure2.attach(dungeon);

        dungeon.addEntity(exit);
        exit.attach(dungeon);

        GoalAND getTreasure = new GoalAND();
        getTreasure.addSubGoal(treasure);
        getTreasure.addSubGoal(treasure2);

        GoalOR chooseGoal = new GoalOR();
        chooseGoal.addSubGoal(getTreasure);
        chooseGoal.addSubGoal(exit);

        dungeon.addGoal(chooseGoal);

        assertFalse(dungeon.getCompletion());
        player.moveRight();
        assertFalse(dungeon.getCompletion());
        player.moveLeft();
        
        player.moveDown();
        assertTrue(dungeon.getCompletion());
    }
}