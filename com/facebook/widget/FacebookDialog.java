// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.AppEventsLogger;
import com.facebook.Settings;
import com.facebook.internal.Utility$DialogFeatureConfig;
import com.facebook.internal.Utility;
import com.facebook.internal.NativeProtocol;
import com.facebook.FacebookException;
import java.util.ArrayList;
import android.os.Bundle;
import android.content.Intent;
import java.util.Iterator;
import java.util.EnumSet;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.app.Activity;
import com.facebook.NativeAppCallAttachmentStore;

public class FacebookDialog
{
    public static final String COMPLETION_GESTURE_CANCEL = "cancel";
    private static final String EXTRA_DIALOG_COMPLETE_KEY = "com.facebook.platform.extra.DID_COMPLETE";
    private static final String EXTRA_DIALOG_COMPLETION_GESTURE_KEY = "com.facebook.platform.extra.COMPLETION_GESTURE";
    private static final String EXTRA_DIALOG_COMPLETION_ID_KEY = "com.facebook.platform.extra.POST_ID";
    public static final String RESULT_ARGS_DIALOG_COMPLETE_KEY = "didComplete";
    public static final String RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY = "completionGesture";
    public static final String RESULT_ARGS_DIALOG_COMPLETION_ID_KEY = "postId";
    private static NativeAppCallAttachmentStore attachmentStore;
    private Activity activity;
    private FacebookDialog$PendingCall appCall;
    private Fragment fragment;
    private FacebookDialog$OnPresentCallback onPresentCallback;
    
    private FacebookDialog(final Activity activity, final Fragment fragment, final FacebookDialog$PendingCall appCall, final FacebookDialog$OnPresentCallback onPresentCallback) {
        this.activity = activity;
        this.fragment = fragment;
        this.appCall = appCall;
        this.onPresentCallback = onPresentCallback;
    }
    
    public static boolean canPresentMessageDialog(final Context context, final FacebookDialog$MessageDialogFeature... array) {
        return handleCanPresent(context, EnumSet.of(FacebookDialog$MessageDialogFeature.MESSAGE_DIALOG, array));
    }
    
    public static boolean canPresentOpenGraphActionDialog(final Context context, final FacebookDialog$OpenGraphActionDialogFeature... array) {
        return handleCanPresent(context, EnumSet.of(FacebookDialog$OpenGraphActionDialogFeature.OG_ACTION_DIALOG, array));
    }
    
    public static boolean canPresentOpenGraphMessageDialog(final Context context, final FacebookDialog$OpenGraphMessageDialogFeature... array) {
        return handleCanPresent(context, EnumSet.of(FacebookDialog$OpenGraphMessageDialogFeature.OG_MESSAGE_DIALOG, array));
    }
    
    public static boolean canPresentShareDialog(final Context context, final FacebookDialog$ShareDialogFeature... array) {
        return handleCanPresent(context, EnumSet.of(FacebookDialog$ShareDialogFeature.SHARE_DIALOG, array));
    }
    
    private static String getActionForFeatures(final Iterable<? extends FacebookDialog$DialogFeature> iterable) {
        final String s = null;
        final Iterator<? extends FacebookDialog$DialogFeature> iterator = iterable.iterator();
        String action = s;
        if (iterator.hasNext()) {
            action = ((FacebookDialog$DialogFeature)iterator.next()).getAction();
        }
        return action;
    }
    
    private static NativeAppCallAttachmentStore getAttachmentStore() {
        if (FacebookDialog.attachmentStore == null) {
            FacebookDialog.attachmentStore = new NativeAppCallAttachmentStore();
        }
        return FacebookDialog.attachmentStore;
    }
    
    private static String getEventName(final Intent intent) {
        final boolean b = true;
        final String stringExtra = intent.getStringExtra("com.facebook.platform.protocol.PROTOCOL_ACTION");
        boolean hasExtra = intent.hasExtra("com.facebook.platform.extra.PHOTOS");
        final Bundle bundleExtra = intent.getBundleExtra("com.facebook.platform.protocol.METHOD_ARGS");
        boolean b3;
        if (bundleExtra != null) {
            final ArrayList stringArrayList = bundleExtra.getStringArrayList("PHOTOS");
            final String string = bundleExtra.getString("VIDEO");
            boolean b2 = hasExtra;
            if (stringArrayList != null) {
                b2 = hasExtra;
                if (!stringArrayList.isEmpty()) {
                    b2 = true;
                }
            }
            if (string != null && !string.isEmpty()) {
                hasExtra = b2;
                b3 = b;
            }
            else {
                final boolean b4 = false;
                hasExtra = b2;
                b3 = b4;
            }
        }
        else {
            b3 = false;
        }
        return getEventName(stringExtra, hasExtra, b3);
    }
    
    private static String getEventName(final String s, final boolean b, final boolean b2) {
        if (s.equals("com.facebook.platform.action.request.FEED_DIALOG")) {
            if (b2) {
                return "fb_dialogs_present_share_video";
            }
            if (b) {
                return "fb_dialogs_present_share_photo";
            }
            return "fb_dialogs_present_share";
        }
        else if (s.equals("com.facebook.platform.action.request.MESSAGE_DIALOG")) {
            if (b) {
                return "fb_dialogs_present_message_photo";
            }
            return "fb_dialogs_present_message";
        }
        else {
            if (s.equals("com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG")) {
                return "fb_dialogs_present_share_og";
            }
            if (s.equals("com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG")) {
                return "fb_dialogs_present_message_og";
            }
            if (s.equals("com.facebook.platform.action.request.LIKE_DIALOG")) {
                return "fb_dialogs_present_like";
            }
            throw new FacebookException("An unspecified action was presented");
        }
    }
    
    public static String getNativeDialogCompletionGesture(final Bundle bundle) {
        if (bundle.containsKey("completionGesture")) {
            return bundle.getString("completionGesture");
        }
        return bundle.getString("com.facebook.platform.extra.COMPLETION_GESTURE");
    }
    
    public static boolean getNativeDialogDidComplete(final Bundle bundle) {
        if (bundle.containsKey("didComplete")) {
            return bundle.getBoolean("didComplete");
        }
        return bundle.getBoolean("com.facebook.platform.extra.DID_COMPLETE", false);
    }
    
    public static String getNativeDialogPostId(final Bundle bundle) {
        if (bundle.containsKey("postId")) {
            return bundle.getString("postId");
        }
        return bundle.getString("com.facebook.platform.extra.POST_ID");
    }
    
    private static int getProtocolVersionForNativeDialog(final Context context, final String s, final int[] array) {
        return NativeProtocol.getLatestAvailableProtocolVersionForAction(context, s, array);
    }
    
    private static int[] getVersionSpecForFeature(final String s, final String s2, final FacebookDialog$DialogFeature facebookDialog$DialogFeature) {
        final Utility$DialogFeatureConfig dialogFeatureConfig = Utility.getDialogFeatureConfig(s, s2, facebookDialog$DialogFeature.name());
        if (dialogFeatureConfig != null) {
            return dialogFeatureConfig.getVersionSpec();
        }
        return new int[] { facebookDialog$DialogFeature.getMinVersion() };
    }
    
    private static int[] getVersionSpecForFeatures(final String s, final String s2, final Iterable<? extends FacebookDialog$DialogFeature> iterable) {
        final Iterator<? extends FacebookDialog$DialogFeature> iterator = iterable.iterator();
        int[] intersectRanges = null;
        while (iterator.hasNext()) {
            intersectRanges = Utility.intersectRanges(intersectRanges, getVersionSpecForFeature(s, s2, (FacebookDialog$DialogFeature)iterator.next()));
        }
        return intersectRanges;
    }
    
    public static boolean handleActivityResult(final Context context, final FacebookDialog$PendingCall facebookDialog$PendingCall, final int n, final Intent intent, final FacebookDialog$Callback facebookDialog$Callback) {
        if (n != facebookDialog$PendingCall.getRequestCode()) {
            return false;
        }
        if (FacebookDialog.attachmentStore != null) {
            FacebookDialog.attachmentStore.cleanupAttachmentsForCall(context, facebookDialog$PendingCall.getCallId());
        }
        if (facebookDialog$Callback != null) {
            if (NativeProtocol.isErrorResult(intent)) {
                final Bundle errorDataFromResultIntent = NativeProtocol.getErrorDataFromResultIntent(intent);
                facebookDialog$Callback.onError(facebookDialog$PendingCall, NativeProtocol.getExceptionFromErrorData(errorDataFromResultIntent), errorDataFromResultIntent);
            }
            else {
                facebookDialog$Callback.onComplete(facebookDialog$PendingCall, NativeProtocol.getSuccessResultsFromIntent(intent));
            }
        }
        return true;
    }
    
    private static boolean handleCanPresent(final Context context, final Iterable<? extends FacebookDialog$DialogFeature> iterable) {
        final String actionForFeatures = getActionForFeatures(iterable);
        String s;
        if (Utility.isNullOrEmpty(s = Settings.getApplicationId())) {
            s = Utility.getMetadataApplicationId(context);
        }
        return getProtocolVersionForNativeDialog(context, actionForFeatures, getVersionSpecForFeatures(s, actionForFeatures, iterable)) != -1;
    }
    
    private static void logDialogActivity(Activity activity, final Fragment fragment, final String s, final String s2) {
        if (fragment != null) {
            activity = fragment.getActivity();
        }
        final AppEventsLogger logger = AppEventsLogger.newLogger((Context)activity);
        final Bundle bundle = new Bundle();
        bundle.putString("fb_dialog_outcome", s2);
        logger.logSdkEvent(s, null, bundle);
    }
    
    public FacebookDialog$PendingCall present() {
        logDialogActivity(this.activity, this.fragment, getEventName(this.appCall.getRequestIntent()), "Completed");
        Label_0044: {
            if (this.onPresentCallback == null) {
                break Label_0044;
            }
            while (true) {
                while (true) {
                    try {
                        this.onPresentCallback.onPresent((Context)this.activity);
                        if (this.fragment != null) {
                            this.fragment.startActivityForResult(this.appCall.getRequestIntent(), this.appCall.getRequestCode());
                            return this.appCall;
                        }
                    }
                    catch (Exception ex) {
                        throw new FacebookException(ex);
                    }
                    this.activity.startActivityForResult(this.appCall.getRequestIntent(), this.appCall.getRequestCode());
                    continue;
                }
            }
        }
    }
}
