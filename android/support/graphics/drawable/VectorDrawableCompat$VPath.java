// 
// Decompiled by Procyon v0.5.30
// 

package android.support.graphics.drawable;

import android.graphics.Path;

class VectorDrawableCompat$VPath
{
    int mChangingConfigurations;
    protected PathParser$PathDataNode[] mNodes;
    String mPathName;
    
    public VectorDrawableCompat$VPath() {
        this.mNodes = null;
    }
    
    public VectorDrawableCompat$VPath(final VectorDrawableCompat$VPath vectorDrawableCompat$VPath) {
        this.mNodes = null;
        this.mPathName = vectorDrawableCompat$VPath.mPathName;
        this.mChangingConfigurations = vectorDrawableCompat$VPath.mChangingConfigurations;
        this.mNodes = PathParser.deepCopyNodes(vectorDrawableCompat$VPath.mNodes);
    }
    
    public String getPathName() {
        return this.mPathName;
    }
    
    public boolean isClipPath() {
        return false;
    }
    
    public void toPath(final Path path) {
        path.reset();
        if (this.mNodes != null) {
            PathParser$PathDataNode.nodesToPath(this.mNodes, path);
        }
    }
}
