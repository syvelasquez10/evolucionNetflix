// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.annotation.SuppressLint;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
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
import com.netflix.mediaclient.Log;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import android.graphics.drawable.Drawable;
import android.widget.RatingBar$OnRatingBarChangeListener;
import android.widget.RatingBar;

public class NetflixRatingBar extends RatingBar implements RatingBar$OnRatingBarChangeListener
{
    private static final String TAG = "NetflixRatingBar";
    private int currRating;
    private boolean dispatchedCallback;
    private Drawable netflixStars;
    private NetflixRatingBar$OnNetflixRatingBarChangeListener onNetflixRatingBarChangeListener;
    private NetflixRatingBar$RatingBarDataProvider provider;
    private Ratable ratableObject;
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
    
    private void dispatchRatingChange(final boolean b) {
        if (this.onNetflixRatingBarChangeListener != null) {
            this.onNetflixRatingBarChangeListener.onRatingChanged(this, this.getRating(), b);
        }
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
        if (this.ratableObject == null) {
            return 0;
        }
        return (int)this.ratableObject.getUserRating();
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
        if (Log.isLoggable()) {
            Log.v("NetflixRatingBar", "Video ID: " + this.provider.getVideoId());
        }
        final String videoId = this.provider.getVideoId();
        final VideoType videoType = this.provider.getVideoType();
        String s = videoId;
        VideoType videoType2;
        if ((videoType2 = videoType) == VideoType.EPISODE) {
            final FalkorEpisode falkorEpisode = (FalkorEpisode)serviceManager.getBrowse().getModelProxy().getValue(PQL.create("episodes", videoId, "detail"));
            if (falkorEpisode == null) {
                Log.w("NetflixRatingBar", "Can't set rating because episode detail is not in cache for video id: " + videoId);
                return;
            }
            final String showId = falkorEpisode.getShowId();
            final VideoType show = VideoType.SHOW;
            s = showId;
            videoType2 = show;
            if (Log.isLoggable()) {
                Log.d("NetflixRatingBar", "Episode show id for setting rating: " + showId);
                videoType2 = show;
                s = showId;
            }
        }
        int trackId;
        if (this.provider.getPlayContext() != null) {
            trackId = this.provider.getPlayContext().getTrackId();
        }
        else {
            trackId = -1;
        }
        serviceManager.getBrowse().setVideoRating(s, videoType2, n, trackId, new NetflixRatingBar$SetVideoRatingCallback(this, n));
    }
    
    private void init() {
        this.netflixStars = this.tileify(this.getResources().getDrawable(this.getNetflixStarRatingDrawableId()), true);
        this.userStars = this.tileify(this.getResources().getDrawable(this.getUserStarRatingDrawableId()), true);
        super.setOnRatingBarChangeListener((RatingBar$OnRatingBarChangeListener)this);
        this.setFocusable(false);
    }
    
    private boolean isDragging() {
        Log.v("NetflixRatingBar", "Getting isDragging field");
        try {
            final Field declaredField = AbsSeekBar.class.getDeclaredField("mIsDragging");
            declaredField.setAccessible(true);
            return declaredField.getBoolean(this);
        }
        catch (NoSuchFieldException ex) {}
        catch (IllegalAccessException ex2) {
            goto Label_0030;
        }
        catch (IllegalArgumentException ex3) {
            goto Label_0030;
        }
    }
    
    private void logDraggingFieldWarning() {
        Log.w("NetflixRatingBar", "Could not read mIsDragging field");
    }
    
    private Drawable tileify(final Drawable drawable, final boolean b) {
        int level = 1;
        final int n = 0;
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
            int n2 = n;
            while (true) {
                o = layerDrawable2;
                if (n2 >= numberOfLayers) {
                    break;
                }
                layerDrawable2.setId(n2, layerDrawable.getId(n2));
                ++n2;
            }
        }
        else if (drawable instanceof StateListDrawable) {
            final StateListDrawable stateListDrawable = (StateListDrawable)drawable;
            final StateListDrawable stateListDrawable2 = new StateListDrawable();
            stateListDrawable.setLevel(0);
            Drawable drawable2 = stateListDrawable.getCurrent();
            while (true) {
                o = stateListDrawable2;
                if (drawable2 == null) {
                    break;
                }
                stateListDrawable2.addState(stateListDrawable.getState(), this.tileify(drawable2, b));
                stateListDrawable.setLevel(level);
                drawable2 = stateListDrawable.getCurrent();
                ++level;
            }
        }
        else {
            if (!(drawable instanceof BitmapDrawable)) {
                return drawable;
            }
            final Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            final ShapeDrawable shapeDrawable = new ShapeDrawable(this.getDrawableShape());
            shapeDrawable.getPaint().setShader((Shader)new BitmapShader(bitmap, Shader$TileMode.REPEAT, Shader$TileMode.CLAMP));
            if (!b) {
                return (Drawable)shapeDrawable;
            }
            o = new ClipDrawable((Drawable)shapeDrawable, 3, 1);
        }
        return (Drawable)o;
    }
    
    private void updateRatingDrawable() {
        if (Log.isLoggable()) {
            Log.v("NetflixRatingBar", "Updating rating drawable, progress: " + this.getProgress() + ", user rating: " + this.getUserRating());
        }
        if (this.getUserRating() > 0) {
            this.setProgressDrawable(this.userStars);
            this.setStepSize(1.0f);
        }
        else {
            this.setProgressDrawable(this.netflixStars);
            this.setStepSize(0.1f);
        }
        this.updateSecondaryProgress();
    }
    
    private void updateSecondaryProgress() {
        final float progressPerStar = this.getProgressPerStar();
        if (progressPerStar > 0.0f) {
            final int secondaryProgress = (int)(progressPerStar * (this.getProgress() / progressPerStar) + 0.5f);
            if (Log.isLoggable()) {
                Log.v("NetflixRatingBar", "Setting secondary progress: " + secondaryProgress);
            }
            this.setSecondaryProgress(secondaryProgress);
        }
    }
    
    protected int getNetflixStarRatingDrawableId() {
        return 2130838154;
    }
    
    protected int getUserStarRatingDrawableId() {
        return 2130838157;
    }
    
    public void onRatingChanged(final RatingBar ratingBar, final float n, final boolean b) {
        this.dispatchedCallback = true;
        final int currRating = (int)Math.ceil(n);
        if (Log.isLoggable()) {
            Log.v("NetflixRatingBar", "Rating changed: " + currRating + ", from user: " + b);
        }
        this.setContentDescription((CharSequence)String.format(this.getResources().getString(2131296450), currRating));
        if (b && this.getUserRating() != currRating) {
            final int progress = (int)(currRating * this.getProgressPerStar());
            Log.v("NetflixRatingBar", "Setting progress: " + progress);
            this.setProgress(progress);
            this.handleUserUpate(currRating);
        }
        else {
            this.currRating = currRating;
        }
        this.updateRatingDrawable();
        this.dispatchRatingChange(b);
    }
    
    @SuppressLint({ "ClickableViewAccessibility" })
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final boolean dragging = this.isDragging();
        if (Log.isLoggable()) {
            Log.v("NetflixRatingBar", "isDragging: " + dragging);
        }
        this.dispatchedCallback = false;
        final boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (!this.dispatchedCallback) {
            final NetflixActivity netflixActivitySafely = ViewUtils.getNetflixActivitySafely((View)this);
            switch (motionEvent.getAction()) {
                case 0: {
                    Log.d("NetflixRatingBar", "Report rate action started");
                    UserActionLogUtils.reportRateActionStarted((Context)netflixActivitySafely, null, netflixActivitySafely.getUiScreen());
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
    
    public void setOnNetflixRatingBarChangeListener(final NetflixRatingBar$OnNetflixRatingBarChangeListener onNetflixRatingBarChangeListener) {
        this.onNetflixRatingBarChangeListener = onNetflixRatingBarChangeListener;
    }
    
    public void setOnRatingBarChangeListener(final RatingBar$OnRatingBarChangeListener ratingBar$OnRatingBarChangeListener) {
        throw new IllegalStateException("Use setOnNetflixRatingBarChangeListener instead!");
    }
    
    public void update(final NetflixRatingBar$RatingBarDataProvider provider, final Ratable ratableObject) {
        if (Log.isLoggable()) {
            Log.v("NetflixRatingBar", "RatingBarDataProvider: " + provider + ", ratable: " + ratableObject);
            if (provider == null) {
                Log.i("NetflixRatingBar", "Data provider is null - this means user won't be able to rate this title");
            }
        }
        if (ratableObject == null) {
            return;
        }
        this.provider = provider;
        this.ratableObject = ratableObject;
        float rating;
        if (this.ratableObject.getUserRating() > 0.0f) {
            Log.v("NetflixRatingBar", "Using user rating: " + this.ratableObject.getUserRating());
            rating = this.ratableObject.getUserRating();
        }
        else {
            Log.v("NetflixRatingBar", "Using predicted rating: " + this.ratableObject.getPredictedRating());
            rating = this.ratableObject.getPredictedRating();
        }
        this.setRating(rating);
        this.updateRatingDrawable();
    }
}
