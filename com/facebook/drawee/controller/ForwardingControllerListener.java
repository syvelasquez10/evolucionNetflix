// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class ForwardingControllerListener<INFO> implements ControllerListener<INFO>
{
    private final List<ControllerListener<? super INFO>> mListeners;
    
    public ForwardingControllerListener() {
        this.mListeners = new ArrayList<ControllerListener<? super INFO>>(2);
    }
    
    private void onException(final String s, final Throwable t) {
        synchronized (this) {
            Log.e("FdingControllerListener", s, t);
        }
    }
    
    public void addListener(final ControllerListener<? super INFO> controllerListener) {
        synchronized (this) {
            this.mListeners.add(controllerListener);
        }
    }
    
    public void clearListeners() {
        synchronized (this) {
            this.mListeners.clear();
        }
    }
    
    @Override
    public void onFailure(final String s, final Throwable t) {
        synchronized (this) {
            final int size = this.mListeners.size();
            int i = 0;
            while (i < size) {
                final ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
                try {
                    controllerListener.onFailure(s, t);
                    ++i;
                }
                catch (Exception ex) {
                    this.onException("InternalListener exception in onFailure", ex);
                }
            }
        }
    }
    // monitorexit(this)
    
    @Override
    public void onFinalImageSet(final String s, final INFO info, final Animatable animatable) {
        synchronized (this) {
            final int size = this.mListeners.size();
            int i = 0;
            while (i < size) {
                final ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
                try {
                    controllerListener.onFinalImageSet(s, info, animatable);
                    ++i;
                }
                catch (Exception ex) {
                    this.onException("InternalListener exception in onFinalImageSet", ex);
                }
            }
        }
    }
    // monitorexit(this)
    
    @Override
    public void onIntermediateImageFailed(final String s, final Throwable t) {
        final int size = this.mListeners.size();
        int i = 0;
    Label_0043_Outer:
        while (i < size) {
            final ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
            while (true) {
                try {
                    controllerListener.onIntermediateImageFailed(s, t);
                    ++i;
                    continue Label_0043_Outer;
                }
                catch (Exception ex) {
                    this.onException("InternalListener exception in onIntermediateImageFailed", ex);
                    continue;
                }
                break;
            }
            break;
        }
    }
    
    @Override
    public void onIntermediateImageSet(final String s, final INFO info) {
        final int size = this.mListeners.size();
        int i = 0;
    Label_0043_Outer:
        while (i < size) {
            final ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
            while (true) {
                try {
                    controllerListener.onIntermediateImageSet(s, info);
                    ++i;
                    continue Label_0043_Outer;
                }
                catch (Exception ex) {
                    this.onException("InternalListener exception in onIntermediateImageSet", ex);
                    continue;
                }
                break;
            }
            break;
        }
    }
    
    @Override
    public void onRelease(final String s) {
        synchronized (this) {
            final int size = this.mListeners.size();
            int i = 0;
            while (i < size) {
                final ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
                try {
                    controllerListener.onRelease(s);
                    ++i;
                }
                catch (Exception ex) {
                    this.onException("InternalListener exception in onRelease", ex);
                }
            }
        }
    }
    // monitorexit(this)
    
    @Override
    public void onSubmit(final String s, final Object o) {
        synchronized (this) {
            final int size = this.mListeners.size();
            int i = 0;
            while (i < size) {
                final ControllerListener<? super INFO> controllerListener = this.mListeners.get(i);
                try {
                    controllerListener.onSubmit(s, o);
                    ++i;
                }
                catch (Exception ex) {
                    this.onException("InternalListener exception in onSubmit", ex);
                }
            }
        }
    }
    // monitorexit(this)
}
