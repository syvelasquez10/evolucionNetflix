// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.subtitles;

import com.netflix.mediaclient.service.player.subtitles.image.ImageSubtitleMetadata;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import java.util.ArrayList;
import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;
import com.netflix.mediaclient.service.player.subtitles.image.ImageDescriptor;
import com.netflix.mediaclient.util.DeviceUtils;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import android.graphics.BitmapFactory;
import com.netflix.mediaclient.util.ViewUtils$ViewComparator;
import java.util.List;
import com.netflix.mediaclient.service.player.subtitles.image.ImageSubtitleBlock;
import android.view.View;
import android.content.Context;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import java.util.HashMap;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import android.widget.ImageView;
import java.util.Map;
import android.widget.RelativeLayout;

class ImageBasedSubtitleManager$1 implements Runnable
{
    final /* synthetic */ ImageBasedSubtitleManager this$0;
    final /* synthetic */ boolean val$visibility;
    
    ImageBasedSubtitleManager$1(final ImageBasedSubtitleManager this$0, final boolean val$visibility) {
        this.this$0 = this$0;
        this.val$visibility = val$visibility;
    }
    
    @Override
    public void run() {
        this.this$0.setVisibility(this.val$visibility);
    }
}
