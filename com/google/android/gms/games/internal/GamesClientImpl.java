// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.multiplayer.ParticipantUtils;
import com.google.android.gms.games.Notifications$GameMuteStatusLoadResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.games.Players$LoadXpForGameCategoriesResult;
import com.google.android.gms.games.GamesMetadata$LoadGameSearchSuggestionsResult;
import com.google.android.gms.games.GamesMetadata$LoadGameInstancesResult;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.snapshot.Snapshots$DeleteSnapshotResult;
import com.google.android.gms.games.Notifications$InboxCountResult;
import android.os.IInterface;
import com.google.android.gms.games.quest.Quests$AcceptQuestResult;
import com.google.android.gms.games.Notifications$ContactSettingLoadResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LoadMatchResult;
import com.google.android.gms.games.internal.game.Acls$LoadAclResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$CancelMatchResult;
import com.google.android.gms.games.Players$LoadOwnerCoverPhotoUrisResult;
import com.google.android.gms.games.Players$LoadProfileSettingsResult;
import com.google.android.gms.games.request.Requests$LoadRequestSummariesResult;
import com.google.android.gms.games.GamesMetadata$LoadGamesResult;
import com.google.android.gms.games.Notifications$GameMuteStatusChangeResult;
import com.google.android.gms.games.snapshot.Snapshots$LoadSnapshotsResult;
import com.google.android.gms.games.Players$LoadXpStreamResult;
import com.google.android.gms.games.multiplayer.Invitations$LoadInvitationsResult;
import com.google.android.gms.games.achievement.Achievements$LoadAchievementsResult;
import com.google.android.gms.games.quest.Quests$ClaimMilestoneResult;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.common.internal.j;
import java.util.ArrayList;
import com.google.android.gms.common.internal.d$e;
import com.google.android.gms.common.internal.k;
import com.google.android.gms.games.event.Events$LoadEventsResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.request.Requests$SendRequestResult;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$UpdateMatchResult;
import com.google.android.gms.games.request.Requests$UpdateRequestsResult;
import com.google.android.gms.games.quest.Quests$LoadQuestsResult;
import com.google.android.gms.games.leaderboard.Leaderboards$LeaderboardMetadataResult;
import com.google.android.gms.games.snapshot.Snapshots$OpenSnapshotResult;
import com.google.android.gms.games.leaderboard.Leaderboards$LoadPlayerScoreResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LeaveMatchResult;
import com.google.android.gms.games.leaderboard.Leaderboards$SubmitScoreResult;
import com.google.android.gms.games.achievement.Achievements$UpdateAchievementResult;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.data.a;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.Snapshots$CommitSnapshotResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$InitiateMatchResult;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards$LoadScoresResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LoadMatchesResult;
import com.google.android.gms.games.Players$LoadPlayersResult;
import com.google.android.gms.games.GamesMetadata$LoadExtendedGamesResult;
import com.google.android.gms.games.request.Requests$LoadRequestsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import android.os.Parcelable;
import android.graphics.Bitmap;
import android.content.Intent;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer$ReliableMessageSentCallback;
import java.util.Iterator;
import android.os.ParcelFileDescriptor;
import java.io.IOException;
import android.os.RemoteException;
import android.net.LocalSocketAddress;
import android.net.LocalSocket;
import com.google.android.gms.internal.kc;
import com.google.android.gms.games.multiplayer.realtime.RoomBuffer;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.common.data.DataHolder;
import java.util.HashMap;
import com.google.android.gms.common.internal.n;
import android.view.View;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.games.Games$GamesOptions;
import android.os.Binder;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import java.util.Map;
import com.google.android.gms.games.internal.events.EventIncrementManager;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.d;

public final class GamesClientImpl extends d<IGamesService> implements GoogleApiClient$ConnectionCallbacks, GoogleApiClient$OnConnectionFailedListener
{
    private final String Dd;
    EventIncrementManager Wh;
    private final String Wi;
    private final Map<String, RealTimeSocket> Wj;
    private PlayerEntity Wk;
    private GameEntity Wl;
    private final PopupManager Wm;
    private boolean Wn;
    private final Binder Wo;
    private final long Wp;
    private final Games$GamesOptions Wq;
    
    public GamesClientImpl(final Context context, final Looper looper, final String wi, final String s, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String[] array, final int n, final View view, final Games$GamesOptions wq) {
        super(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, array);
        this.Wh = new GamesClientImpl$1(this);
        this.Wn = false;
        this.Wi = wi;
        this.Dd = n.i(s);
        this.Wo = new Binder();
        this.Wj = new HashMap<String, RealTimeSocket>();
        this.Wm = PopupManager.a(this, n);
        this.k(view);
        this.Wp = this.hashCode();
        this.Wq = wq;
        this.registerConnectionCallbacks(this);
        this.registerConnectionFailedListener(this);
    }
    
    private Room R(final DataHolder dataHolder) {
        final RoomBuffer roomBuffer = new RoomBuffer(dataHolder);
        Room room = null;
        try {
            if (roomBuffer.getCount() > 0) {
                room = roomBuffer.get(0).freeze();
            }
            return room;
        }
        finally {
            roomBuffer.release();
        }
    }
    
    private RealTimeSocket bw(final String s) {
        RealTimeSocket realTimeSocket;
        if (kc.hD()) {
            realTimeSocket = this.by(s);
        }
        else {
            realTimeSocket = this.bx(s);
        }
        if (realTimeSocket != null) {
            this.Wj.put(s, realTimeSocket);
        }
        return realTimeSocket;
    }
    
    private RealTimeSocket bx(final String s) {
        try {
            final String bc = this.gS().bC(s);
            if (bc == null) {
                return null;
            }
            final LocalSocket localSocket = new LocalSocket();
            localSocket.connect(new LocalSocketAddress(bc));
            return new RealTimeSocketImpl(localSocket, s);
        }
        catch (RemoteException ex2) {
            GamesLog.q("GamesClientImpl", "Unable to create socket. Service died.");
        }
        catch (IOException ex) {
            GamesLog.q("GamesClientImpl", "connect() call failed on socket: " + ex.getMessage());
            goto Label_0060;
        }
    }
    
    private RealTimeSocket by(final String s) {
        try {
            final ParcelFileDescriptor bh = this.gS().bH(s);
            if (bh != null) {
                GamesLog.o("GamesClientImpl", "Created native libjingle socket.");
                return new LibjingleNativeSocket(bh);
            }
            GamesLog.q("GamesClientImpl", "Unable to create socket for " + s);
            return null;
        }
        catch (RemoteException ex) {
            GamesLog.q("GamesClientImpl", "Unable to create socket. Service died.");
            return null;
        }
    }
    
    private void jW() {
        this.Wk = null;
    }
    
    private void kt() {
        for (final RealTimeSocket realTimeSocket : this.Wj.values()) {
            try {
                realTimeSocket.close();
            }
            catch (IOException ex) {
                GamesLog.c("GamesClientImpl", "IOException:", ex);
            }
        }
        this.Wj.clear();
    }
    
    public int a(final RealTimeMultiplayer$ReliableMessageSentCallback realTimeMultiplayer$ReliableMessageSentCallback, final byte[] array, final String s, final String s2) {
        try {
            return this.gS().a(new GamesClientImpl$RealTimeReliableMessageBinderCallbacks(this, realTimeMultiplayer$ReliableMessageSentCallback), array, s, s2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return -1;
        }
    }
    
    public int a(final byte[] array, final String s, final String[] array2) {
        n.b(array2, "Participant IDs must not be null");
        try {
            return this.gS().b(array, s, array2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return -1;
        }
    }
    
    public Intent a(final int n, final int n2, final boolean b) {
        try {
            return this.gS().a(n, n2, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent a(final int n, final byte[] array, final int n2, final Bitmap bitmap, final String s) {
        try {
            final Intent a = this.gS().a(n, array, n2, s);
            n.b(bitmap, "Must provide a non null icon");
            a.putExtra("com.google.android.gms.games.REQUEST_ITEM_ICON", (Parcelable)bitmap);
            return a;
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent a(final Room room, final int n) {
        try {
            return this.gS().a(((Freezable<RoomEntity>)room).freeze(), n);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent a(final String s, final boolean b, final boolean b2, final int n) {
        try {
            return this.gS().a(s, b, b2, n);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    @Override
    protected void a(final int n, final IBinder binder, final Bundle bundle) {
        if (n == 0 && bundle != null) {
            this.Wn = bundle.getBoolean("show_welcome_popup");
        }
        super.a(n, binder, bundle);
    }
    
    public void a(final IBinder binder, final Bundle bundle) {
        if (!this.isConnected()) {
            return;
        }
        try {
            this.gS().a(binder, bundle);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Requests$LoadRequestsResult> baseImplementation$b, final int n, final int n2, final int n3) {
        try {
            this.gS().a(new GamesClientImpl$RequestsLoadedBinderCallbacks(baseImplementation$b), n, n2, n3);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<GamesMetadata$LoadExtendedGamesResult> baseImplementation$b, final int n, final int n2, final boolean b, final boolean b2) {
        try {
            this.gS().a(new GamesClientImpl$ExtendedGamesLoadedBinderCallback(baseImplementation$b), n, n2, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Players$LoadPlayersResult> baseImplementation$b, final int n, final boolean b, final boolean b2) {
        try {
            this.gS().a(new GamesClientImpl$PlayersLoadedBinderCallback(baseImplementation$b), n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<TurnBasedMultiplayer$LoadMatchesResult> baseImplementation$b, final int n, final int[] array) {
        try {
            this.gS().a(new GamesClientImpl$TurnBasedMatchesLoadedBinderCallbacks(baseImplementation$b), n, array);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Leaderboards$LoadScoresResult> baseImplementation$b, final LeaderboardScoreBuffer leaderboardScoreBuffer, final int n, final int n2) {
        try {
            this.gS().a(new GamesClientImpl$LeaderboardScoresLoadedBinderCallback(baseImplementation$b), leaderboardScoreBuffer.ly().lz(), n, n2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<TurnBasedMultiplayer$InitiateMatchResult> baseImplementation$b, final TurnBasedMatchConfig turnBasedMatchConfig) {
        try {
            this.gS().a(new GamesClientImpl$TurnBasedMatchInitiatedBinderCallbacks(baseImplementation$b), turnBasedMatchConfig.getVariant(), turnBasedMatchConfig.lF(), turnBasedMatchConfig.getInvitedPlayerIds(), turnBasedMatchConfig.getAutoMatchCriteria());
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Snapshots$CommitSnapshotResult> baseImplementation$b, final Snapshot snapshot, final SnapshotMetadataChange snapshotMetadataChange) {
        final SnapshotContents snapshotContents = snapshot.getSnapshotContents();
        Label_0098: {
            if (snapshotContents.isClosed()) {
                break Label_0098;
            }
            boolean b = true;
            while (true) {
                n.a(b, (Object)"Snapshot already closed");
                final a lk = snapshotMetadataChange.lK();
                if (lk != null) {
                    lk.a(this.getContext().getCacheDir());
                }
                final Contents contents = snapshotContents.getContents();
                snapshotContents.close();
                try {
                    this.gS().a(new GamesClientImpl$SnapshotCommittedBinderCallbacks(baseImplementation$b), snapshot.getMetadata().getSnapshotId(), snapshotMetadataChange, contents);
                    return;
                    b = false;
                    continue;
                }
                catch (RemoteException ex) {
                    GamesLog.p("GamesClientImpl", "service died");
                }
                break;
            }
        }
    }
    
    public void a(final BaseImplementation$b<Players$LoadPlayersResult> baseImplementation$b, final String s) {
        try {
            this.gS().a(new GamesClientImpl$PlayersLoadedBinderCallback(baseImplementation$b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Achievements$UpdateAchievementResult> baseImplementation$b, final String s, final int n) {
        Label_0036: {
            if (baseImplementation$b != null) {
                break Label_0036;
            }
            IGamesCallbacks gamesCallbacks = null;
            try {
                while (true) {
                    this.gS().a(gamesCallbacks, s, n, this.Wm.kL(), this.Wm.kK());
                    return;
                    gamesCallbacks = new GamesClientImpl$AchievementUpdatedBinderCallback(baseImplementation$b);
                    continue;
                }
            }
            catch (RemoteException ex) {
                GamesLog.p("GamesClientImpl", "service died");
            }
        }
    }
    
    public void a(final BaseImplementation$b<Leaderboards$LoadScoresResult> baseImplementation$b, final String s, final int n, final int n2, final int n3, final boolean b) {
        try {
            this.gS().a(new GamesClientImpl$LeaderboardScoresLoadedBinderCallback(baseImplementation$b), s, n, n2, n3, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Players$LoadPlayersResult> baseImplementation$b, final String s, final int n, final boolean b) {
        try {
            this.gS().a(new GamesClientImpl$PlayersLoadedBinderCallback(baseImplementation$b), s, n, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Players$LoadPlayersResult> baseImplementation$b, final String s, final int n, final boolean b, final boolean b2) {
        switch (s) {
            default: {
                throw new IllegalArgumentException("Invalid player collection: " + s);
            }
            case "played_with": {
                try {
                    this.gS().d(new GamesClientImpl$PlayersLoadedBinderCallback(baseImplementation$b), s, n, b, b2);
                    return;
                }
                catch (RemoteException ex) {
                    GamesLog.p("GamesClientImpl", "service died");
                    return;
                }
                break;
            }
        }
    }
    
    public void a(final BaseImplementation$b<GamesMetadata$LoadExtendedGamesResult> baseImplementation$b, final String s, final int n, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        try {
            this.gS().a(new GamesClientImpl$ExtendedGamesLoadedBinderCallback(baseImplementation$b), s, n, b, b2, b3, b4);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<TurnBasedMultiplayer$LoadMatchesResult> baseImplementation$b, final String s, final int n, final int[] array) {
        try {
            this.gS().a(new GamesClientImpl$TurnBasedMatchesLoadedBinderCallbacks(baseImplementation$b), s, n, array);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Leaderboards$SubmitScoreResult> baseImplementation$b, final String s, final long n, final String s2) {
        Label_0024: {
            if (baseImplementation$b != null) {
                break Label_0024;
            }
            IGamesCallbacks gamesCallbacks = null;
            try {
                while (true) {
                    this.gS().a(gamesCallbacks, s, n, s2);
                    return;
                    gamesCallbacks = new GamesClientImpl$SubmitScoreBinderCallbacks(baseImplementation$b);
                    continue;
                }
            }
            catch (RemoteException ex) {
                GamesLog.p("GamesClientImpl", "service died");
            }
        }
    }
    
    public void a(final BaseImplementation$b<TurnBasedMultiplayer$LeaveMatchResult> baseImplementation$b, final String s, final String s2) {
        try {
            this.gS().c(new GamesClientImpl$TurnBasedMatchLeftBinderCallbacks(baseImplementation$b), s, s2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Leaderboards$LoadPlayerScoreResult> baseImplementation$b, final String s, final String s2, final int n, final int n2) {
        try {
            this.gS().a(new GamesClientImpl$PlayerLeaderboardScoreLoadedBinderCallback(baseImplementation$b), s, s2, n, n2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Requests$LoadRequestsResult> baseImplementation$b, final String s, final String s2, final int n, final int n2, final int n3) {
        try {
            this.gS().a(new GamesClientImpl$RequestsLoadedBinderCallbacks(baseImplementation$b), s, s2, n, n2, n3);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Leaderboards$LoadScoresResult> baseImplementation$b, final String s, final String s2, final int n, final int n2, final int n3, final boolean b) {
        try {
            this.gS().a(new GamesClientImpl$LeaderboardScoresLoadedBinderCallback(baseImplementation$b), s, s2, n, n2, n3, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Players$LoadPlayersResult> baseImplementation$b, final String s, final String s2, final int n, final boolean b, final boolean b2) {
        switch (s) {
            default: {
                throw new IllegalArgumentException("Invalid player collection: " + s);
            }
            case "circled":
            case "played_with":
            case "nearby": {
                try {
                    this.gS().a(new GamesClientImpl$PlayersLoadedBinderCallback(baseImplementation$b), s, s2, n, b, b2);
                    return;
                }
                catch (RemoteException ex) {
                    GamesLog.p("GamesClientImpl", "service died");
                    return;
                }
                break;
            }
        }
    }
    
    public void a(final BaseImplementation$b<Snapshots$OpenSnapshotResult> baseImplementation$b, final String s, final String s2, final SnapshotMetadataChange snapshotMetadataChange, final SnapshotContents snapshotContents) {
        Label_0083: {
            if (snapshotContents.isClosed()) {
                break Label_0083;
            }
            boolean b = true;
            while (true) {
                n.a(b, (Object)"SnapshotContents already closed");
                final a lk = snapshotMetadataChange.lK();
                if (lk != null) {
                    lk.a(this.getContext().getCacheDir());
                }
                final Contents contents = snapshotContents.getContents();
                snapshotContents.close();
                try {
                    this.gS().a(new GamesClientImpl$SnapshotOpenedBinderCallbacks(baseImplementation$b), s, s2, snapshotMetadataChange, contents);
                    return;
                    b = false;
                    continue;
                }
                catch (RemoteException ex) {
                    GamesLog.p("GamesClientImpl", "service died");
                }
                break;
            }
        }
    }
    
    public void a(final BaseImplementation$b<Leaderboards$LeaderboardMetadataResult> baseImplementation$b, final String s, final String s2, final boolean b) {
        try {
            this.gS().b(new GamesClientImpl$LeaderboardsLoadedBinderCallback(baseImplementation$b), s, s2, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Quests$LoadQuestsResult> baseImplementation$b, final String s, final String s2, final boolean b, final String[] array) {
        try {
            this.Wh.flush();
            this.gS().a(new GamesClientImpl$QuestsLoadedBinderCallbacks(baseImplementation$b), s, s2, array, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Quests$LoadQuestsResult> baseImplementation$b, final String s, final String s2, final int[] array, final int n, final boolean b) {
        try {
            this.Wh.flush();
            this.gS().a(new GamesClientImpl$QuestsLoadedBinderCallbacks(baseImplementation$b), s, s2, array, n, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Requests$UpdateRequestsResult> baseImplementation$b, final String s, final String s2, final String[] array) {
        try {
            this.gS().a(new GamesClientImpl$RequestsUpdatedBinderCallbacks(baseImplementation$b), s, s2, array);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Leaderboards$LeaderboardMetadataResult> baseImplementation$b, final String s, final boolean b) {
        try {
            this.gS().c(new GamesClientImpl$LeaderboardsLoadedBinderCallback(baseImplementation$b), s, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<TurnBasedMultiplayer$UpdateMatchResult> baseImplementation$b, final String s, final byte[] array, final String s2, final ParticipantResult[] array2) {
        try {
            this.gS().a(new GamesClientImpl$TurnBasedMatchUpdatedBinderCallbacks(baseImplementation$b), s, array, s2, array2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<TurnBasedMultiplayer$UpdateMatchResult> baseImplementation$b, final String s, final byte[] array, final ParticipantResult[] array2) {
        try {
            this.gS().a(new GamesClientImpl$TurnBasedMatchUpdatedBinderCallbacks(baseImplementation$b), s, array, array2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Requests$SendRequestResult> baseImplementation$b, final String s, final String[] array, final int n, final byte[] array2, final int n2) {
        try {
            this.gS().a(new GamesClientImpl$RequestSentBinderCallbacks(baseImplementation$b), s, array, n, array2, n2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Players$LoadPlayersResult> baseImplementation$b, final boolean b) {
        try {
            this.gS().c(new GamesClientImpl$PlayersLoadedBinderCallback(baseImplementation$b), b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Status> baseImplementation$b, final boolean b, final Bundle bundle) {
        try {
            this.gS().a(new GamesClientImpl$ContactSettingsUpdatedBinderCallback(baseImplementation$b), b, bundle);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Events$LoadEventsResult> baseImplementation$b, final boolean b, final String... array) {
        try {
            this.Wh.flush();
            this.gS().a(new GamesClientImpl$EventsLoadedBinderCallback(baseImplementation$b), b, array);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Quests$LoadQuestsResult> baseImplementation$b, final int[] array, final int n, final boolean b) {
        try {
            this.Wh.flush();
            this.gS().a(new GamesClientImpl$QuestsLoadedBinderCallbacks(baseImplementation$b), array, n, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation$b<Players$LoadPlayersResult> baseImplementation$b, final String[] array) {
        try {
            this.gS().c(new GamesClientImpl$PlayersLoadedBinderCallback(baseImplementation$b), array);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    @Override
    protected void a(final k k, final d$e d$e) {
        final String string = this.getContext().getResources().getConfiguration().locale.toString();
        final Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.games.key.isHeadless", this.Wq.Vs);
        bundle.putBoolean("com.google.android.gms.games.key.showConnectingPopup", this.Wq.Vt);
        bundle.putInt("com.google.android.gms.games.key.connectingPopupGravity", this.Wq.Vu);
        bundle.putBoolean("com.google.android.gms.games.key.retryingSignIn", this.Wq.Vv);
        bundle.putInt("com.google.android.gms.games.key.sdkVariant", this.Wq.Vw);
        bundle.putString("com.google.android.gms.games.key.forceResolveAccountKey", this.Wq.Vx);
        bundle.putStringArrayList("com.google.android.gms.games.key.proxyApis", (ArrayList)this.Wq.Vy);
        k.a(d$e, 6111000, this.getContext().getPackageName(), this.Dd, this.gR(), this.Wi, this.Wm.kL(), string, bundle);
    }
    
    public void a(final OnInvitationReceivedListener onInvitationReceivedListener) {
        try {
            this.gS().a(new GamesClientImpl$InvitationReceivedBinderCallback(this, onInvitationReceivedListener), this.Wp);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final RoomConfig roomConfig) {
        this.kt();
        try {
            this.gS().a(new GamesClientImpl$RoomBinderCallbacks(this, roomConfig.getRoomUpdateListener(), roomConfig.getRoomStatusUpdateListener(), roomConfig.getMessageReceivedListener()), (IBinder)this.Wo, roomConfig.getVariant(), roomConfig.getInvitedPlayerIds(), roomConfig.getAutoMatchCriteria(), roomConfig.isSocketEnabled(), this.Wp);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final RoomUpdateListener roomUpdateListener, final String s) {
        try {
            this.gS().c(new GamesClientImpl$RoomBinderCallbacks(this, roomUpdateListener), s);
            this.kt();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
        try {
            this.gS().b(new GamesClientImpl$MatchUpdateReceivedBinderCallback(this, onTurnBasedMatchUpdateReceivedListener), this.Wp);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final QuestUpdateListener questUpdateListener) {
        try {
            this.gS().d(new GamesClientImpl$QuestUpdateBinderCallback(this, questUpdateListener), this.Wp);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final OnRequestReceivedListener onRequestReceivedListener) {
        try {
            this.gS().c(new GamesClientImpl$RequestReceivedBinderCallback(this, onRequestReceivedListener), this.Wp);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final Snapshot snapshot) {
        final SnapshotContents snapshotContents = snapshot.getSnapshotContents();
        Label_0046: {
            if (snapshotContents.isClosed()) {
                break Label_0046;
            }
            boolean b = true;
            while (true) {
                n.a(b, (Object)"Snapshot already closed");
                final Contents contents = snapshotContents.getContents();
                snapshotContents.close();
                try {
                    this.gS().a(contents);
                    return;
                    b = false;
                }
                catch (RemoteException ex) {
                    GamesLog.p("GamesClientImpl", "service died");
                }
            }
        }
    }
    
    protected IGamesService az(final IBinder binder) {
        return IGamesService$Stub.aB(binder);
    }
    
    public Intent b(final int n, final int n2, final boolean b) {
        try {
            return this.gS().b(n, n2, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent b(final int[] array) {
        try {
            return this.gS().b(array);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public void b(final BaseImplementation$b<Status> baseImplementation$b) {
        try {
            this.Wh.flush();
            this.gS().a(new GamesClientImpl$SignOutCompleteBinderCallbacks(baseImplementation$b));
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation$b<Players$LoadPlayersResult> baseImplementation$b, final int n, final boolean b, final boolean b2) {
        try {
            this.gS().b(new GamesClientImpl$PlayersLoadedBinderCallback(baseImplementation$b), n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation$b<Achievements$UpdateAchievementResult> baseImplementation$b, final String s) {
        Label_0035: {
            if (baseImplementation$b != null) {
                break Label_0035;
            }
            IGamesCallbacks gamesCallbacks = null;
            try {
                while (true) {
                    this.gS().a(gamesCallbacks, s, this.Wm.kL(), this.Wm.kK());
                    return;
                    gamesCallbacks = new GamesClientImpl$AchievementUpdatedBinderCallback(baseImplementation$b);
                    continue;
                }
            }
            catch (RemoteException ex) {
                GamesLog.p("GamesClientImpl", "service died");
            }
        }
    }
    
    public void b(final BaseImplementation$b<Achievements$UpdateAchievementResult> baseImplementation$b, final String s, final int n) {
        Label_0036: {
            if (baseImplementation$b != null) {
                break Label_0036;
            }
            IGamesCallbacks gamesCallbacks = null;
            try {
                while (true) {
                    this.gS().b(gamesCallbacks, s, n, this.Wm.kL(), this.Wm.kK());
                    return;
                    gamesCallbacks = new GamesClientImpl$AchievementUpdatedBinderCallback(baseImplementation$b);
                    continue;
                }
            }
            catch (RemoteException ex) {
                GamesLog.p("GamesClientImpl", "service died");
            }
        }
    }
    
    public void b(final BaseImplementation$b<Leaderboards$LoadScoresResult> baseImplementation$b, final String s, final int n, final int n2, final int n3, final boolean b) {
        try {
            this.gS().b(new GamesClientImpl$LeaderboardScoresLoadedBinderCallback(baseImplementation$b), s, n, n2, n3, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation$b<GamesMetadata$LoadExtendedGamesResult> baseImplementation$b, final String s, final int n, final boolean b, final boolean b2) {
        try {
            this.gS().a(new GamesClientImpl$ExtendedGamesLoadedBinderCallback(baseImplementation$b), s, n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation$b<Quests$ClaimMilestoneResult> baseImplementation$b, final String s, final String s2) {
        try {
            this.Wh.flush();
            this.gS().f(new GamesClientImpl$QuestMilestoneClaimBinderCallbacks(baseImplementation$b, s2), s, s2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation$b<Leaderboards$LoadScoresResult> baseImplementation$b, final String s, final String s2, final int n, final int n2, final int n3, final boolean b) {
        try {
            this.gS().b(new GamesClientImpl$LeaderboardScoresLoadedBinderCallback(baseImplementation$b), s, s2, n, n2, n3, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation$b<Achievements$LoadAchievementsResult> baseImplementation$b, final String s, final String s2, final boolean b) {
        try {
            this.gS().a(new GamesClientImpl$AchievementsLoadedBinderCallback(baseImplementation$b), s, s2, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation$b<Snapshots$OpenSnapshotResult> baseImplementation$b, final String s, final boolean b) {
        try {
            this.gS().e(new GamesClientImpl$SnapshotOpenedBinderCallbacks(baseImplementation$b), s, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation$b<Leaderboards$LeaderboardMetadataResult> baseImplementation$b, final boolean b) {
        try {
            this.gS().b(new GamesClientImpl$LeaderboardsLoadedBinderCallback(baseImplementation$b), b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation$b<Quests$LoadQuestsResult> baseImplementation$b, final boolean b, final String[] array) {
        try {
            this.Wh.flush();
            this.gS().a(new GamesClientImpl$QuestsLoadedBinderCallbacks(baseImplementation$b), array, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation$b<Requests$UpdateRequestsResult> baseImplementation$b, final String[] array) {
        try {
            this.gS().a(new GamesClientImpl$RequestsUpdatedBinderCallbacks(baseImplementation$b), array);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final RoomConfig roomConfig) {
        this.kt();
        try {
            this.gS().a(new GamesClientImpl$RoomBinderCallbacks(this, roomConfig.getRoomUpdateListener(), roomConfig.getRoomStatusUpdateListener(), roomConfig.getMessageReceivedListener()), (IBinder)this.Wo, roomConfig.getInvitationId(), roomConfig.isSocketEnabled(), this.Wp);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void bA(final String s) {
        try {
            this.gS().a(s, this.Wm.kL(), this.Wm.kK());
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public Intent bu(final String s) {
        try {
            return this.gS().bu(s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public void bv(final String s) {
        try {
            this.gS().bG(s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public Intent bz(final String s) {
        try {
            return this.gS().bz(s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public void c(final BaseImplementation$b<Invitations$LoadInvitationsResult> baseImplementation$b, final int n) {
        try {
            this.gS().a(new GamesClientImpl$InvitationsLoadedBinderCallback(baseImplementation$b), n);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void c(final BaseImplementation$b<Players$LoadPlayersResult> baseImplementation$b, final int n, final boolean b, final boolean b2) {
        try {
            this.gS().c(new GamesClientImpl$PlayersLoadedBinderCallback(baseImplementation$b), n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void c(final BaseImplementation$b<Achievements$UpdateAchievementResult> baseImplementation$b, final String s) {
        Label_0035: {
            if (baseImplementation$b != null) {
                break Label_0035;
            }
            IGamesCallbacks gamesCallbacks = null;
            try {
                while (true) {
                    this.gS().b(gamesCallbacks, s, this.Wm.kL(), this.Wm.kK());
                    return;
                    gamesCallbacks = new GamesClientImpl$AchievementUpdatedBinderCallback(baseImplementation$b);
                    continue;
                }
            }
            catch (RemoteException ex) {
                GamesLog.p("GamesClientImpl", "service died");
            }
        }
    }
    
    public void c(final BaseImplementation$b<Players$LoadXpStreamResult> baseImplementation$b, final String s, final int n) {
        try {
            this.gS().b(new GamesClientImpl$PlayerXpStreamLoadedBinderCallback(baseImplementation$b), s, n);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void c(final BaseImplementation$b<GamesMetadata$LoadExtendedGamesResult> baseImplementation$b, final String s, final int n, final boolean b, final boolean b2) {
        try {
            this.gS().e(new GamesClientImpl$ExtendedGamesLoadedBinderCallback(baseImplementation$b), s, n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void c(final BaseImplementation$b<TurnBasedMultiplayer$InitiateMatchResult> baseImplementation$b, final String s, final String s2) {
        try {
            this.gS().d(new GamesClientImpl$TurnBasedMatchInitiatedBinderCallbacks(baseImplementation$b), s, s2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void c(final BaseImplementation$b<Snapshots$LoadSnapshotsResult> baseImplementation$b, final String s, final String s2, final boolean b) {
        try {
            this.gS().c(new GamesClientImpl$SnapshotsLoadedBinderCallbacks(baseImplementation$b), s, s2, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void c(final BaseImplementation$b<Leaderboards$LeaderboardMetadataResult> baseImplementation$b, final String s, final boolean b) {
        try {
            this.gS().d(new GamesClientImpl$LeaderboardsLoadedBinderCallback(baseImplementation$b), s, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void c(final BaseImplementation$b<Achievements$LoadAchievementsResult> baseImplementation$b, final boolean b) {
        try {
            this.gS().a(new GamesClientImpl$AchievementsLoadedBinderCallback(baseImplementation$b), b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void c(final BaseImplementation$b<Requests$UpdateRequestsResult> baseImplementation$b, final String[] array) {
        try {
            this.gS().b(new GamesClientImpl$RequestsUpdatedBinderCallbacks(baseImplementation$b), array);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    @Override
    protected void c(final String... array) {
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
            n.a(!b2, "Cannot have both %s and %s!", "https://www.googleapis.com/auth/games", "https://www.googleapis.com/auth/games.firstparty");
            return;
        }
        n.a(b2, "Games APIs requires %s to function.", "https://www.googleapis.com/auth/games");
    }
    
    @Override
    public void connect() {
        this.jW();
        super.connect();
    }
    
    public int d(final byte[] array, final String s) {
        try {
            return this.gS().b(array, s, null);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return -1;
        }
    }
    
    public void d(final BaseImplementation$b<Players$LoadPlayersResult> baseImplementation$b, final int n, final boolean b, final boolean b2) {
        try {
            this.gS().e(new GamesClientImpl$PlayersLoadedBinderCallback(baseImplementation$b), n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void d(final BaseImplementation$b<TurnBasedMultiplayer$InitiateMatchResult> baseImplementation$b, final String s) {
        try {
            this.gS().l(new GamesClientImpl$TurnBasedMatchInitiatedBinderCallbacks(baseImplementation$b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void d(final BaseImplementation$b<Players$LoadXpStreamResult> baseImplementation$b, final String s, final int n) {
        try {
            this.gS().c(new GamesClientImpl$PlayerXpStreamLoadedBinderCallback(baseImplementation$b), s, n);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void d(final BaseImplementation$b<GamesMetadata$LoadExtendedGamesResult> baseImplementation$b, final String s, final int n, final boolean b, final boolean b2) {
        try {
            this.gS().f(new GamesClientImpl$ExtendedGamesLoadedBinderCallback(baseImplementation$b), s, n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void d(final BaseImplementation$b<TurnBasedMultiplayer$InitiateMatchResult> baseImplementation$b, final String s, final String s2) {
        try {
            this.gS().e(new GamesClientImpl$TurnBasedMatchInitiatedBinderCallbacks(baseImplementation$b), s, s2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void d(final BaseImplementation$b<Notifications$GameMuteStatusChangeResult> baseImplementation$b, final String s, final boolean b) {
        try {
            this.gS().a(new GamesClientImpl$GameMuteStatusChangedBinderCallback(baseImplementation$b), s, b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void d(final BaseImplementation$b<Events$LoadEventsResult> baseImplementation$b, final boolean b) {
        try {
            this.Wh.flush();
            this.gS().f(new GamesClientImpl$EventsLoadedBinderCallback(baseImplementation$b), b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void dB(final int gravity) {
        this.Wm.setGravity(gravity);
    }
    
    public void dC(final int n) {
        try {
            this.gS().dC(n);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    @Override
    public void disconnect() {
        this.Wn = false;
        while (true) {
            if (!this.isConnected()) {
                break Label_0043;
            }
            try {
                final IGamesService gamesService = this.gS();
                gamesService.ku();
                this.Wh.flush();
                gamesService.q(this.Wp);
                this.kt();
                super.disconnect();
            }
            catch (RemoteException ex) {
                GamesLog.p("GamesClientImpl", "Failed to notify client disconnect.");
                continue;
            }
            break;
        }
    }
    
    public void e(final BaseImplementation$b<Players$LoadPlayersResult> baseImplementation$b, final int n, final boolean b, final boolean b2) {
        try {
            this.gS().d(new GamesClientImpl$PlayersLoadedBinderCallback(baseImplementation$b), n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void e(final BaseImplementation$b<TurnBasedMultiplayer$InitiateMatchResult> baseImplementation$b, final String s) {
        try {
            this.gS().m(new GamesClientImpl$TurnBasedMatchInitiatedBinderCallbacks(baseImplementation$b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void e(final BaseImplementation$b<Invitations$LoadInvitationsResult> baseImplementation$b, final String s, final int n) {
        try {
            this.gS().b(new GamesClientImpl$InvitationsLoadedBinderCallback(baseImplementation$b), s, n, false);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void e(final BaseImplementation$b<GamesMetadata$LoadExtendedGamesResult> baseImplementation$b, final String s, final int n, final boolean b, final boolean b2) {
        try {
            this.gS().c(new GamesClientImpl$ExtendedGamesLoadedBinderCallback(baseImplementation$b), s, n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void e(final BaseImplementation$b<Snapshots$LoadSnapshotsResult> baseImplementation$b, final boolean b) {
        try {
            this.gS().d(new GamesClientImpl$SnapshotsLoadedBinderCallbacks(baseImplementation$b), b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void f(final BaseImplementation$b<GamesMetadata$LoadGamesResult> baseImplementation$b) {
        try {
            this.gS().d(new GamesClientImpl$GamesLoadedBinderCallback(baseImplementation$b));
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void f(final BaseImplementation$b<TurnBasedMultiplayer$LeaveMatchResult> baseImplementation$b, final String s) {
        try {
            this.gS().o(new GamesClientImpl$TurnBasedMatchLeftBinderCallbacks(baseImplementation$b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void f(final BaseImplementation$b<Requests$LoadRequestSummariesResult> baseImplementation$b, final String s, final int n) {
        try {
            this.gS().a(new GamesClientImpl$RequestSummariesLoadedBinderCallbacks(baseImplementation$b), s, n);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void f(final BaseImplementation$b<Players$LoadPlayersResult> baseImplementation$b, final String s, final int n, final boolean b, final boolean b2) {
        try {
            this.gS().b(new GamesClientImpl$PlayersLoadedBinderCallback(baseImplementation$b), s, n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void f(final BaseImplementation$b<Players$LoadProfileSettingsResult> baseImplementation$b, final boolean b) {
        try {
            this.gS().g(new GamesClientImpl$ProfileSettingsLoadedBinderCallback(baseImplementation$b), b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    @Override
    public Bundle fD() {
        try {
            final Bundle fd = this.gS().fD();
            if (fd != null) {
                fd.setClassLoader(GamesClientImpl.class.getClassLoader());
            }
            return fd;
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public void g(final BaseImplementation$b<Players$LoadOwnerCoverPhotoUrisResult> baseImplementation$b) {
        try {
            this.gS().j(new GamesClientImpl$OwnerCoverPhotoUrisLoadedBinderCallback(baseImplementation$b));
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void g(final BaseImplementation$b<TurnBasedMultiplayer$CancelMatchResult> baseImplementation$b, final String s) {
        try {
            this.gS().n(new GamesClientImpl$TurnBasedMatchCanceledBinderCallbacks(baseImplementation$b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void g(final BaseImplementation$b<Players$LoadPlayersResult> baseImplementation$b, final String s, final int n, final boolean b, final boolean b2) {
        try {
            this.gS().b(new GamesClientImpl$PlayersLoadedBinderCallback(baseImplementation$b), s, null, n, b, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void g(final BaseImplementation$b<Status> baseImplementation$b, final boolean b) {
        try {
            this.gS().h(new GamesClientImpl$ProfileSettingsUpdatedBinderCallback(baseImplementation$b), b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.games.internal.IGamesService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.games.service.START";
    }
    
    public void h(final BaseImplementation$b<Acls$LoadAclResult> baseImplementation$b) {
        try {
            this.gS().h(new GamesClientImpl$NotifyAclLoadedBinderCallback(baseImplementation$b));
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void h(final BaseImplementation$b<TurnBasedMultiplayer$LoadMatchResult> baseImplementation$b, final String s) {
        try {
            this.gS().p(new GamesClientImpl$TurnBasedMatchLoadedBinderCallbacks(baseImplementation$b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void h(final BaseImplementation$b<Notifications$ContactSettingLoadResult> baseImplementation$b, final boolean b) {
        try {
            this.gS().e(new GamesClientImpl$ContactSettingsLoadedBinderCallback(baseImplementation$b), b);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    @Deprecated
    public void i(final BaseImplementation$b<Notifications$ContactSettingLoadResult> baseImplementation$b) {
        try {
            this.gS().e(new GamesClientImpl$ContactSettingsLoadedBinderCallback(baseImplementation$b), false);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void i(final BaseImplementation$b<Quests$AcceptQuestResult> baseImplementation$b, final String s) {
        try {
            this.Wh.flush();
            this.gS().u(new GamesClientImpl$QuestAcceptedBinderCallbacks(baseImplementation$b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void j(final BaseImplementation$b<Notifications$InboxCountResult> baseImplementation$b) {
        try {
            this.gS().t(new GamesClientImpl$InboxCountsLoadedBinderCallback(baseImplementation$b), null);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void j(final BaseImplementation$b<Snapshots$DeleteSnapshotResult> baseImplementation$b, final String s) {
        try {
            this.gS().r(new GamesClientImpl$SnapshotDeletedBinderCallbacks(baseImplementation$b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public String jX() {
        try {
            return this.gS().jX();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public String jY() {
        try {
            return this.gS().jY();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Player jZ() {
        this.dK();
        synchronized (this) {
            Object wk = this.Wk;
            Label_0063: {
                if (wk != null) {
                    break Label_0063;
                }
                try {
                    wk = new PlayerBuffer(this.gS().kw());
                    try {
                        if (((DataBuffer)wk).getCount() > 0) {
                            this.Wk = (PlayerEntity)((PlayerBuffer)wk).get(0).freeze();
                        }
                        ((PlayerBuffer)wk).release();
                        // monitorexit(this)
                        return this.Wk;
                    }
                    finally {
                        ((PlayerBuffer)wk).release();
                    }
                }
                catch (RemoteException ex) {
                    GamesLog.p("GamesClientImpl", "service died");
                }
            }
        }
    }
    
    public void k(final View view) {
        this.Wm.l(view);
    }
    
    public void k(final BaseImplementation$b<GamesMetadata$LoadExtendedGamesResult> baseImplementation$b, final String s) {
        try {
            this.gS().e(new GamesClientImpl$ExtendedGamesLoadedBinderCallback(baseImplementation$b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public Game ka() {
        this.dK();
        synchronized (this) {
            Object wl = this.Wl;
            Label_0063: {
                if (wl != null) {
                    break Label_0063;
                }
                try {
                    wl = new GameBuffer(this.gS().ky());
                    try {
                        if (((DataBuffer)wl).getCount() > 0) {
                            this.Wl = (GameEntity)((GameBuffer)wl).get(0).freeze();
                        }
                        ((GameBuffer)wl).release();
                        // monitorexit(this)
                        return this.Wl;
                    }
                    finally {
                        ((GameBuffer)wl).release();
                    }
                }
                catch (RemoteException ex) {
                    GamesLog.p("GamesClientImpl", "service died");
                }
            }
        }
    }
    
    public Intent kb() {
        try {
            return this.gS().kb();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent kc() {
        try {
            return this.gS().kc();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent kd() {
        try {
            return this.gS().kd();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent ke() {
        try {
            return this.gS().ke();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public void kf() {
        try {
            this.gS().r(this.Wp);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void kg() {
        try {
            this.gS().s(this.Wp);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void kh() {
        try {
            this.gS().u(this.Wp);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void ki() {
        try {
            this.gS().t(this.Wp);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public Intent kj() {
        try {
            return this.gS().kj();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public Intent kk() {
        try {
            return this.gS().kk();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public int kl() {
        try {
            return this.gS().kl();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return 4368;
        }
    }
    
    public String km() {
        try {
            return this.gS().km();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public int kn() {
        try {
            return this.gS().kn();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return -1;
        }
    }
    
    public Intent ko() {
        try {
            return this.gS().ko();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return null;
        }
    }
    
    public int kp() {
        try {
            return this.gS().kp();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return -1;
        }
    }
    
    public int kq() {
        try {
            return this.gS().kq();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return -1;
        }
    }
    
    public int kr() {
        try {
            return this.gS().kr();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return -1;
        }
    }
    
    public int ks() {
        try {
            return this.gS().ks();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
            return -1;
        }
    }
    
    public void ku() {
        if (!this.isConnected()) {
            return;
        }
        try {
            this.gS().ku();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void l(final BaseImplementation$b<GamesMetadata$LoadGameInstancesResult> baseImplementation$b, final String s) {
        try {
            this.gS().f(new GamesClientImpl$GameInstancesLoadedBinderCallback(baseImplementation$b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void m(final BaseImplementation$b<GamesMetadata$LoadGameSearchSuggestionsResult> baseImplementation$b, final String s) {
        try {
            this.gS().q(new GamesClientImpl$GameSearchSuggestionsLoadedBinderCallback(baseImplementation$b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void n(final BaseImplementation$b<Players$LoadXpForGameCategoriesResult> baseImplementation$b, final String s) {
        try {
            this.gS().s(new GamesClientImpl$PlayerXpForGameCategoriesLoadedBinderCallback(baseImplementation$b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void n(final String s, final int n) {
        this.Wh.n(s, n);
    }
    
    public void o(final BaseImplementation$b<Invitations$LoadInvitationsResult> baseImplementation$b, final String s) {
        try {
            this.gS().k(new GamesClientImpl$InvitationsLoadedBinderCallback(baseImplementation$b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void o(final String s, final int n) {
        try {
            this.gS().o(s, n);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        if (this.Wn) {
            this.Wm.kJ();
            this.Wn = false;
        }
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.Wn = false;
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
    }
    
    public void p(final BaseImplementation$b<Status> baseImplementation$b, final String s) {
        try {
            this.gS().j(new GamesClientImpl$NotifyAclUpdatedBinderCallback(baseImplementation$b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void p(final String s, final int n) {
        try {
            this.gS().p(s, n);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void q(final BaseImplementation$b<Notifications$GameMuteStatusLoadResult> baseImplementation$b, final String s) {
        try {
            this.gS().i(new GamesClientImpl$GameMuteStatusLoadedBinderCallback(baseImplementation$b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public RealTimeSocket t(final String s, final String s2) {
        if (s2 == null || !ParticipantUtils.bS(s2)) {
            throw new IllegalArgumentException("Bad participant ID");
        }
        final RealTimeSocket realTimeSocket = this.Wj.get(s2);
        if (realTimeSocket != null) {
            final RealTimeSocket bw = realTimeSocket;
            if (!realTimeSocket.isClosed()) {
                return bw;
            }
        }
        return this.bw(s2);
    }
}
