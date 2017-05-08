// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelectionDialog;
import com.netflix.mediaclient.ui.launch.RelaunchActivity;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.widget.Toast;
import com.netflix.mediaclient.util.WebApiUtils$VideoIds;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import android.app.FragmentManager;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import com.netflix.mediaclient.ui.barker.BarkerUtils;
import android.app.FragmentTransaction;
import android.app.Fragment;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.verifyplay.PlayVerifierVault;
import java.util.Iterator;
import com.netflix.mediaclient.android.widget.advisor.Advisor;
import com.netflix.mediaclient.android.debug.DebugMenuItems;
import android.view.Menu;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.os.Bundle;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.ui.mdx.CastPlayerControlsFrag;
import com.netflix.mediaclient.ui.signup.SignupActivity;
import com.netflix.mediaclient.ui.launch.LaunchActivity;
import android.app.Activity;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.login.LogoutActivity;
import com.netflix.mediaclient.servicemgr.IMdxSharedState;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import android.app.DialogFragment;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import java.io.Serializable;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog$Builder;
import android.view.MotionEvent;
import com.netflix.mediaclient.util.MdxUtils;
import android.view.KeyEvent;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.ui.barker_kids.BarkerKidsActionBar;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.android.widget.UpdateDialog;
import com.netflix.mediaclient.android.widget.UpdateDialog$Builder;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.servicemgr.IErrorHandler;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.mdx.MdxReceiver;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import android.support.design.widget.CoordinatorLayout$LayoutParams;
import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.voip.ContactUsActivity;
import android.content.Intent;
import java.util.HashSet;
import android.app.Dialog;
import com.netflix.mediaclient.ui.offline.TutorialHelper;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.squareup.seismic.ShakeDetector;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.sothree.slidinguppanel.SlidingUpPanelLayout$PanelSlideListener;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import java.util.LinkedList;
import android.widget.RelativeLayout;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import android.view.MenuItem;
import android.support.design.widget.CoordinatorLayout;
import java.util.concurrent.atomic.AtomicLong;
import android.app.Application$ActivityLifecycleCallbacks;
import android.support.design.widget.FloatingActionButton;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Handler;
import com.netflix.mediaclient.ui.mdx.ICastPlayerFrag;
import android.content.BroadcastReceiver;
import java.util.Set;
import com.netflix.mediaclient.ui.offline.ActivityPageOfflineAgentListener;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.verifyplay.PinAndAgeVerifier$PinAndAgeVerifyCallback;
import com.netflix.mediaclient.ui.mdx.ShowMessageDialogFrag$MessageResponseProvider;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListenerProvider;
import com.netflix.mediaclient.android.app.LoadingStatus;
import android.support.v7.app.AppCompatActivity;
import android.widget.PopupMenu;
import android.widget.PopupMenu$OnDismissListener;

class NetflixActivity$12 implements PopupMenu$OnDismissListener
{
    final /* synthetic */ NetflixActivity this$0;
    
    NetflixActivity$12(final NetflixActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onDismiss(final PopupMenu popupMenu) {
        this.this$0.mShownPopupMenus.remove(popupMenu);
    }
}
