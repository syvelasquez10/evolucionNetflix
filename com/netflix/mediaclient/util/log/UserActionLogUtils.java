// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.log;

import com.netflix.mediaclient.servicemgr.UserActionLogging$RememberProfile;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.UserActionLogging$Profile;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import android.content.Context;

public final class UserActionLogUtils extends ConsolidatedLoggingUtils
{
    public static void reportAddProfileActionEnded(final Context context, final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError, final UserActionLogging$Profile userActionLogging$Profile) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$CompletionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_ADD_PROFILE_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        intent.putExtra("view", clientLogging$ModalView.name());
        while (true) {
            if (uiError == null) {
                break Label_0074;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                if (userActionLogging$Profile != null) {
                    intent.putExtra("profile_is_kids", userActionLogging$Profile.isKids());
                    if (userActionLogging$Profile.getId() != null) {
                        intent.putExtra("profile_id", userActionLogging$Profile.getId());
                    }
                    if (userActionLogging$Profile.getName() != null) {
                        intent.putExtra("profile_name", userActionLogging$Profile.getName());
                    }
                    if (userActionLogging$Profile.getAge() != null) {
                        intent.putExtra("profile_age", (int)userActionLogging$Profile.getAge());
                    }
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportAddProfileActionStarted(final Context context, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$ModalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_ADD_PROFILE_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        if (userActionLogging$CommandName != null) {
            intent.putExtra("cmd", userActionLogging$CommandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportAddToQueueActionEnded(final Context context, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final Integer n) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$CompletionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_ADD_TO_PLAYLIST_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0062;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                if (n != null) {
                    intent.putExtra("title_rank", (int)n);
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportAddToQueueActionStarted(final Context context, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$ModalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_ADD_TO_PLAYLIST_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        if (userActionLogging$CommandName != null) {
            intent.putExtra("cmd", userActionLogging$CommandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportDeleteProfileActionEnded(final Context context, final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$CompletionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_DELETE_PROFILE_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        intent.putExtra("view", clientLogging$ModalView.name());
        while (true) {
            if (uiError == null) {
                break Label_0074;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportDeleteProfileActionStarted(final Context context, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView, final String s) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$ModalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_DELETE_PROFILE_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        intent.putExtra("profile_id", s);
        if (userActionLogging$CommandName != null) {
            intent.putExtra("cmd", userActionLogging$CommandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportEditProfileActionEnded(final Context context, final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError, final UserActionLogging$Profile userActionLogging$Profile) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$CompletionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_EDIT_PROFILE_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        intent.putExtra("view", clientLogging$ModalView.name());
        while (true) {
            if (uiError == null) {
                break Label_0074;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                if (userActionLogging$Profile != null) {
                    intent.putExtra("profile_is_kids", userActionLogging$Profile.isKids());
                    if (userActionLogging$Profile.getId() != null) {
                        intent.putExtra("profile_id", userActionLogging$Profile.getId());
                    }
                    if (userActionLogging$Profile.getName() != null) {
                        intent.putExtra("profile_name", userActionLogging$Profile.getName());
                    }
                    if (userActionLogging$Profile.getAge() != null) {
                        intent.putExtra("profile_age", (int)userActionLogging$Profile.getAge());
                    }
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportEditProfileActionStarted(final Context context, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$ModalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_EDIT_PROFILE_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        if (userActionLogging$CommandName != null) {
            intent.putExtra("cmd", userActionLogging$CommandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportLoginActionEnded(final Context context, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$CompletionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_LOGIN_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0058;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportLoginActionStarted(final Context context, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$ModalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_LOGIN_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        if (userActionLogging$CommandName != null) {
            intent.putExtra("cmd", userActionLogging$CommandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportNavigationActionEnded(final Context context, final IClientLogging$ModalView clientLogging$ModalView, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$CompletionReason, "Reason can not be null!");
        if (clientLogging$ModalView == null) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_NAVIGATION_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0079;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportNavigationActionStarted(final Context context, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        if (clientLogging$ModalView == null) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_NAVIGATION_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        if (userActionLogging$CommandName != null) {
            intent.putExtra("cmd", userActionLogging$CommandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportPlayActionEnded(final Context context, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final Integer n, final PlayerType playerType) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$CompletionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_START_PLAY_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0062;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                if (n != null) {
                    intent.putExtra("rank", (int)n);
                }
                if (playerType != null) {
                    intent.putExtra("playerType", playerType.getValue());
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportPlayActionStarted(final Context context, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$ModalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_START_PLAY_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        if (userActionLogging$CommandName != null) {
            intent.putExtra("cmd", userActionLogging$CommandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportRateActionEnded(final Context context, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, final Integer n, final Integer n2) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$CompletionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_RATE_TITLE_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0062;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                if (n != null) {
                    intent.putExtra("rank", (int)n);
                }
                if (n2 != null) {
                    intent.putExtra("rating", (int)n2);
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportRateActionStarted(final Context context, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$ModalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_RATE_TITLE_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        if (userActionLogging$CommandName != null) {
            intent.putExtra("cmd", userActionLogging$CommandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportRemoveFromQueueActionEnded(final Context context, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$CompletionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_REMOVE_FROM_PLAYLIST_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0058;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportRemoveFromQueueActionStarted(final Context context, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$ModalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_REMOVE_FROM_PLAYLIST_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        if (userActionLogging$CommandName != null) {
            intent.putExtra("cmd", userActionLogging$CommandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportSayThanksActionEnded(final Context context, final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$CompletionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_SAY_THANKS_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        while (true) {
            if (error == null) {
                break Label_0058;
            }
            try {
                intent.putExtra("error", error.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportSayThanksActionStarted(final Context context, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_SAY_THANKS_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        if (clientLogging$ModalView != null) {
            intent.putExtra("view", clientLogging$ModalView.name());
        }
        if (userActionLogging$CommandName != null) {
            intent.putExtra("cmd", userActionLogging$CommandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportSearchActionEnded(final long n, final Context context, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$CompletionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_SEARCH_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        intent.putExtra("id", n);
        while (true) {
            if (uiError == null) {
                break Label_0073;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportSearchActionStarted(final long n, final Context context, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView, final String s) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$ModalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_SEARCH_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        intent.putExtra("id", n);
        if (userActionLogging$CommandName != null) {
            intent.putExtra("cmd", userActionLogging$CommandName.name());
        }
        if (s != null) {
            intent.putExtra("term", s);
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportSelectProfileActionEnded(final Context context, final IClientLogging$CompletionReason clientLogging$CompletionReason, final IClientLogging$ModalView clientLogging$ModalView, final UIError uiError) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$CompletionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_SELECT_PROFILE_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        intent.putExtra("view", clientLogging$ModalView.name());
        while (true) {
            if (uiError == null) {
                break Label_0074;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportSelectProfileActionStarted(final Context context, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView, final String s, final UserActionLogging$RememberProfile userActionLogging$RememberProfile) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        ConsolidatedLoggingUtils.validateArgument(clientLogging$ModalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_SELECT_PROFILE_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        if (userActionLogging$CommandName != null) {
            intent.putExtra("cmd", userActionLogging$CommandName.name());
        }
        intent.putExtra("profile_id", s);
        if (userActionLogging$RememberProfile != null) {
            intent.putExtra("remember_profile", userActionLogging$RememberProfile.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
