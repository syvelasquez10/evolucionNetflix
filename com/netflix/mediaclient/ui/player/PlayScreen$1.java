// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.media.Language;
import com.netflix.mediaclient.ui.mdx.MdxTargetSelection;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.os.Build;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.widget.TappableSurfaceView;
import android.view.SurfaceHolder;
import android.widget.ViewFlipper;
import android.widget.TextView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.netflix.mediaclient.ui.Screen;
import com.netflix.mediaclient.Log;

class PlayScreen$1 implements Runnable
{
    final /* synthetic */ PlayScreen this$0;
    
    PlayScreen$1(final PlayScreen this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.mState == PlayerUiState.Playing) {
            Log.d("screen", "AUDIO:: sound bar hide");
            final TopPanel mTopPanel = this.this$0.mTopPanel;
            if (mTopPanel != null) {
                mTopPanel.hideSoundSection();
            }
            return;
        }
        Log.d("screen", "AUDIO:: not in loaded state anymore! Ignore.");
    }
}
