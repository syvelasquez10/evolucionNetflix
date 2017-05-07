// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.error.action;

import android.app.Activity;

abstract class BaseAction implements Runnable
{
    protected static final String TAG = "ErrorManager";
    protected Activity mActivity;
    
    BaseAction(final Activity mActivity) {
        if (mActivity == null) {
            throw new IllegalArgumentException("Owner activity can not be null!");
        }
        this.mActivity = mActivity;
    }
}
