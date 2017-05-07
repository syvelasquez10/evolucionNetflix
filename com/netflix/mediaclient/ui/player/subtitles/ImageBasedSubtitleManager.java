// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.subtitles;

import com.netflix.mediaclient.service.player.subtitles.image.ImageSubtitleParser;
import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import android.content.Context;
import java.util.ArrayList;
import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;
import com.netflix.mediaclient.service.player.subtitles.image.SegmentIndex$ImageDescriptor;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import java.util.Iterator;
import android.view.View;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.netflix.mediaclient.util.ViewUtils$ViewComparator;
import java.util.List;
import com.netflix.mediaclient.service.player.subtitles.image.ImageSubtitleBlock;
import java.util.HashMap;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import android.widget.ImageView;
import java.util.Map;

public class ImageBasedSubtitleManager extends BaseSubtitleManager
{
    protected MeasureTranslator mMeasureTranslator;
    private Map<String, ImageView> mVisibleBlocks;
    
    ImageBasedSubtitleManager(final PlayerActivity playerActivity) {
        super(playerActivity);
        this.mVisibleBlocks = new HashMap<String, ImageView>();
    }
    
    private ImageView createImage(final String s, final int n, final int n2) {
        final Bitmap scaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(s), n, n2, true);
        final ImageView imageView = new ImageView(this.getContext());
        imageView.setImageBitmap(scaledBitmap);
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
                this.mDisplayArea.removeView((View)imageView);
            }
        }
    }
    
    private void removeVisibleSubtitleBlocks(final boolean b) {
        // monitorenter(this)
        // monitorexit(this)
        while (true) {
            Label_0072: {
                if (!b) {
                    break Label_0072;
                }
                Label_0082: {
                    try {
                        this.mDisplayArea.removeAllViews();
                        for (final ImageView imageView : this.mVisibleBlocks.values()) {
                            Log.d("nf_subtitles_render", "Removing image ");
                            if (imageView != null) {
                                imageView.setVisibility(4);
                            }
                        }
                        break Label_0082;
                    }
                    finally {
                    }
                    // monitorexit(this)
                    break Label_0072;
                }
                this.mVisibleBlocks.clear();
                return;
            }
            this.mDisplayArea.removeAllViewsInLayout();
            continue;
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
            final SegmentIndex$ImageDescriptor image = imageSubtitleBlock.getImage();
            final int n2 = (int)(image.getWidth() * this.getScaleFactor());
            final int n3 = (int)(image.getHeight() * this.getScaleFactor());
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "Scale image " + image.getName() + " from w/h " + image.getWidth() + "/" + image.getHeight() + " to " + n2 + "/" + n3);
            }
            final int n4 = (int)(image.getOriginX() * this.getScaleFactor()) + this.getHorizontalOffset();
            final int n5 = this.mDisplayArea.getWidth() - n4 - n2;
            final int n6 = this.getVerticalOffset() + (int)(image.getOriginY() * this.getScaleFactor());
            final int n7 = this.mDisplayArea.getHeight() - n6 - n3;
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "Original image " + image.getName() + " position l/r/t/b: " + image.getOriginX() + " / " + (image.getOriginX() + image.getWidth()) + " / " + image.getOriginY() + " / " + (image.getOriginY() + image.getHeight()));
                Log.d("nf_subtitles_render", "Set image before validation" + image.getName() + " to position l/r/t/b: " + n4 + " / " + n5 + " / " + n6 + " / " + n7);
            }
            int n8 = n7;
            int n9;
            if ((n9 = n6) < 0) {
                Log.d("nf_subtitles_render", "Top was negative!");
                n8 = n7 - n6;
                n9 = 0;
            }
            int n10 = n8;
            int n11 = n9;
            if (n8 < 0) {
                Log.d("nf_subtitles_render", "Bottom was negative!");
                n11 = n8 + n9;
                n10 = 0;
            }
            int n12 = n5;
            int n13;
            if ((n13 = n4) < 0) {
                Log.d("nf_subtitles_render", "Left was negative!");
                n12 = n5 - n4;
                n13 = 0;
            }
            int n14;
            int n15;
            if (n12 < 0) {
                Log.d("nf_subtitles_render", "Right was negative!");
                n14 = n12 + n13;
                n15 = n;
            }
            else {
                final int n16 = n13;
                n15 = n12;
                n14 = n16;
            }
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "Measurement translation: " + this.mMeasureTranslator);
            }
            final ImageView image2 = this.createImage(image.getLocalImagePath(), n2, n3);
            if (image2 != null) {
                if (list != null) {
                    list.add(new ViewUtils$ViewComparator((View)image2));
                }
                this.mVisibleBlocks.put(image.getName(), image2);
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles_render", "Set image " + image.getName() + " after validation to position l/r/t/b: " + n14 + " / " + n15 + " / " + n11 + " / " + n10);
                }
                final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(n2, n3);
                layoutParams.setMargins(n14, n11, n15, n10);
                image2.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                this.mDisplayArea.addView((View)image2);
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
    public void clear() {
        Log.d("nf_subtitles_render", "Clear.");
        this.removeAll(true);
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
    public void onPlayerOverlayVisibiltyChange(final boolean b) {
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Player UI is now visible: " + b);
        }
        if (this.mDisplayArea != null) {
            this.removeVisibleSubtitleBlocks(true);
            this.mDisplayArea.requestLayout();
            return;
        }
        Log.w("nf_subtitles_render", "Display area is null, unable to set margins!");
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
    public void setSubtitleVisibility(final boolean b) {
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "ImageBasedSubtitleManager:: set subtitle visibility to visible " + b);
        }
        this.mHandler.post((Runnable)new ImageBasedSubtitleManager$1(this, b));
    }
}
