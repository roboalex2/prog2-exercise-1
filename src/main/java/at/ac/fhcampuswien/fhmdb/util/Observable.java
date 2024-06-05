package at.ac.fhcampuswien.fhmdb.util;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public interface Observable {
    List<Observer> observers = new ArrayList<>();

    default void addObserver(Observer observer){
        observers.add(observer);
    }

    default void removeObserver(Observer observer){
        observers.remove(observer);
    }


    void notifyObservers(String message);
}
