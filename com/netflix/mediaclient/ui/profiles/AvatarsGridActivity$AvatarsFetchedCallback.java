// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import java.util.ArrayList;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
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
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.widget.GridView;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import java.util.List;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class AvatarsGridActivity$AvatarsFetchedCallback extends SimpleManagerCallback
{
    final /* synthetic */ AvatarsGridActivity this$0;
    
    private AvatarsGridActivity$AvatarsFetchedCallback(final AvatarsGridActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAvailableAvatarsListFetched(final List<AvatarInfo> list, final Status status) {
        if (Log.isLoggable()) {
            Log.i("AvatarsGridActivity", "onAvailableAvatarsListFetched: " + list);
        }
        if (status.isSucces() && list != null) {
            this.this$0.mAvatars = list;
            if (this.this$0.mAvatars.contains(this.this$0.mDefaultAvatar)) {
                this.this$0.mAvatars.remove(this.this$0.mDefaultAvatar);
            }
            this.this$0.refreshCurrentIconSelection();
            this.this$0.updateUI();
            return;
        }
        this.this$0.handleUserAgentErrors(status);
    }
}
