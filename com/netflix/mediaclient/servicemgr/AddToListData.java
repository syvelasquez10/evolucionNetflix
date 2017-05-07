// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

public class AddToListData
{
    private final Set<AddToListData$StateListener> listeners;
    private AddToListData$AddToListState previousState;
    private AddToListData$AddToListState state;
    
    public AddToListData(final AddToListData$StateListener addToListData$StateListener) {
        this.listeners = new HashSet<AddToListData$StateListener>(2);
        this.state = AddToListData$AddToListState.NOT_IN_LIST;
        this.previousState = AddToListData$AddToListState.NOT_IN_LIST;
        this.listeners.add(addToListData$StateListener);
    }
    
    public void addListener(final AddToListData$StateListener addToListData$StateListener) {
        this.listeners.add(addToListData$StateListener);
    }
    
    public AddToListData$AddToListState getState() {
        return this.state;
    }
    
    public void removeListener(final AddToListData$StateListener addToListData$StateListener) {
        this.listeners.remove(addToListData$StateListener);
    }
    
    public void revertState() {
        this.setStateAndNotifyListeners(this.previousState);
    }
    
    public void setStateAndNotifyListeners(final AddToListData$AddToListState state) {
        if (this.state != AddToListData$AddToListState.LOADING) {
            this.previousState = this.state;
        }
        this.state = state;
        final Iterator<AddToListData$StateListener> iterator = this.listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().update(state);
        }
    }
}
