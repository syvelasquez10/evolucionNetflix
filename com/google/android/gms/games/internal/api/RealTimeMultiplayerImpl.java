// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import java.util.List;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer$ReliableMessageSentCallback;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import android.content.Intent;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;

public final class RealTimeMultiplayerImpl implements RealTimeMultiplayer
{
    @Override
    public void create(final GoogleApiClient googleApiClient, final RoomConfig roomConfig) {
        Games.c(googleApiClient).a(roomConfig);
    }
    
    @Override
    public void declineInvitation(final GoogleApiClient googleApiClient, final String s) {
        Games.c(googleApiClient).p(s, 0);
    }
    
    @Override
    public void dismissInvitation(final GoogleApiClient googleApiClient, final String s) {
        Games.c(googleApiClient).o(s, 0);
    }
    
    @Override
    public Intent getSelectOpponentsIntent(final GoogleApiClient googleApiClient, final int n, final int n2) {
        return Games.c(googleApiClient).b(n, n2, true);
    }
    
    @Override
    public Intent getSelectOpponentsIntent(final GoogleApiClient googleApiClient, final int n, final int n2, final boolean b) {
        return Games.c(googleApiClient).b(n, n2, b);
    }
    
    @Override
    public RealTimeSocket getSocketForParticipant(final GoogleApiClient googleApiClient, final String s, final String s2) {
        return Games.c(googleApiClient).t(s, s2);
    }
    
    @Override
    public Intent getWaitingRoomIntent(final GoogleApiClient googleApiClient, final Room room, final int n) {
        return Games.c(googleApiClient).a(room, n);
    }
    
    @Override
    public void join(final GoogleApiClient googleApiClient, final RoomConfig roomConfig) {
        Games.c(googleApiClient).b(roomConfig);
    }
    
    @Override
    public void leave(final GoogleApiClient googleApiClient, final RoomUpdateListener roomUpdateListener, final String s) {
        Games.c(googleApiClient).a(roomUpdateListener, s);
    }
    
    @Override
    public int sendReliableMessage(final GoogleApiClient googleApiClient, final RealTimeMultiplayer$ReliableMessageSentCallback realTimeMultiplayer$ReliableMessageSentCallback, final byte[] array, final String s, final String s2) {
        return Games.c(googleApiClient).a(realTimeMultiplayer$ReliableMessageSentCallback, array, s, s2);
    }
    
    @Override
    public int sendUnreliableMessage(final GoogleApiClient googleApiClient, final byte[] array, final String s, final String s2) {
        return Games.c(googleApiClient).a(array, s, new String[] { s2 });
    }
    
    @Override
    public int sendUnreliableMessage(final GoogleApiClient googleApiClient, final byte[] array, final String s, final List<String> list) {
        return Games.c(googleApiClient).a(array, s, list.toArray(new String[list.size()]));
    }
    
    @Override
    public int sendUnreliableMessageToOthers(final GoogleApiClient googleApiClient, final byte[] array, final String s) {
        return Games.c(googleApiClient).d(array, s);
    }
}
