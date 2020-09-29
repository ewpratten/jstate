package ca.retrylife.jstate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UsageTest {

    // States
    private enum MyStates {
        A, B
    }

    // State Machine
    private StateMachine<MyStates> stateMachine;

    // Test toggle
    private boolean testToggle = false;

    private MyStates handleA(StateMetadata<MyStates> meta) {
        if (meta.isFirstRun()) {
            testToggle = true;
            return MyStates.A;
        } else {
            return MyStates.B;
        }
    }

    private MyStates handleB(StateMetadata<MyStates> meta) {
        if (meta.isFirstRun()) {
            testToggle = false;
            return MyStates.B;
        } else {
            return MyStates.A;
        }
    }

    @Test
    public void testStateHandling() {

        // Set up the state machine
        stateMachine = new StateMachine<>("TestMachine");

        // Add handlers
        stateMachine.setDefaultState(MyStates.A, this::handleA);
        stateMachine.addState(MyStates.B, this::handleB);

        // Run first cycles
        stateMachine.update();
        assertTrue(testToggle);
        stateMachine.update();
        assertTrue(testToggle);

        // Run flip cycles
        stateMachine.update();
        assertFalse(testToggle);
        stateMachine.update();
        assertFalse(testToggle);

    }
}