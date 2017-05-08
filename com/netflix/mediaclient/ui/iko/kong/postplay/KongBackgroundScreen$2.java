// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.graphics.Paint;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import android.view.View$OnTouchListener;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.graphics.BitmapFactory$Options;
import android.widget.ImageView;
import android.view.View;
import android.view.View$OnClickListener;

class KongBackgroundScreen$2 implements View$OnClickListener
{
    final /* synthetic */ KongBackgroundScreen this$0;
    
    KongBackgroundScreen$2(final KongBackgroundScreen this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (this.this$0.playerFragment != null) {
            this.this$0.playerFragment.setUserInteraction();
        }
        if (this.this$0.postPlayManager.isInGearSelection() && this.this$0.postPlayManager.postPlayState != KongInteractivePostPlayManager$POST_PLAY_STATE.BATTLE) {
            this.this$0.postPlayManager.cancelCurrentAnimation();
            if (this.this$0.handler != null) {
                this.this$0.handler.removeCallbacksAndMessages((Object)null);
            }
            this.this$0.postPlayManager.showBattleIntro();
        }
        else if (this.this$0.kongPostPlayDismissed || this.this$0.postPlayManager.waitUntilEndOfPlay() || this.this$0.postPlayManager.isEndOfPlayPostPlay()) {
            if (this.this$0.playerFragment != null) {
                this.this$0.postPlayManager.cleanup();
                this.this$0.playerFragment.cleanupAndExit();
            }
        }
        else {
            this.this$0.kongPostPlayDismissed = true;
            this.this$0.hide();
            this.this$0.postPlayManager.cleanup();
            if (this.this$0.playerFragment != null) {
                this.this$0.playerFragment.doUnpause();
                this.this$0.playerFragment.showControlScreenOverlay(true);
            }
        }
    }
}
