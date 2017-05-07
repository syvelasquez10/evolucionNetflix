// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.subtitles;

import com.netflix.mediaclient.service.player.subtitles.image.ImageSubtitleParser;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.javabridge.ui.IMedia$SubtitleProfile;
import java.util.ArrayList;
import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;
import com.netflix.mediaclient.service.player.subtitles.image.SegmentIndex$ImageDescriptor;
import com.netflix.mediaclient.util.DeviceUtils;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import android.graphics.Bitmap;
import com.netflix.mediaclient.Log;
import android.graphics.BitmapFactory;
import com.netflix.mediaclient.util.ViewUtils$ViewComparator;
import java.util.List;
import com.netflix.mediaclient.service.player.subtitles.image.ImageSubtitleBlock;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import java.util.HashMap;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import android.widget.ImageView;
import java.util.Map;
import android.widget.RelativeLayout;

public class ImageBasedSubtitleManager extends BaseSubtitleManager
{
    private RelativeLayout mImageWrapper;
    protected MeasureTranslator mMeasureTranslator;
    private boolean mPlayerControlsVisible;
    private Map<String, ImageView> mVisibleBlocks;
    
    ImageBasedSubtitleManager(final PlayerActivity playerActivity) {
        super(playerActivity);
        this.mVisibleBlocks = new HashMap<String, ImageView>();
        this.mImageWrapper = new RelativeLayout((Context)playerActivity);
        this.mDisplayArea.addView((View)this.mImageWrapper, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
    }
    
    private ImageView createImage(final String s, final int n, final int n2, final boolean b) {
        Bitmap imageBitmap = BitmapFactory.decodeFile(s);
        if (b) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "Scale to w/h " + n + "/" + n2);
            }
            imageBitmap = Bitmap.createScaledBitmap(imageBitmap, n, n2, true);
        }
        else {
            Log.d("nf_subtitles_render", "Do not scale, use image original width");
        }
        final ImageView imageView = new ImageView(this.getContext());
        imageView.setImageBitmap(imageBitmap);
        imageView.setVisibility(4);
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
        final int n = 0;
        if (imageSubtitleBlock == null || imageSubtitleBlock.getImage() == null || StringUtils.isEmpty(imageSubtitleBlock.getImage().getLocalImagePath())) {
            Log.e("nf_subtitles_render", "No image! Can not show!");
        }
        else {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "Show subtitle block: " + imageSubtitleBlock);
            }
            final float scaleFactor = this.getScaleFactor();
            final SegmentIndex$ImageDescriptor image = imageSubtitleBlock.getImage();
            int width = (int)(image.getWidth() * scaleFactor);
            int height = (int)(image.getHeight() * scaleFactor);
            short n2;
            short n3;
            boolean b;
            if (scaleFactor < 1.0f && this.mMeasureTranslator.getDeviceHeight() < 480) {
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles_render", "Not scaling down! This happens only when device resolution is lower than 480P (h: " + this.mMeasureTranslator.getDeviceHeight() + " and scale factor is lower than 1 " + scaleFactor);
                }
                n2 = (short)((image.getHeight() - height) / 2);
                n3 = (short)((image.getWidth() - width) / 2);
                width = image.getWidth();
                height = image.getHeight();
                b = false;
            }
            else {
                b = true;
                n3 = 0;
                n2 = 0;
            }
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "Scale image " + image.getName() + " from w/h " + image.getWidth() + "/" + image.getHeight() + " to " + width + "/" + height);
                Log.d("nf_subtitles_render", "Delta Scale image w/h " + n3 + "/" + n2);
            }
            boolean b2 = b;
            if (image.getHeight() == height) {
                b2 = b;
                if (image.getWidth() == width) {
                    Log.d("nf_subtitles_render", "Source and target resolutions are the same, do not scale!");
                    b2 = false;
                }
            }
            final int n4 = this.getHorizontalOffset() + (int)(image.getOriginX() * scaleFactor) - n3;
            final int n5 = this.mDisplayArea.getWidth() - n4 - width;
            final int n6 = (int)(image.getOriginY() * scaleFactor) + this.getVerticalOffset() + n2;
            int n8;
            final int n7 = n8 = DeviceUtils.getScreenHeightInPixels(this.getContext()) - n6 - height;
            int n9 = n6;
            if (this.mPlayerControlsVisible) {
                if (n6 > this.mDisplayArea.getHeight() / 2) {
                    final int n10 = this.getDisplayAreaMarginBottom() - n7;
                    n8 = n7;
                    n9 = n6;
                    if (n10 > 0) {
                        n9 = n6 - n10;
                        n8 = n7 + n10;
                    }
                }
                else {
                    final int n11 = this.getDisplayAreaMarginTop() - n6;
                    n8 = n7;
                    n9 = n6;
                    if (n11 > 0) {
                        n9 = n6 + n11;
                        n8 = n7 - n11;
                    }
                }
            }
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "Original image " + image.getName() + " position l/r/t/b: " + image.getOriginX() + " / " + (image.getOriginX() + image.getWidth()) + " / " + image.getOriginY() + " / " + (image.getOriginY() + image.getHeight()));
                Log.d("nf_subtitles_render", "Set image before validation" + image.getName() + " to position l/r/t/b: " + n4 + " / " + n5 + " / " + n9 + " / " + n8);
            }
            int n12 = n8;
            int n13;
            if ((n13 = n9) < 0) {
                Log.d("nf_subtitles_render", "Top was negative!");
                n12 = n8 - n9;
                n13 = 0;
            }
            int n15;
            int n16;
            if (n12 < 0) {
                Log.d("nf_subtitles_render", "Bottom was negative!");
                final int n14 = 0;
                n15 = n12 + n13;
                n16 = n14;
            }
            else {
                final int n17 = n13;
                n16 = n12;
                n15 = n17;
            }
            int n18;
            int n19;
            if (n4 < 0) {
                Log.d("nf_subtitles_render", "Left was negative!");
                n18 = n5 - n4;
                n19 = 0;
            }
            else {
                n18 = n5;
                n19 = n4;
            }
            if (n18 < 0) {
                Log.d("nf_subtitles_render", "Right was negative!");
                n19 += n18;
                n18 = n;
            }
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "Measurement translation: " + this.mMeasureTranslator);
            }
            final ImageView image2 = this.createImage(image.getLocalImagePath(), width, height, b2);
            if (image2 != null) {
                if (list != null) {
                    list.add(new ViewUtils$ViewComparator((View)image2));
                }
                this.mVisibleBlocks.put(image.getName(), image2);
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles_render", "Set image " + image.getName() + " after validation to position l/r/t/b: " + n19 + " / " + n18 + " / " + n15 + " / " + n16);
                }
                final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(width, height);
                layoutParams.setMargins(n19, n15, n18, n16);
                image2.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                this.mImageWrapper.addView((View)image2);
            }
        }
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
        this.moveBlocksAppartIfNeeded(list2);
        this.makeItVisible(list2);
        this.mDisplayArea.forceLayout();
        this.mDisplayArea.requestLayout();
        this.mDisplayArea.invalidate();
        Log.d("nf_subtitles_render", "Add displayed block to pending queue to be removed at end time");
        this.handleDelayedSubtitleBlocks(list, false);
    }
    
    @Override
    public boolean canHandleSubtitleProfile(final IMedia$SubtitleProfile media$SubtitleProfile) {
        return media$SubtitleProfile != null && media$SubtitleProfile == IMedia$SubtitleProfile.IMAGE;
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
            return;
        }
        if (subtitleScreen.getParser() == null) {
            Log.e("nf_subtitles_render", "Subtitle parser is null. This should never happen!");
            return;
        }
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
        final ImageSubtitleParser imageSubtitleParser = (ImageSubtitleParser)this.mParser;
        if (this.mMeasureTranslator == null) {
            this.mMeasureTranslator = MeasureTranslator.createMeasureTranslator(imageSubtitleParser.getMasterIndex().getRootContainerExtentX(), imageSubtitleParser.getMasterIndex().getRootContainerExtentY(), (View)this.mDisplayArea);
            if (Log.isLoggable()) {
                Log.v("nf_subtitles_render", "measures: " + this.mMeasureTranslator);
            }
        }
        this.showSubtitleBlocks(subtitleScreen.getDisplayNowBlocks());
        this.handleDelayedSubtitleBlocks(subtitleScreen.getDisplayLaterBlocks(), true);
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
            Label_0073: {
                if (!b) {
                    break Label_0073;
                }
                Label_0083: {
                    try {
                        this.mImageWrapper.removeAllViews();
                        for (final ImageView imageView : this.mVisibleBlocks.values()) {
                            Log.d("nf_subtitles_render", "Removing image ");
                            if (imageView != null) {
                                imageView.setVisibility(4);
                            }
                        }
                        break Label_0083;
                    }
                    finally {
                    }
                    // monitorexit(this)
                    break Label_0073;
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
