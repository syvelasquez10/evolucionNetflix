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
import android.widget.ListAdapter;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import android.content.Context;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.widget.GridView;
import java.util.List;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.os.Parcelable;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;

class AvatarsGridActivity$3 implements AdapterView$OnItemClickListener
{
    final /* synthetic */ AvatarsGridActivity this$0;
    
    AvatarsGridActivity$3(final AvatarsGridActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        final Intent intent = new Intent();
        AvatarInfo access$500;
        if (n == 0) {
            access$500 = this.this$0.mDefaultAvatar;
        }
        else {
            access$500 = this.this$0.mAvatars.get(n - 1);
        }
        intent.putExtra("avatar_name", (Parcelable)access$500);
        this.this$0.setResult(-1, intent);
        this.this$0.finish();
    }
}
