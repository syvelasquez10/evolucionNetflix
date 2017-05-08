// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker.details;

import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.util.AndroidUtils;
import android.graphics.Bitmap$Config;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.ui.details.AbsEpisodeView;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup$DetailsStringProvider;
import android.widget.RelativeLayout$LayoutParams;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.ui.barker.BarkerUtils;
import com.netflix.mediaclient.util.TimeUtils;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.FeatureEnabledProvider;
import com.netflix.mediaclient.util.DeviceUtils;
import android.app.FragmentTransaction;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.app.Fragment;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.RatingDialogFrag;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import com.netflix.mediaclient.util.ViewUtils;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.IconFontTextView;
import com.netflix.mediaclient.ui.offline.DownloadButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.ProgressBar;
import android.view.ViewGroup;
import android.widget.TextView;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import android.view.View;
import android.view.View$OnClickListener;

class BarkerVideoDetailsViewGroup$1 implements View$OnClickListener
{
    final /* synthetic */ BarkerVideoDetailsViewGroup this$0;
    
    BarkerVideoDetailsViewGroup$1(final BarkerVideoDetailsViewGroup this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.showRatingDialog();
    }
}
