// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.ui.player.subtitles.SubtitleManager;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.media.Language;
import android.content.res.Configuration;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.media.Watermark$Anchor;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.util.SubtitleUtils;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AutoResizeTextView;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.media.Watermark;
import android.support.v7.widget.Toolbar;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.os.Build;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.widget.TappableSurfaceView;
import android.view.SurfaceHolder;
import android.widget.ViewFlipper;
import android.widget.TextView;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.PopupWindow$OnDismissListener;
import com.netflix.mediaclient.android.widget.advisor.ExpiringContentAdvisor;
import com.netflix.model.leafs.advisory.Advisory$Type;
import com.netflix.mediaclient.android.widget.advisor.Advisor;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.leafs.advisory.Advisory;
import java.util.List;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class PlayScreen$1 extends SimpleManagerCallback
{
    final /* synthetic */ PlayScreen this$0;
    final /* synthetic */ Asset val$asset;
    
    PlayScreen$1(final PlayScreen this$0, final Asset val$asset) {
        this.this$0 = this$0;
        this.val$asset = val$asset;
    }
    
    @Override
    public void onAdvisoriesFetched(final List<Advisory> list, final Status status) {
        if (!status.isError()) {
            final NetflixActivity netflixActivity = this.this$0.mController.getNetflixActivity();
            if (netflixActivity != null && !netflixActivity.isFinishing()) {
                this.this$0.mIsAdvisoryDisabled = (this.val$asset.isAdvisoryDisabled() || list.isEmpty());
                for (int i = 0; i < list.size(); ++i) {
                    final Advisory advisory = list.get(i);
                    final Advisor make = Advisor.make(netflixActivity, advisory);
                    if (advisory.getType() == Advisory$Type.EXPIRY_NOTICE) {
                        ((ExpiringContentAdvisor)make).setController(this.this$0.mController);
                    }
                    if (i == list.size() - 1) {
                        make.withDismissListener((PopupWindow$OnDismissListener)new PlayScreen$1$1(this));
                    }
                    make.queue();
                }
            }
        }
    }
}
