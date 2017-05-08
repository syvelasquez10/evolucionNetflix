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
import com.netflix.mediaclient.servicemgr.IPlayer$PlaybackType;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.media.Language;
import android.content.res.Configuration;
import com.netflix.mediaclient.android.widget.advisor.Advisor;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.Asset;
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
import android.widget.PopupWindow$OnDismissListener;

class PlayScreen$1$1 implements PopupWindow$OnDismissListener
{
    final /* synthetic */ PlayScreen$1 this$1;
    
    PlayScreen$1$1(final PlayScreen$1 this$1) {
        this.this$1 = this$1;
    }
    
    public void onDismiss() {
        this.this$1.this$0.mIsAdvisoryDisabled = true;
        if (this.this$1.this$0.mTopPanel != null) {
            this.this$1.this$0.mTopPanel.setGradientVisibility(false);
        }
    }
}
