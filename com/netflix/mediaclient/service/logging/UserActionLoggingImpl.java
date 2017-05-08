// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.uiaction.model.UpgradeStreamsEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.SurveyEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.SurveyQuestionEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.SubmitPaymentEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.StartPlayEndedEvent;
import com.netflix.mediaclient.service.logging.android.model.ShareSheetEndedEvent;
import com.netflix.mediaclient.service.logging.android.model.ShareSheetOpenEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.SerializeLolomoEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.SelectProfileEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.RemoveFromPlaylistEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.RegisterEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.RateTitleEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.PrefetchLolomoJobEndedEvent;
import com.netflix.mediaclient.service.logging.android.preapp.model.PreAppWidgetActionEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.PostPlayEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.NewLolomoEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.NavigationEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.LoginEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.EditProfileEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.DeserializeLolomoEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.DeleteProfileEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.ChangeValueEndedEvent;
import java.util.Iterator;
import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PaymentType;
import com.netflix.mediaclient.service.logging.uiaction.model.AddToPlaylistEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.AddProfileEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.AcknowledgeSignupEndedEvent;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.service.logging.client.model.Event;
import com.netflix.mediaclient.servicemgr.UserActionLogging$Streams;
import com.netflix.mediaclient.ui.common.PlayLocationType;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.servicemgr.UserActionLogging$RememberProfile;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PostPlayExperience;
import com.netflix.mediaclient.servicemgr.UserActionLogging$Profile;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import java.io.Serializable;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import android.content.Intent;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collection;
import java.util.Arrays;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.logging.uiaction.UpgradeStreamsSession;
import com.netflix.mediaclient.service.logging.uiaction.SurveySession;
import com.netflix.mediaclient.service.logging.uiaction.SurveyQuestionSession;
import com.netflix.mediaclient.service.logging.uiaction.SubmitPaymentSession;
import com.netflix.mediaclient.service.logging.uiaction.StartPlaySession;
import com.netflix.mediaclient.service.logging.android.ShareSheetSession;
import com.netflix.mediaclient.service.logging.android.ShareSheetOpenSession;
import com.netflix.mediaclient.service.logging.uiaction.SerializeLolomoSession;
import com.netflix.mediaclient.service.logging.uiaction.SelectProfileSession;
import com.netflix.mediaclient.service.logging.uiaction.SearchSession;
import java.util.Map;
import com.netflix.mediaclient.service.logging.uiaction.RemoveFromPlaylistSession;
import com.netflix.mediaclient.service.logging.uiaction.RegisterSession;
import com.netflix.mediaclient.service.logging.uiaction.RateTitleSession;
import com.netflix.mediaclient.service.logging.uiaction.PrefetchLolomoJobSession;
import com.netflix.mediaclient.service.logging.android.preapp.PreAppWidgetActionSession;
import com.netflix.mediaclient.service.logging.uiaction.PostPlaySession;
import com.netflix.mediaclient.service.logging.uiaction.NewLolomoSession;
import com.netflix.mediaclient.service.logging.uiaction.NavigationSession;
import com.netflix.mediaclient.service.logging.uiaction.LoginSession;
import com.netflix.mediaclient.service.logging.uiaction.EditProfileSession;
import com.netflix.mediaclient.service.logging.uiaction.DeserializeLolomoSession;
import com.netflix.mediaclient.service.logging.uiaction.DeleteProfileSession;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.uiaction.model.ChangeValueSession;
import com.netflix.mediaclient.service.logging.uiaction.AddToPlaylistSession;
import com.netflix.mediaclient.service.logging.uiaction.AddProfileSession;
import com.netflix.mediaclient.service.logging.uiaction.AcknowledgeSignupSession;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import java.util.HashSet;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

final class UserActionLoggingImpl implements UserActionLogging
{
    private static final String TAG = "nf_log";
    private static final HashSet<IClientLogging$ModalView> excludedViewsSet;
    private AcknowledgeSignupSession mAcknowledgeSignup;
    private AddProfileSession mAddProfileSession;
    private AddToPlaylistSession mAddToPlaylistSession;
    private ChangeValueSession mChangeValueSession;
    private DataContext mDataContext;
    private DeleteProfileSession mDeleteProfileSession;
    private DeserializeLolomoSession mDeserializeLolomoSession;
    private EditProfileSession mEditProfileSession;
    private EventHandler mEventHandler;
    private LoginSession mLoginSession;
    private NavigationSession mNavigationSession;
    private NewLolomoSession mNewLolomoSession;
    private PostPlaySession mPostPlaySession;
    private PreAppWidgetActionSession mPreAppWidgetActionSession;
    private PrefetchLolomoJobSession mPrefetchLolomoJobSession;
    private RateTitleSession mRateTitleSession;
    private RegisterSession mRegisterSession;
    private RemoveFromPlaylistSession mRemoveFromPlaylistSession;
    private Map<Long, SearchSession> mSearchSessions;
    private SelectProfileSession mSelectProfileSession;
    private SerializeLolomoSession mSerializeLolomoSession;
    private ShareSheetOpenSession mShareSheetOpenSession;
    private ShareSheetSession mShareSheetSession;
    private StartPlaySession mStartPlaySession;
    private SubmitPaymentSession mSubmitPaymentSession;
    private SurveyQuestionSession mSurveyQuestionSession;
    private SurveySession mSurveySession;
    private UpgradeStreamsSession mUpgradeStreamsSession;
    private ServiceAgent$UserAgentInterface mUserAgent;
    
    static {
        excludedViewsSet = new HashSet<IClientLogging$ModalView>(Arrays.asList(IClientLogging$ModalView.movieDetails));
    }
    
    UserActionLoggingImpl(final EventHandler mEventHandler, final ServiceAgent$UserAgentInterface mUserAgent) {
        this.mSearchSessions = new ConcurrentHashMap<Long, SearchSession>(5);
        this.mEventHandler = mEventHandler;
        this.mUserAgent = mUserAgent;
    }
    
    private void handleAcknowledgeSignupEnded(final Intent intent) {
    Label_0043_Outer:
        while (true) {
            IClientLogging$ModalView value = null;
            Serializable s = intent.getStringExtra("reason");
            final String stringExtra = intent.getStringExtra("error");
            final String stringExtra2 = intent.getStringExtra("view");
            while (true) {
                while (true) {
                    try {
                        final UIError instance = UIError.createInstance(stringExtra);
                        if (StringUtils.isNotEmpty((String)s)) {
                            s = IClientLogging$CompletionReason.valueOf((String)s);
                            if (StringUtils.isNotEmpty(stringExtra2)) {
                                value = IClientLogging$ModalView.valueOf(stringExtra2);
                            }
                            this.endAcknowledgeSignupSession((IClientLogging$CompletionReason)s, instance, value);
                            return;
                        }
                    }
                    catch (JSONException ex) {
                        Log.e("nf_log", "Failed JSON", (Throwable)ex);
                        final UIError instance = null;
                        continue Label_0043_Outer;
                    }
                    break;
                }
                s = null;
                continue;
            }
        }
    }
    
    private void handleAcknowledgeSignupStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startAcknowledgeSignupSession(value, value2);
    }
    
    private void handleAddProfileEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        final String stringExtra3 = intent.getStringExtra("view");
        while (true) {
            Label_0117: {
                if (!StringUtils.isNotEmpty(stringExtra3)) {
                    break Label_0117;
                }
                final IClientLogging$ModalView value2 = IClientLogging$ModalView.valueOf(stringExtra3);
                while (true) {
                    try {
                        final UIError instance = UIError.createInstance(stringExtra2);
                        if (!StringUtils.isEmpty(stringExtra)) {
                            value = IClientLogging$CompletionReason.valueOf(stringExtra);
                        }
                        this.endAddProfileSession(value, value2, instance, new UserActionLogging$Profile(intent.getStringExtra("profile_id"), intent.getStringExtra("profile_name"), intent.getIntExtra("profile_age", -1), intent.getBooleanExtra("profile_is_kids", false)));
                        return;
                    }
                    catch (JSONException ex) {
                        Log.e("nf_log", "Failed JSON", (Throwable)ex);
                        final UIError instance = null;
                        continue;
                    }
                    break;
                }
            }
            final IClientLogging$ModalView value2 = null;
            continue;
        }
    }
    
    private void handleAddProfileStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (StringUtils.isNotEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startAddProfileSession(value, value2);
    }
    
    private void handleAddToPlaylistEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        final int intExtra = intent.getIntExtra("title_rank", 0);
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endAddToPlaylistSession(value, instance, intExtra);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handleAddToPlaylistStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startAddToPlaylistSession(value, value2);
    }
    
    private void handleChangeValueEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        final String stringExtra3 = intent.getStringExtra("new_value");
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endChangeValueSession(value, instance, stringExtra3);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handleChangeValueStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startChangeValueSession(value, value2);
    }
    
    private void handleDeleteProfileEnded(Intent value) {
        IClientLogging$CompletionReason value2 = null;
        final String stringExtra = value.getStringExtra("reason");
        final String stringExtra2 = value.getStringExtra("error");
        final String stringExtra3 = value.getStringExtra("view");
        while (true) {
            Label_0078: {
                if (!StringUtils.isNotEmpty(stringExtra3)) {
                    break Label_0078;
                }
                value = (Intent)IClientLogging$ModalView.valueOf(stringExtra3);
                while (true) {
                    try {
                        final UIError instance = UIError.createInstance(stringExtra2);
                        if (!StringUtils.isEmpty(stringExtra)) {
                            value2 = IClientLogging$CompletionReason.valueOf(stringExtra);
                        }
                        this.endDeleteProfileSession(value2, (IClientLogging$ModalView)value, instance);
                        return;
                    }
                    catch (JSONException ex) {
                        Log.e("nf_log", "Failed JSON", (Throwable)ex);
                        final UIError instance = null;
                        continue;
                    }
                    break;
                }
            }
            value = null;
            continue;
        }
    }
    
    private void handleDeleteProfileStart(final Intent intent) {
        IClientLogging$ModalView value = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value2;
        if (StringUtils.isNotEmpty(stringExtra)) {
            value2 = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value2 = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startDeleteProfileSession(intent.getStringExtra("profile_id"), value2, value);
    }
    
    private void handleDeserializeLolomoEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endDeserializeLolomoSession(value, instance, intent.getLongExtra("lolomoTtl", -1L));
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handleDeserializeLolomoStarted(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startDeserializeLolomoSession(value, value2);
    }
    
    private void handleEditProfileEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        final String stringExtra3 = intent.getStringExtra("view");
        while (true) {
            Label_0117: {
                if (!StringUtils.isNotEmpty(stringExtra3)) {
                    break Label_0117;
                }
                final IClientLogging$ModalView value2 = IClientLogging$ModalView.valueOf(stringExtra3);
                while (true) {
                    try {
                        final UIError instance = UIError.createInstance(stringExtra2);
                        if (!StringUtils.isEmpty(stringExtra)) {
                            value = IClientLogging$CompletionReason.valueOf(stringExtra);
                        }
                        this.endEditProfileSession(value, value2, instance, new UserActionLogging$Profile(intent.getStringExtra("profile_id"), intent.getStringExtra("profile_name"), intent.getIntExtra("profile_age", -1), intent.getBooleanExtra("profile_is_kids", false)));
                        return;
                    }
                    catch (JSONException ex) {
                        Log.e("nf_log", "Failed JSON", (Throwable)ex);
                        final UIError instance = null;
                        continue;
                    }
                    break;
                }
            }
            final IClientLogging$ModalView value2 = null;
            continue;
        }
    }
    
    private void handleEditProfileStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (StringUtils.isNotEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startEditProfileSession(value, value2);
    }
    
    private void handleLoginEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endLoginSession(value, instance);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handleLoginStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startLoginSession(value, value2);
    }
    
    private void handleNavigationEnded(Intent value) {
        IClientLogging$CompletionReason value2 = null;
        final String stringExtra = value.getStringExtra("reason");
        final String stringExtra2 = value.getStringExtra("error");
        final String stringExtra3 = value.getStringExtra("view");
        while (true) {
            Label_0078: {
                if (!StringUtils.isNotEmpty(stringExtra3)) {
                    break Label_0078;
                }
                value = (Intent)IClientLogging$ModalView.valueOf(stringExtra3);
                while (true) {
                    try {
                        final UIError instance = UIError.createInstance(stringExtra2);
                        if (StringUtils.isNotEmpty(stringExtra)) {
                            value2 = IClientLogging$CompletionReason.valueOf(stringExtra);
                        }
                        this.endNavigationSession((IClientLogging$ModalView)value, value2, instance);
                        return;
                    }
                    catch (JSONException ex) {
                        Log.e("nf_log", "Failed JSON", (Throwable)ex);
                        final UIError instance = null;
                        continue;
                    }
                    break;
                }
            }
            value = null;
            continue;
        }
    }
    
    private void handleNavigationStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startNavigationSession(value, value2);
    }
    
    private void handleNewLolomoEnded(final Intent intent) {
    Label_0092_Outer:
        while (true) {
            Serializable s = intent.getStringExtra("reason");
            final String stringExtra = intent.getStringExtra("error");
            Serializable s2 = intent.getStringExtra("view");
            final String stringExtra2 = intent.getStringExtra("renoCause");
            final String stringExtra3 = intent.getStringExtra("renoMessageGuid");
            final long longExtra = intent.getLongExtra("renoCreationTimestamp", System.currentTimeMillis());
            final String stringExtra4 = intent.getStringExtra("mercuryMessageGuid");
            final String stringExtra5 = intent.getStringExtra("mercuryEventGuid");
            while (true) {
                Label_0147: {
                    while (true) {
                        while (true) {
                            try {
                                final UIError instance = UIError.createInstance(stringExtra);
                                if (!StringUtils.isNotEmpty((String)s)) {
                                    break Label_0147;
                                }
                                s = IClientLogging$CompletionReason.valueOf((String)s);
                                if (StringUtils.isNotEmpty((String)s2)) {
                                    s2 = IClientLogging$ModalView.valueOf((String)s2);
                                    this.endNewLolomoSession((IClientLogging$CompletionReason)s, (IClientLogging$ModalView)s2, instance, stringExtra2, stringExtra3, longExtra, stringExtra4, stringExtra5);
                                    return;
                                }
                            }
                            catch (JSONException ex) {
                                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                                final UIError instance = null;
                                continue Label_0092_Outer;
                            }
                            break;
                        }
                        s2 = null;
                        continue;
                    }
                }
                s = null;
                continue;
            }
        }
    }
    
    private void handleNewLolomoStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startNewLolomoSession(value, value2);
    }
    
    private void handlePostPlayEnded(final Intent intent) {
        Log.d("nf_log", "POSTPLAY_START_ENDED");
        Serializable s = intent.getStringExtra("reason");
        final String stringExtra = intent.getStringExtra("error");
        Serializable s2 = intent.getStringExtra("view");
        final boolean booleanExtra = intent.getBooleanExtra("wasAutoPlayCountdownInterrupted", false);
        final boolean booleanExtra2 = intent.getBooleanExtra("didUserContinueWatching", false);
        int n = intent.getIntExtra("chosenVideoId", -1);
        while (true) {
            while (true) {
                Label_0068: {
                    if (n < 0) {
                        final Integer value = null;
                        break Label_0068;
                    }
                    Label_0147: {
                        break Label_0147;
                        Integer value = null;
                        UIError instance;
                        Integer value2 = null;
                        Label_0099_Outer:Label_0114_Outer:
                        while (true) {
                            n = intent.getIntExtra("trackId", 0);
                            while (true) {
                                Label_0186: {
                                    while (true) {
                                    Label_0180:
                                        while (true) {
                                            try {
                                                instance = UIError.createInstance(stringExtra);
                                                if (!StringUtils.isNotEmpty((String)s)) {
                                                    break Label_0186;
                                                }
                                                s = IClientLogging$CompletionReason.valueOf((String)s);
                                                if (StringUtils.isNotEmpty((String)s2)) {
                                                    s2 = IClientLogging$ModalView.valueOf((String)s2);
                                                    this.endPostPlaySession((IClientLogging$CompletionReason)s, (IClientLogging$ModalView)s2, instance, booleanExtra, booleanExtra2, value, value2, n);
                                                    return;
                                                }
                                                break Label_0180;
                                                value = n;
                                                break;
                                                value2 = n;
                                                continue Label_0099_Outer;
                                            }
                                            catch (JSONException ex) {
                                                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                                                instance = null;
                                                continue Label_0114_Outer;
                                            }
                                            break;
                                        }
                                        s2 = null;
                                        continue;
                                    }
                                }
                                s = null;
                                continue;
                            }
                        }
                    }
                }
                n = intent.getIntExtra("chosenIndex", -1);
                if (n < 0) {
                    final Integer value2 = null;
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    private void handlePostPlayStart(final Intent intent) {
        Log.d("nf_log", "POSTPLAY_START_START");
        final boolean booleanExtra = intent.getBooleanExtra("isAutoPlayCountdownEnabled", false);
        final int intExtra = intent.getIntExtra("lengthOfAutoPlayCountdown", 0);
        final String stringExtra = intent.getStringExtra("postPlayExperience");
        UserActionLogging$PostPlayExperience value = null;
        if (StringUtils.isNotEmpty(stringExtra)) {
            value = UserActionLogging$PostPlayExperience.valueOf(stringExtra);
        }
        this.startPostPlaySession(booleanExtra, intExtra, value);
    }
    
    private void handlePreAppWidgetActionEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (!StringUtils.isEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endPreAppWidgetActionSession(value, instance);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handlePreAppWidgetActionStart(final Intent intent) {
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value = null;
        if (StringUtils.isNotEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        this.startPreAppWidgetActionSession(value, intent.getStringExtra("logData"), intent.getStringExtra("widgetActionData"));
    }
    
    private void handlePrefetchLolomoJobEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endPrefetchLolomoJobSession(value, instance);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handlePrefetchLolomoJobStarted(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startPrefetchLolomoJobSession(value, value2);
    }
    
    private void handleRateTitleEnded(final Intent intent) {
    Label_0060_Outer:
        while (true) {
            Integer value = null;
            Serializable s = intent.getStringExtra("reason");
            final String stringExtra = intent.getStringExtra("error");
            final int intExtra = intent.getIntExtra("rating", 0);
            final int intExtra2 = intent.getIntExtra("rank", Integer.MIN_VALUE);
            while (true) {
                Label_0102: {
                    while (true) {
                        while (true) {
                            try {
                                final UIError instance = UIError.createInstance(stringExtra);
                                if (!StringUtils.isNotEmpty((String)s)) {
                                    break Label_0102;
                                }
                                s = IClientLogging$CompletionReason.valueOf((String)s);
                                if (intExtra2 == Integer.MIN_VALUE) {
                                    this.endRateTitleSession((IClientLogging$CompletionReason)s, instance, value, intExtra);
                                    return;
                                }
                            }
                            catch (JSONException ex) {
                                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                                final UIError instance = null;
                                continue Label_0060_Outer;
                            }
                            break;
                        }
                        value = intExtra2;
                        continue;
                    }
                }
                s = null;
                continue;
            }
        }
    }
    
    private void handleRateTitleStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startRateTitleSession(value, value2);
    }
    
    private void handleRegisterEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endRegisterSession(value, instance);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handleRegisterStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startRegisterSession(value, value2);
    }
    
    private void handleRemoveFromPlaylistEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endRemoveFromPlaylistSession(value, instance);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handleRemoveFromPlaylistStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startRemoveFromPlaylistSession(value, value2);
    }
    
    private void handleSearchEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        final long longExtra = intent.getLongExtra("id", -1L);
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endSearchSession(longExtra, value, instance);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handleSearchStart(final Intent intent) {
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("term");
        final String stringExtra3 = intent.getStringExtra("view");
        IClientLogging$ModalView value2;
        if (StringUtils.isNotEmpty(stringExtra3)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra3);
        }
        else {
            value2 = null;
        }
        this.startSearchSession(intent.getLongExtra("id", -1L), value, value2, stringExtra2);
    }
    
    private void handleSelectProfileEnded(Intent value) {
        IClientLogging$CompletionReason value2 = null;
        final String stringExtra = value.getStringExtra("reason");
        final String stringExtra2 = value.getStringExtra("error");
        final String stringExtra3 = value.getStringExtra("view");
        while (true) {
            Label_0078: {
                if (!StringUtils.isNotEmpty(stringExtra3)) {
                    break Label_0078;
                }
                value = (Intent)IClientLogging$ModalView.valueOf(stringExtra3);
                while (true) {
                    try {
                        final UIError instance = UIError.createInstance(stringExtra2);
                        if (!StringUtils.isEmpty(stringExtra)) {
                            value2 = IClientLogging$CompletionReason.valueOf(stringExtra);
                        }
                        this.endSelectProfileSession(value2, (IClientLogging$ModalView)value, instance);
                        return;
                    }
                    catch (JSONException ex) {
                        Log.e("nf_log", "Failed JSON", (Throwable)ex);
                        final UIError instance = null;
                        continue;
                    }
                    break;
                }
            }
            value = null;
            continue;
        }
    }
    
    private void handleSelectProfileStart(final Intent intent) {
        final UserActionLogging$RememberProfile userActionLogging$RememberProfile = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (StringUtils.isNotEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        else {
            value2 = null;
        }
        final String stringExtra3 = intent.getStringExtra("profile_id");
        final String stringExtra4 = intent.getStringExtra("remember_profile");
        UserActionLogging$RememberProfile value3 = userActionLogging$RememberProfile;
        if (StringUtils.isNotEmpty(stringExtra4)) {
            value3 = UserActionLogging$RememberProfile.valueOf(stringExtra4);
        }
        this.startSelectProfileSession(stringExtra3, value3, value, value2);
    }
    
    private void handleSerializeLolomoEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endSerializeLolomoSession(value, instance, intent.getLongExtra("lolomoFetchedTime", -1L));
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handleSerializeLolomoStarted(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startSerializeLolomoSession(value, value2);
    }
    
    private void handleShareSheetEnded(final Intent intent) {
    Label_0043_Outer:
        while (true) {
            IClientLogging$ModalView value = null;
            Serializable s = intent.getStringExtra("reason");
            final String stringExtra = intent.getStringExtra("error");
            final String stringExtra2 = intent.getStringExtra("view");
            while (true) {
                while (true) {
                    try {
                        final UIError instance = UIError.createInstance(stringExtra);
                        if (StringUtils.isNotEmpty((String)s)) {
                            s = IClientLogging$CompletionReason.valueOf((String)s);
                            if (StringUtils.isNotEmpty(stringExtra2)) {
                                value = IClientLogging$ModalView.valueOf(stringExtra2);
                            }
                            this.endShareSheetSession((IClientLogging$CompletionReason)s, value, instance);
                            return;
                        }
                    }
                    catch (JSONException ex) {
                        Log.e("nf_log", "Failed JSON", (Throwable)ex);
                        final UIError instance = null;
                        continue Label_0043_Outer;
                    }
                    break;
                }
                s = null;
                continue;
            }
        }
    }
    
    private void handleShareSheetOpenEnded(final Intent intent) {
    Label_0043_Outer:
        while (true) {
            IClientLogging$ModalView value = null;
            Serializable s = intent.getStringExtra("reason");
            final String stringExtra = intent.getStringExtra("error");
            final String stringExtra2 = intent.getStringExtra("view");
            while (true) {
                while (true) {
                    try {
                        final UIError instance = UIError.createInstance(stringExtra);
                        if (StringUtils.isNotEmpty((String)s)) {
                            s = IClientLogging$CompletionReason.valueOf((String)s);
                            if (StringUtils.isNotEmpty(stringExtra2)) {
                                value = IClientLogging$ModalView.valueOf(stringExtra2);
                            }
                            this.endShareSheetOpenSession((IClientLogging$CompletionReason)s, value, instance);
                            return;
                        }
                    }
                    catch (JSONException ex) {
                        Log.e("nf_log", "Failed JSON", (Throwable)ex);
                        final UIError instance = null;
                        continue Label_0043_Outer;
                    }
                    break;
                }
                s = null;
                continue;
            }
        }
    }
    
    private void handleShareSheetOpenStart(final Intent intent) {
        IClientLogging$ModalView value = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value2;
        if (!StringUtils.isEmpty(stringExtra)) {
            value2 = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value2 = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startShareSheetOpenSession(intent.getStringExtra("url"), value2, value);
    }
    
    private void handleShareSheetStart(final Intent intent) {
        IClientLogging$ModalView value = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value2;
        if (!StringUtils.isEmpty(stringExtra)) {
            value2 = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value2 = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startShareSheetSession(intent.getStringExtra("url"), value2, value);
    }
    
    private void handleStartPlayEnded(final Intent intent) {
    Label_0046_Outer:
        while (true) {
            Serializable s = intent.getStringExtra("reason");
            final String stringExtra = intent.getStringExtra("error");
            final int intExtra = intent.getIntExtra("rank", Integer.MIN_VALUE);
            while (true) {
                Label_0111: {
                    while (true) {
                        while (true) {
                            try {
                                final UIError instance = UIError.createInstance(stringExtra);
                                if (!StringUtils.isNotEmpty((String)s)) {
                                    break Label_0111;
                                }
                                s = IClientLogging$CompletionReason.valueOf((String)s);
                                if (intExtra == Integer.MIN_VALUE) {
                                    final Integer value = null;
                                    this.endStartPlaySession((IClientLogging$CompletionReason)s, instance, value, PlayerType.toPlayerType(intent.getIntExtra("playerType", -1)), PlayLocationType.create(intent.getStringExtra("playLocation")));
                                    return;
                                }
                            }
                            catch (JSONException ex) {
                                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                                final UIError instance = null;
                                continue Label_0046_Outer;
                            }
                            break;
                        }
                        final Integer value = intExtra;
                        continue;
                    }
                }
                s = null;
                continue;
            }
        }
    }
    
    private void handleStartPlayStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startStartPlaySession(value, value2);
    }
    
    private void handleSubmitPaymentEnded(final Intent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ldc             "reason"
        //     3: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //     6: astore_3       
        //     7: aload_1        
        //     8: ldc_w           "sucess"
        //    11: iconst_0       
        //    12: invokevirtual   android/content/Intent.getBooleanExtra:(Ljava/lang/String;Z)Z
        //    15: istore_2       
        //    16: aload_1        
        //    17: ldc_w           "error_code"
        //    20: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    23: astore          5
        //    25: aload_1        
        //    26: ldc_w           "payment_type"
        //    29: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    32: astore          4
        //    34: aload_1        
        //    35: ldc             "error"
        //    37: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    40: astore_1       
        //    41: aload_1        
        //    42: invokestatic    com/netflix/mediaclient/service/logging/client/model/UIError.createInstance:(Ljava/lang/String;)Lcom/netflix/mediaclient/service/logging/client/model/UIError;
        //    45: astore_1       
        //    46: aload_3        
        //    47: invokestatic    com/netflix/mediaclient/util/StringUtils.isEmpty:(Ljava/lang/String;)Z
        //    50: ifne            143
        //    53: aload_3        
        //    54: invokestatic    com/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason.valueOf:(Ljava/lang/String;)Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;
        //    57: astore_3       
        //    58: aload           4
        //    60: invokestatic    com/netflix/mediaclient/util/StringUtils.isEmpty:(Ljava/lang/String;)Z
        //    63: ifne            137
        //    66: aload           4
        //    68: invokestatic    com/netflix/mediaclient/servicemgr/UserActionLogging$PaymentType.valueOf:(Ljava/lang/String;)Lcom/netflix/mediaclient/servicemgr/UserActionLogging$PaymentType;
        //    71: astore          4
        //    73: aload           5
        //    75: invokestatic    com/netflix/mediaclient/util/StringUtils.isNotEmpty:(Ljava/lang/String;)Z
        //    78: ifeq            131
        //    81: new             Lorg/json/JSONObject;
        //    84: dup            
        //    85: aload           5
        //    87: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    90: astore          5
        //    92: aload_0        
        //    93: aload_3        
        //    94: aload_1        
        //    95: iload_2        
        //    96: aload           4
        //    98: aload           5
        //   100: invokevirtual   com/netflix/mediaclient/service/logging/UserActionLoggingImpl.endSubmitPaymentSession:(Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;Lcom/netflix/mediaclient/service/logging/client/model/UIError;ZLcom/netflix/mediaclient/servicemgr/UserActionLogging$PaymentType;Lorg/json/JSONObject;)V
        //   103: return         
        //   104: astore_1       
        //   105: ldc             "nf_log"
        //   107: ldc             "Failed JSON"
        //   109: aload_1        
        //   110: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   113: pop            
        //   114: aconst_null    
        //   115: astore_1       
        //   116: goto            46
        //   119: astore          5
        //   121: ldc             "nf_log"
        //   123: ldc             "Failed JSON"
        //   125: aload           5
        //   127: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   130: pop            
        //   131: aconst_null    
        //   132: astore          5
        //   134: goto            92
        //   137: aconst_null    
        //   138: astore          4
        //   140: goto            73
        //   143: aconst_null    
        //   144: astore_3       
        //   145: goto            58
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  41     46     104    119    Lorg/json/JSONException;
        //  81     92     119    131    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0092:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void handleSubmitPaymentStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startSubmitPaymentSession(value, value2);
    }
    
    private void handleSurveyEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        final String stringExtra3 = intent.getStringExtra("surveyType");
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endSurveySession(value, instance, stringExtra3);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handleSurveyQuestionEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        final String stringExtra3 = intent.getStringExtra("question");
        final String stringExtra4 = intent.getStringExtra("response");
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endSurveyQuestionSession(value, instance, stringExtra3, stringExtra4);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handleSurveyQuestionStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startSurveyQuestionSession(value, value2);
    }
    
    private void handleSurveyStart(final Intent intent) {
        final IClientLogging$ModalView clientLogging$ModalView = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2 = clientLogging$ModalView;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        this.startSurveySession(value, value2);
    }
    
    private void handleUpgradeStreamsEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        final UserActionLogging$Streams find = UserActionLogging$Streams.find(intent.getStringExtra("streams"));
        while (true) {
            try {
                final UIError instance = UIError.createInstance(stringExtra2);
                if (StringUtils.isNotEmpty(stringExtra)) {
                    value = IClientLogging$CompletionReason.valueOf(stringExtra);
                }
                this.endUpgradeStreamsSession(value, instance, find);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed JSON", (Throwable)ex);
                final UIError instance = null;
                continue;
            }
            break;
        }
    }
    
    private void handleUpgradeStreamsStart(final Intent intent) {
        final UserActionLogging$Streams userActionLogging$Streams = null;
        final String stringExtra = intent.getStringExtra("cmd");
        UserActionLogging$CommandName value;
        if (!StringUtils.isEmpty(stringExtra)) {
            value = UserActionLogging$CommandName.valueOf(stringExtra);
        }
        else {
            value = null;
        }
        final String stringExtra2 = intent.getStringExtra("view");
        IClientLogging$ModalView value2;
        if (StringUtils.isNotEmpty(stringExtra2)) {
            value2 = IClientLogging$ModalView.valueOf(stringExtra2);
        }
        else {
            value2 = null;
        }
        final String stringExtra3 = intent.getStringExtra("streams");
        UserActionLogging$Streams find = userActionLogging$Streams;
        if (StringUtils.isNotEmpty(stringExtra3)) {
            find = UserActionLogging$Streams.find(stringExtra3);
        }
        this.startUpgradeStreamsSession(value, value2, find);
    }
    
    private void populateEvent(final Event event, final DataContext dataContext, final IClientLogging$ModalView modalView) {
        if (event == null) {
            return;
        }
        event.setDataContext(dataContext);
        event.setModalView(modalView);
    }
    
    private String validateTermForPrivacy(final String s) {
        if (StringUtils.isEmpty(s)) {
            Log.w("nf_log", "Query is empty, skip privacy check");
        }
        else {
            if (this.mUserAgent == null) {
                Log.e("nf_log", "User agent is NULL, this should NOT happen, we can not check for privacy violation!");
                return null;
            }
            if (this.mUserAgent.isPotentialPrivacyViolationFoundForLogging(s)) {
                Log.w("nf_log", "Security violation found, do NOT log query");
                return "PRIVACY_VIOLATION";
            }
        }
        return s;
    }
    
    @Override
    public void endAcknowledgeSignupSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mAcknowledgeSignup == null) {
            return;
        }
        Log.d("nf_log", "User session ended");
        final AcknowledgeSignupEndedEvent endedEvent = this.mAcknowledgeSignup.createEndedEvent(clientLogging$CompletionReason, uiError, clientLogging$ModalView);
        if (endedEvent == null) {
            Log.d("nf_log", "AcknowledgeSignup session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, clientLogging$ModalView);
        this.mEventHandler.removeSession(this.mAcknowledgeSignup);
        Log.d("nf_log", "AcknowledgeSignup session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mAcknowledgeSignup = null;
        Log.d("nf_log", "AcknowledgeSignup session end event posted.");
    }
    
    @Override
    public void endAddProfileSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError, final UserActionLogging$Profile userActionLogging$Profile) {
        if (this.mAddProfileSession == null) {
            return;
        }
        Log.d("nf_log", "Add profile session ended");
        final AddProfileEndedEvent endedEvent = this.mAddProfileSession.createEndedEvent(clientLogging$CompletionReason, uiError, clientLogging$ModalView, userActionLogging$Profile);
        if (endedEvent == null) {
            Log.d("nf_log", "Add profile session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mAddProfileSession.getView());
        this.mEventHandler.removeSession(this.mAddProfileSession);
        Log.d("nf_log", "Add profile session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mAddProfileSession = null;
        Log.d("nf_log", "Add profile session end event posted.");
    }
    
    @Override
    public void endAddToPlaylistSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final int n) {
        if (this.mAddToPlaylistSession == null) {
            return;
        }
        Log.d("nf_log", "AddToPlaylist session ended");
        final AddToPlaylistEndedEvent endedEvent = this.mAddToPlaylistSession.createEndedEvent(clientLogging$CompletionReason, uiError, n);
        if (endedEvent == null) {
            Log.d("nf_log", "AddToPlaylist session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mAddToPlaylistSession.getView());
        this.mEventHandler.removeSession(this.mAddToPlaylistSession);
        Log.d("nf_log", "AddToPlaylist session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mAddToPlaylistSession = null;
        Log.d("nf_log", "AddToPlaylist session end event posted.");
    }
    
    @Override
    public void endAllActiveSessions() {
        synchronized (this) {
            this.endAcknowledgeSignupSession(IClientLogging$CompletionReason.canceled, null, IClientLogging$ModalView.logout);
            this.endAddProfileSession(IClientLogging$CompletionReason.canceled, IClientLogging$ModalView.logout, null, null);
            this.endAddToPlaylistSession(IClientLogging$CompletionReason.canceled, null, 0);
            this.endDeleteProfileSession(IClientLogging$CompletionReason.canceled, IClientLogging$ModalView.logout, null);
            this.endEditProfileSession(IClientLogging$CompletionReason.canceled, IClientLogging$ModalView.logout, null, null);
            this.endLoginSession(IClientLogging$CompletionReason.canceled, null);
            this.endNavigationSession(IClientLogging$ModalView.logout, IClientLogging$CompletionReason.canceled, null);
            this.endNewLolomoSession(IClientLogging$CompletionReason.canceled, IClientLogging$ModalView.logout, null, null, null, System.currentTimeMillis(), null, null);
            this.endRateTitleSession(IClientLogging$CompletionReason.canceled, null, 0, 0);
            this.endRegisterSession(IClientLogging$CompletionReason.canceled, null);
            this.endRemoveFromPlaylistSession(IClientLogging$CompletionReason.canceled, null);
            this.endSelectProfileSession(IClientLogging$CompletionReason.canceled, IClientLogging$ModalView.logout, null);
            this.endStartPlaySession(IClientLogging$CompletionReason.canceled, null, 0, null, null);
            this.endSubmitPaymentSession(IClientLogging$CompletionReason.canceled, null, false, null, null);
            this.endUpgradeStreamsSession(IClientLogging$CompletionReason.canceled, null, null);
            this.endShareSheetOpenSession(IClientLogging$CompletionReason.canceled, IClientLogging$ModalView.logout, null);
            this.endShareSheetSession(IClientLogging$CompletionReason.canceled, IClientLogging$ModalView.logout, null);
            this.endPostPlaySession(IClientLogging$CompletionReason.canceled, IClientLogging$ModalView.logout, null, false, false, null, null, 0);
            this.endPreAppWidgetActionSession(IClientLogging$CompletionReason.canceled, null);
            this.endSurveySession(IClientLogging$CompletionReason.canceled, null, null);
            this.endSurveyQuestionSession(IClientLogging$CompletionReason.canceled, null, "", null);
            this.endPrefetchLolomoJobSession(IClientLogging$CompletionReason.canceled, null);
            this.endDeserializeLolomoSession(IClientLogging$CompletionReason.canceled, null, -1L);
            this.endSerializeLolomoSession(IClientLogging$CompletionReason.canceled, null, -1L);
            final HashSet<Long> set = new HashSet<Long>(this.mSearchSessions.size());
            set.addAll((Collection<?>)this.mSearchSessions.keySet());
            final Iterator<Object> iterator = set.iterator();
            while (iterator.hasNext()) {
                this.endSearchSession(iterator.next(), IClientLogging$CompletionReason.canceled, null);
            }
        }
    }
    // monitorexit(this)
    
    @Override
    public void endChangeValueSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String s) {
        if (this.mChangeValueSession == null) {
            return;
        }
        Log.d("nf_log", "changeValue session ended");
        final ChangeValueEndedEvent endedEvent = this.mChangeValueSession.createEndedEvent(clientLogging$CompletionReason, uiError, s);
        if (endedEvent == null) {
            Log.d("nf_log", "ChangeValue session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mChangeValueSession.getView());
        this.mEventHandler.removeSession(this.mChangeValueSession);
        Log.d("nf_log", "changeValue session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mChangeValueSession = null;
        Log.d("nf_log", "changeValue session end event posted.");
    }
    
    @Override
    public void endDeleteProfileSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError) {
        if (this.mDeleteProfileSession == null) {
            return;
        }
        Log.d("nf_log", "Delete profile session ended");
        final DeleteProfileEndedEvent endedEvent = this.mDeleteProfileSession.createEndedEvent(clientLogging$CompletionReason, uiError, clientLogging$ModalView);
        if (endedEvent == null) {
            Log.d("nf_log", "Delete profile session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mDeleteProfileSession.getView());
        this.mEventHandler.removeSession(this.mDeleteProfileSession);
        Log.d("nf_log", "Delete profile session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mDeleteProfileSession = null;
        Log.d("nf_log", "Delete profile session end event posted.");
    }
    
    @Override
    public void endDeserializeLolomoSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final long n) {
        if (this.mDeserializeLolomoSession == null) {
            return;
        }
        Log.d("nf_log", "DeserializeLolomoSession session ended");
        final DeserializeLolomoEndedEvent endedEvent = this.mDeserializeLolomoSession.createEndedEvent(clientLogging$CompletionReason, uiError, n);
        if (endedEvent == null) {
            Log.d("nf_log", "DeserializeLolomoSession still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mDeserializeLolomoSession.getView());
        this.mEventHandler.removeSession(this.mDeserializeLolomoSession);
        Log.d("nf_log", "DeserializeLolomoSession end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mDeserializeLolomoSession = null;
        Log.d("nf_log", "DeserializeLolomoSession session end event posted.");
    }
    
    @Override
    public void endEditProfileSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError, final UserActionLogging$Profile userActionLogging$Profile) {
        if (this.mEditProfileSession == null) {
            return;
        }
        Log.d("nf_log", "Edit profile session ended");
        final EditProfileEndedEvent endedEvent = this.mEditProfileSession.createEndedEvent(clientLogging$CompletionReason, uiError, clientLogging$ModalView, userActionLogging$Profile);
        if (endedEvent == null) {
            Log.d("nf_log", "Edit profile session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mEditProfileSession.getView());
        this.mEventHandler.removeSession(this.mEditProfileSession);
        Log.d("nf_log", "Edit profile session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mEditProfileSession = null;
        Log.d("nf_log", "Edit profile session end event posted.");
        Log.d("nf_log", "Edit profile session end done.");
    }
    
    @Override
    public void endLoginSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        if (this.mLoginSession == null) {
            return;
        }
        Log.d("nf_log", "Login session ended");
        final LoginEndedEvent endedEvent = this.mLoginSession.createEndedEvent(clientLogging$CompletionReason, uiError);
        if (endedEvent == null) {
            Log.d("nf_log", "Login session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mLoginSession.getView());
        this.mEventHandler.removeSession(this.mLoginSession);
        Log.d("nf_log", "Login session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mLoginSession = null;
        Log.d("nf_log", "Login session end event posted.");
    }
    
    @Override
    public void endNavigationSession(final IClientLogging$ModalView clientLogging$ModalView, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        final NavigationSession mNavigationSession = this.mNavigationSession;
        if (mNavigationSession == null) {
            return;
        }
        if (UserActionLoggingImpl.excludedViewsSet.contains(this.mNavigationSession.getView())) {
            if (mNavigationSession.getStartingView() == null) {
                mNavigationSession.setStartingView(clientLogging$ModalView);
                return;
            }
            if (this.mNavigationSession.getView() != clientLogging$ModalView) {
                mNavigationSession.setStartingView(clientLogging$ModalView);
            }
        }
        Log.d("nf_log", "Navigation session ended");
        final NavigationEndedEvent endedEvent = mNavigationSession.createEndedEvent(clientLogging$ModalView, clientLogging$CompletionReason, uiError);
        if (endedEvent == null) {
            Log.d("nf_log", "We stayed in same view, cancel session.");
        }
        else {
            Log.d("nf_log", "Navigation session end event posting...");
            this.populateEvent(endedEvent, this.mDataContext, this.mNavigationSession.getView());
            this.mEventHandler.post(endedEvent);
            Log.d("nf_log", "Navigation session end event posted.");
        }
        this.mEventHandler.removeSession(mNavigationSession);
        this.mNavigationSession = null;
    }
    
    @Override
    public void endNewLolomoSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError, final String s, final String s2, final long n, final String s3, final String s4) {
        if (this.mNewLolomoSession == null) {
            return;
        }
        Log.d("nf_log", "NewLolomoSession ended");
        final NewLolomoEndedEvent endedEvent = this.mNewLolomoSession.createEndedEvent(clientLogging$CompletionReason, uiError, clientLogging$ModalView, s, s2, n, s3, s4);
        if (endedEvent == null) {
            Log.d("nf_log", "NewLolomoSession still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, clientLogging$ModalView);
        this.mEventHandler.removeSession(this.mNewLolomoSession);
        Log.d("nf_log", "NewLolomoSession end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mNewLolomoSession = null;
        Log.d("nf_log", "NewLolomoSession end event posted.");
    }
    
    @Override
    public void endPostPlaySession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError, final boolean b, final boolean b2, final Integer n, final Integer n2, final int n3) {
        if (this.mPostPlaySession == null) {
            return;
        }
        Log.d("nf_log", "PostPlaySession session ended");
        final PostPlayEndedEvent endedEvent = this.mPostPlaySession.createEndedEvent(clientLogging$CompletionReason, clientLogging$ModalView, uiError, b, b2, n, n2, n3);
        if (endedEvent == null) {
            Log.d("nf_log", "PostPlaySession still waits on session id, do not post at this time.");
            return;
        }
        this.mEventHandler.removeSession(this.mPostPlaySession);
        Log.d("nf_log", "PostPlaySession end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mPostPlaySession = null;
        Log.d("nf_log", "PostPlaySession end event posted.");
    }
    
    @Override
    public void endPreAppWidgetActionSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        if (this.mPreAppWidgetActionSession == null) {
            return;
        }
        Log.d("nf_log", "PreAppWidgetActionSession session ended");
        final PreAppWidgetActionEndedEvent endedEvent = this.mPreAppWidgetActionSession.createEndedEvent(clientLogging$CompletionReason, uiError);
        if (endedEvent == null) {
            Log.d("nf_log", "PreAppWidgetActionSession still waits on session id, do not post at this time.");
            return;
        }
        this.mEventHandler.removeSession(this.mPreAppWidgetActionSession);
        Log.d("nf_log", "PreAppWidgetActionSession end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mPreAppWidgetActionSession = null;
        Log.d("nf_log", "PreAppWidgetActionSession end event posted.");
    }
    
    @Override
    public void endPrefetchLolomoJobSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        if (this.mPrefetchLolomoJobSession == null) {
            return;
        }
        Log.d("nf_log", "PrefetchLolomoJobSession session ended");
        final PrefetchLolomoJobEndedEvent endedEvent = this.mPrefetchLolomoJobSession.createEndedEvent(clientLogging$CompletionReason, uiError);
        if (endedEvent == null) {
            Log.d("nf_log", "PrefetchLolomoJobSession still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mPrefetchLolomoJobSession.getView());
        this.mEventHandler.removeSession(this.mPrefetchLolomoJobSession);
        Log.d("nf_log", "PrefetchLolomoJobSession end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mPrefetchLolomoJobSession = null;
        Log.d("nf_log", "PrefetchLolomoJob session end event posted.");
    }
    
    @Override
    public void endRateTitleSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final Integer n, final int n2) {
        if (this.mRateTitleSession == null) {
            return;
        }
        Log.d("nf_log", "RateTitle  session ended");
        final RateTitleEndedEvent endedEvent = this.mRateTitleSession.createEndedEvent(clientLogging$CompletionReason, uiError, n, n2);
        if (endedEvent == null) {
            Log.d("nf_log", "RateTitle  session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mRateTitleSession.getView());
        this.mEventHandler.removeSession(this.mRateTitleSession);
        Log.d("nf_log", "RateTitle session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mRateTitleSession = null;
        Log.d("nf_log", "RateTitle session end event posted.");
    }
    
    @Override
    public void endRegisterSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        if (this.mRegisterSession == null) {
            return;
        }
        Log.d("nf_log", "Register session ended");
        final RegisterEndedEvent endedEvent = this.mRegisterSession.createEndedEvent(clientLogging$CompletionReason, uiError);
        if (endedEvent == null) {
            Log.d("nf_log", "Register session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mRegisterSession.getView());
        this.mEventHandler.removeSession(this.mRegisterSession);
        Log.d("nf_log", "Register session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mRegisterSession = null;
        Log.d("nf_log", "Register session end event posted.");
    }
    
    @Override
    public void endRemoveFromPlaylistSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        if (this.mRemoveFromPlaylistSession == null) {
            return;
        }
        Log.d("nf_log", "RemoveFromPlaylist session ended");
        final RemoveFromPlaylistEndedEvent endedEvent = this.mRemoveFromPlaylistSession.createEndedEvent(clientLogging$CompletionReason, uiError);
        if (endedEvent == null) {
            Log.d("nf_log", "RemoveFromPlaylist session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mRemoveFromPlaylistSession.getView());
        this.mEventHandler.removeSession(this.mRemoveFromPlaylistSession);
        Log.d("nf_log", "RemoveFromPlaylist session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mRemoveFromPlaylistSession = null;
        Log.d("nf_log", "RemoveFromPlaylist session end event posted.");
    }
    
    @Override
    public void endSearchSession(final long n, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        while (true) {
            final SearchSession searchSession;
            Label_0067: {
                synchronized (this) {
                    searchSession = this.mSearchSessions.get(n);
                    if (searchSession != null) {
                        Log.d("nf_log", "Search session ended");
                        if (searchSession.createEndedEvent(clientLogging$CompletionReason, uiError) != null) {
                            break Label_0067;
                        }
                        Log.d("nf_log", "Search session still waits on session id, do not post at this time.");
                    }
                    return;
                }
            }
            final Event event;
            this.populateEvent(event, this.mDataContext, searchSession.getView());
            this.mEventHandler.removeSession(searchSession);
            Log.d("nf_log", "Search session end event posting...");
            this.mEventHandler.post(event);
            Log.d("nf_log", "Search session end event posted.");
        }
    }
    
    @Override
    public void endSelectProfileSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError) {
        if (this.mSelectProfileSession == null) {
            return;
        }
        Log.d("nf_log", "Select profile session ended");
        final SelectProfileEndedEvent endedEvent = this.mSelectProfileSession.createEndedEvent(clientLogging$CompletionReason, uiError, clientLogging$ModalView);
        if (endedEvent == null) {
            Log.d("nf_log", "Select profile session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mSelectProfileSession.getView());
        this.mEventHandler.removeSession(this.mSelectProfileSession);
        Log.d("nf_log", "Select profile session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mSelectProfileSession = null;
        Log.d("nf_log", "Select profile session end event posted.");
    }
    
    @Override
    public void endSerializeLolomoSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final long n) {
        if (this.mSerializeLolomoSession == null) {
            return;
        }
        Log.d("nf_log", "SerializeLolomoSession session ended");
        final SerializeLolomoEndedEvent endedEvent = this.mSerializeLolomoSession.createEndedEvent(clientLogging$CompletionReason, uiError, n);
        if (endedEvent == null) {
            Log.d("nf_log", "SerializeLolomoSession still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mSerializeLolomoSession.getView());
        this.mEventHandler.removeSession(this.mSerializeLolomoSession);
        Log.d("nf_log", "SerializeLolomoSession end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mSerializeLolomoSession = null;
        Log.d("nf_log", "SerializeLolomoSession session end event posted.");
    }
    
    @Override
    public void endShareSheetOpenSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError) {
        if (this.mShareSheetOpenSession == null) {
            return;
        }
        Log.d("nf_log", "ShareSheetOpenSession ended");
        final ShareSheetOpenEndedEvent endedEvent = this.mShareSheetOpenSession.createEndedEvent(clientLogging$CompletionReason, uiError, clientLogging$ModalView);
        this.populateEvent(endedEvent, this.mDataContext, clientLogging$ModalView);
        this.mEventHandler.removeSession(this.mShareSheetOpenSession);
        Log.d("nf_log", "ShareSheetOpenSession end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mShareSheetOpenSession = null;
        Log.d("nf_log", "ShareSheetOpenSession end event posted.");
    }
    
    @Override
    public void endShareSheetSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError) {
        if (this.mShareSheetSession == null) {
            return;
        }
        Log.d("nf_log", "ShareSheetSession ended and posted to executor");
        final ShareSheetEndedEvent endedEvent = this.mShareSheetSession.createEndedEvent(clientLogging$CompletionReason, uiError, clientLogging$ModalView);
        this.populateEvent(endedEvent, this.mDataContext, clientLogging$ModalView);
        this.mEventHandler.removeSession(this.mShareSheetSession);
        Log.d("nf_log", "ShareSheetSession end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mShareSheetSession = null;
        Log.d("nf_log", "ShareSheetSession end event posted.");
    }
    
    @Override
    public void endStartPlaySession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final Integer n, final PlayerType playerType, final PlayLocationType playLocationType) {
        if (this.mStartPlaySession == null) {
            return;
        }
        Log.d("nf_log", "StartPlay session ended");
        final StartPlayEndedEvent endedEvent = this.mStartPlaySession.createEndedEvent(clientLogging$CompletionReason, uiError, n, playerType, playLocationType);
        if (endedEvent == null) {
            Log.d("nf_log", "StartPlay session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mStartPlaySession.getView());
        this.mEventHandler.removeSession(this.mStartPlaySession);
        Log.d("nf_log", "StartPlay session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mStartPlaySession = null;
        Log.d("nf_log", "StartPlay session end event posted.");
    }
    
    @Override
    public void endSubmitPaymentSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final boolean b, final UserActionLogging$PaymentType userActionLogging$PaymentType, final JSONObject jsonObject) {
        if (this.mSubmitPaymentSession == null) {
            return;
        }
        Log.d("nf_log", "SubmitPayment session ended");
        final SubmitPaymentEndedEvent endedEvent = this.mSubmitPaymentSession.createEndedEvent(clientLogging$CompletionReason, uiError, b, userActionLogging$PaymentType, jsonObject);
        if (endedEvent == null) {
            Log.d("nf_log", "SubmitPayment session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mSubmitPaymentSession.getView());
        this.mEventHandler.removeSession(this.mSubmitPaymentSession);
        Log.d("nf_log", "SubmitPayment session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mSubmitPaymentSession = null;
        Log.d("nf_log", "SubmitPayment session end event posted.");
    }
    
    @Override
    public void endSurveyQuestionSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String s, final String s2) {
        if (this.mSurveyQuestionSession == null) {
            return;
        }
        Log.d("nf_log", "SurveyQuestionSession session ended");
        final SurveyQuestionEndedEvent endedEvent = this.mSurveyQuestionSession.createEndedEvent(clientLogging$CompletionReason, uiError, s, s2);
        if (endedEvent == null) {
            Log.d("nf_log", "SurveyQuestionSession session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mSurveyQuestionSession.getView());
        this.mEventHandler.removeSession(this.mSurveyQuestionSession);
        Log.d("nf_log", "SurveyQuestionSession session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mSurveyQuestionSession = null;
        Log.d("nf_log", "SurveyQuestionSession session end event posted.");
    }
    
    @Override
    public void endSurveySession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final String s) {
        if (this.mSurveySession == null) {
            return;
        }
        Log.d("nf_log", "SurveySession session ended");
        final SurveyEndedEvent endedEvent = this.mSurveySession.createEndedEvent(clientLogging$CompletionReason, uiError, s);
        if (endedEvent == null) {
            Log.d("nf_log", "SurveySession session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mSurveySession.getView());
        this.mEventHandler.removeSession(this.mSurveySession);
        Log.d("nf_log", "SurveySession session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mSurveySession = null;
        Log.d("nf_log", "SurveySession session end event posted.");
    }
    
    @Override
    public void endUpgradeStreamsSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final UserActionLogging$Streams userActionLogging$Streams) {
        if (this.mUpgradeStreamsSession == null) {
            return;
        }
        Log.d("nf_log", "UpgradeStreams session ended");
        final UpgradeStreamsEndedEvent endedEvent = this.mUpgradeStreamsSession.createEndedEvent(clientLogging$CompletionReason, uiError, userActionLogging$Streams);
        if (endedEvent == null) {
            Log.d("nf_log", "User session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, this.mDataContext, this.mUpgradeStreamsSession.getView());
        this.mEventHandler.removeSession(this.mUpgradeStreamsSession);
        Log.d("nf_log", "UpgradeStreams session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mUpgradeStreamsSession = null;
        Log.d("nf_log", "UpgradeStreams session end event posted.");
    }
    
    @Override
    public boolean handleIntent(final Intent intent, final boolean b) {
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_ADD_TO_PLAYLIST_START".equals(action)) {
            Log.d("nf_log", "ADD_TO_PLAYLIST_START");
            this.handleAddToPlaylistStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_ADD_TO_PLAYLIST_ENDED".equals(action)) {
            Log.d("nf_log", "ADD_TO_PLAYLIST_ENDED");
            this.handleAddToPlaylistEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SURVEY_START".equals(action)) {
            Log.d("nf_log", "SURVEY_START");
            this.handleSurveyStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SURVEY_ENDED".equals(action)) {
            Log.d("nf_log", "SURVEY_ENDED");
            this.handleSurveyEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SURVEY_QUESTION_START".equals(action)) {
            Log.d("nf_log", "SURVEY_QUESTION_START");
            this.handleSurveyQuestionStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SURVEY_QUESTION_ENDED".equals(action)) {
            Log.d("nf_log", "SURVEY_QUESTION_ENDED");
            this.handleSurveyQuestionEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_CHANGE_VALUE_START".equals(action)) {
            Log.d("nf_log", "CHANGE_VALUE_START");
            this.handleChangeValueStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_CHANGE_VALUE_ENDED".equals(action)) {
            Log.d("nf_log", "CHANGE_VALUE_ENDED");
            this.handleChangeValueEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_LOGIN_START".equals(action)) {
            Log.d("nf_log", "LOGIN_START");
            this.handleLoginStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_LOGIN_ENDED".equals(action)) {
            Log.d("nf_log", "LOGIN_ENDED");
            this.handleLoginEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SIGNUP_START".equals(action)) {
            Log.d("nf_log", "SIGNUP_START");
            this.handleAcknowledgeSignupStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SIGNUP_ENDED".equals(action)) {
            Log.d("nf_log", "SIGNUP_ENDED");
            this.handleAcknowledgeSignupEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_NAVIGATION_START".equals(action)) {
            Log.d("nf_log", "NAVIGATION_START");
            this.handleNavigationStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_NAVIGATION_ENDED".equals(action)) {
            Log.d("nf_log", "NAVIGATION_ENDED");
            this.handleNavigationEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_RATE_TITLE_START".equals(action)) {
            Log.d("nf_log", "RATE_TITLE_START");
            this.handleRateTitleStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_RATE_TITLE_ENDED".equals(action)) {
            Log.d("nf_log", "RATE_TITLE_ENDED");
            this.handleRateTitleEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_REGISTER_START".equals(action)) {
            Log.d("nf_log", "REGISTER_START");
            this.handleRegisterStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_REGISTER_ENDED".equals(action)) {
            Log.d("nf_log", "REGISTER_ENDED");
            this.handleRegisterEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_REMOVE_FROM_PLAYLIST_START".equals(action)) {
            Log.d("nf_log", "REMOVE_FROM_PLAYLIST_START");
            this.handleRemoveFromPlaylistStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_REMOVE_FROM_PLAYLIST_ENDED".equals(action)) {
            Log.d("nf_log", "REMOVE_FROM_PLAYLIST_ENDED");
            this.handleRemoveFromPlaylistEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SEARCH_START".equals(action)) {
            Log.d("nf_log", "SEARCH_START");
            this.handleSearchStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SEARCH_ENDED".equals(action)) {
            Log.d("nf_log", "SEARCH_ENDED");
            this.handleSearchEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_START_PLAY_START".equals(action)) {
            Log.d("nf_log", "START_PLAY_START");
            this.handleStartPlayStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_START_PLAY_ENDED".equals(action)) {
            Log.d("nf_log", "START_PLAY_ENDED");
            this.handleStartPlayEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SUBMIT_PAYMENT_START".equals(action)) {
            Log.d("nf_log", "SUBMIT_PAYMENT_START");
            this.handleSubmitPaymentStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SUBMIT_PAYMENT_ENDED".equals(action)) {
            Log.d("nf_log", "SUBMIT_PAYMENT_ENDED");
            this.handleSubmitPaymentEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_UPGRADE_STREAMS_START".equals(action)) {
            Log.d("nf_log", "UPGRADE_STREAMS_START");
            this.handleUpgradeStreamsStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_UPGRADE_STREAMS_ENDED".equals(action)) {
            Log.d("nf_log", "UPGRADE_STREAMS_ENDED");
            this.handleUpgradeStreamsEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SELECT_PROFILE_START".equals(action)) {
            Log.d("nf_log", "SELECT_PROFILE_START");
            this.handleSelectProfileStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SELECT_PROFILE_ENDED".equals(action)) {
            Log.d("nf_log", "SELECT_PROFILE_ENDED");
            this.handleSelectProfileEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_ADD_PROFILE_START".equals(action)) {
            Log.d("nf_log", "ADD_PROFILE_START");
            this.handleAddProfileStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_ADD_PROFILE_ENDED".equals(action)) {
            Log.d("nf_log", "ADD_PROFILE_ENDED");
            this.handleAddProfileEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_EDIT_PROFILE_START".equals(action)) {
            Log.d("nf_log", "EDIT_PROFILE_START");
            this.handleEditProfileStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_EDIT_PROFILE_ENDED".equals(action)) {
            Log.d("nf_log", "EDIT_PROFILE_ENDED");
            this.handleEditProfileEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_DELETE_PROFILE_START".equals(action)) {
            Log.d("nf_log", "DELETE_PROFILE_START");
            this.handleDeleteProfileStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_DELETE_PROFILE_ENDED".equals(action)) {
            Log.d("nf_log", "DELETE_PROFILE_ENDED");
            this.handleDeleteProfileEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SHARE_SHEET_START".equals(action)) {
            Log.d("nf_log", "SHARE_SHEET_START");
            this.handleShareSheetStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SHARE_SHEET_ENDED".equals(action)) {
            Log.d("nf_log", "SHARE_SHEET_ENDED");
            this.handleShareSheetEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SHARE_SHEET_OPEN_START".equals(action)) {
            Log.d("nf_log", "SHARE_SHEET_OPEN_START");
            this.handleShareSheetOpenStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SHARE_SHEET_OPEN_ENDED".equals(action)) {
            Log.d("nf_log", "SHARE_SHEET_OPEN_ENDED");
            this.handleShareSheetOpenEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_NEW_LOLOMO_START".equals(action)) {
            Log.d("nf_log", "NEW_LOLOMO_START");
            this.handleNewLolomoStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_NEW_LOLOMO_ENDED".equals(action)) {
            Log.d("nf_log", "NEW_LOLOMO_ENDED");
            this.handleNewLolomoEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.PREAPP_WIDGET_ACTION_START".equals(action)) {
            Log.d("nf_log", "PREAPP_WIDGET_ACTION_START");
            this.handlePreAppWidgetActionStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.PREAPP_WIDGET_ACTION_ENDED".equals(action)) {
            Log.d("nf_log", "PREAPP_WIDGET_ACTION_ENDED");
            this.handlePreAppWidgetActionEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_POSTPLAY_START".equals(action)) {
            this.handlePostPlayStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_POSTPLAY_ENDED".equals(action)) {
            this.handlePostPlayEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_PREFETCH_LOLOMO_JOB_START".equals(action)) {
            Log.d("nf_log", "PREFETCH_LOLOMO_JOB_STARTED");
            this.handlePrefetchLolomoJobStarted(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_PREFETCH_LOLOMO_JOB_ENDED".equals(action)) {
            Log.d("nf_log", "PREFETCH_LOLOMO_JOB_ENDED");
            this.handlePrefetchLolomoJobEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_DESERIALIZE_LOLOMO_START".equals(action)) {
            Log.d("nf_log", "DESERIALIZE_LOLOMO_STARTED");
            this.handleDeserializeLolomoStarted(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_DESERIALIZE_LOLOMO_ENDED".equals(action)) {
            Log.d("nf_log", "DESERIALIZE_LOLOMO_ENDED");
            this.handleDeserializeLolomoEnded(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SERIALIZE_LOLOMO_START".equals(action)) {
            Log.d("nf_log", "SERIALIZE_LOLOMO_STARTED");
            this.handleSerializeLolomoStarted(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SERIALIZE_LOLOMO_ENDED".equals(action)) {
            Log.d("nf_log", "SERIALIZE_LOLOMO_ENDED");
            this.handleSerializeLolomoEnded(intent);
            return true;
        }
        if (Log.isLoggable()) {
            Log.d("nf_log", "We do not support action " + action);
        }
        return false;
    }
    
    @Override
    public void setDataContext(final DataContext mDataContext) {
        this.mDataContext = mDataContext;
    }
    
    @Override
    public void startAcknowledgeSignupSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mAcknowledgeSignup != null) {
            Log.e("nf_log", "AcknowledgeSignup session already started!");
            return;
        }
        Log.d("nf_log", "AcknowledgeSignup session starting...");
        final AcknowledgeSignupSession mAcknowledgeSignup = new AcknowledgeSignupSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mAcknowledgeSignup);
        this.mAcknowledgeSignup = mAcknowledgeSignup;
        Log.d("nf_log", "AcknowledgeSignup session start done.");
    }
    
    @Override
    public void startAddProfileSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mAddProfileSession != null) {
            Log.e("nf_log", "Add profile session already started!");
            return;
        }
        Log.d("nf_log", "Add profile session starting...");
        final AddProfileSession mAddProfileSession = new AddProfileSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mAddProfileSession);
        this.mAddProfileSession = mAddProfileSession;
        Log.d("nf_log", "Add profile session start done.");
    }
    
    @Override
    public void startAddToPlaylistSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mAddToPlaylistSession != null) {
            Log.e("nf_log", "AddToPlaylist session already started!");
            return;
        }
        Log.d("nf_log", "AddToPlaylist session starting...");
        final AddToPlaylistSession mAddToPlaylistSession = new AddToPlaylistSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mAddToPlaylistSession);
        this.mAddToPlaylistSession = mAddToPlaylistSession;
        Log.d("nf_log", "AddToPlaylist session start done.");
    }
    
    @Override
    public void startChangeValueSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mChangeValueSession != null) {
            Log.e("nf_log", "changeValue session already started!");
            return;
        }
        Log.d("nf_log", "changeValue session starting...");
        final ChangeValueSession mChangeValueSession = new ChangeValueSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mChangeValueSession);
        this.mChangeValueSession = mChangeValueSession;
        Log.d("nf_log", "changeValue session start done.");
    }
    
    @Override
    public void startDeleteProfileSession(final String s, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mDeleteProfileSession != null) {
            Log.e("nf_log", "Delete profile session already started!");
            return;
        }
        Log.d("nf_log", "Delete profile session starting...");
        final DeleteProfileSession mDeleteProfileSession = new DeleteProfileSession(s, userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mDeleteProfileSession);
        this.mDeleteProfileSession = mDeleteProfileSession;
        Log.d("nf_log", "Delete profile session start done.");
    }
    
    @Override
    public void startDeserializeLolomoSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mDeserializeLolomoSession != null) {
            Log.e("nf_log", "DeserializeLolomoSession already started!");
            return;
        }
        Log.d("nf_log", "DeserializeLolomoSession starting...");
        final DeserializeLolomoSession mDeserializeLolomoSession = new DeserializeLolomoSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mDeserializeLolomoSession);
        this.mDeserializeLolomoSession = mDeserializeLolomoSession;
        Log.d("nf_log", "PrefetchLolomoJobSession start done.");
    }
    
    @Override
    public void startEditProfileSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mEditProfileSession != null) {
            Log.e("nf_log", "Edit profile session already started!");
            return;
        }
        Log.d("nf_log", "Edit profile session starting...");
        final EditProfileSession mEditProfileSession = new EditProfileSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mEditProfileSession);
        this.mEditProfileSession = mEditProfileSession;
        Log.d("nf_log", "Edit profile session start done.");
    }
    
    @Override
    public void startLoginSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mLoginSession != null) {
            Log.e("nf_log", "Login session already started!");
            return;
        }
        Log.d("nf_log", "Login session starting...");
        final LoginSession mLoginSession = new LoginSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mLoginSession);
        this.mLoginSession = mLoginSession;
        Log.d("nf_log", "Login session start done.");
    }
    
    @Override
    public void startNavigationSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mNavigationSession != null) {
            Log.d("nf_log", "Navigation session existed before, overwrite");
            this.mEventHandler.removeSession(this.mNavigationSession);
        }
        else {
            Log.d("nf_log", "Navigation session starting...");
        }
        final NavigationSession mNavigationSession = new NavigationSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mNavigationSession);
        this.mNavigationSession = mNavigationSession;
        Log.d("nf_log", "Navigation session start done.");
    }
    
    @Override
    public void startNewLolomoSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mNewLolomoSession != null) {
            Log.e("nf_log", "NewLolomoSession session already started!");
            return;
        }
        Log.d("nf_log", "NewLolomoSession session starting...");
        this.mNewLolomoSession = new NewLolomoSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(this.mNewLolomoSession);
        Log.d("nf_log", "NewLolomoSession session start done.");
    }
    
    @Override
    public void startPostPlaySession(final boolean b, final int n, final UserActionLogging$PostPlayExperience userActionLogging$PostPlayExperience) {
        if (this.mPostPlaySession != null) {
            Log.e("nf_log", "PostPlaySession already started!");
            return;
        }
        Log.d("nf_log", "PostPlaySession starting...");
        final PostPlaySession mPostPlaySession = new PostPlaySession(null, IClientLogging$ModalView.postPlay, b, n, userActionLogging$PostPlayExperience);
        this.mEventHandler.addSession(mPostPlaySession);
        this.mPostPlaySession = mPostPlaySession;
        Log.d("nf_log", "PostPlaySession start done.");
    }
    
    @Override
    public void startPreAppWidgetActionSession(final UserActionLogging$CommandName userActionLogging$CommandName, final String s, final String s2) {
        if (this.mPreAppWidgetActionSession != null) {
            Log.e("nf_log", "PreAppWidgetActionSession already started!");
            return;
        }
        Log.d("nf_log", "PreAppWidgetActionSessionstarting...");
        final PreAppWidgetActionSession mPreAppWidgetActionSession = new PreAppWidgetActionSession(userActionLogging$CommandName, s, s2);
        this.mEventHandler.addSession(mPreAppWidgetActionSession);
        this.mPreAppWidgetActionSession = mPreAppWidgetActionSession;
        Log.d("nf_log", "PreAppWidgetActionSession start done.");
    }
    
    @Override
    public void startPrefetchLolomoJobSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mPrefetchLolomoJobSession != null) {
            Log.e("nf_log", "PrefetchLolomoJobSession already started!");
            return;
        }
        Log.d("nf_log", "PrefetchLolomoJobSession starting...");
        final PrefetchLolomoJobSession mPrefetchLolomoJobSession = new PrefetchLolomoJobSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mPrefetchLolomoJobSession);
        this.mPrefetchLolomoJobSession = mPrefetchLolomoJobSession;
        Log.d("nf_log", "PrefetchLolomoJobSession start done.");
    }
    
    @Override
    public void startRateTitleSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mRateTitleSession != null) {
            Log.e("nf_log", "RateTitle session already started!");
            return;
        }
        Log.d("nf_log", "RateTitle session starting...");
        final RateTitleSession mRateTitleSession = new RateTitleSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mRateTitleSession);
        this.mRateTitleSession = mRateTitleSession;
        Log.d("nf_log", "RateTitle session start done.");
    }
    
    @Override
    public void startRegisterSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mRegisterSession != null) {
            Log.e("nf_log", "Register session already started!");
            return;
        }
        Log.d("nf_log", "Register session starting...");
        final RegisterSession mRegisterSession = new RegisterSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mRegisterSession);
        this.mRegisterSession = mRegisterSession;
        Log.d("nf_log", "Register session start done.");
    }
    
    @Override
    public void startRemoveFromPlaylistSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mRemoveFromPlaylistSession != null) {
            Log.e("nf_log", "RemoveFromPlaylist session already started!");
            return;
        }
        Log.d("nf_log", "RemoveFromPlaylist session starting...");
        final RemoveFromPlaylistSession mRemoveFromPlaylistSession = new RemoveFromPlaylistSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mRemoveFromPlaylistSession);
        this.mRemoveFromPlaylistSession = mRemoveFromPlaylistSession;
        Log.d("nf_log", "RemoveFromPlaylist session start done.");
    }
    
    @Override
    public void startSearchSession(final long n, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView, final String s) {
        synchronized (this) {
            Log.d("nf_log", "Search session starting...");
            final SearchSession searchSession = new SearchSession(n, userActionLogging$CommandName, clientLogging$ModalView, this.validateTermForPrivacy(s));
            this.mEventHandler.addSession(searchSession);
            this.mSearchSessions.put(n, searchSession);
            Log.d("nf_log", "Search session start done.");
        }
    }
    
    @Override
    public void startSelectProfileSession(final String s, final UserActionLogging$RememberProfile userActionLogging$RememberProfile, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mSelectProfileSession != null) {
            Log.e("nf_log", "Select profile session already started!");
            return;
        }
        Log.d("nf_log", "Select profile session starting...");
        final SelectProfileSession mSelectProfileSession = new SelectProfileSession(s, userActionLogging$RememberProfile, userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mSelectProfileSession);
        this.mSelectProfileSession = mSelectProfileSession;
        Log.d("nf_log", "Select profile session start done.");
    }
    
    @Override
    public void startSerializeLolomoSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mSerializeLolomoSession != null) {
            Log.e("nf_log", "SerializeLolomoSession already started!");
            return;
        }
        Log.d("nf_log", "SerializeLolomoSession starting...");
        final SerializeLolomoSession mSerializeLolomoSession = new SerializeLolomoSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mSerializeLolomoSession);
        this.mSerializeLolomoSession = mSerializeLolomoSession;
        Log.d("nf_log", "PrefetchLolomoJobSession start done.");
    }
    
    @Override
    public void startShareSheetOpenSession(final String s, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mShareSheetOpenSession != null) {
            Log.e("nf_log", "ShareSheetOpenSession session already started!");
            return;
        }
        Log.d("nf_log", "ShareSheetOpenSession session starting...");
        this.mShareSheetOpenSession = new ShareSheetOpenSession(s, userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(this.mShareSheetOpenSession);
        Log.d("nf_log", "ShareSheetOpenSession  session start done.");
    }
    
    @Override
    public void startShareSheetSession(final String s, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mShareSheetSession != null) {
            Log.e("nf_log", "ShareSheetSession session already started!");
            return;
        }
        Log.d("nf_log", "ShareSheetSession session starting...");
        this.mShareSheetSession = new ShareSheetSession(s, userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(this.mShareSheetSession);
        Log.d("nf_log", "ShareSheetSession  session start done.");
    }
    
    @Override
    public void startStartPlaySession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mStartPlaySession != null) {
            Log.e("nf_log", "StartPlay session already started!");
            return;
        }
        Log.d("nf_log", "StartPlay session starting...");
        final StartPlaySession mStartPlaySession = new StartPlaySession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mStartPlaySession);
        this.mStartPlaySession = mStartPlaySession;
        Log.d("nf_log", "StartPlay session start done.");
    }
    
    @Override
    public void startSubmitPaymentSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mSubmitPaymentSession != null) {
            Log.e("nf_log", "SubmitPayment session already started!");
            return;
        }
        Log.d("nf_log", "SubmitPayment session starting...");
        final SubmitPaymentSession mSubmitPaymentSession = new SubmitPaymentSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mSubmitPaymentSession);
        this.mSubmitPaymentSession = mSubmitPaymentSession;
        Log.d("nf_log", "SubmitPayment session start done.");
    }
    
    @Override
    public void startSurveyQuestionSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mSurveyQuestionSession != null) {
            Log.e("nf_log", "SurveyQuestionSession session already started!");
            return;
        }
        Log.d("nf_log", "SurveyQuestionSession session starting...");
        final SurveyQuestionSession mSurveyQuestionSession = new SurveyQuestionSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mSurveyQuestionSession);
        this.mSurveyQuestionSession = mSurveyQuestionSession;
        Log.d("nf_log", "SurveyQuestionSession session start done.");
    }
    
    @Override
    public void startSurveySession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mSurveySession != null) {
            Log.e("nf_log", "SurveySession session already started!");
            return;
        }
        Log.d("nf_log", "SurveySession session starting...");
        final SurveySession mSurveySession = new SurveySession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(mSurveySession);
        this.mSurveySession = mSurveySession;
        Log.d("nf_log", "SurveySession session start done.");
    }
    
    @Override
    public void startUpgradeStreamsSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView, final UserActionLogging$Streams userActionLogging$Streams) {
        if (this.mUpgradeStreamsSession != null) {
            Log.e("nf_log", "UpgradeStreams session already started!");
            return;
        }
        Log.d("nf_log", "UpgradeStreams session starting...");
        final UpgradeStreamsSession mUpgradeStreamsSession = new UpgradeStreamsSession(userActionLogging$CommandName, clientLogging$ModalView, userActionLogging$Streams);
        this.mEventHandler.addSession(mUpgradeStreamsSession);
        this.mUpgradeStreamsSession = mUpgradeStreamsSession;
        Log.d("nf_log", "UpgradeStreams session start done.");
    }
}
