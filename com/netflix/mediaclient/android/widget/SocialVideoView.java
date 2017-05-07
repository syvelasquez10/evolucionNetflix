// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.View;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.Video;
import android.text.Layout$Alignment;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Paint$Align;
import android.content.res.Resources;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import android.graphics.Paint;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.Log;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import android.graphics.Rect;
import android.text.StaticLayout;
import android.graphics.Bitmap;
import android.text.TextPaint;

public class SocialVideoView extends VideoView
{
    public static final int MAX_SOCIAL_BITMAP_IMGS = 9;
    private static final String TAG = "SocialVideoView";
    private static TextPaint microSecondaryCenterPaint;
    private static TextPaint microSecondaryLeftPaint;
    private static TextPaint smallPrimaryLeftPaint;
    private final Bitmap[] bitmaps;
    private String cachedName;
    private StaticLayout connectText;
    private final Rect drawRect;
    private int friendCount;
    private boolean isSocialView;
    private int padding;
    private int singleImgSize;
    private VideoDetails socialData;
    private StaticLayout socialGroupText;
    private int textSizeMicro;
    
    public SocialVideoView(final Context context) {
        super(context);
        this.bitmaps = new Bitmap[9];
        this.drawRect = new Rect();
        this.init();
    }
    
    public SocialVideoView(final Context context, final AttributeSet set) {
        super(context, set);
        this.bitmaps = new Bitmap[9];
        this.drawRect = new Rect();
        this.init();
    }
    
    public SocialVideoView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.bitmaps = new Bitmap[9];
        this.drawRect = new Rect();
        this.init();
    }
    
    private int centerXOffset(final int n) {
        return (this.getWidth() - n) / 2;
    }
    
    private int centerYOffset(final int n) {
        return (this.getHeight() - n) / 2;
    }
    
    private void clearBitmaps() {
        for (int i = 0; i < this.bitmaps.length; ++i) {
            this.bitmaps[i] = null;
        }
    }
    
    @SuppressLint({ "WrongCall" })
    private void drawConnectToFacebook(final Canvas canvas) {
        this.setImageResource(2130837684);
        super.onDraw(canvas);
        Log.v("SocialVideoView", "drawConnectToFacebook: " + this.getWidth() + "x" + this.getHeight());
        canvas.save();
        canvas.translate((float)this.padding, (float)(this.getHeight() * 2 / 3 - this.connectText.getHeight() / 2));
        this.connectText.draw(canvas);
        canvas.restore();
    }
    
    private void drawMultipleFriends(final Canvas canvas, final VideoDetails videoDetails) {
        final int n = this.padding / 2;
        int n2 = 1;
        int singleImgSize;
        if (this.friendCount == 1) {
            singleImgSize = this.singleImgSize;
        }
        else {
            if (this.friendCount <= 4) {
                n2 = 2;
            }
            else {
                n2 = 3;
            }
            singleImgSize = (int)(this.getWidth() * 0.8f);
        }
        final int n3 = (int)(singleImgSize / n2 - n + 0.5f);
        final int n4 = (n3 + n) * ((this.friendCount + n2 - 1) / n2);
        final int n5 = this.socialGroupText.getHeight() + n4 + this.padding;
        final boolean b = this.padding * 2 + n5 < this.getHeight();
        final int centerXOffset = this.centerXOffset(singleImgSize);
        int n6;
        if (b) {
            n6 = n5;
        }
        else {
            n6 = n4;
        }
        final int centerYOffset = this.centerYOffset(n6);
        if (Log.isLoggable("SocialVideoView", 2)) {
            Log.v("SocialVideoView", String.format("view height: %d, view width: %d, friend count: %d, totalImgWidth: %d, boxSize: %d, totalImageHeight: %d, totalHeight: %d, showText: %s, dx: %d, dy: %d", this.getHeight(), this.getWidth(), this.friendCount, singleImgSize, n3, n4, n5, b, centerXOffset, centerYOffset));
        }
        int n7 = centerYOffset;
        if (b) {
            this.drawSocialGroupText(canvas, centerYOffset);
            n7 = centerYOffset + (this.socialGroupText.getHeight() + this.padding);
        }
        if (Log.isLoggable("SocialVideoView", 2)) {
            Log.v("SocialVideoView", "dy: " + n7);
        }
        this.drawSocialBitmaps(canvas, n2, n3, centerXOffset, n7);
    }
    
    private void drawSingleFriend(final Canvas canvas, final VideoDetails videoDetails) {
        final Bitmap bitmap = this.bitmaps[0];
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        final int centerXOffset = this.centerXOffset(this.singleImgSize);
        final int n = this.singleImgSize + this.textSizeMicro + this.padding;
        final int centerYOffset = this.centerYOffset(n);
        this.drawRect.set(centerXOffset, centerYOffset, this.singleImgSize + centerXOffset, this.singleImgSize + centerYOffset);
        this.safelyDrawBitmap(canvas, bitmap, this.drawRect);
        final TextPaint microSecondaryCenterPaint = SocialVideoView.microSecondaryCenterPaint;
        if (this.cachedName == null) {
            this.cachedName = this.getTruncatedName((Paint)microSecondaryCenterPaint);
        }
        canvas.drawText(this.cachedName, (float)(this.getWidth() / 2), (float)(centerYOffset + n), (Paint)microSecondaryCenterPaint);
    }
    
    private void drawSocialBitmaps(final Canvas canvas, final int n, final int n2, final int n3, int i) {
        final int n4 = this.padding / 2;
        this.drawRect.set(n3, i, n3 + n2, i + n2);
        for (i = 0; i < 9; ++i) {
            this.safelyDrawBitmap(canvas, this.bitmaps[i], this.drawRect);
            if (i == this.friendCount - 1) {
                break;
            }
            if ((i + 1) % n == 0) {
                this.drawRect.offset(n3 - this.drawRect.left, n2 + n4);
            }
            else {
                this.drawRect.offset(n2 + n4, 0);
            }
        }
    }
    
    private void drawSocialGroupText(final Canvas canvas, final int n) {
        Log.v("SocialVideoView", "text dy: " + n);
        canvas.save();
        canvas.translate((float)this.padding, (float)n);
        this.socialGroupText.draw(canvas);
        canvas.restore();
    }
    
    private void drawSocialView(final Canvas canvas) {
        final VideoType type = this.socialData.getType();
        Log.v("SocialVideoView", "** Drawing social view for: " + this.socialData.getTitle() + ", type: " + type + ", friend count: " + this.friendCount);
        if (type == VideoType.SOCIAL_POPULAR && this.friendCount == 0) {
            this.drawConnectToFacebook(canvas);
            return;
        }
        if (type == VideoType.SOCIAL_FRIEND) {
            this.drawSingleFriend(canvas, this.socialData);
            return;
        }
        if (type == VideoType.SOCIAL_GROUP) {
            this.drawMultipleFriends(canvas, this.socialData);
            return;
        }
        Log.w("SocialVideoView", "Couldn't draw unknown social type: " + type);
    }
    
    private void fetchImages() {
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        for (int i = 0; i < this.friendCount; ++i) {
            final FriendProfile friendProfile = this.socialData.getFacebookFriends().get(i);
            if (StringUtils.isEmpty(friendProfile.getImageUrl())) {
                Log.w("SocialVideoView", "Empty image url for friend: " + friendProfile.getFullName());
            }
            else {
                imageLoader.getImg(friendProfile.getImageUrl(), IClientLogging.AssetType.profileAvatar, this.singleImgSize, this.singleImgSize, (ImageLoader.ImageLoaderListener)new ImageLoadedListener(i));
            }
        }
    }
    
    private String getTruncatedName(final Paint paint) {
        String s;
        for (s = this.socialData.getTitle(); paint.measureText(s) > this.getWidth(); s = s.substring(0, s.length() / 2) + "...") {
            Log.v("SocialVideoView", "Name too long... halving");
        }
        return s;
    }
    
    private void init() {
        final Resources resources = this.getResources();
        this.singleImgSize = resources.getDimensionPixelOffset(2131361869);
        this.textSizeMicro = resources.getDimensionPixelOffset(2131361837);
        this.padding = resources.getDimensionPixelOffset(2131361835);
        this.initTextPaint(resources);
    }
    
    private void initTextPaint(final Resources resources) {
        if (SocialVideoView.microSecondaryLeftPaint == null) {
            (SocialVideoView.microSecondaryLeftPaint = new TextPaint()).setTextSize((float)this.textSizeMicro);
            SocialVideoView.microSecondaryLeftPaint.setColor(resources.getColor(2131296315));
            SocialVideoView.microSecondaryLeftPaint.setTextAlign(Paint$Align.LEFT);
        }
        if (SocialVideoView.microSecondaryCenterPaint == null) {
            (SocialVideoView.microSecondaryCenterPaint = new TextPaint()).setTextSize((float)this.textSizeMicro);
            SocialVideoView.microSecondaryCenterPaint.setColor(resources.getColor(2131296315));
            SocialVideoView.microSecondaryCenterPaint.setTextAlign(Paint$Align.CENTER);
        }
        if (SocialVideoView.smallPrimaryLeftPaint == null) {
            (SocialVideoView.smallPrimaryLeftPaint = new TextPaint()).setTextSize((float)resources.getDimensionPixelOffset(2131361838));
            SocialVideoView.smallPrimaryLeftPaint.setColor(resources.getColor(2131296314));
            SocialVideoView.smallPrimaryLeftPaint.setTextAlign(Paint$Align.LEFT);
        }
    }
    
    private void logCurrentBitmap(final Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            final Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            if (bitmap != null) {
                Log.v("SocialVideoView", "drawing bitmap: " + bitmap.toString() + ", recycled: " + bitmap.isRecycled() + ", tag: " + this.getUrlTag());
                return;
            }
            Log.v("SocialVideoView", "bitmap is null, tag: " + this.getUrlTag());
        }
        else {
            if (!(drawable instanceof LayerDrawable)) {
                Log.v("SocialVideoView", "drawable is: " + drawable.getClass().getSimpleName() + ", tag: " + this.getUrlTag());
                return;
            }
            final LayerDrawable layerDrawable = (LayerDrawable)drawable;
            for (int i = 0; i < layerDrawable.getNumberOfLayers(); ++i) {
                this.logCurrentBitmap(layerDrawable.getDrawable(i));
            }
        }
    }
    
    private void safelyDrawBitmap(final Canvas canvas, final Bitmap bitmap, final Rect rect) {
        if (bitmap == null) {
            return;
        }
        if (bitmap.isRecycled()) {
            Log.w("SocialVideoView", "Trying to draw recycled bitmap");
            return;
        }
        canvas.drawBitmap(bitmap, (Rect)null, rect, (Paint)null);
    }
    
    boolean isSocialView() {
        return this.isSocialView;
    }
    
    protected void onDraw(final Canvas canvas) {
        if (this.isSocialView) {
            this.drawSocialView(canvas);
            return;
        }
        try {
            super.onDraw(canvas);
        }
        catch (IllegalArgumentException ex) {
            Log.w("SocialVideoView", "IAE while drawing img: " + ex.getMessage());
        }
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (n == 0 || n == n3) {
            return;
        }
        this.connectText = new StaticLayout((CharSequence)this.getResources().getString(2131493225), SocialVideoView.smallPrimaryLeftPaint, n - this.padding * 2, Layout$Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        this.socialGroupText = new StaticLayout((CharSequence)this.getResources().getString(2131493224), SocialVideoView.microSecondaryLeftPaint, n - this.padding * 2, Layout$Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
    }
    
    @Override
    protected boolean shouldDispatchToPressHandler() {
        return !this.isSocialView;
    }
    
    @Override
    public void update(final Video video, final Trackable trackable, int size, final boolean b) {
        final int n = 0;
        if (!(this.isSocialView = com.netflix.mediaclient.service.webclient.model.branches.Video.isSocialVideoType(video.getType()))) {
            super.update(video, trackable, size, b);
            return;
        }
        Log.v("SocialVideoView", "Updating for social view: " + video.getTitle());
        NetflixActivity.getImageLoader(this.getContext()).showImg(this, null, IClientLogging.AssetType.boxArt, video.getTitle(), false, false);
        this.setImageBitmap(null);
        this.setVisibility(0);
        this.socialData = (VideoDetails)video;
        this.cachedName = null;
        if (VideoType.SOCIAL_POPULAR.equals(this.socialData.getType())) {
            this.clicker.update((View)this, video);
        }
        else {
            this.clicker.remove((View)this);
        }
        if (this.socialData.getFacebookFriends() == null) {
            size = n;
        }
        else {
            size = this.socialData.getFacebookFriends().size();
        }
        this.friendCount = size;
        if (this.friendCount > 9) {
            Log.w("SocialVideoView", "More than 9 facebook friends - skipping more than max");
            this.friendCount = 9;
        }
        this.clearBitmaps();
        this.fetchImages();
    }
    
    private class ImageLoadedListener implements ImageLoaderListener
    {
        private final int index;
        
        public ImageLoadedListener(final int index) {
            this.index = index;
        }
        
        @Override
        public void onErrorResponse(final String s) {
            Log.w("SocialVideoView", "Could not load img: " + s + ", index: " + this.index);
        }
        
        @Override
        public void onResponse(final Bitmap bitmap, final String s) {
            if (bitmap == null) {
                return;
            }
            Log.v("SocialVideoView", "Loaded bitmap: " + s + ", index: " + this.index + ", size: " + bitmap.getWidth() + "x" + bitmap.getHeight());
            SocialVideoView.this.bitmaps[this.index] = bitmap;
            SocialVideoView.this.invalidate();
        }
    }
}
