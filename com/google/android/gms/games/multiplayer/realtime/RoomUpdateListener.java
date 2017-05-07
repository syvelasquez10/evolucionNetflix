// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

public interface RoomUpdateListener
{
    void onJoinedRoom(final int p0, final Room p1);
    
    void onLeftRoom(final int p0, final String p1);
    
    void onRoomConnected(final int p0, final Room p1);
    
    void onRoomCreated(final int p0, final Room p1);
}
