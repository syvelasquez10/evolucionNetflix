// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iris.notifications;

import android.os.Parcelable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.app.Activity;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.util.IrisUtils;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import org.json.JSONException;
import com.netflix.model.leafs.social.IrisNotificationsListSummary;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.ListAdapter;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import org.json.JSONObject;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import java.util.Set;
import com.netflix.mediaclient.android.widget.StaticListView;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import android.content.Context;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View$OnClickListener;

class NotificationsFrag$2 implements View$OnClickListener
{
    final /* synthetic */ NotificationsFrag this$0;
    final /* synthetic */ PlayContext val$playContext;
    final /* synthetic */ int val$position;
    final /* synthetic */ IrisNotificationSummary val$summary;
    final /* synthetic */ String val$videoId;
    final /* synthetic */ VideoType val$videoType;
    
    NotificationsFrag$2(final NotificationsFrag this$0, final String val$videoId, final PlayContext val$playContext, final VideoType val$videoType, final IrisNotificationSummary val$summary, final int val$position) {
        this.this$0 = this$0;
        this.val$videoId = val$videoId;
        this.val$playContext = val$playContext;
        this.val$videoType = val$videoType;
        this.val$summary = val$summary;
        this.val$position = val$position;
    }
    
    public void onClick(final View view) {
        this.this$0.playVideo(this.val$videoId, this.val$playContext, this.val$videoType);
        UIViewLogUtils.reportUIViewCommandStarted((Context)this.this$0.getActivity(), UIViewLogging$UIViewCommandName.startPlay, IClientLogging$ModalView.menuPanel, (DataContext)null, (String)null, this.this$0.getModelObject(this.val$summary, this.val$position));
        UIViewLogUtils.reportUIViewCommandEnded((Context)this.this$0.getActivity());
    }
}
