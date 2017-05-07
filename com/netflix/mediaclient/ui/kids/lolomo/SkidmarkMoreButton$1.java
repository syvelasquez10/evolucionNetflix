// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.content.Context;
import com.netflix.mediaclient.android.widget.PressedStateHandler;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import java.util.List;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.annotation.SuppressLint;
import android.widget.FrameLayout;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.view.View$OnClickListener;

class SkidmarkMoreButton$1 implements View$OnClickListener
{
    final /* synthetic */ SkidmarkMoreButton this$0;
    
    SkidmarkMoreButton$1(final SkidmarkMoreButton this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (this.this$0.lomo == null) {
            Log.w("SkidmarkMoreButton", "No lomo available!");
            return;
        }
        this.this$0.launchAndLogKidsDetailsActivity();
    }
}
