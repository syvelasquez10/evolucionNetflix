// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.app.Status;
import android.app.AlertDialog$Builder;
import android.widget.CompoundButton$OnCheckedChangeListener;
import android.app.Dialog;
import com.netflix.mediaclient.util.SocialUtils;
import android.app.Activity;
import android.text.Html;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.mediaclient.Log;
import android.os.Parcelable;
import android.os.Bundle;
import java.util.Set;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.HashSet;
import com.netflix.mediaclient.service.user.volley.FriendForRecommendation;
import java.util.ArrayList;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class SendAsFacebookMessageDialog$3 implements DialogInterface$OnClickListener
{
    final /* synthetic */ SendAsFacebookMessageDialog this$0;
    
    SendAsFacebookMessageDialog$3(final SendAsFacebookMessageDialog this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        if (this.this$0.mDontaskCheckBox.isChecked()) {
            PreferenceUtils.putBooleanPref((Context)this.this$0.getActivity(), SendAsFacebookMessageDialog.getFacebookMsgOptInKey(this.this$0.mServiceManager), true);
        }
        this.this$0.sendMsgAndDismiss();
    }
}
