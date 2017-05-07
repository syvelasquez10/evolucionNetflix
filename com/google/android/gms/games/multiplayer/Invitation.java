// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.games.Game;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface Invitation extends Parcelable, Freezable<Invitation>, Participatable
{
    public static final int INVITATION_TYPE_REAL_TIME = 0;
    public static final int INVITATION_TYPE_TURN_BASED = 1;
    
    int getAvailableAutoMatchSlots();
    
    long getCreationTimestamp();
    
    Game getGame();
    
    String getInvitationId();
    
    int getInvitationType();
    
    Participant getInviter();
    
    int getVariant();
}
