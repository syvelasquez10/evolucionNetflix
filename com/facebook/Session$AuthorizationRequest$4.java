// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Arrays;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import android.support.v4.app.Fragment;
import java.util.HashMap;
import java.util.UUID;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.io.Serializable;
import android.content.Intent;
import android.app.Activity;

class Session$AuthorizationRequest$4 implements AuthorizationClient$StartActivityDelegate
{
    final /* synthetic */ Session$AuthorizationRequest this$0;
    
    Session$AuthorizationRequest$4(final Session$AuthorizationRequest this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Activity getActivityContext() {
        return this.this$0.startActivityDelegate.getActivityContext();
    }
    
    @Override
    public void startActivityForResult(final Intent intent, final int n) {
        this.this$0.startActivityDelegate.startActivityForResult(intent, n);
    }
}
