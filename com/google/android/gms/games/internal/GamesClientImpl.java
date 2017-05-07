// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.g;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.Freezable;
import java.util.Set;
import com.google.android.gms.games.internal.request.RequestUpdateOutcomes;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.snapshot.SnapshotEntity;
import com.google.android.gms.games.OnNearbyPlayerDetectedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer;
import com.google.android.gms.games.internal.experience.ExperienceEventBuffer;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardEntity;
import com.google.android.gms.games.internal.constants.RequestType;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreEntity;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.internal.game.GameInstanceBuffer;
import com.google.android.gms.games.internal.game.ExtendedGameBuffer;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import java.util.List;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.QuestEntity;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.ParticipantUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.Player;
import android.os.IInterface;
import com.google.android.gms.games.internal.game.Acls;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.common.internal.j;
import java.util.ArrayList;
import com.google.android.gms.common.internal.k;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.data.a;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.GamesMetadata;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import android.os.Parcelable;
import android.graphics.Bitmap;
import android.content.Intent;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
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
import com.google.android.gms.games.internal.events.EventIncrementCache;
import android.view.View;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.games.Games;
import android.os.Binder;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import java.util.Map;
import com.google.android.gms.games.internal.events.EventIncrementManager;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.d;

public final class GamesClientImpl extends d<IGamesService> implements GoogleApiClient.ConnectionCallbacks, OnConnectionFailedListener
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
    private final Games.GamesOptions Wq;
    
    public GamesClientImpl(final Context context, final Looper looper, final String wi, final String s, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener, final String[] array, final int n, final View view, final Games.GamesOptions wq) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, array);
        this.Wh = new EventIncrementManager() {
            public EventIncrementCache kv() {
                return new GameClientEventIncrementCache();
            }
        };
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
            goto Label_0062;
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
    
    public int a(final RealTimeMultiplayer.ReliableMessageSentCallback reliableMessageSentCallback, final byte[] array, final String s, final String s2) {
        try {
            return this.gS().a(new RealTimeReliableMessageBinderCallbacks(reliableMessageSentCallback), array, s, s2);
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
    
    public void a(final BaseImplementation.b<Requests.LoadRequestsResult> b, final int n, final int n2, final int n3) {
        try {
            this.gS().a(new RequestsLoadedBinderCallbacks(b), n, n2, n3);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> b, final int n, final int n2, final boolean b2, final boolean b3) {
        try {
            this.gS().a(new ExtendedGamesLoadedBinderCallback(b), n, n2, b2, b3);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Players.LoadPlayersResult> b, final int n, final boolean b2, final boolean b3) {
        try {
            this.gS().a(new PlayersLoadedBinderCallback(b), n, b2, b3);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<TurnBasedMultiplayer.LoadMatchesResult> b, final int n, final int[] array) {
        try {
            this.gS().a(new TurnBasedMatchesLoadedBinderCallbacks(b), n, array);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Leaderboards.LoadScoresResult> b, final LeaderboardScoreBuffer leaderboardScoreBuffer, final int n, final int n2) {
        try {
            this.gS().a(new LeaderboardScoresLoadedBinderCallback(b), leaderboardScoreBuffer.ly().lz(), n, n2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<TurnBasedMultiplayer.InitiateMatchResult> b, final TurnBasedMatchConfig turnBasedMatchConfig) {
        try {
            this.gS().a(new TurnBasedMatchInitiatedBinderCallbacks(b), turnBasedMatchConfig.getVariant(), turnBasedMatchConfig.lF(), turnBasedMatchConfig.getInvitedPlayerIds(), turnBasedMatchConfig.getAutoMatchCriteria());
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Snapshots.CommitSnapshotResult> b, final Snapshot snapshot, final SnapshotMetadataChange snapshotMetadataChange) {
        final SnapshotContents snapshotContents = snapshot.getSnapshotContents();
        Label_0098: {
            if (snapshotContents.isClosed()) {
                break Label_0098;
            }
            boolean b2 = true;
            while (true) {
                n.a(b2, (Object)"Snapshot already closed");
                final com.google.android.gms.common.data.a lk = snapshotMetadataChange.lK();
                if (lk != null) {
                    lk.a(this.getContext().getCacheDir());
                }
                final Contents contents = snapshotContents.getContents();
                snapshotContents.close();
                try {
                    this.gS().a(new SnapshotCommittedBinderCallbacks(b), snapshot.getMetadata().getSnapshotId(), snapshotMetadataChange, contents);
                    return;
                    b2 = false;
                    continue;
                }
                catch (RemoteException ex) {
                    GamesLog.p("GamesClientImpl", "service died");
                }
                break;
            }
        }
    }
    
    public void a(final BaseImplementation.b<Players.LoadPlayersResult> b, final String s) {
        try {
            this.gS().a(new PlayersLoadedBinderCallback(b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Achievements.UpdateAchievementResult> b, final String s, final int n) {
        Label_0036: {
            if (b != null) {
                break Label_0036;
            }
            IGamesCallbacks gamesCallbacks = null;
            try {
                while (true) {
                    this.gS().a(gamesCallbacks, s, n, this.Wm.kL(), this.Wm.kK());
                    return;
                    gamesCallbacks = new AchievementUpdatedBinderCallback(b);
                    continue;
                }
            }
            catch (RemoteException ex) {
                GamesLog.p("GamesClientImpl", "service died");
            }
        }
    }
    
    public void a(final BaseImplementation.b<Leaderboards.LoadScoresResult> b, final String s, final int n, final int n2, final int n3, final boolean b2) {
        try {
            this.gS().a(new LeaderboardScoresLoadedBinderCallback(b), s, n, n2, n3, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Players.LoadPlayersResult> b, final String s, final int n, final boolean b2) {
        try {
            this.gS().a(new PlayersLoadedBinderCallback(b), s, n, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Players.LoadPlayersResult> b, final String s, final int n, final boolean b2, final boolean b3) {
        switch (s) {
            default: {
                throw new IllegalArgumentException("Invalid player collection: " + s);
            }
            case "played_with": {
                try {
                    this.gS().d(new PlayersLoadedBinderCallback(b), s, n, b2, b3);
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
    
    public void a(final BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> b, final String s, final int n, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        try {
            this.gS().a(new ExtendedGamesLoadedBinderCallback(b), s, n, b2, b3, b4, b5);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<TurnBasedMultiplayer.LoadMatchesResult> b, final String s, final int n, final int[] array) {
        try {
            this.gS().a(new TurnBasedMatchesLoadedBinderCallbacks(b), s, n, array);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Leaderboards.SubmitScoreResult> b, final String s, final long n, final String s2) {
        Label_0024: {
            if (b != null) {
                break Label_0024;
            }
            IGamesCallbacks gamesCallbacks = null;
            try {
                while (true) {
                    this.gS().a(gamesCallbacks, s, n, s2);
                    return;
                    gamesCallbacks = new SubmitScoreBinderCallbacks(b);
                    continue;
                }
            }
            catch (RemoteException ex) {
                GamesLog.p("GamesClientImpl", "service died");
            }
        }
    }
    
    public void a(final BaseImplementation.b<TurnBasedMultiplayer.LeaveMatchResult> b, final String s, final String s2) {
        try {
            this.gS().c(new TurnBasedMatchLeftBinderCallbacks(b), s, s2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Leaderboards.LoadPlayerScoreResult> b, final String s, final String s2, final int n, final int n2) {
        try {
            this.gS().a(new PlayerLeaderboardScoreLoadedBinderCallback(b), s, s2, n, n2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Requests.LoadRequestsResult> b, final String s, final String s2, final int n, final int n2, final int n3) {
        try {
            this.gS().a(new RequestsLoadedBinderCallbacks(b), s, s2, n, n2, n3);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Leaderboards.LoadScoresResult> b, final String s, final String s2, final int n, final int n2, final int n3, final boolean b2) {
        try {
            this.gS().a(new LeaderboardScoresLoadedBinderCallback(b), s, s2, n, n2, n3, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Players.LoadPlayersResult> b, final String s, final String s2, final int n, final boolean b2, final boolean b3) {
        switch (s) {
            default: {
                throw new IllegalArgumentException("Invalid player collection: " + s);
            }
            case "circled":
            case "played_with":
            case "nearby": {
                try {
                    this.gS().a(new PlayersLoadedBinderCallback(b), s, s2, n, b2, b3);
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
    
    public void a(final BaseImplementation.b<Snapshots.OpenSnapshotResult> b, final String s, final String s2, final SnapshotMetadataChange snapshotMetadataChange, final SnapshotContents snapshotContents) {
        Label_0083: {
            if (snapshotContents.isClosed()) {
                break Label_0083;
            }
            boolean b2 = true;
            while (true) {
                n.a(b2, (Object)"SnapshotContents already closed");
                final com.google.android.gms.common.data.a lk = snapshotMetadataChange.lK();
                if (lk != null) {
                    lk.a(this.getContext().getCacheDir());
                }
                final Contents contents = snapshotContents.getContents();
                snapshotContents.close();
                try {
                    this.gS().a(new SnapshotOpenedBinderCallbacks(b), s, s2, snapshotMetadataChange, contents);
                    return;
                    b2 = false;
                    continue;
                }
                catch (RemoteException ex) {
                    GamesLog.p("GamesClientImpl", "service died");
                }
                break;
            }
        }
    }
    
    public void a(final BaseImplementation.b<Leaderboards.LeaderboardMetadataResult> b, final String s, final String s2, final boolean b2) {
        try {
            this.gS().b(new LeaderboardsLoadedBinderCallback(b), s, s2, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Quests.LoadQuestsResult> b, final String s, final String s2, final boolean b2, final String[] array) {
        try {
            this.Wh.flush();
            this.gS().a(new QuestsLoadedBinderCallbacks(b), s, s2, array, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Quests.LoadQuestsResult> b, final String s, final String s2, final int[] array, final int n, final boolean b2) {
        try {
            this.Wh.flush();
            this.gS().a(new QuestsLoadedBinderCallbacks(b), s, s2, array, n, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Requests.UpdateRequestsResult> b, final String s, final String s2, final String[] array) {
        try {
            this.gS().a(new RequestsUpdatedBinderCallbacks(b), s, s2, array);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Leaderboards.LeaderboardMetadataResult> b, final String s, final boolean b2) {
        try {
            this.gS().c(new LeaderboardsLoadedBinderCallback(b), s, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<TurnBasedMultiplayer.UpdateMatchResult> b, final String s, final byte[] array, final String s2, final ParticipantResult[] array2) {
        try {
            this.gS().a(new TurnBasedMatchUpdatedBinderCallbacks(b), s, array, s2, array2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<TurnBasedMultiplayer.UpdateMatchResult> b, final String s, final byte[] array, final ParticipantResult[] array2) {
        try {
            this.gS().a(new TurnBasedMatchUpdatedBinderCallbacks(b), s, array, array2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Requests.SendRequestResult> b, final String s, final String[] array, final int n, final byte[] array2, final int n2) {
        try {
            this.gS().a(new RequestSentBinderCallbacks(b), s, array, n, array2, n2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Players.LoadPlayersResult> b, final boolean b2) {
        try {
            this.gS().c(new PlayersLoadedBinderCallback(b), b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Status> b, final boolean b2, final Bundle bundle) {
        try {
            this.gS().a(new ContactSettingsUpdatedBinderCallback(b), b2, bundle);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Events.LoadEventsResult> b, final boolean b2, final String... array) {
        try {
            this.Wh.flush();
            this.gS().a(new EventsLoadedBinderCallback(b), b2, array);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Quests.LoadQuestsResult> b, final int[] array, final int n, final boolean b2) {
        try {
            this.Wh.flush();
            this.gS().a(new QuestsLoadedBinderCallbacks(b), array, n, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final BaseImplementation.b<Players.LoadPlayersResult> b, final String[] array) {
        try {
            this.gS().c(new PlayersLoadedBinderCallback(b), array);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    @Override
    protected void a(final k k, final e e) throws RemoteException {
        final String string = this.getContext().getResources().getConfiguration().locale.toString();
        final Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.games.key.isHeadless", this.Wq.Vs);
        bundle.putBoolean("com.google.android.gms.games.key.showConnectingPopup", this.Wq.Vt);
        bundle.putInt("com.google.android.gms.games.key.connectingPopupGravity", this.Wq.Vu);
        bundle.putBoolean("com.google.android.gms.games.key.retryingSignIn", this.Wq.Vv);
        bundle.putInt("com.google.android.gms.games.key.sdkVariant", this.Wq.Vw);
        bundle.putString("com.google.android.gms.games.key.forceResolveAccountKey", this.Wq.Vx);
        bundle.putStringArrayList("com.google.android.gms.games.key.proxyApis", (ArrayList)this.Wq.Vy);
        k.a(e, 6111000, this.getContext().getPackageName(), this.Dd, this.gR(), this.Wi, this.Wm.kL(), string, bundle);
    }
    
    public void a(final OnInvitationReceivedListener onInvitationReceivedListener) {
        try {
            this.gS().a(new InvitationReceivedBinderCallback(onInvitationReceivedListener), this.Wp);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final RoomConfig roomConfig) {
        this.kt();
        try {
            this.gS().a(new RoomBinderCallbacks(roomConfig.getRoomUpdateListener(), roomConfig.getRoomStatusUpdateListener(), roomConfig.getMessageReceivedListener()), (IBinder)this.Wo, roomConfig.getVariant(), roomConfig.getInvitedPlayerIds(), roomConfig.getAutoMatchCriteria(), roomConfig.isSocketEnabled(), this.Wp);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final RoomUpdateListener roomUpdateListener, final String s) {
        try {
            this.gS().c(new RoomBinderCallbacks(roomUpdateListener), s);
            this.kt();
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
        try {
            this.gS().b(new MatchUpdateReceivedBinderCallback(onTurnBasedMatchUpdateReceivedListener), this.Wp);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final QuestUpdateListener questUpdateListener) {
        try {
            this.gS().d(new QuestUpdateBinderCallback(questUpdateListener), this.Wp);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void a(final OnRequestReceivedListener onRequestReceivedListener) {
        try {
            this.gS().c(new RequestReceivedBinderCallback(onRequestReceivedListener), this.Wp);
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
        return IGamesService.Stub.aB(binder);
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
    
    public void b(final BaseImplementation.b<Status> b) {
        try {
            this.Wh.flush();
            this.gS().a(new SignOutCompleteBinderCallbacks(b));
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation.b<Players.LoadPlayersResult> b, final int n, final boolean b2, final boolean b3) {
        try {
            this.gS().b(new PlayersLoadedBinderCallback(b), n, b2, b3);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation.b<Achievements.UpdateAchievementResult> b, final String s) {
        Label_0035: {
            if (b != null) {
                break Label_0035;
            }
            IGamesCallbacks gamesCallbacks = null;
            try {
                while (true) {
                    this.gS().a(gamesCallbacks, s, this.Wm.kL(), this.Wm.kK());
                    return;
                    gamesCallbacks = new AchievementUpdatedBinderCallback(b);
                    continue;
                }
            }
            catch (RemoteException ex) {
                GamesLog.p("GamesClientImpl", "service died");
            }
        }
    }
    
    public void b(final BaseImplementation.b<Achievements.UpdateAchievementResult> b, final String s, final int n) {
        Label_0036: {
            if (b != null) {
                break Label_0036;
            }
            IGamesCallbacks gamesCallbacks = null;
            try {
                while (true) {
                    this.gS().b(gamesCallbacks, s, n, this.Wm.kL(), this.Wm.kK());
                    return;
                    gamesCallbacks = new AchievementUpdatedBinderCallback(b);
                    continue;
                }
            }
            catch (RemoteException ex) {
                GamesLog.p("GamesClientImpl", "service died");
            }
        }
    }
    
    public void b(final BaseImplementation.b<Leaderboards.LoadScoresResult> b, final String s, final int n, final int n2, final int n3, final boolean b2) {
        try {
            this.gS().b(new LeaderboardScoresLoadedBinderCallback(b), s, n, n2, n3, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> b, final String s, final int n, final boolean b2, final boolean b3) {
        try {
            this.gS().a(new ExtendedGamesLoadedBinderCallback(b), s, n, b2, b3);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation.b<Quests.ClaimMilestoneResult> b, final String s, final String s2) {
        try {
            this.Wh.flush();
            this.gS().f(new QuestMilestoneClaimBinderCallbacks(b, s2), s, s2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation.b<Leaderboards.LoadScoresResult> b, final String s, final String s2, final int n, final int n2, final int n3, final boolean b2) {
        try {
            this.gS().b(new LeaderboardScoresLoadedBinderCallback(b), s, s2, n, n2, n3, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation.b<Achievements.LoadAchievementsResult> b, final String s, final String s2, final boolean b2) {
        try {
            this.gS().a(new AchievementsLoadedBinderCallback(b), s, s2, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation.b<Snapshots.OpenSnapshotResult> b, final String s, final boolean b2) {
        try {
            this.gS().e(new SnapshotOpenedBinderCallbacks(b), s, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation.b<Leaderboards.LeaderboardMetadataResult> b, final boolean b2) {
        try {
            this.gS().b(new LeaderboardsLoadedBinderCallback(b), b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation.b<Quests.LoadQuestsResult> b, final boolean b2, final String[] array) {
        try {
            this.Wh.flush();
            this.gS().a(new QuestsLoadedBinderCallbacks(b), array, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final BaseImplementation.b<Requests.UpdateRequestsResult> b, final String[] array) {
        try {
            this.gS().a(new RequestsUpdatedBinderCallbacks(b), array);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void b(final RoomConfig roomConfig) {
        this.kt();
        try {
            this.gS().a(new RoomBinderCallbacks(roomConfig.getRoomUpdateListener(), roomConfig.getRoomStatusUpdateListener(), roomConfig.getMessageReceivedListener()), (IBinder)this.Wo, roomConfig.getInvitationId(), roomConfig.isSocketEnabled(), this.Wp);
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
    
    public void c(final BaseImplementation.b<Invitations.LoadInvitationsResult> b, final int n) {
        try {
            this.gS().a(new InvitationsLoadedBinderCallback(b), n);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void c(final BaseImplementation.b<Players.LoadPlayersResult> b, final int n, final boolean b2, final boolean b3) {
        try {
            this.gS().c(new PlayersLoadedBinderCallback(b), n, b2, b3);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void c(final BaseImplementation.b<Achievements.UpdateAchievementResult> b, final String s) {
        Label_0035: {
            if (b != null) {
                break Label_0035;
            }
            IGamesCallbacks gamesCallbacks = null;
            try {
                while (true) {
                    this.gS().b(gamesCallbacks, s, this.Wm.kL(), this.Wm.kK());
                    return;
                    gamesCallbacks = new AchievementUpdatedBinderCallback(b);
                    continue;
                }
            }
            catch (RemoteException ex) {
                GamesLog.p("GamesClientImpl", "service died");
            }
        }
    }
    
    public void c(final BaseImplementation.b<Players.LoadXpStreamResult> b, final String s, final int n) {
        try {
            this.gS().b(new PlayerXpStreamLoadedBinderCallback(b), s, n);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void c(final BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> b, final String s, final int n, final boolean b2, final boolean b3) {
        try {
            this.gS().e(new ExtendedGamesLoadedBinderCallback(b), s, n, b2, b3);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void c(final BaseImplementation.b<TurnBasedMultiplayer.InitiateMatchResult> b, final String s, final String s2) {
        try {
            this.gS().d(new TurnBasedMatchInitiatedBinderCallbacks(b), s, s2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void c(final BaseImplementation.b<Snapshots.LoadSnapshotsResult> b, final String s, final String s2, final boolean b2) {
        try {
            this.gS().c(new SnapshotsLoadedBinderCallbacks(b), s, s2, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void c(final BaseImplementation.b<Leaderboards.LeaderboardMetadataResult> b, final String s, final boolean b2) {
        try {
            this.gS().d(new LeaderboardsLoadedBinderCallback(b), s, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void c(final BaseImplementation.b<Achievements.LoadAchievementsResult> b, final boolean b2) {
        try {
            this.gS().a(new AchievementsLoadedBinderCallback(b), b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void c(final BaseImplementation.b<Requests.UpdateRequestsResult> b, final String[] array) {
        try {
            this.gS().b(new RequestsUpdatedBinderCallbacks(b), array);
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
    
    public void d(final BaseImplementation.b<Players.LoadPlayersResult> b, final int n, final boolean b2, final boolean b3) {
        try {
            this.gS().e(new PlayersLoadedBinderCallback(b), n, b2, b3);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void d(final BaseImplementation.b<TurnBasedMultiplayer.InitiateMatchResult> b, final String s) {
        try {
            this.gS().l(new TurnBasedMatchInitiatedBinderCallbacks(b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void d(final BaseImplementation.b<Players.LoadXpStreamResult> b, final String s, final int n) {
        try {
            this.gS().c(new PlayerXpStreamLoadedBinderCallback(b), s, n);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void d(final BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> b, final String s, final int n, final boolean b2, final boolean b3) {
        try {
            this.gS().f(new ExtendedGamesLoadedBinderCallback(b), s, n, b2, b3);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void d(final BaseImplementation.b<TurnBasedMultiplayer.InitiateMatchResult> b, final String s, final String s2) {
        try {
            this.gS().e(new TurnBasedMatchInitiatedBinderCallbacks(b), s, s2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void d(final BaseImplementation.b<Notifications.GameMuteStatusChangeResult> b, final String s, final boolean b2) {
        try {
            this.gS().a(new GameMuteStatusChangedBinderCallback(b), s, b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void d(final BaseImplementation.b<Events.LoadEventsResult> b, final boolean b2) {
        try {
            this.Wh.flush();
            this.gS().f(new EventsLoadedBinderCallback(b), b2);
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
    
    public void e(final BaseImplementation.b<Players.LoadPlayersResult> b, final int n, final boolean b2, final boolean b3) {
        try {
            this.gS().d(new PlayersLoadedBinderCallback(b), n, b2, b3);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void e(final BaseImplementation.b<TurnBasedMultiplayer.InitiateMatchResult> b, final String s) {
        try {
            this.gS().m(new TurnBasedMatchInitiatedBinderCallbacks(b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void e(final BaseImplementation.b<Invitations.LoadInvitationsResult> b, final String s, final int n) {
        try {
            this.gS().b(new InvitationsLoadedBinderCallback(b), s, n, false);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void e(final BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> b, final String s, final int n, final boolean b2, final boolean b3) {
        try {
            this.gS().c(new ExtendedGamesLoadedBinderCallback(b), s, n, b2, b3);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void e(final BaseImplementation.b<Snapshots.LoadSnapshotsResult> b, final boolean b2) {
        try {
            this.gS().d(new SnapshotsLoadedBinderCallbacks(b), b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void f(final BaseImplementation.b<GamesMetadata.LoadGamesResult> b) {
        try {
            this.gS().d(new GamesLoadedBinderCallback(b));
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void f(final BaseImplementation.b<TurnBasedMultiplayer.LeaveMatchResult> b, final String s) {
        try {
            this.gS().o(new TurnBasedMatchLeftBinderCallbacks(b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void f(final BaseImplementation.b<Requests.LoadRequestSummariesResult> b, final String s, final int n) {
        try {
            this.gS().a(new RequestSummariesLoadedBinderCallbacks(b), s, n);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void f(final BaseImplementation.b<Players.LoadPlayersResult> b, final String s, final int n, final boolean b2, final boolean b3) {
        try {
            this.gS().b(new PlayersLoadedBinderCallback(b), s, n, b2, b3);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void f(final BaseImplementation.b<Players.LoadProfileSettingsResult> b, final boolean b2) {
        try {
            this.gS().g(new ProfileSettingsLoadedBinderCallback(b), b2);
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
    
    public void g(final BaseImplementation.b<Players.LoadOwnerCoverPhotoUrisResult> b) {
        try {
            this.gS().j(new OwnerCoverPhotoUrisLoadedBinderCallback(b));
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void g(final BaseImplementation.b<TurnBasedMultiplayer.CancelMatchResult> b, final String s) {
        try {
            this.gS().n(new TurnBasedMatchCanceledBinderCallbacks(b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void g(final BaseImplementation.b<Players.LoadPlayersResult> b, final String s, final int n, final boolean b2, final boolean b3) {
        try {
            this.gS().b(new PlayersLoadedBinderCallback(b), s, null, n, b2, b3);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void g(final BaseImplementation.b<Status> b, final boolean b2) {
        try {
            this.gS().h(new ProfileSettingsUpdatedBinderCallback(b), b2);
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
    
    public void h(final BaseImplementation.b<Acls.LoadAclResult> b) {
        try {
            this.gS().h(new NotifyAclLoadedBinderCallback(b));
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void h(final BaseImplementation.b<TurnBasedMultiplayer.LoadMatchResult> b, final String s) {
        try {
            this.gS().p(new TurnBasedMatchLoadedBinderCallbacks(b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void h(final BaseImplementation.b<Notifications.ContactSettingLoadResult> b, final boolean b2) {
        try {
            this.gS().e(new ContactSettingsLoadedBinderCallback(b), b2);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    @Deprecated
    public void i(final BaseImplementation.b<Notifications.ContactSettingLoadResult> b) {
        try {
            this.gS().e(new ContactSettingsLoadedBinderCallback(b), false);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void i(final BaseImplementation.b<Quests.AcceptQuestResult> b, final String s) {
        try {
            this.Wh.flush();
            this.gS().u(new QuestAcceptedBinderCallbacks(b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void j(final BaseImplementation.b<Notifications.InboxCountResult> b) {
        try {
            this.gS().t(new InboxCountsLoadedBinderCallback(b), null);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void j(final BaseImplementation.b<Snapshots.DeleteSnapshotResult> b, final String s) {
        try {
            this.gS().r(new SnapshotDeletedBinderCallbacks(b), s);
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
    
    public void k(final BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> b, final String s) {
        try {
            this.gS().e(new ExtendedGamesLoadedBinderCallback(b), s);
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
    
    public void l(final BaseImplementation.b<GamesMetadata.LoadGameInstancesResult> b, final String s) {
        try {
            this.gS().f(new GameInstancesLoadedBinderCallback(b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void m(final BaseImplementation.b<GamesMetadata.LoadGameSearchSuggestionsResult> b, final String s) {
        try {
            this.gS().q(new GameSearchSuggestionsLoadedBinderCallback(b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void n(final BaseImplementation.b<Players.LoadXpForGameCategoriesResult> b, final String s) {
        try {
            this.gS().s(new PlayerXpForGameCategoriesLoadedBinderCallback(b), s);
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
    
    public void n(final String s, final int n) {
        this.Wh.n(s, n);
    }
    
    public void o(final BaseImplementation.b<Invitations.LoadInvitationsResult> b, final String s) {
        try {
            this.gS().k(new InvitationsLoadedBinderCallback(b), s);
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
    
    public void p(final BaseImplementation.b<Status> b, final String s) {
        try {
            this.gS().j(new NotifyAclUpdatedBinderCallback(b), s);
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
    
    public void q(final BaseImplementation.b<Notifications.GameMuteStatusLoadResult> b, final String s) {
        try {
            this.gS().i(new GameMuteStatusLoadedBinderCallback(b), s);
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
    
    private abstract class AbstractPeerStatusCallback extends AbstractRoomStatusCallback
    {
        private final ArrayList<String> Ws;
        
        AbstractPeerStatusCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder);
            this.Ws = new ArrayList<String>();
            for (int i = 0; i < array.length; ++i) {
                this.Ws.add(array[i]);
            }
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            this.a(roomStatusUpdateListener, room, this.Ws);
        }
        
        protected abstract void a(final RoomStatusUpdateListener p0, final Room p1, final ArrayList<String> p2);
    }
    
    private abstract class AbstractRoomCallback extends d<RoomUpdateListener>
    {
        AbstractRoomCallback(final RoomUpdateListener roomUpdateListener, final DataHolder dataHolder) {
            super(roomUpdateListener, dataHolder);
        }
        
        protected void a(final RoomUpdateListener roomUpdateListener, final DataHolder dataHolder) {
            this.a(roomUpdateListener, GamesClientImpl.this.R(dataHolder), dataHolder.getStatusCode());
        }
        
        protected abstract void a(final RoomUpdateListener p0, final Room p1, final int p2);
    }
    
    private abstract class AbstractRoomStatusCallback extends d<RoomStatusUpdateListener>
    {
        AbstractRoomStatusCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            super(roomStatusUpdateListener, dataHolder);
        }
        
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            this.a(roomStatusUpdateListener, GamesClientImpl.this.R(dataHolder));
        }
        
        protected abstract void a(final RoomStatusUpdateListener p0, final Room p1);
    }
    
    private static final class AcceptQuestResultImpl extends com.google.android.gms.common.api.a implements AcceptQuestResult
    {
        private final Quest Wt;
        
        AcceptQuestResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            dataHolder = (DataHolder)new QuestBuffer(dataHolder);
            try {
                if (((com.google.android.gms.common.data.g)dataHolder).getCount() > 0) {
                    this.Wt = new QuestEntity(((com.google.android.gms.common.data.g<Quest>)dataHolder).get(0));
                }
                else {
                    this.Wt = null;
                }
            }
            finally {
                ((DataBuffer)dataHolder).release();
            }
        }
        
        @Override
        public Quest getQuest() {
            return this.Wt;
        }
    }
    
    private final class AchievementUpdatedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Achievements.UpdateAchievementResult> De;
        
        AchievementUpdatedBinderCallback(final BaseImplementation.b<Achievements.UpdateAchievementResult> b) {
            this.De = (BaseImplementation.b<Achievements.UpdateAchievementResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void g(final int n, final String s) {
            this.De.b(new UpdateAchievementResultImpl(n, s));
        }
    }
    
    private final class AchievementsLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Achievements.LoadAchievementsResult> De;
        
        AchievementsLoadedBinderCallback(final BaseImplementation.b<Achievements.LoadAchievementsResult> b) {
            this.De = (BaseImplementation.b<Achievements.LoadAchievementsResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void c(final DataHolder dataHolder) {
            this.De.b(new LoadAchievementsResultImpl(dataHolder));
        }
    }
    
    private static final class CancelMatchResultImpl implements CancelMatchResult
    {
        private final Status CM;
        private final String Wu;
        
        CancelMatchResultImpl(final Status cm, final String wu) {
            this.CM = cm;
            this.Wu = wu;
        }
        
        @Override
        public String getMatchId() {
            return this.Wu;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    private static final class ClaimMilestoneResultImpl extends com.google.android.gms.common.api.a implements ClaimMilestoneResult
    {
        private final Quest Wt;
        private final Milestone Wv;
        
        ClaimMilestoneResultImpl(DataHolder dataHolder, final String s) {
            int i = 0;
            super(dataHolder);
            dataHolder = (DataHolder)new QuestBuffer(dataHolder);
            try {
                if (((com.google.android.gms.common.data.g)dataHolder).getCount() > 0) {
                    this.Wt = new QuestEntity(((com.google.android.gms.common.data.g<Quest>)dataHolder).get(0));
                    for (List<Milestone> lh = this.Wt.lH(); i < lh.size(); ++i) {
                        if (lh.get(i).getMilestoneId().equals(s)) {
                            this.Wv = lh.get(i);
                            return;
                        }
                    }
                    this.Wv = null;
                }
                else {
                    this.Wv = null;
                    this.Wt = null;
                }
            }
            finally {
                ((DataBuffer)dataHolder).release();
            }
        }
        
        @Override
        public Milestone getMilestone() {
            return this.Wv;
        }
        
        @Override
        public Quest getQuest() {
            return this.Wt;
        }
    }
    
    private static final class CommitSnapshotResultImpl extends com.google.android.gms.common.api.a implements CommitSnapshotResult
    {
        private final SnapshotMetadata Ww;
        
        CommitSnapshotResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            dataHolder = (DataHolder)new SnapshotMetadataBuffer(dataHolder);
            try {
                if (((DataBuffer)dataHolder).getCount() > 0) {
                    this.Ww = new SnapshotMetadataEntity(((SnapshotMetadataBuffer)dataHolder).get(0));
                }
                else {
                    this.Ww = null;
                }
            }
            finally {
                ((DataBuffer)dataHolder).release();
            }
        }
        
        @Override
        public SnapshotMetadata getSnapshotMetadata() {
            return this.Ww;
        }
    }
    
    private final class ConnectedToRoomCallback extends AbstractRoomStatusCallback
    {
        ConnectedToRoomCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            super(roomStatusUpdateListener, dataHolder);
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            roomStatusUpdateListener.onConnectedToRoom(room);
        }
    }
    
    private static final class ContactSettingLoadResultImpl extends com.google.android.gms.common.api.a implements ContactSettingLoadResult
    {
        ContactSettingLoadResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
        }
    }
    
    private final class ContactSettingsLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Notifications.ContactSettingLoadResult> De;
        
        ContactSettingsLoadedBinderCallback(final BaseImplementation.b<Notifications.ContactSettingLoadResult> b) {
            this.De = (BaseImplementation.b<Notifications.ContactSettingLoadResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void D(final DataHolder dataHolder) {
            this.De.b(new ContactSettingLoadResultImpl(dataHolder));
        }
    }
    
    private final class ContactSettingsUpdatedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Status> De;
        
        ContactSettingsUpdatedBinderCallback(final BaseImplementation.b<Status> b) {
            this.De = (BaseImplementation.b<Status>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void dy(final int n) {
            this.De.b(new Status(n));
        }
    }
    
    private static final class DeleteSnapshotResultImpl implements DeleteSnapshotResult
    {
        private final Status CM;
        private final String Wx;
        
        DeleteSnapshotResultImpl(final int n, final String wx) {
            this.CM = new Status(n);
            this.Wx = wx;
        }
        
        @Override
        public String getSnapshotId() {
            return this.Wx;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    private final class DisconnectedFromRoomCallback extends AbstractRoomStatusCallback
    {
        DisconnectedFromRoomCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            super(roomStatusUpdateListener, dataHolder);
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            roomStatusUpdateListener.onDisconnectedFromRoom(room);
        }
    }
    
    private final class EventsLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Events.LoadEventsResult> De;
        
        EventsLoadedBinderCallback(final BaseImplementation.b<Events.LoadEventsResult> b) {
            this.De = (BaseImplementation.b<Events.LoadEventsResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void d(final DataHolder dataHolder) {
            this.De.b(new LoadEventResultImpl(dataHolder));
        }
    }
    
    private final class ExtendedGamesLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> De;
        
        ExtendedGamesLoadedBinderCallback(final BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult> b) {
            this.De = (BaseImplementation.b<GamesMetadata.LoadExtendedGamesResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void j(final DataHolder dataHolder) {
            this.De.b(new LoadExtendedGamesResultImpl(dataHolder));
        }
    }
    
    private class GameClientEventIncrementCache extends EventIncrementCache
    {
        public GameClientEventIncrementCache() {
            super(GamesClientImpl.this.getContext().getMainLooper(), 1000);
        }
        
        @Override
        protected void q(final String s, final int n) {
            try {
                if (GamesClientImpl.this.isConnected()) {
                    GamesClientImpl.this.gS().n(s, n);
                    return;
                }
                GamesLog.q("GamesClientImpl", "Unable to increment event " + s + " by " + n + " because the games client is no longer connected");
            }
            catch (RemoteException ex) {
                GamesLog.p("GamesClientImpl", "service died");
            }
        }
    }
    
    private final class GameInstancesLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<GamesMetadata.LoadGameInstancesResult> De;
        
        GameInstancesLoadedBinderCallback(final BaseImplementation.b<GamesMetadata.LoadGameInstancesResult> b) {
            this.De = (BaseImplementation.b<GamesMetadata.LoadGameInstancesResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void k(final DataHolder dataHolder) {
            this.De.b(new LoadGameInstancesResultImpl(dataHolder));
        }
    }
    
    private static final class GameMuteStatusChangeResultImpl implements GameMuteStatusChangeResult
    {
        private final Status CM;
        private final String Wy;
        private final boolean Wz;
        
        public GameMuteStatusChangeResultImpl(final int n, final String wy, final boolean wz) {
            this.CM = new Status(n);
            this.Wy = wy;
            this.Wz = wz;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    private final class GameMuteStatusChangedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Notifications.GameMuteStatusChangeResult> De;
        
        GameMuteStatusChangedBinderCallback(final BaseImplementation.b<Notifications.GameMuteStatusChangeResult> b) {
            this.De = (BaseImplementation.b<Notifications.GameMuteStatusChangeResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void a(final int n, final String s, final boolean b) {
            this.De.b(new GameMuteStatusChangeResultImpl(n, s, b));
        }
    }
    
    private static final class GameMuteStatusLoadResultImpl implements GameMuteStatusLoadResult
    {
        private final Status CM;
        private final String Wy;
        private final boolean Wz;
        
        public GameMuteStatusLoadResultImpl(final DataHolder dataHolder) {
            try {
                this.CM = new Status(dataHolder.getStatusCode());
                if (dataHolder.getCount() > 0) {
                    this.Wy = dataHolder.c("external_game_id", 0, 0);
                    this.Wz = dataHolder.d("muted", 0, 0);
                }
                else {
                    this.Wy = null;
                    this.Wz = false;
                }
            }
            finally {
                dataHolder.close();
            }
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    private final class GameMuteStatusLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Notifications.GameMuteStatusLoadResult> De;
        
        GameMuteStatusLoadedBinderCallback(final BaseImplementation.b<Notifications.GameMuteStatusLoadResult> b) {
            this.De = (BaseImplementation.b<Notifications.GameMuteStatusLoadResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void B(final DataHolder dataHolder) {
            this.De.b(new GameMuteStatusLoadResultImpl(dataHolder));
        }
    }
    
    private final class GameSearchSuggestionsLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<GamesMetadata.LoadGameSearchSuggestionsResult> De;
        
        GameSearchSuggestionsLoadedBinderCallback(final BaseImplementation.b<GamesMetadata.LoadGameSearchSuggestionsResult> b) {
            this.De = (BaseImplementation.b<GamesMetadata.LoadGameSearchSuggestionsResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void l(final DataHolder dataHolder) {
            this.De.b(new LoadGameSearchSuggestionsResultImpl(dataHolder));
        }
    }
    
    private final class GamesLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<GamesMetadata.LoadGamesResult> De;
        
        GamesLoadedBinderCallback(final BaseImplementation.b<GamesMetadata.LoadGamesResult> b) {
            this.De = (BaseImplementation.b<GamesMetadata.LoadGamesResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void i(final DataHolder dataHolder) {
            this.De.b(new LoadGamesResultImpl(dataHolder));
        }
    }
    
    private static final class InboxCountResultImpl implements InboxCountResult
    {
        private final Status CM;
        private final Bundle WA;
        
        InboxCountResultImpl(final Status cm, final Bundle wa) {
            this.CM = cm;
            this.WA = wa;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    private final class InboxCountsLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Notifications.InboxCountResult> De;
        
        InboxCountsLoadedBinderCallback(final BaseImplementation.b<Notifications.InboxCountResult> b) {
            this.De = (BaseImplementation.b<Notifications.InboxCountResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void f(final int n, final Bundle bundle) {
            bundle.setClassLoader(this.getClass().getClassLoader());
            this.De.b(new InboxCountResultImpl(new Status(n), bundle));
        }
    }
    
    private static final class InitiateMatchResultImpl extends TurnBasedMatchResult implements InitiateMatchResult
    {
        InitiateMatchResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
        }
    }
    
    private final class InvitationReceivedBinderCallback extends AbstractGamesCallbacks
    {
        private final OnInvitationReceivedListener WB;
        
        InvitationReceivedBinderCallback(final OnInvitationReceivedListener wb) {
            this.WB = wb;
        }
        
        @Override
        public void n(final DataHolder dataHolder) {
            final InvitationBuffer invitationBuffer = new InvitationBuffer(dataHolder);
            Invitation invitation = null;
            try {
                if (invitationBuffer.getCount() > 0) {
                    invitation = invitationBuffer.get(0).freeze();
                }
                invitationBuffer.release();
                if (invitation != null) {
                    GamesClientImpl.this.a((b<?>)new InvitationReceivedCallback(this.WB, invitation));
                }
            }
            finally {
                invitationBuffer.release();
            }
        }
        
        @Override
        public void onInvitationRemoved(final String s) {
            GamesClientImpl.this.a((b<?>)new InvitationRemovedCallback(this.WB, s));
        }
    }
    
    private final class InvitationReceivedCallback extends b<OnInvitationReceivedListener>
    {
        private final Invitation WC;
        
        InvitationReceivedCallback(final OnInvitationReceivedListener onInvitationReceivedListener, final Invitation wc) {
            super(onInvitationReceivedListener);
            this.WC = wc;
        }
        
        protected void b(final OnInvitationReceivedListener onInvitationReceivedListener) {
            onInvitationReceivedListener.onInvitationReceived(this.WC);
        }
        
        @Override
        protected void gT() {
        }
    }
    
    private final class InvitationRemovedCallback extends b<OnInvitationReceivedListener>
    {
        private final String WD;
        
        InvitationRemovedCallback(final OnInvitationReceivedListener onInvitationReceivedListener, final String wd) {
            super(onInvitationReceivedListener);
            this.WD = wd;
        }
        
        protected void b(final OnInvitationReceivedListener onInvitationReceivedListener) {
            onInvitationReceivedListener.onInvitationRemoved(this.WD);
        }
        
        @Override
        protected void gT() {
        }
    }
    
    private final class InvitationsLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Invitations.LoadInvitationsResult> De;
        
        InvitationsLoadedBinderCallback(final BaseImplementation.b<Invitations.LoadInvitationsResult> b) {
            this.De = (BaseImplementation.b<Invitations.LoadInvitationsResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void m(final DataHolder dataHolder) {
            this.De.b(new LoadInvitationsResultImpl(dataHolder));
        }
    }
    
    private final class JoinedRoomCallback extends AbstractRoomCallback
    {
        public JoinedRoomCallback(final RoomUpdateListener roomUpdateListener, final DataHolder dataHolder) {
            super(roomUpdateListener, dataHolder);
        }
        
        public void a(final RoomUpdateListener roomUpdateListener, final Room room, final int n) {
            roomUpdateListener.onJoinedRoom(n, room);
        }
    }
    
    private static final class LeaderboardMetadataResultImpl extends com.google.android.gms.common.api.a implements LeaderboardMetadataResult
    {
        private final LeaderboardBuffer WE;
        
        LeaderboardMetadataResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
            this.WE = new LeaderboardBuffer(dataHolder);
        }
        
        @Override
        public LeaderboardBuffer getLeaderboards() {
            return this.WE;
        }
    }
    
    private final class LeaderboardScoresLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Leaderboards.LoadScoresResult> De;
        
        LeaderboardScoresLoadedBinderCallback(final BaseImplementation.b<Leaderboards.LoadScoresResult> b) {
            this.De = (BaseImplementation.b<Leaderboards.LoadScoresResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void a(final DataHolder dataHolder, final DataHolder dataHolder2) {
            this.De.b(new LoadScoresResultImpl(dataHolder, dataHolder2));
        }
    }
    
    private final class LeaderboardsLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Leaderboards.LeaderboardMetadataResult> De;
        
        LeaderboardsLoadedBinderCallback(final BaseImplementation.b<Leaderboards.LeaderboardMetadataResult> b) {
            this.De = (BaseImplementation.b<Leaderboards.LeaderboardMetadataResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void e(final DataHolder dataHolder) {
            this.De.b(new LeaderboardMetadataResultImpl(dataHolder));
        }
    }
    
    private static final class LeaveMatchResultImpl extends TurnBasedMatchResult implements LeaveMatchResult
    {
        LeaveMatchResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
        }
    }
    
    private final class LeftRoomCallback extends b<RoomUpdateListener>
    {
        private final int HF;
        private final String WF;
        
        LeftRoomCallback(final RoomUpdateListener roomUpdateListener, final int hf, final String wf) {
            super(roomUpdateListener);
            this.HF = hf;
            this.WF = wf;
        }
        
        public void a(final RoomUpdateListener roomUpdateListener) {
            roomUpdateListener.onLeftRoom(this.HF, this.WF);
        }
        
        @Override
        protected void gT() {
        }
    }
    
    private static final class LoadAchievementsResultImpl extends com.google.android.gms.common.api.a implements LoadAchievementsResult
    {
        private final AchievementBuffer WG;
        
        LoadAchievementsResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
            this.WG = new AchievementBuffer(dataHolder);
        }
        
        @Override
        public AchievementBuffer getAchievements() {
            return this.WG;
        }
    }
    
    private static final class LoadAclResultImpl extends com.google.android.gms.common.api.a implements LoadAclResult
    {
        LoadAclResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
        }
    }
    
    private static final class LoadEventResultImpl extends com.google.android.gms.common.api.a implements LoadEventsResult
    {
        private final EventBuffer WH;
        
        LoadEventResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
            this.WH = new EventBuffer(dataHolder);
        }
        
        @Override
        public EventBuffer getEvents() {
            return this.WH;
        }
    }
    
    private static final class LoadExtendedGamesResultImpl extends com.google.android.gms.common.api.a implements LoadExtendedGamesResult
    {
        private final ExtendedGameBuffer WI;
        
        LoadExtendedGamesResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
            this.WI = new ExtendedGameBuffer(dataHolder);
        }
    }
    
    private static final class LoadGameInstancesResultImpl extends com.google.android.gms.common.api.a implements LoadGameInstancesResult
    {
        private final GameInstanceBuffer WJ;
        
        LoadGameInstancesResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
            this.WJ = new GameInstanceBuffer(dataHolder);
        }
    }
    
    private static final class LoadGameSearchSuggestionsResultImpl extends com.google.android.gms.common.api.a implements LoadGameSearchSuggestionsResult
    {
        LoadGameSearchSuggestionsResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
        }
    }
    
    private static final class LoadGamesResultImpl extends com.google.android.gms.common.api.a implements LoadGamesResult
    {
        private final GameBuffer WK;
        
        LoadGamesResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
            this.WK = new GameBuffer(dataHolder);
        }
        
        @Override
        public GameBuffer getGames() {
            return this.WK;
        }
    }
    
    private static final class LoadInvitationsResultImpl extends com.google.android.gms.common.api.a implements LoadInvitationsResult
    {
        private final InvitationBuffer WL;
        
        LoadInvitationsResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
            this.WL = new InvitationBuffer(dataHolder);
        }
        
        @Override
        public InvitationBuffer getInvitations() {
            return this.WL;
        }
    }
    
    private static final class LoadMatchResultImpl extends TurnBasedMatchResult implements LoadMatchResult
    {
        LoadMatchResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
        }
    }
    
    private static final class LoadMatchesResultImpl implements LoadMatchesResult
    {
        private final Status CM;
        private final LoadMatchesResponse WM;
        
        LoadMatchesResultImpl(final Status cm, final Bundle bundle) {
            this.CM = cm;
            this.WM = new LoadMatchesResponse(bundle);
        }
        
        @Override
        public LoadMatchesResponse getMatches() {
            return this.WM;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
        
        @Override
        public void release() {
            this.WM.close();
        }
    }
    
    private static final class LoadOwnerCoverPhotoUrisResultImpl implements LoadOwnerCoverPhotoUrisResult
    {
        private final Status CM;
        private final Bundle MZ;
        
        LoadOwnerCoverPhotoUrisResultImpl(final int n, final Bundle mz) {
            this.CM = new Status(n);
            this.MZ = mz;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    private static final class LoadPlayerScoreResultImpl extends com.google.android.gms.common.api.a implements LoadPlayerScoreResult
    {
        private final LeaderboardScoreEntity WN;
        
        LoadPlayerScoreResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            dataHolder = (DataHolder)new LeaderboardScoreBuffer(dataHolder);
            try {
                if (((DataBuffer)dataHolder).getCount() > 0) {
                    this.WN = ((Freezable<LeaderboardScoreEntity>)((LeaderboardScoreBuffer)dataHolder).get(0)).freeze();
                }
                else {
                    this.WN = null;
                }
            }
            finally {
                ((DataBuffer)dataHolder).release();
            }
        }
        
        @Override
        public LeaderboardScore getScore() {
            return this.WN;
        }
    }
    
    private static final class LoadPlayersResultImpl extends com.google.android.gms.common.api.a implements LoadPlayersResult
    {
        private final PlayerBuffer WO;
        
        LoadPlayersResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
            this.WO = new PlayerBuffer(dataHolder);
        }
        
        @Override
        public PlayerBuffer getPlayers() {
            return this.WO;
        }
    }
    
    private static final class LoadProfileSettingsResultImpl extends com.google.android.gms.common.api.a implements LoadProfileSettingsResult
    {
        private final boolean WP;
        private final boolean We;
        
        LoadProfileSettingsResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
            try {
                if (dataHolder.getCount() > 0) {
                    final int ar = dataHolder.ar(0);
                    this.We = dataHolder.d("profile_visible", 0, ar);
                    this.WP = dataHolder.d("profile_visibility_explicitly_set", 0, ar);
                }
                else {
                    this.We = true;
                    this.WP = false;
                }
            }
            finally {
                dataHolder.close();
            }
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
        
        @Override
        public boolean isProfileVisible() {
            return this.We;
        }
        
        @Override
        public boolean isVisibilityExplicitlySet() {
            return this.WP;
        }
    }
    
    private static final class LoadQuestsResultImpl extends com.google.android.gms.common.api.a implements LoadQuestsResult
    {
        private final DataHolder IC;
        
        LoadQuestsResultImpl(final DataHolder ic) {
            super(ic);
            this.IC = ic;
        }
        
        @Override
        public QuestBuffer getQuests() {
            return new QuestBuffer(this.IC);
        }
    }
    
    private static final class LoadRequestSummariesResultImpl extends com.google.android.gms.common.api.a implements LoadRequestSummariesResult
    {
        LoadRequestSummariesResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
        }
    }
    
    private static final class LoadRequestsResultImpl implements LoadRequestsResult
    {
        private final Status CM;
        private final Bundle WQ;
        
        LoadRequestsResultImpl(final Status cm, final Bundle wq) {
            this.CM = cm;
            this.WQ = wq;
        }
        
        @Override
        public GameRequestBuffer getRequests(final int n) {
            final String dh = RequestType.dH(n);
            if (!this.WQ.containsKey(dh)) {
                return null;
            }
            return new GameRequestBuffer((DataHolder)this.WQ.get(dh));
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
        
        @Override
        public void release() {
            final Iterator<String> iterator = this.WQ.keySet().iterator();
            while (iterator.hasNext()) {
                final DataHolder dataHolder = (DataHolder)this.WQ.getParcelable((String)iterator.next());
                if (dataHolder != null) {
                    dataHolder.close();
                }
            }
        }
    }
    
    private static final class LoadScoresResultImpl extends com.google.android.gms.common.api.a implements LoadScoresResult
    {
        private final LeaderboardEntity WR;
        private final LeaderboardScoreBuffer WS;
        
        LoadScoresResultImpl(DataHolder dataHolder, final DataHolder dataHolder2) {
            super(dataHolder2);
            dataHolder = (DataHolder)new LeaderboardBuffer(dataHolder);
            try {
                if (((com.google.android.gms.common.data.g)dataHolder).getCount() > 0) {
                    this.WR = ((Freezable<LeaderboardEntity>)((com.google.android.gms.common.data.g<Leaderboard>)dataHolder).get(0)).freeze();
                }
                else {
                    this.WR = null;
                }
                ((DataBuffer)dataHolder).release();
                this.WS = new LeaderboardScoreBuffer(dataHolder2);
            }
            finally {
                ((DataBuffer)dataHolder).release();
            }
        }
        
        @Override
        public Leaderboard getLeaderboard() {
            return this.WR;
        }
        
        @Override
        public LeaderboardScoreBuffer getScores() {
            return this.WS;
        }
    }
    
    private static final class LoadSnapshotsResultImpl extends com.google.android.gms.common.api.a implements LoadSnapshotsResult
    {
        LoadSnapshotsResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
        }
        
        @Override
        public SnapshotMetadataBuffer getSnapshots() {
            return new SnapshotMetadataBuffer(this.IC);
        }
    }
    
    private static final class LoadXpForGameCategoriesResultImpl implements LoadXpForGameCategoriesResult
    {
        private final Status CM;
        private final List<String> WT;
        private final Bundle WU;
        
        LoadXpForGameCategoriesResultImpl(final Status cm, final Bundle wu) {
            this.CM = cm;
            this.WT = (List<String>)wu.getStringArrayList("game_category_list");
            this.WU = wu;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    private static final class LoadXpStreamResultImpl extends com.google.android.gms.common.api.a implements LoadXpStreamResult
    {
        private final ExperienceEventBuffer WV;
        
        LoadXpStreamResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
            this.WV = new ExperienceEventBuffer(dataHolder);
        }
    }
    
    private final class MatchRemovedCallback extends b<OnTurnBasedMatchUpdateReceivedListener>
    {
        private final String WW;
        
        MatchRemovedCallback(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener, final String ww) {
            super(onTurnBasedMatchUpdateReceivedListener);
            this.WW = ww;
        }
        
        protected void b(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
            onTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchRemoved(this.WW);
        }
        
        @Override
        protected void gT() {
        }
    }
    
    private final class MatchUpdateReceivedBinderCallback extends AbstractGamesCallbacks
    {
        private final OnTurnBasedMatchUpdateReceivedListener WX;
        
        MatchUpdateReceivedBinderCallback(final OnTurnBasedMatchUpdateReceivedListener wx) {
            this.WX = wx;
        }
        
        @Override
        public void onTurnBasedMatchRemoved(final String s) {
            GamesClientImpl.this.a((b<?>)new MatchRemovedCallback(this.WX, s));
        }
        
        @Override
        public void t(final DataHolder dataHolder) {
            final TurnBasedMatchBuffer turnBasedMatchBuffer = new TurnBasedMatchBuffer(dataHolder);
            TurnBasedMatch turnBasedMatch = null;
            try {
                if (turnBasedMatchBuffer.getCount() > 0) {
                    turnBasedMatch = turnBasedMatchBuffer.get(0).freeze();
                }
                turnBasedMatchBuffer.release();
                if (turnBasedMatch != null) {
                    GamesClientImpl.this.a((b<?>)new MatchUpdateReceivedCallback(this.WX, turnBasedMatch));
                }
            }
            finally {
                turnBasedMatchBuffer.release();
            }
        }
    }
    
    private final class MatchUpdateReceivedCallback extends b<OnTurnBasedMatchUpdateReceivedListener>
    {
        private final TurnBasedMatch WY;
        
        MatchUpdateReceivedCallback(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener, final TurnBasedMatch wy) {
            super(onTurnBasedMatchUpdateReceivedListener);
            this.WY = wy;
        }
        
        protected void b(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
            onTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchReceived(this.WY);
        }
        
        @Override
        protected void gT() {
        }
    }
    
    private final class MessageReceivedCallback extends b<RealTimeMessageReceivedListener>
    {
        private final RealTimeMessage WZ;
        
        MessageReceivedCallback(final RealTimeMessageReceivedListener realTimeMessageReceivedListener, final RealTimeMessage wz) {
            super(realTimeMessageReceivedListener);
            this.WZ = wz;
        }
        
        public void a(final RealTimeMessageReceivedListener realTimeMessageReceivedListener) {
            if (realTimeMessageReceivedListener != null) {
                realTimeMessageReceivedListener.onRealTimeMessageReceived(this.WZ);
            }
        }
        
        @Override
        protected void gT() {
        }
    }
    
    private final class NearbyPlayerDetectedCallback extends b<OnNearbyPlayerDetectedListener>
    {
        private final Player Xa;
        
        protected void a(final OnNearbyPlayerDetectedListener onNearbyPlayerDetectedListener) {
            onNearbyPlayerDetectedListener.a(this.Xa);
        }
        
        @Override
        protected void gT() {
        }
    }
    
    private final class NotifyAclLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Acls.LoadAclResult> De;
        
        NotifyAclLoadedBinderCallback(final BaseImplementation.b<Acls.LoadAclResult> b) {
            this.De = (BaseImplementation.b<Acls.LoadAclResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void C(final DataHolder dataHolder) {
            this.De.b(new LoadAclResultImpl(dataHolder));
        }
    }
    
    private final class NotifyAclUpdatedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Status> De;
        
        NotifyAclUpdatedBinderCallback(final BaseImplementation.b<Status> b) {
            this.De = (BaseImplementation.b<Status>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void dx(final int n) {
            this.De.b(new Status(n));
        }
    }
    
    private static final class OpenSnapshotResultImpl extends com.google.android.gms.common.api.a implements OpenSnapshotResult
    {
        private final Snapshot Xb;
        private final String Xc;
        private final Snapshot Xd;
        private final Contents Xe;
        private final SnapshotContents Xf;
        
        OpenSnapshotResultImpl(final DataHolder dataHolder, final Contents contents) {
            this(dataHolder, null, contents, null, null);
        }
        
        OpenSnapshotResultImpl(final DataHolder dataHolder, final String xc, final Contents contents, final Contents contents2, final Contents xe) {
        Label_0036_Outer:
            while (true) {
                boolean b = true;
                super(dataHolder);
                final SnapshotMetadataBuffer snapshotMetadataBuffer = new SnapshotMetadataBuffer(dataHolder);
                while (true) {
                    Label_0144: {
                        while (true) {
                            Label_0138: {
                                try {
                                    if (snapshotMetadataBuffer.getCount() == 0) {
                                        this.Xb = null;
                                        this.Xd = null;
                                    }
                                    else {
                                        if (snapshotMetadataBuffer.getCount() != 1) {
                                            break Label_0144;
                                        }
                                        if (dataHolder.getStatusCode() == 4004) {
                                            break Label_0138;
                                        }
                                        com.google.android.gms.common.internal.a.I(b);
                                        this.Xb = new SnapshotEntity(new SnapshotMetadataEntity(snapshotMetadataBuffer.get(0)), new SnapshotContents(contents));
                                        this.Xd = null;
                                    }
                                    snapshotMetadataBuffer.release();
                                    this.Xc = xc;
                                    this.Xe = xe;
                                    this.Xf = new SnapshotContents(xe);
                                    return;
                                }
                                finally {
                                    snapshotMetadataBuffer.release();
                                }
                            }
                            b = false;
                            continue Label_0036_Outer;
                        }
                    }
                    this.Xb = new SnapshotEntity(new SnapshotMetadataEntity(snapshotMetadataBuffer.get(0)), new SnapshotContents(contents));
                    this.Xd = new SnapshotEntity(new SnapshotMetadataEntity(snapshotMetadataBuffer.get(1)), new SnapshotContents(contents2));
                    continue;
                }
            }
        }
        
        @Override
        public String getConflictId() {
            return this.Xc;
        }
        
        @Override
        public Snapshot getConflictingSnapshot() {
            return this.Xd;
        }
        
        @Deprecated
        @Override
        public Contents getResolutionContents() {
            return this.Xe;
        }
        
        @Override
        public SnapshotContents getResolutionSnapshotContents() {
            return this.Xf;
        }
        
        @Override
        public Snapshot getSnapshot() {
            return this.Xb;
        }
    }
    
    private final class OwnerCoverPhotoUrisLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Players.LoadOwnerCoverPhotoUrisResult> De;
        
        OwnerCoverPhotoUrisLoadedBinderCallback(final BaseImplementation.b<Players.LoadOwnerCoverPhotoUrisResult> b) {
            this.De = (BaseImplementation.b<Players.LoadOwnerCoverPhotoUrisResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void d(final int n, final Bundle bundle) {
            bundle.setClassLoader(this.getClass().getClassLoader());
            this.De.b(new LoadOwnerCoverPhotoUrisResultImpl(n, bundle));
        }
    }
    
    private final class P2PConnectedCallback extends b<RoomStatusUpdateListener>
    {
        private final String Xg;
        
        P2PConnectedCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final String xg) {
            super(roomStatusUpdateListener);
            this.Xg = xg;
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener) {
            if (roomStatusUpdateListener != null) {
                roomStatusUpdateListener.onP2PConnected(this.Xg);
            }
        }
        
        @Override
        protected void gT() {
        }
    }
    
    private final class P2PDisconnectedCallback extends b<RoomStatusUpdateListener>
    {
        private final String Xg;
        
        P2PDisconnectedCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final String xg) {
            super(roomStatusUpdateListener);
            this.Xg = xg;
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener) {
            if (roomStatusUpdateListener != null) {
                roomStatusUpdateListener.onP2PDisconnected(this.Xg);
            }
        }
        
        @Override
        protected void gT() {
        }
    }
    
    private final class PeerConnectedCallback extends AbstractPeerStatusCallback
    {
        PeerConnectedCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeersConnected(room, list);
        }
    }
    
    private final class PeerDeclinedCallback extends AbstractPeerStatusCallback
    {
        PeerDeclinedCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeerDeclined(room, list);
        }
    }
    
    private final class PeerDisconnectedCallback extends AbstractPeerStatusCallback
    {
        PeerDisconnectedCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeersDisconnected(room, list);
        }
    }
    
    private final class PeerInvitedToRoomCallback extends AbstractPeerStatusCallback
    {
        PeerInvitedToRoomCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeerInvitedToRoom(room, list);
        }
    }
    
    private final class PeerJoinedRoomCallback extends AbstractPeerStatusCallback
    {
        PeerJoinedRoomCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeerJoined(room, list);
        }
    }
    
    private final class PeerLeftRoomCallback extends AbstractPeerStatusCallback
    {
        PeerLeftRoomCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder, final String[] array) {
            super(roomStatusUpdateListener, dataHolder, array);
        }
        
        @Override
        protected void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeerLeft(room, list);
        }
    }
    
    private final class PlayerLeaderboardScoreLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Leaderboards.LoadPlayerScoreResult> De;
        
        PlayerLeaderboardScoreLoadedBinderCallback(final BaseImplementation.b<Leaderboards.LoadPlayerScoreResult> b) {
            this.De = (BaseImplementation.b<Leaderboards.LoadPlayerScoreResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void E(final DataHolder dataHolder) {
            this.De.b(new LoadPlayerScoreResultImpl(dataHolder));
        }
    }
    
    private final class PlayerXpForGameCategoriesLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Players.LoadXpForGameCategoriesResult> De;
        
        PlayerXpForGameCategoriesLoadedBinderCallback(final BaseImplementation.b<Players.LoadXpForGameCategoriesResult> b) {
            this.De = (BaseImplementation.b<Players.LoadXpForGameCategoriesResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void e(final int n, final Bundle bundle) {
            bundle.setClassLoader(this.getClass().getClassLoader());
            this.De.b(new LoadXpForGameCategoriesResultImpl(new Status(n), bundle));
        }
    }
    
    final class PlayerXpStreamLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Players.LoadXpStreamResult> De;
        
        PlayerXpStreamLoadedBinderCallback(final BaseImplementation.b<Players.LoadXpStreamResult> b) {
            this.De = (BaseImplementation.b<Players.LoadXpStreamResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void P(final DataHolder dataHolder) {
            this.De.b(new LoadXpStreamResultImpl(dataHolder));
        }
    }
    
    private final class PlayersLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Players.LoadPlayersResult> De;
        
        PlayersLoadedBinderCallback(final BaseImplementation.b<Players.LoadPlayersResult> b) {
            this.De = (BaseImplementation.b<Players.LoadPlayersResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void g(final DataHolder dataHolder) {
            this.De.b(new LoadPlayersResultImpl(dataHolder));
        }
        
        @Override
        public void h(final DataHolder dataHolder) {
            this.De.b(new LoadPlayersResultImpl(dataHolder));
        }
    }
    
    final class ProfileSettingsLoadedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Players.LoadProfileSettingsResult> De;
        
        ProfileSettingsLoadedBinderCallback(final BaseImplementation.b<Players.LoadProfileSettingsResult> b) {
            this.De = (BaseImplementation.b<Players.LoadProfileSettingsResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void Q(final DataHolder dataHolder) {
            this.De.b(new LoadProfileSettingsResultImpl(dataHolder));
        }
    }
    
    private final class ProfileSettingsUpdatedBinderCallback extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Status> De;
        
        ProfileSettingsUpdatedBinderCallback(final BaseImplementation.b<Status> b) {
            this.De = (BaseImplementation.b<Status>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void dz(final int n) {
            this.De.b(new Status(n));
        }
    }
    
    private final class QuestAcceptedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Quests.AcceptQuestResult> Xh;
        
        public QuestAcceptedBinderCallbacks(final BaseImplementation.b<Quests.AcceptQuestResult> b) {
            this.Xh = (BaseImplementation.b<Quests.AcceptQuestResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void L(final DataHolder dataHolder) {
            this.Xh.b(new AcceptQuestResultImpl(dataHolder));
        }
    }
    
    private final class QuestCompletedCallback extends b<QuestUpdateListener>
    {
        private final Quest Wt;
        
        QuestCompletedCallback(final QuestUpdateListener questUpdateListener, final Quest wt) {
            super(questUpdateListener);
            this.Wt = wt;
        }
        
        protected void b(final QuestUpdateListener questUpdateListener) {
            questUpdateListener.onQuestCompleted(this.Wt);
        }
        
        @Override
        protected void gT() {
        }
    }
    
    private final class QuestMilestoneClaimBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Quests.ClaimMilestoneResult> Xi;
        private final String Xj;
        
        public QuestMilestoneClaimBinderCallbacks(final BaseImplementation.b<Quests.ClaimMilestoneResult> b, final String s) {
            this.Xi = (BaseImplementation.b<Quests.ClaimMilestoneResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
            this.Xj = n.b(s, (Object)"MilestoneId must not be null");
        }
        
        @Override
        public void K(final DataHolder dataHolder) {
            this.Xi.b(new ClaimMilestoneResultImpl(dataHolder, this.Xj));
        }
    }
    
    private final class QuestUpdateBinderCallback extends AbstractGamesCallbacks
    {
        private final QuestUpdateListener Xk;
        
        QuestUpdateBinderCallback(final QuestUpdateListener xk) {
            this.Xk = xk;
        }
        
        private Quest S(final DataHolder dataHolder) {
            final QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            Quest quest = null;
            try {
                if (questBuffer.getCount() > 0) {
                    quest = questBuffer.get(0).freeze();
                }
                return quest;
            }
            finally {
                questBuffer.release();
            }
        }
        
        @Override
        public void M(final DataHolder dataHolder) {
            final Quest s = this.S(dataHolder);
            if (s != null) {
                GamesClientImpl.this.a((b<?>)new QuestCompletedCallback(this.Xk, s));
            }
        }
    }
    
    private final class QuestsLoadedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Quests.LoadQuestsResult> Xl;
        
        public QuestsLoadedBinderCallbacks(final BaseImplementation.b<Quests.LoadQuestsResult> b) {
            this.Xl = (BaseImplementation.b<Quests.LoadQuestsResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void O(final DataHolder dataHolder) {
            this.Xl.b(new LoadQuestsResultImpl(dataHolder));
        }
    }
    
    private final class RealTimeMessageSentCallback extends b<RealTimeMultiplayer.ReliableMessageSentCallback>
    {
        private final int HF;
        private final String Xm;
        private final int Xn;
        
        RealTimeMessageSentCallback(final RealTimeMultiplayer.ReliableMessageSentCallback reliableMessageSentCallback, final int hf, final int xn, final String xm) {
            super(reliableMessageSentCallback);
            this.HF = hf;
            this.Xn = xn;
            this.Xm = xm;
        }
        
        public void a(final RealTimeMultiplayer.ReliableMessageSentCallback reliableMessageSentCallback) {
            if (reliableMessageSentCallback != null) {
                reliableMessageSentCallback.onRealTimeMessageSent(this.HF, this.Xn, this.Xm);
            }
        }
        
        @Override
        protected void gT() {
        }
    }
    
    private final class RealTimeReliableMessageBinderCallbacks extends AbstractGamesCallbacks
    {
        final RealTimeMultiplayer.ReliableMessageSentCallback Xo;
        
        public RealTimeReliableMessageBinderCallbacks(final RealTimeMultiplayer.ReliableMessageSentCallback xo) {
            this.Xo = xo;
        }
        
        @Override
        public void b(final int n, final int n2, final String s) {
            GamesClientImpl.this.a((b<?>)new RealTimeMessageSentCallback(this.Xo, n, n2, s));
        }
    }
    
    private final class RequestReceivedBinderCallback extends AbstractGamesCallbacks
    {
        private final OnRequestReceivedListener Xp;
        
        RequestReceivedBinderCallback(final OnRequestReceivedListener xp) {
            this.Xp = xp;
        }
        
        @Override
        public void o(final DataHolder dataHolder) {
            final GameRequestBuffer gameRequestBuffer = new GameRequestBuffer(dataHolder);
            GameRequest gameRequest = null;
            try {
                if (gameRequestBuffer.getCount() > 0) {
                    gameRequest = gameRequestBuffer.get(0).freeze();
                }
                gameRequestBuffer.release();
                if (gameRequest != null) {
                    GamesClientImpl.this.a((b<?>)new RequestReceivedCallback(this.Xp, gameRequest));
                }
            }
            finally {
                gameRequestBuffer.release();
            }
        }
        
        @Override
        public void onRequestRemoved(final String s) {
            GamesClientImpl.this.a((b<?>)new RequestRemovedCallback(this.Xp, s));
        }
    }
    
    private final class RequestReceivedCallback extends b<OnRequestReceivedListener>
    {
        private final GameRequest Xq;
        
        RequestReceivedCallback(final OnRequestReceivedListener onRequestReceivedListener, final GameRequest xq) {
            super(onRequestReceivedListener);
            this.Xq = xq;
        }
        
        protected void b(final OnRequestReceivedListener onRequestReceivedListener) {
            onRequestReceivedListener.onRequestReceived(this.Xq);
        }
        
        @Override
        protected void gT() {
        }
    }
    
    private final class RequestRemovedCallback extends b<OnRequestReceivedListener>
    {
        private final String Xr;
        
        RequestRemovedCallback(final OnRequestReceivedListener onRequestReceivedListener, final String xr) {
            super(onRequestReceivedListener);
            this.Xr = xr;
        }
        
        protected void b(final OnRequestReceivedListener onRequestReceivedListener) {
            onRequestReceivedListener.onRequestRemoved(this.Xr);
        }
        
        @Override
        protected void gT() {
        }
    }
    
    private final class RequestSentBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Requests.SendRequestResult> Xs;
        
        public RequestSentBinderCallbacks(final BaseImplementation.b<Requests.SendRequestResult> b) {
            this.Xs = (BaseImplementation.b<Requests.SendRequestResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void G(final DataHolder dataHolder) {
            this.Xs.b(new SendRequestResultImpl(dataHolder));
        }
    }
    
    private final class RequestSummariesLoadedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Requests.LoadRequestSummariesResult> Xt;
        
        public RequestSummariesLoadedBinderCallbacks(final BaseImplementation.b<Requests.LoadRequestSummariesResult> b) {
            this.Xt = (BaseImplementation.b<Requests.LoadRequestSummariesResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void H(final DataHolder dataHolder) {
            this.Xt.b(new LoadRequestSummariesResultImpl(dataHolder));
        }
    }
    
    private final class RequestsLoadedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Requests.LoadRequestsResult> Xu;
        
        public RequestsLoadedBinderCallbacks(final BaseImplementation.b<Requests.LoadRequestsResult> b) {
            this.Xu = (BaseImplementation.b<Requests.LoadRequestsResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void c(final int n, final Bundle bundle) {
            bundle.setClassLoader(this.getClass().getClassLoader());
            this.Xu.b(new LoadRequestsResultImpl(new Status(n), bundle));
        }
    }
    
    private final class RequestsUpdatedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Requests.UpdateRequestsResult> Xv;
        
        public RequestsUpdatedBinderCallbacks(final BaseImplementation.b<Requests.UpdateRequestsResult> b) {
            this.Xv = (BaseImplementation.b<Requests.UpdateRequestsResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void F(final DataHolder dataHolder) {
            this.Xv.b(new UpdateRequestsResultImpl(dataHolder));
        }
    }
    
    private final class RoomAutoMatchingCallback extends AbstractRoomStatusCallback
    {
        RoomAutoMatchingCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            super(roomStatusUpdateListener, dataHolder);
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            roomStatusUpdateListener.onRoomAutoMatching(room);
        }
    }
    
    private final class RoomBinderCallbacks extends AbstractGamesCallbacks
    {
        private final RoomUpdateListener Xw;
        private final RoomStatusUpdateListener Xx;
        private final RealTimeMessageReceivedListener Xy;
        
        public RoomBinderCallbacks(final RoomUpdateListener roomUpdateListener) {
            this.Xw = n.b(roomUpdateListener, "Callbacks must not be null");
            this.Xx = null;
            this.Xy = null;
        }
        
        public RoomBinderCallbacks(final RoomUpdateListener roomUpdateListener, final RoomStatusUpdateListener xx, final RealTimeMessageReceivedListener xy) {
            this.Xw = n.b(roomUpdateListener, "Callbacks must not be null");
            this.Xx = xx;
            this.Xy = xy;
        }
        
        @Override
        public void A(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new DisconnectedFromRoomCallback(this.Xx, dataHolder));
        }
        
        @Override
        public void a(final DataHolder dataHolder, final String[] array) {
            GamesClientImpl.this.a((b<?>)new PeerInvitedToRoomCallback(this.Xx, dataHolder, array));
        }
        
        @Override
        public void b(final DataHolder dataHolder, final String[] array) {
            GamesClientImpl.this.a((b<?>)new PeerJoinedRoomCallback(this.Xx, dataHolder, array));
        }
        
        @Override
        public void c(final DataHolder dataHolder, final String[] array) {
            GamesClientImpl.this.a((b<?>)new PeerLeftRoomCallback(this.Xx, dataHolder, array));
        }
        
        @Override
        public void d(final DataHolder dataHolder, final String[] array) {
            GamesClientImpl.this.a((b<?>)new PeerDeclinedCallback(this.Xx, dataHolder, array));
        }
        
        @Override
        public void e(final DataHolder dataHolder, final String[] array) {
            GamesClientImpl.this.a((b<?>)new PeerConnectedCallback(this.Xx, dataHolder, array));
        }
        
        @Override
        public void f(final DataHolder dataHolder, final String[] array) {
            GamesClientImpl.this.a((b<?>)new PeerDisconnectedCallback(this.Xx, dataHolder, array));
        }
        
        @Override
        public void onLeftRoom(final int n, final String s) {
            GamesClientImpl.this.a((b<?>)new LeftRoomCallback(this.Xw, n, s));
        }
        
        @Override
        public void onP2PConnected(final String s) {
            GamesClientImpl.this.a((b<?>)new P2PConnectedCallback(this.Xx, s));
        }
        
        @Override
        public void onP2PDisconnected(final String s) {
            GamesClientImpl.this.a((b<?>)new P2PDisconnectedCallback(this.Xx, s));
        }
        
        @Override
        public void onRealTimeMessageReceived(final RealTimeMessage realTimeMessage) {
            GamesClientImpl.this.a((b<?>)new MessageReceivedCallback(this.Xy, realTimeMessage));
        }
        
        @Override
        public void u(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new RoomCreatedCallback(this.Xw, dataHolder));
        }
        
        @Override
        public void v(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new JoinedRoomCallback(this.Xw, dataHolder));
        }
        
        @Override
        public void w(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new RoomConnectingCallback(this.Xx, dataHolder));
        }
        
        @Override
        public void x(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new RoomAutoMatchingCallback(this.Xx, dataHolder));
        }
        
        @Override
        public void y(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new RoomConnectedCallback(this.Xw, dataHolder));
        }
        
        @Override
        public void z(final DataHolder dataHolder) {
            GamesClientImpl.this.a((b<?>)new ConnectedToRoomCallback(this.Xx, dataHolder));
        }
    }
    
    private final class RoomConnectedCallback extends AbstractRoomCallback
    {
        RoomConnectedCallback(final RoomUpdateListener roomUpdateListener, final DataHolder dataHolder) {
            super(roomUpdateListener, dataHolder);
        }
        
        public void a(final RoomUpdateListener roomUpdateListener, final Room room, final int n) {
            roomUpdateListener.onRoomConnected(n, room);
        }
    }
    
    private final class RoomConnectingCallback extends AbstractRoomStatusCallback
    {
        RoomConnectingCallback(final RoomStatusUpdateListener roomStatusUpdateListener, final DataHolder dataHolder) {
            super(roomStatusUpdateListener, dataHolder);
        }
        
        public void a(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            roomStatusUpdateListener.onRoomConnecting(room);
        }
    }
    
    private final class RoomCreatedCallback extends AbstractRoomCallback
    {
        public RoomCreatedCallback(final RoomUpdateListener roomUpdateListener, final DataHolder dataHolder) {
            super(roomUpdateListener, dataHolder);
        }
        
        public void a(final RoomUpdateListener roomUpdateListener, final Room room, final int n) {
            roomUpdateListener.onRoomCreated(n, room);
        }
    }
    
    private static final class SendRequestResultImpl extends com.google.android.gms.common.api.a implements SendRequestResult
    {
        private final GameRequest Xq;
        
        SendRequestResultImpl(DataHolder dataHolder) {
            super(dataHolder);
            dataHolder = (DataHolder)new GameRequestBuffer(dataHolder);
            try {
                if (((com.google.android.gms.common.data.g)dataHolder).getCount() > 0) {
                    this.Xq = ((com.google.android.gms.common.data.g<GameRequest>)dataHolder).get(0).freeze();
                }
                else {
                    this.Xq = null;
                }
            }
            finally {
                ((DataBuffer)dataHolder).release();
            }
        }
    }
    
    private final class SignOutCompleteBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Status> De;
        
        public SignOutCompleteBinderCallbacks(final BaseImplementation.b<Status> b) {
            this.De = (BaseImplementation.b<Status>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void fq() {
            this.De.b(new Status(0));
        }
    }
    
    private final class SnapshotCommittedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Snapshots.CommitSnapshotResult> Xz;
        
        public SnapshotCommittedBinderCallbacks(final BaseImplementation.b<Snapshots.CommitSnapshotResult> b) {
            this.Xz = (BaseImplementation.b<Snapshots.CommitSnapshotResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void J(final DataHolder dataHolder) {
            this.Xz.b(new CommitSnapshotResultImpl(dataHolder));
        }
    }
    
    final class SnapshotDeletedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Snapshots.DeleteSnapshotResult> De;
        
        public SnapshotDeletedBinderCallbacks(final BaseImplementation.b<Snapshots.DeleteSnapshotResult> b) {
            this.De = (BaseImplementation.b<Snapshots.DeleteSnapshotResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void i(final int n, final String s) {
            this.De.b(new DeleteSnapshotResultImpl(n, s));
        }
    }
    
    private final class SnapshotOpenedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Snapshots.OpenSnapshotResult> XA;
        
        public SnapshotOpenedBinderCallbacks(final BaseImplementation.b<Snapshots.OpenSnapshotResult> b) {
            this.XA = (BaseImplementation.b<Snapshots.OpenSnapshotResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void a(final DataHolder dataHolder, final Contents contents) {
            this.XA.b(new OpenSnapshotResultImpl(dataHolder, contents));
        }
        
        @Override
        public void a(final DataHolder dataHolder, final String s, final Contents contents, final Contents contents2, final Contents contents3) {
            this.XA.b(new OpenSnapshotResultImpl(dataHolder, s, contents, contents2, contents3));
        }
    }
    
    private final class SnapshotsLoadedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Snapshots.LoadSnapshotsResult> XB;
        
        public SnapshotsLoadedBinderCallbacks(final BaseImplementation.b<Snapshots.LoadSnapshotsResult> b) {
            this.XB = (BaseImplementation.b<Snapshots.LoadSnapshotsResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void I(final DataHolder dataHolder) {
            this.XB.b(new LoadSnapshotsResultImpl(dataHolder));
        }
    }
    
    private final class SubmitScoreBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<Leaderboards.SubmitScoreResult> De;
        
        public SubmitScoreBinderCallbacks(final BaseImplementation.b<Leaderboards.SubmitScoreResult> b) {
            this.De = (BaseImplementation.b<Leaderboards.SubmitScoreResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void f(final DataHolder dataHolder) {
            this.De.b(new SubmitScoreResultImpl(dataHolder));
        }
    }
    
    private static final class SubmitScoreResultImpl extends com.google.android.gms.common.api.a implements SubmitScoreResult
    {
        private final ScoreSubmissionData XC;
        
        public SubmitScoreResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
            try {
                this.XC = new ScoreSubmissionData(dataHolder);
            }
            finally {
                dataHolder.close();
            }
        }
        
        @Override
        public ScoreSubmissionData getScoreData() {
            return this.XC;
        }
    }
    
    private final class TurnBasedMatchCanceledBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<TurnBasedMultiplayer.CancelMatchResult> XD;
        
        public TurnBasedMatchCanceledBinderCallbacks(final BaseImplementation.b<TurnBasedMultiplayer.CancelMatchResult> b) {
            this.XD = (BaseImplementation.b<TurnBasedMultiplayer.CancelMatchResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void h(final int n, final String s) {
            this.XD.b(new CancelMatchResultImpl(new Status(n), s));
        }
    }
    
    private final class TurnBasedMatchInitiatedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<TurnBasedMultiplayer.InitiateMatchResult> XE;
        
        public TurnBasedMatchInitiatedBinderCallbacks(final BaseImplementation.b<TurnBasedMultiplayer.InitiateMatchResult> b) {
            this.XE = (BaseImplementation.b<TurnBasedMultiplayer.InitiateMatchResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void q(final DataHolder dataHolder) {
            this.XE.b(new InitiateMatchResultImpl(dataHolder));
        }
    }
    
    private final class TurnBasedMatchLeftBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<TurnBasedMultiplayer.LeaveMatchResult> XF;
        
        public TurnBasedMatchLeftBinderCallbacks(final BaseImplementation.b<TurnBasedMultiplayer.LeaveMatchResult> b) {
            this.XF = (BaseImplementation.b<TurnBasedMultiplayer.LeaveMatchResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void s(final DataHolder dataHolder) {
            this.XF.b(new LeaveMatchResultImpl(dataHolder));
        }
    }
    
    private final class TurnBasedMatchLoadedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<TurnBasedMultiplayer.LoadMatchResult> XG;
        
        public TurnBasedMatchLoadedBinderCallbacks(final BaseImplementation.b<TurnBasedMultiplayer.LoadMatchResult> b) {
            this.XG = (BaseImplementation.b<TurnBasedMultiplayer.LoadMatchResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void p(final DataHolder dataHolder) {
            this.XG.b(new LoadMatchResultImpl(dataHolder));
        }
    }
    
    private abstract static class TurnBasedMatchResult extends com.google.android.gms.common.api.a
    {
        final TurnBasedMatch WY;
        
        TurnBasedMatchResult(DataHolder dataHolder) {
            super(dataHolder);
            dataHolder = (DataHolder)new TurnBasedMatchBuffer(dataHolder);
            try {
                if (((com.google.android.gms.common.data.g)dataHolder).getCount() > 0) {
                    this.WY = ((com.google.android.gms.common.data.g<TurnBasedMatch>)dataHolder).get(0).freeze();
                }
                else {
                    this.WY = null;
                }
            }
            finally {
                ((DataBuffer)dataHolder).release();
            }
        }
        
        public TurnBasedMatch getMatch() {
            return this.WY;
        }
    }
    
    private final class TurnBasedMatchUpdatedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<TurnBasedMultiplayer.UpdateMatchResult> XH;
        
        public TurnBasedMatchUpdatedBinderCallbacks(final BaseImplementation.b<TurnBasedMultiplayer.UpdateMatchResult> b) {
            this.XH = (BaseImplementation.b<TurnBasedMultiplayer.UpdateMatchResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void r(final DataHolder dataHolder) {
            this.XH.b(new UpdateMatchResultImpl(dataHolder));
        }
    }
    
    private final class TurnBasedMatchesLoadedBinderCallbacks extends AbstractGamesCallbacks
    {
        private final BaseImplementation.b<TurnBasedMultiplayer.LoadMatchesResult> XI;
        
        public TurnBasedMatchesLoadedBinderCallbacks(final BaseImplementation.b<TurnBasedMultiplayer.LoadMatchesResult> b) {
            this.XI = (BaseImplementation.b<TurnBasedMultiplayer.LoadMatchesResult>)n.b((BaseImplementation.b)b, "Holder must not be null");
        }
        
        @Override
        public void b(final int n, final Bundle bundle) {
            bundle.setClassLoader(this.getClass().getClassLoader());
            this.XI.b(new LoadMatchesResultImpl(new Status(n), bundle));
        }
    }
    
    private static final class UpdateAchievementResultImpl implements UpdateAchievementResult
    {
        private final Status CM;
        private final String VP;
        
        UpdateAchievementResultImpl(final int n, final String vp) {
            this.CM = new Status(n);
            this.VP = vp;
        }
        
        @Override
        public String getAchievementId() {
            return this.VP;
        }
        
        @Override
        public Status getStatus() {
            return this.CM;
        }
    }
    
    private static final class UpdateMatchResultImpl extends TurnBasedMatchResult implements UpdateMatchResult
    {
        UpdateMatchResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
        }
    }
    
    private static final class UpdateRequestsResultImpl extends com.google.android.gms.common.api.a implements UpdateRequestsResult
    {
        private final RequestUpdateOutcomes XJ;
        
        UpdateRequestsResultImpl(final DataHolder dataHolder) {
            super(dataHolder);
            this.XJ = RequestUpdateOutcomes.V(dataHolder);
        }
        
        @Override
        public Set<String> getRequestIds() {
            return this.XJ.getRequestIds();
        }
        
        @Override
        public int getRequestOutcome(final String s) {
            return this.XJ.getRequestOutcome(s);
        }
    }
}
