// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import com.netflix.mediaclient.util.CryptoUtils;
import java.util.Arrays;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.media.UnsupportedSchemeException;
import android.media.MediaDrm$CryptoSession;
import android.util.Base64;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.media.NotProvisionedException;
import com.netflix.mediaclient.StatusCode;
import java.util.HashMap;
import android.media.MediaDrm$KeyRequest;
import com.netflix.mediaclient.util.PreferenceUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import android.content.Context;
import android.media.MediaDrm;
import java.util.UUID;
import android.annotation.TargetApi;
import android.media.MediaDrm$OnEventListener;
import com.netflix.mediaclient.Log;

class WidevineDrmManager$2 implements Runnable
{
    final /* synthetic */ WidevineDrmManager this$0;
    
    WidevineDrmManager$2(final WidevineDrmManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.mUser.logoutUser();
        Log.d(WidevineDrmManager.TAG, "Redo CDM provisioning");
        this.this$0.init();
    }
}
