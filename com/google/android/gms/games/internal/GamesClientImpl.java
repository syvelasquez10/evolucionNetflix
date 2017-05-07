// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.d;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;
import java.util.Set;
import com.google.android.gms.games.internal.request.RequestUpdateOutcomes;
import com.google.android.gms.games.internal.constants.RequestType;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreEntity;
import java.util.List;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardEntity;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.internal.game.GameInstanceBuffer;
import com.google.android.gms.games.internal.player.ExtendedPlayerBuffer;
import com.google.android.gms.games.internal.game.ExtendedGameBuffer;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import java.util.ArrayList;
import android.os.IInterface;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.games.internal.game.Acls;
import com.google.android.gms.games.multiplayer.ParticipantUtils;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.internal.fl;
import com.google.android.gms.internal.fm;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.GamesMetadata;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.common.api.a;
import android.os.Bundle;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import android.os.Parcelable;
import android.graphics.Bitmap;
import android.content.Intent;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import android.os.IBinder;
import java.util.Iterator;
import android.os.ParcelFileDescriptor;
import java.io.IOException;
import android.os.RemoteException;
import android.net.LocalSocketAddress;
import android.net.LocalSocket;
import com.google.android.gms.games.multiplayer.realtime.RoomBuffer;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.common.data.DataHolder;
import java.util.HashMap;
import com.google.android.gms.internal.fq;
import android.view.View;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import java.util.Map;
import android.os.Binder;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.ff;

public final class GamesClientImpl extends ff<IGamesService> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
    private boolean IA;
    private int IB;
    private final Binder IC;
    private final long IE;
    private final boolean IF;
    private final int IG;
    private final boolean IH;
    private final String Iu;
    private final Map<String, RealTimeSocket> Iv;
    private PlayerEntity Iw;
    private GameEntity Ix;
    private final PopupManager Iy;
    private boolean Iz;
    private final String wG;
    
    public GamesClientImpl(final Context context, final Looper looper, final String iu, final String s, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String[] array, final int n, final View view, final boolean if1, final boolean ia, final int ib, final boolean ih, final int ig) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, array);
        this.Iz = false;
        this.IA = false;
        this.Iu = iu;
        this.wG = fq.f(s);
        this.IC = new Binder();
        this.Iv = new HashMap<String, RealTimeSocket>();
        this.Iy = PopupManager.a(this, n);
        this.f(view);
        this.IA = ia;
        this.IB = ib;
        this.IE = this.hashCode();
        this.IF = if1;
        this.IH = ih;
        this.IG = ig;
        this.registerConnectionCallbacks(this);
        this.registerConnectionFailedListener(this);
    }
    
    private Room G(final DataHolder dataHolder) {
        final RoomBuffer roomBuffer = new RoomBuffer(dataHolder);
        Room room = null;
        try {
            if (roomBuffer.getCount() > 0) {
                room = roomBuffer.get(0).freeze();
            }
            return room;
        }
        finally {
            roomBuffer.close();
        }
    }
    
    private RealTimeSocket aC(final String s) {
        String ae;
        LocalSocket localSocket;
        try {
            final ParcelFileDescriptor aj = this.eM().aJ(s);
            if (aj != null) {
                GamesLog.f("GamesClientImpl", "Created native libjingle socket.");
                final LibjingleNativeSocket libjingleNativeSocket = new LibjingleNativeSocket(aj);
                this.Iv.put(s, libjingleNativeSocket);
                return libjingleNativeSocket;
            }
            GamesLog.f("GamesClientImpl", "Unable to create native libjingle socket, resorting to old socket.");
            ae = this.eM().aE(s);
            if (ae == null) {
                return null;
            }
            final LocalSocket localSocket2;
            localSocket = (localSocket2 = new LocalSocket());
            final String s2 = ae;
            final LocalSocketAddress localSocketAddress = new LocalSocketAddress(s2);
            localSocket2.connect(localSocketAddress);
            final LocalSocket localSocket3 = localSocket;
            final String s3 = s;
            final RealTimeSocketImpl realTimeSocketImpl = new RealTimeSocketImpl(localSocket3, s3);
            final GamesClientImpl gamesClientImpl = this;
            final Map<String, RealTimeSocket> map = gamesClientImpl.Iv;
            final String s4 = s;
            final RealTimeSocketImpl realTimeSocketImpl2 = realTimeSocketImpl;
            map.put(s4, realTimeSocketImpl2);
            return realTimeSocketImpl;
        }
        catch (RemoteException ex2) {
            GamesLog.h("GamesClientImpl", "Unable to create socket. Service died.");
            return null;
        }
        try {
            final LocalSocket localSocket2 = localSocket;
            final String s2 = ae;
            final LocalSocketAddress localSocketAddress = new LocalSocketAddress(s2);
            localSocket2.connect(localSocketAddress);
            final LocalSocket localSocket3 = localSocket;
            final String s3 = s;
            final RealTimeSocketImpl realTimeSocketImpl = new RealTimeSocketImpl(localSocket3, s3);
            final GamesClientImpl gamesClientImpl = this;
            final Map<String, RealTimeSocket> map = gamesClientImpl.Iv;
            final String s4 = s;
            final RealTimeSocketImpl realTimeSocketImpl2 = realTimeSocketImpl;
            map.put(s4, realTimeSocketImpl2);
            return realTimeSocketImpl;
        }
        catch (IOException ex) {
            GamesLog.h("GamesClientImpl", "connect() call failed on socket: " + ex.getMessage());
            return null;
        }
    }
    
    private void gE() {
        for (final RealTimeSocket realTimeSocket : this.Iv.values()) {
            try {
                realTimeSocket.close();
            }
            catch (IOException ex) {
                GamesLog.a("GamesClientImpl", "IOException:", ex);
            }
        }
        this.Iv.clear();
    }
    
    private void gk() {
        this.Iw = null;
    }
    
    protected IGamesService L(final IBinder binder) {
        return IGamesService.Stub.N(binder);
    }
    
    public int a(final RealTimeMultiplayer.ReliableMessageSentCallback reliableMessageSentCallback, final byte[] array, final String s, final String s2) {
        try {
            return this.eM().a(new RealTimeReliableMessageBinderCallbacks(reliableMessageSentCallback), array, s, s2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return -1;
        }
    }
    
    public int a(final byte[] array, final String s, final String[] array2) {
        fq.b(array2, "Participant IDs must not be null");
        try {
            return this.eM().b(array, s, array2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return -1;
        }
    }
    
    public Intent a(final int n, final int n2, final boolean b) {
        try {
            return this.eM().a(n, n2, b);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent a(final int n, final byte[] array, final int n2, final Bitmap bitmap, final String s) {
        try {
            final Intent a = this.eM().a(n, array, n2, s);
            fq.b(bitmap, "Must provide a non null icon");
            a.putExtra("com.google.android.gms.games.REQUEST_ITEM_ICON", (Parcelable)bitmap);
            return a;
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent a(final Room room, final int n) {
        try {
            return this.eM().a(((Freezable<RoomEntity>)room).freeze(), n);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return null;
        }
    }
    
    @Override
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        if (n == 0 && bundle != null) {
            this.Iz = bundle.getBoolean("show_welcome_popup");
        }
        super.a(n, binder, bundle);
    }
    
    public void a(final IBinder binder, final Bundle bundle) {
        if (!this.isConnected()) {
            return;
        }
        try {
            this.eM().a(binder, bundle);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Requests.LoadRequestsResult> d, final int n, final int n2, final int n3) {
        try {
            this.eM().a(new RequestsLoadedBinderCallbacks(d), n, n2, n3);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<GamesMetadata.LoadExtendedGamesResult> d, final int n, final int n2, final boolean b, final boolean b2) {
        try {
            this.eM().a(new ExtendedGamesLoadedBinderCallback(d), n, n2, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Players.LoadPlayersResult> d, final int n, final boolean b, final boolean b2) {
        try {
            this.eM().a(new PlayersLoadedBinderCallback(d), n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.LoadMatchesResult> d, final int n, final int[] array) {
        try {
            this.eM().a(new TurnBasedMatchesLoadedBinderCallbacks(d), n, array);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Leaderboards.LoadScoresResult> d, final LeaderboardScoreBuffer leaderboardScoreBuffer, final int n, final int n2) {
        try {
            this.eM().a(new LeaderboardScoresLoadedBinderCallback(d), leaderboardScoreBuffer.hD().hE(), n, n2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.InitiateMatchResult> d, final TurnBasedMatchConfig turnBasedMatchConfig) {
        try {
            this.eM().a(new TurnBasedMatchInitiatedBinderCallbacks(d), turnBasedMatchConfig.getVariant(), turnBasedMatchConfig.getMinPlayers(), turnBasedMatchConfig.getInvitedPlayerIds(), turnBasedMatchConfig.getAutoMatchCriteria());
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Players.LoadPlayersResult> d, final String s) {
        try {
            this.eM().a(new PlayersLoadedBinderCallback(d), s);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Achievements.UpdateAchievementResult> d, final String s, final int n) {
        Label_0036: {
            if (d != null) {
                break Label_0036;
            }
            IGamesCallbacks gamesCallbacks = null;
            try {
                while (true) {
                    this.eM().a(gamesCallbacks, s, n, this.Iy.gU(), this.Iy.gT());
                    return;
                    gamesCallbacks = new AchievementUpdatedBinderCallback(d);
                    continue;
                }
            }
            catch (RemoteException ex) {
                GamesLog.g("GamesClientImpl", "service died");
            }
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Leaderboards.LoadScoresResult> d, final String s, final int n, final int n2, final int n3, final boolean b) {
        try {
            this.eM().a(new LeaderboardScoresLoadedBinderCallback(d), s, n, n2, n3, b);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Players.LoadPlayersResult> d, final String s, final int n, final boolean b) {
        try {
            this.eM().a(new PlayersLoadedBinderCallback(d), s, n, b);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Players.LoadPlayersResult> d, final String s, final int n, final boolean b, final boolean b2) {
        if (!s.equals("playedWith")) {
            throw new IllegalArgumentException("Invalid player collection: " + s);
        }
        try {
            this.eM().d(new PlayersLoadedBinderCallback(d), s, n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<GamesMetadata.LoadExtendedGamesResult> d, final String s, final int n, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        try {
            this.eM().a(new ExtendedGamesLoadedBinderCallback(d), s, n, b, b2, b3, b4);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.LoadMatchesResult> d, final String s, final int n, final int[] array) {
        try {
            this.eM().a(new TurnBasedMatchesLoadedBinderCallbacks(d), s, n, array);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Leaderboards.SubmitScoreResult> d, final String s, final long n, final String s2) {
        Label_0024: {
            if (d != null) {
                break Label_0024;
            }
            IGamesCallbacks gamesCallbacks = null;
            try {
                while (true) {
                    this.eM().a(gamesCallbacks, s, n, s2);
                    return;
                    gamesCallbacks = new SubmitScoreBinderCallbacks(d);
                    continue;
                }
            }
            catch (RemoteException ex) {
                GamesLog.g("GamesClientImpl", "service died");
            }
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.LeaveMatchResult> d, final String s, final String s2) {
        try {
            this.eM().c(new TurnBasedMatchLeftBinderCallbacks(d), s, s2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Leaderboards.LoadPlayerScoreResult> d, final String s, final String s2, final int n, final int n2) {
        try {
            this.eM().a(new PlayerLeaderboardScoreLoadedBinderCallback(d), s, s2, n, n2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Requests.LoadRequestsResult> d, final String s, final String s2, final int n, final int n2, final int n3) {
        try {
            this.eM().a(new RequestsLoadedBinderCallbacks(d), s, s2, n, n2, n3);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Leaderboards.LoadScoresResult> d, final String s, final String s2, final int n, final int n2, final int n3, final boolean b) {
        try {
            this.eM().a(new LeaderboardScoresLoadedBinderCallback(d), s, s2, n, n2, n3, b);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Players.LoadPlayersResult> d, final String s, final String s2, final int n, final boolean b, final boolean b2) {
        if (!s.equals("playedWith") && !s.equals("circled")) {
            throw new IllegalArgumentException("Invalid player collection: " + s);
        }
        try {
            this.eM().a(new PlayersLoadedBinderCallback(d), s, s2, n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Leaderboards.LeaderboardMetadataResult> d, final String s, final String s2, final boolean b) {
        try {
            this.eM().b(new LeaderboardsLoadedBinderCallback(d), s, s2, b);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Requests.UpdateRequestsResult> d, final String s, final String s2, final String[] array) {
        try {
            this.eM().a(new RequestsUpdatedBinderCallbacks(d), s, s2, array);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Leaderboards.LeaderboardMetadataResult> d, final String s, final boolean b) {
        try {
            this.eM().c(new LeaderboardsLoadedBinderCallback(d), s, b);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.UpdateMatchResult> d, final String s, final byte[] array, final String s2, final ParticipantResult[] array2) {
        try {
            this.eM().a(new TurnBasedMatchUpdatedBinderCallbacks(d), s, array, s2, array2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.UpdateMatchResult> d, final String s, final byte[] array, final ParticipantResult[] array2) {
        try {
            this.eM().a(new TurnBasedMatchUpdatedBinderCallbacks(d), s, array, array2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Requests.SendRequestResult> d, final String s, final String[] array, final int n, final byte[] array2, final int n2) {
        try {
            this.eM().a(new RequestSentBinderCallbacks(d), s, array, n, array2, n2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Players.LoadPlayersResult> d, final boolean b) {
        try {
            this.eM().c(new PlayersLoadedBinderCallback(d), b);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Status> d, final boolean b, final Bundle bundle) {
        try {
            this.eM().a(new ContactSettingsUpdatedBinderCallback(d), b, bundle);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final com.google.android.gms.common.api.a.d<Players.LoadPlayersResult> d, final String[] array) {
        try {
            this.eM().c(new PlayersLoadedBinderCallback(d), array);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final OnInvitationReceivedListener onInvitationReceivedListener) {
        try {
            this.eM().a(new InvitationReceivedBinderCallback(onInvitationReceivedListener), this.IE);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final RoomConfig roomConfig) {
        try {
            this.eM().a(new RoomBinderCallbacks(roomConfig.getRoomUpdateListener(), roomConfig.getRoomStatusUpdateListener(), roomConfig.getMessageReceivedListener()), (IBinder)this.IC, roomConfig.getVariant(), roomConfig.getInvitedPlayerIds(), roomConfig.getAutoMatchCriteria(), roomConfig.isSocketEnabled(), this.IE);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final RoomUpdateListener roomUpdateListener, final String s) {
        try {
            this.eM().c(new RoomBinderCallbacks(roomUpdateListener), s);
            this.gE();
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
        try {
            this.eM().b(new MatchUpdateReceivedBinderCallback(onTurnBasedMatchUpdateReceivedListener), this.IE);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void a(final OnRequestReceivedListener onRequestReceivedListener) {
        try {
            this.eM().c(new RequestReceivedBinderCallback(onRequestReceivedListener), this.IE);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    @Override
    protected void a(final fm fm, final e e) throws RemoteException {
        final String string = this.getContext().getResources().getConfiguration().locale.toString();
        final Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.games.key.isHeadless", this.IF);
        bundle.putBoolean("com.google.android.gms.games.key.showConnectingPopup", this.IA);
        bundle.putInt("com.google.android.gms.games.key.connectingPopupGravity", this.IB);
        bundle.putBoolean("com.google.android.gms.games.key.retryingSignIn", this.IH);
        bundle.putInt("com.google.android.gms.games.key.sdkVariant", this.IG);
        fm.a(e, 4452000, this.getContext().getPackageName(), this.wG, this.eL(), this.Iu, this.Iy.gU(), string, bundle);
    }
    
    public Intent aA(final String s) {
        try {
            return this.eM().aA(s);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public void aB(final String s) {
        try {
            this.eM().aI(s);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void aX(final int gravity) {
        this.Iy.setGravity(gravity);
    }
    
    public void aY(final int n) {
        try {
            this.eM().aY(n);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public Intent b(final int n, final int n2, final boolean b) {
        try {
            return this.eM().b(n, n2, b);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.d<Status> d) {
        try {
            this.eM().a(new SignOutCompleteBinderCallbacks(d));
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.d<Players.LoadPlayersResult> d, final int n, final boolean b, final boolean b2) {
        try {
            this.eM().b(new PlayersLoadedBinderCallback(d), n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.d<Achievements.UpdateAchievementResult> d, final String s) {
        Label_0035: {
            if (d != null) {
                break Label_0035;
            }
            IGamesCallbacks gamesCallbacks = null;
            try {
                while (true) {
                    this.eM().a(gamesCallbacks, s, this.Iy.gU(), this.Iy.gT());
                    return;
                    gamesCallbacks = new AchievementUpdatedBinderCallback(d);
                    continue;
                }
            }
            catch (RemoteException ex) {
                GamesLog.g("GamesClientImpl", "service died");
            }
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.d<Achievements.UpdateAchievementResult> d, final String s, final int n) {
        Label_0036: {
            if (d != null) {
                break Label_0036;
            }
            IGamesCallbacks gamesCallbacks = null;
            try {
                while (true) {
                    this.eM().b(gamesCallbacks, s, n, this.Iy.gU(), this.Iy.gT());
                    return;
                    gamesCallbacks = new AchievementUpdatedBinderCallback(d);
                    continue;
                }
            }
            catch (RemoteException ex) {
                GamesLog.g("GamesClientImpl", "service died");
            }
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.d<Leaderboards.LoadScoresResult> d, final String s, final int n, final int n2, final int n3, final boolean b) {
        try {
            this.eM().b(new LeaderboardScoresLoadedBinderCallback(d), s, n, n2, n3, b);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.d<GamesMetadata.LoadExtendedGamesResult> d, final String s, final int n, final boolean b, final boolean b2) {
        try {
            this.eM().a(new ExtendedGamesLoadedBinderCallback(d), s, n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.InitiateMatchResult> d, final String s, final String s2) {
        try {
            this.eM().d(new TurnBasedMatchInitiatedBinderCallbacks(d), s, s2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.d<Leaderboards.LoadScoresResult> d, final String s, final String s2, final int n, final int n2, final int n3, final boolean b) {
        try {
            this.eM().b(new LeaderboardScoresLoadedBinderCallback(d), s, s2, n, n2, n3, b);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.d<Achievements.LoadAchievementsResult> d, final String s, final String s2, final boolean b) {
        try {
            this.eM().a(new AchievementsLoadedBinderCallback(d), s, s2, b);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.d<Leaderboards.LeaderboardMetadataResult> d, final String s, final boolean b) {
        try {
            this.eM().d(new LeaderboardsLoadedBinderCallback(d), s, b);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.d<Leaderboards.LeaderboardMetadataResult> d, final boolean b) {
        try {
            this.eM().b(new LeaderboardsLoadedBinderCallback(d), b);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void b(final com.google.android.gms.common.api.a.d<Requests.UpdateRequestsResult> d, final String[] array) {
        try {
            this.eM().a(new RequestsUpdatedBinderCallbacks(d), array);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void b(final RoomConfig roomConfig) {
        try {
            this.eM().a(new RoomBinderCallbacks(roomConfig.getRoomUpdateListener(), roomConfig.getRoomStatusUpdateListener(), roomConfig.getMessageReceivedListener()), (IBinder)this.IC, roomConfig.getInvitationId(), roomConfig.isSocketEnabled(), this.IE);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    @Override
    protected void b(final String... array) {
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
            fq.a(!b2, (Object)String.format("Cannot have both %s and %s!", "https://www.googleapis.com/auth/games", "https://www.googleapis.com/auth/games.firstparty"));
            return;
        }
        fq.a(b2, (Object)String.format("Games APIs requires %s to function.", "https://www.googleapis.com/auth/games"));
    }
    
    @Override
    protected String bg() {
        return "com.google.android.gms.games.service.START";
    }
    
    @Override
    protected String bh() {
        return "com.google.android.gms.games.internal.IGamesService";
    }
    
    public void c(final com.google.android.gms.common.api.a.d<Invitations.LoadInvitationsResult> d, final int n) {
        try {
            this.eM().a(new InvitationsLoadedBinderCallback(d), n);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void c(final com.google.android.gms.common.api.a.d<Players.LoadPlayersResult> d, final int n, final boolean b, final boolean b2) {
        try {
            this.eM().c(new PlayersLoadedBinderCallback(d), n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void c(final com.google.android.gms.common.api.a.d<Achievements.UpdateAchievementResult> d, final String s) {
        Label_0035: {
            if (d != null) {
                break Label_0035;
            }
            IGamesCallbacks gamesCallbacks = null;
            try {
                while (true) {
                    this.eM().b(gamesCallbacks, s, this.Iy.gU(), this.Iy.gT());
                    return;
                    gamesCallbacks = new AchievementUpdatedBinderCallback(d);
                    continue;
                }
            }
            catch (RemoteException ex) {
                GamesLog.g("GamesClientImpl", "service died");
            }
        }
    }
    
    public void c(final com.google.android.gms.common.api.a.d<Invitations.LoadInvitationsResult> d, final String s, final int n) {
        try {
            this.eM().b(new InvitationsLoadedBinderCallback(d), s, n, false);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void c(final com.google.android.gms.common.api.a.d<GamesMetadata.LoadExtendedGamesResult> d, final String s, final int n, final boolean b, final boolean b2) {
        try {
            this.eM().c(new ExtendedGamesLoadedBinderCallback(d), s, n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void c(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.InitiateMatchResult> d, final String s, final String s2) {
        try {
            this.eM().e(new TurnBasedMatchInitiatedBinderCallbacks(d), s, s2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void c(final com.google.android.gms.common.api.a.d<Notifications.GameMuteStatusChangeResult> d, final String s, final boolean b) {
        try {
            this.eM().a(new GameMuteStatusChangedBinderCallback(d), s, b);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void c(final com.google.android.gms.common.api.a.d<Achievements.LoadAchievementsResult> d, final boolean b) {
        try {
            this.eM().a(new AchievementsLoadedBinderCallback(d), b);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void c(final com.google.android.gms.common.api.a.d<Requests.UpdateRequestsResult> d, final String[] array) {
        try {
            this.eM().b(new RequestsUpdatedBinderCallbacks(d), array);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    @Override
    public void connect() {
        this.gk();
        super.connect();
    }
    
    public int d(final byte[] array, final String s) {
        try {
            return this.eM().b(array, s, null);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return -1;
        }
    }
    
    public void d(final com.google.android.gms.common.api.a.d<Players.LoadPlayersResult> d, final int n, final boolean b, final boolean b2) {
        try {
            this.eM().e(new PlayersLoadedBinderCallback(d), n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void d(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.InitiateMatchResult> d, final String s) {
        try {
            this.eM().l(new TurnBasedMatchInitiatedBinderCallbacks(d), s);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void d(final com.google.android.gms.common.api.a.d<Requests.LoadRequestSummariesResult> d, final String s, final int n) {
        try {
            this.eM().a(new RequestSummariesLoadedBinderCallbacks(d), s, n);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void d(final com.google.android.gms.common.api.a.d<Players.LoadPlayersResult> d, final String s, final int n, final boolean b, final boolean b2) {
        try {
            this.eM().b(new PlayersLoadedBinderCallback(d), s, n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    @Override
    public Bundle dG() {
        try {
            final Bundle dg = this.eM().dG();
            if (dg != null) {
                dg.setClassLoader(GamesClientImpl.class.getClassLoader());
            }
            return dg;
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return null;
        }
    }
    
    @Override
    public void disconnect() {
        this.Iz = false;
        while (true) {
            if (!this.isConnected()) {
                break Label_0036;
            }
            try {
                final IGamesService gamesService = this.eM();
                gamesService.gF();
                gamesService.o(this.IE);
                this.gE();
                super.disconnect();
            }
            catch (RemoteException ex) {
                GamesLog.g("GamesClientImpl", "Failed to notify client disconnect.");
                continue;
            }
            break;
        }
    }
    
    public void e(final com.google.android.gms.common.api.a.d<Players.LoadExtendedPlayersResult> d, final int n, final boolean b, final boolean b2) {
        try {
            this.eM().d(new ExtendedPlayersLoadedBinderCallback(d), n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void e(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.InitiateMatchResult> d, final String s) {
        try {
            this.eM().m(new TurnBasedMatchInitiatedBinderCallbacks(d), s);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void f(final View view) {
        this.Iy.g(view);
    }
    
    public void f(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.LeaveMatchResult> d, final String s) {
        try {
            this.eM().o(new TurnBasedMatchLeftBinderCallbacks(d), s);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void g(final com.google.android.gms.common.api.a.d<GamesMetadata.LoadGamesResult> d) {
        try {
            this.eM().d(new GamesLoadedBinderCallback(d));
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void g(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.CancelMatchResult> d, final String s) {
        try {
            this.eM().n(new TurnBasedMatchCanceledBinderCallbacks(d), s);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public int gA() {
        try {
            return this.eM().gA();
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return 2;
        }
    }
    
    public Intent gB() {
        try {
            return this.eM().gB();
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public int gC() {
        try {
            return this.eM().gC();
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return 2;
        }
    }
    
    public int gD() {
        try {
            return this.eM().gD();
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return 2;
        }
    }
    
    public void gF() {
        if (!this.isConnected()) {
            return;
        }
        try {
            this.eM().gF();
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public String gl() {
        try {
            return this.eM().gl();
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public String gm() {
        try {
            return this.eM().gm();
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Player gn() {
        this.bT();
        synchronized (this) {
            Object iw = this.Iw;
            Label_0063: {
                if (iw != null) {
                    break Label_0063;
                }
                try {
                    iw = new PlayerBuffer(this.eM().gG());
                    try {
                        if (((DataBuffer)iw).getCount() > 0) {
                            this.Iw = (PlayerEntity)((PlayerBuffer)iw).get(0).freeze();
                        }
                        ((PlayerBuffer)iw).close();
                        // monitorexit(this)
                        return this.Iw;
                    }
                    finally {
                        ((PlayerBuffer)iw).close();
                    }
                }
                catch (RemoteException ex) {
                    GamesLog.g("GamesClientImpl", "service died");
                }
            }
        }
    }
    
    public Game go() {
        this.bT();
        synchronized (this) {
            Object ix = this.Ix;
            Label_0063: {
                if (ix != null) {
                    break Label_0063;
                }
                try {
                    ix = new GameBuffer(this.eM().gI());
                    try {
                        if (((DataBuffer)ix).getCount() > 0) {
                            this.Ix = (GameEntity)((GameBuffer)ix).get(0).freeze();
                        }
                        ((GameBuffer)ix).close();
                        // monitorexit(this)
                        return this.Ix;
                    }
                    finally {
                        ((GameBuffer)ix).close();
                    }
                }
                catch (RemoteException ex) {
                    GamesLog.g("GamesClientImpl", "service died");
                }
            }
        }
    }
    
    public Intent gp() {
        try {
            return this.eM().gp();
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent gq() {
        try {
            return this.eM().gq();
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent gr() {
        try {
            return this.eM().gr();
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent gs() {
        try {
            return this.eM().gs();
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public void gt() {
        try {
            this.eM().p(this.IE);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void gu() {
        try {
            this.eM().q(this.IE);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void gv() {
        try {
            this.eM().r(this.IE);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public Intent gw() {
        try {
            return this.eM().gw();
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent gx() {
        try {
            return this.eM().gx();
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public int gy() {
        try {
            return this.eM().gy();
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return 4368;
        }
    }
    
    public String gz() {
        try {
            return this.eM().gz();
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public void h(final com.google.android.gms.common.api.a.d<Players.LoadOwnerCoverPhotoUrisResult> d) {
        try {
            this.eM().j(new OwnerCoverPhotoUrisLoadedBinderCallback(d));
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void h(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.LoadMatchResult> d, final String s) {
        try {
            this.eM().p(new TurnBasedMatchLoadedBinderCallbacks(d), s);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public RealTimeSocket i(final String s, final String s2) {
        if (s2 == null || !ParticipantUtils.aV(s2)) {
            throw new IllegalArgumentException("Bad participant ID");
        }
        final RealTimeSocket realTimeSocket = this.Iv.get(s2);
        if (realTimeSocket != null) {
            final RealTimeSocket ac = realTimeSocket;
            if (!realTimeSocket.isClosed()) {
                return ac;
            }
        }
        return this.aC(s2);
    }
    
    public void i(final com.google.android.gms.common.api.a.d<Acls.LoadAclResult> d) {
        try {
            this.eM().h(new NotifyAclLoadedBinderCallback(d));
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void i(final com.google.android.gms.common.api.a.d<GamesMetadata.LoadExtendedGamesResult> d, final String s) {
        try {
            this.eM().e(new ExtendedGamesLoadedBinderCallback(d), s);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void j(final com.google.android.gms.common.api.a.d<Notifications.ContactSettingLoadResult> d) {
        try {
            this.eM().i(new ContactSettingsLoadedBinderCallback(d));
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void j(final com.google.android.gms.common.api.a.d<GamesMetadata.LoadGameInstancesResult> d, final String s) {
        try {
            this.eM().f(new GameInstancesLoadedBinderCallback(d), s);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void k(final com.google.android.gms.common.api.a.d<GamesMetadata.LoadGameSearchSuggestionsResult> d, final String s) {
        try {
            this.eM().q(new GameSearchSuggestionsLoadedBinderCallback(d), s);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void l(final com.google.android.gms.common.api.a.d<Invitations.LoadInvitationsResult> d, final String s) {
        try {
            this.eM().k(new InvitationsLoadedBinderCallback(d), s);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void l(final String s, final int n) {
        try {
            this.eM().l(s, n);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void m(final com.google.android.gms.common.api.a.d<Status> d, final String s) {
        try {
            this.eM().j(new NotifyAclUpdatedBinderCallback(d), s);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void m(final String s, final int n) {
        try {
            this.eM().m(s, n);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    public void n(final com.google.android.gms.common.api.a.d<Notifications.GameMuteStatusLoadResult> d, final String s) {
        try {
            this.eM().i(new GameMuteStatusLoadedBinderCallback(d), s);
        }
        catch (RemoteException ex) {
            GamesLog.g("GamesClientImpl", "service died");
        }
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        if (this.Iz) {
            this.Iy.gS();
            this.Iz = false;
        }
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.Iz = false;
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
    }
    
    abstract class AbstractPeerStatusCallback extends AbstractRoomStatusCallback
    {
        private final ArrayList<String> II;
        
        AbstractPeerStatusCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder);
            this.II = new ArrayList<String>();
            for (int i = 0; i < array.length; ++i) {
                this.II.add(array[i]);
            }
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            this.a(roomStatusUpdateListener, room, this.II);
        }
        
        protected abstract void a(final RoomStatusUpdateListener p0, final Room p1, final ArrayList<String> p2);
    }
    
    abstract class AbstractRoomCallback extends d<RoomUpdateListener>
    {
        AbstractRoomCallback(final RoomUpdateListener roomUpdateListener, final DataHolder dataHolder) {
            super(roomUpdateListener, dataHolder);
        }
        
        protected void a(final RoomUpdateListener roomUpdateListener, final DataHolder dataHolder) {
            this.a(roomUpdateListener, GamesClientImpl.this.G(dataHolder), dataHolder.getStatusCode());
        }
        
        protected abstract void a(final RoomUpdateListener p0, final Room p1, final int p2);
    }
    
    abstract class AbstractRoomStatusCallback extends d<RoomStatusUpdateListener>
    {
        AbstractRoomStatusCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            super(roomStatusUpdateListener, dataHolder);
        }
        
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            this.a(roomStatusUpdateListener, GamesClientImpl.this.G(dataHolder));
        }
        
        protected abstract void a(final RoomStatusUpdateListener p0, final Room p1);
    }
    
    final class AchievementUpdatedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Achievements.UpdateAchievementResult> wH;
        
        AchievementUpdatedBinderCallback(final com.google.android.gms.common.api.a.d<Achievements.UpdateAchievementResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Achievements.UpdateAchievementResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void e(final int n, final String s) {
            GamesClientImpl.this.a((b<?>)new AchievementUpdatedCallback(this.wH, n, s));
        }
    }
    
    final class AchievementUpdatedCallback extends b<com.google.android.gms.common.api.a.d<UpdateAchievementResult>> implements UpdateAchievementResult
    {
        private final String IK;
        private final Status wJ;
        
        AchievementUpdatedCallback(final com.google.android.gms.common.api.a.d<UpdateAchievementResult> d, final int n, final String ik) {
            super(d);
            this.wJ = new Status(n);
            this.IK = ik;
        }
        
        protected void c(final com.google.android.gms.common.api.a.d<UpdateAchievementResult> d) {
            d.b(this);
        }
        
        @Override
        protected void dx() {
        }
        
        @Override
        public String getAchievementId() {
            return this.IK;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
    }
    
    final class AchievementsLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Achievements.LoadAchievementsResult> wH;
        
        AchievementsLoadedBinderCallback(final com.google.android.gms.common.api.a.d<Achievements.LoadAchievementsResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Achievements.LoadAchievementsResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void b(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new AchievementsLoadedCallback(this.wH, dataHolder));
        }
    }
    
    final class AchievementsLoadedCallback extends ResultDataHolderCallback<com.google.android.gms.common.api.a.d<LoadAchievementsResult>> implements LoadAchievementsResult
    {
        private final AchievementBuffer IL;
        
        AchievementsLoadedCallback(final com.google.android.gms.common.api.a.d<LoadAchievementsResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
            this.IL = new AchievementBuffer(dataHolder);
        }
        
        protected void a(final com.google.android.gms.common.api.a.d<LoadAchievementsResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
        
        @Override
        public AchievementBuffer getAchievements() {
            return this.IL;
        }
    }
    
    final class ConnectedToRoomCallback extends AbstractRoomStatusCallback
    {
        ConnectedToRoomCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            super(roomStatusUpdateListener, dataHolder);
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            roomStatusUpdateListener.onConnectedToRoom(room);
        }
    }
    
    final class ContactSettingsLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Notifications.ContactSettingLoadResult> wH;
        
        ContactSettingsLoadedBinderCallback(final com.google.android.gms.common.api.a.d<Notifications.ContactSettingLoadResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Notifications.ContactSettingLoadResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void B(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new ContactSettingsLoadedCallback(this.wH, dataHolder));
        }
    }
    
    final class ContactSettingsLoadedCallback extends ResultDataHolderCallback<com.google.android.gms.common.api.a.d<ContactSettingLoadResult>> implements ContactSettingLoadResult
    {
        ContactSettingsLoadedCallback(final com.google.android.gms.common.api.a.d<ContactSettingLoadResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
        }
        
        protected void a(final com.google.android.gms.common.api.a.d<ContactSettingLoadResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
    }
    
    final class ContactSettingsUpdatedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Status> wH;
        
        ContactSettingsUpdatedBinderCallback(final com.google.android.gms.common.api.a.d<Status> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Status>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void aV(final int n) {
            GamesClientImpl.this.a((b<?>)new ContactSettingsUpdatedCallback(this.wH, n));
        }
    }
    
    final class ContactSettingsUpdatedCallback extends b<com.google.android.gms.common.api.a.d<Status>>
    {
        private final Status wJ;
        
        ContactSettingsUpdatedCallback(final com.google.android.gms.common.api.a.d<Status> d, final int n) {
            super(d);
            this.wJ = new Status(n);
        }
        
        protected void c(final com.google.android.gms.common.api.a.d<Status> d) {
            d.b(this.wJ);
        }
        
        @Override
        protected void dx() {
        }
    }
    
    final class DisconnectedFromRoomCallback extends AbstractRoomStatusCallback
    {
        DisconnectedFromRoomCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            super(roomStatusUpdateListener, dataHolder);
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            roomStatusUpdateListener.onDisconnectedFromRoom(room);
        }
    }
    
    final class ExtendedGamesLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<GamesMetadata.LoadExtendedGamesResult> wH;
        
        ExtendedGamesLoadedBinderCallback(final com.google.android.gms.common.api.a.d<GamesMetadata.LoadExtendedGamesResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<GamesMetadata.LoadExtendedGamesResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void h(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new ExtendedGamesLoadedCallback(this.wH, dataHolder));
        }
    }
    
    final class ExtendedGamesLoadedCallback extends ResultDataHolderCallback<com.google.android.gms.common.api.a.d<LoadExtendedGamesResult>> implements LoadExtendedGamesResult
    {
        private final ExtendedGameBuffer IM;
        
        ExtendedGamesLoadedCallback(final com.google.android.gms.common.api.a.d<LoadExtendedGamesResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
            this.IM = new ExtendedGameBuffer(dataHolder);
        }
        
        protected void a(final com.google.android.gms.common.api.a.d<LoadExtendedGamesResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
    }
    
    final class ExtendedPlayersLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Players.LoadExtendedPlayersResult> wH;
        
        ExtendedPlayersLoadedBinderCallback(final com.google.android.gms.common.api.a.d<Players.LoadExtendedPlayersResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Players.LoadExtendedPlayersResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void f(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new ExtendedPlayersLoadedCallback(this.wH, dataHolder));
        }
    }
    
    final class ExtendedPlayersLoadedCallback extends ResultDataHolderCallback<com.google.android.gms.common.api.a.d<LoadExtendedPlayersResult>> implements LoadExtendedPlayersResult
    {
        private final ExtendedPlayerBuffer IN;
        
        ExtendedPlayersLoadedCallback(final com.google.android.gms.common.api.a.d<LoadExtendedPlayersResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
            this.IN = new ExtendedPlayerBuffer(dataHolder);
        }
        
        protected void a(final com.google.android.gms.common.api.a.d<LoadExtendedPlayersResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
    }
    
    final class GameInstancesLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<GamesMetadata.LoadGameInstancesResult> wH;
        
        GameInstancesLoadedBinderCallback(final com.google.android.gms.common.api.a.d<GamesMetadata.LoadGameInstancesResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<GamesMetadata.LoadGameInstancesResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void i(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new GameInstancesLoadedCallback(this.wH, dataHolder));
        }
    }
    
    final class GameInstancesLoadedCallback extends ResultDataHolderCallback<com.google.android.gms.common.api.a.d<LoadGameInstancesResult>> implements LoadGameInstancesResult
    {
        private final GameInstanceBuffer IO;
        
        GameInstancesLoadedCallback(final com.google.android.gms.common.api.a.d<LoadGameInstancesResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
            this.IO = new GameInstanceBuffer(dataHolder);
        }
        
        protected void a(final com.google.android.gms.common.api.a.d<LoadGameInstancesResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
    }
    
    final class GameMuteStatusChangedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Notifications.GameMuteStatusChangeResult> wH;
        
        GameMuteStatusChangedBinderCallback(final com.google.android.gms.common.api.a.d<Notifications.GameMuteStatusChangeResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Notifications.GameMuteStatusChangeResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void a(final int n, final String s, final boolean b) {
            GamesClientImpl.this.a((b<?>)new GameMuteStatusChangedCallback(this.wH, n, s, b));
        }
    }
    
    final class GameMuteStatusChangedCallback extends b<com.google.android.gms.common.api.a.d<GameMuteStatusChangeResult>> implements GameMuteStatusChangeResult
    {
        private final String IP;
        private final boolean IQ;
        private final Status wJ;
        
        public GameMuteStatusChangedCallback(final com.google.android.gms.common.api.a.d<GameMuteStatusChangeResult> d, final int n, final String ip, final boolean iq) {
            super(d);
            this.wJ = new Status(n);
            this.IP = ip;
            this.IQ = iq;
        }
        
        protected void c(final com.google.android.gms.common.api.a.d<GameMuteStatusChangeResult> d) {
            d.b(this);
        }
        
        @Override
        protected void dx() {
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
    }
    
    final class GameMuteStatusLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Notifications.GameMuteStatusLoadResult> wH;
        
        GameMuteStatusLoadedBinderCallback(final com.google.android.gms.common.api.a.d<Notifications.GameMuteStatusLoadResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Notifications.GameMuteStatusLoadResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void z(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new GameMuteStatusLoadedCallback(this.wH, dataHolder));
        }
    }
    
    final class GameMuteStatusLoadedCallback extends b<com.google.android.gms.common.api.a.d<GameMuteStatusLoadResult>> implements GameMuteStatusLoadResult
    {
        private final String IP;
        private final boolean IQ;
        private final Status wJ;
        
        public GameMuteStatusLoadedCallback(final com.google.android.gms.common.api.a.d<GameMuteStatusLoadResult> d, final DataHolder dataHolder) {
            super(d);
            try {
                this.wJ = new Status(dataHolder.getStatusCode());
                if (dataHolder.getCount() > 0) {
                    this.IP = dataHolder.getString("external_game_id", 0, 0);
                    this.IQ = dataHolder.getBoolean("muted", 0, 0);
                }
                else {
                    this.IP = null;
                    this.IQ = false;
                }
            }
            finally {
                dataHolder.close();
            }
        }
        
        protected void c(final com.google.android.gms.common.api.a.d<GameMuteStatusLoadResult> d) {
            d.b(this);
        }
        
        @Override
        protected void dx() {
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
    }
    
    final class GameSearchSuggestionsLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<GamesMetadata.LoadGameSearchSuggestionsResult> wH;
        
        GameSearchSuggestionsLoadedBinderCallback(final com.google.android.gms.common.api.a.d<GamesMetadata.LoadGameSearchSuggestionsResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<GamesMetadata.LoadGameSearchSuggestionsResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void j(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new GameSearchSuggestionsLoadedCallback(this.wH, dataHolder));
        }
    }
    
    final class GameSearchSuggestionsLoadedCallback extends ResultDataHolderCallback<com.google.android.gms.common.api.a.d<LoadGameSearchSuggestionsResult>> implements LoadGameSearchSuggestionsResult
    {
        private final DataHolder IR;
        
        GameSearchSuggestionsLoadedCallback(final com.google.android.gms.common.api.a.d<LoadGameSearchSuggestionsResult> d, final DataHolder ir) {
            super(d, ir);
            this.IR = ir;
        }
        
        protected void a(final com.google.android.gms.common.api.a.d<LoadGameSearchSuggestionsResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
    }
    
    final class GamesLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<GamesMetadata.LoadGamesResult> wH;
        
        GamesLoadedBinderCallback(final com.google.android.gms.common.api.a.d<GamesMetadata.LoadGamesResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<GamesMetadata.LoadGamesResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void g(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new GamesLoadedCallback(this.wH, dataHolder));
        }
    }
    
    final class GamesLoadedCallback extends ResultDataHolderCallback<com.google.android.gms.common.api.a.d<LoadGamesResult>> implements LoadGamesResult
    {
        private final GameBuffer IS;
        
        GamesLoadedCallback(final com.google.android.gms.common.api.a.d<LoadGamesResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
            this.IS = new GameBuffer(dataHolder);
        }
        
        protected void a(final com.google.android.gms.common.api.a.d<LoadGamesResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
        
        @Override
        public GameBuffer getGames() {
            return this.IS;
        }
    }
    
    final class InvitationReceivedBinderCallback extends AbstractGamesCallbacks
    {
        private final OnInvitationReceivedListener IT;
        
        InvitationReceivedBinderCallback(final OnInvitationReceivedListener it) {
            this.IT = it;
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
                    GamesClientImpl.this.a((b<?>)new InvitationReceivedCallback(this.IT, invitation));
                }
            }
            finally {
                invitationBuffer.close();
            }
        }
        
        @Override
        public void onInvitationRemoved(final String s) {
            GamesClientImpl.this.a((b<?>)new InvitationRemovedCallback(this.IT, s));
        }
    }
    
    final class InvitationReceivedCallback extends b<OnInvitationReceivedListener>
    {
        private final Invitation IU;
        
        InvitationReceivedCallback(final OnInvitationReceivedListener onInvitationReceivedListener, final Invitation iu) {
            super(onInvitationReceivedListener);
            this.IU = iu;
        }
        
        protected void b(final OnInvitationReceivedListener onInvitationReceivedListener) {
            onInvitationReceivedListener.onInvitationReceived(this.IU);
        }
        
        @Override
        protected void dx() {
        }
    }
    
    final class InvitationRemovedCallback extends b<OnInvitationReceivedListener>
    {
        private final String IV;
        
        InvitationRemovedCallback(final OnInvitationReceivedListener onInvitationReceivedListener, final String iv) {
            super(onInvitationReceivedListener);
            this.IV = iv;
        }
        
        protected void b(final OnInvitationReceivedListener onInvitationReceivedListener) {
            onInvitationReceivedListener.onInvitationRemoved(this.IV);
        }
        
        @Override
        protected void dx() {
        }
    }
    
    final class InvitationsLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Invitations.LoadInvitationsResult> wH;
        
        InvitationsLoadedBinderCallback(final com.google.android.gms.common.api.a.d<Invitations.LoadInvitationsResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Invitations.LoadInvitationsResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void k(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new InvitationsLoadedCallback(this.wH, dataHolder));
        }
    }
    
    final class InvitationsLoadedCallback extends ResultDataHolderCallback<com.google.android.gms.common.api.a.d<LoadInvitationsResult>> implements LoadInvitationsResult
    {
        private final InvitationBuffer IW;
        
        InvitationsLoadedCallback(final com.google.android.gms.common.api.a.d<LoadInvitationsResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
            this.IW = new InvitationBuffer(dataHolder);
        }
        
        protected void a(final com.google.android.gms.common.api.a.d<LoadInvitationsResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
        
        @Override
        public InvitationBuffer getInvitations() {
            return this.IW;
        }
    }
    
    final class JoinedRoomCallback extends AbstractRoomCallback
    {
        public JoinedRoomCallback(final RoomUpdateListener roomUpdateListener, final DataHolder dataHolder) {
            super(roomUpdateListener, dataHolder);
        }
        
        public void a(final RoomUpdateListener roomUpdateListener, final Room room, final int n) {
            roomUpdateListener.onJoinedRoom(n, room);
        }
    }
    
    final class LeaderboardScoresLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Leaderboards.LoadScoresResult> wH;
        
        LeaderboardScoresLoadedBinderCallback(final com.google.android.gms.common.api.a.d<Leaderboards.LoadScoresResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Leaderboards.LoadScoresResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void a(final DataHolder dataHolder, final DataHolder dataHolder2) {
            GamesClientImpl.this.a((b<?>)new LeaderboardScoresLoadedCallback(this.wH, dataHolder, dataHolder2));
        }
    }
    
    final class LeaderboardScoresLoadedCallback extends ResultDataHolderCallback<com.google.android.gms.common.api.a.d<LoadScoresResult>> implements LoadScoresResult
    {
        private final LeaderboardEntity IX;
        private final LeaderboardScoreBuffer IY;
        
        LeaderboardScoresLoadedCallback(final com.google.android.gms.common.api.a.d<LoadScoresResult> d, final DataHolder dataHolder, final DataHolder dataHolder2) {
            super(d, dataHolder2);
            GamesClientImpl.this = (GamesClientImpl)new LeaderboardBuffer(dataHolder);
            try {
                if (((com.google.android.gms.common.data.d)GamesClientImpl.this).getCount() > 0) {
                    this.IX = ((Freezable<LeaderboardEntity>)((com.google.android.gms.common.data.d<Leaderboard>)GamesClientImpl.this).get(0)).freeze();
                }
                else {
                    this.IX = null;
                }
                ((DataBuffer)GamesClientImpl.this).close();
                this.IY = new LeaderboardScoreBuffer(dataHolder2);
            }
            finally {
                ((DataBuffer)GamesClientImpl.this).close();
            }
        }
        
        protected void a(final com.google.android.gms.common.api.a.d<LoadScoresResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
        
        @Override
        public Leaderboard getLeaderboard() {
            return this.IX;
        }
        
        @Override
        public LeaderboardScoreBuffer getScores() {
            return this.IY;
        }
    }
    
    final class LeaderboardsLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Leaderboards.LeaderboardMetadataResult> wH;
        
        LeaderboardsLoadedBinderCallback(final com.google.android.gms.common.api.a.d<Leaderboards.LeaderboardMetadataResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Leaderboards.LeaderboardMetadataResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void c(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new LeaderboardsLoadedCallback(this.wH, dataHolder));
        }
    }
    
    final class LeaderboardsLoadedCallback extends ResultDataHolderCallback<com.google.android.gms.common.api.a.d<LeaderboardMetadataResult>> implements LeaderboardMetadataResult
    {
        private final LeaderboardBuffer IZ;
        
        LeaderboardsLoadedCallback(final com.google.android.gms.common.api.a.d<LeaderboardMetadataResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
            this.IZ = new LeaderboardBuffer(dataHolder);
        }
        
        protected void a(final com.google.android.gms.common.api.a.d<LeaderboardMetadataResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
        
        @Override
        public LeaderboardBuffer getLeaderboards() {
            return this.IZ;
        }
    }
    
    final class LeftRoomCallback extends b<RoomUpdateListener>
    {
        private final int Ah;
        private final String Ja;
        
        LeftRoomCallback(final RoomUpdateListener roomUpdateListener, final int ah, final String ja) {
            super(roomUpdateListener);
            this.Ah = ah;
            this.Ja = ja;
        }
        
        public void a(final RoomUpdateListener roomUpdateListener) {
            roomUpdateListener.onLeftRoom(this.Ah, this.Ja);
        }
        
        @Override
        protected void dx() {
        }
    }
    
    final class MatchRemovedCallback extends b<OnTurnBasedMatchUpdateReceivedListener>
    {
        private final String Jb;
        
        MatchRemovedCallback(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener, final String jb) {
            super(onTurnBasedMatchUpdateReceivedListener);
            this.Jb = jb;
        }
        
        protected void b(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
            onTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchRemoved(this.Jb);
        }
        
        @Override
        protected void dx() {
        }
    }
    
    final class MatchUpdateReceivedBinderCallback extends AbstractGamesCallbacks
    {
        private final OnTurnBasedMatchUpdateReceivedListener Jc;
        
        MatchUpdateReceivedBinderCallback(final OnTurnBasedMatchUpdateReceivedListener jc) {
            this.Jc = jc;
        }
        
        @Override
        public void onTurnBasedMatchRemoved(final String s) {
            GamesClientImpl.this.a((b<?>)new MatchRemovedCallback(this.Jc, s));
        }
        
        @Override
        public void r(final DataHolder dataHolder) {
            final TurnBasedMatchBuffer turnBasedMatchBuffer = new TurnBasedMatchBuffer(dataHolder);
            TurnBasedMatch turnBasedMatch = null;
            try {
                if (turnBasedMatchBuffer.getCount() > 0) {
                    turnBasedMatch = turnBasedMatchBuffer.get(0).freeze();
                }
                turnBasedMatchBuffer.close();
                if (turnBasedMatch != null) {
                    GamesClientImpl.this.a((b<?>)new MatchUpdateReceivedCallback(this.Jc, turnBasedMatch));
                }
            }
            finally {
                turnBasedMatchBuffer.close();
            }
        }
    }
    
    final class MatchUpdateReceivedCallback extends b<OnTurnBasedMatchUpdateReceivedListener>
    {
        private final TurnBasedMatch Jd;
        
        MatchUpdateReceivedCallback(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener, final TurnBasedMatch jd) {
            super(onTurnBasedMatchUpdateReceivedListener);
            this.Jd = jd;
        }
        
        protected void b(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
            onTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchReceived(this.Jd);
        }
        
        @Override
        protected void dx() {
        }
    }
    
    final class MessageReceivedCallback extends b<RealTimeMessageReceivedListener>
    {
        private final RealTimeMessage Je;
        
        MessageReceivedCallback(final RealTimeMessageReceivedListener realTimeMessageReceivedListener, final RealTimeMessage je) {
            super(realTimeMessageReceivedListener);
            this.Je = je;
        }
        
        public void a(final RealTimeMessageReceivedListener realTimeMessageReceivedListener) {
            if (realTimeMessageReceivedListener != null) {
                realTimeMessageReceivedListener.onRealTimeMessageReceived(this.Je);
            }
        }
        
        @Override
        protected void dx() {
        }
    }
    
    final class NotifyAclLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Acls.LoadAclResult> wH;
        
        NotifyAclLoadedBinderCallback(final com.google.android.gms.common.api.a.d<Acls.LoadAclResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Acls.LoadAclResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void A(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new NotifyAclLoadedCallback(this.wH, dataHolder));
        }
    }
    
    final class NotifyAclLoadedCallback extends ResultDataHolderCallback<com.google.android.gms.common.api.a.d<LoadAclResult>> implements LoadAclResult
    {
        NotifyAclLoadedCallback(final com.google.android.gms.common.api.a.d<LoadAclResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
        }
        
        protected void a(final com.google.android.gms.common.api.a.d<LoadAclResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
    }
    
    final class NotifyAclUpdatedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Status> wH;
        
        NotifyAclUpdatedBinderCallback(final com.google.android.gms.common.api.a.d<Status> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Status>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void aU(final int n) {
            GamesClientImpl.this.a((b<?>)new NotifyAclUpdatedCallback(this.wH, n));
        }
    }
    
    final class NotifyAclUpdatedCallback extends b<com.google.android.gms.common.api.a.d<Status>>
    {
        private final Status wJ;
        
        NotifyAclUpdatedCallback(final com.google.android.gms.common.api.a.d<Status> d, final int n) {
            super(d);
            this.wJ = new Status(n);
        }
        
        protected void c(final com.google.android.gms.common.api.a.d<Status> d) {
            d.b(this.wJ);
        }
        
        @Override
        protected void dx() {
        }
    }
    
    final class OwnerCoverPhotoUrisLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Players.LoadOwnerCoverPhotoUrisResult> wH;
        
        OwnerCoverPhotoUrisLoadedBinderCallback(final com.google.android.gms.common.api.a.d<Players.LoadOwnerCoverPhotoUrisResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Players.LoadOwnerCoverPhotoUrisResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void c(final int n, final Bundle bundle) {
            bundle.setClassLoader(this.getClass().getClassLoader());
            GamesClientImpl.this.a((b<?>)new OwnerCoverPhotoUrisLoadedCallback(this.wH, n, bundle));
        }
    }
    
    final class OwnerCoverPhotoUrisLoadedCallback extends b<com.google.android.gms.common.api.a.d<LoadOwnerCoverPhotoUrisResult>> implements LoadOwnerCoverPhotoUrisResult
    {
        private final Bundle Jf;
        private final Status wJ;
        
        OwnerCoverPhotoUrisLoadedCallback(final com.google.android.gms.common.api.a.d<LoadOwnerCoverPhotoUrisResult> d, final int n, final Bundle jf) {
            super(d);
            this.wJ = new Status(n);
            this.Jf = jf;
        }
        
        protected void c(final com.google.android.gms.common.api.a.d<LoadOwnerCoverPhotoUrisResult> d) {
            d.b(this);
        }
        
        @Override
        protected void dx() {
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
    }
    
    final class P2PConnectedCallback extends b<RoomStatusUpdateListener>
    {
        private final String Jg;
        
        P2PConnectedCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final String jg) {
            super(roomStatusUpdateListener);
            this.Jg = jg;
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener) {
            if (roomStatusUpdateListener != null) {
                roomStatusUpdateListener.onP2PConnected(this.Jg);
            }
        }
        
        @Override
        protected void dx() {
        }
    }
    
    final class P2PDisconnectedCallback extends b<RoomStatusUpdateListener>
    {
        private final String Jg;
        
        P2PDisconnectedCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final String jg) {
            super(roomStatusUpdateListener);
            this.Jg = jg;
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener) {
            if (roomStatusUpdateListener != null) {
                roomStatusUpdateListener.onP2PDisconnected(this.Jg);
            }
        }
        
        @Override
        protected void dx() {
        }
    }
    
    final class PeerConnectedCallback extends AbstractPeerStatusCallback
    {
        PeerConnectedCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeersConnected(room, list);
        }
    }
    
    final class PeerDeclinedCallback extends AbstractPeerStatusCallback
    {
        PeerDeclinedCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeerDeclined(room, list);
        }
    }
    
    final class PeerDisconnectedCallback extends AbstractPeerStatusCallback
    {
        PeerDisconnectedCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeersDisconnected(room, list);
        }
    }
    
    final class PeerInvitedToRoomCallback extends AbstractPeerStatusCallback
    {
        PeerInvitedToRoomCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeerInvitedToRoom(room, list);
        }
    }
    
    final class PeerJoinedRoomCallback extends AbstractPeerStatusCallback
    {
        PeerJoinedRoomCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeerJoined(room, list);
        }
    }
    
    final class PeerLeftRoomCallback extends AbstractPeerStatusCallback
    {
        PeerLeftRoomCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeerLeft(room, list);
        }
    }
    
    final class PlayerLeaderboardScoreLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Leaderboards.LoadPlayerScoreResult> wH;
        
        PlayerLeaderboardScoreLoadedBinderCallback(final com.google.android.gms.common.api.a.d<Leaderboards.LoadPlayerScoreResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Leaderboards.LoadPlayerScoreResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void C(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new PlayerLeaderboardScoreLoadedCallback(this.wH, dataHolder));
        }
    }
    
    final class PlayerLeaderboardScoreLoadedCallback extends d<com.google.android.gms.common.api.a.d<LoadPlayerScoreResult>> implements LoadPlayerScoreResult
    {
        private final LeaderboardScoreEntity Jh;
        private final Status wJ;
        
        PlayerLeaderboardScoreLoadedCallback(final com.google.android.gms.common.api.a.d<LoadPlayerScoreResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
            this.wJ = new Status(dataHolder.getStatusCode());
            GamesClientImpl.this = (GamesClientImpl)new LeaderboardScoreBuffer(dataHolder);
            try {
                if (((DataBuffer)GamesClientImpl.this).getCount() > 0) {
                    this.Jh = ((Freezable<LeaderboardScoreEntity>)((LeaderboardScoreBuffer)GamesClientImpl.this).get(0)).freeze();
                }
                else {
                    this.Jh = null;
                }
            }
            finally {
                ((DataBuffer)GamesClientImpl.this).close();
            }
        }
        
        protected void a(final com.google.android.gms.common.api.a.d<LoadPlayerScoreResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
        
        @Override
        public LeaderboardScore getScore() {
            return this.Jh;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
    }
    
    final class PlayersLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Players.LoadPlayersResult> wH;
        
        PlayersLoadedBinderCallback(final com.google.android.gms.common.api.a.d<Players.LoadPlayersResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Players.LoadPlayersResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void e(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new PlayersLoadedCallback(this.wH, dataHolder));
        }
    }
    
    final class PlayersLoadedCallback extends ResultDataHolderCallback<com.google.android.gms.common.api.a.d<LoadPlayersResult>> implements LoadPlayersResult
    {
        private final PlayerBuffer Ji;
        
        PlayersLoadedCallback(final com.google.android.gms.common.api.a.d<LoadPlayersResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
            this.Ji = new PlayerBuffer(dataHolder);
        }
        
        protected void a(final com.google.android.gms.common.api.a.d<LoadPlayersResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
        
        @Override
        public PlayerBuffer getPlayers() {
            return this.Ji;
        }
    }
    
    final class RealTimeMessageSentCallback extends b<RealTimeMultiplayer.ReliableMessageSentCallback>
    {
        private final int Ah;
        private final String Jj;
        private final int Jk;
        
        RealTimeMessageSentCallback(final RealTimeMultiplayer.ReliableMessageSentCallback reliableMessageSentCallback, final int ah, final int jk, final String jj) {
            super(reliableMessageSentCallback);
            this.Ah = ah;
            this.Jk = jk;
            this.Jj = jj;
        }
        
        public void a(final RealTimeMultiplayer.ReliableMessageSentCallback reliableMessageSentCallback) {
            if (reliableMessageSentCallback != null) {
                reliableMessageSentCallback.onRealTimeMessageSent(this.Ah, this.Jk, this.Jj);
            }
        }
        
        @Override
        protected void dx() {
        }
    }
    
    final class RealTimeReliableMessageBinderCallbacks extends AbstractGamesCallbacks
    {
        final RealTimeMultiplayer.ReliableMessageSentCallback Jl;
        
        public RealTimeReliableMessageBinderCallbacks(final RealTimeMultiplayer.ReliableMessageSentCallback jl) {
            this.Jl = jl;
        }
        
        @Override
        public void b(final int n, final int n2, final String s) {
            GamesClientImpl.this.a((b<?>)new RealTimeMessageSentCallback(this.Jl, n, n2, s));
        }
    }
    
    final class RequestReceivedBinderCallback extends AbstractGamesCallbacks
    {
        private final OnRequestReceivedListener Jm;
        
        RequestReceivedBinderCallback(final OnRequestReceivedListener jm) {
            this.Jm = jm;
        }
        
        @Override
        public void m(final DataHolder dataHolder) {
            final GameRequestBuffer gameRequestBuffer = new GameRequestBuffer(dataHolder);
            GameRequest gameRequest = null;
            try {
                if (gameRequestBuffer.getCount() > 0) {
                    gameRequest = gameRequestBuffer.get(0).freeze();
                }
                gameRequestBuffer.close();
                if (gameRequest != null) {
                    GamesClientImpl.this.a((b<?>)new RequestReceivedCallback(this.Jm, gameRequest));
                }
            }
            finally {
                gameRequestBuffer.close();
            }
        }
        
        @Override
        public void onRequestRemoved(final String s) {
            GamesClientImpl.this.a((b<?>)new RequestRemovedCallback(this.Jm, s));
        }
    }
    
    final class RequestReceivedCallback extends b<OnRequestReceivedListener>
    {
        private final GameRequest Jn;
        
        RequestReceivedCallback(final OnRequestReceivedListener onRequestReceivedListener, final GameRequest jn) {
            super(onRequestReceivedListener);
            this.Jn = jn;
        }
        
        protected void b(final OnRequestReceivedListener onRequestReceivedListener) {
            onRequestReceivedListener.onRequestReceived(this.Jn);
        }
        
        @Override
        protected void dx() {
        }
    }
    
    final class RequestRemovedCallback extends b<OnRequestReceivedListener>
    {
        private final String Jo;
        
        RequestRemovedCallback(final OnRequestReceivedListener onRequestReceivedListener, final String jo) {
            super(onRequestReceivedListener);
            this.Jo = jo;
        }
        
        protected void b(final OnRequestReceivedListener onRequestReceivedListener) {
            onRequestReceivedListener.onRequestRemoved(this.Jo);
        }
        
        @Override
        protected void dx() {
        }
    }
    
    final class RequestSentBinderCallbacks extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Requests.SendRequestResult> Jp;
        
        public RequestSentBinderCallbacks(final com.google.android.gms.common.api.a.d<Requests.SendRequestResult> d) {
            this.Jp = (com.google.android.gms.common.api.a.d<Requests.SendRequestResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void E(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new RequestSentCallback(this.Jp, dataHolder));
        }
    }
    
    final class RequestSentCallback extends ResultDataHolderCallback<com.google.android.gms.common.api.a.d<SendRequestResult>> implements SendRequestResult
    {
        private final GameRequest Jn;
        
        RequestSentCallback(final com.google.android.gms.common.api.a.d<SendRequestResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
            GamesClientImpl.this = (GamesClientImpl)new GameRequestBuffer(dataHolder);
            try {
                if (((com.google.android.gms.common.data.d)GamesClientImpl.this).getCount() > 0) {
                    this.Jn = ((com.google.android.gms.common.data.d<GameRequest>)GamesClientImpl.this).get(0).freeze();
                }
                else {
                    this.Jn = null;
                }
            }
            finally {
                ((DataBuffer)GamesClientImpl.this).close();
            }
        }
        
        protected void a(final com.google.android.gms.common.api.a.d<SendRequestResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
    }
    
    final class RequestSummariesLoadedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Requests.LoadRequestSummariesResult> Jq;
        
        public RequestSummariesLoadedBinderCallbacks(final com.google.android.gms.common.api.a.d<Requests.LoadRequestSummariesResult> d) {
            this.Jq = (com.google.android.gms.common.api.a.d<Requests.LoadRequestSummariesResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void F(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new RequestSummariesLoadedCallback(this.Jq, dataHolder));
        }
    }
    
    final class RequestSummariesLoadedCallback extends ResultDataHolderCallback<com.google.android.gms.common.api.a.d<LoadRequestSummariesResult>> implements LoadRequestSummariesResult
    {
        RequestSummariesLoadedCallback(final com.google.android.gms.common.api.a.d<LoadRequestSummariesResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
        }
        
        protected void a(final com.google.android.gms.common.api.a.d<LoadRequestSummariesResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
    }
    
    final class RequestsLoadedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Requests.LoadRequestsResult> Jr;
        
        public RequestsLoadedBinderCallbacks(final com.google.android.gms.common.api.a.d<Requests.LoadRequestsResult> d) {
            this.Jr = (com.google.android.gms.common.api.a.d<Requests.LoadRequestsResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void b(final int n, final Bundle bundle) {
            bundle.setClassLoader(this.getClass().getClassLoader());
            GamesClientImpl.this.a((b<?>)new RequestsLoadedCallback(this.Jr, new Status(n), bundle));
        }
    }
    
    final class RequestsLoadedCallback extends b<com.google.android.gms.common.api.a.d<LoadRequestsResult>> implements LoadRequestsResult
    {
        private final Bundle Js;
        private final Status wJ;
        
        RequestsLoadedCallback(final com.google.android.gms.common.api.a.d<LoadRequestsResult> d, final Status wj, final Bundle js) {
            super(d);
            this.wJ = wj;
            this.Js = js;
        }
        
        protected void c(final com.google.android.gms.common.api.a.d<LoadRequestsResult> d) {
            d.b(this);
        }
        
        @Override
        protected void dx() {
            this.release();
        }
        
        @Override
        public GameRequestBuffer getRequests(final int n) {
            final String bd = RequestType.bd(n);
            if (!this.Js.containsKey(bd)) {
                return null;
            }
            return new GameRequestBuffer((DataHolder)this.Js.get(bd));
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
        
        @Override
        public void release() {
            final Iterator<String> iterator = this.Js.keySet().iterator();
            while (iterator.hasNext()) {
                final DataHolder dataHolder = (DataHolder)this.Js.getParcelable((String)iterator.next());
                if (dataHolder != null) {
                    dataHolder.close();
                }
            }
        }
    }
    
    final class RequestsUpdatedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Requests.UpdateRequestsResult> Jt;
        
        public RequestsUpdatedBinderCallbacks(final com.google.android.gms.common.api.a.d<Requests.UpdateRequestsResult> d) {
            this.Jt = (com.google.android.gms.common.api.a.d<Requests.UpdateRequestsResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void D(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new RequestsUpdatedCallback(this.Jt, dataHolder));
        }
    }
    
    final class RequestsUpdatedCallback extends ResultDataHolderCallback<com.google.android.gms.common.api.a.d<UpdateRequestsResult>> implements UpdateRequestsResult
    {
        private final RequestUpdateOutcomes Ju;
        
        RequestsUpdatedCallback(final com.google.android.gms.common.api.a.d<UpdateRequestsResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
            this.Ju = RequestUpdateOutcomes.J(dataHolder);
        }
        
        protected void a(final com.google.android.gms.common.api.a.d<UpdateRequestsResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
        
        @Override
        public Set<String> getRequestIds() {
            return this.Ju.getRequestIds();
        }
        
        @Override
        public int getRequestOutcome(final String s) {
            return this.Ju.getRequestOutcome(s);
        }
    }
    
    abstract class ResultDataHolderCallback<R extends com.google.android.gms.common.api.a.d<?>> extends d<R> implements Releasable, Result
    {
        final DataHolder BB;
        final Status wJ;
        
        public ResultDataHolderCallback(final R r, final DataHolder bb) {
            super(r, bb);
            this.wJ = new Status(bb.getStatusCode());
            this.BB = bb;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
        
        @Override
        public void release() {
            if (this.BB != null) {
                this.BB.close();
            }
        }
    }
    
    final class RoomAutoMatchingCallback extends AbstractRoomStatusCallback
    {
        RoomAutoMatchingCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            super(roomStatusUpdateListener, dataHolder);
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            roomStatusUpdateListener.onRoomAutoMatching(room);
        }
    }
    
    final class RoomBinderCallbacks extends AbstractGamesCallbacks
    {
        private final RoomUpdateListener Jv;
        private final RoomStatusUpdateListener Jw;
        private final RealTimeMessageReceivedListener Jx;
        
        public RoomBinderCallbacks(final RoomUpdateListener roomUpdateListener) {
            this.Jv = fq.b(roomUpdateListener, "Callbacks must not be null");
            this.Jw = null;
            this.Jx = null;
        }
        
        public RoomBinderCallbacks(final RoomUpdateListener roomUpdateListener, final RoomStatusUpdateListener jw, final RealTimeMessageReceivedListener jx) {
            this.Jv = fq.b(roomUpdateListener, "Callbacks must not be null");
            this.Jw = jw;
            this.Jx = jx;
        }
        
        @Override
        public void a(final DataHolder dataHolder, final String[] array) {
            GamesClientImpl.this.a((b<?>)new PeerInvitedToRoomCallback(this.Jw, dataHolder, array));
        }
        
        @Override
        public void b(final DataHolder dataHolder, final String[] array) {
            GamesClientImpl.this.a((b<?>)new PeerJoinedRoomCallback(this.Jw, dataHolder, array));
        }
        
        @Override
        public void c(final DataHolder dataHolder, final String[] array) {
            GamesClientImpl.this.a((b<?>)new PeerLeftRoomCallback(this.Jw, dataHolder, array));
        }
        
        @Override
        public void d(final DataHolder dataHolder, final String[] array) {
            GamesClientImpl.this.a((b<?>)new PeerDeclinedCallback(this.Jw, dataHolder, array));
        }
        
        @Override
        public void e(final DataHolder dataHolder, final String[] array) {
            GamesClientImpl.this.a((b<?>)new PeerConnectedCallback(this.Jw, dataHolder, array));
        }
        
        @Override
        public void f(final DataHolder dataHolder, final String[] array) {
            GamesClientImpl.this.a((b<?>)new PeerDisconnectedCallback(this.Jw, dataHolder, array));
        }
        
        @Override
        public void onLeftRoom(final int n, final String s) {
            GamesClientImpl.this.a((b<?>)new LeftRoomCallback(this.Jv, n, s));
        }
        
        @Override
        public void onP2PConnected(final String s) {
            GamesClientImpl.this.a((b<?>)new P2PConnectedCallback(this.Jw, s));
        }
        
        @Override
        public void onP2PDisconnected(final String s) {
            GamesClientImpl.this.a((b<?>)new P2PDisconnectedCallback(this.Jw, s));
        }
        
        @Override
        public void onRealTimeMessageReceived(final RealTimeMessage realTimeMessage) {
            GamesClientImpl.this.a((b<?>)new MessageReceivedCallback(this.Jx, realTimeMessage));
        }
        
        @Override
        public void s(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new RoomCreatedCallback(this.Jv, dataHolder));
        }
        
        @Override
        public void t(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new JoinedRoomCallback(this.Jv, dataHolder));
        }
        
        @Override
        public void u(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new RoomConnectingCallback(this.Jw, dataHolder));
        }
        
        @Override
        public void v(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new RoomAutoMatchingCallback(this.Jw, dataHolder));
        }
        
        @Override
        public void w(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new RoomConnectedCallback(this.Jv, dataHolder));
        }
        
        @Override
        public void x(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new ConnectedToRoomCallback(this.Jw, dataHolder));
        }
        
        @Override
        public void y(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new DisconnectedFromRoomCallback(this.Jw, dataHolder));
        }
    }
    
    final class RoomConnectedCallback extends AbstractRoomCallback
    {
        RoomConnectedCallback(final RoomUpdateListener roomUpdateListener, final DataHolder dataHolder) {
            super(roomUpdateListener, dataHolder);
        }
        
        public void a(final RoomUpdateListener roomUpdateListener, final Room room, final int n) {
            roomUpdateListener.onRoomConnected(n, room);
        }
    }
    
    final class RoomConnectingCallback extends AbstractRoomStatusCallback
    {
        RoomConnectingCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            super(roomStatusUpdateListener, dataHolder);
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            roomStatusUpdateListener.onRoomConnecting(room);
        }
    }
    
    final class RoomCreatedCallback extends AbstractRoomCallback
    {
        public RoomCreatedCallback(final RoomUpdateListener roomUpdateListener, final DataHolder dataHolder) {
            super(roomUpdateListener, dataHolder);
        }
        
        public void a(final RoomUpdateListener roomUpdateListener, final Room room, final int n) {
            roomUpdateListener.onRoomCreated(n, room);
        }
    }
    
    final class SignOutCompleteBinderCallbacks extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Status> wH;
        
        public SignOutCompleteBinderCallbacks(final com.google.android.gms.common.api.a.d<Status> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Status>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void du() {
            GamesClientImpl.this.a((b<?>)new SignOutCompleteCallback(this.wH, new Status(0)));
        }
    }
    
    final class SignOutCompleteCallback extends b<com.google.android.gms.common.api.a.d<Status>>
    {
        private final Status wJ;
        
        public SignOutCompleteCallback(final com.google.android.gms.common.api.a.d<Status> d, final Status wj) {
            super(d);
            this.wJ = wj;
        }
        
        public void c(final com.google.android.gms.common.api.a.d<Status> d) {
            d.b(this.wJ);
        }
        
        @Override
        protected void dx() {
        }
    }
    
    final class SubmitScoreBinderCallbacks extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<Leaderboards.SubmitScoreResult> wH;
        
        public SubmitScoreBinderCallbacks(final com.google.android.gms.common.api.a.d<Leaderboards.SubmitScoreResult> d) {
            this.wH = (com.google.android.gms.common.api.a.d<Leaderboards.SubmitScoreResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void d(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new SubmitScoreCallback(this.wH, dataHolder));
        }
    }
    
    final class SubmitScoreCallback extends ResultDataHolderCallback<com.google.android.gms.common.api.a.d<SubmitScoreResult>> implements SubmitScoreResult
    {
        private final ScoreSubmissionData Jy;
        
        public SubmitScoreCallback(final com.google.android.gms.common.api.a.d<SubmitScoreResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
            try {
                this.Jy = new ScoreSubmissionData(dataHolder);
            }
            finally {
                dataHolder.close();
            }
        }
        
        public void a(final com.google.android.gms.common.api.a.d<SubmitScoreResult> d, final DataHolder dataHolder) {
            d.b(this);
        }
        
        @Override
        public ScoreSubmissionData getScoreData() {
            return this.Jy;
        }
    }
    
    abstract class TurnBasedMatchCallback<T extends com.google.android.gms.common.api.a.d<?>> extends ResultDataHolderCallback<T>
    {
        final TurnBasedMatch Jd;
        
        TurnBasedMatchCallback(final T t, final DataHolder dataHolder) {
            super(t, dataHolder);
            GamesClientImpl.this = (GamesClientImpl)new TurnBasedMatchBuffer(dataHolder);
            try {
                if (((com.google.android.gms.common.data.d)GamesClientImpl.this).getCount() > 0) {
                    this.Jd = ((com.google.android.gms.common.data.d<TurnBasedMatch>)GamesClientImpl.this).get(0).freeze();
                }
                else {
                    this.Jd = null;
                }
            }
            finally {
                ((DataBuffer)GamesClientImpl.this).close();
            }
        }
        
        protected void a(final T t, final DataHolder dataHolder) {
            this.k(t);
        }
        
        public TurnBasedMatch getMatch() {
            return this.Jd;
        }
        
        abstract void k(final T p0);
    }
    
    final class TurnBasedMatchCanceledBinderCallbacks extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.CancelMatchResult> Jz;
        
        public TurnBasedMatchCanceledBinderCallbacks(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.CancelMatchResult> d) {
            this.Jz = (com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.CancelMatchResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void f(final int n, final String s) {
            GamesClientImpl.this.a((b<?>)new TurnBasedMatchCanceledCallback(this.Jz, new Status(n), s));
        }
    }
    
    final class TurnBasedMatchCanceledCallback extends b<com.google.android.gms.common.api.a.d<CancelMatchResult>> implements CancelMatchResult
    {
        private final String JA;
        private final Status wJ;
        
        TurnBasedMatchCanceledCallback(final com.google.android.gms.common.api.a.d<CancelMatchResult> d, final Status wj, final String ja) {
            super(d);
            this.wJ = wj;
            this.JA = ja;
        }
        
        public void c(final com.google.android.gms.common.api.a.d<CancelMatchResult> d) {
            d.b(this);
        }
        
        @Override
        protected void dx() {
        }
        
        @Override
        public String getMatchId() {
            return this.JA;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
    }
    
    final class TurnBasedMatchInitiatedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.InitiateMatchResult> JB;
        
        public TurnBasedMatchInitiatedBinderCallbacks(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.InitiateMatchResult> d) {
            this.JB = (com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.InitiateMatchResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void o(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new TurnBasedMatchInitiatedCallback(this.JB, dataHolder));
        }
    }
    
    final class TurnBasedMatchInitiatedCallback extends TurnBasedMatchCallback<com.google.android.gms.common.api.a.d<InitiateMatchResult>> implements InitiateMatchResult
    {
        TurnBasedMatchInitiatedCallback(final com.google.android.gms.common.api.a.d<InitiateMatchResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
        }
        
        protected void k(final com.google.android.gms.common.api.a.d<InitiateMatchResult> d) {
            d.b(this);
        }
    }
    
    final class TurnBasedMatchLeftBinderCallbacks extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.LeaveMatchResult> JC;
        
        public TurnBasedMatchLeftBinderCallbacks(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.LeaveMatchResult> d) {
            this.JC = (com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.LeaveMatchResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void q(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new TurnBasedMatchLeftCallback(this.JC, dataHolder));
        }
    }
    
    final class TurnBasedMatchLeftCallback extends TurnBasedMatchCallback<com.google.android.gms.common.api.a.d<LeaveMatchResult>> implements LeaveMatchResult
    {
        TurnBasedMatchLeftCallback(final com.google.android.gms.common.api.a.d<LeaveMatchResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
        }
        
        protected void k(final com.google.android.gms.common.api.a.d<LeaveMatchResult> d) {
            d.b(this);
        }
    }
    
    final class TurnBasedMatchLoadedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.LoadMatchResult> JD;
        
        public TurnBasedMatchLoadedBinderCallbacks(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.LoadMatchResult> d) {
            this.JD = (com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.LoadMatchResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void n(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new TurnBasedMatchLoadedCallback(this.JD, dataHolder));
        }
    }
    
    final class TurnBasedMatchLoadedCallback extends TurnBasedMatchCallback<com.google.android.gms.common.api.a.d<LoadMatchResult>> implements LoadMatchResult
    {
        TurnBasedMatchLoadedCallback(final com.google.android.gms.common.api.a.d<LoadMatchResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
        }
        
        protected void k(final com.google.android.gms.common.api.a.d<LoadMatchResult> d) {
            d.b(this);
        }
    }
    
    final class TurnBasedMatchUpdatedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.UpdateMatchResult> JE;
        
        public TurnBasedMatchUpdatedBinderCallbacks(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.UpdateMatchResult> d) {
            this.JE = (com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.UpdateMatchResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void p(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new TurnBasedMatchUpdatedCallback(this.JE, dataHolder));
        }
    }
    
    final class TurnBasedMatchUpdatedCallback extends TurnBasedMatchCallback<com.google.android.gms.common.api.a.d<UpdateMatchResult>> implements UpdateMatchResult
    {
        TurnBasedMatchUpdatedCallback(final com.google.android.gms.common.api.a.d<UpdateMatchResult> d, final DataHolder dataHolder) {
            super(d, dataHolder);
        }
        
        protected void k(final com.google.android.gms.common.api.a.d<UpdateMatchResult> d) {
            d.b(this);
        }
    }
    
    final class TurnBasedMatchesLoadedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.LoadMatchesResult> JF;
        
        public TurnBasedMatchesLoadedBinderCallbacks(final com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.LoadMatchesResult> d) {
            this.JF = (com.google.android.gms.common.api.a.d<TurnBasedMultiplayer.LoadMatchesResult>)fq.b((com.google.android.gms.common.api.a.d)d, "Holder must not be null");
        }
        
        @Override
        public void a(final int n, final Bundle bundle) {
            bundle.setClassLoader(this.getClass().getClassLoader());
            GamesClientImpl.this.a((b<?>)new TurnBasedMatchesLoadedCallback(this.JF, new Status(n), bundle));
        }
    }
    
    final class TurnBasedMatchesLoadedCallback extends b<com.google.android.gms.common.api.a.d<LoadMatchesResult>> implements LoadMatchesResult
    {
        private final LoadMatchesResponse JG;
        private final Status wJ;
        
        TurnBasedMatchesLoadedCallback(final com.google.android.gms.common.api.a.d<LoadMatchesResult> d, final Status wj, final Bundle bundle) {
            super(d);
            this.wJ = wj;
            this.JG = new LoadMatchesResponse(bundle);
        }
        
        protected void c(final com.google.android.gms.common.api.a.d<LoadMatchesResult> d) {
            d.b(this);
        }
        
        @Override
        protected void dx() {
        }
        
        @Override
        public LoadMatchesResponse getMatches() {
            return this.JG;
        }
        
        @Override
        public Status getStatus() {
            return this.wJ;
        }
        
        @Override
        public void release() {
            this.JG.close();
        }
    }
}
