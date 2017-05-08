// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.annotation.SuppressLint;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.MotionEvent;
import android.graphics.Bitmap;
import android.graphics.drawable.ClipDrawable;
import android.graphics.Shader;
import android.graphics.BitmapShader;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.LayerDrawable;
import java.lang.reflect.Field;
import android.widget.AbsSeekBar;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.falkor.PQL;
import com.netflix.model.branches.FalkorEpisode;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import android.graphics.drawable.Drawable;
import android.widget.RatingBar$OnRatingBarChangeListener;
import android.widget.RatingBar;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import android.widget.Toast;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.UserRating;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class NetflixRatingBar$SetVideoRatingCallback extends LoggingManagerCallback
{
    private final int rating;
    final /* synthetic */ NetflixRatingBar this$0;
    
    public NetflixRatingBar$SetVideoRatingCallback(final NetflixRatingBar this$0, final int rating) {
        this.this$0 = this$0;
        super("NetflixRatingBar");
        this.rating = rating;
    }
    
    @Override
    public void onVideoRatingSet(final UserRating userRating, final Status status) {
        super.onVideoRatingSet(userRating, status);
        if (this.this$0.provider == null || this.this$0.provider.destroyed()) {
            Log.v("NetflixRatingBar", "Activity destroyed - ignoring ratings update callback");
            return;
        }
        this.this$0.setEnabled(true);
        if (status.isError()) {
            Log.w("NetflixRatingBar", "Invalid status code");
            Toast.makeText(this.this$0.getContext(), 2131231099, 1).show();
            this.this$0.setRating((float)this.this$0.currRating);
            UserActionLogUtils.reportRateActionEnded(this.this$0.getContext(), IClientLogging$CompletionReason.failed, ConsolidatedLoggingUtils.createUIError(status, this.this$0.getContext().getString(2131231099), ActionOnUIError.displayedError), null, this.this$0.currRating);
            return;
        }
        Log.v("NetflixRatingBar", "Rating has been updated");
        Toast.makeText(this.this$0.getContext(), 2131231214, 1).show();
        this.this$0.currRating = this.rating;
        if (this.this$0.ratableObject != null) {
            this.this$0.ratableObject.setUserRating(this.rating);
        }
        else {
            Log.e("NetflixRatingBar", "Failed to update rating, ratableObject missing!");
        }
        this.this$0.updateRatingDrawable();
        this.this$0.setRating((float)this.this$0.currRating);
        this.this$0.dispatchSystemUiVisibilityChanged(0);
        Log.d("NetflixRatingBar", "Report rate action ended");
        UserActionLogUtils.reportRateActionEnded(this.this$0.getContext(), IClientLogging$CompletionReason.success, null, null, this.this$0.currRating);
    }
}
