// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import java.util.Iterator;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.uimanager.UIImplementation;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.HashMap;
import com.facebook.react.bridge.ReadableMap;
import java.util.Map;

class PropsAnimatedNode extends AnimatedNode
{
    int mConnectedViewTag;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    private final Map<String, Integer> mPropMapping;
    
    PropsAnimatedNode(ReadableMap map, final NativeAnimatedNodesManager mNativeAnimatedNodesManager) {
        this.mConnectedViewTag = -1;
        map = map.getMap("props");
        final ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
        this.mPropMapping = new HashMap<String, Integer>();
        while (keySetIterator.hasNextKey()) {
            final String nextKey = keySetIterator.nextKey();
            this.mPropMapping.put(nextKey, map.getInt(nextKey));
        }
        this.mNativeAnimatedNodesManager = mNativeAnimatedNodesManager;
    }
    
    public final void updateView(final UIImplementation uiImplementation) {
        if (this.mConnectedViewTag == -1) {
            throw new IllegalStateException("Node has not been attached to a view");
        }
        final JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        for (final Map.Entry<String, Integer> entry : this.mPropMapping.entrySet()) {
            final AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(entry.getValue());
            if (nodeById == null) {
                throw new IllegalArgumentException("Mapped property node does not exists");
            }
            if (nodeById instanceof StyleAnimatedNode) {
                ((StyleAnimatedNode)nodeById).collectViewUpdates(javaOnlyMap);
            }
            else {
                if (!(nodeById instanceof ValueAnimatedNode)) {
                    throw new IllegalArgumentException("Unsupported type of node used in property node " + ((StyleAnimatedNode)nodeById).getClass());
                }
                javaOnlyMap.putDouble(entry.getKey(), ((ValueAnimatedNode)nodeById).getValue());
            }
        }
        uiImplementation.synchronouslyUpdateViewOnUIThread(this.mConnectedViewTag, new ReactStylesDiffMap(javaOnlyMap));
    }
}
