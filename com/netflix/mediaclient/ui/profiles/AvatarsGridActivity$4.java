// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import java.util.ArrayList;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import android.os.Parcelable;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.android.app.Status;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.widget.GridView;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import java.util.List;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class AvatarsGridActivity$4 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ AvatarsGridActivity this$0;
    
    AvatarsGridActivity$4(final AvatarsGridActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onGlobalLayout() {
        ViewUtils.removeGlobalLayoutListener((View)this.this$0.mGridView, (ViewTreeObserver$OnGlobalLayoutListener)this);
        this.this$0.mAdapter.setupGridViewColumns();
    }
}
