// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.servicemgr.model.HdEnabledProvider;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup$DetailsStringProvider;
import com.netflix.mediaclient.servicemgr.model.details.EvidenceDetails;
import android.content.res.Resources;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.KubrickVideo;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.IconFontTextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import android.widget.Toast;
import android.view.View;
import android.view.View$OnClickListener;

class KubrickVideoDetailsViewGroup$1 implements View$OnClickListener
{
    final /* synthetic */ KubrickVideoDetailsViewGroup this$0;
    
    KubrickVideoDetailsViewGroup$1(final KubrickVideoDetailsViewGroup this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        Toast.makeText(this.this$0.rate.getContext(), (CharSequence)"NOT IMPLEMENTED", 0).show();
    }
}
