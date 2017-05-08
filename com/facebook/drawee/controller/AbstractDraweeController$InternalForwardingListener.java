// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.controller;

class AbstractDraweeController$InternalForwardingListener<INFO> extends ForwardingControllerListener<INFO>
{
    public static <INFO> AbstractDraweeController$InternalForwardingListener<INFO> createInternal(final ControllerListener<? super INFO> controllerListener, final ControllerListener<? super INFO> controllerListener2) {
        final AbstractDraweeController$InternalForwardingListener<Object> abstractDraweeController$InternalForwardingListener = (AbstractDraweeController$InternalForwardingListener<Object>)new AbstractDraweeController$InternalForwardingListener<INFO>();
        abstractDraweeController$InternalForwardingListener.addListener((ControllerListener<? super Object>)controllerListener);
        abstractDraweeController$InternalForwardingListener.addListener((ControllerListener<? super Object>)controllerListener2);
        return (AbstractDraweeController$InternalForwardingListener<INFO>)abstractDraweeController$InternalForwardingListener;
    }
}
