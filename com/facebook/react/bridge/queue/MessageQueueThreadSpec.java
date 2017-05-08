// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge.queue;

public class MessageQueueThreadSpec
{
    private static final MessageQueueThreadSpec MAIN_UI_SPEC;
    private final String mName;
    private final long mStackSize;
    private final MessageQueueThreadSpec$ThreadType mThreadType;
    
    static {
        MAIN_UI_SPEC = new MessageQueueThreadSpec(MessageQueueThreadSpec$ThreadType.MAIN_UI, "main_ui");
    }
    
    private MessageQueueThreadSpec(final MessageQueueThreadSpec$ThreadType messageQueueThreadSpec$ThreadType, final String s) {
        this(messageQueueThreadSpec$ThreadType, s, 0L);
    }
    
    private MessageQueueThreadSpec(final MessageQueueThreadSpec$ThreadType mThreadType, final String mName, final long mStackSize) {
        this.mThreadType = mThreadType;
        this.mName = mName;
        this.mStackSize = mStackSize;
    }
    
    public static MessageQueueThreadSpec mainThreadSpec() {
        return MessageQueueThreadSpec.MAIN_UI_SPEC;
    }
    
    public static MessageQueueThreadSpec newBackgroundThreadSpec(final String s) {
        return new MessageQueueThreadSpec(MessageQueueThreadSpec$ThreadType.NEW_BACKGROUND, s);
    }
    
    public static MessageQueueThreadSpec newBackgroundThreadSpec(final String s, final long n) {
        return new MessageQueueThreadSpec(MessageQueueThreadSpec$ThreadType.NEW_BACKGROUND, s, n);
    }
    
    public String getName() {
        return this.mName;
    }
    
    public long getStackSize() {
        return this.mStackSize;
    }
    
    public MessageQueueThreadSpec$ThreadType getThreadType() {
        return this.mThreadType;
    }
}
