// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import java.util.Iterator;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import java.util.ArrayList;
import com.facebook.react.bridge.ReadableMap;
import java.util.List;

class TransformAnimatedNode extends AnimatedNode
{
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    private final List<TransformAnimatedNode$TransformConfig> mTransformConfigs;
    
    TransformAnimatedNode(final ReadableMap readableMap, final NativeAnimatedNodesManager mNativeAnimatedNodesManager) {
        final ReadableArray array = readableMap.getArray("transforms");
        this.mTransformConfigs = new ArrayList<TransformAnimatedNode$TransformConfig>(array.size());
        for (int i = 0; i < array.size(); ++i) {
            final ReadableMap map = array.getMap(i);
            final String string = map.getString("property");
            if (map.getString("type").equals("animated")) {
                final TransformAnimatedNode$AnimatedTransformConfig transformAnimatedNode$AnimatedTransformConfig = new TransformAnimatedNode$AnimatedTransformConfig(this, null);
                transformAnimatedNode$AnimatedTransformConfig.mProperty = string;
                transformAnimatedNode$AnimatedTransformConfig.mNodeTag = map.getInt("nodeTag");
                this.mTransformConfigs.add(transformAnimatedNode$AnimatedTransformConfig);
            }
            else {
                final TransformAnimatedNode$StaticTransformConfig transformAnimatedNode$StaticTransformConfig = new TransformAnimatedNode$StaticTransformConfig(this, null);
                transformAnimatedNode$StaticTransformConfig.mProperty = string;
                transformAnimatedNode$StaticTransformConfig.mValue = map.getDouble("value");
                this.mTransformConfigs.add(transformAnimatedNode$StaticTransformConfig);
            }
        }
        this.mNativeAnimatedNodesManager = mNativeAnimatedNodesManager;
    }
    
    public void collectViewUpdates(final JavaOnlyMap javaOnlyMap) {
        final ArrayList<JavaOnlyMap> list = new ArrayList<JavaOnlyMap>(this.mTransformConfigs.size());
        for (final TransformAnimatedNode$TransformConfig transformAnimatedNode$TransformConfig : this.mTransformConfigs) {
            double n;
            if (transformAnimatedNode$TransformConfig instanceof TransformAnimatedNode$AnimatedTransformConfig) {
                final AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(((TransformAnimatedNode$AnimatedTransformConfig)transformAnimatedNode$TransformConfig).mNodeTag);
                if (nodeById == null) {
                    throw new IllegalArgumentException("Mapped style node does not exists");
                }
                if (!(nodeById instanceof ValueAnimatedNode)) {
                    throw new IllegalArgumentException("Unsupported type of node used as a transform child node " + ((ValueAnimatedNode)nodeById).getClass());
                }
                n = ((ValueAnimatedNode)nodeById).getValue();
            }
            else {
                n = ((TransformAnimatedNode$StaticTransformConfig)transformAnimatedNode$TransformConfig).mValue;
            }
            list.add(JavaOnlyMap.of(transformAnimatedNode$TransformConfig.mProperty, n));
        }
        javaOnlyMap.putArray("transform", JavaOnlyArray.from(list));
    }
}
