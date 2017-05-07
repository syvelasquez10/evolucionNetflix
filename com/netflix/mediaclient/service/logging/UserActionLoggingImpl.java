// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import org.json.JSONObject;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PaymentType;
import com.netflix.mediaclient.service.logging.uiaction.model.SelectProfileEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.EditProfileEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.DeleteProfileEndedEvent;
import com.netflix.mediaclient.service.logging.uiaction.model.AddProfileEndedEvent;
import com.netflix.mediaclient.service.logging.client.LoggingSession;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.UserActionLogging$Streams;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.servicemgr.UserActionLogging$RememberProfile;
import com.netflix.mediaclient.servicemgr.UserActionLogging$Profile;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import java.io.Serializable;
import org.json.JSONException;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.Event;
import java.util.concurrent.ConcurrentHashMap;
import com.netflix.mediaclient.service.logging.uiaction.UpgradeStreamsSession;
import com.netflix.mediaclient.service.logging.uiaction.SubmitPaymentSession;
import com.netflix.mediaclient.service.logging.uiaction.StartPlaySession;
import com.netflix.mediaclient.service.logging.uiaction.SelectProfileSession;
import com.netflix.mediaclient.service.logging.uiaction.SearchSession;
import java.util.Map;
import com.netflix.mediaclient.service.logging.uiaction.SayThanksSession;
import com.netflix.mediaclient.service.logging.uiaction.RemoveFromPlaylistSession;
import com.netflix.mediaclient.service.logging.uiaction.RegisterSession;
import com.netflix.mediaclient.service.logging.uiaction.RateTitleSession;
import com.netflix.mediaclient.service.logging.uiaction.NewLolomoSession;
import com.netflix.mediaclient.service.logging.uiaction.NavigationSession;
import com.netflix.mediaclient.service.logging.uiaction.LoginSession;
import com.netflix.mediaclient.service.logging.uiaction.EditProfileSession;
import com.netflix.mediaclient.service.logging.uiaction.DeleteProfileSession;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.service.logging.uiaction.AddToPlaylistSession;
import com.netflix.mediaclient.service.logging.uiaction.AddProfileSession;
import com.netflix.mediaclient.service.logging.uiaction.AcknowledgeSignupSession;
import com.netflix.mediaclient.servicemgr.UserActionLogging;

final class UserActionLoggingImpl implements UserActionLogging
{
    private static final String TAG = "nf_log";
    private AcknowledgeSignupSession mAcknowledgeSignup;
    private AddProfileSession mAddProfileSession;
    private AddToPlaylistSession mAddToPlaylistSession;
    private DataContext mDataContext;
    private DeleteProfileSession mDeleteProfileSession;
    private EditProfileSession mEditProfileSession;
    private EventHandler mEventHandler;
    private LoginSession mLoginSession;
    private NavigationSession mNavigationSession;
    private NewLolomoSession mNewLolomoSession;
    private RateTitleSession mRateTitleSession;
    private RegisterSession mRegisterSession;
    private RemoveFromPlaylistSession mRemoveFromPlaylistSession;
    private SayThanksSession mSayThanksSession;
    private Map<Long, SearchSession> mSearchSessions;
    private SelectProfileSession mSelectProfileSession;
    private StartPlaySession mStartPlaySession;
    private SubmitPaymentSession mSubmitPaymentSession;
    private UpgradeStreamsSession mUpgradeStreamsSession;
    
    UserActionLoggingImpl(final EventHandler mEventHandler) {
        this.mSearchSessions = new ConcurrentHashMap<Long, SearchSession>(5);
        this.mEventHandler = mEventHandler;
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
            Label_0108: {
                if (!StringUtils.isNotEmpty(stringExtra3)) {
                    break Label_0108;
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
    
    private void handleDeleteProfileEnded(Intent value) {
        IClientLogging$CompletionReason value2 = null;
        final String stringExtra = value.getStringExtra("reason");
        final String stringExtra2 = value.getStringExtra("error");
        final String stringExtra3 = value.getStringExtra("view");
        while (true) {
            Label_0069: {
                if (!StringUtils.isNotEmpty(stringExtra3)) {
                    break Label_0069;
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
    
    private void handleEditProfileEnded(final Intent intent) {
        IClientLogging$CompletionReason value = null;
        final String stringExtra = intent.getStringExtra("reason");
        final String stringExtra2 = intent.getStringExtra("error");
        final String stringExtra3 = intent.getStringExtra("view");
        while (true) {
            Label_0108: {
                if (!StringUtils.isNotEmpty(stringExtra3)) {
                    break Label_0108;
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
            Label_0069: {
                if (!StringUtils.isNotEmpty(stringExtra3)) {
                    break Label_0069;
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
                Label_0138: {
                    while (true) {
                        while (true) {
                            try {
                                final UIError instance = UIError.createInstance(stringExtra);
                                if (!StringUtils.isNotEmpty((String)s)) {
                                    break Label_0138;
                                }
                                s = IClientLogging$CompletionReason.valueOf((String)s);
                                if (StringUtils.isNotEmpty((String)s2)) {
                                    s2 = IClientLogging$ModalView.valueOf((String)s2);
                                    this.endNewLolomoSession((IClientLogging$CompletionReason)s, (IClientLogging$ModalView)s2, instance, stringExtra2, stringExtra3, longExtra, stringExtra4, stringExtra5);
                                    return;
                                }
                            }
                            catch (JSONException ex) {
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
    
    private void handleRateTitleEnded(final Intent intent) {
    Label_0060_Outer:
        while (true) {
            Integer value = null;
            Serializable s = intent.getStringExtra("reason");
            final String stringExtra = intent.getStringExtra("error");
            final int intExtra = intent.getIntExtra("rating", 0);
            final int intExtra2 = intent.getIntExtra("rank", Integer.MIN_VALUE);
            while (true) {
                Label_0093: {
                    while (true) {
                        while (true) {
                            try {
                                final UIError instance = UIError.createInstance(stringExtra);
                                if (!StringUtils.isNotEmpty((String)s)) {
                                    break Label_0093;
                                }
                                s = IClientLogging$CompletionReason.valueOf((String)s);
                                if (intExtra2 == Integer.MIN_VALUE) {
                                    this.endRateTitleSession((IClientLogging$CompletionReason)s, instance, value, intExtra);
                                    return;
                                }
                            }
                            catch (JSONException ex) {
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
    
    private void handleSayThanksEnded(final Intent intent) {
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
                            this.endSayThanksSession((IClientLogging$CompletionReason)s, value, instance);
                            return;
                        }
                    }
                    catch (JSONException ex) {
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
    
    private void handleSayThanksStart(final Intent intent) {
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
        this.startSayThanksSession(value, value2);
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
            Label_0069: {
                if (!StringUtils.isNotEmpty(stringExtra3)) {
                    break Label_0069;
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
    
    private void handleStartPlayEnded(final Intent intent) {
    Label_0049_Outer:
        while (true) {
            Integer value = null;
            Serializable s = intent.getStringExtra("reason");
            final String stringExtra = intent.getStringExtra("error");
            final int intExtra = intent.getIntExtra("rank", Integer.MIN_VALUE);
            while (true) {
                Label_0092: {
                    while (true) {
                        while (true) {
                            try {
                                final UIError instance = UIError.createInstance(stringExtra);
                                if (!StringUtils.isNotEmpty((String)s)) {
                                    break Label_0092;
                                }
                                s = IClientLogging$CompletionReason.valueOf((String)s);
                                if (intExtra == Integer.MIN_VALUE) {
                                    this.endStartPlaySession((IClientLogging$CompletionReason)s, instance, value, PlayerType.toPlayerType(intent.getIntExtra("playerType", -1)));
                                    return;
                                }
                            }
                            catch (JSONException ex) {
                                final UIError instance = null;
                                continue Label_0049_Outer;
                            }
                            break;
                        }
                        value = intExtra;
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
        //     6: astore          4
        //     8: aload_1        
        //     9: ldc_w           "sucess"
        //    12: iconst_0       
        //    13: invokevirtual   android/content/Intent.getBooleanExtra:(Ljava/lang/String;Z)Z
        //    16: istore_2       
        //    17: aload_1        
        //    18: ldc_w           "error_code"
        //    21: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    24: astore          6
        //    26: aload_1        
        //    27: ldc_w           "payment_type"
        //    30: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    33: astore          5
        //    35: aload_1        
        //    36: ldc             "error"
        //    38: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    41: astore_1       
        //    42: aload_1        
        //    43: invokestatic    com/netflix/mediaclient/service/logging/client/model/UIError.createInstance:(Ljava/lang/String;)Lcom/netflix/mediaclient/service/logging/client/model/UIError;
        //    46: astore_3       
        //    47: aload           4
        //    49: invokestatic    com/netflix/mediaclient/util/StringUtils.isEmpty:(Ljava/lang/String;)Z
        //    52: ifne            130
        //    55: aload           4
        //    57: invokestatic    com/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason.valueOf:(Ljava/lang/String;)Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;
        //    60: astore          4
        //    62: aload           5
        //    64: invokestatic    com/netflix/mediaclient/util/StringUtils.isEmpty:(Ljava/lang/String;)Z
        //    67: ifne            124
        //    70: aload           5
        //    72: invokestatic    com/netflix/mediaclient/servicemgr/UserActionLogging$PaymentType.valueOf:(Ljava/lang/String;)Lcom/netflix/mediaclient/servicemgr/UserActionLogging$PaymentType;
        //    75: astore          5
        //    77: aload           6
        //    79: invokestatic    com/netflix/mediaclient/util/StringUtils.isNotEmpty:(Ljava/lang/String;)Z
        //    82: ifeq            119
        //    85: new             Lorg/json/JSONObject;
        //    88: dup            
        //    89: aload           6
        //    91: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //    94: astore_1       
        //    95: aload_0        
        //    96: aload           4
        //    98: aload_3        
        //    99: iload_2        
        //   100: aload           5
        //   102: aload_1        
        //   103: invokevirtual   com/netflix/mediaclient/service/logging/UserActionLoggingImpl.endSubmitPaymentSession:(Lcom/netflix/mediaclient/servicemgr/IClientLogging$CompletionReason;Lcom/netflix/mediaclient/service/logging/client/model/UIError;ZLcom/netflix/mediaclient/servicemgr/UserActionLogging$PaymentType;Lorg/json/JSONObject;)V
        //   106: return         
        //   107: astore_1       
        //   108: aconst_null    
        //   109: astore_3       
        //   110: goto            47
        //   113: astore_1       
        //   114: aconst_null    
        //   115: astore_1       
        //   116: goto            95
        //   119: aconst_null    
        //   120: astore_1       
        //   121: goto            95
        //   124: aconst_null    
        //   125: astore          5
        //   127: goto            77
        //   130: aconst_null    
        //   131: astore          4
        //   133: goto            62
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  42     47     107    113    Lorg/json/JSONException;
        //  85     95     113    119    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0095:
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
    
    @Override
    public void endAcknowledgeSignupSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final IClientLogging$ModalView clientLogging$ModalView) {
        Log.d("nf_log", "AcknowledgeSignup session ended and posted to executor");
        this.mEventHandler.executeInBackground(new UserActionLoggingImpl$1(this, clientLogging$CompletionReason, uiError, clientLogging$ModalView, this.mDataContext));
        Log.d("nf_log", "AcknowledgeSignup session end done.");
    }
    
    @Override
    public void endAddProfileSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError, final UserActionLogging$Profile userActionLogging$Profile) {
        Log.d("nf_log", "Add profile session ended and posted to executor");
        final DataContext mDataContext = this.mDataContext;
        Log.d("nf_log", "Add profile session ended");
        if (this.mAddProfileSession == null) {
            Log.w("nf_log", "Add profile session does NOT exist!");
            return;
        }
        final AddProfileEndedEvent endedEvent = this.mAddProfileSession.createEndedEvent(clientLogging$CompletionReason, uiError, clientLogging$ModalView, userActionLogging$Profile);
        if (endedEvent == null) {
            Log.d("nf_log", "Add profile session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, mDataContext, this.mAddProfileSession.getView());
        this.mEventHandler.removeSession(this.mAddProfileSession);
        Log.d("nf_log", "Add profile session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mAddProfileSession = null;
        Log.d("nf_log", "Add profile session end event posted.");
        Log.d("nf_log", "Add profile session end done.");
    }
    
    @Override
    public void endAddToPlaylistSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final int n) {
        Log.d("nf_log", "AddToPlaylist session ended and posted to executor");
        this.mEventHandler.executeInBackground(new UserActionLoggingImpl$2(this, clientLogging$CompletionReason, uiError, n, this.mDataContext));
        Log.d("nf_log", "AddToPlaylist session end done.");
    }
    
    @Override
    public void endDeleteProfileSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError) {
        Log.d("nf_log", "Delete profile session ended and posted to executor");
        final DataContext mDataContext = this.mDataContext;
        Log.d("nf_log", "Delete profile session ended");
        if (this.mDeleteProfileSession == null) {
            Log.w("nf_log", "Delete profile session does NOT exist!");
            return;
        }
        final DeleteProfileEndedEvent endedEvent = this.mDeleteProfileSession.createEndedEvent(clientLogging$CompletionReason, uiError, clientLogging$ModalView);
        if (endedEvent == null) {
            Log.d("nf_log", "Delete profile session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, mDataContext, this.mDeleteProfileSession.getView());
        this.mEventHandler.removeSession(this.mDeleteProfileSession);
        Log.d("nf_log", "Delete profile session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mDeleteProfileSession = null;
        Log.d("nf_log", "Delete profile session end event posted.");
        Log.d("nf_log", "Delete profile session end done.");
    }
    
    @Override
    public void endEditProfileSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError, final UserActionLogging$Profile userActionLogging$Profile) {
        Log.d("nf_log", "Edit profile session ended and posted to executor");
        final DataContext mDataContext = this.mDataContext;
        Log.d("nf_log", "Edit profile session ended");
        if (this.mEditProfileSession == null) {
            Log.w("nf_log", "Edit profile session does NOT exist!");
            return;
        }
        final EditProfileEndedEvent endedEvent = this.mEditProfileSession.createEndedEvent(clientLogging$CompletionReason, uiError, clientLogging$ModalView, userActionLogging$Profile);
        if (endedEvent == null) {
            Log.d("nf_log", "Edit profile session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, mDataContext, this.mEditProfileSession.getView());
        this.mEventHandler.removeSession(this.mEditProfileSession);
        Log.d("nf_log", "Edit profile session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mEditProfileSession = null;
        Log.d("nf_log", "Edit profile session end event posted.");
        Log.d("nf_log", "Edit profile session end done.");
    }
    
    @Override
    public void endLoginSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        Log.d("nf_log", "Login session ended and posted to executor");
        this.mEventHandler.executeInBackground(new UserActionLoggingImpl$3(this, clientLogging$CompletionReason, uiError, this.mDataContext));
        Log.d("nf_log", "Login session end done.");
    }
    
    @Override
    public void endNavigationSession(final IClientLogging$ModalView clientLogging$ModalView, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        Log.d("nf_log", "Navigation session ended and posted to executor");
        this.mEventHandler.executeInBackground(new UserActionLoggingImpl$4(this, clientLogging$ModalView, clientLogging$CompletionReason, uiError, this.mDataContext));
        Log.d("nf_log", "Navigation session end done.");
    }
    
    @Override
    public void endNewLolomoSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError, final String s, final String s2, final long n, final String s3, final String s4) {
        Log.d("nf_log", "NewLolomoSession ended and posted to executor");
        this.mEventHandler.executeInBackground(new UserActionLoggingImpl$13(this, clientLogging$CompletionReason, uiError, clientLogging$ModalView, s, s2, n, s3, s4, this.mDataContext));
        Log.d("nf_log", "NewLolomoSession end done.");
    }
    
    @Override
    public void endRateTitleSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final Integer n, final int n2) {
        Log.d("nf_log", "RateTitle session ended and posted to executor");
        this.mEventHandler.executeInBackground(new UserActionLoggingImpl$5(this, clientLogging$CompletionReason, uiError, n, n2, this.mDataContext));
        Log.d("nf_log", "RateTitle session end done.");
    }
    
    @Override
    public void endRegisterSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        Log.d("nf_log", "Register session ended and posted to executor");
        this.mEventHandler.executeInBackground(new UserActionLoggingImpl$6(this, clientLogging$CompletionReason, uiError, this.mDataContext));
        Log.d("nf_log", "Register session end done.");
    }
    
    @Override
    public void endRemoveFromPlaylistSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        Log.d("nf_log", "RemoveFromPlaylist session ended and posted to executor");
        this.mEventHandler.executeInBackground(new UserActionLoggingImpl$7(this, clientLogging$CompletionReason, uiError, this.mDataContext));
        Log.d("nf_log", "RemoveFromPlaylist session end done.");
    }
    
    @Override
    public void endSayThanksSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError) {
        Log.d("nf_log", "SayThanks ended and posted to executor");
        this.mEventHandler.executeInBackground(new UserActionLoggingImpl$12(this, clientLogging$CompletionReason, uiError, clientLogging$ModalView, this.mDataContext));
        Log.d("nf_log", "SayThanks end done.");
    }
    
    @Override
    public void endSearchSession(final long n, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        Log.d("nf_log", "Search session ended and posted to executor");
        this.mEventHandler.executeInBackground(new UserActionLoggingImpl$8(this, n, clientLogging$CompletionReason, uiError, this.mDataContext));
        Log.d("nf_log", "Search session end done.");
    }
    
    @Override
    public void endSelectProfileSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError) {
        Log.d("nf_log", "Select profile session ended and posted to executor");
        final DataContext mDataContext = this.mDataContext;
        Log.d("nf_log", "Select profile session ended");
        if (this.mSelectProfileSession == null) {
            Log.w("nf_log", "Select profile session does NOT exist!");
            return;
        }
        final SelectProfileEndedEvent endedEvent = this.mSelectProfileSession.createEndedEvent(clientLogging$CompletionReason, uiError, clientLogging$ModalView);
        if (endedEvent == null) {
            Log.d("nf_log", "Select profile session still waits on session id, do not post at this time.");
            return;
        }
        this.populateEvent(endedEvent, mDataContext, this.mSelectProfileSession.getView());
        this.mEventHandler.removeSession(this.mSelectProfileSession);
        Log.d("nf_log", "Select profile session end event posting...");
        this.mEventHandler.post(endedEvent);
        this.mSelectProfileSession = null;
        Log.d("nf_log", "Select profile session end event posted.");
        Log.d("nf_log", "Select profile session end done.");
    }
    
    @Override
    public void endStartPlaySession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final Integer n, final PlayerType playerType) {
        Log.d("nf_log", "StartPlay session ended and posted to executor");
        this.mEventHandler.executeInBackground(new UserActionLoggingImpl$9(this, clientLogging$CompletionReason, uiError, n, playerType, this.mDataContext));
        Log.d("nf_log", "StartPlay session end done.");
    }
    
    @Override
    public void endSubmitPaymentSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final boolean b, final UserActionLogging$PaymentType userActionLogging$PaymentType, final JSONObject jsonObject) {
        Log.d("nf_log", "SubmitPayment session ended and posted to executor");
        this.mEventHandler.executeInBackground(new UserActionLoggingImpl$10(this, clientLogging$CompletionReason, uiError, b, userActionLogging$PaymentType, jsonObject, this.mDataContext));
        Log.d("nf_log", "SubmitPayment session end done.");
    }
    
    @Override
    public void endUpgradeStreamsSession(final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final UserActionLogging$Streams userActionLogging$Streams) {
        Log.d("nf_log", "UpgradeStreams session ended and posted to executor");
        this.mEventHandler.executeInBackground(new UserActionLoggingImpl$11(this, clientLogging$CompletionReason, uiError, userActionLogging$Streams, this.mDataContext));
        Log.d("nf_log", "UpgradeStreams session end done.");
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
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SAY_THANKS_START".equals(action)) {
            Log.d("nf_log", "SAY_THANKS_START");
            this.handleSayThanksStart(intent);
            return true;
        }
        if ("com.netflix.mediaclient.intent.action.LOG_UIA_SAY_THANKS_ENDED".equals(action)) {
            Log.d("nf_log", "SAY_THANKS_ENDED");
            this.handleSayThanksEnded(intent);
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
        if (Log.isLoggable("nf_log", 3)) {
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
    public void startSayThanksSession(final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (this.mSayThanksSession != null) {
            Log.e("nf_log", "SayThanks session already started!");
            return;
        }
        Log.d("nf_log", "SayThanks session starting...");
        this.mSayThanksSession = new SayThanksSession(userActionLogging$CommandName, clientLogging$ModalView);
        this.mEventHandler.addSession(this.mSayThanksSession);
        Log.d("nf_log", "SayThanks  session start done.");
    }
    
    @Override
    public void startSearchSession(final long n, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView, final String s) {
        Log.d("nf_log", "Search session starting...");
        final SearchSession searchSession = new SearchSession(n, userActionLogging$CommandName, clientLogging$ModalView, s);
        this.mEventHandler.addSession(searchSession);
        this.mSearchSessions.put(n, searchSession);
        Log.d("nf_log", "Search session start done.");
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
