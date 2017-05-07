// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.a;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.view.View;
import android.content.Intent;
import com.google.android.gms.internal.fq;
import com.google.android.gms.games.internal.api.AclsImpl;
import com.google.android.gms.games.internal.api.RequestsImpl;
import com.google.android.gms.games.internal.api.NotificationsImpl;
import com.google.android.gms.games.internal.api.PlayersImpl;
import com.google.android.gms.games.internal.api.MultiplayerImpl;
import com.google.android.gms.games.internal.api.RealTimeMultiplayerImpl;
import com.google.android.gms.games.internal.api.TurnBasedMultiplayerImpl;
import com.google.android.gms.games.internal.api.InvitationsImpl;
import com.google.android.gms.games.internal.api.LeaderboardsImpl;
import com.google.android.gms.games.internal.api.AchievementsImpl;
import com.google.android.gms.games.internal.api.GamesMetadataImpl;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.fc;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.internal.game.Acls;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.common.api.Api;

public final class Games
{
    public static final Api<GamesOptions> API;
    public static final Achievements Achievements;
    public static final String EXTRA_PLAYER_IDS = "players";
    public static final GamesMetadata GamesMetadata;
    public static final Scope HV;
    public static final Api<GamesOptions> HW;
    public static final Multiplayer HX;
    public static final Acls HY;
    public static final Invitations Invitations;
    public static final Leaderboards Leaderboards;
    public static final Notifications Notifications;
    public static final Players Players;
    public static final RealTimeMultiplayer RealTimeMultiplayer;
    public static final Requests Requests;
    public static final Scope SCOPE_GAMES;
    public static final TurnBasedMultiplayer TurnBasedMultiplayer;
    static final Api.c<GamesClientImpl> wx;
    private static final Api.b<GamesClientImpl, GamesOptions> wy;
    
    static {
        wx = new Api.c();
        wy = new Api.b<GamesClientImpl, GamesOptions>() {
            public GamesClientImpl a(final Context context, final Looper looper, final fc fc, final GamesOptions gamesOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                GamesOptions gamesOptions2 = gamesOptions;
                if (gamesOptions == null) {
                    gamesOptions2 = new GamesOptions();
                }
                return new GamesClientImpl(context, looper, fc.eG(), fc.eC(), connectionCallbacks, onConnectionFailedListener, fc.eF(), fc.eD(), fc.eH(), gamesOptions2.HZ, gamesOptions2.Ia, gamesOptions2.Ib, gamesOptions2.Ic, gamesOptions2.Id);
            }
            
            @Override
            public int getPriority() {
                return 1;
            }
        };
        SCOPE_GAMES = new Scope("https://www.googleapis.com/auth/games");
        API = new Api<GamesOptions>((Api.b<C, GamesOptions>)Games.wy, (Api.c<C>)Games.wx, new Scope[] { Games.SCOPE_GAMES });
        HV = new Scope("https://www.googleapis.com/auth/games.firstparty");
        HW = new Api<GamesOptions>((Api.b<C, GamesOptions>)Games.wy, (Api.c<C>)Games.wx, new Scope[] { Games.HV });
        GamesMetadata = new GamesMetadataImpl();
        Achievements = new AchievementsImpl();
        Leaderboards = new LeaderboardsImpl();
        Invitations = new InvitationsImpl();
        TurnBasedMultiplayer = new TurnBasedMultiplayerImpl();
        RealTimeMultiplayer = new RealTimeMultiplayerImpl();
        HX = new MultiplayerImpl();
        Players = new PlayersImpl();
        Notifications = new NotificationsImpl();
        Requests = new RequestsImpl();
        HY = new AclsImpl();
    }
    
    public static GamesClientImpl c(final GoogleApiClient googleApiClient) {
        final boolean b = true;
        fq.b(googleApiClient != null, "GoogleApiClient parameter is required.");
        fq.a(googleApiClient.isConnected(), (Object)"GoogleApiClient must be connected.");
        final GamesClientImpl gamesClientImpl = googleApiClient.a(Games.wx);
        fq.a(gamesClientImpl != null && b, (Object)"GoogleApiClient is not configured to use the Games Api. Pass Games.API into GoogleApiClient.Builder#addApi() to use this feature.");
        return gamesClientImpl;
    }
    
    public static String getAppId(final GoogleApiClient googleApiClient) {
        return c(googleApiClient).gz();
    }
    
    public static String getCurrentAccountName(final GoogleApiClient googleApiClient) {
        return c(googleApiClient).gl();
    }
    
    public static int getSdkVariant(final GoogleApiClient googleApiClient) {
        return c(googleApiClient).gy();
    }
    
    public static Intent getSettingsIntent(final GoogleApiClient googleApiClient) {
        return c(googleApiClient).gx();
    }
    
    public static void setGravityForPopups(final GoogleApiClient googleApiClient, final int n) {
        c(googleApiClient).aX(n);
    }
    
    public static void setViewForPopups(final GoogleApiClient googleApiClient, final View view) {
        fq.f(view);
        c(googleApiClient).f(view);
    }
    
    public static PendingResult<Status> signOut(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<Status>)new SignOutImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.b((d<Status>)this);
            }
        });
    }
    
    public abstract static class BaseGamesApiMethodImpl<R extends Result> extends b<R, GamesClientImpl>
    {
        public BaseGamesApiMethodImpl() {
            super(Games.wx);
        }
    }
    
    public static final class GamesOptions implements Optional
    {
        final boolean HZ;
        final boolean Ia;
        final int Ib;
        final boolean Ic;
        final int Id;
        
        private GamesOptions() {
            this.HZ = false;
            this.Ia = true;
            this.Ib = 17;
            this.Ic = false;
            this.Id = 4368;
        }
        
        private GamesOptions(final Builder builder) {
            this.HZ = builder.HZ;
            this.Ia = builder.Ia;
            this.Ib = builder.Ib;
            this.Ic = builder.Ic;
            this.Id = builder.Id;
        }
        
        public static Builder builder() {
            return new Builder();
        }
        
        public static final class Builder
        {
            boolean HZ;
            boolean Ia;
            int Ib;
            boolean Ic;
            int Id;
            
            private Builder() {
                this.HZ = false;
                this.Ia = true;
                this.Ib = 17;
                this.Ic = false;
                this.Id = 4368;
            }
            
            public GamesOptions build() {
                return new GamesOptions(this);
            }
            
            public Builder setSdkVariant(final int id) {
                this.Id = id;
                return this;
            }
            
            public Builder setShowConnectingPopup(final boolean ia) {
                this.Ia = ia;
                this.Ib = 17;
                return this;
            }
            
            public Builder setShowConnectingPopup(final boolean ia, final int ib) {
                this.Ia = ia;
                this.Ib = ib;
                return this;
            }
        }
    }
    
    private abstract static class SignOutImpl extends BaseGamesApiMethodImpl<Status>
    {
        public Status f(final Status status) {
            return status;
        }
    }
}
