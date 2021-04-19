package trafficlight.states;


import trafficlight.gui.IObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    private ArrayList<IObserver> observers = new ArrayList<>();

    public void add(IObserver observer){
        this.observers.add(observer);
    };

    public void remove(IObserver observer){
        this.observers.remove(observer);
    };
    public void notifyObservers(){
        for(IObserver observer: this.observers){
            observer.update();
        }
    };
}
