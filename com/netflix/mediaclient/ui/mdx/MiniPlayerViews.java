// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.barker.BarkerUtils;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.viewpagerindicator.android.osp.ViewPager;
import android.view.View;
import com.viewpagerindicator.CirclePageIndicator;

public class MiniPlayerViews extends MdxMiniPlayerViews
{
    CirclePageIndicator indicator;
    View mementoGroup;
    View mementoTabs;
    ViewPager pager;
    View puller;
    View pullerContainer;
    View titleExpanded;
    
    public MiniPlayerViews(final NetflixActivity netflixActivity, final IMdxMiniPlayerViewCallbacks mdxMiniPlayerViewCallbacks, final MdxUtils$MdxTargetSelectionDialogInterface mdxUtils$MdxTargetSelectionDialogInterface) {
        super(netflixActivity, mdxMiniPlayerViewCallbacks, mdxUtils$MdxTargetSelectionDialogInterface);
    }
    
    public static int colorWithAlpha(final int n, final float n2) {
        return (int)(255.0f * n2) << 24 | n;
    }
    
    private void fadePullerContainer(final float n) {
        if (this.pullerContainer != null) {
            this.pullerContainer.setBackgroundColor((int)(153 * n) << 24 | 0x0);
        }
    }
    
    @Override
    protected void findViews() {
        super.findViews();
        this.pager = (ViewPager)this.content.findViewById(2131755553);
        this.indicator = (CirclePageIndicator)this.content.findViewById(2131755554);
        this.mementoGroup = this.content.findViewById(2131755560);
        this.puller = this.content.findViewById(2131755570);
        this.titleExpanded = this.content.findViewById(2131755561);
        this.mementoTabs = this.content.findViewById(2131755555);
        this.pullerContainer = this.activity.findViewById(2131755514);
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903213;
    }
    
    @Override
    public View getSlidingPanelDragView() {
        return this.puller;
    }
    
    @Override
    public void init() {
        super.init();
        this.titleGroup.getLayoutParams().width = BarkerUtils.getDetailsPageContentWidth(this.titleGroup.getContext());
        if (DeviceUtils.getScreenSizeCategory(this.titleGroup.getContext()) == 3 && DeviceUtils.isLandscape(this.titleGroup.getContext())) {
            this.titleExpanded.getLayoutParams().height = 0;
        }
    }
    
    @Override
    protected void setBifHeight(final int n) {
    }
    
    @Override
    protected void setBifWidth(final int n) {
    }
    
    @Override
    protected void translateTitleGroup(final float n) {
        final float n2 = 1.0f - n;
        final float n3 = this.getContentView().getResources().getDimensionPixelOffset(2131427816);
        final float n4 = -(BarkerUtils.getDetailsPageContentWidth(this.getContentView().getContext()) / 2 - this.getContentView().getResources().getDimensionPixelOffset(2131427814)) * (1.0f - n);
        final float n5 = this.getContentView().getResources().getDimensionPixelSize(2131427816);
        if (this.mementoGroup != null) {
            this.mementoGroup.setY(n3 * n);
            this.mementoGroup.setAlpha(n2);
        }
        if (this.title != null) {
            this.title.setAlpha(n);
        }
        if (this.subtitle != null) {
            this.subtitle.setAlpha(n);
        }
        if (this.playcardCaret != null) {
            this.playcardCaret.setTranslationX(n4);
            if (n2 == 0.0f) {
                this.playcardCaret.animate().alpha(1.0f).setDuration(300L);
            }
            else {
                this.playcardCaret.setAlpha(n2);
            }
            this.playcardCaret.setY(n5 * n2 * 0.09f - this.getContentView().getResources().getDimensionPixelSize(2131427815) * n);
        }
        if (this.puller != null) {
            this.puller.setTranslationX(n4);
        }
        this.fadePullerContainer(n2);
    }
    
    @Override
    protected void updateViewsForSeekBarUsage(final boolean b) {
        if (b) {
            this.fadeOut(this.auxControlsGroup, this.playcardControlsGroup, this.titleGroup, this.pager, this.indicator, this.deviceNameGroup, this.playcardCaret, this.titleExpanded, this.mementoTabs);
            if (this.deviceNameGroup != null) {
                this.fadeOutAndHide(this.deviceNameGroup);
            }
            return;
        }
        this.fadeInAndShow(this.auxControlsGroup, this.playcardControlsGroup, this.titleGroup, this.pager, this.indicator, this.deviceNameGroup, this.playcardCaret, this.titleExpanded, this.mementoTabs);
    }
}
