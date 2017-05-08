// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.subtitles;

import com.netflix.mediaclient.service.player.subtitles.image.ImageSubtitleMetadata;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import java.util.ArrayList;
import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;
import com.netflix.mediaclient.service.player.subtitles.image.ImageDescriptor;
import com.netflix.mediaclient.util.DeviceUtils;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import android.graphics.BitmapFactory;
import com.netflix.mediaclient.util.ViewUtils$ViewComparator;
import java.util.List;
import com.netflix.mediaclient.service.player.subtitles.image.ImageSubtitleBlock;
import android.view.View;
import android.content.Context;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import java.util.HashMap;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import android.widget.ImageView;
import java.util.Map;
import android.widget.RelativeLayout;

public class ImageBasedSubtitleManager extends BaseSubtitleManager
{
    private RelativeLayout mImageWrapper;
    protected MeasureTranslator mMeasureTranslator;
    private boolean mPlayerControlsVisible;
    private Map<String, ImageView> mVisibleBlocks;
    
    ImageBasedSubtitleManager(final PlayerFragment playerFragment) {
        super(playerFragment);
        this.mVisibleBlocks = new HashMap<String, ImageView>();
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.mDisplayArea.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 0);
        this.mDisplayArea.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.mDisplayArea.removeAllViews();
        this.mImageWrapper = new RelativeLayout((Context)playerFragment.getActivity());
        this.mDisplayArea.addView((View)this.mImageWrapper, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
    }
    
    private ImageView createImage(final String s, final int n, final int n2, final boolean b) {
        final Bitmap decodeFile = BitmapFactory.decodeFile(s);
        if (decodeFile == null) {
            Log.w("nf_subtitles_render", "==> Unable to decode file on path " + s);
            return null;
        }
        Bitmap imageBitmap;
        if (b) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "Scale to w/h " + n + "/" + n2);
            }
            imageBitmap = Bitmap.createScaledBitmap(decodeFile, n, n2, true);
        }
        else {
            Log.d("nf_subtitles_render", "Do not scale, use image original width");
            imageBitmap = Bitmap.createScaledBitmap(decodeFile, n, n2, true);
        }
        final ImageView imageView = new ImageView((Context)this.getContext());
        imageView.setVisibility(0);
        imageView.setImageBitmap(imageBitmap);
        return imageView;
    }
    
    private int getHorizontalOffset() {
        if (this.mMeasureTranslator == null) {
            return 0;
        }
        return this.mMeasureTranslator.getHorizontalOffset();
    }
    
    private float getScaleFactor() {
        if (this.mMeasureTranslator == null) {
            return 1.0f;
        }
        return this.mMeasureTranslator.getScaleFactor();
    }
    
    private int getVerticalOffset() {
        if (this.mMeasureTranslator == null) {
            return 0;
        }
        return this.mMeasureTranslator.getVerticalOffset();
    }
    
    private void removeAll(final boolean b) {
        this.removeVisibleSubtitleBlocks(b);
    }
    
    private void removeSubtitleBlock(final ImageSubtitleBlock imageSubtitleBlock) {
        if (imageSubtitleBlock == null || imageSubtitleBlock.getImage() == null || StringUtils.isEmpty(imageSubtitleBlock.getImage().getName())) {
            Log.e("nf_subtitles_render", "Subtitle block can not be null!");
        }
        else {
            final ImageView imageView = this.mVisibleBlocks.remove(imageSubtitleBlock.getImage().getName());
            if (imageView != null) {
                imageView.setVisibility(4);
                this.mImageWrapper.removeView((View)imageView);
            }
        }
    }
    
    private void setVisibility(final boolean b) {
        Log.d("nf_subtitles_render", "All images invisible");
        for (final ImageView imageView : this.mVisibleBlocks.values()) {
            int visibility;
            if (b) {
                visibility = 0;
            }
            else {
                visibility = 4;
            }
            imageView.setVisibility(visibility);
        }
    }
    
    private void showSubtitleBlock(final ImageSubtitleBlock imageSubtitleBlock, final List<ViewUtils$ViewComparator> list) {
        if (imageSubtitleBlock == null || imageSubtitleBlock.getImage() == null || StringUtils.isEmpty(imageSubtitleBlock.getImage().getLocalImagePath())) {
            Log.e("nf_subtitles_render", "No image! Can not show!");
            return;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Show subtitle block: " + imageSubtitleBlock);
        }
        imageSubtitleBlock.displayed();
        final float scaleFactor = this.getScaleFactor();
        final boolean b = true;
        final ImageDescriptor image = imageSubtitleBlock.getImage();
        final int n = (int)(image.getWidth() * scaleFactor);
        final int n2 = (int)(image.getHeight() * scaleFactor);
        final int n3 = 0;
        int n5;
        final int n4 = n5 = 0;
        int n6 = n3;
        int height = n2;
        int width = n;
        boolean b2 = b;
        if (scaleFactor < 1.0f) {
            n5 = n4;
            n6 = n3;
            height = n2;
            width = n;
            b2 = b;
            if (this.mMeasureTranslator.getDeviceHeight() < 480) {
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles_render", "Not scaling down! This happens only when device resolution is lower than 480P (h: " + this.mMeasureTranslator.getDeviceHeight() + " and scale factor is lower than 1 " + scaleFactor);
                }
                b2 = false;
                n5 = (image.getHeight() - n2) / 2;
                n6 = (image.getWidth() - n) / 2;
                width = image.getWidth();
                height = image.getHeight();
            }
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Scale image " + image.getName() + " from w/h " + image.getWidth() + "/" + image.getHeight() + " to " + width + "/" + height);
            Log.d("nf_subtitles_render", "Delta Scale image w/h " + n6 + "/" + n5);
        }
        boolean b3 = b2;
        if (image.getHeight() == height) {
            b3 = b2;
            if (image.getWidth() == width) {
                b3 = false;
                Log.d("nf_subtitles_render", "Source and target resolutions are the same, do not scale!");
            }
        }
        final int n7 = this.getHorizontalOffset() + (int)(image.getOriginX() * scaleFactor) - n6;
        final int n8 = this.mDisplayArea.getWidth() - n7 - width;
        final int n9 = (int)(image.getOriginY() * scaleFactor) + this.getVerticalOffset() + n5;
        int n11;
        final int n10 = n11 = DeviceUtils.getScreenHeightInPixels((Context)this.getContext()) - n9 - height;
        int n12 = n9;
        if (this.mPlayerControlsVisible) {
            if (n9 > this.mDisplayArea.getHeight() / 2) {
                final int n13 = this.getDisplayAreaMarginBottom() - n10;
                n11 = n10;
                n12 = n9;
                if (n13 > 0) {
                    n12 = n9 - n13;
                    n11 = n10 + n13;
                }
            }
            else {
                final int n14 = this.getDisplayAreaMarginTop() - n9;
                n11 = n10;
                n12 = n9;
                if (n14 > 0) {
                    n12 = n9 + n14;
                    n11 = n10 - n14;
                }
            }
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Original image " + image.getName() + " position l/r/t/b: " + image.getOriginX() + " / " + (image.getOriginX() + image.getWidth()) + " / " + image.getOriginY() + " / " + (image.getOriginY() + image.getHeight()));
            Log.d("nf_subtitles_render", "Set image before validation" + image.getName() + " to position l/r/t/b: " + n7 + " / " + n8 + " / " + n12 + " / " + n11);
        }
        int n15 = n11;
        int n16;
        if ((n16 = n12) < 0) {
            Log.d("nf_subtitles_render", "Top was negative!");
            n15 = n11 - n12;
            n16 = 0;
        }
        int n18;
        int n19;
        if (n15 < 0) {
            Log.d("nf_subtitles_render", "Bottom was negative!");
            final int n17 = 0;
            n18 = n16 + n15;
            n19 = n17;
        }
        else {
            final int n20 = n16;
            n19 = n15;
            n18 = n20;
        }
        int n21;
        int n22;
        if (n7 < 0) {
            Log.d("nf_subtitles_render", "Left was negative!");
            n21 = n8 - n7;
            n22 = 0;
        }
        else {
            n21 = n8;
            n22 = n7;
        }
        int n23 = n21;
        int n24 = n22;
        if (n21 < 0) {
            Log.d("nf_subtitles_render", "Right was negative!");
            n24 = n22 + n21;
            n23 = 0;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Measurement translation: " + this.mMeasureTranslator);
        }
        final ImageView image2 = this.createImage(image.getLocalImagePath(), width, height, b3);
        if (image2 == null) {
            Log.e("nf_subtitles_render", "showSubtitleBlock:: NULL image for desc.getLocalImagePath()!");
            return;
        }
        Log.d("nf_subtitles_render", "showSubtitleBlock:: Image for " + image.getLocalImagePath() + " exist and it is visible " + (image2.getVisibility() == 0) + ", w/h: " + image2.getMeasuredWidth() + "/" + image2.getMeasuredHeight());
        if (list != null) {
            list.add(new ViewUtils$ViewComparator((View)image2));
        }
        this.mVisibleBlocks.put(image.getName(), image2);
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Set image " + image.getName() + " after validation to position l/r/t/b: " + n24 + " / " + n23 + " / " + n18 + " / " + n19);
        }
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(width, height);
        layoutParams.setMargins(n24, n18, n23, n19);
        image2.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.mImageWrapper.addView((View)image2);
    }
    
    private void showSubtitleBlocks(final List<SubtitleBlock> list) {
        if (list == null) {
            return;
        }
        final ArrayList<ViewUtils$ViewComparator> list2 = new ArrayList<ViewUtils$ViewComparator>();
        final Iterator<SubtitleBlock> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.showSubtitleBlock((ImageSubtitleBlock)iterator.next(), list2);
        }
        if (list2.size() > 1) {
            this.moveBlocksAppartIfNeeded(list2);
        }
        else {
            Log.d("nf_subtitles_render", "No need to move blocks");
        }
        this.makeItVisible(list2);
        this.mDisplayArea.forceLayout();
        this.mDisplayArea.requestLayout();
        this.mDisplayArea.invalidate();
        Log.d("nf_subtitles_render", "Add displayed block to pending queue to be removed at end time");
        this.handleDelayedSubtitleBlocks(list, false);
    }
    
    @Override
    public boolean canHandleSubtitleProfile(final IMedia$SubtitleProfile media$SubtitleProfile) {
        return media$SubtitleProfile != null && (media$SubtitleProfile == IMedia$SubtitleProfile.IMAGE || media$SubtitleProfile == IMedia$SubtitleProfile.IMAGE_ENC);
    }
    
    @Override
    public void clear() {
        Log.d("nf_subtitles_render", "Clear.");
        this.removeAll(true);
        if (this.mImageWrapper != null && this.mDisplayArea != null) {
            this.mDisplayArea.removeView((View)this.mImageWrapper);
        }
    }
    
    @Override
    public void clearPendingUpdates() {
        Log.d("nf_subtitles_render", "Clear pending updates:: NOOP.");
    }
    
    @Override
    protected Runnable createRunnable(final SubtitleBlock subtitleBlock, final boolean b) {
        final ImageBasedSubtitleManager$2 imageBasedSubtitleManager$2 = new ImageBasedSubtitleManager$2(this, b, (ImageSubtitleBlock)subtitleBlock);
        this.mPendingActions.add(imageBasedSubtitleManager$2);
        return imageBasedSubtitleManager$2;
    }
    
    @Override
    public IMedia$SubtitleProfile getSubtitleProfile() {
        return IMedia$SubtitleProfile.IMAGE;
    }
    
    @Override
    public void onPlayerOverlayVisibiltyChange(final boolean mPlayerControlsVisible) {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "Player UI is now visible: " + mPlayerControlsVisible);
            }
            this.mPlayerControlsVisible = mPlayerControlsVisible;
            if (this.mDisplayArea != null) {
                this.removeVisibleSubtitleBlocks(true);
            }
            else {
                Log.w("nf_subtitles_render", "Display area is null, unable to set margins!");
            }
        }
    }
    
    @Override
    public void onSubtitleChange(final SubtitleScreen subtitleScreen) {
        Log.d("nf_subtitles_render", "ImageBasedSubtitleManager:: update subtitle data");
        if (subtitleScreen == null) {
            Log.e("nf_subtitles_render", "Subtitle data is null. This should never happen!");
        }
        else {
            if (subtitleScreen.getParser() == null) {
                Log.e("nf_subtitles_render", "Subtitle parser is null. This should never happen!");
                return;
            }
            if (subtitleScreen.getParser() instanceof ImageSubtitleMetadata) {
                Log.d("nf_subtitles_render", "Parser is as expected...");
                this.mParser = subtitleScreen.getParser();
                this.removeAll(false);
                final int hashCode = subtitleScreen.getParser().hashCode();
                if (this.mSubtitleParserId != null && this.mSubtitleParserId == hashCode) {
                    if (Log.isLoggable()) {
                        Log.v("nf_subtitles_render", "Same subtitles file " + this.mSubtitleParserId);
                    }
                }
                else {
                    if (Log.isLoggable()) {
                        Log.v("nf_subtitles_render", "Subtitles file changed. Was " + this.mSubtitleParserId + ", now " + hashCode + ". (Re) create regions!");
                    }
                    this.mSubtitleParserId = hashCode;
                    this.mMeasureTranslator = null;
                }
                final ImageSubtitleMetadata imageSubtitleMetadata = (ImageSubtitleMetadata)this.mParser;
                if (this.mMeasureTranslator == null) {
                    this.mMeasureTranslator = MeasureTranslator.createMeasureTranslator(imageSubtitleMetadata.getRootContainerExtentX(), imageSubtitleMetadata.getRootContainerExtentY(), (View)this.mDisplayArea);
                    if (Log.isLoggable()) {
                        Log.v("nf_subtitles_render", "measures: " + this.mMeasureTranslator);
                    }
                }
                this.showSubtitleBlocks(subtitleScreen.getDisplayNowBlocks());
                this.handleDelayedSubtitleBlocks(subtitleScreen.getDisplayLaterBlocks(), true);
                return;
            }
            if (Log.isLoggable()) {
                Log.w("nf_subtitles_render", "Drop subtitle change! Timing issue, subtitles are supposed to be image based and parser is not, but " + subtitleScreen.getParser());
            }
        }
    }
    
    @Override
    public void onSubtitleRemove() {
        Log.d("nf_subtitles_render", "Remove all subtitles.");
        this.removeAll(true);
    }
    
    @Override
    protected void removeVisibleSubtitleBlocks(final boolean b) {
        // monitorenter(this)
        // monitorexit(this)
        while (true) {
            Label_0064: {
                if (!b) {
                    break Label_0064;
                }
                Label_0074: {
                    try {
                        this.mImageWrapper.removeAllViews();
                        for (final ImageView imageView : this.mVisibleBlocks.values()) {
                            if (imageView != null) {
                                imageView.setVisibility(4);
                            }
                        }
                        break Label_0074;
                    }
                    finally {
                    }
                    // monitorexit(this)
                    break Label_0064;
                }
                this.mVisibleBlocks.clear();
                return;
            }
            this.mImageWrapper.removeAllViewsInLayout();
            continue;
        }
    }
    
    @Override
    public void setSubtitleVisibility(final boolean b) {
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "ImageBasedSubtitleManager:: set subtitle visibility to visible " + b);
        }
        this.mHandler.post((Runnable)new ImageBasedSubtitleManager$1(this, b));
    }
}
