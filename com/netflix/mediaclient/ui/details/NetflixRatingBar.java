// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import android.widget.Toast;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
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
import com.netflix.mediaclient.Log;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.ui.common.VideoDetailsProvider;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.servicemgr.VideoDetails;
import android.widget.RatingBar$OnRatingBarChangeListener;
import android.widget.RatingBar;

public class NetflixRatingBar extends RatingBar implements RatingBar$OnRatingBarChangeListener
{
    private static final String TAG = "NetflixRatingBar";
    private int currRating;
    private VideoDetails details;
    private boolean dispatchedCallback;
    private Drawable netflixStars;
    private VideoDetailsProvider provider;
    private Drawable userStars;
    
    public NetflixRatingBar(final Context context) {
        super(context);
        this.init();
    }
    
    public NetflixRatingBar(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public NetflixRatingBar(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private Shape getDrawableShape() {
        return (Shape)new RoundRectShape(new float[] { 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f }, (RectF)null, (float[])null);
    }
    
    private float getProgressPerStar() {
        float n = 1.0f;
        if (this.getNumStars() > 0) {
            n = 1.0f * this.getMax() / this.getNumStars();
        }
        return n;
    }
    
    private int getUserRating() {
        if (this.details == null) {
            return 0;
        }
        return (int)this.details.getUserRating();
    }
    
    private void handleUserUpate(final int n) {
        this.setEnabled(false);
        if (this.provider == null) {
            Log.w("NetflixRatingBar", "Can't set rating because provider is null");
            return;
        }
        final ServiceManager serviceManager = this.provider.getServiceManager();
        if (serviceManager == null) {
            Log.w("NetflixRatingBar", "Can't set rating because service man is null");
            return;
        }
        if (Log.isLoggable("NetflixRatingBar", 2)) {
            Log.v("NetflixRatingBar", "Video ID: " + this.provider.getVideoId());
        }
        final String videoId = this.provider.getVideoId();
        int trackId;
        if (this.provider.getPlayContext() != null) {
            trackId = this.provider.getPlayContext().getTrackId();
        }
        else {
            trackId = -1;
        }
        serviceManager.setVideoRating(videoId, n, trackId, new SetVideoRatingCallback(n));
    }
    
    private void init() {
        this.netflixStars = this.tileify(this.getResources().getDrawable(2130837869), true);
        this.userStars = this.tileify(this.getResources().getDrawable(2130837870), true);
        this.setOnRatingBarChangeListener((RatingBar$OnRatingBarChangeListener)this);
        final Context context = this.getContext();
        if (context instanceof VideoDetailsProvider) {
            Log.d("NetflixRatingBar", "Owner activity is provider");
            this.provider = (VideoDetailsProvider)context;
        }
        else {
            Log.d("NetflixRatingBar", "Owner activity is NOT provider " + context);
        }
        this.setFocusable(false);
    }
    
    private boolean isDragging() {
        Log.v("NetflixRatingBar", "Getting isDragging field");
        try {
            final Field declaredField = AbsSeekBar.class.getDeclaredField("mIsDragging");
            declaredField.setAccessible(true);
            return declaredField.getBoolean(this);
        }
        catch (NoSuchFieldException ex) {
            this.logDraggingFieldWarning();
        }
        catch (IllegalArgumentException ex2) {
            this.logDraggingFieldWarning();
            goto Label_0034;
        }
        catch (IllegalAccessException ex3) {
            this.logDraggingFieldWarning();
            goto Label_0034;
        }
    }
    
    private void logDraggingFieldWarning() {
        Log.w("NetflixRatingBar", "Could not read mIsDragging field");
    }
    
    private Drawable tileify(Drawable drawable, final boolean b) {
        Object o;
        if (drawable instanceof LayerDrawable) {
            final LayerDrawable layerDrawable = (LayerDrawable)drawable;
            final int numberOfLayers = layerDrawable.getNumberOfLayers();
            final Drawable[] array = new Drawable[numberOfLayers];
            for (int i = 0; i < numberOfLayers; ++i) {
                final int id = layerDrawable.getId(i);
                array[i] = this.tileify(layerDrawable.getDrawable(i), id == 16908301 || id == 16908303);
            }
            final LayerDrawable layerDrawable2 = new LayerDrawable(array);
            int n = 0;
            while (true) {
                o = layerDrawable2;
                if (n >= numberOfLayers) {
                    break;
                }
                layerDrawable2.setId(n, layerDrawable.getId(n));
                ++n;
            }
        }
        else if (drawable instanceof StateListDrawable) {
            final StateListDrawable stateListDrawable = (StateListDrawable)drawable;
            final StateListDrawable stateListDrawable2 = new StateListDrawable();
            int level = 0 + 1;
            stateListDrawable.setLevel(0);
            for (drawable = stateListDrawable.getCurrent(); drawable != null; drawable = stateListDrawable.getCurrent(), ++level) {
                stateListDrawable2.addState(stateListDrawable.getState(), this.tileify(drawable, b));
                stateListDrawable.setLevel(level);
            }
            o = stateListDrawable2;
        }
        else {
            if (drawable instanceof BitmapDrawable) {
                final Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                final ShapeDrawable shapeDrawable = new ShapeDrawable(this.getDrawableShape());
                shapeDrawable.getPaint().setShader((Shader)new BitmapShader(bitmap, Shader$TileMode.REPEAT, Shader$TileMode.CLAMP));
                Object o2 = shapeDrawable;
                if (b) {
                    o2 = new ClipDrawable((Drawable)shapeDrawable, 3, 1);
                }
                return (Drawable)o2;
            }
            return drawable;
        }
        return (Drawable)o;
    }
    
    private void updateRatingDrawable() {
        if (Log.isLoggable("NetflixRatingBar", 2)) {
            Log.v("NetflixRatingBar", "Updating rating drawable, progress: " + this.getProgress() + ", user rating: " + this.getUserRating());
        }
        if (this.getUserRating() > 0) {
            this.setProgressDrawable(this.userStars);
            this.setStepSize(1.0f);
        }
        else {
            this.setProgressDrawable(this.netflixStars);
        }
        this.updateSecondaryProgress();
    }
    
    private void updateSecondaryProgress() {
        final float progressPerStar = this.getProgressPerStar();
        if (progressPerStar > 0.0f) {
            final int secondaryProgress = (int)(this.getProgress() / progressPerStar * progressPerStar + 0.5f);
            if (Log.isLoggable("NetflixRatingBar", 2)) {
                Log.v("NetflixRatingBar", "Setting secondary progress: " + secondaryProgress);
            }
            this.setSecondaryProgress(secondaryProgress);
        }
    }
    
    public void onRatingChanged(final RatingBar ratingBar, final float n, final boolean b) {
        this.dispatchedCallback = true;
        final int currRating = (int)Math.ceil(n);
        if (Log.isLoggable("NetflixRatingBar", 2)) {
            Log.v("NetflixRatingBar", "Rating changed: " + currRating + ", from user: " + b);
        }
        this.setContentDescription((CharSequence)String.format(this.getResources().getString(2131493191), currRating));
        if (b && this.getUserRating() != currRating) {
            final int progress = (int)(currRating * this.getProgressPerStar());
            Log.v("NetflixRatingBar", "Setting progress: " + progress);
            this.setProgress(progress);
            this.handleUserUpate(currRating);
            if (this.provider != null) {
                this.provider.onActionExecuted();
            }
            else {
                Log.e("NetflixRatingBar", "Provider is NULL!");
            }
        }
        else {
            this.currRating = currRating;
        }
        this.updateRatingDrawable();
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final boolean dragging = this.isDragging();
        Log.v("NetflixRatingBar", "isDragging: " + dragging);
        this.dispatchedCallback = false;
        final boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (!this.dispatchedCallback) {
            final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
            switch (motionEvent.getAction()) {
                case 0: {
                    Log.d("NetflixRatingBar", "Report rate action started");
                    LogUtils.reportRateActionStarted((Context)netflixActivity, null, netflixActivity.getUiScreen());
                    return onTouchEvent;
                }
                case 1: {
                    this.onRatingChanged(this, this.getRating(), true);
                    return onTouchEvent;
                }
                case 3: {
                    if (dragging) {
                        this.onRatingChanged(this, this.getRating(), true);
                        return onTouchEvent;
                    }
                    break;
                }
            }
        }
        return onTouchEvent;
    }
    
    public void setDetails(final VideoDetails details) {
        final StringBuilder append = new StringBuilder().append("setting details: ");
        String title;
        if (details == null) {
            title = "n/a";
        }
        else {
            title = details.getTitle();
        }
        Log.v("NetflixRatingBar", append.append(title).toString());
        if (details == null) {
            return;
        }
        this.details = details;
        float rating;
        if (details.getUserRating() > 0.0f) {
            Log.v("NetflixRatingBar", "Using user rating: " + details.getUserRating());
            rating = details.getUserRating();
        }
        else {
            Log.v("NetflixRatingBar", "Using predicted rating: " + details.getPredictedRating());
            rating = details.getPredictedRating();
        }
        this.setRating(rating);
    }
    
    private class SetVideoRatingCallback extends LoggingManagerCallback
    {
        private final int rating;
        
        public SetVideoRatingCallback(final int rating) {
            super("NetflixRatingBar");
            this.rating = rating;
        }
        
        @Override
        public void onVideoRatingSet(final int n) {
            super.onVideoRatingSet(n);
            if (NetflixRatingBar.this.provider == null || NetflixRatingBar.this.provider.destroyed()) {
                Log.v("NetflixRatingBar", "Activity destroyed - ignoring ratings update callback");
                return;
            }
            NetflixRatingBar.this.setEnabled(true);
            if (n != 0) {
                Log.w("NetflixRatingBar", "Invalid status code");
                Toast.makeText(NetflixRatingBar.this.getContext(), 2131493199, 1).show();
                NetflixRatingBar.this.setRating((float)NetflixRatingBar.this.currRating);
                return;
            }
            Log.v("NetflixRatingBar", "Rating has been updated");
            Toast.makeText(NetflixRatingBar.this.getContext(), 2131493200, 1).show();
            NetflixRatingBar.this.currRating = this.rating;
            if (NetflixRatingBar.this.details != null) {
                NetflixRatingBar.this.details.setUserRating(this.rating);
            }
            else {
                Log.e("NetflixRatingBar", "Failed to update rating, details missing!");
            }
            NetflixRatingBar.this.updateRatingDrawable();
            NetflixRatingBar.this.setRating((float)NetflixRatingBar.this.currRating);
            Log.d("NetflixRatingBar", "Report rate action ended");
            final LogUtils.LogReportErrorArgs logReportErrorArgs = new LogUtils.LogReportErrorArgs(n, ActionOnUIError.displayedError, "", null);
            LogUtils.reportRateActionEnded(NetflixRatingBar.this.getContext(), logReportErrorArgs.getReason(), logReportErrorArgs.getError(), null, NetflixRatingBar.this.currRating);
        }
    }
}
