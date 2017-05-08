// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.listener;

import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import com.facebook.common.logging.FLog;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

public class ForwardingRequestListener implements RequestListener
{
    private final List<RequestListener> mRequestListeners;
    
    public ForwardingRequestListener(final Set<RequestListener> set) {
        this.mRequestListeners = new ArrayList<RequestListener>(set.size());
        final Iterator<RequestListener> iterator = set.iterator();
        while (iterator.hasNext()) {
            this.mRequestListeners.add(iterator.next());
        }
    }
    
    private void onException(final String s, final Throwable t) {
        FLog.e("ForwardingRequestListener", s, t);
    }
    
    @Override
    public void onProducerEvent(final String s, final String s2, final String s3) {
        final int size = this.mRequestListeners.size();
        int i = 0;
    Label_0047_Outer:
        while (i < size) {
            final RequestListener requestListener = this.mRequestListeners.get(i);
            while (true) {
                try {
                    requestListener.onProducerEvent(s, s2, s3);
                    ++i;
                    continue Label_0047_Outer;
                }
                catch (Exception ex) {
                    this.onException("InternalListener exception in onIntermediateChunkStart", ex);
                    continue;
                }
                break;
            }
            break;
        }
    }
    
    @Override
    public void onProducerFinishWithCancellation(final String s, final String s2, final Map<String, String> map) {
        final int size = this.mRequestListeners.size();
        int i = 0;
    Label_0047_Outer:
        while (i < size) {
            final RequestListener requestListener = this.mRequestListeners.get(i);
            while (true) {
                try {
                    requestListener.onProducerFinishWithCancellation(s, s2, map);
                    ++i;
                    continue Label_0047_Outer;
                }
                catch (Exception ex) {
                    this.onException("InternalListener exception in onProducerFinishWithCancellation", ex);
                    continue;
                }
                break;
            }
            break;
        }
    }
    
    @Override
    public void onProducerFinishWithFailure(final String s, final String s2, final Throwable t, final Map<String, String> map) {
        final int size = this.mRequestListeners.size();
        int i = 0;
    Label_0049_Outer:
        while (i < size) {
            final RequestListener requestListener = this.mRequestListeners.get(i);
            while (true) {
                try {
                    requestListener.onProducerFinishWithFailure(s, s2, t, map);
                    ++i;
                    continue Label_0049_Outer;
                }
                catch (Exception ex) {
                    this.onException("InternalListener exception in onProducerFinishWithFailure", ex);
                    continue;
                }
                break;
            }
            break;
        }
    }
    
    @Override
    public void onProducerFinishWithSuccess(final String s, final String s2, final Map<String, String> map) {
        final int size = this.mRequestListeners.size();
        int i = 0;
    Label_0047_Outer:
        while (i < size) {
            final RequestListener requestListener = this.mRequestListeners.get(i);
            while (true) {
                try {
                    requestListener.onProducerFinishWithSuccess(s, s2, map);
                    ++i;
                    continue Label_0047_Outer;
                }
                catch (Exception ex) {
                    this.onException("InternalListener exception in onProducerFinishWithSuccess", ex);
                    continue;
                }
                break;
            }
            break;
        }
    }
    
    @Override
    public void onProducerStart(final String s, final String s2) {
        final int size = this.mRequestListeners.size();
        int i = 0;
    Label_0043_Outer:
        while (i < size) {
            final RequestListener requestListener = this.mRequestListeners.get(i);
            while (true) {
                try {
                    requestListener.onProducerStart(s, s2);
                    ++i;
                    continue Label_0043_Outer;
                }
                catch (Exception ex) {
                    this.onException("InternalListener exception in onProducerStart", ex);
                    continue;
                }
                break;
            }
            break;
        }
    }
    
    @Override
    public void onRequestCancellation(final String s) {
        final int size = this.mRequestListeners.size();
        int i = 0;
    Label_0040_Outer:
        while (i < size) {
            final RequestListener requestListener = this.mRequestListeners.get(i);
            while (true) {
                try {
                    requestListener.onRequestCancellation(s);
                    ++i;
                    continue Label_0040_Outer;
                }
                catch (Exception ex) {
                    this.onException("InternalListener exception in onRequestCancellation", ex);
                    continue;
                }
                break;
            }
            break;
        }
    }
    
    @Override
    public void onRequestFailure(final ImageRequest imageRequest, final String s, final Throwable t, final boolean b) {
        final int size = this.mRequestListeners.size();
        int i = 0;
    Label_0049_Outer:
        while (i < size) {
            final RequestListener requestListener = this.mRequestListeners.get(i);
            while (true) {
                try {
                    requestListener.onRequestFailure(imageRequest, s, t, b);
                    ++i;
                    continue Label_0049_Outer;
                }
                catch (Exception ex) {
                    this.onException("InternalListener exception in onRequestFailure", ex);
                    continue;
                }
                break;
            }
            break;
        }
    }
    
    @Override
    public void onRequestStart(final ImageRequest imageRequest, final Object o, final String s, final boolean b) {
        final int size = this.mRequestListeners.size();
        int i = 0;
    Label_0049_Outer:
        while (i < size) {
            final RequestListener requestListener = this.mRequestListeners.get(i);
            while (true) {
                try {
                    requestListener.onRequestStart(imageRequest, o, s, b);
                    ++i;
                    continue Label_0049_Outer;
                }
                catch (Exception ex) {
                    this.onException("InternalListener exception in onRequestStart", ex);
                    continue;
                }
                break;
            }
            break;
        }
    }
    
    @Override
    public void onRequestSuccess(final ImageRequest imageRequest, final String s, final boolean b) {
        final int size = this.mRequestListeners.size();
        int i = 0;
    Label_0047_Outer:
        while (i < size) {
            final RequestListener requestListener = this.mRequestListeners.get(i);
            while (true) {
                try {
                    requestListener.onRequestSuccess(imageRequest, s, b);
                    ++i;
                    continue Label_0047_Outer;
                }
                catch (Exception ex) {
                    this.onException("InternalListener exception in onRequestSuccess", ex);
                    continue;
                }
                break;
            }
            break;
        }
    }
    
    @Override
    public boolean requiresExtraMap(final String s) {
        for (int size = this.mRequestListeners.size(), i = 0; i < size; ++i) {
            if (this.mRequestListeners.get(i).requiresExtraMap(s)) {
                return true;
            }
        }
        return false;
    }
}
