// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.internal.Utility;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View$MeasureSpec;
import android.graphics.BitmapFactory;
import java.net.MalformedURLException;
import com.facebook.FacebookException;
import com.facebook.internal.Logger;
import com.facebook.LoggingBehavior;
import android.content.res.TypedArray;
import android.view.View;
import android.widget.ImageView$ScaleType;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.facebook.android.R;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.FrameLayout;

public class ProfilePictureView extends FrameLayout
{
    private static final String BITMAP_HEIGHT_KEY = "ProfilePictureView_height";
    private static final String BITMAP_KEY = "ProfilePictureView_bitmap";
    private static final String BITMAP_WIDTH_KEY = "ProfilePictureView_width";
    public static final int CUSTOM = -1;
    private static final boolean IS_CROPPED_DEFAULT_VALUE = true;
    private static final String IS_CROPPED_KEY = "ProfilePictureView_isCropped";
    public static final int LARGE = -4;
    private static final int MIN_SIZE = 1;
    public static final int NORMAL = -3;
    private static final String PENDING_REFRESH_KEY = "ProfilePictureView_refresh";
    private static final String PRESET_SIZE_KEY = "ProfilePictureView_presetSize";
    private static final String PROFILE_ID_KEY = "ProfilePictureView_profileId";
    public static final int SMALL = -2;
    private static final String SUPER_STATE_KEY = "ProfilePictureView_superState";
    public static final String TAG;
    private ImageView image;
    private Bitmap imageContents;
    private boolean isCropped;
    private ImageRequest lastRequest;
    private OnErrorListener onErrorListener;
    private int presetSizeType;
    private String profileId;
    private int queryHeight;
    private int queryWidth;
    
    static {
        TAG = ProfilePictureView.class.getSimpleName();
    }
    
    public ProfilePictureView(final Context context) {
        super(context);
        this.queryHeight = 0;
        this.queryWidth = 0;
        this.isCropped = true;
        this.presetSizeType = -1;
        this.initialize(context);
    }
    
    public ProfilePictureView(final Context context, final AttributeSet set) {
        super(context, set);
        this.queryHeight = 0;
        this.queryWidth = 0;
        this.isCropped = true;
        this.presetSizeType = -1;
        this.initialize(context);
        this.parseAttributes(set);
    }
    
    public ProfilePictureView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.queryHeight = 0;
        this.queryWidth = 0;
        this.isCropped = true;
        this.presetSizeType = -1;
        this.initialize(context);
        this.parseAttributes(set);
    }
    
    private int getPresetSizeInPixels(final boolean b) {
        switch (this.presetSizeType) {
            case -2: {
                final int n = R.dimen.com_facebook_profilepictureview_preset_size_small;
                return this.getResources().getDimensionPixelSize(n);
            }
            case -3: {
                final int n = R.dimen.com_facebook_profilepictureview_preset_size_normal;
                return this.getResources().getDimensionPixelSize(n);
            }
            case -4: {
                final int n = R.dimen.com_facebook_profilepictureview_preset_size_large;
                return this.getResources().getDimensionPixelSize(n);
            }
            case -1: {
                if (b) {
                    final int n = R.dimen.com_facebook_profilepictureview_preset_size_normal;
                    return this.getResources().getDimensionPixelSize(n);
                }
                break;
            }
        }
        return 0;
    }
    
    private void initialize(final Context context) {
        this.removeAllViews();
        (this.image = new ImageView(context)).setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        this.image.setScaleType(ImageView$ScaleType.CENTER_INSIDE);
        this.addView((View)this.image);
    }
    
    private void parseAttributes(final AttributeSet set) {
        final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes(set, R.styleable.com_facebook_profile_picture_view);
        this.setPresetSize(obtainStyledAttributes.getInt(0, -1));
        this.isCropped = obtainStyledAttributes.getBoolean(1, true);
        obtainStyledAttributes.recycle();
    }
    
    private void processResponse(final ImageResponse imageResponse) {
        if (imageResponse.getRequest() == this.lastRequest) {
            this.lastRequest = null;
            final Bitmap bitmap = imageResponse.getBitmap();
            final Exception error = imageResponse.getError();
            if (error != null) {
                final OnErrorListener onErrorListener = this.onErrorListener;
                if (onErrorListener == null) {
                    Logger.log(LoggingBehavior.REQUESTS, 6, ProfilePictureView.TAG, error.toString());
                    return;
                }
                onErrorListener.onError(new FacebookException("Error in downloading profile picture for profileId: " + this.getProfileId(), error));
            }
            else if (bitmap != null) {
                this.setImageBitmap(bitmap);
                if (imageResponse.isCachedRedirect()) {
                    this.sendImageRequest(false);
                }
            }
        }
    }
    
    private void refreshImage(final boolean b) {
        final boolean updateImageQueryParameters = this.updateImageQueryParameters();
        if (this.profileId == null || this.profileId.length() == 0 || (this.queryWidth == 0 && this.queryHeight == 0)) {
            this.setBlankProfilePicture();
        }
        else if (updateImageQueryParameters || b) {
            this.sendImageRequest(true);
        }
    }
    
    private void sendImageRequest(final boolean allowCachedRedirects) {
        try {
            final ImageRequest build = new ImageRequest.Builder(this.getContext(), ImageRequest.getProfilePictureUrl(this.profileId, this.queryWidth, this.queryHeight)).setAllowCachedRedirects(allowCachedRedirects).setCallerTag(this).setCallback(new ImageRequest.Callback() {
                @Override
                public void onCompleted(final ImageResponse imageResponse) {
                    ProfilePictureView.this.processResponse(imageResponse);
                }
            }).build();
            if (this.lastRequest != null) {
                ImageDownloader.cancelRequest(this.lastRequest);
            }
            ImageDownloader.downloadAsync(this.lastRequest = build);
        }
        catch (MalformedURLException ex) {
            Logger.log(LoggingBehavior.REQUESTS, 6, ProfilePictureView.TAG, ex.toString());
        }
    }
    
    private void setBlankProfilePicture() {
        int n;
        if (this.isCropped()) {
            n = R.drawable.com_facebook_profile_picture_blank_square;
        }
        else {
            n = R.drawable.com_facebook_profile_picture_blank_portrait;
        }
        this.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), n));
    }
    
    private void setImageBitmap(final Bitmap bitmap) {
        if (this.image != null && bitmap != null) {
            this.imageContents = bitmap;
            this.image.setImageBitmap(bitmap);
        }
    }
    
    private boolean updateImageQueryParameters() {
        final boolean b = true;
        int height = this.getHeight();
        int width = this.getWidth();
        if (width < 1 || height < 1) {
            return false;
        }
        final int presetSizeInPixels = this.getPresetSizeInPixels(false);
        if (presetSizeInPixels != 0) {
            width = presetSizeInPixels;
            height = presetSizeInPixels;
        }
        if (width <= height) {
            if (this.isCropped()) {
                height = width;
            }
            else {
                height = 0;
            }
        }
        else if (this.isCropped()) {
            width = height;
        }
        else {
            width = 0;
        }
        boolean b2 = b;
        if (width == this.queryWidth) {
            b2 = (height != this.queryHeight && b);
        }
        this.queryWidth = width;
        this.queryHeight = height;
        return b2;
    }
    
    public final OnErrorListener getOnErrorListener() {
        return this.onErrorListener;
    }
    
    public final int getPresetSize() {
        return this.presetSizeType;
    }
    
    public final String getProfileId() {
        return this.profileId;
    }
    
    public final boolean isCropped() {
        return this.isCropped;
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.lastRequest = null;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        super.onLayout(b, n, n2, n3, n4);
        this.refreshImage(false);
    }
    
    protected void onMeasure(final int n, int measureSpec) {
        final ViewGroup$LayoutParams layoutParams = this.getLayoutParams();
        final boolean b = false;
        final int size = View$MeasureSpec.getSize(measureSpec);
        final int size2 = View$MeasureSpec.getSize(n);
        boolean b2 = b;
        int presetSizeInPixels = size;
        int measureSpec2 = measureSpec;
        if (View$MeasureSpec.getMode(measureSpec) != 1073741824) {
            b2 = b;
            presetSizeInPixels = size;
            measureSpec2 = measureSpec;
            if (layoutParams.height == -2) {
                presetSizeInPixels = this.getPresetSizeInPixels(true);
                measureSpec2 = View$MeasureSpec.makeMeasureSpec(presetSizeInPixels, 1073741824);
                b2 = true;
            }
        }
        boolean b3 = b2;
        int presetSizeInPixels2 = size2;
        measureSpec = n;
        if (View$MeasureSpec.getMode(n) != 1073741824) {
            b3 = b2;
            presetSizeInPixels2 = size2;
            measureSpec = n;
            if (layoutParams.width == -2) {
                presetSizeInPixels2 = this.getPresetSizeInPixels(true);
                measureSpec = View$MeasureSpec.makeMeasureSpec(presetSizeInPixels2, 1073741824);
                b3 = true;
            }
        }
        if (b3) {
            this.setMeasuredDimension(presetSizeInPixels2, presetSizeInPixels);
            this.measureChildren(measureSpec, measureSpec2);
            return;
        }
        super.onMeasure(measureSpec, measureSpec2);
    }
    
    protected void onRestoreInstanceState(final Parcelable parcelable) {
        if (parcelable.getClass() != Bundle.class) {
            super.onRestoreInstanceState(parcelable);
        }
        else {
            final Bundle bundle = (Bundle)parcelable;
            super.onRestoreInstanceState(bundle.getParcelable("ProfilePictureView_superState"));
            this.profileId = bundle.getString("ProfilePictureView_profileId");
            this.presetSizeType = bundle.getInt("ProfilePictureView_presetSize");
            this.isCropped = bundle.getBoolean("ProfilePictureView_isCropped");
            this.queryWidth = bundle.getInt("ProfilePictureView_width");
            this.queryHeight = bundle.getInt("ProfilePictureView_height");
            this.setImageBitmap((Bitmap)bundle.getParcelable("ProfilePictureView_bitmap"));
            if (bundle.getBoolean("ProfilePictureView_refresh")) {
                this.refreshImage(true);
            }
        }
    }
    
    protected Parcelable onSaveInstanceState() {
        final Parcelable onSaveInstanceState = super.onSaveInstanceState();
        final Bundle bundle = new Bundle();
        bundle.putParcelable("ProfilePictureView_superState", onSaveInstanceState);
        bundle.putString("ProfilePictureView_profileId", this.profileId);
        bundle.putInt("ProfilePictureView_presetSize", this.presetSizeType);
        bundle.putBoolean("ProfilePictureView_isCropped", this.isCropped);
        bundle.putParcelable("ProfilePictureView_bitmap", (Parcelable)this.imageContents);
        bundle.putInt("ProfilePictureView_width", this.queryWidth);
        bundle.putInt("ProfilePictureView_height", this.queryHeight);
        bundle.putBoolean("ProfilePictureView_refresh", this.lastRequest != null);
        return (Parcelable)bundle;
    }
    
    public final void setCropped(final boolean isCropped) {
        this.isCropped = isCropped;
        this.refreshImage(false);
    }
    
    public final void setOnErrorListener(final OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }
    
    public final void setPresetSize(final int presetSizeType) {
        switch (presetSizeType) {
            default: {
                throw new IllegalArgumentException("Must use a predefined preset size");
            }
            case -4:
            case -3:
            case -2:
            case -1: {
                this.presetSizeType = presetSizeType;
                this.requestLayout();
            }
        }
    }
    
    public final void setProfileId(final String profileId) {
        boolean b = false;
        if (Utility.isNullOrEmpty(this.profileId) || !this.profileId.equalsIgnoreCase(profileId)) {
            this.setBlankProfilePicture();
            b = true;
        }
        this.profileId = profileId;
        this.refreshImage(b);
    }
    
    public interface OnErrorListener
    {
        void onError(final FacebookException p0);
    }
}
