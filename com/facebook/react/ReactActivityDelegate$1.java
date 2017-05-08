// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import android.annotation.TargetApi;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import android.view.KeyEvent;
import com.facebook.common.logging.FLog;
import android.net.Uri;
import android.widget.Toast;
import android.provider.Settings;
import android.os.Build$VERSION;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import com.facebook.infer.annotation.Assertions;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import com.facebook.react.devsupport.DoubleTapReloadRecognizer;
import android.app.Activity;
import com.facebook.react.modules.core.PermissionListener;
import com.facebook.react.bridge.Callback;

class ReactActivityDelegate$1 implements Callback
{
    final /* synthetic */ ReactActivityDelegate this$0;
    final /* synthetic */ int[] val$grantResults;
    final /* synthetic */ String[] val$permissions;
    final /* synthetic */ int val$requestCode;
    
    ReactActivityDelegate$1(final ReactActivityDelegate this$0, final int val$requestCode, final String[] val$permissions, final int[] val$grantResults) {
        this.this$0 = this$0;
        this.val$requestCode = val$requestCode;
        this.val$permissions = val$permissions;
        this.val$grantResults = val$grantResults;
    }
    
    @Override
    public void invoke(final Object... array) {
        if (this.this$0.mPermissionListener != null && this.this$0.mPermissionListener.onRequestPermissionsResult(this.val$requestCode, this.val$permissions, this.val$grantResults)) {
            this.this$0.mPermissionListener = null;
        }
    }
}
