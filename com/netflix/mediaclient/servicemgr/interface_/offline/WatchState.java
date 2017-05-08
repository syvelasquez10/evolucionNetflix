// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline;

public enum WatchState
{
    GEO_BLOCKED(8), 
    LICENSE_EXPIRED(3), 
    NOT_WATCHABLE_DUE_TO_NOT_ENOUGH_DATA(1), 
    PLAY_WINDOW_EXPIRED_BUT_RENEWABLE(5), 
    PLAY_WINDOW_EXPIRED_FINAL(6), 
    UNKNOWN(-1), 
    VIEW_WINDOW_EXPIRED(7), 
    WATCHING_ALLOWED(2);
    
    private final int mValue;
    
    private WatchState(final int mValue) {
        this.mValue = mValue;
    }
    
    public static WatchState getStateByValue(final int n) {
        final WatchState[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final WatchState watchState = values[i];
            if (watchState.getIntValue() == n) {
                return watchState;
            }
        }
        return WatchState.UNKNOWN;
    }
    
    public int getIntValue() {
        return this.mValue;
    }
    
    public boolean hasError() {
        return this.getIntValue() != WatchState.WATCHING_ALLOWED.getIntValue();
    }
}
