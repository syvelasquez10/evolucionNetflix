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
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.media.Watermark$Anchor;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.util.SubtitleUtils;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AutoResizeTextView;
import com.netflix.mediaclient.media.Watermark;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import android.support.v7.widget.Toolbar;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.os.Build;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.TappableSurfaceView;
import android.view.SurfaceHolder;
import android.os.Handler;
import android.widget.ViewFlipper;
import android.widget.TextView;
import android.view.View;
import android.animation.Animator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

class PlayScreen$1 implements Runnable
{
    final /* synthetic */ PlayScreen this$0;
    
    PlayScreen$1(final PlayScreen this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.mContentAdvisory == null) {
            return;
        }
        this.this$0.mContentAdvisory.show(true);
        this.this$0.mHandler.postDelayed(this.this$0.contentAdvisoryNoticeCompletionRunnable, (long)this.this$0.mContentAdvisory.getDisplayDuration());
    }
}
