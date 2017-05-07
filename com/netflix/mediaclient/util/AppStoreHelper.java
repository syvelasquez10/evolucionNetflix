// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.content.pm.PackageManager;
import com.amazon.drm.AmazonLicenseVerificationCallback;
import com.netflix.mediaclient.Log;
import java.net.URLEncoder;
import android.content.Context;
import android.net.Uri;
import android.content.Intent;

public final class AppStoreHelper
{
    private static final String AMAZON_APP_STORE_URI = "amzn://apps/android?p=com.netflix.mediaclient";
    private static final String ANDROID_PACKAGE_PREFIX = "com.android.";
    private static final String GOOGLE_PACKAGE_PREFIX = "com.google.";
    private static final String INSTALL_SOURCE_AMAZON = "amazon";
    private static final String INSTALL_SOURCE_GOOGLE = "google";
    private static final String INSTALL_SOURCE_SIDELOAD = "sideload";
    private static final String NOOK_APP_STORE_ACTION = "com.bn.sdk.shop.details";
    private static final String NOOK_APP_STORE_NETFLIX_EAN = "2940043872739";
    private static final String PACKAGE_NAME = "com.netflix.mediaclient";
    private static final String PLAY_STORE_HTTPS_LINK = "https://market.android.com/details?id=com.netflix.mediaclient";
    private static final String PLAY_STORE_URI = "market://details?id=com.netflix.mediaclient";
    private static final String REFERRER = "referrer";
    private static final String TAG = "nf_appstorehelper";
    
    public static final Intent getAmazonStoreIntent() {
        final Intent intent = new Intent();
        intent.setData(Uri.parse("amzn://apps/android?p=com.netflix.mediaclient"));
        return intent;
    }
    
    public static String getInstallationSource(final Context context) {
        if (isPlayStoreInstallSource(context)) {
            return "google";
        }
        if (isAmazonStoreInstallSource()) {
            return "amazon";
        }
        return "sideload";
    }
    
    public static final Intent getNookStoreIntent() {
        final Intent intent = new Intent();
        intent.setAction("com.bn.sdk.shop.details");
        intent.putExtra("product_details_ean", "2940043872739");
        return intent;
    }
    
    public static final Intent getPlayStoreIntent() {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=com.netflix.mediaclient"));
        return intent;
    }
    
    public static String getPlayStoreLinkForInstallation(final String s) {
        final String encode = URLEncoder.encode("token=" + s);
        final StringBuilder sb = new StringBuilder("https://market.android.com/details?id=com.netflix.mediaclient");
        if (StringUtils.isNotEmpty(s)) {
            sb.append('&').append("referrer").append('=').append(encode);
        }
        return sb.toString();
    }
    
    public static final Intent getUpdateSourceIntent(final Context context) {
        if (isAmazonStoreInstallSource()) {
            final Intent amazonStoreIntent = getAmazonStoreIntent();
            if (AndroidUtils.queryIntentActivities(context, amazonStoreIntent) != null) {
                Log.d("nf_appstorehelper", "App Update Source is Amazon App Store");
                return amazonStoreIntent;
            }
        }
        final Intent playStoreIntent = getPlayStoreIntent();
        if (AndroidUtils.queryIntentActivities(context, playStoreIntent) != null) {
            Log.d("nf_appstorehelper", "App Update Source is Google Play Store");
            return playStoreIntent;
        }
        Log.d("nf_appstorehelper", "Google Play Store is not installed or was not setup.");
        final Intent nookStoreIntent = getNookStoreIntent();
        if (AndroidUtils.queryIntentActivities(context, nookStoreIntent) != null) {
            Log.d("nf_appstorehelper", "App Update Source is Nook App Store");
            return nookStoreIntent;
        }
        final Intent amazonStoreIntent2 = getAmazonStoreIntent();
        if (AndroidUtils.queryIntentActivities(context, amazonStoreIntent2) != null) {
            Log.d("nf_appstorehelper", "App Update Source is Amazon App Store");
            return amazonStoreIntent2;
        }
        return null;
    }
    
    public static final boolean isAmazonStoreInstallSource() {
        if (AmazonLicenseVerificationCallback.isCalled()) {
            Log.d("nf_appstorehelper", "Installation source is Amazon App Store.");
            return true;
        }
        return false;
    }
    
    public static final boolean isPlayStoreInstallSource(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            final String installerPackageName = packageManager.getInstallerPackageName("com.netflix.mediaclient");
            if (installerPackageName != null) {
                final String lowerCase = installerPackageName.toLowerCase();
                if (lowerCase.startsWith("com.android.") || lowerCase.startsWith("com.google.")) {
                    Log.d("nf_appstorehelper", "Installation source is Google Play Store.");
                    return true;
                }
            }
        }
        return false;
    }
}
