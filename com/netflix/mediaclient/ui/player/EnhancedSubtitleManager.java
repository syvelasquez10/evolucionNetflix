// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.service.player.subtitles.SubtitleScreen;
import android.text.TextUtils$TruncateAt;
import com.netflix.mediaclient.android.widget.AutoResizeTextView;
import android.view.ViewTreeObserver;
import com.netflix.mediaclient.service.player.subtitles.ColorMapping;
import android.graphics.Rect;
import java.util.Comparator;
import com.netflix.mediaclient.service.player.subtitles.DoubleLength;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Iterator;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.service.player.subtitles.SubtitleTextNode;
import android.util.Pair;
import com.netflix.mediaclient.android.widget.StrokeTextView;
import com.netflix.mediaclient.service.player.subtitles.TextStyle;
import com.netflix.mediaclient.service.player.subtitles.Outline;
import com.netflix.mediaclient.service.player.subtitles.VerticalAlignment;
import com.netflix.mediaclient.service.player.subtitles.HorizontalAlignment;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.util.SubtitleUtils;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import com.netflix.mediaclient.service.player.subtitles.Region;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.service.player.subtitles.SubtitleBlock;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.TextView;
import java.util.Map;
import java.util.List;
import com.netflix.mediaclient.service.player.subtitles.SubtitleParser;
import android.os.Handler;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.widget.RelativeLayout;
import java.util.concurrent.atomic.AtomicBoolean;
import android.widget.LinearLayout;

final class EnhancedSubtitleManager implements SubtitleManager
{
    private static final String DEFAULT_REGION_ID = "DEFAULT";
    private static final int H_REGION_PADDING = 5;
    private static final float SAFE_DISPLAY_AREA_MARGIN = 5.0f;
    private static final String TAG = "nf_subtitles_render";
    private static final int V_REGION_PADDING = 1;
    private PlayerActivity mActivity;
    private LinearLayout mDefaultRegion;
    private float mDefaultTextSize;
    private AtomicBoolean mDefaultsInitiated;
    private RelativeLayout mDisplayArea;
    final ViewTreeObserver$OnPreDrawListener mDoNotDraw;
    private Handler mHandler;
    private int mHorizontalRegionPadding;
    private SubtitleParser mParser;
    private List<Runnable> mPendingActions;
    private Map<String, LinearLayout> mRegions;
    private RelativeLayout mSafeDisplayArea;
    private PlayScreen mScreen;
    private Integer mSubtitleParserId;
    private int mTransparent;
    private int mVerticalRegionPadding;
    private Map<String, List<TextView>> mVisibleBlocks;
    
    EnhancedSubtitleManager(final PlayerActivity mActivity) {
        this.mRegions = new HashMap<String, LinearLayout>();
        this.mVisibleBlocks = new HashMap<String, List<TextView>>();
        this.mPendingActions = Collections.synchronizedList(new ArrayList<Runnable>());
        this.mDefaultsInitiated = new AtomicBoolean(false);
        this.mDoNotDraw = (ViewTreeObserver$OnPreDrawListener)new ViewTreeObserver$OnPreDrawListener() {
            public boolean onPreDraw() {
                Log.d("nf_subtitles_render", "onPreDraw on display area");
                return false;
            }
        };
        Log.d("nf_subtitles_render", "EnhancedSubtitleManager created");
        this.mActivity = mActivity;
        this.mScreen = this.mActivity.getScreen();
        if (this.mScreen == null) {
            throw new IllegalArgumentException("Player screen is not initialized!");
        }
        if (this.mActivity.isTablet()) {
            this.mDefaultTextSize = this.mActivity.getResources().getDimension(2131361960);
        }
        else {
            this.mDefaultTextSize = this.mActivity.getResources().getDimension(2131361959);
        }
        Log.v("nf_subtitles_render", "Create handler.");
        this.mHandler = new Handler();
        this.mHorizontalRegionPadding = AndroidUtils.dipToPixels((Context)mActivity, 5);
        this.mVerticalRegionPadding = AndroidUtils.dipToPixels((Context)mActivity, 1);
        this.mTransparent = mActivity.getResources().getColor(17170445);
        this.mDisplayArea = (RelativeLayout)this.mActivity.findViewById(2131165536);
        if (this.mDisplayArea.getWidth() == 0 || this.mDisplayArea.getHeight() == 0) {
            Log.w("nf_subtitles_render", "Display area w/h are 0, display area is not visible yet!");
        }
    }
    
    private void addRegion(final Region region) {
        Log.d("nf_subtitles_render", "Add region ");
        final LinearLayout linearLayout = new LinearLayout((Context)this.mActivity);
        linearLayout.setVisibility(4);
        linearLayout.setTag((Object)region.getId());
        final RelativeLayout$LayoutParams setRegionPosition = this.setRegionPosition(linearLayout, region.getExtent(), region.getOrigin());
        linearLayout.setOrientation(1);
        this.mDisplayArea.addView((View)linearLayout, (ViewGroup$LayoutParams)setRegionPosition);
        this.mRegions.put(region.getId(), linearLayout);
        HorizontalAlignment horizontalAlignment2;
        final HorizontalAlignment horizontalAlignment = horizontalAlignment2 = null;
        if (region != null) {
            horizontalAlignment2 = horizontalAlignment;
            if (region.getHorizontalAlignment() != null) {
                horizontalAlignment2 = region.getHorizontalAlignment();
            }
        }
        VerticalAlignment verticalAlignment2;
        final VerticalAlignment verticalAlignment = verticalAlignment2 = null;
        if (region != null) {
            verticalAlignment2 = verticalAlignment;
            if (region.getVerticalAlignment() != null) {
                verticalAlignment2 = region.getVerticalAlignment();
            }
        }
        if (horizontalAlignment2 != null && verticalAlignment2 != null) {
            if (Log.isLoggable("nf_subtitles_render", 2)) {
                Log.v("nf_subtitles_render", "Set gravity for region " + region.getId());
            }
            linearLayout.setGravity(SubtitleUtils.toGravity(horizontalAlignment2, verticalAlignment2));
        }
    }
    
    private void applyEdge(final TextView textView, final Outline outline) {
        Log.d("nf_subtitles_render", "Apply edge");
        Outline outline2 = outline;
        if (this.mParser.getUserDefaults() != null && this.mParser.getUserDefaults().getOutline() != null) {
            Log.d("nf_subtitles_render", "Using user outline overide");
            outline2 = this.mParser.getUserDefaults().getOutline();
        }
        else {
            Log.d("nf_subtitles_render", "Using content outline if exist");
        }
        if (outline2 == null || !outline2.isOutlineRequired()) {
            Log.d("nf_subtitles_render", "No outline to be applied");
            return;
        }
        final Integer edgeColor = SubtitleUtils.getEdgeColor(outline, this.mParser.getUserDefaults());
        if (edgeColor == null) {
            Log.w("nf_subtitles_render", "Edge color unresolved, not setting anything!");
            return;
        }
        final Outline.Shadow shadow = outline2.getShadow();
        if (shadow == null) {
            Log.w("nf_subtitles_render", "Shadow is null, not setting anything!");
            return;
        }
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "Sets text shadow with color " + edgeColor + ", radius " + shadow.radius + ", dx " + shadow.dx + ", dy " + shadow.dy);
        }
        textView.setShadowLayer(shadow.radius, (float)shadow.dx, (float)shadow.dy, (int)edgeColor);
    }
    
    private void applyOutline(final TextView textView, final TextStyle textStyle) {
        if (textStyle.getOutline() == null) {
            Log.d("nf_subtitles_render", "No outline!");
            return;
        }
        if (textView instanceof StrokeTextView) {
            this.applyStroke((StrokeTextView)textView, textStyle.getOutline());
            return;
        }
        this.applyEdge(textView, textStyle.getOutline());
    }
    
    private void applyStroke(final StrokeTextView strokeTextView, final Outline outline) {
        final Integer edgeColor = SubtitleUtils.getEdgeColor(outline, this.mParser.getUserDefaults());
        int intValue;
        if (outline.getOutlineWidth() != null) {
            intValue = outline.getOutlineWidth();
        }
        else {
            intValue = 1;
        }
        if (edgeColor != null) {
            strokeTextView.setStrokeWidth(intValue);
            strokeTextView.setStrokeColor(edgeColor);
        }
    }
    
    private void applyStyle(final TextView textView, final TextStyle textStyle) {
        final TextStyle userDefaults = this.mParser.getUserDefaults();
        TextStyle textStyleDefault = textStyle;
        if (textStyle == null) {
            Log.w("nf_subtitles_render", "Style is null! Apply default!");
            textStyleDefault = this.mParser.getTextStyleDefault();
        }
        if (textStyleDefault == null) {
            Log.e("nf_subtitles_render", "Style does NOT exist, apply default! This should NEVER happen!");
            textView.setTextColor(this.mActivity.getResources().getColor(2131296355));
            textView.setTextSize(0, this.mDefaultTextSize);
            return;
        }
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "Apply user style first " + userDefaults);
            Log.d("nf_subtitles_render", "Apply style " + textStyleDefault);
        }
        final float fontSizeMultiplier = SubtitleUtils.getFontSizeMultiplier(textStyleDefault, this.mParser.getUserDefaults());
        final float n = this.mDefaultTextSize * fontSizeMultiplier;
        textView.setTextSize(0, n);
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "Text size " + n + ", scale " + fontSizeMultiplier);
        }
        textView.setTypeface(SubtitleUtils.toTypeFace(textStyleDefault, this.mParser.getUserDefaults()), SubtitleUtils.toTypeFaceStyle(textStyleDefault));
        final Integer textColor = SubtitleUtils.getTextColor(textStyleDefault, this.mParser.getUserDefaults());
        if (textColor != null) {
            if (Log.isLoggable("nf_subtitles_render", 3)) {
                Log.d("nf_subtitles_render", "Sets text color to " + textColor);
            }
            textView.setTextColor((int)textColor);
        }
        else {
            Log.w("nf_subtitles_render", "Text color unresolved, not setting anything!");
        }
        final Integer backgroundColor = SubtitleUtils.getBackgroundColor(textStyleDefault, this.mParser.getUserDefaults());
        if (backgroundColor != null) {
            if (Log.isLoggable("nf_subtitles_render", 3)) {
                Log.d("nf_subtitles_render", "Sets text view background color to " + backgroundColor);
            }
            textView.setBackgroundColor((int)backgroundColor);
        }
        else {
            Log.w("nf_subtitles_render", "Background color unresolved, not setting anything!");
        }
        this.applyOutline(textView, textStyleDefault);
    }
    
    private Pair<Integer, Integer> calculateRegionSize(final SubtitleBlock subtitleBlock, final LinearLayout linearLayout) {
        int measuredWidth = 0;
        int n = 0;
        Object o = null;
        for (int i = 0; i < subtitleBlock.getTextNodes().size(); ++i) {
            final SubtitleTextNode subtitleTextNode = subtitleBlock.getTextNodes().get(i);
            final TextView textView = this.toTextView(subtitleTextNode);
            Object o2 = o;
            int n2 = n;
            int measuredWidth2 = measuredWidth;
            if (subtitleTextNode.getLineBreaks() > 0) {
                Log.d("nf_subtitles_render", "This node belongs to its own line");
                n2 = n;
                measuredWidth2 = measuredWidth;
                if (o != null) {
                    ((LinearLayout)o).measure(0, 0);
                    final int n3 = n2 = n + ((LinearLayout)o).getMeasuredHeight();
                    if ((measuredWidth2 = measuredWidth) < ((LinearLayout)o).getMeasuredWidth()) {
                        measuredWidth2 = ((LinearLayout)o).getMeasuredWidth();
                        n2 = n3;
                    }
                }
                o2 = null;
            }
            if (this.isNextNodeInSameLine(subtitleBlock.getTextNodes(), i)) {
                Log.d("nf_subtitles_render", "Next node is in same line, add current node to horizontal LL.");
                if ((o = o2) == null) {
                    o = new LinearLayout((Context)this.mActivity);
                    ((LinearLayout)o).setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -2));
                    ((LinearLayout)o).setOrientation(0);
                    linearLayout.addView((View)o);
                }
            }
            else {
                Log.d("nf_subtitles_render", "Next node is not in same line");
                o = o2;
            }
            if (o != null) {
                Log.d("nf_subtitles_render", "Adding node to horizontal wrapper");
                ((LinearLayout)o).addView((View)textView);
                measuredWidth = measuredWidth2;
                n = n2;
            }
            else {
                Log.d("nf_subtitles_render", "Adding node directly to a window region");
                linearLayout.addView((View)textView);
                final int n4 = n = n2 + textView.getMeasuredHeight();
                if ((measuredWidth = measuredWidth2) < textView.getMeasuredWidth()) {
                    measuredWidth = textView.getMeasuredWidth();
                    n = n4;
                }
            }
        }
        int measuredWidth3;
        if (o != null) {
            Log.d("nf_subtitles_render", "Last horizontal wrapper needs to be measured");
            ((LinearLayout)o).measure(0, 0);
            final int n5 = n += ((LinearLayout)o).getMeasuredHeight();
            if ((measuredWidth3 = measuredWidth) < ((LinearLayout)o).getMeasuredWidth()) {
                measuredWidth3 = ((LinearLayout)o).getMeasuredWidth();
                n = n5;
            }
        }
        else {
            Log.d("nf_subtitles_render", "No unmeasured last horizontal wrapper");
            measuredWidth3 = measuredWidth;
        }
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "Region after text nodes added, calculated w: " + measuredWidth3 + ", h: " + n);
        }
        linearLayout.measure(0, 0);
        if (Log.isLoggable("nf_subtitles_render", 3)) {
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
                    if (Log.isLoggable("nf_subtitles_render", 2)) {
                        Log.v("nf_subtitles_render", "Create linear layout from region " + region + " on position " + i);
                    }
                    this.addRegion(region);
                }
            }
        }
    }
    
    private Runnable createRunnable(final SubtitleBlock subtitleBlock, final boolean b) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final boolean remove = EnhancedSubtitleManager.this.mPendingActions.remove(this);
                if (Log.isLoggable("nf_subtitles_render", 3)) {
                    Log.d("nf_subtitles_render", "Delayed show " + b + " for block " + subtitleBlock.getId());
                    Log.d("nf_subtitles_render", "Removed from pending queue " + remove);
                }
                if (b) {
                    Log.w("nf_subtitles_render", "===> showSubtitleBlock was called from pending queue!!");
                    EnhancedSubtitleManager.this.showSubtitleBlock(subtitleBlock, null);
                    return;
                }
                EnhancedSubtitleManager.this.removeSubtitleBlock(subtitleBlock);
            }
        };
        this.mPendingActions.add(runnable);
        return runnable;
    }
    
    private String createText(final String s, final int n) {
        final StringBuilder sb = new StringBuilder();
        if (n > 1) {
            for (int i = 1; i < n; ++i) {
                sb.append('\n');
            }
        }
        if (!StringUtils.isEmpty(s)) {
            sb.append(s);
        }
        sb.append(" ");
        return sb.toString();
    }
    
    private LinearLayout findRegionLayout(final SubtitleBlock subtitleBlock) {
        Log.d("nf_subtitles_render", "findRegionLayout start");
        final Region region = subtitleBlock.getRegion();
        if (region == null || region.getId() == null) {
            Log.d("nf_subtitles_render", "Block does not have region, put it in default region");
            return this.mDefaultRegion;
        }
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "Block have region " + region);
        }
        final LinearLayout linearLayout = this.mRegions.get(region.getId());
        if (linearLayout == null) {
            if (Log.isLoggable("nf_subtitles_render", 6)) {
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
    
    private void handleDelayedSubtitleBlocks(final List<SubtitleBlock> list, final boolean b) {
        for (final SubtitleBlock subtitleBlock : list) {
            if (Log.isLoggable("nf_subtitles_render", 2)) {
                Log.v("nf_subtitles_render", "Posting delayed block by " + subtitleBlock.getEnd() + " [ms]. Display " + b + " for block " + subtitleBlock.getId());
            }
            this.mHandler.postDelayed(this.createRunnable(subtitleBlock, b), subtitleBlock.getEnd());
        }
    }
    
    private boolean isDisplayAreaVisible() {
        if (this.mDisplayArea == null) {
            Log.e("nf_subtitles_render", "Display area NOT found! This should NOT happen!");
            return false;
        }
        if (this.mDisplayArea.getWidth() == 0 || this.mDisplayArea.getHeight() == 0) {
            Log.w("nf_subtitles_render", "Display area w/h are 0, display area is not visible yet, postpone getting w/h");
            return false;
        }
        return true;
    }
    
    private boolean isNextNodeInSameLine(final List<SubtitleTextNode> list, int n) {
        boolean b = true;
        ++n;
        if (list.size() <= n) {
            return false;
        }
        if (list.get(n).getLineBreaks() >= 1) {
            b = false;
        }
        return b;
    }
    
    private boolean isPositionDefinedInBlock(final LinearLayout linearLayout, final SubtitleBlock subtitleBlock) {
        Log.d("nf_subtitles_render", "isPositionDefinedInBlock start");
        if (subtitleBlock.getRegion() == null) {
            Log.d("nf_subtitles_render", "isPositionDefinedInBlock no region, no need for wrapper");
            return false;
        }
        if (subtitleBlock.getTextNodes().size() < 1) {
            Log.w("nf_subtitles_render", "isPositionDefinedInBlock no text blocks!");
            return false;
        }
        final SubtitleTextNode subtitleTextNode = subtitleBlock.getTextNodes().get(0);
        if (subtitleTextNode == null || subtitleTextNode.getStyle() == null) {
            Log.w("nf_subtitles_render", "isPositionDefinedInBlock p missing");
            return false;
        }
        final DoubleLength extent = subtitleBlock.getStyle().getExtent();
        final DoubleLength origin = subtitleBlock.getStyle().getOrigin();
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "isPositionDefinedInBlock extent " + extent);
            Log.d("nf_subtitles_render", "isPositionDefinedInBlock origin " + origin);
        }
        if (DoubleLength.canUse(extent) && DoubleLength.canUse(origin)) {
            Log.d("nf_subtitles_render", "isPositionDefinedInBlock using block extent and origin overrides, return true");
            return true;
        }
        Log.d("nf_subtitles_render", "isPositionDefinedInBlock using region defaults for extent and origin, return false");
        return false;
    }
    
    private void makeItVisible(final List<ViewUtils.ViewComparator> list) {
        for (final ViewUtils.ViewComparator viewComparator : list) {
            viewComparator.getView().setTag((Object)null);
            viewComparator.getView().setVisibility(0);
        }
    }
    
    private void moveBlocksAppartIfNeeded(final List<ViewUtils.ViewComparator> list) {
        Collections.sort((List<Object>)list, (Comparator<? super Object>)ViewUtils.ViewComparator.REVERSE_SORT_BY_TOP);
        if (this.moveBlocksUpIfNeeded(list)) {
            Log.d("nf_subtitles_render", "Blocks moved appart, but we hit top, reorder blocks and move appart by pushing to bottom");
            Collections.sort((List<Object>)list, (Comparator<? super Object>)ViewUtils.ViewComparator.SORT_BY_TOP);
            Log.d("nf_subtitles_render", "Sorted");
            this.moveBlocksBottomIfNeeded(list);
            return;
        }
        Log.d("nf_subtitles_render", "Blocks moved appart, no issues");
    }
    
    private void moveBlocksBottomIfNeeded(final List<ViewUtils.ViewComparator> list) {
        for (int size = list.size(), i = 0; i < size - 1; ++i) {
            for (int j = i + 1; j < list.size(); ++j) {
                if (Log.isLoggable("nf_subtitles_render", 3)) {
                    Log.d("nf_subtitles_render", "Blocks " + i + " and " + j + " test " + list.size());
                }
                SubtitleUtils.adjustIfIntersectByMovingSecondDown((LinearLayout)list.get(i).getView(), (LinearLayout)list.get(j).getView(), this.mDisplayArea.getHeight());
            }
        }
    }
    
    private boolean moveBlocksUpIfNeeded(final List<ViewUtils.ViewComparator> list) {
        boolean b = false;
        for (int size = list.size(), i = 0; i < size - 1; ++i) {
            for (int j = i + 1; j < list.size(); ++j) {
                if (Log.isLoggable("nf_subtitles_render", 3)) {
                    Log.d("nf_subtitles_render", "Blocks " + i + " and " + j + " test " + list.size());
                }
                if (SubtitleUtils.adjustIfIntersectByMovingFirstUp((LinearLayout)list.get(j).getView(), (LinearLayout)list.get(i).getView()) == -1) {
                    Log.w("nf_subtitles_render", "We hit top!");
                    b = true;
                }
            }
        }
        return b;
    }
    
    private SubtitleUtils.Margins moveRegionInsideVisibleDisplayArea(final SubtitleUtils.Margins margins, int left, int right, final Rect rect) {
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "Margins, left: " + margins.left + ", top: " + margins.top + ", right: " + margins.right + ", bottom: " + margins.bottom);
        }
        final SubtitleUtils.Margins margins2 = new SubtitleUtils.Margins();
        final int top = margins.top;
        final int left2 = margins.left;
        int bottom;
        if (rect.height() >= right) {
            Log.d("nf_subtitles_render", "Original region is high enough, keep original measure");
            bottom = margins.bottom;
        }
        else {
            Log.d("nf_subtitles_render", "Original region is NOT high enough, recalculate");
            bottom = margins.top + right + this.mVerticalRegionPadding * 2;
        }
        if (rect.width() >= left) {
            Log.d("nf_subtitles_render", "Original region is wide enough, keep original measure");
            right = margins.right;
        }
        else {
            Log.d("nf_subtitles_render", "Original region is NOT wide enough, recalculate");
            right = margins.left + left + this.mHorizontalRegionPadding * 2;
        }
        Log.d("nf_subtitles_render", "Check if region bottom is lower than display area");
        if (bottom > this.mDisplayArea.getHeight()) {
            final int n = left = top - (bottom - this.mDisplayArea.getHeight());
            if (Log.isLoggable("nf_subtitles_render", 3)) {
                Log.d("nf_subtitles_render", "New top " + n);
                left = n;
            }
        }
        else {
            Log.d("nf_subtitles_render", "No need to change top");
            left = top;
        }
        Log.d("nf_subtitles_render", "Check if region top is higher than display area");
        int top2;
        if (left < 0) {
            top2 = 0;
            Log.d("nf_subtitles_render", "Top is 0");
        }
        else {
            Log.d("nf_subtitles_render", "No need to change top");
            top2 = left;
        }
        Log.d("nf_subtitles_render", "Check if region right is pass right of display area");
        if (right > this.mDisplayArea.getWidth()) {
            right = (left = left2 - (right - this.mDisplayArea.getWidth()));
            if (Log.isLoggable("nf_subtitles_render", 3)) {
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
            left = 0;
            Log.d("nf_subtitles_render", "Left is 0");
        }
        else {
            Log.d("nf_subtitles_render", "No need to change left");
        }
        margins2.left = left;
        margins2.top = top2;
        return margins2;
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
        Label_0175:
            while (true) {
                final String s;
                final LinearLayout linearLayout;
                Label_0129: {
                    synchronized (this) {
                        if (this.mRegions.size() < 1) {
                            Log.d("nf_subtitles_render", "removeRegions:: no region found to remove.");
                        }
                        else {
                            final Iterator<String> iterator = this.mRegions.keySet().iterator();
                            if (!iterator.hasNext()) {
                                break Label_0175;
                            }
                            s = iterator.next();
                            linearLayout = this.mRegions.get(s);
                            if (linearLayout != null) {
                                break Label_0129;
                            }
                            if (Log.isLoggable("nf_subtitles_render", 6)) {
                                Log.e("nf_subtitles_render", "Removing region " + s + " is not possible. Region is null!");
                            }
                        }
                        return;
                    }
                }
                if (Log.isLoggable("nf_subtitles_render", 2)) {
                    Log.v("nf_subtitles_render", "Removing region " + s);
                }
                this.mDisplayArea.removeView((View)linearLayout);
                continue;
            }
            this.mRegions.clear();
        }
    }
    
    private void removeSubtitleBlock(final SubtitleBlock subtitleBlock) {
        if (subtitleBlock == null) {
            Log.e("nf_subtitles_render", "Subtitle block can not be null!");
            return;
        }
        final Region region = subtitleBlock.getRegion();
        if (region == null) {
            Log.d("nf_subtitles_render", "Remove block from default region for id " + subtitleBlock.getId());
            this.removeBlock(subtitleBlock.getId(), this.mVisibleBlocks.get("DEFAULT"), "DEFAULT");
            return;
        }
        final String id = region.getId();
        if (id == null) {
            Log.e("nf_subtitles_render", "Region id can NOT be null!");
            return;
        }
        Log.d("nf_subtitles_render", "Remove block from region " + id + " for block id " + subtitleBlock.getId());
        this.removeBlock(subtitleBlock.getId(), this.mVisibleBlocks.get(id), id);
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
    
    private void removeVisibleSubtitleBlocks(final boolean b) {
        LinearLayout linearLayout = null;
        Label_0039_Outer:Label_0054_Outer:
        while (true) {
            while (true) {
            Label_0187:
                while (true) {
                    Label_0177: {
                        synchronized (this) {
                            if (this.mDefaultRegion != null) {
                                this.mDefaultRegion.setBackgroundColor(this.mTransparent);
                                this.mDefaultRegion.setVisibility(4);
                                if (!b) {
                                    break Label_0177;
                                }
                                this.mDefaultRegion.removeAllViews();
                            }
                            for (final String s : this.mRegions.keySet()) {
                                if (Log.isLoggable("nf_subtitles_render", 3)) {
                                    Log.d("nf_subtitles_render", "Removing visible blocks for region " + s);
                                }
                                linearLayout = this.mRegions.get(s);
                                if (linearLayout != null) {
                                    break Label_0187;
                                }
                                if (!Log.isLoggable("nf_subtitles_render", 3)) {
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
    
    private void setAlignmentToRegion(final LinearLayout linearLayout, final SubtitleBlock subtitleBlock) {
        final Region region = subtitleBlock.getRegion();
        HorizontalAlignment horizontalAlignment2;
        final HorizontalAlignment horizontalAlignment = horizontalAlignment2 = HorizontalAlignment.center;
        if (region != null) {
            horizontalAlignment2 = horizontalAlignment;
            if (region.getHorizontalAlignment() != null) {
                Log.d("nf_subtitles_render", "Horizontal alignment from region");
                horizontalAlignment2 = region.getHorizontalAlignment();
            }
        }
        VerticalAlignment verticalAlignment2;
        final VerticalAlignment verticalAlignment = verticalAlignment2 = VerticalAlignment.bottom;
        if (region != null) {
            verticalAlignment2 = verticalAlignment;
            if (region.getVerticalAlignment() != null) {
                Log.d("nf_subtitles_render", "Vertical alignment from region");
                verticalAlignment2 = region.getVerticalAlignment();
            }
        }
        HorizontalAlignment horizontalAlignment3 = horizontalAlignment2;
        VerticalAlignment verticalAlignment3 = verticalAlignment2;
        if (subtitleBlock.getTextNodes().size() > 1) {
            final SubtitleTextNode subtitleTextNode = subtitleBlock.getTextNodes().get(0);
            horizontalAlignment3 = horizontalAlignment2;
            verticalAlignment3 = verticalAlignment2;
            if (subtitleTextNode != null) {
                horizontalAlignment3 = horizontalAlignment2;
                verticalAlignment3 = verticalAlignment2;
                if (subtitleTextNode.getStyle() != null) {
                    if (subtitleTextNode.getStyle().getHorizontalAlignment() != null) {
                        Log.d("nf_subtitles_render", "Horizontal alignment overide from p!");
                        horizontalAlignment2 = subtitleTextNode.getStyle().getHorizontalAlignment();
                    }
                    horizontalAlignment3 = horizontalAlignment2;
                    verticalAlignment3 = verticalAlignment2;
                    if (subtitleTextNode.getStyle().getVerticalAlignment() != null) {
                        Log.d("nf_subtitles_render", "Vertical alignment overide from p!");
                        verticalAlignment3 = subtitleTextNode.getStyle().getVerticalAlignment();
                        horizontalAlignment3 = horizontalAlignment2;
                    }
                }
            }
        }
        linearLayout.setGravity(SubtitleUtils.toGravity(horizontalAlignment3, verticalAlignment3));
    }
    
    private void setBackgroundColorToRegion(final LinearLayout linearLayout, final SubtitleBlock subtitleBlock) {
        final TextStyle userDefaults = this.mParser.getUserDefaults();
        final Integer n = null;
        Integer n2;
        if (userDefaults == null) {
            Log.d("nf_subtitles_render", "No user overides for window color");
            n2 = n;
            if (subtitleBlock.getTextNodes().size() > 0) {
                final TextStyle style = subtitleBlock.getTextNodes().get(0).getStyle();
                n2 = n;
                if (style != null) {
                    n2 = n;
                    if (style.getWindowColor() != null) {
                        n2 = ColorMapping.resolveColor(style.getWindowOpacity(), style.getWindowColor());
                    }
                }
            }
        }
        else {
            Log.d("nf_subtitles_render", "User overides found for window color");
            n2 = ColorMapping.resolveColor(userDefaults.getWindowOpacity(), userDefaults.getWindowColor());
        }
        if (n2 == null) {
            Log.d("nf_subtitles_render", "Sets region background color to transparent");
            linearLayout.setBackgroundColor(this.mActivity.getResources().getColor(17170445));
            return;
        }
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "Sets region background color to " + n2);
        }
        linearLayout.setBackgroundColor((int)n2);
    }
    
    private void setDefaults() {
        Log.v("nf_subtitles_render", "Find root display area");
        if (!this.isDisplayAreaVisible()) {
            return;
        }
        Log.v("nf_subtitles_render", "Create safe display area");
        this.mSafeDisplayArea = new RelativeLayout((Context)this.mActivity);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, -1);
        relativeLayout$LayoutParams.addRule(13);
        this.mDisplayArea.addView((View)this.mSafeDisplayArea, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        Log.v("nf_subtitles_render", "Create safe display area done.");
        Log.v("nf_subtitles_render", "Create default region");
        this.mDefaultRegion = new LinearLayout((Context)this.mActivity);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(-1, -2);
        this.mDefaultRegion.setOrientation(1);
        relativeLayout$LayoutParams2.addRule(12);
        this.mSafeDisplayArea.addView((View)this.mDefaultRegion, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
        this.mDefaultRegion.setVisibility(4);
        this.mVisibleBlocks.put("DEFAULT", new ArrayList<TextView>());
        this.mDefaultsInitiated.set(true);
    }
    
    private void setMargins(final RelativeLayout$LayoutParams relativeLayout$LayoutParams, final Rect rect) {
        final int left = rect.left;
        final int top = rect.top;
        final int n = this.mDisplayArea.getWidth() - rect.right;
        final int n2 = this.mDisplayArea.getHeight() - rect.bottom;
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "Margins, left: " + left + ", top: " + top + ", right: " + n + ", bottom: " + n2);
        }
        int n3 = n2;
        int n4 = top;
        if (n2 < 0) {
            Log.w("nf_subtitles_render", "Bottom margin is less than 0, correct");
            final int n5 = top + n2;
            final int n6 = n3 = 0;
            n4 = n5;
            if (Log.isLoggable("nf_subtitles_render", 3)) {
                Log.d("nf_subtitles_render", "Margins, left: " + left + ", top: " + n5 + ", right: " + n + ", bottom: " + 0);
                n4 = n5;
                n3 = n6;
            }
        }
        int n7 = n3;
        int n8;
        if ((n8 = n4) < 0) {
            Log.w("nf_subtitles_render", "Top margin is less than 0, correct");
            final int n9 = n3 + n4;
            final int n10 = 0;
            int n11;
            if ((n11 = n9) < 0) {
                Log.w("nf_subtitles_render", "Bottom margin is left than 0, after fixing top, set to 0");
                n11 = 0;
            }
            n7 = n11;
            n8 = n10;
            if (Log.isLoggable("nf_subtitles_render", 3)) {
                Log.d("nf_subtitles_render", "Margins, left: " + left + ", top: " + 0 + ", right: " + n + ", bottom: " + n11);
                n8 = n10;
                n7 = n11;
            }
        }
        relativeLayout$LayoutParams.setMargins(left, n8, n, n7);
    }
    
    private void setMarginsForSafeDisplayArea() {
        Log.d("nf_subtitles_render", "Sets margin for safe display area");
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)this.mSafeDisplayArea.getLayoutParams();
        final int n = (int)(this.mDisplayArea.getWidth() / 100 * 5.0f);
        final int n2 = (int)(this.mDisplayArea.getHeight() / 100 * 5.0f);
        layoutParams.setMargins(n, n2, n, n2);
        this.mSafeDisplayArea.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    private RelativeLayout$LayoutParams setRegionPosition(final LinearLayout linearLayout, final DoubleLength doubleLength, final DoubleLength doubleLength2) {
        final Rect regionForRectangle = SubtitleUtils.createRegionForRectangle((View)this.mDisplayArea, doubleLength, doubleLength2);
        RelativeLayout$LayoutParams relativeLayout$LayoutParams;
        if (doubleLength == null || !doubleLength.isValid()) {
            Log.d("nf_subtitles_render", "Region w/h not know, wrap around content");
            relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-2, -2);
        }
        else {
            final int width = regionForRectangle.width();
            final int height = regionForRectangle.height();
            if (Log.isLoggable("nf_subtitles_render", 2)) {
                Log.v("nf_subtitles_render", "Display area: w " + this.mDisplayArea.getWidth() + ", h " + this.mDisplayArea.getHeight());
                Log.v("nf_subtitles_render", "Region w/h " + width + "/" + height + " known, set them");
            }
            final int n = this.mDisplayArea.getHeight() / 4;
            if (Log.isLoggable("nf_subtitles_render", 2)) {
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
        if (doubleLength2 != null && doubleLength2.isValid() && doubleLength != null && doubleLength.isValid()) {
            this.setMargins(relativeLayout$LayoutParams, regionForRectangle);
        }
        return relativeLayout$LayoutParams;
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
    
    private void showSubtitleBlock(final SubtitleBlock subtitleBlock, final List<ViewUtils.ViewComparator> list) {
        if (subtitleBlock == null || subtitleBlock.getTextNodes().size() < 1) {
            Log.e("nf_subtitles_render", "Block is empty! Can not show!");
            return;
        }
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "Show subtitle block: " + subtitleBlock);
        }
        final LinearLayout regionLayout = this.findRegionLayout(subtitleBlock);
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "LL for region " + regionLayout.getTag());
        }
        regionLayout.setVisibility(4);
        if (list != null) {
            list.add(new ViewUtils.ViewComparator((View)regionLayout));
        }
        int n;
        if (!this.isPositionDefinedInBlock(regionLayout, subtitleBlock)) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.setAlignmentToRegion(regionLayout, subtitleBlock);
        LinearLayout linearLayout = regionLayout;
        if (n != 0) {
            Log.d("nf_subtitles_render", "Using extent/origin from original region, add wrapper region that will be wrapped around block and that will be used for background color for region");
            linearLayout = new LinearLayout((Context)this.mActivity);
            linearLayout.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -2));
            linearLayout.setPadding(this.mHorizontalRegionPadding, this.mVerticalRegionPadding, this.mHorizontalRegionPadding, this.mVerticalRegionPadding);
            linearLayout.setOrientation(1);
            regionLayout.addView((View)linearLayout);
        }
        else {
            Log.d("nf_subtitles_render", "Using extent/origin from block, region will be wrapped around block and background color will be applied to it.");
        }
        final Pair<Integer, Integer> calculateRegionSize = this.calculateRegionSize(subtitleBlock, linearLayout);
        this.updatePositionIfNeeded(regionLayout, subtitleBlock, (int)calculateRegionSize.first, (int)calculateRegionSize.second);
        this.setBackgroundColorToRegion(linearLayout, subtitleBlock);
    }
    
    private void showSubtitleBlocks(final List<SubtitleBlock> list) {
        final ViewTreeObserver viewTreeObserver = this.mDisplayArea.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(this.mDoNotDraw);
        final ArrayList<ViewUtils.ViewComparator> list2 = new ArrayList<ViewUtils.ViewComparator>();
        final Iterator<SubtitleBlock> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.showSubtitleBlock(iterator.next(), list2);
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
        final String text = this.createText(subtitleTextNode.getText(), subtitleTextNode.getLineBreaks());
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "Create text view from " + subtitleTextNode);
            Log.d("nf_subtitles_render", "Text to set " + text);
        }
        AutoResizeTextView autoResizeTextView;
        if (SubtitleUtils.isStrokeTextViewRequired(subtitleTextNode.getStyle(), this.mParser.getUserDefaults())) {
            autoResizeTextView = new StrokeTextView((Context)this.mActivity);
        }
        else {
            autoResizeTextView = new AutoResizeTextView((Context)this.mActivity);
        }
        autoResizeTextView.setEllipsize((TextUtils$TruncateAt)null);
        autoResizeTextView.setSingleLine(true);
        autoResizeTextView.setLayoutParams(new ViewGroup$LayoutParams(-2, -2));
        if (Boolean.TRUE.equals(subtitleTextNode.getStyle().getUnderline())) {
            Log.d("nf_subtitles_render", "Sets underline");
            autoResizeTextView.setUnderline(true);
        }
        this.applyStyle(autoResizeTextView, subtitleTextNode.getStyle());
        autoResizeTextView.setText((CharSequence)text);
        autoResizeTextView.setGravity(119);
        autoResizeTextView.measure(0, 0);
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "Post measure text view mw: " + autoResizeTextView.getMeasuredWidth() + ", mh: " + autoResizeTextView.getMeasuredHeight());
        }
        return autoResizeTextView;
    }
    
    private void updatePositionIfNeeded(final LinearLayout linearLayout, final SubtitleBlock subtitleBlock, final int n, final int n2) {
        Log.d("nf_subtitles_render", "updatePositionIfNeeded start");
        final Region region = subtitleBlock.getRegion();
        if (region == null) {
            Log.d("nf_subtitles_render", "updatePositionIfNeeded no region, no update");
        }
        else {
            Log.d("nf_subtitles_render", "updatePositionIfNeeded start for real");
            if (subtitleBlock.getTextNodes().size() < 1) {
                Log.w("nf_subtitles_render", "updatePositionIfNeeded no text blocks!");
                return;
            }
            final SubtitleTextNode subtitleTextNode = subtitleBlock.getTextNodes().get(0);
            if (subtitleTextNode == null || subtitleTextNode.getStyle() == null) {
                Log.w("nf_subtitles_render", "updatePositionIfNeeded p missing");
                return;
            }
            final DoubleLength extent = subtitleBlock.getStyle().getExtent();
            final DoubleLength origin = subtitleBlock.getStyle().getOrigin();
            if (DoubleLength.canUse(extent) && DoubleLength.canUse(origin)) {
                Log.w("nf_subtitles_render", "updatePositionIfNeeded using block extent and origin overrides");
                this.updateRegionPosition(linearLayout, n, n2, extent, origin);
                return;
            }
            Log.w("nf_subtitles_render", "updatePositionIfNeeded using region defaults for extent and origin");
            if (Log.isLoggable("nf_subtitles_render", 2)) {
                Log.d("nf_subtitles_render", "Region h " + linearLayout.getHeight() + ", needed h " + n2);
            }
            if (this.shouldUpdateRegionPosition(linearLayout, region, n, n2)) {
                this.updateRegionPosition(linearLayout, n, n2, region.getExtent(), region.getOrigin());
            }
        }
    }
    
    private void updateRegionPosition(final LinearLayout linearLayout, int n, int n2, final DoubleLength doubleLength, final DoubleLength doubleLength2) {
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)linearLayout.getLayoutParams();
        if (Log.isLoggable("nf_subtitles_render", 2)) {
            Log.d("nf_subtitles_render", "Display area: w " + this.mDisplayArea.getWidth() + ", h " + this.mDisplayArea.getHeight());
        }
        final Rect regionForRectangle = SubtitleUtils.createRegionForRectangle((View)this.mDisplayArea, doubleLength, doubleLength2);
        final SubtitleUtils.Margins moveRegionInsideVisibleDisplayArea = this.moveRegionInsideVisibleDisplayArea(SubtitleUtils.getMarginsForRectangle((View)this.mDisplayArea, doubleLength, doubleLength2), n, n2, regionForRectangle);
        layoutParams.setMargins(moveRegionInsideVisibleDisplayArea.left, moveRegionInsideVisibleDisplayArea.top, 0, 0);
        if (regionForRectangle.height() >= n2) {
            Log.d("nf_subtitles_render", "Original region is high enough, keep original measure");
            layoutParams.height = regionForRectangle.height();
            n2 = moveRegionInsideVisibleDisplayArea.top + regionForRectangle.height();
        }
        else {
            Log.d("nf_subtitles_render", "Original region is NOT high enough, recalculate");
            layoutParams.height = this.mVerticalRegionPadding * 2 + n2;
            n2 = moveRegionInsideVisibleDisplayArea.top + layoutParams.height;
        }
        if (regionForRectangle.width() >= n) {
            Log.d("nf_subtitles_render", "Original region is wide enough, keep original measure");
            layoutParams.width = regionForRectangle.width();
            n = moveRegionInsideVisibleDisplayArea.left + regionForRectangle.width();
        }
        else {
            Log.d("nf_subtitles_render", "Original region is NOT wide enough, recalculate");
            layoutParams.width = this.mHorizontalRegionPadding * 2 + n;
            n = moveRegionInsideVisibleDisplayArea.left + layoutParams.width;
        }
        linearLayout.setTag((Object)new Rect(moveRegionInsideVisibleDisplayArea.left, moveRegionInsideVisibleDisplayArea.top, n, n2));
        linearLayout.setPadding(this.mHorizontalRegionPadding, this.mVerticalRegionPadding, this.mHorizontalRegionPadding, this.mVerticalRegionPadding);
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    @Override
    public void clear() {
        synchronized (this) {
            Log.v("nf_subtitles_render", "Remove pending actions");
            this.removeAll(true);
        }
    }
    
    @Override
    public void clearPendingUpdates() {
        this.clearDelayedPosts();
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
            final int hashCode = subtitleScreen2.getParser().hashCode();
            if (this.mSubtitleParserId != null && this.mSubtitleParserId == hashCode) {
                if (Log.isLoggable("nf_subtitles_render", 2)) {
                    Log.v("nf_subtitles_render", "Same subtitles file " + this.mSubtitleParserId);
                }
            }
            else {
                if (Log.isLoggable("nf_subtitles_render", 2)) {
                    Log.v("nf_subtitles_render", "Subtitles file changed. Was " + this.mSubtitleParserId + ", now " + hashCode + ". (Re) create regions!");
                }
                this.mSubtitleParserId = subtitleScreen2.getParser().hashCode();
                this.removeRegions();
                this.createRegions(subtitleScreen2.getParser().getRegions());
            }
            this.showSubtitleBlocks(subtitleScreen2.getDisplayNowBlocks());
            this.handleDelayedSubtitleBlocks(subtitleScreen2.getDisplayLaterBlocks(), true);
        }
    }
    
    @Override
    public void onSubtitleRemove() {
        Log.d("nf_subtitles_render", "Remove all subtitles.");
        this.removeAll(true);
    }
    
    @Override
    public void onSubtitleShow(final String s) {
        Log.w("nf_subtitles_render", "EnhancedSubtitleManager does not implement onSubtitleShow!");
    }
    
    @Override
    public void setSubtitleVisibility(final boolean b) {
        synchronized (this) {
            if (Log.isLoggable("nf_subtitles_render", 3)) {
                Log.d("nf_subtitles_render", "EnhancedSubtitleManager:: set subtitle visibility to visible " + b);
            }
            this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    EnhancedSubtitleManager.this.setVisibilityForAllRegions(b);
                }
            });
        }
    }
}
