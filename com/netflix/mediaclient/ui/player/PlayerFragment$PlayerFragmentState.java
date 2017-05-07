// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

public enum PlayerFragment$PlayerFragmentState
{
    ACTIVITY_NOTREADY(0, "NOTREADY"), 
    ACTIVITY_PLAYER_READY(2, "PLAYER_READY"), 
    ACTIVITY_SRVCMNGR_READY(1, "SRVCMNGR_READY");
    
    int mActivityState;
    String mName;
    
    private PlayerFragment$PlayerFragmentState(final int mActivityState, final String mName) {
        this.mActivityState = mActivityState;
        this.mName = mName;
    }
    
    protected String getName() {
        return this.mName;
    }
    
    protected int getState() {
        return this.mActivityState;
    }
}
