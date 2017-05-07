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
import android.view.View;
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
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import com.netflix.mediaclient.ui.mdx.MdxReceiver;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
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
import java.util.Set;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifier$PlayVerifyCallback;
import com.netflix.mediaclient.ui.mdx.ShowMessageDialogFrag$MessageResponseProvider;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListenerProvider;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.support.v7.app.ActionBarActivity;
import android.net.Uri;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.util.LaunchBrowser;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class NetflixActivity$13 extends BroadcastReceiver
{
    final /* synthetic */ NetflixActivity this$0;
    
    NetflixActivity$13(final NetflixActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (this.this$0.isVisible) {
            Log.d("NetflixActivity", "Display error dialog");
            final StatusCode statusCodeByValue = StatusCode.getStatusCodeByValue(intent.getIntExtra("status", Integer.MIN_VALUE));
            final int intExtra = intent.getIntExtra("message_id", Integer.MAX_VALUE);
            final String stringExtra = intent.getStringExtra("url");
            if (statusCodeByValue == null) {
                Log.e("NetflixActivity", "Required to display error dialog without status code!");
            }
            else if (Log.isLoggable()) {
                Log.d("NetflixActivity", "Required to display error dialog with status code " + statusCodeByValue.name() + ", " + statusCodeByValue.getValue());
            }
            final String access$1100 = this.this$0.getMessage(intExtra, statusCodeByValue);
            final Uri access$1101 = this.this$0.getUri(stringExtra);
            final NetflixActivity$13$1 netflixActivity$13$1 = new NetflixActivity$13$1(this);
            AlertDialogFactory$AlertDialogDescriptor alertDialogFactory$AlertDialogDescriptor;
            if (access$1101 != null) {
                alertDialogFactory$AlertDialogDescriptor = new AlertDialogFactory$TwoButtonAlertDialogDescriptor("", access$1100, null, netflixActivity$13$1, this.this$0.getString(2131493354), new LaunchBrowser((Context)this.this$0, access$1101));
            }
            else {
                alertDialogFactory$AlertDialogDescriptor = new AlertDialogFactory$AlertDialogDescriptor("", access$1100, null, netflixActivity$13$1);
            }
            this.this$0.displayDialog(AlertDialogFactory.createDialog((Context)this.this$0, this.this$0.handler, alertDialogFactory$AlertDialogDescriptor));
            return;
        }
        Log.d("NetflixActivity", "Not visible, can not display error dialog");
    }
}
