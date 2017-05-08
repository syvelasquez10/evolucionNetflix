// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.text;

import android.util.Pair;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;

public class CellResolution
{
    public static final CellResolution CELL_RESOLUTION_40x19;
    public static final CellResolution CELL_RESOLUTION_52x19;
    private static final String TAG = "nf_subtitles";
    private int mHeight;
    private int mWidth;
    
    static {
        CELL_RESOLUTION_40x19 = new CellResolution(40, 19);
        CELL_RESOLUTION_52x19 = new CellResolution(52, 19);
    }
    
    private CellResolution(final int mWidth, final int mHeight) {
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }
    
    public static CellResolution createInstance(final String s, final String s2, final String s3, final float n) {
        if (!StringUtils.isEmpty(s)) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Cell resolution " + s);
            }
            final Pair numberPair = StringUtils.extractNumberPair(s);
            if (numberPair != null && (int)numberPair.first > 0 && (int)numberPair.second > 0) {
                final CellResolution cellResolution = new CellResolution((int)numberPair.first, (int)numberPair.second);
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles", "New cell resolution: " + cellResolution);
                }
                return cellResolution;
            }
        }
        if (!StringUtils.isEmpty(s2)) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Cell resolution ajust using extent " + s2);
            }
            final Pair numberPair2 = StringUtils.extractNumberPair(s2);
            Pair numberPair3 = StringUtils.extractNumberPair(s3);
            if (numberPair3 != null) {
                numberPair3 = new Pair((Object)1, (Object)1);
            }
            if (numberPair2 != null) {
                if ((int)numberPair2.first * (int)numberPair3.first / ((int)numberPair3.second * (int)numberPair2.second) > 1.5) {
                    return CellResolution.CELL_RESOLUTION_52x19;
                }
                return CellResolution.CELL_RESOLUTION_40x19;
            }
        }
        else {
            Log.d("nf_subtitles", "Find cell resolution not ajusted to extent");
        }
        if (n > 1.5) {
            return CellResolution.CELL_RESOLUTION_52x19;
        }
        return CellResolution.CELL_RESOLUTION_40x19;
    }
    
    public int getHeightCount() {
        return this.mHeight;
    }
    
    public int getWidthCount() {
        return this.mWidth;
    }
    
    @Override
    public String toString() {
        return "CellResolution [width count=" + this.mWidth + ", height count=" + this.mHeight + "]";
    }
}
