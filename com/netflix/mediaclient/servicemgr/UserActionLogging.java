// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.logging.client.model.DataContext;
import android.content.Intent;
import org.json.JSONObject;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.logging.client.model.UIError;

public interface UserActionLogging
{
    public static final String[] ACTIONS = { "com.netflix.mediaclient.intent.action.LOG_UIA_SIGNUP_START", "com.netflix.mediaclient.intent.action.LOG_UIA_SIGNUP_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_ADD_TO_PLAYLIST_START", "com.netflix.mediaclient.intent.action.LOG_UIA_ADD_TO_PLAYLIST_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_LOGIN_START", "com.netflix.mediaclient.intent.action.LOG_UIA_LOGIN_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_REMOVE_FROM_PLAYLIST_START", "com.netflix.mediaclient.intent.action.LOG_UIA_REMOVE_FROM_PLAYLIST_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_NAVIGATION_START", "com.netflix.mediaclient.intent.action.LOG_UIA_NAVIGATION_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_RATE_TITLE_START", "com.netflix.mediaclient.intent.action.LOG_UIA_RATE_TITLE_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_REGISTER_START", "com.netflix.mediaclient.intent.action.LOG_UIA_REGISTER_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_SEARCH_START", "com.netflix.mediaclient.intent.action.LOG_UIA_SEARCH_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_START_PLAY_START", "com.netflix.mediaclient.intent.action.LOG_UIA_START_PLAY_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_SUBMIT_PAYMENT_START", "com.netflix.mediaclient.intent.action.LOG_UIA_SUBMIT_PAYMENT_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_UPGRADE_STREAMS_START", "com.netflix.mediaclient.intent.action.LOG_UIA_UPGRADE_STREAMS_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_SELECT_PROFILE_START", "com.netflix.mediaclient.intent.action.LOG_UIA_SELECT_PROFILE_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_ADD_PROFILE_START", "com.netflix.mediaclient.intent.action.LOG_UIA_ADD_PROFILE_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_EDIT_PROFILE_START", "com.netflix.mediaclient.intent.action.LOG_UIA_EDIT_PROFILE_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_DELETE_PROFILE_START", "com.netflix.mediaclient.intent.action.LOG_UIA_DELETE_PROFILE_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_SAY_THANKS_START", "com.netflix.mediaclient.intent.action.LOG_UIA_SAY_THANKS_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_NEW_LOLOMO_START", "com.netflix.mediaclient.intent.action.LOG_UIA_NEW_LOLOMO_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_SAY_THANKS_START", "com.netflix.mediaclient.intent.action.LOG_UIA_RECOMMEND_SHEET_START", "com.netflix.mediaclient.intent.action.LOG_UIA_RECOMMEND_SHEET_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_SHARE_SHEET_START", "com.netflix.mediaclient.intent.action.LOG_UIA_SHARE_SHEET_ENDED", "com.netflix.mediaclient.intent.action.LOG_UIA_SHARE_SHEET_OPEN_START", "com.netflix.mediaclient.intent.action.LOG_UIA_SHARE_SHEET_OPEN_ENDED", "com.netflix.mediaclient.intent.action.PREAPP_WIDGET_ACTION_START", "com.netflix.mediaclient.intent.action.PREAPP_WIDGET_ACTION_ENDED" };
    public static final String ADD_PROFILE_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_ADD_PROFILE_ENDED";
    public static final String ADD_PROFILE_START = "com.netflix.mediaclient.intent.action.LOG_UIA_ADD_PROFILE_START";
    public static final String ADD_TO_PLAYLIST_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_ADD_TO_PLAYLIST_ENDED";
    public static final String ADD_TO_PLAYLIST_START = "com.netflix.mediaclient.intent.action.LOG_UIA_ADD_TO_PLAYLIST_START";
    public static final String DELETE_PROFILE_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_DELETE_PROFILE_ENDED";
    public static final String DELETE_PROFILE_START = "com.netflix.mediaclient.intent.action.LOG_UIA_DELETE_PROFILE_START";
    public static final String EDIT_PROFILE_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_EDIT_PROFILE_ENDED";
    public static final String EDIT_PROFILE_START = "com.netflix.mediaclient.intent.action.LOG_UIA_EDIT_PROFILE_START";
    public static final String EXTRA_CMD = "cmd";
    public static final String EXTRA_ERROR = "error";
    public static final String EXTRA_ERROR_CODE = "error_code";
    public static final String EXTRA_HTTP_RESPONSE = "http_response";
    public static final String EXTRA_ID = "id";
    public static final String EXTRA_MERCURY_EVENT_GUID = "mercuryEventGuid";
    public static final String EXTRA_MERCURY_MESSAGE_GUID = "mercuryMessageGuid";
    public static final String EXTRA_PAYMENT_TYPE = "payment_type";
    public static final String EXTRA_PLAYER_TYPE = "playerType";
    public static final String EXTRA_PROFILE_AGE = "profile_age";
    public static final String EXTRA_PROFILE_ID = "profile_id";
    public static final String EXTRA_PROFILE_IS_KIDS = "profile_is_kids";
    public static final String EXTRA_PROFILE_NAME = "profile_name";
    public static final String EXTRA_RANK = "rank";
    public static final String EXTRA_RATING = "rating";
    public static final String EXTRA_REASON = "reason";
    public static final String EXTRA_REMEMBER_PROFILE = "remember_profile";
    public static final String EXTRA_RENO_CAUSE = "renoCause";
    public static final String EXTRA_RENO_CREATION_TS = "renoCreationTimestamp";
    public static final String EXTRA_RENO_MESSAGE_GUID = "renoMessageGuid";
    public static final String EXTRA_STREAMS = "streams";
    public static final String EXTRA_SUCCESS = "sucess";
    public static final String EXTRA_TERM = "term";
    public static final String EXTRA_TITLE_RANK = "title_rank";
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_VIEW = "view";
    public static final String LOGIN_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_LOGIN_ENDED";
    public static final String LOGIN_START = "com.netflix.mediaclient.intent.action.LOG_UIA_LOGIN_START";
    public static final String NAVIGATION_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_NAVIGATION_ENDED";
    public static final String NAVIGATION_START = "com.netflix.mediaclient.intent.action.LOG_UIA_NAVIGATION_START";
    public static final String NEW_LOLOMO_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_NEW_LOLOMO_ENDED";
    public static final String NEW_LOLOMO_START = "com.netflix.mediaclient.intent.action.LOG_UIA_NEW_LOLOMO_START";
    public static final String PREAPP_WIDGET_ACTION_ENDED = "com.netflix.mediaclient.intent.action.PREAPP_WIDGET_ACTION_ENDED";
    public static final String PREAPP_WIDGET_ACTION_START = "com.netflix.mediaclient.intent.action.PREAPP_WIDGET_ACTION_START";
    public static final String RATE_TITLE_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_RATE_TITLE_ENDED";
    public static final String RATE_TITLE_START = "com.netflix.mediaclient.intent.action.LOG_UIA_RATE_TITLE_START";
    public static final String RECOMMEND_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_SAY_THANKS_ENDED";
    public static final String RECOMMEND_SHEET_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_RECOMMEND_SHEET_ENDED";
    public static final String RECOMMEND_SHEET_START = "com.netflix.mediaclient.intent.action.LOG_UIA_RECOMMEND_SHEET_START";
    public static final String RECOMMEND_START = "com.netflix.mediaclient.intent.action.LOG_UIA_SAY_THANKS_START";
    public static final String REGISTER_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_REGISTER_ENDED";
    public static final String REGISTER_START = "com.netflix.mediaclient.intent.action.LOG_UIA_REGISTER_START";
    public static final String REMOVE_FROM_PLAYLIST_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_REMOVE_FROM_PLAYLIST_ENDED";
    public static final String REMOVE_FROM_PLAYLIST_START = "com.netflix.mediaclient.intent.action.LOG_UIA_REMOVE_FROM_PLAYLIST_START";
    public static final String SAY_THANKS_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_SAY_THANKS_ENDED";
    public static final String SAY_THANKS_START = "com.netflix.mediaclient.intent.action.LOG_UIA_SAY_THANKS_START";
    public static final String SEARCH_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_SEARCH_ENDED";
    public static final String SEARCH_START = "com.netflix.mediaclient.intent.action.LOG_UIA_SEARCH_START";
    public static final String SELECT_PROFILE_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_SELECT_PROFILE_ENDED";
    public static final String SELECT_PROFILE_START = "com.netflix.mediaclient.intent.action.LOG_UIA_SELECT_PROFILE_START";
    public static final String SHARE_SHEET_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_SHARE_SHEET_ENDED";
    public static final String SHARE_SHEET_OPEN_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_SHARE_SHEET_OPEN_ENDED";
    public static final String SHARE_SHEET_OPEN_START = "com.netflix.mediaclient.intent.action.LOG_UIA_SHARE_SHEET_OPEN_START";
    public static final String SHARE_SHEET_START = "com.netflix.mediaclient.intent.action.LOG_UIA_SHARE_SHEET_START";
    public static final String SIGNUP_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_SIGNUP_ENDED";
    public static final String SIGNUP_START = "com.netflix.mediaclient.intent.action.LOG_UIA_SIGNUP_START";
    public static final String START_PLAY_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_START_PLAY_ENDED";
    public static final String START_PLAY_START = "com.netflix.mediaclient.intent.action.LOG_UIA_START_PLAY_START";
    public static final String SUBMIT_PAYMENT_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_SUBMIT_PAYMENT_ENDED";
    public static final String SUBMIT_PAYMENT_START = "com.netflix.mediaclient.intent.action.LOG_UIA_SUBMIT_PAYMENT_START";
    public static final String UPGRADE_STREAMS_ENDED = "com.netflix.mediaclient.intent.action.LOG_UIA_UPGRADE_STREAMS_ENDED";
    public static final String UPGRADE_STREAMS_START = "com.netflix.mediaclient.intent.action.LOG_UIA_UPGRADE_STREAMS_START";
    
    void endAcknowledgeSignupSession(final IClientLogging$CompletionReason p0, final UIError p1, final IClientLogging$ModalView p2);
    
    void endAddProfileSession(final IClientLogging$CompletionReason p0, final IClientLogging$ModalView p1, final UIError p2, final UserActionLogging$Profile p3);
    
    void endAddToPlaylistSession(final IClientLogging$CompletionReason p0, final UIError p1, final int p2);
    
    void endAllActiveSessions();
    
    void endDeleteProfileSession(final IClientLogging$CompletionReason p0, final IClientLogging$ModalView p1, final UIError p2);
    
    void endEditProfileSession(final IClientLogging$CompletionReason p0, final IClientLogging$ModalView p1, final UIError p2, final UserActionLogging$Profile p3);
    
    void endLoginSession(final IClientLogging$CompletionReason p0, final UIError p1);
    
    void endNavigationSession(final IClientLogging$ModalView p0, final IClientLogging$CompletionReason p1, final UIError p2);
    
    void endNewLolomoSession(final IClientLogging$CompletionReason p0, final IClientLogging$ModalView p1, final UIError p2, final String p3, final String p4, final long p5, final String p6, final String p7);
    
    void endPreAppWidgetActionSession(final IClientLogging$CompletionReason p0, final UIError p1);
    
    void endRateTitleSession(final IClientLogging$CompletionReason p0, final UIError p1, final Integer p2, final int p3);
    
    void endRecommendSheetSession(final IClientLogging$CompletionReason p0, final IClientLogging$ModalView p1, final UIError p2);
    
    void endRegisterSession(final IClientLogging$CompletionReason p0, final UIError p1);
    
    void endRemoveFromPlaylistSession(final IClientLogging$CompletionReason p0, final UIError p1);
    
    void endSayThanksSession(final IClientLogging$CompletionReason p0, final IClientLogging$ModalView p1, final UIError p2);
    
    void endSearchSession(final long p0, final IClientLogging$CompletionReason p1, final UIError p2);
    
    void endSelectProfileSession(final IClientLogging$CompletionReason p0, final IClientLogging$ModalView p1, final UIError p2);
    
    void endShareSheetOpenSession(final IClientLogging$CompletionReason p0, final IClientLogging$ModalView p1, final UIError p2);
    
    void endShareSheetSession(final IClientLogging$CompletionReason p0, final IClientLogging$ModalView p1, final UIError p2);
    
    void endStartPlaySession(final IClientLogging$CompletionReason p0, final UIError p1, final Integer p2, final PlayerType p3);
    
    void endSubmitPaymentSession(final IClientLogging$CompletionReason p0, final UIError p1, final boolean p2, final UserActionLogging$PaymentType p3, final JSONObject p4);
    
    void endUpgradeStreamsSession(final IClientLogging$CompletionReason p0, final UIError p1, final UserActionLogging$Streams p2);
    
    boolean handleIntent(final Intent p0, final boolean p1);
    
    void setDataContext(final DataContext p0);
    
    void startAcknowledgeSignupSession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1);
    
    void startAddProfileSession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1);
    
    void startAddToPlaylistSession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1);
    
    void startDeleteProfileSession(final String p0, final UserActionLogging$CommandName p1, final IClientLogging$ModalView p2);
    
    void startEditProfileSession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1);
    
    void startLoginSession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1);
    
    void startNavigationSession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1);
    
    void startNewLolomoSession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1);
    
    void startPreAppWidgetActionSession(final UserActionLogging$CommandName p0, final String p1, final String p2);
    
    void startRateTitleSession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1);
    
    void startRecommendSheetSession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1);
    
    void startRegisterSession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1);
    
    void startRemoveFromPlaylistSession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1);
    
    void startSayThanksSession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1);
    
    void startSearchSession(final long p0, final UserActionLogging$CommandName p1, final IClientLogging$ModalView p2, final String p3);
    
    void startSelectProfileSession(final String p0, final UserActionLogging$RememberProfile p1, final UserActionLogging$CommandName p2, final IClientLogging$ModalView p3);
    
    void startShareSheetOpenSession(final String p0, final UserActionLogging$CommandName p1, final IClientLogging$ModalView p2);
    
    void startShareSheetSession(final String p0, final UserActionLogging$CommandName p1, final IClientLogging$ModalView p2);
    
    void startStartPlaySession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1);
    
    void startSubmitPaymentSession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1);
    
    void startUpgradeStreamsSession(final UserActionLogging$CommandName p0, final IClientLogging$ModalView p1, final UserActionLogging$Streams p2);
}
