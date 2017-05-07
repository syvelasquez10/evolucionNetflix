// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui;

import android.content.Context;
import android.content.Intent;
import android.app.Activity;

public class RelaunchActivity extends LaunchActivity
{
    private static final String EXTRA_SOURCE = "extra_source";
    
    public static Intent createStartIntent(final Activity activity, final String s) {
        return new Intent((Context)activity, (Class)RelaunchActivity.class).putExtra("extra_source", s);
    }
    
    @Override
    protected boolean shouldCreateUiSessions() {
        return false;
    }
}
