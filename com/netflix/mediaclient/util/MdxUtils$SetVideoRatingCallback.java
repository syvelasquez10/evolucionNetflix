// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.UserRating;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

public class MdxUtils$SetVideoRatingCallback extends LoggingManagerCallback
{
    private final NetflixActivity activity;
    private final float rating;
    
    public MdxUtils$SetVideoRatingCallback(final NetflixActivity activity, final float rating) {
        super("MdxUtils");
        this.activity = activity;
        this.rating = rating;
    }
    
    @Override
    public void onVideoRatingSet(final UserRating userRating, final Status status) {
        super.onVideoRatingSet(userRating, status);
        if (this.activity.destroyed()) {
            return;
        }
        if (status.isError()) {
            Log.w("MdxUtils", "Invalid status code failed");
            Toast.makeText((Context)this.activity, 2131493178, 1).show();
            Log.d("MdxUtils", "Report rate action ended");
            UserActionLogUtils.reportRateActionEnded((Context)this.activity, IClientLogging$CompletionReason.failed, ConsolidatedLoggingUtils.createUIError(status, this.activity.getString(2131493178), ActionOnUIError.displayedError), null, (int)this.rating);
            return;
        }
        Log.v("MdxUtils", "Rating has been updated ok");
        Toast.makeText((Context)this.activity, 2131493179, 1).show();
        UserActionLogUtils.reportRateActionEnded((Context)this.activity, IClientLogging$CompletionReason.success, null, null, (int)this.rating);
    }
}
