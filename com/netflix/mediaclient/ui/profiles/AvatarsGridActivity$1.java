// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import java.util.ArrayList;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import android.os.Parcelable;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.widget.GridView;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import java.util.List;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class AvatarsGridActivity$1 implements ManagerStatusListener
{
    final /* synthetic */ AvatarsGridActivity this$0;
    
    AvatarsGridActivity$1(final AvatarsGridActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        Log.d("AvatarsGridActivity", "Manager is here!");
        this.this$0.mServiceManager = serviceManager;
        if (this.this$0.mAvatars == null) {
            this.this$0.mServiceManager.fetchAvailableAvatarsList(new AvatarsGridActivity$AvatarsFetchedCallback(this.this$0, null));
            return;
        }
        this.this$0.refreshCurrentIconSelection();
        this.this$0.updateUI();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        Log.d("AvatarsGridActivity", "Manager isn't available!");
        this.this$0.mServiceManager = null;
        this.this$0.updateUI();
    }
}
