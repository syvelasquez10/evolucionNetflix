// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import android.content.Context;
import java.util.Map;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class SendThanksToSocialNotificationActionHandler extends BaseNflxHandlerWithoutDelayedActionSupport
{
    public SendThanksToSocialNotificationActionHandler(final NetflixActivity netflixActivity, final Map<String, String> map) {
        super(netflixActivity, map);
    }
    
    public static Intent getSayThanksIntent(final Context context, final String s, final String s2, final boolean b, final MessageData messageData) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.NOTIFICATION_SAY_THANKS");
        intent.putExtra("g", s);
        intent.putExtra("story_id", s2);
        intent.putExtra("close_system_dialogs_needed", b);
        MessageData.addMessageDataToIntent(intent, messageData);
        return intent;
    }
    
    @Override
    public Response handle() {
        final String s = this.mParamsMap.get("g");
        final String s2 = this.mParamsMap.get("story_id");
        if (StringUtils.isEmpty(s) || StringUtils.isEmpty(s2)) {
            Log.e("NflxHandler", "Could not find ID or Story ID of social notification");
            return Response.NOT_HANDLING;
        }
        this.mActivity.startService(getSayThanksIntent((Context)this.mActivity, s, s2, false, null));
        return Response.HANDLING;
    }
}
