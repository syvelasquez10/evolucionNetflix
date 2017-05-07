// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.log;

import com.netflix.mediaclient.servicemgr.SocialLogging$Source;
import com.netflix.mediaclient.servicemgr.SocialLogging$Channel;
import org.json.JSONArray;
import com.netflix.mediaclient.servicemgr.SocialLogging$FriendPosition;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.Error;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import android.content.Context;

public final class SocialLoggingUtils extends ConsolidatedLoggingUtils
{
    public static void reportEndSocialConnectSession(final Context context) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SOCIAL_CONNECT_SESSION_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportEndSocialImpressionSession(final Context context, final boolean b, final Error error) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SOCIAL_IMPRESSION_SESSION_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("success", b);
        while (true) {
            if (error == null) {
                break Label_0049;
            }
            try {
                intent.putExtra("error", error.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to create string presentation of error object", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportRecommendFriendSelectedEvent(final Context context, final IClientLogging$ModalView clientLogging$ModalView, final String s, final SocialLogging$FriendPosition[] array, int i) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        intent.putExtra("guid", s);
        intent.putExtra("trackId", i);
        while (true) {
            if (array == null || array.length <= 0) {
                break Label_0116;
            }
            try {
                final JSONArray jsonArray = new JSONArray();
                for (i = 0; i < array.length; ++i) {
                    jsonArray.put((Object)array[i].toJson());
                }
                intent.putExtra("friendPositions", jsonArray.toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to create string presentation of error object", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportRecommendImplicitFeedbackReadEvent(final Context context, final String s, final String s2, final int n) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND_READ");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("trackId", n);
        if (s2 != null) {
            intent.putExtra("videoId", s2);
        }
        if (s != null) {
            intent.putExtra("msgId", s);
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportRecommendMessageAddedEvent(final Context context, final IClientLogging$ModalView clientLogging$ModalView, final String s, final int n) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND_MESSAGE_ADDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        intent.putExtra("guid", s);
        intent.putExtra("trackId", n);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportRecommendPanelScrolledEvent(final Context context, final IClientLogging$ModalView clientLogging$ModalView, final String s, final int n) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND_SCROLLED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        intent.putExtra("guid", s);
        intent.putExtra("trackId", n);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportRecommendPanelSearchedEvent(final Context context, final IClientLogging$ModalView clientLogging$ModalView, final String s, final int n) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SOCIAL_RECOMMEND_SEARCHED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        intent.putExtra("guid", s);
        intent.putExtra("trackId", n);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportSocialConnectActionResponseEvent(final Context context, final SocialLogging$Channel socialLogging$Channel, final SocialLogging$Source socialLogging$Source, final boolean b, final Error error) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SOCIAL_CONNECT_ACTION_RESPONSE");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        if (socialLogging$Channel != null) {
            intent.putExtra("channel", socialLogging$Channel.name());
        }
        if (socialLogging$Source != null) {
            intent.putExtra("source", socialLogging$Source.name());
        }
        intent.putExtra("success", b);
        while (true) {
            if (error == null) {
                break Label_0087;
            }
            try {
                intent.putExtra("error", error.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to create string presentation of error object", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportSocialConnectImpressionEvent(final Context context, final IClientLogging$ModalView clientLogging$ModalView) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SOCIAL_CONNECT_IMPRESSION");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", clientLogging$ModalView.name());
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportStartSocialConnectSession(final Context context, final SocialLogging$Channel socialLogging$Channel) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SOCIAL_CONNECT_SESSION_STARTED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        if (socialLogging$Channel != null) {
            intent.putExtra("channel", socialLogging$Channel.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportStartSocialImpressionSession(final Context context, final IClientLogging$ModalView clientLogging$ModalView, final String s, final String s2, final int n) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_SOCIAL_IMPRESSION_SESSION_STARTED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("storyId", s2);
        intent.putExtra("view", clientLogging$ModalView.name());
        intent.putExtra("guid", s);
        intent.putExtra("trackId", n);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
