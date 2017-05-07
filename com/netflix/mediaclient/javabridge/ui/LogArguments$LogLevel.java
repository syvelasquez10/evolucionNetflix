// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

public enum LogArguments$LogLevel
{
    CONSOLE(-1), 
    DEBUG(20), 
    ERROR(50), 
    FATAL(60), 
    INFO(30), 
    TRACE(10), 
    WARN(40);
    
    private int mLevel;
    
    private LogArguments$LogLevel(final int mLevel) {
        this.mLevel = mLevel;
    }
    
    public int getLevel() {
        return this.mLevel;
    }
    
    public String getLevelInString() {
        return Integer.toString(this.mLevel);
    }
}
