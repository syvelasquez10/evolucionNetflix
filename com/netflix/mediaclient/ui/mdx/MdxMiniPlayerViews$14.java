// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.animation.TimeInterpolator;
import com.netflix.mediaclient.util.gfx.AnimationUtils$HideViewOnAnimatorEnd;
import java.util.Arrays;
import java.util.Iterator;
import android.animation.Animator$AnimatorListener;
import android.view.LayoutInflater;
import android.content.res.Resources;
import java.util.ArrayList;
import java.util.Collection;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.netflix.mediaclient.android.widget.SnappableSeekBar;
import com.netflix.mediaclient.android.widget.SnappableSeekBar$OnSnappableSeekBarChangeListener;
import com.netflix.mediaclient.android.widget.IconFontTextView;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import android.view.ViewGroup;
import java.util.List;
import android.widget.TextView;
import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.animation.Interpolator;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.TimeFormatterHelper;
import android.graphics.Rect;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class MdxMiniPlayerViews$14 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ MdxMiniPlayerViews this$0;
    
    MdxMiniPlayerViews$14(final MdxMiniPlayerViews this$0) {
        this.this$0 = this$0;
    }
    
    public void onGlobalLayout() {
        if (this.this$0.seekBar.getHeight() <= 0) {
            return;
        }
        final Rect bounds = this.this$0.seekBar.getCachedThumb().getBounds();
        final int thumbOffset = (bounds.right - bounds.left) / 2;
        Log.v("MdxMiniPlayerViews", "Setting thumb offset: " + thumbOffset);
        this.this$0.seekBar.setThumbOffset(thumbOffset);
        ViewUtils.removeGlobalLayoutListener((View)this.this$0.seekBar, (ViewTreeObserver$OnGlobalLayoutListener)this);
    }
}
