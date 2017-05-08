// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.internal;

public final class Objects$ToStringHelper
{
    private final String className;
    private Objects$ToStringHelper$ValueHolder holderHead;
    private Objects$ToStringHelper$ValueHolder holderTail;
    private boolean omitNullValues;
    
    private Objects$ToStringHelper(final String s) {
        this.holderHead = new Objects$ToStringHelper$ValueHolder(null);
        this.holderTail = this.holderHead;
        this.omitNullValues = false;
        this.className = Preconditions.checkNotNull(s);
    }
    
    private Objects$ToStringHelper$ValueHolder addHolder() {
        final Objects$ToStringHelper$ValueHolder objects$ToStringHelper$ValueHolder = new Objects$ToStringHelper$ValueHolder(null);
        this.holderTail.next = objects$ToStringHelper$ValueHolder;
        return this.holderTail = objects$ToStringHelper$ValueHolder;
    }
    
    private Objects$ToStringHelper addHolder(final String s, final Object value) {
        final Objects$ToStringHelper$ValueHolder addHolder = this.addHolder();
        addHolder.value = value;
        addHolder.name = Preconditions.checkNotNull(s);
        return this;
    }
    
    public Objects$ToStringHelper add(final String s, final int n) {
        return this.addHolder(s, String.valueOf(n));
    }
    
    public Objects$ToStringHelper add(final String s, final Object o) {
        return this.addHolder(s, o);
    }
    
    public Objects$ToStringHelper add(final String s, final boolean b) {
        return this.addHolder(s, String.valueOf(b));
    }
    
    @Override
    public String toString() {
        final boolean omitNullValues = this.omitNullValues;
        final StringBuilder append = new StringBuilder(32).append(this.className).append('{');
        Objects$ToStringHelper$ValueHolder objects$ToStringHelper$ValueHolder = this.holderHead.next;
        String s = "";
        while (objects$ToStringHelper$ValueHolder != null) {
            String s2 = null;
            Label_0100: {
                if (omitNullValues) {
                    s2 = s;
                    if (objects$ToStringHelper$ValueHolder.value == null) {
                        break Label_0100;
                    }
                }
                append.append(s);
                s2 = ", ";
                if (objects$ToStringHelper$ValueHolder.name != null) {
                    append.append(objects$ToStringHelper$ValueHolder.name).append('=');
                }
                append.append(objects$ToStringHelper$ValueHolder.value);
            }
            objects$ToStringHelper$ValueHolder = objects$ToStringHelper$ValueHolder.next;
            s = s2;
        }
        return append.append('}').toString();
    }
}
