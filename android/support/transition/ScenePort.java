// 
// Decompiled by Procyon v0.5.30
// 

package android.support.transition;

import android.view.View;
import android.view.ViewGroup;
import android.annotation.TargetApi;

@TargetApi(14)
final class ScenePort
{
    Runnable mExitAction;
    private ViewGroup mSceneRoot;
    
    static ScenePort getCurrentScene(final View view) {
        return (ScenePort)view.getTag(R$id.transition_current_scene);
    }
    
    static void setCurrentScene(final View view, final ScenePort scenePort) {
        view.setTag(R$id.transition_current_scene, (Object)scenePort);
    }
    
    public void exit() {
        if (getCurrentScene((View)this.mSceneRoot) == this && this.mExitAction != null) {
            this.mExitAction.run();
        }
    }
}
