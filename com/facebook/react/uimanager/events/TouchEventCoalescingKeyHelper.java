// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.events;

import android.util.SparseIntArray;

public class TouchEventCoalescingKeyHelper
{
    private final SparseIntArray mDownTimeToCoalescingKey;
    
    public TouchEventCoalescingKeyHelper() {
        this.mDownTimeToCoalescingKey = new SparseIntArray();
    }
    
    public void addCoalescingKey(final long n) {
        this.mDownTimeToCoalescingKey.put((int)n, 0);
    }
    
    public short getCoalescingKey(final long n) {
        final int value = this.mDownTimeToCoalescingKey.get((int)n, -1);
        if (value == -1) {
            throw new RuntimeException("Tried to get non-existent cookie");
        }
        return (short)(value & 0xFFFF);
    }
    
    public boolean hasCoalescingKey(final long n) {
        return this.mDownTimeToCoalescingKey.get((int)n, -1) != -1;
    }
    
    public void incrementCoalescingKey(final long n) {
        final int value = this.mDownTimeToCoalescingKey.get((int)n, -1);
        if (value == -1) {
            throw new RuntimeException("Tried to increment non-existent cookie");
        }
        this.mDownTimeToCoalescingKey.put((int)n, value + 1);
    }
    
    public void removeCoalescingKey(final long n) {
        this.mDownTimeToCoalescingKey.delete((int)n);
    }
}
