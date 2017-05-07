// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelectionDialog;
import com.netflix.mediaclient.ui.launch.RelaunchActivity;
import com.netflix.mediaclient.ui.launch.LaunchActivity;
import com.netflix.mediaclient.util.WebApiUtils$VideoIds;
import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.content.IntentFilter;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault;
import android.view.MenuItem;
import com.netflix.mediaclient.util.DeviceUtils;
import java.util.Iterator;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.ui.settings.SettingsActivity;
import com.netflix.mediaclient.ui.common.DebugMenuItems;
import com.netflix.mediaclient.util.ViewUtils;
import android.os.Bundle;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import android.app.Activity;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.ui.login.LogoutActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IMdxSharedState;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import android.app.DialogFragment;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import java.io.Serializable;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.UpdateDialog;
import com.netflix.mediaclient.android.widget.UpdateDialog$Builder;
import android.app.AlertDialog;
import android.app.AlertDialog$Builder;
import android.view.MotionEvent;
import com.netflix.mediaclient.util.MdxUtils;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.ui.kubrick_kids.KubrickKidsActionBar;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import com.netflix.mediaclient.ui.mdx.MdxReceiver;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import android.content.Intent;
import android.net.Uri;
import com.netflix.mediaclient.StatusCode;
import java.util.HashSet;
import android.app.Dialog;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Handler;
import android.content.BroadcastReceiver;
import java.util.Set;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifier$PlayVerifyCallback;
import com.netflix.mediaclient.ui.mdx.ShowMessageDialogFrag$MessageResponseProvider;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListenerProvider;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.support.v7.app.ActionBarActivity;
import com.netflix.mediaclient.Log;
import android.view.View;
import com.sothree.slidinguppanel.SlidingUpPanelLayout$PanelSlideListener;

class NetflixActivity$9 implements SlidingUpPanelLayout$PanelSlideListener
{
    final /* synthetic */ NetflixActivity this$0;
    
    NetflixActivity$9(final NetflixActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onPanelAnchored(final View view) {
        Log.v("NetflixActivity", "onPanelAnchored");
    }
    
    @Override
    public void onPanelCollapsed(final View view) {
        Log.v("NetflixActivity", "onPanelCollapsed");
        this.this$0.onSlidingPanelCollapsed(view);
        if (this.this$0.mdxFrag != null) {
            this.this$0.mdxFrag.onPanelCollapsed();
        }
    }
    
    @Override
    public void onPanelExpanded(final View view) {
        Log.v("NetflixActivity", "onPanelExpanded");
        this.this$0.onSlidingPanelExpanded(view);
        if (this.this$0.mdxFrag != null) {
            this.this$0.mdxFrag.onPanelExpanded();
        }
    }
    
    @Override
    public void onPanelSlide(final View view, final float n) {
        if (Log.isLoggable()) {
            Log.v("NetflixActivity", "onPanelSlide, offset: " + n);
        }
        if (this.this$0.mdxFrag != null) {
            this.this$0.mdxFrag.onPanelSlide(n);
        }
        if (this.this$0.netflixActionBar != null) {
            float n3;
            final float n2 = n3 = 0.3f;
            if (this.this$0.actionBarHeight > 0) {
                n3 = n2;
                if (view != null) {
                    n3 = n2;
                    if (view.getMeasuredHeight() > 0) {
                        n3 = this.this$0.actionBarHeight / view.getMeasuredHeight();
                    }
                }
            }
            if (n <= n3) {
                if (this.this$0.netflixActionBar.isShowing()) {
                    this.this$0.netflixActionBar.hide(true);
                }
            }
            else if (!this.this$0.netflixActionBar.isShowing()) {
                this.this$0.netflixActionBar.show(true);
            }
        }
    }
}
