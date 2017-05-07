// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import java.util.ArrayList;
import com.google.android.gms.games.multiplayer.Participant;
import android.database.CharArrayBuffer;
import android.os.Bundle;
import com.google.android.gms.games.multiplayer.Participatable;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface Room extends Parcelable, Freezable<Room>, Participatable
{
    public static final int ROOM_STATUS_ACTIVE = 3;
    public static final int ROOM_STATUS_AUTO_MATCHING = 1;
    public static final int ROOM_STATUS_CONNECTING = 2;
    public static final int ROOM_STATUS_INVITING = 0;
    public static final int ROOM_VARIANT_DEFAULT = -1;
    
    Bundle getAutoMatchCriteria();
    
    int getAutoMatchWaitEstimateSeconds();
    
    long getCreationTimestamp();
    
    String getCreatorId();
    
    String getDescription();
    
    void getDescription(final CharArrayBuffer p0);
    
    Participant getParticipant(final String p0);
    
    String getParticipantId(final String p0);
    
    ArrayList<String> getParticipantIds();
    
    int getParticipantStatus(final String p0);
    
    String getRoomId();
    
    int getStatus();
    
    int getVariant();
}
