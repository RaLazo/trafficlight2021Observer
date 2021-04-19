package trafficlight.gui;


import java.awt.*;

public class TrafficLight extends Light implements IObserver {

    TrafficLight(Color color) {
        super(color);
    }

    public void turnOn(boolean a) {
        isOn = a;
        repaint();
    }

    public boolean isOn() {
        return isOn;
    }

    @Override
    public void update() {
        turnOn(!isOn());
    }
}
