package trafficlight.ctrl;


import trafficlight.gui.IObserver;

import java.util.List;

public interface ISubject {

    public void add(IObserver observer);
    public void remove(IObserver observer);
    public void notifyObservers();
}
