// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import java.util.Collection;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class SearchActivity$VoiceSearchSupportReceiver extends BroadcastReceiver
{
    public void onReceive(final Context context, final Intent intent) {
        Log.d("SearchActivity", "VoiceSearchSupportReceiver, result: " + this.getResultCode());
        Log.d("SearchActivity", intent);
        if (this.getResultCode() != -1) {
            return;
        }
        Log.d("SearchActivity", "Voice search supported langs: " + StringUtils.createStringFromCollection((Collection<Object>)this.getResultExtras(true).getStringArrayList("android.speech.extra.SUPPORTED_LANGUAGES")));
    }
}
