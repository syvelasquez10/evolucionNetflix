// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.os.Parcelable;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.content.Context;

public class NonMemberPlayerActivity extends PlayerActivity
{
    private static final String TAG = "NonMemberPlayerActivity";
    
    public static Intent createColdStartIntent(final Context context, final String s, final VideoType videoType, final PlayContext playContext) {
        if (Log.isLoggable()) {
            Log.d("NonMemberPlayerActivity", "Performing 'cold start' - activity itself will get details for videoId: " + s);
        }
        final Intent intent = new Intent(context, (Class)NonMemberPlayerActivity.class);
        intent.addFlags(131072);
        intent.putExtra("extra_get_details_video_id", s);
        intent.putExtra("extra_get_details_video_type", videoType.getValue());
        intent.putExtra("extra_get_details_play_context", (Parcelable)playContext);
        return intent;
    }
}
