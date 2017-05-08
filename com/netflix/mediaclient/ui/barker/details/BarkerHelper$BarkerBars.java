// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker.details;

import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;

public class BarkerHelper$BarkerBars
{
    private int mColumnAndGutterWidth;
    private Context mContext;
    private int mMaxModalWidth;
    private BarkerHelper$ModalFormat mModalFormat;
    
    public BarkerHelper$BarkerBars(final Context mContext) {
        this.mContext = mContext;
        this.mModalFormat = BarkerHelper$ModalFormat.getFormatForDevice(mContext);
        this.mMaxModalWidth = (int)this.mContext.getResources().getDimension(2131427574);
        final int totalColumnNum = this.getTotalColumnNum();
        int n;
        if (this.isOversized()) {
            n = this.mMaxModalWidth;
        }
        else {
            n = DeviceUtils.getScreenWidthInPixels(mContext);
        }
        this.mColumnAndGutterWidth = (n - this.getGutterWidth()) / totalColumnNum;
    }
    
    private int getColumnsWidth(final int n) {
        return this.getColumnPlusGutterWidth() * n + this.getGutterWidth();
    }
    
    public int getBookmarkWidth() {
        return this.mModalFormat.getBookmarkColumnSpan() * this.getColumnPlusGutterWidth() - this.getGutterWidth();
    }
    
    public int getColumnPlusGutterWidth() {
        return this.mColumnAndGutterWidth;
    }
    
    public int getColumnWidth() {
        return this.mColumnAndGutterWidth - this.getGutterWidth();
    }
    
    public int getCreditsWidth() {
        return this.mModalFormat.getCreditsColumnSpan() * this.getColumnPlusGutterWidth() - this.getGutterWidth();
    }
    
    public int getDpArtworkHeight(final int n) {
        final float n2 = 0.5625f * n;
        if (BrowseExperience.showKidsExperience()) {
            return (int)n2;
        }
        final float n3 = DeviceUtils.getScreenHeightInPixels(this.mContext);
        float n4 = n2;
        if (n2 / n3 > BarkerHelper.MAX_PERCENT_SCREEN_HEIGHT_OF_DP_ARTWORK) {
            n4 = n3 / 2.0f;
        }
        return (int)n4;
    }
    
    public int getEpisodeImageWidth() {
        return this.mModalFormat.getEpisodeImageColumnSpan() * this.getColumnPlusGutterWidth() - this.getGutterWidth();
    }
    
    public int getGutterWidth() {
        return this.mContext.getResources().getDimensionPixelOffset(2131427573);
    }
    
    public int getModalWidth() {
        return this.getColumnsWidth(this.mModalFormat.getModalColumnSpan());
    }
    
    public int getNumberOfSims() {
        return this.mModalFormat.getNumberOfSims();
    }
    
    public int getPreReleaseContentWidth() {
        return this.mModalFormat.getPreReleaseColumnSpan() * this.getColumnPlusGutterWidth() - this.getGutterWidth();
    }
    
    public int getSynopsisWidth() {
        return this.mModalFormat.getEpisodeSynopsisColumnSpan() * this.getColumnPlusGutterWidth() - this.getGutterWidth();
    }
    
    public int getTotalColumnNum() {
        if (this.isOversized() || this.mModalFormat == BarkerHelper$ModalFormat.NARROW) {
            return this.mModalFormat.getModalColumnSpan();
        }
        return 12;
    }
    
    public boolean isOversized() {
        return this.mModalFormat == BarkerHelper$ModalFormat.WIDE && this.getColumnsWidth(this.mModalFormat.getModalColumnSpan()) > this.mMaxModalWidth;
    }
    
    public boolean isSynopsisAndCreditsInSameRow() {
        return this.mModalFormat != BarkerHelper$ModalFormat.NARROW;
    }
}
