// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker.details;

import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;

public enum BarkerHelper$ModalFormat
{
    NARROW(0, 8, 3, 4, 8, 8, 6, 4), 
    STANDARD(721, 10, 4, 5, 6, 4, 6, 4), 
    WIDE(1223, 10, 3, 5, 6, 4, 6, 6);
    
    private int mBookmarkColumnSpan;
    private int mCreditsColumnSpan;
    private int mEpImgColumnSpan;
    private int mEpSynopsisColumnSpan;
    private int mModalColumnSpan;
    private int mPreReleaseColumnSpan;
    private int mSimsNumber;
    private int mStartRangeDp;
    
    private BarkerHelper$ModalFormat(final int mStartRangeDp, final int mModalColumnSpan, final int mEpImgColumnSpan, final int mEpSynopsisColumnSpan, final int mBookmarkColumnSpan, final int mCreditsColumnSpan, final int mPreReleaseColumnSpan, final int mSimsNumber) {
        this.mStartRangeDp = mStartRangeDp;
        this.mModalColumnSpan = mModalColumnSpan;
        this.mEpImgColumnSpan = mEpImgColumnSpan;
        this.mEpSynopsisColumnSpan = mEpSynopsisColumnSpan;
        this.mBookmarkColumnSpan = mBookmarkColumnSpan;
        this.mCreditsColumnSpan = mCreditsColumnSpan;
        this.mPreReleaseColumnSpan = mPreReleaseColumnSpan;
        this.mSimsNumber = mSimsNumber;
    }
    
    public static BarkerHelper$ModalFormat getFormatForDevice(final Context context) {
        final int screenWidthInDPs = DeviceUtils.getScreenWidthInDPs(context);
        final BarkerHelper$ModalFormat[] values = values();
        final int length = values.length;
        BarkerHelper$ModalFormat barkerHelper$ModalFormat = null;
        BarkerHelper$ModalFormat barkerHelper$ModalFormat2;
        for (int i = 0; i < length; ++i, barkerHelper$ModalFormat = barkerHelper$ModalFormat2) {
            barkerHelper$ModalFormat2 = values[i];
            if (screenWidthInDPs <= barkerHelper$ModalFormat2.mStartRangeDp) {
                break;
            }
        }
        return barkerHelper$ModalFormat;
    }
    
    public int getBookmarkColumnSpan() {
        return this.mBookmarkColumnSpan;
    }
    
    public int getCreditsColumnSpan() {
        return this.mCreditsColumnSpan;
    }
    
    public int getEpisodeImageColumnSpan() {
        return this.mEpImgColumnSpan;
    }
    
    public int getEpisodeSynopsisColumnSpan() {
        return this.mEpSynopsisColumnSpan;
    }
    
    public int getModalColumnSpan() {
        return this.mModalColumnSpan;
    }
    
    public int getNumberOfSims() {
        return this.mSimsNumber;
    }
    
    public int getPreReleaseColumnSpan() {
        return this.mPreReleaseColumnSpan;
    }
}
