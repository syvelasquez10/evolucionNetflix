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
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.TappableSurfaceView;
import android.view.SurfaceHolder;
import android.os.Handler;
import android.widget.ViewFlipper;
import android.animation.Animator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.netflix.mediaclient.servicemgr.Asset;
import java.util.Date;
import junit.framework.Assert;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.ExpiringContentAction;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.interface_.IExpiringContentWarning;
import android.view.View;
import android.widget.TextView;

class PlayScreen$ExpiringContent
{
    private TextView dismissButton;
    private boolean hasShown;
    private View layoutContainer;
    final /* synthetic */ PlayScreen this$0;
    private TextView warningText;
    
    PlayScreen$ExpiringContent(final PlayScreen this$0) {
        this.this$0 = this$0;
        this.findViews();
    }
    
    private void dismiss() {
        this.hideWarning();
        if (isBrowseValid(this.this$0.mController)) {
            this.this$0.mController.getServiceManager().getBrowse().expiringContent(this.this$0.mController.getCurrentAsset().getPlayableId(), new LoggingManagerCallback("screen"), ExpiringContentAction.NEVER_SHOW_NOTICE_AGAIN);
        }
    }
    
    private void findViews() {
        this.dismissButton = (TextView)this.this$0.mController.getView().findViewById(2131689830);
        this.warningText = (TextView)this.this$0.mController.getView().findViewById(2131689829);
        this.layoutContainer = this.this$0.mController.getView().findViewById(2131689828);
        if (this.layoutContainer != null) {
            LocalizationUtils.setLayoutDirection(this.layoutContainer);
        }
    }
    
    private void hideWarning() {
        if (this.layoutContainer != null) {
            this.layoutContainer.setVisibility(8);
        }
    }
    
    private void setWarningText(final IExpiringContentWarning expiringContentWarning) {
        int n = 0;
        switch (PlayScreen$3.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$ExpiringContentType[expiringContentWarning.getWarningType().ordinal()]) {
            default: {
                n = 2131230941;
                break;
            }
            case 1: {
                n = 2131230943;
                break;
            }
            case 2: {
                n = 2131230944;
                break;
            }
            case 3: {
                n = 2131230942;
                break;
            }
        }
        this.warningText.setText((CharSequence)this.warningText.getResources().getString(n, new Object[] { expiringContentWarning.getLocalizedDate() }));
    }
    
    private void showWarning(final IExpiringContentWarning warningText) {
        this.layoutContainer.setVisibility(0);
        this.setWarningText(warningText);
        this.dismissButton.setOnClickListener((View$OnClickListener)new PlayScreen$ExpiringContent$2(this));
        this.hasShown = true;
        if (isBrowseValid(this.this$0.mController)) {
            this.this$0.mController.getServiceManager().getBrowse().expiringContent(this.this$0.mController.getCurrentAsset().getPlayableId(), new LoggingManagerCallback("screen"), ExpiringContentAction.LOG_WHEN_NOTICE_SHOWN);
        }
    }
    
    private void tryShowWarning() {
        Assert.assertNotNull((Object)this.layoutContainer);
        Assert.assertNotNull((Object)this.dismissButton);
        Assert.assertNotNull((Object)this.warningText);
        final Asset currentAsset = this.this$0.mController.getCurrentAsset();
        if (!this.hasShown && currentAsset != null && currentAsset.isEpisode() && currentAsset.getExpirationTime() <= 2592000000L + new Date().getTime() && isBrowseValid(this.this$0.mController)) {
            this.this$0.mController.getServiceManager().getBrowse().expiringContent(this.this$0.mController.getCurrentAsset().getPlayableId(), new PlayScreen$ExpiringContent$1(this, "screen"), ExpiringContentAction.SHOULD_SHOW_NOTICE);
        }
    }
}
