// 
// Decompiled by Procyon v0.5.30
// 

package android.support.graphics.drawable;

import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.Canvas;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import java.util.Stack;
import android.util.AttributeSet;
import android.content.res.XmlResourceParser;
import java.io.IOException;
import android.util.Log;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParser;
import android.util.Xml;
import android.support.v4.content.res.ResourcesCompat;
import android.os.Build$VERSION;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.PorterDuffColorFilter;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable$ConstantState;
import android.graphics.PorterDuff$Mode;
import android.annotation.TargetApi;

@TargetApi(21)
public class VectorDrawableCompat extends VectorDrawableCommon
{
    static final PorterDuff$Mode DEFAULT_TINT_MODE;
    private boolean mAllowCaching;
    private Drawable$ConstantState mCachedConstantStateDelegate;
    private ColorFilter mColorFilter;
    private boolean mMutated;
    private PorterDuffColorFilter mTintFilter;
    private final Rect mTmpBounds;
    private final float[] mTmpFloats;
    private final Matrix mTmpMatrix;
    private VectorDrawableCompat$VectorDrawableCompatState mVectorState;
    
    static {
        DEFAULT_TINT_MODE = PorterDuff$Mode.SRC_IN;
    }
    
    VectorDrawableCompat() {
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = new VectorDrawableCompat$VectorDrawableCompatState();
    }
    
    VectorDrawableCompat(final VectorDrawableCompat$VectorDrawableCompatState mVectorState) {
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = mVectorState;
        this.mTintFilter = this.updateTintFilter(this.mTintFilter, mVectorState.mTint, mVectorState.mTintMode);
    }
    
    static int applyAlpha(final int n, final float n2) {
        return (int)(Color.alpha(n) * n2) << 24 | (0xFFFFFF & n);
    }
    
    public static VectorDrawableCompat create(final Resources resources, int next, final Resources$Theme resources$Theme) {
        if (Build$VERSION.SDK_INT >= 23) {
            final VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
            vectorDrawableCompat.mDelegateDrawable = ResourcesCompat.getDrawable(resources, next, resources$Theme);
            vectorDrawableCompat.mCachedConstantStateDelegate = new VectorDrawableCompat$VectorDrawableDelegateState(vectorDrawableCompat.mDelegateDrawable.getConstantState());
            return vectorDrawableCompat;
        }
        try {
            final XmlResourceParser xml = resources.getXml(next);
            Xml.asAttributeSet((XmlPullParser)xml);
            do {
                next = ((XmlPullParser)xml).next();
            } while (next != 2 && next != 1);
            if (next != 2) {
                throw new XmlPullParserException("No start tag found");
            }
            goto Label_0102;
        }
        catch (XmlPullParserException ex) {
            Log.e("VectorDrawableCompat", "parser error", (Throwable)ex);
        }
        catch (IOException ex2) {
            Log.e("VectorDrawableCompat", "parser error", (Throwable)ex2);
            goto Label_0100;
        }
    }
    
    public static VectorDrawableCompat createFromXmlInner(final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        final VectorDrawableCompat vectorDrawableCompat = new VectorDrawableCompat();
        vectorDrawableCompat.inflate(resources, xmlPullParser, set, resources$Theme);
        return vectorDrawableCompat;
    }
    
    private void inflateInternal(final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        final VectorDrawableCompat$VectorDrawableCompatState mVectorState = this.mVectorState;
        final VectorDrawableCompat$VPathRenderer mvPathRenderer = mVectorState.mVPathRenderer;
        final Stack<VectorDrawableCompat$VGroup> stack = new Stack<VectorDrawableCompat$VGroup>();
        stack.push(mvPathRenderer.mRootGroup);
        int i = xmlPullParser.getEventType();
        int n = 1;
        while (i != 1) {
            int n2;
            if (i == 2) {
                final String name = xmlPullParser.getName();
                final VectorDrawableCompat$VGroup vectorDrawableCompat$VGroup = stack.peek();
                if ("path".equals(name)) {
                    final VectorDrawableCompat$VFullPath vectorDrawableCompat$VFullPath = new VectorDrawableCompat$VFullPath();
                    vectorDrawableCompat$VFullPath.inflate(resources, set, resources$Theme, xmlPullParser);
                    vectorDrawableCompat$VGroup.mChildren.add(vectorDrawableCompat$VFullPath);
                    if (vectorDrawableCompat$VFullPath.getPathName() != null) {
                        mvPathRenderer.mVGTargetsMap.put(vectorDrawableCompat$VFullPath.getPathName(), vectorDrawableCompat$VFullPath);
                    }
                    n = 0;
                    mVectorState.mChangingConfigurations |= vectorDrawableCompat$VFullPath.mChangingConfigurations;
                }
                else if ("clip-path".equals(name)) {
                    final VectorDrawableCompat$VClipPath vectorDrawableCompat$VClipPath = new VectorDrawableCompat$VClipPath();
                    vectorDrawableCompat$VClipPath.inflate(resources, set, resources$Theme, xmlPullParser);
                    vectorDrawableCompat$VGroup.mChildren.add(vectorDrawableCompat$VClipPath);
                    if (vectorDrawableCompat$VClipPath.getPathName() != null) {
                        mvPathRenderer.mVGTargetsMap.put(vectorDrawableCompat$VClipPath.getPathName(), vectorDrawableCompat$VClipPath);
                    }
                    mVectorState.mChangingConfigurations |= vectorDrawableCompat$VClipPath.mChangingConfigurations;
                }
                else if ("group".equals(name)) {
                    final VectorDrawableCompat$VGroup vectorDrawableCompat$VGroup2 = new VectorDrawableCompat$VGroup();
                    vectorDrawableCompat$VGroup2.inflate(resources, set, resources$Theme, xmlPullParser);
                    vectorDrawableCompat$VGroup.mChildren.add(vectorDrawableCompat$VGroup2);
                    stack.push(vectorDrawableCompat$VGroup2);
                    if (vectorDrawableCompat$VGroup2.getGroupName() != null) {
                        mvPathRenderer.mVGTargetsMap.put(vectorDrawableCompat$VGroup2.getGroupName(), vectorDrawableCompat$VGroup2);
                    }
                    mVectorState.mChangingConfigurations |= vectorDrawableCompat$VGroup2.mChangingConfigurations;
                }
                n2 = n;
            }
            else {
                n2 = n;
                if (i == 3) {
                    n2 = n;
                    if ("group".equals(xmlPullParser.getName())) {
                        stack.pop();
                        n2 = n;
                    }
                }
            }
            i = xmlPullParser.next();
            n = n2;
        }
        if (n != 0) {
            final StringBuffer sb = new StringBuffer();
            if (sb.length() > 0) {
                sb.append(" or ");
            }
            sb.append("path");
            throw new XmlPullParserException("no " + (Object)sb + " defined");
        }
    }
    
    private boolean needMirroring() {
        return false;
    }
    
    private static PorterDuff$Mode parseTintModeCompat(final int n, final PorterDuff$Mode porterDuff$Mode) {
        switch (n) {
            default: {
                return porterDuff$Mode;
            }
            case 3: {
                return PorterDuff$Mode.SRC_OVER;
            }
            case 5: {
                return PorterDuff$Mode.SRC_IN;
            }
            case 9: {
                return PorterDuff$Mode.SRC_ATOP;
            }
            case 14: {
                return PorterDuff$Mode.MULTIPLY;
            }
            case 15: {
                return PorterDuff$Mode.SCREEN;
            }
            case 16: {
                return PorterDuff$Mode.ADD;
            }
        }
    }
    
    private void updateStateFromTypedArray(final TypedArray typedArray, final XmlPullParser xmlPullParser) {
        final VectorDrawableCompat$VectorDrawableCompatState mVectorState = this.mVectorState;
        final VectorDrawableCompat$VPathRenderer mvPathRenderer = mVectorState.mVPathRenderer;
        mVectorState.mTintMode = parseTintModeCompat(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff$Mode.SRC_IN);
        final ColorStateList colorStateList = typedArray.getColorStateList(1);
        if (colorStateList != null) {
            mVectorState.mTint = colorStateList;
        }
        mVectorState.mAutoMirrored = TypedArrayUtils.getNamedBoolean(typedArray, xmlPullParser, "autoMirrored", 5, mVectorState.mAutoMirrored);
        mvPathRenderer.mViewportWidth = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "viewportWidth", 7, mvPathRenderer.mViewportWidth);
        mvPathRenderer.mViewportHeight = TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "viewportHeight", 8, mvPathRenderer.mViewportHeight);
        if (mvPathRenderer.mViewportWidth <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (mvPathRenderer.mViewportHeight <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
        mvPathRenderer.mBaseWidth = typedArray.getDimension(3, mvPathRenderer.mBaseWidth);
        mvPathRenderer.mBaseHeight = typedArray.getDimension(2, mvPathRenderer.mBaseHeight);
        if (mvPathRenderer.mBaseWidth <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
        }
        if (mvPathRenderer.mBaseHeight <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
        }
        mvPathRenderer.setAlpha(TypedArrayUtils.getNamedFloat(typedArray, xmlPullParser, "alpha", 4, mvPathRenderer.getAlpha()));
        final String string = typedArray.getString(0);
        if (string != null) {
            mvPathRenderer.mRootName = string;
            mvPathRenderer.mVGTargetsMap.put(string, mvPathRenderer);
        }
    }
    
    public boolean canApplyTheme() {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.canApplyTheme(this.mDelegateDrawable);
        }
        return false;
    }
    
    public void draw(final Canvas canvas) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.draw(canvas);
        }
        else {
            this.copyBounds(this.mTmpBounds);
            if (this.mTmpBounds.width() > 0 && this.mTmpBounds.height() > 0) {
                Object o;
                if (this.mColorFilter == null) {
                    o = this.mTintFilter;
                }
                else {
                    o = this.mColorFilter;
                }
                canvas.getMatrix(this.mTmpMatrix);
                this.mTmpMatrix.getValues(this.mTmpFloats);
                float abs = Math.abs(this.mTmpFloats[0]);
                float abs2 = Math.abs(this.mTmpFloats[4]);
                final float abs3 = Math.abs(this.mTmpFloats[1]);
                final float abs4 = Math.abs(this.mTmpFloats[3]);
                if (abs3 != 0.0f || abs4 != 0.0f) {
                    abs2 = 1.0f;
                    abs = 1.0f;
                }
                final int n = (int)(abs * this.mTmpBounds.width());
                final int n2 = (int)(abs2 * this.mTmpBounds.height());
                final int min = Math.min(2048, n);
                final int min2 = Math.min(2048, n2);
                if (min > 0 && min2 > 0) {
                    final int save = canvas.save();
                    canvas.translate((float)this.mTmpBounds.left, (float)this.mTmpBounds.top);
                    if (this.needMirroring()) {
                        canvas.translate((float)this.mTmpBounds.width(), 0.0f);
                        canvas.scale(-1.0f, 1.0f);
                    }
                    this.mTmpBounds.offsetTo(0, 0);
                    this.mVectorState.createCachedBitmapIfNeeded(min, min2);
                    if (!this.mAllowCaching) {
                        this.mVectorState.updateCachedBitmap(min, min2);
                    }
                    else if (!this.mVectorState.canReuseCache()) {
                        this.mVectorState.updateCachedBitmap(min, min2);
                        this.mVectorState.updateCacheStates();
                    }
                    this.mVectorState.drawCachedBitmapWithRootAlpha(canvas, (ColorFilter)o, this.mTmpBounds);
                    canvas.restoreToCount(save);
                }
            }
        }
    }
    
    public int getAlpha() {
        if (this.mDelegateDrawable != null) {
            return DrawableCompat.getAlpha(this.mDelegateDrawable);
        }
        return this.mVectorState.mVPathRenderer.getRootAlpha();
    }
    
    public int getChangingConfigurations() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getChangingConfigurations();
        }
        return super.getChangingConfigurations() | this.mVectorState.getChangingConfigurations();
    }
    
    public Drawable$ConstantState getConstantState() {
        if (this.mDelegateDrawable != null) {
            return new VectorDrawableCompat$VectorDrawableDelegateState(this.mDelegateDrawable.getConstantState());
        }
        this.mVectorState.mChangingConfigurations = this.getChangingConfigurations();
        return this.mVectorState;
    }
    
    public int getIntrinsicHeight() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getIntrinsicHeight();
        }
        return (int)this.mVectorState.mVPathRenderer.mBaseHeight;
    }
    
    public int getIntrinsicWidth() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getIntrinsicWidth();
        }
        return (int)this.mVectorState.mVPathRenderer.mBaseWidth;
    }
    
    public int getOpacity() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.getOpacity();
        }
        return -3;
    }
    
    Object getTargetByName(final String s) {
        return this.mVectorState.mVPathRenderer.mVGTargetsMap.get(s);
    }
    
    public void inflate(final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.inflate(resources, xmlPullParser, set);
            return;
        }
        this.inflate(resources, xmlPullParser, set, null);
    }
    
    public void inflate(final Resources resources, final XmlPullParser xmlPullParser, final AttributeSet set, final Resources$Theme resources$Theme) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.inflate(this.mDelegateDrawable, resources, xmlPullParser, set, resources$Theme);
            return;
        }
        final VectorDrawableCompat$VectorDrawableCompatState mVectorState = this.mVectorState;
        mVectorState.mVPathRenderer = new VectorDrawableCompat$VPathRenderer();
        final TypedArray obtainAttributes = VectorDrawableCommon.obtainAttributes(resources, resources$Theme, set, AndroidResources.styleable_VectorDrawableTypeArray);
        this.updateStateFromTypedArray(obtainAttributes, xmlPullParser);
        obtainAttributes.recycle();
        mVectorState.mChangingConfigurations = this.getChangingConfigurations();
        mVectorState.mCacheDirty = true;
        this.inflateInternal(resources, xmlPullParser, set, resources$Theme);
        this.mTintFilter = this.updateTintFilter(this.mTintFilter, mVectorState.mTint, mVectorState.mTintMode);
    }
    
    public void invalidateSelf() {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.invalidateSelf();
            return;
        }
        super.invalidateSelf();
    }
    
    public boolean isStateful() {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.isStateful();
        }
        return super.isStateful() || (this.mVectorState != null && this.mVectorState.mTint != null && this.mVectorState.mTint.isStateful());
    }
    
    public Drawable mutate() {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.mutate();
        }
        else if (!this.mMutated && super.mutate() == this) {
            this.mVectorState = new VectorDrawableCompat$VectorDrawableCompatState(this.mVectorState);
            this.mMutated = true;
            return this;
        }
        return this;
    }
    
    @Override
    protected void onBoundsChange(final Rect bounds) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setBounds(bounds);
        }
    }
    
    protected boolean onStateChange(final int[] state) {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setState(state);
        }
        final VectorDrawableCompat$VectorDrawableCompatState mVectorState = this.mVectorState;
        if (mVectorState.mTint != null && mVectorState.mTintMode != null) {
            this.mTintFilter = this.updateTintFilter(this.mTintFilter, mVectorState.mTint, mVectorState.mTintMode);
            this.invalidateSelf();
            return true;
        }
        return false;
    }
    
    public void scheduleSelf(final Runnable runnable, final long n) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.scheduleSelf(runnable, n);
            return;
        }
        super.scheduleSelf(runnable, n);
    }
    
    void setAllowCaching(final boolean mAllowCaching) {
        this.mAllowCaching = mAllowCaching;
    }
    
    public void setAlpha(final int n) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setAlpha(n);
        }
        else if (this.mVectorState.mVPathRenderer.getRootAlpha() != n) {
            this.mVectorState.mVPathRenderer.setRootAlpha(n);
            this.invalidateSelf();
        }
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.setColorFilter(colorFilter);
            return;
        }
        this.mColorFilter = colorFilter;
        this.invalidateSelf();
    }
    
    public void setTint(final int n) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTint(this.mDelegateDrawable, n);
            return;
        }
        this.setTintList(ColorStateList.valueOf(n));
    }
    
    public void setTintList(final ColorStateList mTint) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTintList(this.mDelegateDrawable, mTint);
        }
        else {
            final VectorDrawableCompat$VectorDrawableCompatState mVectorState = this.mVectorState;
            if (mVectorState.mTint != mTint) {
                mVectorState.mTint = mTint;
                this.mTintFilter = this.updateTintFilter(this.mTintFilter, mTint, mVectorState.mTintMode);
                this.invalidateSelf();
            }
        }
    }
    
    public void setTintMode(final PorterDuff$Mode mTintMode) {
        if (this.mDelegateDrawable != null) {
            DrawableCompat.setTintMode(this.mDelegateDrawable, mTintMode);
        }
        else {
            final VectorDrawableCompat$VectorDrawableCompatState mVectorState = this.mVectorState;
            if (mVectorState.mTintMode != mTintMode) {
                mVectorState.mTintMode = mTintMode;
                this.mTintFilter = this.updateTintFilter(this.mTintFilter, mVectorState.mTint, mTintMode);
                this.invalidateSelf();
            }
        }
    }
    
    public boolean setVisible(final boolean b, final boolean b2) {
        if (this.mDelegateDrawable != null) {
            return this.mDelegateDrawable.setVisible(b, b2);
        }
        return super.setVisible(b, b2);
    }
    
    public void unscheduleSelf(final Runnable runnable) {
        if (this.mDelegateDrawable != null) {
            this.mDelegateDrawable.unscheduleSelf(runnable);
            return;
        }
        super.unscheduleSelf(runnable);
    }
    
    PorterDuffColorFilter updateTintFilter(final PorterDuffColorFilter porterDuffColorFilter, final ColorStateList list, final PorterDuff$Mode porterDuff$Mode) {
        if (list == null || porterDuff$Mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(list.getColorForState(this.getState(), 0), porterDuff$Mode);
    }
}
