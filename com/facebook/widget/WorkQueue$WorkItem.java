// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

interface WorkQueue$WorkItem
{
    boolean cancel();
    
    boolean isRunning();
    
    void moveToFront();
}
