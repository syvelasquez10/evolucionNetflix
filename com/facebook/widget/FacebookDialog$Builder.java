// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import com.facebook.AppEventsLogger;
import com.facebook.Settings;
import com.facebook.NativeAppCallAttachmentStore;
import android.net.Uri;
import com.facebook.internal.Utility$DialogFeatureConfig;
import com.facebook.internal.ServerProtocol;
import java.util.EnumSet;
import android.content.Intent;
import com.facebook.FacebookException;
import android.os.Bundle;
import com.facebook.internal.NativeProtocol;
import java.util.Iterator;
import com.facebook.NativeAppCallContentProvider;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import android.content.Context;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.io.File;
import android.graphics.Bitmap;
import java.util.HashMap;
import android.support.v4.app.Fragment;
import android.app.Activity;

public abstract class FacebookDialog$Builder<CONCRETE extends FacebookDialog$Builder<?>>
{
    protected final Activity activity;
    protected final FacebookDialog$PendingCall appCall;
    protected final String applicationId;
    protected String applicationName;
    protected Fragment fragment;
    protected HashMap<String, Bitmap> imageAttachments;
    protected HashMap<String, File> mediaAttachmentFiles;
    
    public FacebookDialog$Builder(final Activity activity) {
        this.imageAttachments = new HashMap<String, Bitmap>();
        this.mediaAttachmentFiles = new HashMap<String, File>();
        Validate.notNull(activity, "activity");
        this.activity = activity;
        this.applicationId = Utility.getMetadataApplicationId((Context)activity);
        this.appCall = new FacebookDialog$PendingCall(64207);
    }
    
    protected CONCRETE addImageAttachment(final String s, final Bitmap bitmap) {
        this.imageAttachments.put(s, bitmap);
        return (CONCRETE)this;
    }
    
    protected CONCRETE addImageAttachment(final String s, final File file) {
        this.mediaAttachmentFiles.put(s, file);
        return (CONCRETE)this;
    }
    
    protected List<String> addImageAttachmentFiles(final Collection<File> collection) {
        final ArrayList<String> list = new ArrayList<String>();
        for (final File file : collection) {
            final String string = UUID.randomUUID().toString();
            this.addImageAttachment(string, file);
            list.add(NativeAppCallContentProvider.getAttachmentUrl(this.applicationId, this.appCall.getCallId(), string));
        }
        return list;
    }
    
    protected List<String> addImageAttachments(final Collection<Bitmap> collection) {
        final ArrayList<String> list = new ArrayList<String>();
        for (final Bitmap bitmap : collection) {
            final String string = UUID.randomUUID().toString();
            this.addImageAttachment(string, bitmap);
            list.add(NativeAppCallContentProvider.getAttachmentUrl(this.applicationId, this.appCall.getCallId(), string));
        }
        return list;
    }
    
    protected CONCRETE addVideoAttachment(final String s, final File file) {
        this.mediaAttachmentFiles.put(s, file);
        return (CONCRETE)this;
    }
    
    protected String addVideoAttachmentFile(final File file) {
        final String string = UUID.randomUUID().toString();
        this.addVideoAttachment(string, file);
        return NativeAppCallContentProvider.getAttachmentUrl(this.applicationId, this.appCall.getCallId(), string);
    }
    
    public FacebookDialog build() {
        this.validate();
        final String access$100 = getActionForFeatures(this.getDialogFeatures());
        final int access$101 = getProtocolVersionForNativeDialog((Context)this.activity, access$100, getVersionSpecForFeatures(this.applicationId, access$100, this.getDialogFeatures()));
        Bundle bundle;
        if (NativeProtocol.isVersionCompatibleWithBucketedIntent(access$101)) {
            bundle = this.getMethodArguments();
        }
        else {
            bundle = this.setBundleExtras(new Bundle());
        }
        final Intent platformActivityIntent = NativeProtocol.createPlatformActivityIntent((Context)this.activity, this.appCall.getCallId().toString(), access$100, access$101, this.applicationName, bundle);
        if (platformActivityIntent == null) {
            logDialogActivity(this.activity, this.fragment, getEventName(access$100, bundle.containsKey("com.facebook.platform.extra.PHOTOS"), false), "Failed");
            throw new FacebookException("Unable to create Intent; this likely means the Facebook app is not installed.");
        }
        this.appCall.setRequestIntent(platformActivityIntent);
        return new FacebookDialog(this.activity, this.fragment, this.appCall, this.getOnPresentCallback(), null);
    }
    
    public boolean canPresent() {
        return handleCanPresent((Context)this.activity, this.getDialogFeatures());
    }
    
    protected abstract EnumSet<? extends FacebookDialog$DialogFeature> getDialogFeatures();
    
    List<String> getImageAttachmentNames() {
        return new ArrayList<String>(this.imageAttachments.keySet());
    }
    
    protected abstract Bundle getMethodArguments();
    
    FacebookDialog$OnPresentCallback getOnPresentCallback() {
        return new FacebookDialog$Builder$1(this);
    }
    
    protected String getWebFallbackUrlInternal() {
        final Iterator<FacebookDialog$DialogFeature> iterator = this.getDialogFeatures().iterator();
        String name;
        String action;
        if (iterator.hasNext()) {
            final FacebookDialog$DialogFeature facebookDialog$DialogFeature = iterator.next();
            name = facebookDialog$DialogFeature.name();
            action = facebookDialog$DialogFeature.getAction();
        }
        else {
            action = null;
            name = null;
        }
        final Utility$DialogFeatureConfig dialogFeatureConfig = Utility.getDialogFeatureConfig(this.applicationId, action, name);
        if (dialogFeatureConfig != null) {
            final Uri fallbackUrl = dialogFeatureConfig.getFallbackUrl();
            if (fallbackUrl != null) {
                final Bundle queryParamsForPlatformActivityIntentWebFallback = ServerProtocol.getQueryParamsForPlatformActivityIntentWebFallback((Context)this.activity, this.appCall.getCallId().toString(), NativeProtocol.getLatestKnownVersion(), this.applicationName, this.getMethodArguments());
                if (queryParamsForPlatformActivityIntentWebFallback != null) {
                    Uri buildUri = fallbackUrl;
                    if (fallbackUrl.isRelative()) {
                        buildUri = Utility.buildUri(ServerProtocol.getDialogAuthority(), fallbackUrl.toString(), queryParamsForPlatformActivityIntentWebFallback);
                    }
                    return buildUri.toString();
                }
            }
        }
        return null;
    }
    
    protected void putExtra(final Bundle bundle, final String s, final String s2) {
        if (s2 != null) {
            bundle.putString(s, s2);
        }
    }
    
    public CONCRETE setApplicationName(final String applicationName) {
        this.applicationName = applicationName;
        return (CONCRETE)this;
    }
    
    protected Bundle setBundleExtras(final Bundle bundle) {
        return bundle;
    }
    
    public CONCRETE setFragment(final Fragment fragment) {
        this.fragment = fragment;
        return (CONCRETE)this;
    }
    
    public CONCRETE setRequestCode(final int n) {
        this.appCall.setRequestCode(n);
        return (CONCRETE)this;
    }
    
    void validate() {
    }
}
