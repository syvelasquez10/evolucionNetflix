// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications;

import android.os.Parcelable;
import android.widget.ListAdapter;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.SocialUtils;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import com.netflix.model.leafs.social.SocialNotificationsListSummary;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import java.util.Iterator;
import android.content.Context;
import com.netflix.mediaclient.util.log.SocialLoggingUtils;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.ui.social.notifications.types.SocialNotification;
import com.netflix.mediaclient.android.app.Status;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import java.util.Set;
import android.widget.ListView;
import com.netflix.mediaclient.servicemgr.interface_.search.SocialNotificationsList;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;

class SocialNotificationsFrag$2 implements View$OnClickListener
{
    final /* synthetic */ SocialNotificationsFrag this$0;
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ PlayContext val$playContext;
    final /* synthetic */ String val$videoId;
    final /* synthetic */ VideoType val$videoType;
    
    SocialNotificationsFrag$2(final SocialNotificationsFrag this$0, final NetflixActivity val$activity, final String val$videoId, final PlayContext val$playContext, final VideoType val$videoType) {
        this.this$0 = this$0;
        this.val$activity = val$activity;
        this.val$videoId = val$videoId;
        this.val$playContext = val$playContext;
        this.val$videoType = val$videoType;
    }
    
    public void onClick(final View view) {
        this.this$0.playVideo(this.val$activity, this.val$videoId, this.val$playContext, this.val$videoType);
    }
}
