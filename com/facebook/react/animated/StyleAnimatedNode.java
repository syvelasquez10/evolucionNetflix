// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import java.util.Iterator;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.HashMap;
import com.facebook.react.bridge.ReadableMap;
import java.util.Map;

class StyleAnimatedNode extends AnimatedNode
{
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    private final Map<String, Integer> mPropMapping;
    
    StyleAnimatedNode(ReadableMap map, final NativeAnimatedNodesManager mNativeAnimatedNodesManager) {
        map = map.getMap("style");
        final ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
        this.mPropMapping = new HashMap<String, Integer>();
        while (keySetIterator.hasNextKey()) {
            final String nextKey = keySetIterator.nextKey();
            this.mPropMapping.put(nextKey, map.getInt(nextKey));
        }
        this.mNativeAnimatedNodesManager = mNativeAnimatedNodesManager;
    }
    
    public void collectViewUpdates(final JavaOnlyMap javaOnlyMap) {
        for (final Map.Entry<String, Integer> entry : this.mPropMapping.entrySet()) {
            final AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(entry.getValue());
            if (nodeById == null) {
                throw new IllegalArgumentException("Mapped style node does not exists");
            }
            if (nodeById instanceof TransformAnimatedNode) {
                ((TransformAnimatedNode)nodeById).collectViewUpdates(javaOnlyMap);
            }
            else {
                if (!(nodeById instanceof ValueAnimatedNode)) {
                    throw new IllegalArgumentException("Unsupported type of node used in property node " + ((TransformAnimatedNode)nodeById).getClass());
                }
                javaOnlyMap.putDouble(entry.getKey(), ((ValueAnimatedNode)nodeById).getValue());
            }
        }
    }
}
