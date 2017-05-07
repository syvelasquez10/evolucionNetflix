// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.view.MenuItem;
import android.view.KeyEvent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.content.Context;
import android.view.Menu;
import android.os.Parcelable;
import java.util.ArrayList;
import android.view.LayoutInflater$Factory;
import android.os.Bundle;
import android.content.res.Configuration;
import android.util.Log;
import android.content.Intent;
import android.os.Build$VERSION;
import java.io.FileDescriptor;
import android.content.res.Resources;
import android.content.res.Resources$NotFoundException;
import android.view.ViewGroup;
import java.io.PrintWriter;
import android.view.View;
import android.os.Message;
import android.os.Handler;
import android.support.v4.util.SimpleArrayMap;
import android.app.Activity;

public class FragmentActivity extends Activity
{
    static final String FRAGMENTS_TAG = "android:support:fragments";
    private static final int HONEYCOMB = 11;
    static final int MSG_REALLY_STOPPED = 1;
    static final int MSG_RESUME_PENDING = 2;
    private static final String TAG = "FragmentActivity";
    SimpleArrayMap<String, LoaderManagerImpl> mAllLoaderManagers;
    boolean mCheckedForLoaderManager;
    final FragmentContainer mContainer;
    boolean mCreated;
    final FragmentManagerImpl mFragments;
    final Handler mHandler;
    LoaderManagerImpl mLoaderManager;
    boolean mLoadersStarted;
    boolean mOptionsMenuInvalidated;
    boolean mReallyStopped;
    boolean mResumed;
    boolean mRetaining;
    boolean mStopped;
    
    public FragmentActivity() {
        this.mHandler = new Handler() {
            public void handleMessage(final Message message) {
                switch (message.what) {
                    default: {
                        super.handleMessage(message);
                        break;
                    }
                    case 1: {
                        if (FragmentActivity.this.mStopped) {
                            FragmentActivity.this.doReallyStop(false);
                            return;
                        }
                        break;
                    }
                    case 2: {
                        FragmentActivity.this.onResumeFragments();
                        FragmentActivity.this.mFragments.execPendingActions();
                    }
                }
            }
        };
        this.mFragments = new FragmentManagerImpl();
        this.mContainer = new FragmentContainer() {
            @Override
            public View findViewById(final int n) {
                return FragmentActivity.this.findViewById(n);
            }
        };
    }
    
    private void dumpViewHierarchy(String string, final PrintWriter printWriter, final View view) {
        printWriter.print(string);
        if (view == null) {
            printWriter.println("null");
        }
        else {
            printWriter.println(viewToString(view));
            if (view instanceof ViewGroup) {
                final ViewGroup viewGroup = (ViewGroup)view;
                final int childCount = viewGroup.getChildCount();
                if (childCount > 0) {
                    string += "  ";
                    for (int i = 0; i < childCount; ++i) {
                        this.dumpViewHierarchy(string, printWriter, viewGroup.getChildAt(i));
                    }
                }
            }
        }
    }
    
    private static String viewToString(final View view) {
        final char c = 'F';
        final char c2 = '.';
        final StringBuilder sb = new StringBuilder(128);
        sb.append(view.getClass().getName());
        sb.append('{');
        sb.append(Integer.toHexString(System.identityHashCode(view)));
        sb.append(' ');
        Resources resources;
        int id = 0;
        String resourcePackageName;
        String resourceTypeName;
        String resourceEntryName;
        char c3 = '\0';
        char c4 = '\0';
        char c5 = '\0';
        char c6 = '\0';
        char c7 = '\0';
        char c8 = '\0';
        char c9 = '\0';
        char c10 = '\0';
        char c11 = '\0';
        char c12;
        Label_0509_Outer:Label_0610_Outer:
        while (true) {
        Label_0616_Outer:
            while (true) {
                while (true) {
                    Label_0562_Outer:Label_0592_Outer:Label_0604_Outer:
                    while (true) {
                        Label_0261: {
                        Label_0598_Outer:
                            while (true) {
                                Label_0244: {
                                    while (true) {
                                        Label_0220: {
                                            Label_0580_Outer:Label_0586_Outer:
                                            while (true) {
                                                Label_0203: {
                                                    while (true) {
                                                        Label_0186: {
                                                            while (true) {
                                                                Label_0169: {
                                                                Label_0568_Outer:
                                                                    while (true) {
                                                                        Label_0152: {
                                                                            while (true) {
                                                                                Label_0135: {
                                                                                    while (true) {
                                                                                        Label_0118: {
                                                                                            while (true) {
                                                                                                while (true) {
                                                                                                    while (true) {
                                                                                                        switch (view.getVisibility()) {
                                                                                                            default: {
                                                                                                                sb.append('.');
                                                                                                                break;
                                                                                                            }
                                                                                                            case 0: {
                                                                                                                Label_0523: {
                                                                                                                    break Label_0523;
                                                                                                                    try {
                                                                                                                        resourcePackageName = resources.getResourcePackageName(id);
                                                                                                                        while (true) {
                                                                                                                            resourceTypeName = resources.getResourceTypeName(id);
                                                                                                                            resourceEntryName = resources.getResourceEntryName(id);
                                                                                                                            sb.append(" ");
                                                                                                                            sb.append(resourcePackageName);
                                                                                                                            sb.append(":");
                                                                                                                            sb.append(resourceTypeName);
                                                                                                                            sb.append("/");
                                                                                                                            sb.append(resourceEntryName);
                                                                                                                            sb.append("}");
                                                                                                                            return sb.toString();
                                                                                                                            c3 = '.';
                                                                                                                            break Label_0135;
                                                                                                                            c4 = '.';
                                                                                                                            break Label_0220;
                                                                                                                            sb.append('I');
                                                                                                                            break;
                                                                                                                            resourcePackageName = "app";
                                                                                                                            continue Label_0509_Outer;
                                                                                                                            sb.append('V');
                                                                                                                            break;
                                                                                                                            sb.append('G');
                                                                                                                            break;
                                                                                                                            c5 = '.';
                                                                                                                            break Label_0186;
                                                                                                                            c6 = '.';
                                                                                                                            break Label_0261;
                                                                                                                            resourcePackageName = "android";
                                                                                                                            continue Label_0509_Outer;
                                                                                                                        }
                                                                                                                        c7 = '.';
                                                                                                                        break Label_0169;
                                                                                                                        c8 = 'D';
                                                                                                                        break Label_0152;
                                                                                                                        c9 = '.';
                                                                                                                        break Label_0244;
                                                                                                                        c10 = '.';
                                                                                                                        break Label_0118;
                                                                                                                        c11 = '.';
                                                                                                                        break Label_0203;
                                                                                                                    }
                                                                                                                    catch (Resources$NotFoundException ex) {
                                                                                                                        continue Label_0562_Outer;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            }
                                                                                                            case 4: {
                                                                                                                continue Label_0610_Outer;
                                                                                                            }
                                                                                                            case 8: {
                                                                                                                continue Label_0580_Outer;
                                                                                                            }
                                                                                                        }
                                                                                                        break;
                                                                                                    }
                                                                                                    break;
                                                                                                }
                                                                                                if (!view.isFocusable()) {
                                                                                                    continue Label_0586_Outer;
                                                                                                }
                                                                                                break;
                                                                                            }
                                                                                            c10 = 'F';
                                                                                        }
                                                                                        sb.append(c10);
                                                                                        if (!view.isEnabled()) {
                                                                                            continue Label_0592_Outer;
                                                                                        }
                                                                                        break;
                                                                                    }
                                                                                    c3 = 'E';
                                                                                }
                                                                                sb.append(c3);
                                                                                if (!view.willNotDraw()) {
                                                                                    continue Label_0598_Outer;
                                                                                }
                                                                                break;
                                                                            }
                                                                            c8 = '.';
                                                                        }
                                                                        sb.append(c8);
                                                                        if (!view.isHorizontalScrollBarEnabled()) {
                                                                            continue Label_0568_Outer;
                                                                        }
                                                                        break;
                                                                    }
                                                                    c7 = 'H';
                                                                }
                                                                sb.append(c7);
                                                                if (!view.isVerticalScrollBarEnabled()) {
                                                                    continue Label_0604_Outer;
                                                                }
                                                                break;
                                                            }
                                                            c5 = 'V';
                                                        }
                                                        sb.append(c5);
                                                        if (!view.isClickable()) {
                                                            continue;
                                                        }
                                                        break;
                                                    }
                                                    c11 = 'C';
                                                }
                                                sb.append(c11);
                                                if (!view.isLongClickable()) {
                                                    continue Label_0610_Outer;
                                                }
                                                break;
                                            }
                                            c4 = 'L';
                                        }
                                        sb.append(c4);
                                        sb.append(' ');
                                        if (!view.isFocused()) {
                                            continue;
                                        }
                                        break;
                                    }
                                    c9 = c;
                                }
                                sb.append(c9);
                                if (!view.isSelected()) {
                                    continue Label_0616_Outer;
                                }
                                break;
                            }
                            c6 = 'S';
                        }
                        sb.append(c6);
                        c12 = c2;
                        if (view.isPressed()) {
                            c12 = 'P';
                        }
                        sb.append(c12);
                        sb.append(' ');
                        sb.append(view.getLeft());
                        sb.append(',');
                        sb.append(view.getTop());
                        sb.append('-');
                        sb.append(view.getRight());
                        sb.append(',');
                        sb.append(view.getBottom());
                        id = view.getId();
                        if (id == -1) {
                            continue Label_0562_Outer;
                        }
                        sb.append(" #");
                        sb.append(Integer.toHexString(id));
                        resources = view.getResources();
                        if (id == 0 || resources == null) {
                            continue Label_0562_Outer;
                        }
                        break;
                    }
                    switch (0xFF000000 & id) {
                        default: {
                            continue Label_0509_Outer;
                        }
                        case 2130706432: {
                            continue Label_0616_Outer;
                        }
                        case 16777216: {
                            continue;
                        }
                    }
                    break;
                }
                break;
            }
            break;
        }
    }
    
    void doReallyStop(final boolean mRetaining) {
        if (!this.mReallyStopped) {
            this.mReallyStopped = true;
            this.mRetaining = mRetaining;
            this.mHandler.removeMessages(1);
            this.onReallyStop();
        }
    }
    
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        if (Build$VERSION.SDK_INT >= 11) {}
        printWriter.print(s);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        final String string = s + "  ";
        printWriter.print(string);
        printWriter.print("mCreated=");
        printWriter.print(this.mCreated);
        printWriter.print("mResumed=");
        printWriter.print(this.mResumed);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        printWriter.print(" mReallyStopped=");
        printWriter.println(this.mReallyStopped);
        printWriter.print(string);
        printWriter.print("mLoadersStarted=");
        printWriter.println(this.mLoadersStarted);
        if (this.mLoaderManager != null) {
            printWriter.print(s);
            printWriter.print("Loader Manager ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this.mLoaderManager)));
            printWriter.println(":");
            this.mLoaderManager.dump(s + "  ", fileDescriptor, printWriter, array);
        }
        this.mFragments.dump(s, fileDescriptor, printWriter, array);
        printWriter.print(s);
        printWriter.println("View Hierarchy:");
        this.dumpViewHierarchy(s + "  ", printWriter, this.getWindow().getDecorView());
    }
    
    public Object getLastCustomNonConfigurationInstance() {
        final NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances)this.getLastNonConfigurationInstance();
        if (nonConfigurationInstances != null) {
            return nonConfigurationInstances.custom;
        }
        return null;
    }
    
    LoaderManagerImpl getLoaderManager(final String s, final boolean b, final boolean b2) {
        if (this.mAllLoaderManagers == null) {
            this.mAllLoaderManagers = new SimpleArrayMap<String, LoaderManagerImpl>();
        }
        LoaderManagerImpl loaderManagerImpl = this.mAllLoaderManagers.get(s);
        if (loaderManagerImpl == null) {
            if (b2) {
                loaderManagerImpl = new LoaderManagerImpl(s, this, b);
                this.mAllLoaderManagers.put(s, loaderManagerImpl);
            }
            return loaderManagerImpl;
        }
        loaderManagerImpl.updateActivity(this);
        return loaderManagerImpl;
    }
    
    public FragmentManager getSupportFragmentManager() {
        return this.mFragments;
    }
    
    public LoaderManager getSupportLoaderManager() {
        if (this.mLoaderManager != null) {
            return this.mLoaderManager;
        }
        this.mCheckedForLoaderManager = true;
        return this.mLoaderManager = this.getLoaderManager("(root)", this.mLoadersStarted, true);
    }
    
    void invalidateSupportFragment(final String s) {
        if (this.mAllLoaderManagers != null) {
            final LoaderManagerImpl loaderManagerImpl = this.mAllLoaderManagers.get(s);
            if (loaderManagerImpl != null && !loaderManagerImpl.mRetaining) {
                loaderManagerImpl.doDestroy();
                this.mAllLoaderManagers.remove(s);
            }
        }
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        this.mFragments.noteStateNotSaved();
        final int n3 = n >> 16;
        if (n3 == 0) {
            super.onActivityResult(n, n2, intent);
            return;
        }
        final int n4 = n3 - 1;
        if (this.mFragments.mActive == null || n4 < 0 || n4 >= this.mFragments.mActive.size()) {
            Log.w("FragmentActivity", "Activity result fragment index out of range: 0x" + Integer.toHexString(n));
            return;
        }
        final Fragment fragment = this.mFragments.mActive.get(n4);
        if (fragment == null) {
            Log.w("FragmentActivity", "Activity result no fragment exists for index: 0x" + Integer.toHexString(n));
            return;
        }
        fragment.onActivityResult(0xFFFF & n, n2, intent);
    }
    
    public void onAttachFragment(final Fragment fragment) {
    }
    
    public void onBackPressed() {
        if (!this.mFragments.popBackStackImmediate()) {
            this.finish();
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mFragments.dispatchConfigurationChanged(configuration);
    }
    
    protected void onCreate(final Bundle bundle) {
        final ArrayList<Fragment> list = null;
        this.mFragments.attachActivity(this, this.mContainer, null);
        if (this.getLayoutInflater().getFactory() == null) {
            this.getLayoutInflater().setFactory((LayoutInflater$Factory)this);
        }
        super.onCreate(bundle);
        final NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances)this.getLastNonConfigurationInstance();
        if (nonConfigurationInstances != null) {
            this.mAllLoaderManagers = nonConfigurationInstances.loaders;
        }
        if (bundle != null) {
            final Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            final FragmentManagerImpl mFragments = this.mFragments;
            ArrayList<Fragment> fragments = list;
            if (nonConfigurationInstances != null) {
                fragments = nonConfigurationInstances.fragments;
            }
            mFragments.restoreAllState(parcelable, fragments);
        }
        this.mFragments.dispatchCreate();
    }
    
    public boolean onCreatePanelMenu(final int n, final Menu menu) {
        if (n == 0) {
            final boolean onCreatePanelMenu = super.onCreatePanelMenu(n, menu);
            final boolean dispatchCreateOptionsMenu = this.mFragments.dispatchCreateOptionsMenu(menu, this.getMenuInflater());
            return Build$VERSION.SDK_INT < 11 || (onCreatePanelMenu | dispatchCreateOptionsMenu);
        }
        return super.onCreatePanelMenu(n, menu);
    }
    
    public View onCreateView(final String s, final Context context, final AttributeSet set) {
        final Fragment fragment = null;
        if (!"fragment".equals(s)) {
            return super.onCreateView(s, context, set);
        }
        final String attributeValue = set.getAttributeValue((String)null, "class");
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, FragmentTag.Fragment);
        String string;
        if ((string = attributeValue) == null) {
            string = obtainStyledAttributes.getString(0);
        }
        final int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        final String string2 = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (!Fragment.isSupportFragmentClass((Context)this, string)) {
            return super.onCreateView(s, context, set);
        }
        if (false) {
            throw new NullPointerException();
        }
        if (-1 == 0 && resourceId == -1 && string2 == null) {
            throw new IllegalArgumentException(set.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + string);
        }
        Fragment fragmentById = fragment;
        if (resourceId != -1) {
            fragmentById = this.mFragments.findFragmentById(resourceId);
        }
        Fragment fragmentByTag;
        if ((fragmentByTag = fragmentById) == null) {
            fragmentByTag = fragmentById;
            if (string2 != null) {
                fragmentByTag = this.mFragments.findFragmentByTag(string2);
            }
        }
        Fragment fragment2;
        if ((fragment2 = fragmentByTag) == null) {
            fragment2 = fragmentByTag;
            if (-1 != 0) {
                fragment2 = this.mFragments.findFragmentById(0);
            }
        }
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentActivity", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + string + " existing=" + fragment2);
        }
        if (fragment2 == null) {
            fragment2 = Fragment.instantiate((Context)this, string);
            fragment2.mFromLayout = true;
            int mFragmentId;
            if (resourceId != 0) {
                mFragmentId = resourceId;
            }
            else {
                mFragmentId = 0;
            }
            fragment2.mFragmentId = mFragmentId;
            fragment2.mContainerId = 0;
            fragment2.mTag = string2;
            fragment2.mInLayout = true;
            fragment2.mFragmentManager = this.mFragments;
            fragment2.onInflate(this, set, fragment2.mSavedFragmentState);
            this.mFragments.addFragment(fragment2, true);
        }
        else {
            if (fragment2.mInLayout) {
                throw new IllegalArgumentException(set.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string2 + ", or parent id 0x" + Integer.toHexString(0) + " with another fragment for " + string);
            }
            fragment2.mInLayout = true;
            if (!fragment2.mRetaining) {
                fragment2.onInflate(this, set, fragment2.mSavedFragmentState);
            }
            this.mFragments.moveToState(fragment2);
        }
        if (fragment2.mView == null) {
            throw new IllegalStateException("Fragment " + string + " did not create a view.");
        }
        if (resourceId != 0) {
            fragment2.mView.setId(resourceId);
        }
        if (fragment2.mView.getTag() == null) {
            fragment2.mView.setTag((Object)string2);
        }
        return fragment2.mView;
    }
    
    protected void onDestroy() {
        super.onDestroy();
        this.doReallyStop(false);
        this.mFragments.dispatchDestroy();
        if (this.mLoaderManager != null) {
            this.mLoaderManager.doDestroy();
        }
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (Build$VERSION.SDK_INT < 5 && n == 4 && keyEvent.getRepeatCount() == 0) {
            this.onBackPressed();
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public void onLowMemory() {
        super.onLowMemory();
        this.mFragments.dispatchLowMemory();
    }
    
    public boolean onMenuItemSelected(final int n, final MenuItem menuItem) {
        if (super.onMenuItemSelected(n, menuItem)) {
            return true;
        }
        switch (n) {
            default: {
                return false;
            }
            case 0: {
                return this.mFragments.dispatchOptionsItemSelected(menuItem);
            }
            case 6: {
                return this.mFragments.dispatchContextItemSelected(menuItem);
            }
        }
    }
    
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        this.mFragments.noteStateNotSaved();
    }
    
    public void onPanelClosed(final int n, final Menu menu) {
        switch (n) {
            case 0: {
                this.mFragments.dispatchOptionsMenuClosed(menu);
                break;
            }
        }
        super.onPanelClosed(n, menu);
    }
    
    protected void onPause() {
        super.onPause();
        this.mResumed = false;
        if (this.mHandler.hasMessages(2)) {
            this.mHandler.removeMessages(2);
            this.onResumeFragments();
        }
        this.mFragments.dispatchPause();
    }
    
    protected void onPostResume() {
        super.onPostResume();
        this.mHandler.removeMessages(2);
        this.onResumeFragments();
        this.mFragments.execPendingActions();
    }
    
    protected boolean onPrepareOptionsPanel(final View view, final Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }
    
    public boolean onPreparePanel(final int n, final View view, final Menu menu) {
        if (n == 0 && menu != null) {
            if (this.mOptionsMenuInvalidated) {
                this.mOptionsMenuInvalidated = false;
                menu.clear();
                this.onCreatePanelMenu(n, menu);
            }
            return this.onPrepareOptionsPanel(view, menu) | this.mFragments.dispatchPrepareOptionsMenu(menu);
        }
        return super.onPreparePanel(n, view, menu);
    }
    
    void onReallyStop() {
        if (this.mLoadersStarted) {
            this.mLoadersStarted = false;
            if (this.mLoaderManager != null) {
                if (!this.mRetaining) {
                    this.mLoaderManager.doStop();
                }
                else {
                    this.mLoaderManager.doRetain();
                }
            }
        }
        this.mFragments.dispatchReallyStop();
    }
    
    protected void onResume() {
        super.onResume();
        this.mHandler.sendEmptyMessage(2);
        this.mResumed = true;
        this.mFragments.execPendingActions();
    }
    
    protected void onResumeFragments() {
        this.mFragments.dispatchResume();
    }
    
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }
    
    public final Object onRetainNonConfigurationInstance() {
        if (this.mStopped) {
            this.doReallyStop(true);
        }
        final Object onRetainCustomNonConfigurationInstance = this.onRetainCustomNonConfigurationInstance();
        final ArrayList<Fragment> retainNonConfig = this.mFragments.retainNonConfig();
        int n = 0;
        final boolean b = false;
        if (this.mAllLoaderManagers != null) {
            final int size = this.mAllLoaderManagers.size();
            final LoaderManagerImpl[] array = new LoaderManagerImpl[size];
            for (int i = size - 1; i >= 0; --i) {
                array[i] = this.mAllLoaderManagers.valueAt(i);
            }
            final int n2 = 0;
            int n3 = b ? 1 : 0;
            int n4 = n2;
            while (true) {
                n = n3;
                if (n4 >= size) {
                    break;
                }
                final LoaderManagerImpl loaderManagerImpl = array[n4];
                if (loaderManagerImpl.mRetaining) {
                    n3 = 1;
                }
                else {
                    loaderManagerImpl.doDestroy();
                    this.mAllLoaderManagers.remove(loaderManagerImpl.mWho);
                }
                ++n4;
            }
        }
        if (retainNonConfig == null && n == 0 && onRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        final NonConfigurationInstances nonConfigurationInstances = new NonConfigurationInstances();
        nonConfigurationInstances.activity = null;
        nonConfigurationInstances.custom = onRetainCustomNonConfigurationInstance;
        nonConfigurationInstances.children = null;
        nonConfigurationInstances.fragments = retainNonConfig;
        nonConfigurationInstances.loaders = this.mAllLoaderManagers;
        return nonConfigurationInstances;
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        final Parcelable saveAllState = this.mFragments.saveAllState();
        if (saveAllState != null) {
            bundle.putParcelable("android:support:fragments", saveAllState);
        }
    }
    
    protected void onStart() {
        super.onStart();
        this.mStopped = false;
        this.mReallyStopped = false;
        this.mHandler.removeMessages(1);
        if (!this.mCreated) {
            this.mCreated = true;
            this.mFragments.dispatchActivityCreated();
        }
        this.mFragments.noteStateNotSaved();
        this.mFragments.execPendingActions();
        if (!this.mLoadersStarted) {
            this.mLoadersStarted = true;
            if (this.mLoaderManager != null) {
                this.mLoaderManager.doStart();
            }
            else if (!this.mCheckedForLoaderManager) {
                this.mLoaderManager = this.getLoaderManager("(root)", this.mLoadersStarted, false);
                if (this.mLoaderManager != null && !this.mLoaderManager.mStarted) {
                    this.mLoaderManager.doStart();
                }
            }
            this.mCheckedForLoaderManager = true;
        }
        this.mFragments.dispatchStart();
        if (this.mAllLoaderManagers != null) {
            final int size = this.mAllLoaderManagers.size();
            final LoaderManagerImpl[] array = new LoaderManagerImpl[size];
            for (int i = size - 1; i >= 0; --i) {
                array[i] = this.mAllLoaderManagers.valueAt(i);
            }
            for (int j = 0; j < size; ++j) {
                final LoaderManagerImpl loaderManagerImpl = array[j];
                loaderManagerImpl.finishRetain();
                loaderManagerImpl.doReportStart();
            }
        }
    }
    
    protected void onStop() {
        super.onStop();
        this.mStopped = true;
        this.mHandler.sendEmptyMessage(1);
        this.mFragments.dispatchStop();
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        if (n != -1 && (0xFFFF0000 & n) != 0x0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
        super.startActivityForResult(intent, n);
    }
    
    public void startActivityFromFragment(final Fragment fragment, final Intent intent, final int n) {
        if (n == -1) {
            super.startActivityForResult(intent, -1);
            return;
        }
        if ((0xFFFF0000 & n) != 0x0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
        super.startActivityForResult(intent, (fragment.mIndex + 1 << 16) + (0xFFFF & n));
    }
    
    public void supportInvalidateOptionsMenu() {
        if (Build$VERSION.SDK_INT >= 11) {
            ActivityCompatHoneycomb.invalidateOptionsMenu(this);
            return;
        }
        this.mOptionsMenuInvalidated = true;
    }
    
    static class FragmentTag
    {
        public static final int[] Fragment;
        public static final int Fragment_id = 1;
        public static final int Fragment_name = 0;
        public static final int Fragment_tag = 2;
        
        static {
            Fragment = new int[] { 16842755, 16842960, 16842961 };
        }
    }
    
    static final class NonConfigurationInstances
    {
        Object activity;
        SimpleArrayMap<String, Object> children;
        Object custom;
        ArrayList<Fragment> fragments;
        SimpleArrayMap<String, LoaderManagerImpl> loaders;
    }
}
