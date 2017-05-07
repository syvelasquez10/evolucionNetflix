// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.ui.RelaunchActivity;
import com.netflix.mediaclient.ui.LaunchActivity;
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
import android.view.View;
import com.netflix.mediaclient.ui.pin.PinDialogVault;
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
import com.netflix.mediaclient.ui.ServiceErrorsHandler;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import android.app.Activity;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.ui.login.LogoutActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.IMdxSharedState;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.ui.details.EpisodeRowView$EpisodeRowListener;
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
import com.netflix.mediaclient.ui.kids.NetflixKidsActionBar;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import com.netflix.mediaclient.ui.mdx.MdxReceiver;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import android.net.Uri;
import com.netflix.mediaclient.StatusCode;
import java.util.HashSet;
import android.app.Dialog;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.sothree.slidinguppanel.SlidingUpPanelLayout$PanelSlideListener;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Handler;
import android.content.BroadcastReceiver;
import java.util.Set;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.pin.PinVerifier$PinVerificationCallback;
import com.netflix.mediaclient.ui.mdx.ShowMessageDialogFrag$MessageResponseProvider;
import com.netflix.mediaclient.ui.details.EpisodeRowView$EpisodeRowListenerProvider;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.support.v7.app.ActionBarActivity;
import com.netflix.mediaclient.Log;

class NetflixActivity$10 implements Runnable
{
    final /* synthetic */ NetflixActivity this$0;
    
    NetflixActivity$10(final NetflixActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.isVisible && !this.this$0.destroyed() && this.this$0.mdxFrag != null && this.this$0.slidingPanel != null && this.this$0.netflixActionBar != null) {
            if (Log.isLoggable("NetflixActivity", 2)) {
                Log.v("NetflixActivity", "Checking to see if action bar visibility is valid.  Frag showing: " + this.this$0.mdxFrag.isShowing() + ", panel expanded: " + this.this$0.slidingPanel.isExpanded() + ", system action bar showing: " + this.this$0.netflixActionBar.isShowing());
            }
            if (this.this$0.mdxFrag.isShowing() && this.this$0.slidingPanel.isExpanded()) {
                if (this.this$0.netflixActionBar.isShowing()) {
                    Log.v("NetflixActivity", "Hiding action bar since it should not be shown");
                    this.this$0.netflixActionBar.hide(false);
                }
            }
            else if (!this.this$0.netflixActionBar.isShowing()) {
                Log.v("NetflixActivity", "Showing action bar since it should not be hidden");
                this.this$0.netflixActionBar.show(false);
            }
        }
    }
}
