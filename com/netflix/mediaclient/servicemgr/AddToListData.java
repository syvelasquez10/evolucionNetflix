// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

public class AddToListData
{
    private final Set<StateListener> listeners;
    private AddToListState previousState;
    private AddToListState state;
    
    public AddToListData(final StateListener stateListener) {
        this.listeners = new HashSet<StateListener>(2);
        this.state = AddToListState.NOT_IN_LIST;
        this.previousState = AddToListState.NOT_IN_LIST;
        this.listeners.add(stateListener);
    }
    
    public void addListener(final StateListener stateListener) {
        this.listeners.add(stateListener);
    }
    
    public AddToListState getState() {
        return this.state;
    }
    
    public void removeListener(final StateListener stateListener) {
        this.listeners.remove(stateListener);
    }
    
    public void revertState() {
        this.setStateAndNotify(this.previousState);
    }
    
    public void setStateAndNotify(final AddToListState state) {
        if (this.state != AddToListState.LOADING) {
            this.previousState = this.state;
        }
        this.state = state;
        final Iterator<StateListener> iterator = this.listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().update(state);
        }
    }
    
    public enum AddToListState
    {
        IN_LIST, 
        LOADING, 
        NOT_IN_LIST;
    }
    
    public interface StateListener
    {
        void update(final AddToListState p0);
    }
}
