// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player.subtitles;

import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.ISubtitleDef$SubtitleProfile;
import android.text.TextUtils$TruncateAt;
import com.netflix.mediaclient.android.widget.AutoResizeTextView;
import com.netflix.mediaclient.android.widget.StrokeTextView;
import android.view.ViewTreeObserver;
import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;
import com.netflix.mediaclient.service.player.subtitles.DoubleLength;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.service.player.subtitles.text.ColorMapping;
import com.netflix.mediaclient.service.player.subtitles.TextSubtitleParser;
import android.graphics.Rect;
import com.netflix.mediaclient.util.SubtitleUtils$Margins;
import java.util.ArrayList;
import java.util.Iterator;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.service.player.subtitles.text.SubtitleTextNode;
import android.util.Pair;
import com.netflix.mediaclient.service.player.subtitles.text.HorizontalAlignment;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.service.player.subtitles.text.VerticalAlignment;
import com.netflix.mediaclient.util.SubtitleUtils;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.subtitles.text.Region;
import com.netflix.mediaclient.util.ViewUtils$ViewComparator;
import com.netflix.mediaclient.service.player.subtitles.text.TextSubtitleBlock;
import java.util.HashMap;
import com.netflix.mediaclient.ui.player.PlayerFragment;
import android.widget.TextView;
import java.util.List;
import android.widget.RelativeLayout;
import java.util.Map;
import android.view.ViewTreeObserver$OnPreDrawListener;
import java.util.concurrent.atomic.AtomicBoolean;
import android.widget.LinearLayout;
import android.graphics.Point;

public class EnhancedSubtitleManager extends BaseSubtitleManager
{
    private static final String DEFAULT_REGION_ID = "DEFAULT";
    private static final float SAFE_DISPLAY_AREA_MARGIN = 5.0f;
    private Point mAspectRatio;
    private LinearLayout mDefaultRegion;
    private float mDefaultTextSize;
    private AtomicBoolean mDefaultsInitiated;
    final ViewTreeObserver$OnPreDrawListener mDoNotDraw;
    private MeasureTranslator mMeasureTranslator;
    private Map<String, LinearLayout> mRegions;
    private RelativeLayout mSafeDisplayArea;
    private Map<String, List<TextView>> mVisibleBlocks;
    
    public EnhancedSubtitleManager(final PlayerFragment playerFragment) {
        super(playerFragment);
        this.mRegions = new HashMap<String, LinearLayout>();
        this.mVisibleBlocks = new HashMap<String, List<TextView>>();
        this.mDefaultsInitiated = new AtomicBoolean(false);
        this.mDoNotDraw = (ViewTreeObserver$OnPreDrawListener)new EnhancedSubtitleManager$1(this);
        if (playerFragment.getNetflixActivity().isTablet()) {
            this.mDefaultTextSize = this.mPlayerFragment.getResources().getDimension(2131427855);
        }
        else {
            this.mDefaultTextSize = this.mPlayerFragment.getResources().getDimension(2131427853);
        }
        this.mTransparent = playerFragment.getActivity().getResources().getColor(17170445);
    }
    
    private void addRegion(final Region initialRegionPosition) {
        final VerticalAlignment verticalAlignment = null;
        Log.d("nf_subtitles_render", "Add region ");
        final LinearLayout linearLayout = new LinearLayout((Context)this.mPlayerFragment.getActivity());
        linearLayout.setVisibility(4);
        linearLayout.setTag((Object)initialRegionPosition.getId());
        final RelativeLayout$LayoutParams setInitialRegionPosition = this.setInitialRegionPosition(initialRegionPosition);
        linearLayout.setOrientation(1);
        this.mDisplayArea.addView((View)linearLayout, (ViewGroup$LayoutParams)setInitialRegionPosition);
        this.mRegions.put(initialRegionPosition.getId(), linearLayout);
        HorizontalAlignment horizontalAlignment;
        if (initialRegionPosition != null && initialRegionPosition.getHorizontalAlignment() != null) {
            horizontalAlignment = initialRegionPosition.getHorizontalAlignment();
        }
        else {
            horizontalAlignment = null;
        }
        VerticalAlignment verticalAlignment2 = verticalAlignment;
        if (initialRegionPosition != null) {
            verticalAlignment2 = verticalAlignment;
            if (initialRegionPosition.getVerticalAlignment() != null) {
                verticalAlignment2 = initialRegionPosition.getVerticalAlignment();
            }
        }
        if (horizontalAlignment != null && verticalAlignment2 != null) {
            if (Log.isLoggable()) {
                Log.v("nf_subtitles_render", "Set gravity for region " + initialRegionPosition.getId());
            }
            linearLayout.setGravity(SubtitleUtils.toGravity(horizontalAlignment, verticalAlignment2));
        }
    }
    
    private Pair<Integer, Integer> calculateRegionSize(final TextSubtitleBlock textSubtitleBlock, final LinearLayout linearLayout) {
        int i = 0;
        Object o = null;
        int n = 0;
        int n2 = 0;
        while (i < textSubtitleBlock.getTextNodes().size()) {
            final SubtitleTextNode subtitleTextNode = textSubtitleBlock.getTextNodes().get(i);
            final TextView textView = this.toTextView(subtitleTextNode);
            int n4;
            int n5;
            if (subtitleTextNode.getLineBreaks() > 0) {
                Log.d("nf_subtitles_render", "This node belongs to its own line");
                int n3 = n;
                int measuredWidth = n2;
                if (o != null) {
                    ((LinearLayout)o).measure(0, 0);
                    n3 = n + ((LinearLayout)o).getMeasuredHeight();
                    if ((measuredWidth = n2) < ((LinearLayout)o).getMeasuredWidth()) {
                        measuredWidth = ((LinearLayout)o).getMeasuredWidth();
                        n3 = n3;
                    }
                }
                n4 = n3;
                n5 = measuredWidth;
                o = null;
            }
            else {
                final int n6 = n2;
                n4 = n;
                n5 = n6;
            }
            LinearLayout linearLayout2;
            if (SubtitleUtils.isNextNodeInSameLine(textSubtitleBlock.getTextNodes(), i)) {
                Log.d("nf_subtitles_render", "Next node is in same line, add current node to horizontal LL.");
                if ((linearLayout2 = (LinearLayout)o) == null) {
                    linearLayout2 = new LinearLayout((Context)this.mPlayerFragment.getActivity());
                    linearLayout2.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -2));
                    linearLayout2.setOrientation(0);
                    linearLayout.addView((View)linearLayout2);
                }
            }
            else {
                Log.d("nf_subtitles_render", "Next node is not in same line");
                linearLayout2 = (LinearLayout)o;
            }
            int measuredWidth2;
            if (linearLayout2 != null) {
                Log.d("nf_subtitles_render", "Adding node to horizontal wrapper");
                linearLayout2.addView((View)textView);
                measuredWidth2 = n5;
            }
            else {
                Log.d("nf_subtitles_render", "Adding node directly to a window region");
                linearLayout.addView((View)textView);
                final int n7 = n4 += textView.getMeasuredHeight();
                if ((measuredWidth2 = n5) < textView.getMeasuredWidth()) {
                    measuredWidth2 = textView.getMeasuredWidth();
                    n4 = n7;
                }
            }
            ++i;
            o = linearLayout2;
            n = n4;
            n2 = measuredWidth2;
        }
        int measuredWidth3;
        if (o != null) {
            Log.d("nf_subtitles_render", "Last horizontal wrapper needs to be measured");
            ((LinearLayout)o).measure(0, 0);
            final int n8 = n += ((LinearLayout)o).getMeasuredHeight();
            if ((measuredWidth3 = n2) < ((LinearLayout)o).getMeasuredWidth()) {
                measuredWidth3 = ((LinearLayout)o).getMeasuredWidth();
                n = n8;
            }
        }
        else {
            Log.d("nf_subtitles_render", "No unmeasured last horizontal wrapper");
            measuredWidth3 = n2;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Region after text nodes added, calculated w: " + measuredWidth3 + ", h: " + n);
        }
        linearLayout.measure(0, 0);
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Region after text nodes added, measured w: " + linearLayout.getMeasuredWidth() + ", h: " + linearLayout.getMeasuredHeight());
        }
        return (Pair<Integer, Integer>)new Pair((Object)linearLayout.getMeasuredWidth(), (Object)linearLayout.getMeasuredHeight());
    }
    
    private void clearDelayedPosts() {
        while (true) {
            synchronized (this) {
                if (this.mPendingActions == null || this.mPendingActions.size() < 1) {
                    return;
                }
                final Iterator<Runnable> iterator = this.mPendingActions.iterator();
                while (iterator.hasNext()) {
                    this.mHandler.removeCallbacks((Runnable)iterator.next());
                }
            }
            this.mPendingActions.clear();
        }
    }
    
    private void createRegions(final Region[] array) {
        this.setMarginsForSafeDisplayArea();
        this.mVisibleBlocks.put("DEFAULT", new ArrayList<TextView>());
        if (array == null || array.length < 1) {
            Log.e("nf_subtitles_render", "createRegions:: no region found to be added!");
        }
        else {
            for (int i = 0; i < array.length; ++i) {
                final Region region = array[i];
                if (region == null) {
                    Log.e("nf_subtitles_render", "createRegions:: region is null on position " + i);
                }
                else if (region.getId() == null) {
                    Log.e("nf_subtitles_render", "createRegions:: region ID is null on position " + i + ". Ignore region " + region);
                }
                else {
                    if (Log.isLoggable()) {
                        Log.v("nf_subtitles_render", "Create linear layout from region " + region + " on position " + i);
                    }
                    this.addRegion(region);
                }
            }
        }
    }
    
    private LinearLayout findRegionLayout(final TextSubtitleBlock textSubtitleBlock) {
        Log.d("nf_subtitles_render", "findRegionLayout start");
        final Region region = textSubtitleBlock.getRegion();
        if (region == null || region.getId() == null) {
            Log.d("nf_subtitles_render", "Block does not have region, put it in default region");
            return this.mDefaultRegion;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Block have region " + region);
        }
        final LinearLayout linearLayout = this.mRegions.get(region.getId());
        if (linearLayout == null) {
            if (Log.isLoggable()) {
                Log.e("nf_subtitles_render", "Regions known " + this.mRegions.size());
                final Iterator<String> iterator = this.mRegions.keySet().iterator();
                while (iterator.hasNext()) {
                    Log.e("nf_subtitles_render", "Region '" + iterator.next() + "' found!");
                }
                Log.e("nf_subtitles_render", "Region NOT found for '" + region.getId() + "'. Default to default region!");
            }
            return this.mDefaultRegion;
        }
        Log.d("nf_subtitles_render", "LL for region found!");
        return linearLayout;
    }
    
    private List<TextView> findTextView(final String s, final List<TextView> list) {
        final ArrayList<TextView> list2 = new ArrayList<TextView>();
        for (final TextView textView : list) {
            if (textView == null) {
                Log.e("nf_subtitles_render", "Text view can not be null!");
            }
            else {
                if (!s.equals(textView.getTag())) {
                    continue;
                }
                Log.d("nf_subtitles_render", "Text view found for removal");
                list2.add(textView);
            }
        }
        if (list2.size() < 1) {
            Log.w("nf_subtitles_render", "Text view NOT found for removal for block " + s);
        }
        return list2;
    }
    
    private int getHorizontalOffsetForDisplayArea() {
        final MeasureTranslator mMeasureTranslator = this.mMeasureTranslator;
        if (mMeasureTranslator == null) {
            return 0;
        }
        return mMeasureTranslator.getHorizontalOffset();
    }
    
    private int getVerticalOffsetForDisplayArea() {
        final MeasureTranslator mMeasureTranslator = this.mMeasureTranslator;
        if (mMeasureTranslator == null) {
            return 0;
        }
        return mMeasureTranslator.getVerticalOffset();
    }
    
    private SubtitleUtils$Margins moveRegionInsideVisibleDisplayArea(final SubtitleUtils$Margins subtitleUtils$Margins, int left, int right, final Rect rect) {
        final int n = 0;
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Margins, left: " + subtitleUtils$Margins.left + ", top: " + subtitleUtils$Margins.top + ", right: " + subtitleUtils$Margins.right + ", bottom: " + subtitleUtils$Margins.bottom);
        }
        final SubtitleUtils$Margins subtitleUtils$Margins2 = new SubtitleUtils$Margins();
        final int top = subtitleUtils$Margins.top;
        final int left2 = subtitleUtils$Margins.left;
        int bottom;
        if (rect.height() >= right) {
            Log.d("nf_subtitles_render", "Original region is high enough, keep original measure");
            bottom = subtitleUtils$Margins.bottom;
        }
        else {
            Log.d("nf_subtitles_render", "Original region is NOT high enough, recalculate");
            bottom = subtitleUtils$Margins.top + right + this.mVerticalRegionPadding * 2;
        }
        if (rect.width() >= left) {
            Log.d("nf_subtitles_render", "Original region is wide enough, keep original measure");
            right = subtitleUtils$Margins.right;
        }
        else {
            Log.d("nf_subtitles_render", "Original region is NOT wide enough, recalculate");
            right = subtitleUtils$Margins.left + left + this.mHorizontalRegionPadding * 2;
        }
        Log.d("nf_subtitles_render", "Check if region bottom is lower than display area");
        if (bottom > this.mDisplayArea.getHeight()) {
            final int n2 = left = top - (bottom - this.mDisplayArea.getHeight());
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "New top " + n2);
                left = n2;
            }
        }
        else {
            Log.d("nf_subtitles_render", "No need to change top");
            left = top;
        }
        Log.d("nf_subtitles_render", "Check if region top is higher than display area");
        int top2;
        if (left < 0) {
            Log.d("nf_subtitles_render", "Top is 0");
            top2 = 0;
        }
        else {
            Log.d("nf_subtitles_render", "No need to change top");
            top2 = left;
        }
        Log.d("nf_subtitles_render", "Check if region right is pass right of display area");
        if (right > this.mDisplayArea.getWidth()) {
            right = (left = left2 - (right - this.mDisplayArea.getWidth()));
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "New left " + right);
                left = right;
            }
        }
        else {
            Log.d("nf_subtitles_render", "No need to change left");
            left = left2;
        }
        Log.d("nf_subtitles_render", "Check if region left is pass left of display area");
        if (left < 0) {
            Log.d("nf_subtitles_render", "Left is 0");
            left = n;
        }
        else {
            Log.d("nf_subtitles_render", "No need to change left");
        }
        subtitleUtils$Margins2.left = left;
        subtitleUtils$Margins2.top = top2;
        return subtitleUtils$Margins2;
    }
    
    private void removeAll(final boolean b) {
        this.clearDelayedPosts();
        this.removeVisibleSubtitleBlocks(b);
    }
    
    private void removeBlock(final String s, final List<TextView> list, final String s2) {
        if (s == null) {
            Log.w("nf_subtitles_render", "Block id can not be null!");
        }
        else {
            if (list == null || list.size() < 1) {
                Log.d("nf_subtitles_render", "Views are null or empty, nothing to remove!");
                return;
            }
            if ("DEFAULT".equals(s2)) {
                this.removeViews(this.mRegions.get(s2), this.findTextView(s2, list));
            }
            final Iterator<TextView> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    Log.e("nf_subtitles_render", "Text view can not be null!");
                }
            }
        }
    }
    
    private void removeRegions() {
        while (true) {
        Label_0168:
            while (true) {
                final String s;
                final LinearLayout linearLayout;
                Label_0125: {
                    synchronized (this) {
                        if (this.mRegions.size() < 1) {
                            Log.d("nf_subtitles_render", "removeRegions:: no region found to remove.");
                        }
                        else {
                            final Iterator<String> iterator = this.mRegions.keySet().iterator();
                            if (!iterator.hasNext()) {
                                break Label_0168;
                            }
                            s = iterator.next();
                            linearLayout = this.mRegions.get(s);
                            if (linearLayout != null) {
                                break Label_0125;
                            }
                            if (Log.isLoggable()) {
                                Log.e("nf_subtitles_render", "Removing region " + s + " is not possible. Region is null!");
                            }
                        }
                        return;
                    }
                }
                if (Log.isLoggable()) {
                    Log.v("nf_subtitles_render", "Removing region " + s);
                }
                this.mDisplayArea.removeView((View)linearLayout);
                continue;
            }
            this.mRegions.clear();
        }
    }
    
    private void removeSubtitleBlock(final TextSubtitleBlock textSubtitleBlock) {
        if (textSubtitleBlock == null) {
            Log.e("nf_subtitles_render", "Subtitle block can not be null!");
            return;
        }
        final Region region = textSubtitleBlock.getRegion();
        if (region == null) {
            Log.d("nf_subtitles_render", "Remove block from default region for id " + textSubtitleBlock.getId());
            this.removeBlock(textSubtitleBlock.getId(), this.mVisibleBlocks.get("DEFAULT"), "DEFAULT");
            return;
        }
        final String id = region.getId();
        if (id == null) {
            Log.e("nf_subtitles_render", "Region id can NOT be null!");
            return;
        }
        Log.d("nf_subtitles_render", "Remove block from region " + id + " for block id " + textSubtitleBlock.getId());
        this.removeBlock(textSubtitleBlock.getId(), this.mVisibleBlocks.get(id), id);
    }
    
    private void removeViews(final LinearLayout linearLayout, final List<TextView> list) {
        if (linearLayout == null) {
            Log.e("nf_subtitles_render", "Region is null, can not remove views!");
        }
        else {
            if (list == null) {
                Log.e("nf_subtitles_render", "Blocks are null, can not remove views!");
                return;
            }
            for (final TextView textView : list) {
                Log.d("nf_subtitles_render", "Removing block from region " + linearLayout.getTag());
                linearLayout.removeView((View)textView);
                linearLayout.setVisibility(4);
            }
        }
    }
    
    private void setAspectRatioIfNeeded() {
        final Point displayAspectRatioDimension = this.mPlayerFragment.getPlayer().getDisplayAspectRatioDimension();
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "AspectRatio dimensions: " + displayAspectRatioDimension);
        }
        if (displayAspectRatioDimension == null) {
            Log.w("nf_subtitles_render", "AspectRatio is null!");
            return;
        }
        if (this.mAspectRatio != null && this.mAspectRatio.equals((Object)displayAspectRatioDimension)) {
            Log.d("nf_subtitles_render", "AspectRatio not changed");
            return;
        }
        Log.d("nf_subtitles_render", "AspectRatio is changed, update");
        this.mAspectRatio = displayAspectRatioDimension;
        this.mMeasureTranslator = MeasureTranslator.createMeasureTranslator(displayAspectRatioDimension.x, displayAspectRatioDimension.y, (View)this.mDisplayArea);
    }
    
    private void setBackgroundColorToRegion(final LinearLayout linearLayout, final TextSubtitleBlock textSubtitleBlock) {
        final TextStyle userDefaults = ((TextSubtitleParser)this.mParser).getUserDefaults();
        Integer n = null;
        Label_0078: {
            if (userDefaults == null) {
                Log.d("nf_subtitles_render", "No user overides for window color");
                if (textSubtitleBlock.getTextNodes().size() > 0) {
                    final TextStyle style = textSubtitleBlock.getTextNodes().get(0).getStyle();
                    if (style != null && style.getWindowColor() != null) {
                        n = ColorMapping.resolveColor(style.getWindowOpacity(), style.getWindowColor());
                        break Label_0078;
                    }
                }
                n = null;
            }
            else {
                Log.d("nf_subtitles_render", "User overides found for window color");
                n = ColorMapping.resolveColor(userDefaults.getWindowOpacity(), userDefaults.getWindowColor());
            }
        }
        if (n == null) {
            Log.d("nf_subtitles_render", "Sets region background color to transparent");
            linearLayout.setBackgroundColor(this.mPlayerFragment.getResources().getColor(17170445));
            return;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Sets region background color to " + n);
        }
        linearLayout.setBackgroundColor((int)n);
    }
    
    private void setDefaults() {
        Log.v("nf_subtitles_render", "Find root display area");
        if (!this.isDisplayAreaVisible()) {
            return;
        }
        Log.v("nf_subtitles_render", "Create safe display area");
        this.mSafeDisplayArea = new RelativeLayout((Context)this.mPlayerFragment.getActivity());
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, -1);
        relativeLayout$LayoutParams.addRule(13);
        this.mDisplayArea.addView((View)this.mSafeDisplayArea, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        Log.v("nf_subtitles_render", "Create safe display area done.");
        Log.v("nf_subtitles_render", "Create default region");
        this.mDefaultRegion = new LinearLayout((Context)this.mPlayerFragment.getActivity());
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(-1, -2);
        this.mDefaultRegion.setOrientation(1);
        relativeLayout$LayoutParams2.addRule(12);
        this.mSafeDisplayArea.addView((View)this.mDefaultRegion, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
        this.mDefaultRegion.setVisibility(4);
        this.mVisibleBlocks.put("DEFAULT", new ArrayList<TextView>());
        this.mDefaultsInitiated.set(true);
    }
    
    private RelativeLayout$LayoutParams setInitialRegionPosition(final Region region) {
        Log.d("nf_subtitles_render", "Set initial region position");
        final DoubleLength extent = region.getExtent();
        final DoubleLength origin = region.getOrigin();
        final Rect regionForRectangle = SubtitleUtils.createRegionForRectangle((View)this.mDisplayArea, extent, origin);
        RelativeLayout$LayoutParams relativeLayout$LayoutParams;
        if (extent == null || !extent.isValid()) {
            Log.d("nf_subtitles_render", "Region w/h not know, wrap around content");
            relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-2, -2);
        }
        else {
            final int width = regionForRectangle.width();
            final int height = regionForRectangle.height();
            if (Log.isLoggable()) {
                Log.v("nf_subtitles_render", "Display area: w/h: " + this.mDisplayArea.getWidth() + "/" + this.mDisplayArea.getHeight() + " Region w/h " + width + "/" + height + " known, set them");
            }
            final int n = this.mDisplayArea.getHeight() / 4;
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "h: " + height + ", maxHeight: " + n);
            }
            if (height < n) {
                Log.d("nf_subtitles_render", "Use wrap content for height");
                relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(width, -2);
            }
            else {
                Log.d("nf_subtitles_render", "Use region height");
                relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(width, height);
            }
        }
        if (origin != null && origin.isValid() && extent != null && extent.isValid()) {
            this.setMargins(relativeLayout$LayoutParams, regionForRectangle);
        }
        return relativeLayout$LayoutParams;
    }
    
    private void setMargins(final RelativeLayout$LayoutParams relativeLayout$LayoutParams, final Rect rect) {
        final int n = 0;
        final int left = rect.left;
        final int top = rect.top;
        final int n2 = this.mDisplayArea.getWidth() - rect.right;
        final int n3 = this.mDisplayArea.getHeight() - rect.bottom;
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Display area w/h" + this.mDisplayArea.getWidth() + "/" + this.mDisplayArea.getHeight());
            Log.d("nf_subtitles_render", "Margins, left: " + left + ", top: " + top + ", right: " + n2 + ", bottom: " + n3);
        }
        int n4 = n3;
        int n5 = top;
        if (n3 < 0) {
            Log.w("nf_subtitles_render", "Bottom margin is less than 0, correct");
            n5 = top + n3;
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "Margins, left: " + left + ", top: " + n5 + ", right: " + n2 + ", bottom: " + 0);
            }
            n4 = 0;
        }
        int n7;
        int n8;
        if (n5 < 0) {
            Log.w("nf_subtitles_render", "Top margin is less than 0, correct");
            int n6;
            if ((n6 = n4 + n5) < 0) {
                Log.w("nf_subtitles_render", "Bottom margin is left than 0, after fixing top, set to 0");
                n6 = 0;
            }
            n7 = n6;
            n8 = n;
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "Margins, left: " + left + ", top: " + 0 + ", right: " + n2 + ", bottom: " + n6);
                n8 = n;
                n7 = n6;
            }
        }
        else {
            n8 = n5;
            n7 = n4;
        }
        relativeLayout$LayoutParams.setMargins(left, n8, n2, n7);
    }
    
    private void setMarginsForSafeDisplayArea() {
        Log.d("nf_subtitles_render", "Sets margin for safe display area");
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.mSafeDisplayArea.getLayoutParams();
        final int n = (int)(this.mDisplayArea.getWidth() / 100 * 5.0f);
        final int n2 = (int)(this.mDisplayArea.getHeight() / 100 * 5.0f);
        layoutParams.setMargins(n, n2, n, n2);
        this.mSafeDisplayArea.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    private void setRegionPosition(final LinearLayout linearLayout, final Region region) {
        Log.d("nf_subtitles_render", "Set region position");
        final DoubleLength extent = region.getExtent();
        final DoubleLength origin = region.getOrigin();
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)linearLayout.getLayoutParams();
        final Rect regionForRectangle = SubtitleUtils.createRegionForRectangle((View)this.mDisplayArea, extent, origin);
        if (extent == null || !extent.isValid()) {
            Log.d("nf_subtitles_render", "Region w/h not know, skip setting w/h");
        }
        else {
            final int width = regionForRectangle.width();
            final int height = regionForRectangle.height();
            if (Log.isLoggable()) {
                Log.v("nf_subtitles_render", "Display area: w/h: " + this.mDisplayArea.getWidth() + "/" + this.mDisplayArea.getHeight() + " Region w/h " + width + "/" + height + " known, set them");
            }
            final int n = this.mDisplayArea.getHeight() / 4;
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "h: " + height + ", maxHeight: " + n);
            }
            if (height < n) {
                Log.d("nf_subtitles_render", "Use wrap content for height");
                layoutParams.width = width;
                layoutParams.height = -2;
            }
            else {
                Log.d("nf_subtitles_render", "Use region height");
                layoutParams.width = width;
                layoutParams.height = height;
            }
        }
        if (origin != null && origin.isValid() && extent != null && extent.isValid()) {
            this.setMargins(layoutParams, regionForRectangle);
        }
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        linearLayout.requestLayout();
    }
    
    private void setVisibilityForAllRegions(final boolean b) {
        for (final LinearLayout linearLayout : this.mRegions.values()) {
            int visibility;
            if (b) {
                visibility = 0;
            }
            else {
                visibility = 4;
            }
            linearLayout.setVisibility(visibility);
        }
    }
    
    private boolean shouldUpdateRegionPosition(final LinearLayout linearLayout, final Region region, final int n, final int n2) {
        if (DoubleLength.canUse(region.getExtent()) && DoubleLength.canUse(region.getOrigin())) {
            final Rect regionForRectangle = SubtitleUtils.createRegionForRectangle((View)this.mDisplayArea, region.getExtent(), region.getOrigin());
            if (regionForRectangle.height() < n2 && regionForRectangle.width() < n) {
                Log.d("nf_subtitles_render", "Text does not fit into region by height AND width. Update region position taking into account measured data.");
                return true;
            }
            if (regionForRectangle.height() < n2) {
                Log.d("nf_subtitles_render", "Text does not fit into region by height. Update region position taking into account measured data.");
                return true;
            }
            if (regionForRectangle.width() < n) {
                Log.d("nf_subtitles_render", "Text does not fit into region by width. Update region position taking into account measured data.");
                return true;
            }
            Log.d("nf_subtitles_render", "Text fits into region.");
        }
        return false;
    }
    
    private void showSubtitleBlock(final TextSubtitleBlock textSubtitleBlock, final List<ViewUtils$ViewComparator> list) {
        if (textSubtitleBlock == null || textSubtitleBlock.getTextNodes().size() < 1) {
            Log.e("nf_subtitles_render", "Block is empty! Can not show!");
            return;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Show subtitle block: " + textSubtitleBlock);
        }
        final LinearLayout regionLayout = this.findRegionLayout(textSubtitleBlock);
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "LL for region " + regionLayout.getTag());
        }
        regionLayout.setVisibility(4);
        if (list != null) {
            list.add(new ViewUtils$ViewComparator((View)regionLayout));
        }
        int n;
        if (!SubtitleUtils.isPositionDefinedInBlock(regionLayout, textSubtitleBlock)) {
            n = 1;
        }
        else {
            n = 0;
        }
        SubtitleUtils.setAlignmentToRegion(regionLayout, textSubtitleBlock);
        LinearLayout linearLayout;
        if (n != 0) {
            Log.d("nf_subtitles_render", "Using extent/origin from original region, add wrapper region that will be wrapped around block and that will be used for background color for region");
            linearLayout = new LinearLayout((Context)this.mPlayerFragment.getActivity());
            SubtitleUtils.setAlignmentToRegion(linearLayout, textSubtitleBlock);
            linearLayout.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -2));
            linearLayout.setPadding(this.mHorizontalRegionPadding, this.mVerticalRegionPadding, this.mHorizontalRegionPadding, this.mVerticalRegionPadding);
            linearLayout.setOrientation(1);
            regionLayout.addView((View)linearLayout);
        }
        else {
            Log.d("nf_subtitles_render", "Using extent/origin from block, region will be wrapped around block and background color will be applied to it.");
            linearLayout = regionLayout;
        }
        final Pair<Integer, Integer> calculateRegionSize = this.calculateRegionSize(textSubtitleBlock, linearLayout);
        this.updatePositionIfNeeded(regionLayout, textSubtitleBlock, (int)calculateRegionSize.first, (int)calculateRegionSize.second);
        this.setBackgroundColorToRegion(linearLayout, textSubtitleBlock);
        textSubtitleBlock.displayed();
    }
    
    private void showSubtitleBlocks(final List<SubtitleBlock> list) {
        final ViewTreeObserver viewTreeObserver = this.mDisplayArea.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(this.mDoNotDraw);
        final ArrayList<ViewUtils$ViewComparator> list2 = new ArrayList<ViewUtils$ViewComparator>();
        final Iterator<SubtitleBlock> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.showSubtitleBlock((TextSubtitleBlock)iterator.next(), list2);
        }
        this.moveBlocksAppartIfNeeded(list2);
        this.makeItVisible(list2);
        viewTreeObserver.removeOnPreDrawListener(this.mDoNotDraw);
        this.mDisplayArea.forceLayout();
        this.mDisplayArea.requestLayout();
        this.mDisplayArea.invalidate();
        Log.d("nf_subtitles_render", "Add displayed block to pending queue to be removed at end time");
        this.handleDelayedSubtitleBlocks(list, false);
    }
    
    private TextView toTextView(final SubtitleTextNode subtitleTextNode) {
        final String text = SubtitleUtils.createText(subtitleTextNode.getText(), subtitleTextNode.getLineBreaks());
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Create text view from " + subtitleTextNode);
            Log.d("nf_subtitles_render", "Text to set " + text);
        }
        final TextStyle textStyle = new TextStyle();
        textStyle.merge(((TextSubtitleParser)this.mParser).getUserDefaults());
        textStyle.merge(subtitleTextNode.getStyle());
        AutoResizeTextView autoResizeTextView;
        if (SubtitleUtils.isStrokeTextViewRequired(textStyle)) {
            autoResizeTextView = new StrokeTextView((Context)this.mPlayerFragment.getActivity());
        }
        else {
            autoResizeTextView = new AutoResizeTextView((Context)this.mPlayerFragment.getActivity());
        }
        autoResizeTextView.setEllipsize((TextUtils$TruncateAt)null);
        autoResizeTextView.setSingleLine(true);
        autoResizeTextView.setLayoutParams(new ViewGroup$LayoutParams(-2, -2));
        if (Boolean.TRUE.equals(subtitleTextNode.getStyle().getUnderline())) {
            Log.d("nf_subtitles_render", "Sets underline");
            autoResizeTextView.setUnderline(true);
        }
        SubtitleUtils.applyStyle(autoResizeTextView, textStyle, this.mDefaultTextSize);
        autoResizeTextView.setText((CharSequence)text);
        autoResizeTextView.setGravity(119);
        autoResizeTextView.measure(0, 0);
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Post measure text view mw: " + autoResizeTextView.getMeasuredWidth() + ", mh: " + autoResizeTextView.getMeasuredHeight());
        }
        return autoResizeTextView;
    }
    
    private void updatePositionIfNeeded(final LinearLayout linearLayout, final TextSubtitleBlock textSubtitleBlock, final int n, final int n2) {
        Log.d("nf_subtitles_render", "updatePositionIfNeeded start");
        final Region region = textSubtitleBlock.getRegion();
        if (region == null) {
            Log.d("nf_subtitles_render", "updatePositionIfNeeded no region, no update");
        }
        else {
            Log.d("nf_subtitles_render", "updatePositionIfNeeded start for real");
            if (textSubtitleBlock.getTextNodes().size() < 1) {
                Log.w("nf_subtitles_render", "updatePositionIfNeeded no text blocks!");
                return;
            }
            final SubtitleTextNode subtitleTextNode = textSubtitleBlock.getTextNodes().get(0);
            if (subtitleTextNode == null || subtitleTextNode.getStyle() == null) {
                Log.w("nf_subtitles_render", "updatePositionIfNeeded p missing");
                return;
            }
            final DoubleLength extent = textSubtitleBlock.getStyle().getExtent();
            final DoubleLength origin = textSubtitleBlock.getStyle().getOrigin();
            if (DoubleLength.canUse(extent) && DoubleLength.canUse(origin)) {
                Log.w("nf_subtitles_render", "updatePositionIfNeeded using block extent and origin overrides");
                this.updateRegionPosition(linearLayout, n, n2, extent, origin);
                return;
            }
            Log.w("nf_subtitles_render", "updatePositionIfNeeded using region defaults for extent and origin");
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "Region h " + linearLayout.getHeight() + ", needed h " + n2);
            }
            this.setRegionPosition(linearLayout, region);
            if (this.shouldUpdateRegionPosition(linearLayout, region, n, n2)) {
                this.updateRegionPosition(linearLayout, n, n2, region.getExtent(), region.getOrigin());
            }
        }
    }
    
    private void updateRegionPosition(final LinearLayout linearLayout, int left, int n, final DoubleLength doubleLength, final DoubleLength doubleLength2) {
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)linearLayout.getLayoutParams();
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Display area: w " + this.mDisplayArea.getWidth() + ", h " + this.mDisplayArea.getHeight());
        }
        final Rect regionForRectangle = SubtitleUtils.createRegionForRectangle((View)this.mDisplayArea, doubleLength, doubleLength2);
        final SubtitleUtils$Margins moveRegionInsideVisibleDisplayArea = this.moveRegionInsideVisibleDisplayArea(SubtitleUtils.getMarginsForRectangle((View)this.mDisplayArea, doubleLength, doubleLength2), left, n, regionForRectangle);
        layoutParams.setMargins(moveRegionInsideVisibleDisplayArea.left, moveRegionInsideVisibleDisplayArea.top, 0, 0);
        if (regionForRectangle.height() >= n) {
            Log.d("nf_subtitles_render", "Original region is high enough, keep original measure");
            layoutParams.height = regionForRectangle.height();
            n = moveRegionInsideVisibleDisplayArea.top + regionForRectangle.height();
        }
        else {
            Log.d("nf_subtitles_render", "Original region is NOT high enough, recalculate");
            layoutParams.height = this.mVerticalRegionPadding * 2 + n;
            n = moveRegionInsideVisibleDisplayArea.top + layoutParams.height;
        }
        if (regionForRectangle.width() >= left) {
            Log.d("nf_subtitles_render", "Original region is wide enough, keep original measure");
            layoutParams.width = regionForRectangle.width();
            left = moveRegionInsideVisibleDisplayArea.left;
            left += regionForRectangle.width();
        }
        else {
            Log.d("nf_subtitles_render", "Original region is NOT wide enough, recalculate");
            layoutParams.width = this.mHorizontalRegionPadding * 2 + left;
            left = moveRegionInsideVisibleDisplayArea.left + layoutParams.width;
        }
        linearLayout.setTag((Object)new Rect(moveRegionInsideVisibleDisplayArea.left, moveRegionInsideVisibleDisplayArea.top, left, n));
        linearLayout.setPadding(this.mHorizontalRegionPadding, this.mVerticalRegionPadding, this.mHorizontalRegionPadding, this.mVerticalRegionPadding);
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    @Override
    public boolean canHandleSubtitleProfile(final ISubtitleDef$SubtitleProfile subtitleDef$SubtitleProfile) {
        return subtitleDef$SubtitleProfile != null && subtitleDef$SubtitleProfile != ISubtitleDef$SubtitleProfile.IMAGE && subtitleDef$SubtitleProfile != ISubtitleDef$SubtitleProfile.IMAGE_ENC;
    }
    
    @Override
    public void clear() {
        synchronized (this) {
            Log.v("nf_subtitles_render", "Remove current and pending actions");
            this.removeAll(true);
        }
    }
    
    @Override
    public void clearPendingUpdates() {
        this.clearDelayedPosts();
    }
    
    @Override
    protected Runnable createRunnable(final SubtitleBlock subtitleBlock, final boolean b) {
        final EnhancedSubtitleManager$2 enhancedSubtitleManager$2 = new EnhancedSubtitleManager$2(this, b, (TextSubtitleBlock)subtitleBlock);
        this.mPendingActions.add(enhancedSubtitleManager$2);
        return enhancedSubtitleManager$2;
    }
    
    @Override
    public ISubtitleDef$SubtitleProfile getSubtitleProfile() {
        return ISubtitleDef$SubtitleProfile.ENHANCED;
    }
    
    @Override
    public void onPlayerOverlayVisibiltyChange(final boolean b) {
        while (true) {
            synchronized (this) {
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles_render", "Player UI is now visible: " + b);
                }
                if (this.mDisplayArea != null) {
                    final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.mDisplayArea.getLayoutParams();
                    if (b) {
                        final int displayAreaMarginTop = this.getDisplayAreaMarginTop();
                        final int displayAreaMarginBottom = this.getDisplayAreaMarginBottom();
                        if (Log.isLoggable()) {
                            Log.d("nf_subtitles_render", "Add bottom/top margin to display area on visible. Bottom margin " + displayAreaMarginBottom + ", top margin: " + displayAreaMarginTop);
                        }
                        layoutParams.setMargins(this.getHorizontalOffsetForDisplayArea(), displayAreaMarginTop, this.getHorizontalOffsetForDisplayArea(), displayAreaMarginBottom);
                    }
                    else {
                        Log.d("nf_subtitles_render", "Reset all margins to display area on not visible");
                        layoutParams.setMargins(this.getHorizontalOffsetForDisplayArea(), this.getVerticalOffsetForDisplayArea(), this.getHorizontalOffsetForDisplayArea(), this.getVerticalOffsetForDisplayArea());
                    }
                    this.removeVisibleSubtitleBlocks(true);
                    this.mDisplayArea.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
                    this.mDisplayArea.requestLayout();
                    return;
                }
            }
            Log.w("nf_subtitles_render", "Display area is null, unable to set margins!");
        }
    }
    
    @Override
    public void onSubtitleChange(final SubtitleScreen subtitleScreen) {
        while (true) {
            Label_0051: {
                synchronized (this) {
                    Log.d("nf_subtitles_render", "EnhancedSubtitleManager:: update subtitle data");
                    if (subtitleScreen == null) {
                        Log.e("nf_subtitles_render", "Subtitle data is null. This should never happen!");
                    }
                    else {
                        if (subtitleScreen.getParser() != null) {
                            break Label_0051;
                        }
                        Log.e("nf_subtitles_render", "Subtitle parser is null. This should never happen!");
                    }
                    return;
                }
            }
            final SubtitleScreen subtitleScreen2;
            if (subtitleScreen2.getParser() instanceof TextSubtitleParser) {
                this.mParser = subtitleScreen2.getParser();
                if (!this.mDefaultsInitiated.get()) {
                    Log.d("nf_subtitles_render", "Try to set defaults. They were not initialized before");
                    this.setDefaults();
                    if (!this.mDefaultsInitiated.get()) {
                        Log.w("nf_subtitles_render", "Initialization was NOT ok, exit.");
                        return;
                    }
                    Log.d("nf_subtitles_render", "Initialization was ok, proceed with subtitles.");
                }
                this.removeAll(false);
                this.setAspectRatioIfNeeded();
                final int hashCode = subtitleScreen2.getParser().hashCode();
                if (this.mSubtitleParserId != null && this.mSubtitleParserId == hashCode) {
                    if (Log.isLoggable()) {
                        Log.v("nf_subtitles_render", "Same subtitles file " + this.mSubtitleParserId);
                    }
                }
                else {
                    if (Log.isLoggable()) {
                        Log.v("nf_subtitles_render", "Subtitles file changed. Was " + this.mSubtitleParserId + ", now " + hashCode + ". (Re) create regions!");
                    }
                    this.mSubtitleParserId = subtitleScreen2.getParser().hashCode();
                    this.removeRegions();
                    this.createRegions(((TextSubtitleParser)subtitleScreen2.getParser()).getRegions());
                }
                this.showSubtitleBlocks(subtitleScreen2.getDisplayNowBlocks());
                this.handleDelayedSubtitleBlocks(subtitleScreen2.getDisplayLaterBlocks(), true);
                return;
            }
            if (Log.isLoggable()) {
                Log.w("nf_subtitles_render", "Drop subtitle change! Timing issue, subtitles are supposed to be text based and parser is not, but " + subtitleScreen2.getParser());
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
        LinearLayout linearLayout = null;
        Label_0039_Outer:Label_0054_Outer:
        while (true) {
            while (true) {
            Label_0181:
                while (true) {
                    Label_0171: {
                        synchronized (this) {
                            if (this.mDefaultRegion != null) {
                                this.mDefaultRegion.setBackgroundColor(this.mTransparent);
                                this.mDefaultRegion.setVisibility(4);
                                if (!b) {
                                    break Label_0171;
                                }
                                this.mDefaultRegion.removeAllViews();
                            }
                            for (final String s : this.mRegions.keySet()) {
                                if (Log.isLoggable()) {
                                    Log.d("nf_subtitles_render", "Removing visible blocks for region " + s);
                                }
                                linearLayout = this.mRegions.get(s);
                                if (linearLayout != null) {
                                    break Label_0181;
                                }
                                if (!Log.isLoggable()) {
                                    continue Label_0039_Outer;
                                }
                                Log.d("nf_subtitles_render", "Region not found for id " + s + ". Probably default region.");
                            }
                            break;
                        }
                    }
                    this.mDefaultRegion.removeAllViewsInLayout();
                    continue Label_0054_Outer;
                }
                linearLayout.setBackgroundColor(this.mTransparent);
                linearLayout.setVisibility(4);
                if (b) {
                    linearLayout.removeAllViews();
                    continue;
                }
                linearLayout.removeAllViewsInLayout();
                continue;
            }
        }
        this.mVisibleBlocks.clear();
    }
    // monitorexit(this)
    
    @Override
    public void setSubtitleVisibility(final boolean b) {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "EnhancedSubtitleManager:: set subtitle visibility to visible " + b);
            }
            this.mHandler.post((Runnable)new EnhancedSubtitleManager$3(this, b));
        }
    }
}
