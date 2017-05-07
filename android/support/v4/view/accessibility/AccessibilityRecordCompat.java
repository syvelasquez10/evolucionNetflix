// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.accessibility;

import android.os.Build$VERSION;

public class AccessibilityRecordCompat
{
    private static final AccessibilityRecordCompat$AccessibilityRecordImpl IMPL;
    private final Object mRecord;
    
    static {
        if (Build$VERSION.SDK_INT >= 16) {
            IMPL = new AccessibilityRecordCompat$AccessibilityRecordJellyBeanImpl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 15) {
            IMPL = new AccessibilityRecordCompat$AccessibilityRecordIcsMr1Impl();
            return;
        }
        if (Build$VERSION.SDK_INT >= 14) {
            IMPL = new AccessibilityRecordCompat$AccessibilityRecordIcsImpl();
            return;
        }
        IMPL = new AccessibilityRecordCompat$AccessibilityRecordStubImpl();
    }
    
    public AccessibilityRecordCompat(final Object mRecord) {
        this.mRecord = mRecord;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            final AccessibilityRecordCompat accessibilityRecordCompat = (AccessibilityRecordCompat)o;
            if (this.mRecord == null) {
                if (accessibilityRecordCompat.mRecord != null) {
                    return false;
                }
            }
            else if (!this.mRecord.equals(accessibilityRecordCompat.mRecord)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        if (this.mRecord == null) {
            return 0;
        }
        return this.mRecord.hashCode();
    }
    
    public void setFromIndex(final int n) {
        AccessibilityRecordCompat.IMPL.setFromIndex(this.mRecord, n);
    }
    
    public void setItemCount(final int n) {
        AccessibilityRecordCompat.IMPL.setItemCount(this.mRecord, n);
    }
    
    public void setScrollable(final boolean b) {
        AccessibilityRecordCompat.IMPL.setScrollable(this.mRecord, b);
    }
    
    public void setToIndex(final int n) {
        AccessibilityRecordCompat.IMPL.setToIndex(this.mRecord, n);
    }
}
