// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.print;

import android.net.Uri;
import android.graphics.Bitmap;
import android.content.Context;

final class PrintHelper$PrintHelperKitkatImpl implements PrintHelper$PrintHelperVersionImpl
{
    private final PrintHelperKitkat mPrintHelper;
    
    PrintHelper$PrintHelperKitkatImpl(final Context context) {
        this.mPrintHelper = new PrintHelperKitkat(context);
    }
    
    @Override
    public int getColorMode() {
        return this.mPrintHelper.getColorMode();
    }
    
    @Override
    public int getOrientation() {
        return this.mPrintHelper.getOrientation();
    }
    
    @Override
    public int getScaleMode() {
        return this.mPrintHelper.getScaleMode();
    }
    
    @Override
    public void printBitmap(final String s, final Bitmap bitmap, final PrintHelper$OnPrintFinishCallback printHelper$OnPrintFinishCallback) {
        PrintHelperKitkat$OnPrintFinishCallback printHelperKitkat$OnPrintFinishCallback = null;
        if (printHelper$OnPrintFinishCallback != null) {
            printHelperKitkat$OnPrintFinishCallback = new PrintHelper$PrintHelperKitkatImpl$1(this, printHelper$OnPrintFinishCallback);
        }
        this.mPrintHelper.printBitmap(s, bitmap, printHelperKitkat$OnPrintFinishCallback);
    }
    
    @Override
    public void printBitmap(final String s, final Uri uri, final PrintHelper$OnPrintFinishCallback printHelper$OnPrintFinishCallback) {
        PrintHelperKitkat$OnPrintFinishCallback printHelperKitkat$OnPrintFinishCallback = null;
        if (printHelper$OnPrintFinishCallback != null) {
            printHelperKitkat$OnPrintFinishCallback = new PrintHelper$PrintHelperKitkatImpl$2(this, printHelper$OnPrintFinishCallback);
        }
        this.mPrintHelper.printBitmap(s, uri, printHelperKitkat$OnPrintFinishCallback);
    }
    
    @Override
    public void setColorMode(final int colorMode) {
        this.mPrintHelper.setColorMode(colorMode);
    }
    
    @Override
    public void setOrientation(final int orientation) {
        this.mPrintHelper.setOrientation(orientation);
    }
    
    @Override
    public void setScaleMode(final int scaleMode) {
        this.mPrintHelper.setScaleMode(scaleMode);
    }
}
