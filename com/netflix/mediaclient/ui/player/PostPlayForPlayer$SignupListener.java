// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.content.Intent;
import com.netflix.mediaclient.Log;
import android.view.View;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;

class PostPlayForPlayer$SignupListener implements View$OnClickListener
{
    private static final String GET_STARTED_URL = "/getstarted";
    private static final int PLAYER_COMPLETE = 21;
    NetflixActivity netflixActivity;
    final /* synthetic */ PostPlayForPlayer this$0;
    
    public PostPlayForPlayer$SignupListener(final PostPlayForPlayer this$0, final NetflixActivity netflixActivity) {
        this.this$0 = this$0;
        this.netflixActivity = netflixActivity;
    }
    
    public void onClick(final View view) {
        if (Log.isLoggable()) {
            Log.d("nf_postplay", "SignUp touched");
        }
        final Intent intent = new Intent();
        intent.putExtra("nextUrl", "/getstarted");
        this.netflixActivity.setResult(21, intent);
        this.netflixActivity.finish();
    }
}
