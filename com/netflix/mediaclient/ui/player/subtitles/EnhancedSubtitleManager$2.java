// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.subtitles;

import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import android.text.TextUtils$TruncateAt;
import com.netflix.mediaclient.android.widget.AutoResizeTextView;
import com.netflix.mediaclient.android.widget.StrokeTextView;
import android.view.ViewTreeObserver;
import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;
import com.netflix.mediaclient.service.player.subtitles.DoubleLength;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.service.player.subtitles.text.ColorMapping;
import com.netflix.mediaclient.service.player.subtitles.text.TextSubtitleParser;
import android.graphics.Rect;
import com.netflix.mediaclient.util.SubtitleUtils$Margins;
import java.util.ArrayList;
import java.util.Iterator;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.service.player.subtitles.text.SubtitleTextNode;
import android.util.Pair;
import com.netflix.mediaclient.service.player.subtitles.text.HorizontalAlignment;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.service.player.subtitles.text.VerticalAlignment;
import com.netflix.mediaclient.util.SubtitleUtils;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.service.player.subtitles.text.Region;
import com.netflix.mediaclient.util.ViewUtils$ViewComparator;
import java.util.HashMap;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import android.widget.TextView;
import android.widget.RelativeLayout;
import java.util.Map;
import android.view.ViewTreeObserver$OnPreDrawListener;
import java.util.concurrent.atomic.AtomicBoolean;
import android.widget.LinearLayout;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.subtitles.text.TextSubtitleBlock;

class EnhancedSubtitleManager$2 implements Runnable
{
    final /* synthetic */ EnhancedSubtitleManager this$0;
    final /* synthetic */ TextSubtitleBlock val$block;
    final /* synthetic */ boolean val$show;
    
    EnhancedSubtitleManager$2(final EnhancedSubtitleManager this$0, final boolean val$show, final TextSubtitleBlock val$block) {
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
