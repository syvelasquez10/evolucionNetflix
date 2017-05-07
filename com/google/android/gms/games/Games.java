// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.a;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.view.View;
import android.content.Intent;
import com.google.android.gms.internal.ft;
import com.google.android.gms.internal.fy;
import com.google.android.gms.internal.fz;
import com.google.android.gms.internal.fx;
import com.google.android.gms.internal.ga;
import com.google.android.gms.internal.gb;
import com.google.android.gms.internal.fv;
import com.google.android.gms.internal.fw;
import com.google.android.gms.internal.fs;
import com.google.android.gms.internal.fu;
import com.google.android.gms.internal.eg;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.dt;
import android.content.Context;
import com.google.android.gms.internal.gg;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.internal.fl;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.common.api.Api;

public final class Games
{
    public static final Api API;
    public static final Achievements Achievements;
    public static final GamesMetadata GamesMetadata;
    public static final Invitations Invitations;
    public static final Leaderboards Leaderboards;
    public static final Notifications Notifications;
    public static final Players Players;
    public static final RealTimeMultiplayer RealTimeMultiplayer;
    public static final Scope SCOPE_GAMES;
    public static final TurnBasedMultiplayer TurnBasedMultiplayer;
    static final Api.b<fl> jO;
    public static final Scope sW;
    public static final Api sX;
    public static final Multiplayer sY;
    public static final gg sZ;
    
    static {
        jO = new Api.b<fl>() {
            public fl e(final Context context, final dt dt, final GoogleApiClient.ApiOptions apiOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                final GamesOptions gamesOptions = new GamesOptions();
                GamesOptions gamesOptions2;
                if (apiOptions != null) {
                    eg.b(apiOptions instanceof GamesOptions, "Must provide valid GamesOptions!");
                    gamesOptions2 = (GamesOptions)apiOptions;
                }
                else {
                    gamesOptions2 = gamesOptions;
                }
                return new fl(context, dt.bJ(), dt.bF(), connectionCallbacks, onConnectionFailedListener, dt.bI(), dt.bG(), dt.bK(), gamesOptions2.ta, gamesOptions2.tb, gamesOptions2.tc, gamesOptions2.td);
            }
            
            @Override
            public int getPriority() {
                return 1;
            }
        };
        SCOPE_GAMES = new Scope("https://www.googleapis.com/auth/games");
        API = new Api((Api.b<?>)Games.jO, new Scope[] { Games.SCOPE_GAMES });
        sW = new Scope("https://www.googleapis.com/auth/games.firstparty");
        sX = new Api((Api.b<?>)Games.jO, new Scope[] { Games.sW });
        GamesMetadata = new fu();
        Achievements = new fs();
        Leaderboards = new fw();
        Invitations = new fv();
        TurnBasedMultiplayer = new gb();
        RealTimeMultiplayer = new ga();
        sY = new fx();
        Players = new fz();
        Notifications = new fy();
        sZ = new ft();
    }
    
    public static String getAppId(final GoogleApiClient googleApiClient) {
        return j(googleApiClient).getAppId();
    }
    
    public static String getCurrentAccountName(final GoogleApiClient googleApiClient) {
        return j(googleApiClient).getCurrentAccountName();
    }
    
    public static int getSdkVariant(final GoogleApiClient googleApiClient) {
        return j(googleApiClient).dd();
    }
    
    public static Intent getSettingsIntent(final GoogleApiClient googleApiClient) {
        return j(googleApiClient).getSettingsIntent();
    }
    
    public static fl j(final GoogleApiClient googleApiClient) {
        final boolean b = true;
        eg.b(googleApiClient != null, "GoogleApiClient parameter is required.");
        eg.a(googleApiClient.isConnected(), (Object)"GoogleApiClient must be connected.");
        final fl fl = googleApiClient.a(Games.jO);
        eg.a(fl != null && b, (Object)"GoogleApiClient is not configured to use the Games Api. Pass Games.API into GoogleApiClient.Builder#addApi() to use this feature.");
        return fl;
    }
    
    public static void setGravityForPopups(final GoogleApiClient googleApiClient, final int gravityForPopups) {
        j(googleApiClient).setGravityForPopups(gravityForPopups);
    }
    
    public static void setViewForPopups(final GoogleApiClient googleApiClient, final View viewForPopups) {
        eg.f(viewForPopups);
        j(googleApiClient).setViewForPopups(viewForPopups);
    }
    
    public static PendingResult<Status> signOut(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<Status>)new b() {
            protected void a(final fl fl) {
                fl.b((c<Status>)this);
            }
        });
    }
    
    public static final class GamesOptions implements ApiOptions
    {
        final boolean ta;
        final boolean tb;
        final int tc;
        final int td;
        
        private GamesOptions() {
            this.ta = false;
            this.tb = true;
            this.tc = 17;
            this.td = 4368;
        }
        
        private GamesOptions(final Builder builder) {
            this.ta = builder.ta;
            this.tb = builder.tb;
            this.tc = builder.tc;
            this.td = builder.td;
        }
        
        public static Builder builder() {
            return new Builder();
        }
        
        public static final class Builder
        {
            boolean ta;
            boolean tb;
            int tc;
            int td;
            
            private Builder() {
                this.ta = false;
                this.tb = true;
                this.tc = 17;
                this.td = 4368;
            }
            
            public GamesOptions build() {
                return new GamesOptions(this);
            }
            
            public Builder setSdkVariant(final int td) {
                this.td = td;
                return this;
            }
            
            public Builder setShowConnectingPopup(final boolean tb) {
                this.tb = tb;
                this.tc = 17;
                return this;
            }
            
            public Builder setShowConnectingPopup(final boolean tb, final int tc) {
                this.tb = tb;
                this.tc = tc;
                return this;
            }
        }
    }
    
    public abstract static class a<R extends Result> extends com.google.android.gms.common.api.a.a<R, fl> implements PendingResult<R>
    {
        public a() {
            super(Games.jO);
        }
    }
    
    private abstract static class b extends a<Status>
    {
        public Status g(final Status status) {
            return status;
        }
    }
}
