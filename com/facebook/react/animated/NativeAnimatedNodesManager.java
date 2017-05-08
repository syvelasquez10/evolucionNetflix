// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import java.util.ArrayDeque;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.bridge.ReadableArray;
import java.util.List;
import java.util.ArrayList;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.infer.annotation.Assertions;
import java.util.HashMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIImplementation;
import java.util.Map;
import android.util.SparseArray;
import com.facebook.react.uimanager.events.EventDispatcherListener;

class NativeAnimatedNodesManager implements EventDispatcherListener
{
    private final SparseArray<AnimationDriver> mActiveAnimations;
    private int mAnimatedGraphBFSColor;
    private final SparseArray<AnimatedNode> mAnimatedNodes;
    private final Map<String, Map<String, String>> mCustomEventTypes;
    private final Map<String, EventAnimationDriver> mEventDrivers;
    private final UIImplementation mUIImplementation;
    private final SparseArray<AnimatedNode> mUpdatedNodes;
    
    public NativeAnimatedNodesManager(final UIManagerModule uiManagerModule) {
        this.mAnimatedNodes = (SparseArray<AnimatedNode>)new SparseArray();
        this.mActiveAnimations = (SparseArray<AnimationDriver>)new SparseArray();
        this.mUpdatedNodes = (SparseArray<AnimatedNode>)new SparseArray();
        this.mEventDrivers = new HashMap<String, EventAnimationDriver>();
        this.mAnimatedGraphBFSColor = 0;
        this.mUIImplementation = uiManagerModule.getUIImplementation();
        uiManagerModule.getEventDispatcher().addListener(this);
        this.mCustomEventTypes = Assertions.assertNotNull(uiManagerModule.getConstants()).get("customDirectEventTypes");
    }
    
    public void addAnimatedEventToView(final int n, final String s, final ReadableMap readableMap) {
        final int int1 = readableMap.getInt("animatedValueTag");
        final AnimatedNode animatedNode = (AnimatedNode)this.mAnimatedNodes.get(int1);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + int1 + " does not exists");
        }
        if (!(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("Animated node connected to event should beof type " + ValueAnimatedNode.class.getName());
        }
        final ReadableArray array = readableMap.getArray("nativeEventPath");
        final ArrayList list = new ArrayList<String>(array.size());
        for (int i = 0; i < array.size(); ++i) {
            list.add(array.getString(i));
        }
        this.mEventDrivers.put(n + s, new EventAnimationDriver((List<String>)list, (ValueAnimatedNode)animatedNode));
    }
    
    public void connectAnimatedNodeToView(final int n, final int mConnectedViewTag) {
        final AnimatedNode animatedNode = (AnimatedNode)this.mAnimatedNodes.get(n);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + n + " does not exists");
        }
        if (!(animatedNode instanceof PropsAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("Animated node connected to view should beof type " + PropsAnimatedNode.class.getName());
        }
        final PropsAnimatedNode propsAnimatedNode = (PropsAnimatedNode)animatedNode;
        if (propsAnimatedNode.mConnectedViewTag != -1) {
            throw new JSApplicationIllegalArgumentException("Animated node " + n + " is " + "already attached to a view");
        }
        propsAnimatedNode.mConnectedViewTag = mConnectedViewTag;
        this.mUpdatedNodes.put(n, (Object)animatedNode);
    }
    
    public void connectAnimatedNodes(final int n, final int n2) {
        final AnimatedNode animatedNode = (AnimatedNode)this.mAnimatedNodes.get(n);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + n + " does not exists");
        }
        final AnimatedNode animatedNode2 = (AnimatedNode)this.mAnimatedNodes.get(n2);
        if (animatedNode2 == null) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + n2 + " does not exists");
        }
        animatedNode.addChild(animatedNode2);
        this.mUpdatedNodes.put(n2, (Object)animatedNode2);
    }
    
    public void createAnimatedNode(final int mTag, final ReadableMap readableMap) {
        if (this.mAnimatedNodes.get(mTag) != null) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + mTag + " already exists");
        }
        final String string = readableMap.getString("type");
        AnimatedNode animatedNode;
        if ("style".equals(string)) {
            animatedNode = new StyleAnimatedNode(readableMap, this);
        }
        else if ("value".equals(string)) {
            animatedNode = new ValueAnimatedNode(readableMap);
        }
        else if ("props".equals(string)) {
            animatedNode = new PropsAnimatedNode(readableMap, this);
        }
        else if ("interpolation".equals(string)) {
            animatedNode = new InterpolationAnimatedNode(readableMap);
        }
        else if ("addition".equals(string)) {
            animatedNode = new AdditionAnimatedNode(readableMap, this);
        }
        else if ("division".equals(string)) {
            animatedNode = new DivisionAnimatedNode(readableMap, this);
        }
        else if ("multiplication".equals(string)) {
            animatedNode = new MultiplicationAnimatedNode(readableMap, this);
        }
        else if ("modulus".equals(string)) {
            animatedNode = new ModulusAnimatedNode(readableMap, this);
        }
        else if ("diffclamp".equals(string)) {
            animatedNode = new DiffClampAnimatedNode(readableMap, this);
        }
        else {
            if (!"transform".equals(string)) {
                throw new JSApplicationIllegalArgumentException("Unsupported node type: " + string);
            }
            animatedNode = new TransformAnimatedNode(readableMap, this);
        }
        animatedNode.mTag = mTag;
        this.mAnimatedNodes.put(mTag, (Object)animatedNode);
        this.mUpdatedNodes.put(mTag, (Object)animatedNode);
    }
    
    public void disconnectAnimatedNodeFromView(final int n, final int n2) {
        final AnimatedNode animatedNode = (AnimatedNode)this.mAnimatedNodes.get(n);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + n + " does not exists");
        }
        if (!(animatedNode instanceof PropsAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("Animated node connected to view should beof type " + PropsAnimatedNode.class.getName());
        }
        final PropsAnimatedNode propsAnimatedNode = (PropsAnimatedNode)animatedNode;
        if (propsAnimatedNode.mConnectedViewTag != n2) {
            throw new JSApplicationIllegalArgumentException("Attempting to disconnect view that has not been connected with the given animated node");
        }
        propsAnimatedNode.mConnectedViewTag = -1;
    }
    
    public void disconnectAnimatedNodes(final int n, final int n2) {
        final AnimatedNode animatedNode = (AnimatedNode)this.mAnimatedNodes.get(n);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + n + " does not exists");
        }
        final AnimatedNode animatedNode2 = (AnimatedNode)this.mAnimatedNodes.get(n2);
        if (animatedNode2 == null) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + n2 + " does not exists");
        }
        animatedNode.removeChild(animatedNode2);
        this.mUpdatedNodes.put(n2, (Object)animatedNode2);
    }
    
    public void dropAnimatedNode(final int n) {
        this.mAnimatedNodes.remove(n);
        this.mUpdatedNodes.remove(n);
    }
    
    public void extractAnimatedNodeOffset(final int n) {
        final AnimatedNode animatedNode = (AnimatedNode)this.mAnimatedNodes.get(n);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + n + " does not exists or is not a 'value' node");
        }
        ((ValueAnimatedNode)animatedNode).extractOffset();
    }
    
    public void flattenAnimatedNodeOffset(final int n) {
        final AnimatedNode animatedNode = (AnimatedNode)this.mAnimatedNodes.get(n);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + n + " does not exists or is not a 'value' node");
        }
        ((ValueAnimatedNode)animatedNode).flattenOffset();
    }
    
    AnimatedNode getNodeById(final int n) {
        return (AnimatedNode)this.mAnimatedNodes.get(n);
    }
    
    public boolean hasActiveAnimations() {
        return this.mActiveAnimations.size() > 0 || this.mUpdatedNodes.size() > 0;
    }
    
    @Override
    public void onEventDispatch(final Event event) {
        if (UiThreadUtil.isOnUiThread() && !this.mEventDrivers.isEmpty()) {
            String eventName = event.getEventName();
            final Map<String, String> map = this.mCustomEventTypes.get(eventName);
            if (map != null) {
                eventName = map.get("registrationName");
            }
            final EventAnimationDriver eventAnimationDriver = this.mEventDrivers.get(event.getViewTag() + eventName);
            if (eventAnimationDriver != null) {
                event.dispatch(eventAnimationDriver);
                this.mUpdatedNodes.put(eventAnimationDriver.mValueNode.mTag, (Object)eventAnimationDriver.mValueNode);
            }
        }
    }
    
    public void removeAnimatedEventFromView(final int n, final String s) {
        this.mEventDrivers.remove(n + s);
    }
    
    public void runUpdates(final long n) {
        UiThreadUtil.assertOnUiThread();
        ++this.mAnimatedGraphBFSColor;
        if (this.mAnimatedGraphBFSColor == 0) {
            ++this.mAnimatedGraphBFSColor;
        }
        final ArrayDeque<Object> arrayDeque = new ArrayDeque<Object>();
        int i = 0;
        int n2 = 0;
        while (i < this.mUpdatedNodes.size()) {
            final AnimatedNode animatedNode = (AnimatedNode)this.mUpdatedNodes.valueAt(i);
            int n3 = n2;
            if (animatedNode.mBFSColor != this.mAnimatedGraphBFSColor) {
                animatedNode.mBFSColor = this.mAnimatedGraphBFSColor;
                n3 = n2 + 1;
                arrayDeque.add(animatedNode);
            }
            ++i;
            n2 = n3;
        }
        int n4 = 0;
        boolean b = false;
        int n5 = n2;
        int n6;
        while (true) {
            n6 = n5;
            if (n4 >= this.mActiveAnimations.size()) {
                break;
            }
            final AnimationDriver animationDriver = (AnimationDriver)this.mActiveAnimations.valueAt(n4);
            animationDriver.runAnimationStep(n);
            final ValueAnimatedNode mAnimatedValue = animationDriver.mAnimatedValue;
            int n7 = n5;
            if (mAnimatedValue.mBFSColor != this.mAnimatedGraphBFSColor) {
                mAnimatedValue.mBFSColor = this.mAnimatedGraphBFSColor;
                n7 = n5 + 1;
                arrayDeque.add(mAnimatedValue);
            }
            if (animationDriver.mHasFinished) {
                b = true;
            }
            ++n4;
            n5 = n7;
        }
        while (!arrayDeque.isEmpty()) {
            final AnimatedNode animatedNode2 = arrayDeque.poll();
            int n9;
            if (animatedNode2.mChildren != null) {
                int n8 = 0;
                while (true) {
                    n9 = n6;
                    if (n8 >= animatedNode2.mChildren.size()) {
                        break;
                    }
                    final AnimatedNode animatedNode3 = animatedNode2.mChildren.get(n8);
                    ++animatedNode3.mActiveIncomingNodes;
                    int n10 = n6;
                    if (animatedNode3.mBFSColor != this.mAnimatedGraphBFSColor) {
                        animatedNode3.mBFSColor = this.mAnimatedGraphBFSColor;
                        n10 = n6 + 1;
                        arrayDeque.add(animatedNode3);
                    }
                    ++n8;
                    n6 = n10;
                }
            }
            else {
                n9 = n6;
            }
            n6 = n9;
        }
        ++this.mAnimatedGraphBFSColor;
        if (this.mAnimatedGraphBFSColor == 0) {
            ++this.mAnimatedGraphBFSColor;
        }
        int j = 0;
        int n11 = 0;
        while (j < this.mUpdatedNodes.size()) {
            final AnimatedNode animatedNode4 = (AnimatedNode)this.mUpdatedNodes.valueAt(j);
            int n12 = n11;
            if (animatedNode4.mActiveIncomingNodes == 0) {
                n12 = n11;
                if (animatedNode4.mBFSColor != this.mAnimatedGraphBFSColor) {
                    animatedNode4.mBFSColor = this.mAnimatedGraphBFSColor;
                    n12 = n11 + 1;
                    arrayDeque.add(animatedNode4);
                }
            }
            ++j;
            n11 = n12;
        }
        int n13 = 0;
        int n14 = n11;
        int n15;
        while (true) {
            n15 = n14;
            if (n13 >= this.mActiveAnimations.size()) {
                break;
            }
            final ValueAnimatedNode mAnimatedValue2 = ((AnimationDriver)this.mActiveAnimations.valueAt(n13)).mAnimatedValue;
            int n16 = n14;
            if (mAnimatedValue2.mActiveIncomingNodes == 0) {
                n16 = n14;
                if (mAnimatedValue2.mBFSColor != this.mAnimatedGraphBFSColor) {
                    mAnimatedValue2.mBFSColor = this.mAnimatedGraphBFSColor;
                    n16 = n14 + 1;
                    arrayDeque.add(mAnimatedValue2);
                }
            }
            ++n13;
            n14 = n16;
        }
        while (!arrayDeque.isEmpty()) {
            final ValueAnimatedNode valueAnimatedNode = arrayDeque.poll();
            valueAnimatedNode.update();
            if (valueAnimatedNode instanceof PropsAnimatedNode) {
                ((PropsAnimatedNode)valueAnimatedNode).updateView(this.mUIImplementation);
            }
            if (valueAnimatedNode instanceof ValueAnimatedNode) {
                valueAnimatedNode.onValueUpdate();
            }
            int n18;
            if (valueAnimatedNode.mChildren != null) {
                int n17 = 0;
                while (true) {
                    n18 = n15;
                    if (n17 >= valueAnimatedNode.mChildren.size()) {
                        break;
                    }
                    final AnimatedNode animatedNode5 = valueAnimatedNode.mChildren.get(n17);
                    --animatedNode5.mActiveIncomingNodes;
                    int n19 = n15;
                    if (animatedNode5.mBFSColor != this.mAnimatedGraphBFSColor) {
                        n19 = n15;
                        if (animatedNode5.mActiveIncomingNodes == 0) {
                            animatedNode5.mBFSColor = this.mAnimatedGraphBFSColor;
                            n19 = n15 + 1;
                            arrayDeque.add(animatedNode5);
                        }
                    }
                    ++n17;
                    n15 = n19;
                }
            }
            else {
                n18 = n15;
            }
            n15 = n18;
        }
        if (n6 != n15) {
            throw new IllegalStateException("Looks like animated nodes graph has cycles, there are " + n6 + " but toposort visited only " + n15);
        }
        this.mUpdatedNodes.clear();
        if (b) {
            for (int k = this.mActiveAnimations.size() - 1; k >= 0; --k) {
                final AnimationDriver animationDriver2 = (AnimationDriver)this.mActiveAnimations.valueAt(k);
                if (animationDriver2.mHasFinished) {
                    final WritableMap map = Arguments.createMap();
                    map.putBoolean("finished", true);
                    animationDriver2.mEndCallback.invoke(map);
                    this.mActiveAnimations.removeAt(k);
                }
            }
        }
    }
    
    public void setAnimatedNodeOffset(final int n, final double mOffset) {
        final AnimatedNode animatedNode = (AnimatedNode)this.mAnimatedNodes.get(n);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + n + " does not exists or is not a 'value' node");
        }
        ((ValueAnimatedNode)animatedNode).mOffset = mOffset;
        this.mUpdatedNodes.put(n, (Object)animatedNode);
    }
    
    public void setAnimatedNodeValue(final int n, final double mValue) {
        final AnimatedNode animatedNode = (AnimatedNode)this.mAnimatedNodes.get(n);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + n + " does not exists or is not a 'value' node");
        }
        ((ValueAnimatedNode)animatedNode).mValue = mValue;
        this.mUpdatedNodes.put(n, (Object)animatedNode);
    }
    
    public void startAnimatingNode(final int mId, final int n, final ReadableMap readableMap, final Callback mEndCallback) {
        final AnimatedNode animatedNode = (AnimatedNode)this.mAnimatedNodes.get(n);
        if (animatedNode == null) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + n + " does not exists");
        }
        if (!(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("Animated node should be of type " + ValueAnimatedNode.class.getName());
        }
        final String string = readableMap.getString("type");
        AnimationDriver animationDriver;
        if ("frames".equals(string)) {
            animationDriver = new FrameBasedAnimationDriver(readableMap);
        }
        else if ("spring".equals(string)) {
            animationDriver = new SpringAnimation(readableMap);
        }
        else {
            if (!"decay".equals(string)) {
                throw new JSApplicationIllegalArgumentException("Unsupported animation type: " + string);
            }
            animationDriver = new DecayAnimation(readableMap);
        }
        animationDriver.mId = mId;
        animationDriver.mEndCallback = mEndCallback;
        animationDriver.mAnimatedValue = (ValueAnimatedNode)animatedNode;
        this.mActiveAnimations.put(mId, (Object)animationDriver);
    }
    
    public void startListeningToAnimatedNodeValue(final int n, final AnimatedNodeValueListener valueListener) {
        final AnimatedNode animatedNode = (AnimatedNode)this.mAnimatedNodes.get(n);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + n + " does not exists or is not a 'value' node");
        }
        ((ValueAnimatedNode)animatedNode).setValueListener(valueListener);
    }
    
    public void stopAnimation(final int n) {
        for (int i = 0; i < this.mActiveAnimations.size(); ++i) {
            final AnimationDriver animationDriver = (AnimationDriver)this.mActiveAnimations.valueAt(i);
            if (animationDriver.mId == n) {
                final WritableMap map = Arguments.createMap();
                map.putBoolean("finished", false);
                animationDriver.mEndCallback.invoke(map);
                this.mActiveAnimations.removeAt(i);
                break;
            }
        }
    }
    
    public void stopListeningToAnimatedNodeValue(final int n) {
        final AnimatedNode animatedNode = (AnimatedNode)this.mAnimatedNodes.get(n);
        if (animatedNode == null || !(animatedNode instanceof ValueAnimatedNode)) {
            throw new JSApplicationIllegalArgumentException("Animated node with tag " + n + " does not exists or is not a 'value' node");
        }
        ((ValueAnimatedNode)animatedNode).setValueListener(null);
    }
}
