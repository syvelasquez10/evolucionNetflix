// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import java.util.List;

public interface RoomStatusUpdateListener
{
    void onConnectedToRoom(final Room p0);
    
    void onDisconnectedFromRoom(final Room p0);
    
    void onP2PConnected(final String p0);
    
    void onP2PDisconnected(final String p0);
    
    void onPeerDeclined(final Room p0, final List<String> p1);
    
    void onPeerInvitedToRoom(final Room p0, final List<String> p1);
    
    void onPeerJoined(final Room p0, final List<String> p1);
    
    void onPeerLeft(final Room p0, final List<String> p1);
    
    void onPeersConnected(final Room p0, final List<String> p1);
    
    void onPeersDisconnected(final Room p0, final List<String> p1);
    
    void onRoomAutoMatching(final Room p0);
    
    void onRoomConnecting(final Room p0);
}
