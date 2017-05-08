// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.common;

public enum Priority
{
    HIGH, 
    LOW, 
    MEDIUM;
    
    public static Priority getHigherPriority(final Priority priority, final Priority priority2) {
        if (priority != null) {
            if (priority2 == null) {
                return priority;
            }
            if (priority.ordinal() > priority2.ordinal()) {
                return priority;
            }
        }
        return priority2;
    }
}
