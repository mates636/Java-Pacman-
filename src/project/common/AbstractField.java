/**
 * Authors: Martin Priessnitz(xpries01), Mkuláš Uřídil(xuridi01)
 * File: AbstractField
 */

package project.common;

import java.util.HashSet;
import java.util.Set;
public abstract class AbstractField implements CommonField{
    private final Set<Observable.Observer> observers = new HashSet<>();

    public AbstractField(){}

    public void addObserver(Observable.Observer o) {
        this.observers.add(o);
    }

    public void removeObserver(Observable.Observer o) {
        this.observers.remove(o);
    }

    public void notifyObservers() {
        this.observers.forEach((o) -> o.update(this));
    }
}
