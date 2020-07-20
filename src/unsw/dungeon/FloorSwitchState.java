package unsw.dungeon;
/**
 * An interface for floor switch states
 * 
 * 
 */
public interface FloorSwitchState {
    /**
     * Changes the floor switch state to a triggered state.
     */
    public void triggerFloorSwitch();
    /**
     * Changes the floor switch state to an untriggered state.
     */
    public void untriggerFloorSwitch();
}