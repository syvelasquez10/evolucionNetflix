// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableUiList;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnLongClickListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import java.util.Iterator;
import android.content.res.TypedArray;
import com.netflix.mediaclient.R$styleable;
import android.support.v4.graphics.drawable.DrawableCompat;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.util.AttributeSet;
import android.content.Context;
import java.util.ArrayList;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import java.util.List;
import android.widget.LinearLayout;
import com.netflix.mediaclient.Log;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

class DownloadButton$2 implements Animation$AnimationListener
{
    final /* synthetic */ DownloadButton this$0;
    
    DownloadButton$2(final DownloadButton this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animation animation) {
        Log.i("download_button", "onAnimationEnd");
        if (this.this$0.getState() != DownloadButton$ButtonState.ERROR) {
            this.this$0.setState(DownloadButton$ButtonState.QUEUED, this.this$0.playableId);
        }
        this.this$0.progressBar.clearAnimation();
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}
