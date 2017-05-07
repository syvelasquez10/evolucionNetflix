// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.error;

public class NFError$Response
{
    public static final int CONTINUE_PLAY = 1;
    public static final int IGNORE = 2;
    public static final int TERMINATE_PLAY = 0;
    public int action;
    public int labelId;
    
    public NFError$Response() {
        this.labelId = 2131165248;
        this.action = 0;
    }
}
