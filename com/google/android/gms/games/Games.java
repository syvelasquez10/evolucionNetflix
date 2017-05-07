// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.view.View;
import android.content.Intent;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.api.AclsImpl;
import com.google.android.gms.games.internal.api.SnapshotsImpl;
import com.google.android.gms.games.internal.api.RequestsImpl;
import com.google.android.gms.games.internal.api.QuestsImpl;
import com.google.android.gms.games.internal.api.NotificationsImpl;
import com.google.android.gms.games.internal.api.PlayersImpl;
import com.google.android.gms.games.internal.api.MultiplayerImpl;
import com.google.android.gms.games.internal.api.RealTimeMultiplayerImpl;
import com.google.android.gms.games.internal.api.TurnBasedMultiplayerImpl;
import com.google.android.gms.games.internal.api.InvitationsImpl;
import com.google.android.gms.games.internal.api.LeaderboardsImpl;
import com.google.android.gms.games.internal.api.EventsImpl;
import com.google.android.gms.games.internal.api.AchievementsImpl;
import com.google.android.gms.games.internal.api.GamesMetadataImpl;
import com.google.android.gms.games.internal.game.Acls;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.common.api.Api$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$c;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.common.api.Api;

public final class Games
{
    public static final Api<Games$GamesOptions> API;
    public static final Achievements Achievements;
    static final Api$c<GamesClientImpl> CU;
    private static final Api$b<GamesClientImpl, Games$GamesOptions> CV;
    public static final String EXTRA_PLAYER_IDS = "players";
    public static final Events Events;
    public static final GamesMetadata GamesMetadata;
    public static final Invitations Invitations;
    public static final Leaderboards Leaderboards;
    public static final Notifications Notifications;
    public static final Players Players;
    public static final Quests Quests;
    public static final RealTimeMultiplayer RealTimeMultiplayer;
    public static final Requests Requests;
    public static final Scope SCOPE_GAMES;
    public static final Snapshots Snapshots;
    public static final TurnBasedMultiplayer TurnBasedMultiplayer;
    public static final Scope Vo;
    public static final Api<Games$GamesOptions> Vp;
    public static final Multiplayer Vq;
    public static final Acls Vr;
    
    static {
        CU = new Api$c<GamesClientImpl>();
        CV = new Games$1();
        SCOPE_GAMES = new Scope("https://www.googleapis.com/auth/games");
        API = new Api<Games$GamesOptions>((Api$b<C, Games$GamesOptions>)Games.CV, (Api$c<C>)Games.CU, new Scope[] { Games.SCOPE_GAMES });
        Vo = new Scope("https://www.googleapis.com/auth/games.firstparty");
        Vp = new Api<Games$GamesOptions>((Api$b<C, Games$GamesOptions>)Games.CV, (Api$c<C>)Games.CU, new Scope[] { Games.Vo });
        GamesMetadata = new GamesMetadataImpl();
        Achievements = new AchievementsImpl();
        Events = new EventsImpl();
        Leaderboards = new LeaderboardsImpl();
        Invitations = new InvitationsImpl();
        TurnBasedMultiplayer = new TurnBasedMultiplayerImpl();
        RealTimeMultiplayer = new RealTimeMultiplayerImpl();
        Vq = new MultiplayerImpl();
        Players = new PlayersImpl();
        Notifications = new NotificationsImpl();
        Quests = new QuestsImpl();
        Requests = new RequestsImpl();
        Snapshots = new SnapshotsImpl();
        Vr = new AclsImpl();
    }
    
    public static GamesClientImpl c(final GoogleApiClient googleApiClient) {
        n.b(googleApiClient != null, (Object)"GoogleApiClient parameter is required.");
        n.a(googleApiClient.isConnected(), (Object)"GoogleApiClient must be connected.");
        return d(googleApiClient);
    }
    
    public static GamesClientImpl d(final GoogleApiClient googleApiClient) {
        final GamesClientImpl gamesClientImpl = googleApiClient.a(Games.CU);
        n.a(gamesClientImpl != null, (Object)"GoogleApiClient is not configured to use the Games Api. Pass Games.API into GoogleApiClient.Builder#addApi() to use this feature.");
        return gamesClientImpl;
    }
    
    public static String getAppId(final GoogleApiClient googleApiClient) {
        return c(googleApiClient).km();
    }
    
    public static String getCurrentAccountName(final GoogleApiClient googleApiClient) {
        return c(googleApiClient).jX();
    }
    
    public static int getSdkVariant(final GoogleApiClient googleApiClient) {
        return c(googleApiClient).kl();
    }
    
    public static Intent getSettingsIntent(final GoogleApiClient googleApiClient) {
        return c(googleApiClient).kk();
    }
    
    public static void setGravityForPopups(final GoogleApiClient googleApiClient, final int n) {
        c(googleApiClient).dB(n);
    }
    
    public static void setViewForPopups(final GoogleApiClient googleApiClient, final View view) {
        n.i(view);
        c(googleApiClient).k(view);
    }
    
    public static PendingResult<Status> signOut(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<Status>)new Games$2());
    }
}
