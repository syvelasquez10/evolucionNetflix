// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.coppola.details;

import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault;
import android.support.v7.widget.RecyclerView$LayoutManager;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import android.view.KeyEvent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.content.res.Configuration;
import java.io.Serializable;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListener;
import android.app.Activity;
import android.view.Window;
import android.widget.LinearLayout$LayoutParams;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.widget.TappableSurfaceView;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.details.MovieDetailsFrag;
import android.os.Parcelable;
import android.content.Intent;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import com.netflix.mediaclient.util.CoppolaUtils;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import android.widget.ImageView;
import android.widget.FrameLayout;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import android.view.Menu;
import android.app.Fragment;
import com.netflix.mediaclient.ui.details.MovieDetailsActivity$BackStackData;
import java.util.Stack;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag$DialogCanceledListenerProvider;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class CoppolaDetailsActivity$3 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ CoppolaDetailsActivity this$0;
    final /* synthetic */ View val$fragView;
    
    CoppolaDetailsActivity$3(final CoppolaDetailsActivity this$0, final View val$fragView) {
        this.this$0 = this$0;
        this.val$fragView = val$fragView;
    }
    
    public void onGlobalLayout() {
        ViewUtils.removeGlobalLayoutListener(this.val$fragView, (ViewTreeObserver$OnGlobalLayoutListener)this);
        this.this$0.playerFragment.getView().setAlpha(0.0f);
    }
}
