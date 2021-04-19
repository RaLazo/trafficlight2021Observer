package trafficlight.ctrl;

import trafficlight.gui.IObserver;
import trafficlight.gui.TrafficLight;
import trafficlight.gui.TrafficLightGui;
import trafficlight.states.State;

import java.util.ArrayList;
import java.util.List;

public class TrafficLightCtrl {

    private List<IObserver> observers = new ArrayList<>();

    private static TrafficLightCtrl trafficLightCtrl = null;

    private State greenState;

    private State redState;

    private State yellowState;

    private State currentState;

    private State previousState;

    private final TrafficLightGui gui;

    private boolean doRun = true;

    private TrafficLightCtrl() {
        super();
        initStates();
        gui = new TrafficLightGui(this);
        gui.setVisible(true);
        this.getGreenState().notifyObservers();
    }

    public static TrafficLightCtrl getInstance() {
        if (trafficLightCtrl == null) {
            trafficLightCtrl = new TrafficLightCtrl();
        }
        return trafficLightCtrl;
    }


    private void initStates() {
        greenState = new State() {
            @Override
            public State getNextState() {
                currentState.notifyObservers();
                previousState = currentState;
                yellowState.notifyObservers();
                return yellowState;
            }
            @Override
            public String getColor() {
                return "green";
            }
        };

        redState = new State() {
            @Override
            public State getNextState() {
                currentState.notifyObservers();
                previousState = currentState;
                yellowState.notifyObservers();
                return yellowState;
            }
            @Override
            public String getColor() {
                return "red";
            }
        };

        yellowState = new State() {
            @Override
            public State getNextState() {
                if (previousState.equals(greenState)) {
                    currentState.notifyObservers();
                    previousState = currentState;
                    redState.notifyObservers();
                    return redState;
                }else {
                    currentState.notifyObservers();
                    previousState = currentState;
                    greenState.notifyObservers();
                    return greenState;
                }
            }
            @Override
            public String getColor() {
                return "yellow";
            }
        };
        currentState = greenState;
        previousState = yellowState;
    }

    public State getGreenState() {
        return greenState;
    }

    public State getRedState() {
        return redState;
    }

    public State getYellowState() {
        return yellowState;
    }

    public void run()  {
        int intervall = 1500;
        while (doRun) {
            try {
                Thread.sleep(intervall);
                nextState();
            } catch (InterruptedException e) {
                gui.showErrorMessage(e);
            }
        }
        System.out.println("Stopped");
        System.exit(0);
    }

    public void nextState() {
        currentState = currentState.getNextState();
    }

    public State getCurrentState() {
        return currentState;
    }

    public void stop() {
        doRun = false;
    }

}