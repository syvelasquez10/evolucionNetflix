// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.app.Status;
import android.widget.Button;
import com.netflix.mediaclient.util.SocialNotificationsUtils;
import android.content.DialogInterface;
import android.app.Activity;
import android.text.Html;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import android.os.Parcelable;
import android.os.Bundle;
import java.util.Set;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.HashSet;
import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import java.util.ArrayList;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.view.View;
import android.view.View$OnClickListener;

class SendAsFacebookMessageDialog$1 implements View$OnClickListener
{
    final /* synthetic */ SendAsFacebookMessageDialog this$0;
    
    SendAsFacebookMessageDialog$1(final SendAsFacebookMessageDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        PreferenceUtils.putBooleanPref((Context)this.this$0.getActivity(), SendAsFacebookMessageDialog.getFacebookMsgOptInKey(this.this$0.mServiceManager), true);
        this.this$0.sendMsgAndDismiss();
    }
}
