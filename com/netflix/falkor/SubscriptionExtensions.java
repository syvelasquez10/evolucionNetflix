// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

public class SubscriptionExtensions
{
    public static final Subscription EMPTY;
    
    static {
        EMPTY = new Subscription() {
            @Override
            public void dispose() {
            }
        };
    }
}
