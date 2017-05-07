// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import com.google.android.gms.common.internal.m;
import android.content.IntentSender$SendIntentException;
import android.content.Intent;
import android.app.Activity;
import android.app.PendingIntent;

public final class ConnectionResult
{
    public static final int API_UNAVAILABLE = 16;
    public static final int CANCELED = 13;
    public static final int DEVELOPER_ERROR = 10;
    @Deprecated
    public static final int DRIVE_EXTERNAL_STORAGE_REQUIRED = 1500;
    public static final ConnectionResult HE;
    public static final int INTERNAL_ERROR = 8;
    public static final int INTERRUPTED = 15;
    public static final int INVALID_ACCOUNT = 5;
    public static final int LICENSE_CHECK_FAILED = 11;
    public static final int NETWORK_ERROR = 7;
    public static final int RESOLUTION_REQUIRED = 6;
    public static final int SERVICE_DISABLED = 3;
    public static final int SERVICE_INVALID = 9;
    public static final int SERVICE_MISSING = 1;
    public static final int SERVICE_VERSION_UPDATE_REQUIRED = 2;
    public static final int SIGN_IN_REQUIRED = 4;
    public static final int SUCCESS = 0;
    public static final int TIMEOUT = 14;
    private final int HF;
    private final PendingIntent mPendingIntent;
    
    static {
        HE = new ConnectionResult(0, null);
    }
    
    public ConnectionResult(final int hf, final PendingIntent mPendingIntent) {
        this.HF = hf;
        this.mPendingIntent = mPendingIntent;
    }
    
    private String fY() {
        switch (this.HF) {
            default: {
                return "unknown status code " + this.HF;
            }
            case 0: {
                return "SUCCESS";
            }
            case 1: {
                return "SERVICE_MISSING";
            }
            case 2: {
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            }
            case 3: {
                return "SERVICE_DISABLED";
            }
            case 4: {
                return "SIGN_IN_REQUIRED";
            }
            case 5: {
                return "INVALID_ACCOUNT";
            }
            case 6: {
                return "RESOLUTION_REQUIRED";
            }
            case 7: {
                return "NETWORK_ERROR";
            }
            case 8: {
                return "INTERNAL_ERROR";
            }
            case 9: {
                return "SERVICE_INVALID";
            }
            case 10: {
                return "DEVELOPER_ERROR";
            }
            case 11: {
                return "LICENSE_CHECK_FAILED";
            }
            case 13: {
                return "CANCELED";
            }
            case 14: {
                return "TIMEOUT";
            }
            case 15: {
                return "INTERRUPTED";
            }
        }
    }
    
    public int getErrorCode() {
        return this.HF;
    }
    
    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }
    
    public boolean hasResolution() {
        return this.HF != 0 && this.mPendingIntent != null;
    }
    
    public boolean isSuccess() {
        return this.HF == 0;
    }
    
    public void startResolutionForResult(final Activity activity, final int n) throws IntentSender$SendIntentException {
        if (!this.hasResolution()) {
            return;
        }
        activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), n, (Intent)null, 0, 0, 0);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("statusCode", this.fY()).a("resolution", this.mPendingIntent).toString();
    }
}
