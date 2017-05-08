// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.graphics.Paint;
import android.graphics.Shader$TileMode;
import android.os.Build;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import android.view.View$OnClickListener;
import android.view.View$OnTouchListener;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.graphics.BitmapFactory$Options;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;

class KongBackgroundScreen$3 implements Runnable
{
    final /* synthetic */ KongBackgroundScreen this$0;
    final /* synthetic */ BitmapDrawable val$drawable;
    
    KongBackgroundScreen$3(final KongBackgroundScreen this$0, final BitmapDrawable val$drawable) {
        this.this$0 = this$0;
        this.val$drawable = val$drawable;
    }
    
    @Override
    public void run() {
        if (this.this$0.backgroundPattern != null) {
            this.this$0.backgroundPattern.setBackground((Drawable)this.val$drawable);
        }
    }
}
