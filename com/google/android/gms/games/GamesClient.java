// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.games.leaderboard.SubmitScoreResult;
import com.google.android.gms.games.leaderboard.OnScoreSubmittedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.eg;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.realtime.RealTimeReliableMessageSentListener;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchesLoadedListener;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.OnLeaderboardScoresLoadedListener;
import com.google.android.gms.games.leaderboard.OnLeaderboardMetadataLoadedListener;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.OnInvitationsLoadedListener;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.OnPlayerLeaderboardScoreLoadedListener;
import com.google.android.gms.games.achievement.OnAchievementsLoadedListener;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchLeftListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.achievement.OnAchievementUpdatedListener;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchLoadedListener;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import android.content.Intent;
import java.util.List;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdatedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchCanceledListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.common.api.a;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchInitiatedListener;
import android.view.View;
import android.content.Context;
import com.google.android.gms.internal.fl;
import com.google.android.gms.common.GooglePlayServicesClient;

@Deprecated
public final class GamesClient implements GooglePlayServicesClient
{
    public static final String EXTRA_EXCLUSIVE_BIT_MASK = "exclusive_bit_mask";
    public static final String EXTRA_INVITATION = "invitation";
    public static final String EXTRA_MAX_AUTOMATCH_PLAYERS = "max_automatch_players";
    public static final String EXTRA_MIN_AUTOMATCH_PLAYERS = "min_automatch_players";
    public static final String EXTRA_PLAYERS = "players";
    public static final String EXTRA_PLAYER_SEARCH_RESULTS = "player_search_results";
    public static final String EXTRA_ROOM = "room";
    public static final String EXTRA_TURN_BASED_MATCH = "turn_based_match";
    public static final int MAX_RELIABLE_MESSAGE_LEN = 1400;
    public static final int MAX_UNRELIABLE_MESSAGE_LEN = 1168;
    public static final int NOTIFICATION_TYPES_ALL = -1;
    public static final int NOTIFICATION_TYPES_MULTIPLAYER = 3;
    public static final int NOTIFICATION_TYPE_INVITATION = 1;
    public static final int NOTIFICATION_TYPE_MATCH_UPDATE = 2;
    public static final int STATUS_ACHIEVEMENT_NOT_INCREMENTAL = 3002;
    public static final int STATUS_ACHIEVEMENT_UNKNOWN = 3001;
    public static final int STATUS_ACHIEVEMENT_UNLOCKED = 3003;
    public static final int STATUS_ACHIEVEMENT_UNLOCK_FAILURE = 3000;
    public static final int STATUS_APP_MISCONFIGURED = 8;
    public static final int STATUS_CLIENT_RECONNECT_REQUIRED = 2;
    public static final int STATUS_GAME_NOT_FOUND = 9;
    public static final int STATUS_INTERNAL_ERROR = 1;
    public static final int STATUS_INVALID_REAL_TIME_ROOM_ID = 7002;
    public static final int STATUS_LICENSE_CHECK_FAILED = 7;
    public static final int STATUS_MATCH_ERROR_ALREADY_REMATCHED = 6505;
    public static final int STATUS_MATCH_ERROR_INACTIVE_MATCH = 6501;
    public static final int STATUS_MATCH_ERROR_INVALID_MATCH_RESULTS = 6504;
    public static final int STATUS_MATCH_ERROR_INVALID_MATCH_STATE = 6502;
    public static final int STATUS_MATCH_ERROR_INVALID_PARTICIPANT_STATE = 6500;
    public static final int STATUS_MATCH_ERROR_LOCALLY_MODIFIED = 6507;
    public static final int STATUS_MATCH_ERROR_OUT_OF_DATE_VERSION = 6503;
    public static final int STATUS_MATCH_NOT_FOUND = 6506;
    public static final int STATUS_MULTIPLAYER_DISABLED = 6003;
    public static final int STATUS_MULTIPLAYER_ERROR_CREATION_NOT_ALLOWED = 6000;
    public static final int STATUS_MULTIPLAYER_ERROR_INVALID_MULTIPLAYER_TYPE = 6002;
    public static final int STATUS_MULTIPLAYER_ERROR_INVALID_OPERATION = 6004;
    public static final int STATUS_MULTIPLAYER_ERROR_NOT_TRUSTED_TESTER = 6001;
    public static final int STATUS_NETWORK_ERROR_NO_DATA = 4;
    public static final int STATUS_NETWORK_ERROR_OPERATION_DEFERRED = 5;
    public static final int STATUS_NETWORK_ERROR_OPERATION_FAILED = 6;
    public static final int STATUS_NETWORK_ERROR_STALE_DATA = 3;
    public static final int STATUS_OK = 0;
    public static final int STATUS_OPERATION_IN_FLIGHT = 7007;
    public static final int STATUS_PARTICIPANT_NOT_CONNECTED = 7003;
    public static final int STATUS_REAL_TIME_CONNECTION_FAILED = 7000;
    public static final int STATUS_REAL_TIME_INACTIVE_ROOM = 7005;
    public static final int STATUS_REAL_TIME_MESSAGE_FAILED = -1;
    public static final int STATUS_REAL_TIME_MESSAGE_SEND_FAILED = 7001;
    public static final int STATUS_REAL_TIME_ROOM_NOT_JOINED = 7004;
    private final fl te;
    
    private GamesClient(final Context context, final String s, final String s2, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener, final String[] array, final int n, final View view, final boolean b, final int n2) {
        this.te = new fl(context, s, s2, connectionCallbacks, onConnectionFailedListener, array, n, view, false, b, n2);
    }
    
    @Deprecated
    public void acceptTurnBasedInvitation(final OnTurnBasedMatchInitiatedListener onTurnBasedMatchInitiatedListener, final String s) {
        this.te.e(new a.c<TurnBasedMultiplayer.InitiateMatchResult>() {
            public void a(final TurnBasedMultiplayer.InitiateMatchResult initiateMatchResult) {
                onTurnBasedMatchInitiatedListener.onTurnBasedMatchInitiated(initiateMatchResult.getStatus().getStatusCode(), initiateMatchResult.getMatch());
            }
        }, s);
    }
    
    @Deprecated
    public void cancelTurnBasedMatch(final OnTurnBasedMatchCanceledListener onTurnBasedMatchCanceledListener, final String s) {
        this.te.g(new a.c<TurnBasedMultiplayer.CancelMatchResult>() {
            public void a(final TurnBasedMultiplayer.CancelMatchResult cancelMatchResult) {
                onTurnBasedMatchCanceledListener.onTurnBasedMatchCanceled(cancelMatchResult.getStatus().getStatusCode(), cancelMatchResult.getMatchId());
            }
        }, s);
    }
    
    @Deprecated
    public void cancelTurnBasedMatch(final String s) {
        this.te.g(new a.c<TurnBasedMultiplayer.CancelMatchResult>() {
            public void a(final TurnBasedMultiplayer.CancelMatchResult cancelMatchResult) {
            }
        }, s);
    }
    
    @Deprecated
    public void clearAllNotifications() {
        this.te.clearNotifications(-1);
    }
    
    @Deprecated
    public void clearNotifications(final int n) {
        this.te.clearNotifications(n);
    }
    
    @Deprecated
    @Override
    public void connect() {
        this.te.connect();
    }
    
    @Deprecated
    public void createRoom(final RoomConfig roomConfig) {
        this.te.createRoom(roomConfig);
    }
    
    @Deprecated
    public void createTurnBasedMatch(final OnTurnBasedMatchInitiatedListener onTurnBasedMatchInitiatedListener, final TurnBasedMatchConfig turnBasedMatchConfig) {
        this.te.a(new a.c<TurnBasedMultiplayer.InitiateMatchResult>() {
            public void a(final TurnBasedMultiplayer.InitiateMatchResult initiateMatchResult) {
                onTurnBasedMatchInitiatedListener.onTurnBasedMatchInitiated(initiateMatchResult.getStatus().getStatusCode(), initiateMatchResult.getMatch());
            }
        }, turnBasedMatchConfig);
    }
    
    @Deprecated
    public void declineRoomInvitation(final String s) {
        this.te.j(s, 0);
    }
    
    @Deprecated
    public void declineTurnBasedInvitation(final String s) {
        this.te.j(s, 1);
    }
    
    @Deprecated
    @Override
    public void disconnect() {
        this.te.disconnect();
    }
    
    @Deprecated
    public void dismissRoomInvitation(final String s) {
        this.te.i(s, 0);
    }
    
    @Deprecated
    public void dismissTurnBasedInvitation(final String s) {
        this.te.i(s, 1);
    }
    
    @Deprecated
    public void dismissTurnBasedMatch(final String s) {
        this.te.dismissTurnBasedMatch(s);
    }
    
    @Deprecated
    public void finishTurnBasedMatch(final OnTurnBasedMatchUpdatedListener onTurnBasedMatchUpdatedListener, final String s) {
        this.te.a(new a.c<TurnBasedMultiplayer.UpdateMatchResult>() {
            public void a(final TurnBasedMultiplayer.UpdateMatchResult updateMatchResult) {
                onTurnBasedMatchUpdatedListener.onTurnBasedMatchUpdated(updateMatchResult.getStatus().getStatusCode(), updateMatchResult.getMatch());
            }
        }, s, null, null);
    }
    
    @Deprecated
    public void finishTurnBasedMatch(final OnTurnBasedMatchUpdatedListener onTurnBasedMatchUpdatedListener, final String s, final byte[] array, final List<ParticipantResult> list) {
        ParticipantResult[] array2;
        if (list == null) {
            array2 = null;
        }
        else {
            array2 = list.toArray(new ParticipantResult[list.size()]);
        }
        this.finishTurnBasedMatch(onTurnBasedMatchUpdatedListener, s, array, array2);
    }
    
    @Deprecated
    public void finishTurnBasedMatch(final OnTurnBasedMatchUpdatedListener onTurnBasedMatchUpdatedListener, final String s, final byte[] array, final ParticipantResult... array2) {
        this.te.a(new a.c<TurnBasedMultiplayer.UpdateMatchResult>() {
            public void a(final TurnBasedMultiplayer.UpdateMatchResult updateMatchResult) {
                onTurnBasedMatchUpdatedListener.onTurnBasedMatchUpdated(updateMatchResult.getStatus().getStatusCode(), updateMatchResult.getMatch());
            }
        }, s, array, array2);
    }
    
    @Deprecated
    public Intent getAchievementsIntent() {
        return this.te.getAchievementsIntent();
    }
    
    @Deprecated
    public Intent getAllLeaderboardsIntent() {
        return this.te.getAllLeaderboardsIntent();
    }
    
    @Deprecated
    public String getAppId() {
        return this.te.getAppId();
    }
    
    @Deprecated
    public String getCurrentAccountName() {
        return this.te.getCurrentAccountName();
    }
    
    @Deprecated
    public Game getCurrentGame() {
        return this.te.getCurrentGame();
    }
    
    @Deprecated
    public Player getCurrentPlayer() {
        return this.te.getCurrentPlayer();
    }
    
    @Deprecated
    public String getCurrentPlayerId() {
        return this.te.getCurrentPlayerId();
    }
    
    @Deprecated
    public Intent getInvitationInboxIntent() {
        return this.te.getInvitationInboxIntent();
    }
    
    @Deprecated
    public Intent getLeaderboardIntent(final String s) {
        return this.te.getLeaderboardIntent(s);
    }
    
    @Deprecated
    public Intent getMatchInboxIntent() {
        return this.te.getMatchInboxIntent();
    }
    
    @Deprecated
    public int getMaxTurnBasedMatchDataSize() {
        return this.te.getMaxTurnBasedMatchDataSize();
    }
    
    @Deprecated
    public Intent getPlayerSearchIntent() {
        return this.te.getPlayerSearchIntent();
    }
    
    @Deprecated
    public Intent getRealTimeSelectOpponentsIntent(final int n, final int n2) {
        return this.te.getRealTimeSelectOpponentsIntent(n, n2, true);
    }
    
    @Deprecated
    public Intent getRealTimeSelectOpponentsIntent(final int n, final int n2, final boolean b) {
        return this.te.getRealTimeSelectOpponentsIntent(n, n2, b);
    }
    
    @Deprecated
    public RealTimeSocket getRealTimeSocketForParticipant(final String s, final String s2) {
        return this.te.getRealTimeSocketForParticipant(s, s2);
    }
    
    @Deprecated
    public Intent getRealTimeWaitingRoomIntent(final Room room, final int n) {
        return this.te.getRealTimeWaitingRoomIntent(room, n);
    }
    
    @Deprecated
    public Intent getSettingsIntent() {
        return this.te.getSettingsIntent();
    }
    
    @Deprecated
    public void getTurnBasedMatch(final OnTurnBasedMatchLoadedListener onTurnBasedMatchLoadedListener, final String s) {
        this.te.h(new a.c<TurnBasedMultiplayer.LoadMatchResult>() {
            public void a(final TurnBasedMultiplayer.LoadMatchResult loadMatchResult) {
                onTurnBasedMatchLoadedListener.onTurnBasedMatchLoaded(loadMatchResult.getStatus().getStatusCode(), loadMatchResult.getMatch());
            }
        }, s);
    }
    
    @Deprecated
    public Intent getTurnBasedSelectOpponentsIntent(final int n, final int n2) {
        return this.te.getTurnBasedSelectOpponentsIntent(n, n2, true);
    }
    
    @Deprecated
    public Intent getTurnBasedSelectOpponentsIntent(final int n, final int n2, final boolean b) {
        return this.te.getTurnBasedSelectOpponentsIntent(n, n2, b);
    }
    
    @Deprecated
    public void incrementAchievement(final String s, final int n) {
        this.te.a(null, s, n);
    }
    
    @Deprecated
    public void incrementAchievementImmediate(final OnAchievementUpdatedListener onAchievementUpdatedListener, final String s, final int n) {
        this.te.a(new a.c<Achievements.UpdateAchievementResult>() {
            public void a(final Achievements.UpdateAchievementResult updateAchievementResult) {
                onAchievementUpdatedListener.onAchievementUpdated(updateAchievementResult.getStatus().getStatusCode(), updateAchievementResult.getAchievementId());
            }
        }, s, n);
    }
    
    @Deprecated
    @Override
    public boolean isConnected() {
        return this.te.isConnected();
    }
    
    @Deprecated
    @Override
    public boolean isConnecting() {
        return this.te.isConnecting();
    }
    
    @Deprecated
    @Override
    public boolean isConnectionCallbacksRegistered(final ConnectionCallbacks connectionCallbacks) {
        return this.te.isConnectionCallbacksRegistered(connectionCallbacks);
    }
    
    @Deprecated
    @Override
    public boolean isConnectionFailedListenerRegistered(final OnConnectionFailedListener onConnectionFailedListener) {
        return this.te.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    @Deprecated
    public void joinRoom(final RoomConfig roomConfig) {
        this.te.joinRoom(roomConfig);
    }
    
    @Deprecated
    public void leaveRoom(final RoomUpdateListener roomUpdateListener, final String s) {
        this.te.leaveRoom(roomUpdateListener, s);
    }
    
    @Deprecated
    public void leaveTurnBasedMatch(final OnTurnBasedMatchLeftListener onTurnBasedMatchLeftListener, final String s) {
        this.te.f(new a.c<TurnBasedMultiplayer.LeaveMatchResult>() {
            public void a(final TurnBasedMultiplayer.LeaveMatchResult leaveMatchResult) {
                onTurnBasedMatchLeftListener.onTurnBasedMatchLeft(leaveMatchResult.getStatus().getStatusCode(), leaveMatchResult.getMatch());
            }
        }, s);
    }
    
    @Deprecated
    public void leaveTurnBasedMatchDuringTurn(final OnTurnBasedMatchLeftListener onTurnBasedMatchLeftListener, final String s, final String s2) {
        this.te.a(new a.c<TurnBasedMultiplayer.LeaveMatchResult>() {
            public void a(final TurnBasedMultiplayer.LeaveMatchResult leaveMatchResult) {
                onTurnBasedMatchLeftListener.onTurnBasedMatchLeft(leaveMatchResult.getStatus().getStatusCode(), leaveMatchResult.getMatch());
            }
        }, s, s2);
    }
    
    @Deprecated
    public void loadAchievements(final OnAchievementsLoadedListener onAchievementsLoadedListener, final boolean b) {
        this.te.b(new a.c<Achievements.LoadAchievementsResult>() {
            public void a(final Achievements.LoadAchievementsResult loadAchievementsResult) {
                onAchievementsLoadedListener.onAchievementsLoaded(loadAchievementsResult.getStatus().getStatusCode(), loadAchievementsResult.getAchievements());
            }
        }, b);
    }
    
    @Deprecated
    public void loadCurrentPlayerLeaderboardScore(final OnPlayerLeaderboardScoreLoadedListener onPlayerLeaderboardScoreLoadedListener, final String s, final int n, final int n2) {
        this.te.a(new a.c<Leaderboards.LoadPlayerScoreResult>() {
            public void a(final Leaderboards.LoadPlayerScoreResult loadPlayerScoreResult) {
                onPlayerLeaderboardScoreLoadedListener.onPlayerLeaderboardScoreLoaded(loadPlayerScoreResult.getStatus().getStatusCode(), loadPlayerScoreResult.getScore());
            }
        }, null, s, n, n2);
    }
    
    @Deprecated
    public void loadGame(final OnGamesLoadedListener onGamesLoadedListener) {
        this.te.g(new a.c<GamesMetadata.LoadGamesResult>() {
            public void a(final GamesMetadata.LoadGamesResult loadGamesResult) {
                onGamesLoadedListener.onGamesLoaded(loadGamesResult.getStatus().getStatusCode(), loadGamesResult.getGames());
            }
        });
    }
    
    @Deprecated
    public void loadInvitablePlayers(final OnPlayersLoadedListener onPlayersLoadedListener, final int n, final boolean b) {
        this.te.a(new a.c<Players.LoadPlayersResult>() {
            public void a(final Players.LoadPlayersResult loadPlayersResult) {
                onPlayersLoadedListener.onPlayersLoaded(loadPlayersResult.getStatus().getStatusCode(), loadPlayersResult.getPlayers());
            }
        }, n, false, b);
    }
    
    @Deprecated
    public void loadInvitations(final OnInvitationsLoadedListener onInvitationsLoadedListener) {
        this.te.h(new a.c<Invitations.LoadInvitationsResult>() {
            public void a(final Invitations.LoadInvitationsResult loadInvitationsResult) {
                onInvitationsLoadedListener.onInvitationsLoaded(loadInvitationsResult.getStatus().getStatusCode(), loadInvitationsResult.getInvitations());
            }
        });
    }
    
    @Deprecated
    public void loadLeaderboardMetadata(final OnLeaderboardMetadataLoadedListener onLeaderboardMetadataLoadedListener, final String s, final boolean b) {
        this.te.a(new a.c<Leaderboards.LeaderboardMetadataResult>() {
            public void a(final Leaderboards.LeaderboardMetadataResult leaderboardMetadataResult) {
                onLeaderboardMetadataLoadedListener.onLeaderboardMetadataLoaded(leaderboardMetadataResult.getStatus().getStatusCode(), leaderboardMetadataResult.getLeaderboards());
            }
        }, s, b);
    }
    
    @Deprecated
    public void loadLeaderboardMetadata(final OnLeaderboardMetadataLoadedListener onLeaderboardMetadataLoadedListener, final boolean b) {
        this.te.a(new a.c<Leaderboards.LeaderboardMetadataResult>() {
            public void a(final Leaderboards.LeaderboardMetadataResult leaderboardMetadataResult) {
                onLeaderboardMetadataLoadedListener.onLeaderboardMetadataLoaded(leaderboardMetadataResult.getStatus().getStatusCode(), leaderboardMetadataResult.getLeaderboards());
            }
        }, b);
    }
    
    @Deprecated
    public void loadMoreInvitablePlayers(final OnPlayersLoadedListener onPlayersLoadedListener, final int n) {
        this.te.a(new a.c<Players.LoadPlayersResult>() {
            public void a(final Players.LoadPlayersResult loadPlayersResult) {
                onPlayersLoadedListener.onPlayersLoaded(loadPlayersResult.getStatus().getStatusCode(), loadPlayersResult.getPlayers());
            }
        }, n, true, false);
    }
    
    @Deprecated
    public void loadMoreScores(final OnLeaderboardScoresLoadedListener onLeaderboardScoresLoadedListener, final LeaderboardScoreBuffer leaderboardScoreBuffer, final int n, final int n2) {
        this.te.a(new a.c<Leaderboards.LoadScoresResult>() {
            public void a(final Leaderboards.LoadScoresResult loadScoresResult) {
                onLeaderboardScoresLoadedListener.onLeaderboardScoresLoaded(loadScoresResult.getStatus().getStatusCode(), loadScoresResult.getLeaderboard(), loadScoresResult.getScores());
            }
        }, leaderboardScoreBuffer, n, n2);
    }
    
    @Deprecated
    public void loadPlayer(final OnPlayersLoadedListener onPlayersLoadedListener, final String s) {
        this.te.a(new a.c<Players.LoadPlayersResult>() {
            public void a(final Players.LoadPlayersResult loadPlayersResult) {
                onPlayersLoadedListener.onPlayersLoaded(loadPlayersResult.getStatus().getStatusCode(), loadPlayersResult.getPlayers());
            }
        }, s);
    }
    
    @Deprecated
    public void loadPlayerCenteredScores(final OnLeaderboardScoresLoadedListener onLeaderboardScoresLoadedListener, final String s, final int n, final int n2, final int n3) {
        this.te.b(new a.c<Leaderboards.LoadScoresResult>() {
            public void a(final Leaderboards.LoadScoresResult loadScoresResult) {
                onLeaderboardScoresLoadedListener.onLeaderboardScoresLoaded(loadScoresResult.getStatus().getStatusCode(), loadScoresResult.getLeaderboard(), loadScoresResult.getScores());
            }
        }, s, n, n2, n3, false);
    }
    
    @Deprecated
    public void loadPlayerCenteredScores(final OnLeaderboardScoresLoadedListener onLeaderboardScoresLoadedListener, final String s, final int n, final int n2, final int n3, final boolean b) {
        this.te.b(new a.c<Leaderboards.LoadScoresResult>() {
            public void a(final Leaderboards.LoadScoresResult loadScoresResult) {
                onLeaderboardScoresLoadedListener.onLeaderboardScoresLoaded(loadScoresResult.getStatus().getStatusCode(), loadScoresResult.getLeaderboard(), loadScoresResult.getScores());
            }
        }, s, n, n2, n3, b);
    }
    
    @Deprecated
    public void loadTopScores(final OnLeaderboardScoresLoadedListener onLeaderboardScoresLoadedListener, final String s, final int n, final int n2, final int n3) {
        this.te.a(new a.c<Leaderboards.LoadScoresResult>() {
            public void a(final Leaderboards.LoadScoresResult loadScoresResult) {
                onLeaderboardScoresLoadedListener.onLeaderboardScoresLoaded(loadScoresResult.getStatus().getStatusCode(), loadScoresResult.getLeaderboard(), loadScoresResult.getScores());
            }
        }, s, n, n2, n3, false);
    }
    
    @Deprecated
    public void loadTopScores(final OnLeaderboardScoresLoadedListener onLeaderboardScoresLoadedListener, final String s, final int n, final int n2, final int n3, final boolean b) {
        this.te.a(new a.c<Leaderboards.LoadScoresResult>() {
            public void a(final Leaderboards.LoadScoresResult loadScoresResult) {
                onLeaderboardScoresLoadedListener.onLeaderboardScoresLoaded(loadScoresResult.getStatus().getStatusCode(), loadScoresResult.getLeaderboard(), loadScoresResult.getScores());
            }
        }, s, n, n2, n3, b);
    }
    
    @Deprecated
    public void loadTurnBasedMatches(final OnTurnBasedMatchesLoadedListener onTurnBasedMatchesLoadedListener, final int... array) {
        this.te.a(new a.c<TurnBasedMultiplayer.LoadMatchesResult>() {
            public void a(final TurnBasedMultiplayer.LoadMatchesResult loadMatchesResult) {
                onTurnBasedMatchesLoadedListener.onTurnBasedMatchesLoaded(loadMatchesResult.getStatus().getStatusCode(), loadMatchesResult.getMatches());
            }
        }, array);
    }
    
    @Deprecated
    public void reconnect() {
        this.te.disconnect();
        this.te.connect();
    }
    
    @Deprecated
    @Override
    public void registerConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.te.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Deprecated
    @Override
    public void registerConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.te.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Deprecated
    public void registerInvitationListener(final OnInvitationReceivedListener onInvitationReceivedListener) {
        this.te.registerInvitationListener(onInvitationReceivedListener);
    }
    
    @Deprecated
    public void registerMatchUpdateListener(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
        this.te.registerMatchUpdateListener(onTurnBasedMatchUpdateReceivedListener);
    }
    
    @Deprecated
    public void rematchTurnBasedMatch(final OnTurnBasedMatchInitiatedListener onTurnBasedMatchInitiatedListener, final String s) {
        this.te.d(new a.c<TurnBasedMultiplayer.InitiateMatchResult>() {
            public void a(final TurnBasedMultiplayer.InitiateMatchResult initiateMatchResult) {
                onTurnBasedMatchInitiatedListener.onTurnBasedMatchInitiated(initiateMatchResult.getStatus().getStatusCode(), initiateMatchResult.getMatch());
            }
        }, s);
    }
    
    @Deprecated
    public void revealAchievement(final String s) {
        this.te.b(null, s);
    }
    
    @Deprecated
    public void revealAchievementImmediate(final OnAchievementUpdatedListener onAchievementUpdatedListener, final String s) {
        this.te.b(new a.c<Achievements.UpdateAchievementResult>() {
            public void a(final Achievements.UpdateAchievementResult updateAchievementResult) {
                onAchievementUpdatedListener.onAchievementUpdated(updateAchievementResult.getStatus().getStatusCode(), updateAchievementResult.getAchievementId());
            }
        }, s);
    }
    
    @Deprecated
    public int sendReliableRealTimeMessage(final RealTimeReliableMessageSentListener realTimeReliableMessageSentListener, final byte[] array, final String s, final String s2) {
        return this.te.a(new RealTimeMultiplayer.ReliableMessageSentCallback() {
            @Override
            public void onRealTimeMessageSent(final int n, final int n2, final String s) {
                realTimeReliableMessageSentListener.onRealTimeMessageSent(n, n2, s);
            }
        }, array, s, s2);
    }
    
    @Deprecated
    public int sendUnreliableRealTimeMessage(final byte[] array, final String s, final String s2) {
        return this.te.a(array, s, new String[] { s2 });
    }
    
    @Deprecated
    public int sendUnreliableRealTimeMessage(final byte[] array, final String s, final List<String> list) {
        return this.te.a(array, s, list.toArray(new String[list.size()]));
    }
    
    @Deprecated
    public int sendUnreliableRealTimeMessageToAll(final byte[] array, final String s) {
        return this.te.sendUnreliableRealTimeMessageToAll(array, s);
    }
    
    @Deprecated
    public void setAchievementSteps(final String s, final int n) {
        this.te.b(null, s, n);
    }
    
    @Deprecated
    public void setAchievementStepsImmediate(final OnAchievementUpdatedListener onAchievementUpdatedListener, final String s, final int n) {
        this.te.b(new a.c<Achievements.UpdateAchievementResult>() {
            public void a(final Achievements.UpdateAchievementResult updateAchievementResult) {
                onAchievementUpdatedListener.onAchievementUpdated(updateAchievementResult.getStatus().getStatusCode(), updateAchievementResult.getAchievementId());
            }
        }, s, n);
    }
    
    @Deprecated
    public void setGravityForPopups(final int gravityForPopups) {
        this.te.setGravityForPopups(gravityForPopups);
    }
    
    @Deprecated
    public void setViewForPopups(final View viewForPopups) {
        eg.f(viewForPopups);
        this.te.setViewForPopups(viewForPopups);
    }
    
    @Deprecated
    public void signOut() {
        this.te.b(new a.c<Status>() {
            public void a(final Status status) {
            }
        });
    }
    
    @Deprecated
    public void signOut(final OnSignOutCompleteListener onSignOutCompleteListener) {
        this.te.b(new a.c<Status>() {
            public void a(final Status status) {
                onSignOutCompleteListener.onSignOutComplete();
            }
        });
    }
    
    @Deprecated
    public void submitScore(final String s, final long n) {
        this.te.a(null, s, n, null);
    }
    
    @Deprecated
    public void submitScore(final String s, final long n, final String s2) {
        this.te.a(null, s, n, s2);
    }
    
    @Deprecated
    public void submitScoreImmediate(final OnScoreSubmittedListener onScoreSubmittedListener, final String s, final long n) {
        this.te.a(new a.c<Leaderboards.SubmitScoreResult>() {
            public void a(final Leaderboards.SubmitScoreResult submitScoreResult) {
                onScoreSubmittedListener.onScoreSubmitted(submitScoreResult.getStatus().getStatusCode(), new SubmitScoreResult(submitScoreResult.getScoreData().dx()));
            }
        }, s, n, null);
    }
    
    @Deprecated
    public void submitScoreImmediate(final OnScoreSubmittedListener onScoreSubmittedListener, final String s, final long n, final String s2) {
        this.te.a(new a.c<Leaderboards.SubmitScoreResult>() {
            public void a(final Leaderboards.SubmitScoreResult submitScoreResult) {
                onScoreSubmittedListener.onScoreSubmitted(submitScoreResult.getStatus().getStatusCode(), new SubmitScoreResult(submitScoreResult.getScoreData().dx()));
            }
        }, s, n, s2);
    }
    
    @Deprecated
    public void takeTurn(final OnTurnBasedMatchUpdatedListener onTurnBasedMatchUpdatedListener, final String s, final byte[] array, final String s2) {
        this.te.a(new a.c<TurnBasedMultiplayer.UpdateMatchResult>() {
            public void a(final TurnBasedMultiplayer.UpdateMatchResult updateMatchResult) {
                onTurnBasedMatchUpdatedListener.onTurnBasedMatchUpdated(updateMatchResult.getStatus().getStatusCode(), updateMatchResult.getMatch());
            }
        }, s, array, s2, null);
    }
    
    @Deprecated
    public void takeTurn(final OnTurnBasedMatchUpdatedListener onTurnBasedMatchUpdatedListener, final String s, final byte[] array, final String s2, final List<ParticipantResult> list) {
        ParticipantResult[] array2;
        if (list == null) {
            array2 = null;
        }
        else {
            array2 = list.toArray(new ParticipantResult[list.size()]);
        }
        this.te.a(new a.c<TurnBasedMultiplayer.UpdateMatchResult>() {
            public void a(final TurnBasedMultiplayer.UpdateMatchResult updateMatchResult) {
                onTurnBasedMatchUpdatedListener.onTurnBasedMatchUpdated(updateMatchResult.getStatus().getStatusCode(), updateMatchResult.getMatch());
            }
        }, s, array, s2, array2);
    }
    
    @Deprecated
    public void takeTurn(final OnTurnBasedMatchUpdatedListener onTurnBasedMatchUpdatedListener, final String s, final byte[] array, final String s2, final ParticipantResult... array2) {
        this.te.a(new a.c<TurnBasedMultiplayer.UpdateMatchResult>() {
            public void a(final TurnBasedMultiplayer.UpdateMatchResult updateMatchResult) {
                onTurnBasedMatchUpdatedListener.onTurnBasedMatchUpdated(updateMatchResult.getStatus().getStatusCode(), updateMatchResult.getMatch());
            }
        }, s, array, s2, array2);
    }
    
    @Deprecated
    public void unlockAchievement(final String s) {
        this.te.c(null, s);
    }
    
    @Deprecated
    public void unlockAchievementImmediate(final OnAchievementUpdatedListener onAchievementUpdatedListener, final String s) {
        this.te.c(new a.c<Achievements.UpdateAchievementResult>() {
            public void a(final Achievements.UpdateAchievementResult updateAchievementResult) {
                onAchievementUpdatedListener.onAchievementUpdated(updateAchievementResult.getStatus().getStatusCode(), updateAchievementResult.getAchievementId());
            }
        }, s);
    }
    
    @Deprecated
    @Override
    public void unregisterConnectionCallbacks(final ConnectionCallbacks connectionCallbacks) {
        this.te.unregisterConnectionCallbacks(connectionCallbacks);
    }
    
    @Deprecated
    @Override
    public void unregisterConnectionFailedListener(final OnConnectionFailedListener onConnectionFailedListener) {
        this.te.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Deprecated
    public void unregisterInvitationListener() {
        this.te.unregisterInvitationListener();
    }
    
    @Deprecated
    public void unregisterMatchUpdateListener() {
        this.te.unregisterMatchUpdateListener();
    }
    
    @Deprecated
    public static final class Builder
    {
        private final ConnectionCallbacks jD;
        private final OnConnectionFailedListener jE;
        private String[] jF;
        private String jG;
        private final Context mContext;
        private boolean tA;
        private int tB;
        private String tx;
        private int ty;
        private View tz;
        
        public Builder(final Context mContext, final ConnectionCallbacks jd, final OnConnectionFailedListener je) {
            this.jG = "<<default account>>";
            this.jF = new String[] { "https://www.googleapis.com/auth/games" };
            this.ty = 49;
            this.tA = true;
            this.tB = 17;
            this.mContext = mContext;
            this.tx = mContext.getPackageName();
            this.jD = jd;
            this.jE = je;
        }
        
        public GamesClient create() {
            return new GamesClient(this.mContext, this.tx, this.jG, this.jD, this.jE, this.jF, this.ty, this.tz, this.tA, this.tB, null);
        }
        
        public Builder setAccountName(final String s) {
            this.jG = eg.f(s);
            return this;
        }
        
        public Builder setGravityForPopups(final int ty) {
            this.ty = ty;
            return this;
        }
        
        public Builder setScopes(final String... jf) {
            this.jF = jf;
            return this;
        }
        
        public Builder setShowConnectingPopup(final boolean ta) {
            this.tA = ta;
            this.tB = 17;
            return this;
        }
        
        public Builder setShowConnectingPopup(final boolean ta, final int tb) {
            this.tA = ta;
            this.tB = tb;
            return this;
        }
        
        public Builder setViewForPopups(final View view) {
            this.tz = eg.f(view);
            return this;
        }
    }
}
