// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import android.text.TextUtils$TruncateAt;
import com.netflix.mediaclient.android.widget.AutoResizeTextView;
import com.netflix.mediaclient.android.widget.StrokeTextView;
import android.view.ViewTreeObserver;
import com.netflix.mediaclient.service.player.subtitles.DoubleLength;
import com.netflix.mediaclient.service.player.subtitles.TextStyle;
import com.netflix.mediaclient.service.player.subtitles.ColorMapping;
import android.graphics.Rect;
import com.netflix.mediaclient.util.SubtitleUtils$Margins;
import java.util.Comparator;
import android.app.Activity;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import java.util.Iterator;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.service.player.subtitles.SubtitleTextNode;
import android.util.Pair;
import com.netflix.mediaclient.service.player.subtitles.HorizontalAlignment;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.service.player.subtitles.VerticalAlignment;
import com.netflix.mediaclient.util.SubtitleUtils;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.service.player.subtitles.Region;
import com.netflix.mediaclient.util.ViewUtils$ViewComparator;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.TextView;
import android.view.View;
import java.util.Map;
import com.netflix.mediaclient.service.player.subtitles.SubtitleParser;
import android.os.Handler;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.widget.RelativeLayout;
import java.util.concurrent.atomic.AtomicBoolean;
import android.widget.LinearLayout;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;

class EnhancedSubtitleManager$2 implements Runnable
{
    final /* synthetic */ EnhancedSubtitleManager this$0;
    final /* synthetic */ SubtitleBlock val$block;
    final /* synthetic */ boolean val$show;
    
    EnhancedSubtitleManager$2(final EnhancedSubtitleManager this$0, final boolean val$show, final SubtitleBlock val$block) {
        this.this$0 = this$0;
        this.val$show = val$show;
        this.val$block = val$block;
    }
    
    @Override
    public void run() {
        final boolean remove = this.this$0.mPendingActions.remove(this);
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Delayed show " + this.val$show + " for block " + this.val$block.getId());
            Log.d("nf_subtitles_render", "Removed from pending queue " + remove);
        }
        synchronized (this.this$0) {
            if (this.val$show) {
                Log.w("nf_subtitles_render", "===> showSubtitleBlock was called from pending queue!!");
                this.this$0.showSubtitleBlock(this.val$block, null);
            }
            else {
                this.this$0.removeSubtitleBlock(this.val$block);
            }
        }
    }
}
