// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.d;
import java.util.List;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import java.util.ArrayList;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import android.os.IInterface;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.multiplayer.ParticipantUtils;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.Game;
import android.content.Intent;
import com.google.android.gms.games.GamesMetadata;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.Players;
import android.os.Bundle;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import android.os.IBinder;
import java.util.Iterator;
import java.io.IOException;
import android.os.RemoteException;
import android.net.LocalSocketAddress;
import android.net.LocalSocket;
import com.google.android.gms.games.multiplayer.realtime.a;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.common.data.DataHolder;
import java.util.HashMap;
import android.view.View;
import com.google.android.gms.common.GooglePlayServicesClient;
import android.content.Context;
import android.os.Binder;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import java.util.Map;
import com.google.android.gms.common.api.GoogleApiClient;

public final class fl extends dw<fp> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
    private final String jG;
    private boolean tA;
    private int tB;
    private final String tO;
    private final Map<String, RealTimeSocket> tP;
    private PlayerEntity tQ;
    private GameEntity tR;
    private final fq tS;
    private boolean tT;
    private final Binder tU;
    private final long tV;
    private final boolean tW;
    private final int tX;
    
    public fl(final Context context, final String s, final String s2, final GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks, final GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener, final String[] array, final int n, final View view, final boolean b, final boolean b2, final int n2) {
        this(context, s, s2, new dw.c(connectionCallbacks), new dw.g(onConnectionFailedListener), array, n, view, b, b2, n2, 4368);
    }
    
    public fl(final Context context, final String to, final String s, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String[] array, final int n, final View viewForPopups, final boolean tw, final boolean ta, final int tb, final int tx) {
        super(context, connectionCallbacks, onConnectionFailedListener, array);
        this.tT = false;
        this.tA = false;
        this.tO = to;
        this.jG = eg.f(s);
        this.tU = new Binder();
        this.tP = new HashMap<String, RealTimeSocket>();
        this.tS = fq.a(this, n);
        this.setViewForPopups(viewForPopups);
        this.tA = ta;
        this.tB = tb;
        this.tV = this.hashCode();
        this.tW = tw;
        this.tX = tx;
        this.registerConnectionCallbacks(this);
        this.registerConnectionFailedListener(this);
    }
    
    private Room E(final DataHolder dataHolder) {
        final com.google.android.gms.games.multiplayer.realtime.a a = new com.google.android.gms.games.multiplayer.realtime.a(dataHolder);
        Room room = null;
        try {
            if (a.getCount() > 0) {
                room = a.get(0).freeze();
            }
            return room;
        }
        finally {
            a.close();
        }
    }
    
    private RealTimeSocket ae(final String s) {
        String ag;
        LocalSocket localSocket;
        try {
            ag = this.bQ().ag(s);
            if (ag == null) {
                return null;
            }
            final LocalSocket localSocket2;
            localSocket = (localSocket2 = new LocalSocket());
            final String s2 = ag;
            final LocalSocketAddress localSocketAddress = new LocalSocketAddress(s2);
            localSocket2.connect(localSocketAddress);
            final LocalSocket localSocket3 = localSocket;
            final String s3 = s;
            final fr fr = new fr(localSocket3, s3);
            final fl fl = this;
            final Map<String, RealTimeSocket> map = fl.tP;
            final String s4 = s;
            final fr fr2 = fr;
            map.put(s4, fr2);
            return fr;
        }
        catch (RemoteException ex2) {
            fn.d("GamesClientImpl", "Unable to create socket. Service died.");
            return null;
        }
        try {
            final LocalSocket localSocket2 = localSocket;
            final String s2 = ag;
            final LocalSocketAddress localSocketAddress = new LocalSocketAddress(s2);
            localSocket2.connect(localSocketAddress);
            final LocalSocket localSocket3 = localSocket;
            final String s3 = s;
            final fr fr = new fr(localSocket3, s3);
            final fl fl = this;
            final Map<String, RealTimeSocket> map = fl.tP;
            final String s4 = s;
            final fr fr2 = fr;
            map.put(s4, fr2);
            return fr;
        }
        catch (IOException ex) {
            fn.d("GamesClientImpl", "connect() call failed on socket: " + ex.getMessage());
            return null;
        }
    }
    
    private void dc() {
        this.tQ = null;
    }
    
    private void de() {
        for (final RealTimeSocket realTimeSocket : this.tP.values()) {
            try {
                realTimeSocket.close();
            }
            catch (IOException ex) {
                fn.a("GamesClientImpl", "IOException:", ex);
            }
        }
        this.tP.clear();
    }
    
    protected fp F(final IBinder binder) {
        return fp.a.H(binder);
    }
    
    public int a(final RealTimeMultiplayer.ReliableMessageSentCallback reliableMessageSentCallback, final byte[] array, final String s, final String s2) {
        try {
            return this.bQ().a(new an(reliableMessageSentCallback), array, s, s2);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return -1;
        }
    }
    
    public int a(final byte[] array, final String s, final String[] array2) {
        eg.b(array2, "Participant IDs must not be null");
        try {
            return this.bQ().b(array, s, array2);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return -1;
        }
    }
    
    @Override
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        if (n == 0 && bundle != null) {
            this.tT = bundle.getBoolean("show_welcome_popup");
        }
        super.a(n, binder, bundle);
    }
    
    public void a(final IBinder binder, final Bundle bundle) {
        if (!this.isConnected()) {
            return;
        }
        try {
            this.bQ().a(binder, bundle);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<Players.LoadPlayersResult> c, final int n, final boolean b, final boolean b2) {
        try {
            this.bQ().a(new ak(c), n, b, b2);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<Leaderboards.LoadScoresResult> c, final LeaderboardScoreBuffer leaderboardScoreBuffer, final int n, final int n2) {
        try {
            this.bQ().a(new r(c), leaderboardScoreBuffer.dq().dr(), n, n2);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.InitiateMatchResult> c, final TurnBasedMatchConfig turnBasedMatchConfig) {
        try {
            this.bQ().a(new bb(c), turnBasedMatchConfig.getVariant(), turnBasedMatchConfig.getMinPlayers(), turnBasedMatchConfig.getInvitedPlayerIds(), turnBasedMatchConfig.getAutoMatchCriteria());
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<Players.LoadPlayersResult> c, final String s) {
        try {
            this.bQ().c(new ak(c), s);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<Achievements.UpdateAchievementResult> c, final String s, final int n) {
        Label_0036: {
            if (c != null) {
                break Label_0036;
            }
            fo fo = null;
            try {
                while (true) {
                    this.bQ().a(fo, s, n, this.tS.dn(), this.tS.dm());
                    return;
                    fo = new d(c);
                    continue;
                }
            }
            catch (RemoteException ex) {
                fn.c("GamesClientImpl", "service died");
            }
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<Leaderboards.LoadScoresResult> c, final String s, final int n, final int n2, final int n3, final boolean b) {
        try {
            this.bQ().a(new r(c), s, n, n2, n3, b);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<Players.LoadPlayersResult> c, final String s, final int n, final boolean b, final boolean b2) {
        if (!s.equals("playedWith")) {
            throw new IllegalArgumentException("Invalid player collection: " + s);
        }
        try {
            this.bQ().d(new ak(c), s, n, b, b2);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<Leaderboards.SubmitScoreResult> c, final String s, final long n, final String s2) {
        Label_0024: {
            if (c != null) {
                break Label_0024;
            }
            fo fo = null;
            try {
                while (true) {
                    this.bQ().a(fo, s, n, s2);
                    return;
                    fo = new aw(c);
                    continue;
                }
            }
            catch (RemoteException ex) {
                fn.c("GamesClientImpl", "service died");
            }
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.LeaveMatchResult> c, final String s, final String s2) {
        try {
            this.bQ().d(new bd(c), s, s2);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<Leaderboards.LoadPlayerScoreResult> c, final String s, final String s2, final int n, final int n2) {
        try {
            this.bQ().a(new ai(c), s, s2, n, n2);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<Leaderboards.LeaderboardMetadataResult> c, final String s, final boolean b) {
        try {
            this.bQ().c(new t(c), s, b);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.UpdateMatchResult> c, final String s, final byte[] array, final String s2, final ParticipantResult[] array2) {
        try {
            this.bQ().a(new bh(c), s, array, s2, array2);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.UpdateMatchResult> c, final String s, final byte[] array, final ParticipantResult[] array2) {
        try {
            this.bQ().a(new bh(c), s, array, array2);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<Leaderboards.LeaderboardMetadataResult> c, final boolean b) {
        try {
            this.bQ().b(new t(c), b);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.LoadMatchesResult> c, final int[] array) {
        try {
            this.bQ().a(new bj(c), array);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    @Override
    protected void a(final ec ec, final dw.e e) throws RemoteException {
        final String string = this.getContext().getResources().getConfiguration().locale.toString();
        final Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.games.key.isHeadless", this.tW);
        bundle.putBoolean("com.google.android.gms.games.key.showConnectingPopup", this.tA);
        bundle.putInt("com.google.android.gms.games.key.connectingPopupGravity", this.tB);
        bundle.putInt("com.google.android.gms.games.key.sdkVariant", this.tX);
        ec.a(e, 4242000, this.getContext().getPackageName(), this.jG, this.bO(), this.tO, this.tS.dn(), string, bundle);
    }
    
    @Override
    protected void a(final String... array) {
        int i = 0;
        boolean b = false;
        boolean b2 = false;
        while (i < array.length) {
            final String s = array[i];
            boolean b3;
            if (s.equals("https://www.googleapis.com/auth/games")) {
                b3 = true;
            }
            else {
                b3 = b2;
                if (s.equals("https://www.googleapis.com/auth/games.firstparty")) {
                    b = true;
                    b3 = b2;
                }
            }
            ++i;
            b2 = b3;
        }
        if (b) {
            eg.a(!b2, (Object)String.format("Cannot have both %s and %s!", "https://www.googleapis.com/auth/games", "https://www.googleapis.com/auth/games.firstparty"));
            return;
        }
        eg.a(b2, (Object)String.format("Games APIs requires %s to function.", "https://www.googleapis.com/auth/games"));
    }
    
    @Override
    public Bundle aU() {
        try {
            final Bundle au = this.bQ().aU();
            if (au != null) {
                au.setClassLoader(fl.class.getClassLoader());
            }
            return au;
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return null;
        }
    }
    
    @Override
    protected String am() {
        return "com.google.android.gms.games.service.START";
    }
    
    @Override
    protected String an() {
        return "com.google.android.gms.games.internal.IGamesService";
    }
    
    public void b(final com.google.android.gms.common.api.a.c<Status> c) {
        try {
            this.bQ().a(new au(c));
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.c<Achievements.UpdateAchievementResult> c, final String s) {
        Label_0035: {
            if (c != null) {
                break Label_0035;
            }
            fo fo = null;
            try {
                while (true) {
                    this.bQ().a(fo, s, this.tS.dn(), this.tS.dm());
                    return;
                    fo = new d(c);
                    continue;
                }
            }
            catch (RemoteException ex) {
                fn.c("GamesClientImpl", "service died");
            }
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.c<Achievements.UpdateAchievementResult> c, final String s, final int n) {
        Label_0036: {
            if (c != null) {
                break Label_0036;
            }
            fo fo = null;
            try {
                while (true) {
                    this.bQ().b(fo, s, n, this.tS.dn(), this.tS.dm());
                    return;
                    fo = new d(c);
                    continue;
                }
            }
            catch (RemoteException ex) {
                fn.c("GamesClientImpl", "service died");
            }
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.c<Leaderboards.LoadScoresResult> c, final String s, final int n, final int n2, final int n3, final boolean b) {
        try {
            this.bQ().b(new r(c), s, n, n2, n3, b);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.c<Achievements.LoadAchievementsResult> c, final boolean b) {
        try {
            this.bQ().a(new f(c), b);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void c(final com.google.android.gms.common.api.a.c<Achievements.UpdateAchievementResult> c, final String s) {
        Label_0035: {
            if (c != null) {
                break Label_0035;
            }
            fo fo = null;
            try {
                while (true) {
                    this.bQ().b(fo, s, this.tS.dn(), this.tS.dm());
                    return;
                    fo = new d(c);
                    continue;
                }
            }
            catch (RemoteException ex) {
                fn.c("GamesClientImpl", "service died");
            }
        }
    }
    
    public void clearNotifications(final int n) {
        try {
            this.bQ().clearNotifications(n);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    @Override
    public void connect() {
        this.dc();
        super.connect();
    }
    
    public void createRoom(final RoomConfig roomConfig) {
        try {
            this.bQ().a(new aq(roomConfig.getRoomUpdateListener(), roomConfig.getRoomStatusUpdateListener(), roomConfig.getMessageReceivedListener()), (IBinder)this.tU, roomConfig.getVariant(), roomConfig.getInvitedPlayerIds(), roomConfig.getAutoMatchCriteria(), roomConfig.isSocketEnabled(), this.tV);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void d(final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.InitiateMatchResult> c, final String s) {
        try {
            this.bQ().n(new bb(c), s);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public int dd() {
        try {
            return this.bQ().dd();
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return 4368;
        }
    }
    
    public void df() {
        if (!this.isConnected()) {
            return;
        }
        try {
            this.bQ().df();
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    @Override
    public void disconnect() {
        this.tT = false;
        while (true) {
            if (!this.isConnected()) {
                break Label_0036;
            }
            try {
                final fp fp = this.bQ();
                fp.df();
                fp.j(this.tV);
                this.de();
                super.disconnect();
            }
            catch (RemoteException ex) {
                fn.c("GamesClientImpl", "Failed to notify client disconnect.");
                continue;
            }
            break;
        }
    }
    
    public void dismissTurnBasedMatch(final String s) {
        try {
            this.bQ().ak(s);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void e(final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.InitiateMatchResult> c, final String s) {
        try {
            this.bQ().o(new bb(c), s);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void f(final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.LeaveMatchResult> c, final String s) {
        try {
            this.bQ().q(new bd(c), s);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void g(final com.google.android.gms.common.api.a.c<GamesMetadata.LoadGamesResult> c) {
        try {
            this.bQ().d(new j(c));
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void g(final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.CancelMatchResult> c, final String s) {
        try {
            this.bQ().p(new az(c), s);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public Intent getAchievementsIntent() {
        try {
            return this.bQ().getAchievementsIntent();
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent getAllLeaderboardsIntent() {
        try {
            return this.bQ().getAllLeaderboardsIntent();
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public String getAppId() {
        try {
            return this.bQ().getAppId();
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public String getCurrentAccountName() {
        try {
            return this.bQ().getCurrentAccountName();
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Game getCurrentGame() {
        this.bP();
        synchronized (this) {
            Object tr = this.tR;
            Label_0063: {
                if (tr != null) {
                    break Label_0063;
                }
                try {
                    tr = new GameBuffer(this.bQ().di());
                    try {
                        if (((DataBuffer)tr).getCount() > 0) {
                            this.tR = (GameEntity)((GameBuffer)tr).get(0).freeze();
                        }
                        ((GameBuffer)tr).close();
                        // monitorexit(this)
                        return this.tR;
                    }
                    finally {
                        ((GameBuffer)tr).close();
                    }
                }
                catch (RemoteException ex) {
                    fn.c("GamesClientImpl", "service died");
                }
            }
        }
    }
    
    public Player getCurrentPlayer() {
        this.bP();
        synchronized (this) {
            Object tq = this.tQ;
            Label_0063: {
                if (tq != null) {
                    break Label_0063;
                }
                try {
                    tq = new PlayerBuffer(this.bQ().dg());
                    try {
                        if (((DataBuffer)tq).getCount() > 0) {
                            this.tQ = (PlayerEntity)((PlayerBuffer)tq).get(0).freeze();
                        }
                        ((PlayerBuffer)tq).close();
                        // monitorexit(this)
                        return this.tQ;
                    }
                    finally {
                        ((PlayerBuffer)tq).close();
                    }
                }
                catch (RemoteException ex) {
                    fn.c("GamesClientImpl", "service died");
                }
            }
        }
    }
    
    public String getCurrentPlayerId() {
        try {
            return this.bQ().getCurrentPlayerId();
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent getInvitationInboxIntent() {
        try {
            return this.bQ().getInvitationInboxIntent();
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent getLeaderboardIntent(final String s) {
        try {
            return this.bQ().getLeaderboardIntent(s);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent getMatchInboxIntent() {
        try {
            return this.bQ().getMatchInboxIntent();
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public int getMaxTurnBasedMatchDataSize() {
        try {
            return this.bQ().getMaxTurnBasedMatchDataSize();
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return 2;
        }
    }
    
    public Intent getPlayerSearchIntent() {
        try {
            return this.bQ().getPlayerSearchIntent();
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent getRealTimeSelectOpponentsIntent(final int n, final int n2, final boolean b) {
        try {
            return this.bQ().getRealTimeSelectOpponentsIntent(n, n2, b);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public RealTimeSocket getRealTimeSocketForParticipant(final String s, final String s2) {
        if (s2 == null || !ParticipantUtils.am(s2)) {
            throw new IllegalArgumentException("Bad participant ID");
        }
        final RealTimeSocket realTimeSocket = this.tP.get(s2);
        if (realTimeSocket != null) {
            final RealTimeSocket ae = realTimeSocket;
            if (!realTimeSocket.isClosed()) {
                return ae;
            }
        }
        return this.ae(s2);
    }
    
    public Intent getRealTimeWaitingRoomIntent(final Room room, final int n) {
        try {
            return this.bQ().a(((Freezable<RoomEntity>)room).freeze(), n);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent getSettingsIntent() {
        try {
            return this.bQ().getSettingsIntent();
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent getTurnBasedSelectOpponentsIntent(final int n, final int n2, final boolean b) {
        try {
            return this.bQ().getTurnBasedSelectOpponentsIntent(n, n2, b);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public void h(final com.google.android.gms.common.api.a.c<Invitations.LoadInvitationsResult> c) {
        try {
            this.bQ().e(new o(c));
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void h(final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.LoadMatchResult> c, final String s) {
        try {
            this.bQ().r(new bf(c), s);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void i(final String s, final int n) {
        try {
            this.bQ().i(s, n);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void j(final String s, final int n) {
        try {
            this.bQ().j(s, n);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void joinRoom(final RoomConfig roomConfig) {
        try {
            this.bQ().a(new aq(roomConfig.getRoomUpdateListener(), roomConfig.getRoomStatusUpdateListener(), roomConfig.getMessageReceivedListener()), (IBinder)this.tU, roomConfig.getInvitationId(), roomConfig.isSocketEnabled(), this.tV);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void leaveRoom(final RoomUpdateListener roomUpdateListener, final String s) {
        try {
            this.bQ().e(new aq(roomUpdateListener), s);
            this.de();
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        if (this.tT) {
            this.tS.dl();
            this.tT = false;
        }
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.tT = false;
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
    }
    
    public void registerInvitationListener(final OnInvitationReceivedListener onInvitationReceivedListener) {
        try {
            this.bQ().a(new l(onInvitationReceivedListener), this.tV);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void registerMatchUpdateListener(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
        try {
            this.bQ().b(new x(onTurnBasedMatchUpdateReceivedListener), this.tV);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public int sendUnreliableRealTimeMessageToAll(final byte[] array, final String s) {
        try {
            return this.bQ().b(array, s, null);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
            return -1;
        }
    }
    
    public void setGravityForPopups(final int gravity) {
        this.tS.setGravity(gravity);
    }
    
    public void setViewForPopups(final View view) {
        this.tS.e(view);
    }
    
    public void unregisterInvitationListener() {
        try {
            this.bQ().k(this.tV);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    public void unregisterMatchUpdateListener() {
        try {
            this.bQ().l(this.tV);
        }
        catch (RemoteException ex) {
            fn.c("GamesClientImpl", "service died");
        }
    }
    
    abstract class a extends c
    {
        private final ArrayList<String> tY;
        
        a(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder);
            this.tY = new ArrayList<String>();
            for (int i = 0; i < array.length; ++i) {
                this.tY.add(array[i]);
            }
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            this.a(roomStatusUpdateListener, room, this.tY);
        }
        
        protected abstract void a(final RoomStatusUpdateListener p0, final Room p1, final ArrayList<String> p2);
    }
    
    final class aa extends dw.b<RoomStatusUpdateListener>
    {
        private final String up;
        
        aa(final RoomStatusUpdateListener roomStatusUpdateListener, final String up) {
            super(roomStatusUpdateListener);
            this.up = up;
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener) {
            if (roomStatusUpdateListener != null) {
                roomStatusUpdateListener.onP2PConnected(this.up);
            }
        }
        
        @Override
        protected void aL() {
        }
    }
    
    final class ab extends dw.b<RoomStatusUpdateListener>
    {
        private final String up;
        
        ab(final RoomStatusUpdateListener roomStatusUpdateListener, final String up) {
            super(roomStatusUpdateListener);
            this.up = up;
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener) {
            if (roomStatusUpdateListener != null) {
                roomStatusUpdateListener.onP2PDisconnected(this.up);
            }
        }
        
        @Override
        protected void aL() {
        }
    }
    
    final class ac extends a
    {
        ac(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeersConnected(room, list);
        }
    }
    
    final class ad extends a
    {
        ad(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeerDeclined(room, list);
        }
    }
    
    final class ae extends a
    {
        ae(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeersDisconnected(room, list);
        }
    }
    
    final class af extends a
    {
        af(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeerInvitedToRoom(room, list);
        }
    }
    
    final class ag extends a
    {
        ag(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeerJoined(room, list);
        }
    }
    
    final class ah extends a
    {
        ah(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeerLeft(room, list);
        }
    }
    
    final class ai extends fk
    {
        private final com.google.android.gms.common.api.a.c<Leaderboards.LoadPlayerScoreResult> jW;
        
        ai(final com.google.android.gms.common.api.a.c<Leaderboards.LoadPlayerScoreResult> c) {
            this.jW = (com.google.android.gms.common.api.a.c<Leaderboards.LoadPlayerScoreResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void D(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new aj(this.jW, dataHolder));
        }
    }
    
    final class aj extends dw.d<a.c<LoadPlayerScoreResult>> implements LoadPlayerScoreResult
    {
        private final Status jY;
        private final d uq;
        
        aj(final a.c<LoadPlayerScoreResult> c, final DataHolder dataHolder) {
            super(c, dataHolder);
            this.jY = new Status(dataHolder.getStatusCode());
            fl.this = (fl)new LeaderboardScoreBuffer(dataHolder);
            try {
                if (((DataBuffer)fl.this).getCount() > 0) {
                    this.uq = ((Freezable<d>)((LeaderboardScoreBuffer)fl.this).get(0)).freeze();
                }
                else {
                    this.uq = null;
                }
            }
            finally {
                ((DataBuffer)fl.this).close();
            }
        }
        
        protected void a(final a.c<LoadPlayerScoreResult> c, final DataHolder dataHolder) {
            c.a(this);
        }
        
        @Override
        public LeaderboardScore getScore() {
            return this.uq;
        }
        
        @Override
        public Status getStatus() {
            return this.jY;
        }
    }
    
    final class ak extends fk
    {
        private final com.google.android.gms.common.api.a.c<Players.LoadPlayersResult> jW;
        
        ak(final com.google.android.gms.common.api.a.c<Players.LoadPlayersResult> c) {
            this.jW = (com.google.android.gms.common.api.a.c<Players.LoadPlayersResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void e(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new al(this.jW, dataHolder));
        }
    }
    
    final class al extends ao<a.c<LoadPlayersResult>> implements LoadPlayersResult
    {
        private final PlayerBuffer ur;
        
        al(final a.c<LoadPlayersResult> c, final DataHolder dataHolder) {
            super(c, dataHolder);
            this.ur = new PlayerBuffer(dataHolder);
        }
        
        protected void a(final a.c<LoadPlayersResult> c, final DataHolder dataHolder) {
            c.a(this);
        }
        
        @Override
        public PlayerBuffer getPlayers() {
            return this.ur;
        }
    }
    
    final class am extends dw.b<RealTimeMultiplayer.ReliableMessageSentCallback>
    {
        private final int mC;
        private final String us;
        private final int ut;
        
        am(final RealTimeMultiplayer.ReliableMessageSentCallback reliableMessageSentCallback, final int mc, final int ut, final String us) {
            super(reliableMessageSentCallback);
            this.mC = mc;
            this.ut = ut;
            this.us = us;
        }
        
        public void a(final RealTimeMultiplayer.ReliableMessageSentCallback reliableMessageSentCallback) {
            if (reliableMessageSentCallback != null) {
                reliableMessageSentCallback.onRealTimeMessageSent(this.mC, this.ut, this.us);
            }
        }
        
        @Override
        protected void aL() {
        }
    }
    
    final class an extends fk
    {
        final RealTimeMultiplayer.ReliableMessageSentCallback uu;
        
        public an(final RealTimeMultiplayer.ReliableMessageSentCallback uu) {
            this.uu = uu;
        }
        
        @Override
        public void b(final int n, final int n2, final String s) {
            fl.this.a((dw.b<?>)new am(this.uu, n, n2, s));
        }
    }
    
    abstract class ao<R extends a.c<?>> extends dw.d<R> implements Releasable, Result
    {
        final Status jY;
        final DataHolder nE;
        
        public ao(final R r, final DataHolder ne) {
            super(r, ne);
            this.jY = new Status(ne.getStatusCode());
            this.nE = ne;
        }
        
        @Override
        public Status getStatus() {
            return this.jY;
        }
        
        @Override
        public void release() {
            if (this.nE != null) {
                this.nE.close();
            }
        }
    }
    
    final class ap extends c
    {
        ap(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            super(roomStatusUpdateListener, dataHolder);
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            roomStatusUpdateListener.onRoomAutoMatching(room);
        }
    }
    
    final class aq extends fk
    {
        private final RoomUpdateListener uv;
        private final RoomStatusUpdateListener uw;
        private final RealTimeMessageReceivedListener ux;
        
        public aq(final RoomUpdateListener roomUpdateListener) {
            this.uv = eg.b(roomUpdateListener, "Callbacks must not be null");
            this.uw = null;
            this.ux = null;
        }
        
        public aq(final RoomUpdateListener roomUpdateListener, final RoomStatusUpdateListener uw, final RealTimeMessageReceivedListener ux) {
            this.uv = eg.b(roomUpdateListener, "Callbacks must not be null");
            this.uw = uw;
            this.ux = ux;
        }
        
        @Override
        public void a(final DataHolder dataHolder, final String[] array) {
            fl.this.a((dw.b<?>)new af(this.uw, dataHolder, array));
        }
        
        @Override
        public void b(final DataHolder dataHolder, final String[] array) {
            fl.this.a((dw.b<?>)new ag(this.uw, dataHolder, array));
        }
        
        @Override
        public void c(final DataHolder dataHolder, final String[] array) {
            fl.this.a((dw.b<?>)new ah(this.uw, dataHolder, array));
        }
        
        @Override
        public void d(final DataHolder dataHolder, final String[] array) {
            fl.this.a((dw.b<?>)new ad(this.uw, dataHolder, array));
        }
        
        @Override
        public void e(final DataHolder dataHolder, final String[] array) {
            fl.this.a((dw.b<?>)new ac(this.uw, dataHolder, array));
        }
        
        @Override
        public void f(final DataHolder dataHolder, final String[] array) {
            fl.this.a((dw.b<?>)new ae(this.uw, dataHolder, array));
        }
        
        @Override
        public void onLeftRoom(final int n, final String s) {
            fl.this.a((dw.b<?>)new v(this.uv, n, s));
        }
        
        @Override
        public void onP2PConnected(final String s) {
            fl.this.a((dw.b<?>)new aa(this.uw, s));
        }
        
        @Override
        public void onP2PDisconnected(final String s) {
            fl.this.a((dw.b<?>)new ab(this.uw, s));
        }
        
        @Override
        public void onRealTimeMessageReceived(final RealTimeMessage realTimeMessage) {
            fl.this.a((dw.b<?>)new z(this.ux, realTimeMessage));
        }
        
        @Override
        public void t(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new at(this.uv, dataHolder));
        }
        
        @Override
        public void u(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new q(this.uv, dataHolder));
        }
        
        @Override
        public void v(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new as(this.uw, dataHolder));
        }
        
        @Override
        public void w(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new ap(this.uw, dataHolder));
        }
        
        @Override
        public void x(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new ar(this.uv, dataHolder));
        }
        
        @Override
        public void y(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new h(this.uw, dataHolder));
        }
        
        @Override
        public void z(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new i(this.uw, dataHolder));
        }
    }
    
    final class ar extends b
    {
        ar(final RoomUpdateListener roomUpdateListener, final DataHolder dataHolder) {
            super(roomUpdateListener, dataHolder);
        }
        
        public void a(final RoomUpdateListener roomUpdateListener, final Room room, final int n) {
            roomUpdateListener.onRoomConnected(n, room);
        }
    }
    
    final class as extends c
    {
        as(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            super(roomStatusUpdateListener, dataHolder);
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            roomStatusUpdateListener.onRoomConnecting(room);
        }
    }
    
    final class at extends b
    {
        public at(final RoomUpdateListener roomUpdateListener, final DataHolder dataHolder) {
            super(roomUpdateListener, dataHolder);
        }
        
        public void a(final RoomUpdateListener roomUpdateListener, final Room room, final int n) {
            roomUpdateListener.onRoomCreated(n, room);
        }
    }
    
    final class au extends fk
    {
        private final com.google.android.gms.common.api.a.c<Status> jW;
        
        public au(final com.google.android.gms.common.api.a.c<Status> c) {
            this.jW = (com.google.android.gms.common.api.a.c<Status>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void onSignOutComplete() {
            fl.this.a((dw.b<?>)new av(this.jW, new Status(0)));
        }
    }
    
    final class av extends dw.b<a.c<Status>>
    {
        private final Status jY;
        
        public av(final a.c<Status> c, final Status jy) {
            super(c);
            this.jY = jy;
        }
        
        @Override
        protected void aL() {
        }
        
        public void c(final a.c<Status> c) {
            c.a(this.jY);
        }
    }
    
    final class aw extends fk
    {
        private final com.google.android.gms.common.api.a.c<Leaderboards.SubmitScoreResult> jW;
        
        public aw(final com.google.android.gms.common.api.a.c<Leaderboards.SubmitScoreResult> c) {
            this.jW = (com.google.android.gms.common.api.a.c<Leaderboards.SubmitScoreResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void d(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new ax(this.jW, dataHolder));
        }
    }
    
    final class ax extends ao<a.c<SubmitScoreResult>> implements SubmitScoreResult
    {
        private final ScoreSubmissionData uy;
        
        public ax(final a.c<SubmitScoreResult> c, final DataHolder dataHolder) {
            super(c, dataHolder);
            this.uy = new ScoreSubmissionData(dataHolder);
        }
        
        public void a(final a.c<SubmitScoreResult> c, final DataHolder dataHolder) {
            c.a(this);
        }
        
        @Override
        public ScoreSubmissionData getScoreData() {
            return this.uy;
        }
    }
    
    abstract class ay<T extends a.c<?>> extends ao<T>
    {
        final TurnBasedMatch un;
        
        ay(final T t, final DataHolder dataHolder) {
            super(t, dataHolder);
            fl.this = (fl)new TurnBasedMatchBuffer(dataHolder);
            try {
                if (((com.google.android.gms.common.data.d)fl.this).getCount() > 0) {
                    this.un = ((com.google.android.gms.common.data.d<TurnBasedMatch>)fl.this).get(0).freeze();
                }
                else {
                    this.un = null;
                }
            }
            finally {
                ((DataBuffer)fl.this).close();
            }
        }
        
        protected void a(final T t, final DataHolder dataHolder) {
            this.i(t);
        }
        
        public TurnBasedMatch getMatch() {
            return this.un;
        }
        
        abstract void i(final T p0);
    }
    
    final class az extends fk
    {
        private final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.CancelMatchResult> uz;
        
        public az(final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.CancelMatchResult> c) {
            this.uz = (com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.CancelMatchResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void onTurnBasedMatchCanceled(final int n, final String s) {
            fl.this.a((dw.b<?>)new ba(this.uz, new Status(n), s));
        }
    }
    
    abstract class b extends dw.d<RoomUpdateListener>
    {
        b(final RoomUpdateListener roomUpdateListener, final DataHolder dataHolder) {
            super(roomUpdateListener, dataHolder);
        }
        
        protected void a(final RoomUpdateListener roomUpdateListener, final DataHolder dataHolder) {
            this.a(roomUpdateListener, fl.this.E(dataHolder), dataHolder.getStatusCode());
        }
        
        protected abstract void a(final RoomUpdateListener p0, final Room p1, final int p2);
    }
    
    final class ba extends dw.b<a.c<CancelMatchResult>> implements CancelMatchResult
    {
        private final Status jY;
        private final String uA;
        
        ba(final a.c<CancelMatchResult> c, final Status jy, final String ua) {
            super(c);
            this.jY = jy;
            this.uA = ua;
        }
        
        @Override
        protected void aL() {
        }
        
        public void c(final a.c<CancelMatchResult> c) {
            c.a(this);
        }
        
        @Override
        public String getMatchId() {
            return this.uA;
        }
        
        @Override
        public Status getStatus() {
            return this.jY;
        }
    }
    
    final class bb extends fk
    {
        private final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.InitiateMatchResult> uB;
        
        public bb(final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.InitiateMatchResult> c) {
            this.uB = (com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.InitiateMatchResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void n(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new bc(this.uB, dataHolder));
        }
    }
    
    final class bc extends ay<a.c<InitiateMatchResult>> implements InitiateMatchResult
    {
        bc(final a.c<InitiateMatchResult> c, final DataHolder dataHolder) {
            super(c, dataHolder);
        }
        
        protected void i(final a.c<InitiateMatchResult> c) {
            c.a(this);
        }
    }
    
    final class bd extends fk
    {
        private final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.LeaveMatchResult> uC;
        
        public bd(final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.LeaveMatchResult> c) {
            this.uC = (com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.LeaveMatchResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void p(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new be(this.uC, dataHolder));
        }
    }
    
    final class be extends ay<a.c<LeaveMatchResult>> implements LeaveMatchResult
    {
        be(final a.c<LeaveMatchResult> c, final DataHolder dataHolder) {
            super(c, dataHolder);
        }
        
        protected void i(final a.c<LeaveMatchResult> c) {
            c.a(this);
        }
    }
    
    final class bf extends fk
    {
        private final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.LoadMatchResult> uD;
        
        public bf(final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.LoadMatchResult> c) {
            this.uD = (com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.LoadMatchResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void m(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new bg(this.uD, dataHolder));
        }
    }
    
    final class bg extends ay<a.c<LoadMatchResult>> implements LoadMatchResult
    {
        bg(final a.c<LoadMatchResult> c, final DataHolder dataHolder) {
            super(c, dataHolder);
        }
        
        protected void i(final a.c<LoadMatchResult> c) {
            c.a(this);
        }
    }
    
    final class bh extends fk
    {
        private final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.UpdateMatchResult> uE;
        
        public bh(final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.UpdateMatchResult> c) {
            this.uE = (com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.UpdateMatchResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void o(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new bi(this.uE, dataHolder));
        }
    }
    
    final class bi extends ay<a.c<UpdateMatchResult>> implements UpdateMatchResult
    {
        bi(final a.c<UpdateMatchResult> c, final DataHolder dataHolder) {
            super(c, dataHolder);
        }
        
        protected void i(final a.c<UpdateMatchResult> c) {
            c.a(this);
        }
    }
    
    final class bj extends fk
    {
        private final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.LoadMatchesResult> uF;
        
        public bj(final com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.LoadMatchesResult> c) {
            this.uF = (com.google.android.gms.common.api.a.c<TurnBasedMultiplayer.LoadMatchesResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void a(final int n, final Bundle bundle) {
            bundle.setClassLoader(this.getClass().getClassLoader());
            fl.this.a((dw.b<?>)new bk(this.uF, new Status(n), bundle));
        }
    }
    
    final class bk extends dw.b<a.c<LoadMatchesResult>> implements LoadMatchesResult
    {
        private final Status jY;
        private final LoadMatchesResponse uG;
        
        bk(final a.c<LoadMatchesResult> c, final Status jy, final Bundle bundle) {
            super(c);
            this.jY = jy;
            this.uG = new LoadMatchesResponse(bundle);
        }
        
        @Override
        protected void aL() {
        }
        
        protected void c(final a.c<LoadMatchesResult> c) {
            c.a(this);
        }
        
        @Override
        public LoadMatchesResponse getMatches() {
            return this.uG;
        }
        
        @Override
        public Status getStatus() {
            return this.jY;
        }
        
        @Override
        public void release() {
            this.uG.close();
        }
    }
    
    abstract class c extends dw.d<RoomStatusUpdateListener>
    {
        c(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            super(roomStatusUpdateListener, dataHolder);
        }
        
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            this.a(roomStatusUpdateListener, fl.this.E(dataHolder));
        }
        
        protected abstract void a(final RoomStatusUpdateListener p0, final Room p1);
    }
    
    final class d extends fk
    {
        private final com.google.android.gms.common.api.a.c<Achievements.UpdateAchievementResult> jW;
        
        d(final com.google.android.gms.common.api.a.c<Achievements.UpdateAchievementResult> c) {
            this.jW = (com.google.android.gms.common.api.a.c<Achievements.UpdateAchievementResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void onAchievementUpdated(final int n, final String s) {
            fl.this.a((dw.b<?>)new e(this.jW, n, s));
        }
    }
    
    final class e extends dw.b<a.c<UpdateAchievementResult>> implements UpdateAchievementResult
    {
        private final Status jY;
        private final String ua;
        
        e(final a.c<UpdateAchievementResult> c, final int n, final String ua) {
            super(c);
            this.jY = new Status(n);
            this.ua = ua;
        }
        
        @Override
        protected void aL() {
        }
        
        protected void c(final a.c<UpdateAchievementResult> c) {
            c.a(this);
        }
        
        @Override
        public String getAchievementId() {
            return this.ua;
        }
        
        @Override
        public Status getStatus() {
            return this.jY;
        }
    }
    
    final class f extends fk
    {
        private final com.google.android.gms.common.api.a.c<Achievements.LoadAchievementsResult> jW;
        
        f(final com.google.android.gms.common.api.a.c<Achievements.LoadAchievementsResult> c) {
            this.jW = (com.google.android.gms.common.api.a.c<Achievements.LoadAchievementsResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void b(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new g(this.jW, dataHolder));
        }
    }
    
    final class g extends ao<a.c<LoadAchievementsResult>> implements LoadAchievementsResult
    {
        private final AchievementBuffer ub;
        
        g(final a.c<LoadAchievementsResult> c, final DataHolder dataHolder) {
            super(c, dataHolder);
            this.ub = new AchievementBuffer(dataHolder);
        }
        
        protected void a(final a.c<LoadAchievementsResult> c, final DataHolder dataHolder) {
            c.a(this);
        }
        
        @Override
        public AchievementBuffer getAchievements() {
            return this.ub;
        }
    }
    
    final class h extends c
    {
        h(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            super(roomStatusUpdateListener, dataHolder);
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            roomStatusUpdateListener.onConnectedToRoom(room);
        }
    }
    
    final class i extends c
    {
        i(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            super(roomStatusUpdateListener, dataHolder);
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            roomStatusUpdateListener.onDisconnectedFromRoom(room);
        }
    }
    
    final class j extends fk
    {
        private final com.google.android.gms.common.api.a.c<GamesMetadata.LoadGamesResult> jW;
        
        j(final com.google.android.gms.common.api.a.c<GamesMetadata.LoadGamesResult> c) {
            this.jW = (com.google.android.gms.common.api.a.c<GamesMetadata.LoadGamesResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void g(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new k(this.jW, dataHolder));
        }
    }
    
    final class k extends ao<a.c<LoadGamesResult>> implements LoadGamesResult
    {
        private final GameBuffer uc;
        
        k(final a.c<LoadGamesResult> c, final DataHolder dataHolder) {
            super(c, dataHolder);
            this.uc = new GameBuffer(dataHolder);
        }
        
        protected void a(final a.c<LoadGamesResult> c, final DataHolder dataHolder) {
            c.a(this);
        }
        
        @Override
        public GameBuffer getGames() {
            return this.uc;
        }
    }
    
    final class l extends fk
    {
        private final OnInvitationReceivedListener ud;
        
        l(final OnInvitationReceivedListener ud) {
            this.ud = ud;
        }
        
        @Override
        public void l(final DataHolder dataHolder) {
            final InvitationBuffer invitationBuffer = new InvitationBuffer(dataHolder);
            Invitation invitation = null;
            try {
                if (invitationBuffer.getCount() > 0) {
                    invitation = invitationBuffer.get(0).freeze();
                }
                invitationBuffer.close();
                if (invitation != null) {
                    fl.this.a((dw.b<?>)new m(this.ud, invitation));
                }
            }
            finally {
                invitationBuffer.close();
            }
        }
        
        @Override
        public void onInvitationRemoved(final String s) {
            fl.this.a((dw.b<?>)new n(this.ud, s));
        }
    }
    
    final class m extends dw.b<OnInvitationReceivedListener>
    {
        private final Invitation ue;
        
        m(final OnInvitationReceivedListener onInvitationReceivedListener, final Invitation ue) {
            super(onInvitationReceivedListener);
            this.ue = ue;
        }
        
        protected void a(final OnInvitationReceivedListener onInvitationReceivedListener) {
            onInvitationReceivedListener.onInvitationReceived(this.ue);
        }
        
        @Override
        protected void aL() {
        }
    }
    
    final class n extends dw.b<OnInvitationReceivedListener>
    {
        private final String uf;
        
        n(final OnInvitationReceivedListener onInvitationReceivedListener, final String uf) {
            super(onInvitationReceivedListener);
            this.uf = uf;
        }
        
        protected void a(final OnInvitationReceivedListener onInvitationReceivedListener) {
            onInvitationReceivedListener.onInvitationRemoved(this.uf);
        }
        
        @Override
        protected void aL() {
        }
    }
    
    final class o extends fk
    {
        private final com.google.android.gms.common.api.a.c<Invitations.LoadInvitationsResult> jW;
        
        o(final com.google.android.gms.common.api.a.c<Invitations.LoadInvitationsResult> c) {
            this.jW = (com.google.android.gms.common.api.a.c<Invitations.LoadInvitationsResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void k(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new p(this.jW, dataHolder));
        }
    }
    
    final class p extends ao<a.c<LoadInvitationsResult>> implements LoadInvitationsResult
    {
        private final InvitationBuffer ug;
        
        p(final a.c<LoadInvitationsResult> c, final DataHolder dataHolder) {
            super(c, dataHolder);
            this.ug = new InvitationBuffer(dataHolder);
        }
        
        protected void a(final a.c<LoadInvitationsResult> c, final DataHolder dataHolder) {
            c.a(this);
        }
        
        @Override
        public InvitationBuffer getInvitations() {
            return this.ug;
        }
    }
    
    final class q extends b
    {
        public q(final RoomUpdateListener roomUpdateListener, final DataHolder dataHolder) {
            super(roomUpdateListener, dataHolder);
        }
        
        public void a(final RoomUpdateListener roomUpdateListener, final Room room, final int n) {
            roomUpdateListener.onJoinedRoom(n, room);
        }
    }
    
    final class r extends fk
    {
        private final com.google.android.gms.common.api.a.c<Leaderboards.LoadScoresResult> jW;
        
        r(final com.google.android.gms.common.api.a.c<Leaderboards.LoadScoresResult> c) {
            this.jW = (com.google.android.gms.common.api.a.c<Leaderboards.LoadScoresResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void a(final DataHolder dataHolder, final DataHolder dataHolder2) {
            fl.this.a((dw.b<?>)new s(this.jW, dataHolder, dataHolder2));
        }
    }
    
    final class s extends ao<a.c<LoadScoresResult>> implements LoadScoresResult
    {
        private final com.google.android.gms.games.leaderboard.a uh;
        private final LeaderboardScoreBuffer ui;
        
        s(final a.c<LoadScoresResult> c, final DataHolder dataHolder, final DataHolder dataHolder2) {
            super(c, dataHolder2);
            fl.this = (fl)new LeaderboardBuffer(dataHolder);
            try {
                if (((com.google.android.gms.common.data.d)fl.this).getCount() > 0) {
                    this.uh = ((Freezable<com.google.android.gms.games.leaderboard.a>)((com.google.android.gms.common.data.d<Leaderboard>)fl.this).get(0)).freeze();
                }
                else {
                    this.uh = null;
                }
                ((DataBuffer)fl.this).close();
                this.ui = new LeaderboardScoreBuffer(dataHolder2);
            }
            finally {
                ((DataBuffer)fl.this).close();
            }
        }
        
        protected void a(final a.c<LoadScoresResult> c, final DataHolder dataHolder) {
            c.a(this);
        }
        
        @Override
        public Leaderboard getLeaderboard() {
            return this.uh;
        }
        
        @Override
        public LeaderboardScoreBuffer getScores() {
            return this.ui;
        }
    }
    
    final class t extends fk
    {
        private final com.google.android.gms.common.api.a.c<Leaderboards.LeaderboardMetadataResult> jW;
        
        t(final com.google.android.gms.common.api.a.c<Leaderboards.LeaderboardMetadataResult> c) {
            this.jW = (com.google.android.gms.common.api.a.c<Leaderboards.LeaderboardMetadataResult>)eg.b((com.google.android.gms.common.api.a.c)c, "Holder must not be null");
        }
        
        @Override
        public void c(final DataHolder dataHolder) {
            fl.this.a((dw.b<?>)new u(this.jW, dataHolder));
        }
    }
    
    final class u extends ao<a.c<LeaderboardMetadataResult>> implements LeaderboardMetadataResult
    {
        private final LeaderboardBuffer uj;
        
        u(final a.c<LeaderboardMetadataResult> c, final DataHolder dataHolder) {
            super(c, dataHolder);
            this.uj = new LeaderboardBuffer(dataHolder);
        }
        
        protected void a(final a.c<LeaderboardMetadataResult> c, final DataHolder dataHolder) {
            c.a(this);
        }
        
        @Override
        public LeaderboardBuffer getLeaderboards() {
            return this.uj;
        }
    }
    
    final class v extends dw.b<RoomUpdateListener>
    {
        private final int mC;
        private final String uk;
        
        v(final RoomUpdateListener roomUpdateListener, final int mc, final String uk) {
            super(roomUpdateListener);
            this.mC = mc;
            this.uk = uk;
        }
        
        public void a(final RoomUpdateListener roomUpdateListener) {
            roomUpdateListener.onLeftRoom(this.mC, this.uk);
        }
        
        @Override
        protected void aL() {
        }
    }
    
    final class w extends dw.b<OnTurnBasedMatchUpdateReceivedListener>
    {
        private final String ul;
        
        w(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener, final String ul) {
            super(onTurnBasedMatchUpdateReceivedListener);
            this.ul = ul;
        }
        
        protected void a(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
            onTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchRemoved(this.ul);
        }
        
        @Override
        protected void aL() {
        }
    }
    
    final class x extends fk
    {
        private final OnTurnBasedMatchUpdateReceivedListener um;
        
        x(final OnTurnBasedMatchUpdateReceivedListener um) {
            this.um = um;
        }
        
        @Override
        public void onTurnBasedMatchRemoved(final String s) {
            fl.this.a((dw.b<?>)new w(this.um, s));
        }
        
        @Override
        public void q(final DataHolder dataHolder) {
            final TurnBasedMatchBuffer turnBasedMatchBuffer = new TurnBasedMatchBuffer(dataHolder);
            TurnBasedMatch turnBasedMatch = null;
            try {
                if (turnBasedMatchBuffer.getCount() > 0) {
                    turnBasedMatch = turnBasedMatchBuffer.get(0).freeze();
                }
                turnBasedMatchBuffer.close();
                if (turnBasedMatch != null) {
                    fl.this.a((dw.b<?>)new y(this.um, turnBasedMatch));
                }
            }
            finally {
                turnBasedMatchBuffer.close();
            }
        }
    }
    
    final class y extends dw.b<OnTurnBasedMatchUpdateReceivedListener>
    {
        private final TurnBasedMatch un;
        
        y(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener, final TurnBasedMatch un) {
            super(onTurnBasedMatchUpdateReceivedListener);
            this.un = un;
        }
        
        protected void a(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
            onTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchReceived(this.un);
        }
        
        @Override
        protected void aL() {
        }
    }
    
    final class z extends dw.b<RealTimeMessageReceivedListener>
    {
        private final RealTimeMessage uo;
        
        z(final RealTimeMessageReceivedListener realTimeMessageReceivedListener, final RealTimeMessage uo) {
            super(realTimeMessageReceivedListener);
            this.uo = uo;
        }
        
        public void a(final RealTimeMessageReceivedListener realTimeMessageReceivedListener) {
            if (realTimeMessageReceivedListener != null) {
                realTimeMessageReceivedListener.onRealTimeMessageReceived(this.uo);
            }
        }
        
        @Override
        protected void aL() {
        }
    }
}
