// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.content.Intent;
import android.app.Activity;
import android.support.v4.app.Fragment;

class Session$AuthorizationRequest$2 implements Session$StartActivityDelegate
{
    final /* synthetic */ Session$AuthorizationRequest this$0;
    final /* synthetic */ Fragment val$fragment;
    
    Session$AuthorizationRequest$2(final Session$AuthorizationRequest this$0, final Fragment val$fragment) {
        this.this$0 = this$0;
        this.val$fragment = val$fragment;
    }
    
    @Override
    public Activity getActivityContext() {
        return this.val$fragment.getActivity();
    }
    
    @Override
    public void startActivityForResult(final Intent intent, final int n) {
        this.val$fragment.startActivityForResult(intent, n);
    }
}
