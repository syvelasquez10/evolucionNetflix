// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import java.util.Iterator;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import android.content.ComponentName;
import android.app.Activity;
import android.os.Build$VERSION;
import android.content.Context;
import java.util.ArrayList;
import android.content.Intent;

public class TaskStackBuilder implements Iterable<Intent>
{
    private static final TaskStackBuilder$TaskStackBuilderImpl IMPL;
    private final ArrayList<Intent> mIntents;
    private final Context mSourceContext;
    
    static {
        if (Build$VERSION.SDK_INT >= 11) {
            IMPL = new TaskStackBuilder$TaskStackBuilderImplHoneycomb();
            return;
        }
        IMPL = new TaskStackBuilder$TaskStackBuilderImplBase();
    }
    
    private TaskStackBuilder(final Context mSourceContext) {
        this.mIntents = new ArrayList<Intent>();
        this.mSourceContext = mSourceContext;
    }
    
    public static TaskStackBuilder create(final Context context) {
        return new TaskStackBuilder(context);
    }
    
    public TaskStackBuilder addNextIntent(final Intent intent) {
        this.mIntents.add(intent);
        return this;
    }
    
    public TaskStackBuilder addParentStack(final Activity activity) {
        Intent supportParentActivityIntent = null;
        if (activity instanceof TaskStackBuilder$SupportParentable) {
            supportParentActivityIntent = ((TaskStackBuilder$SupportParentable)activity).getSupportParentActivityIntent();
        }
        Intent parentActivityIntent;
        if (supportParentActivityIntent == null) {
            parentActivityIntent = NavUtils.getParentActivityIntent(activity);
        }
        else {
            parentActivityIntent = supportParentActivityIntent;
        }
        if (parentActivityIntent != null) {
            ComponentName componentName;
            if ((componentName = parentActivityIntent.getComponent()) == null) {
                componentName = parentActivityIntent.resolveActivity(this.mSourceContext.getPackageManager());
            }
            this.addParentStack(componentName);
            this.addNextIntent(parentActivityIntent);
        }
        return this;
    }
    
    public TaskStackBuilder addParentStack(final ComponentName componentName) {
        final int size = this.mIntents.size();
        try {
            for (Intent intent = NavUtils.getParentActivityIntent(this.mSourceContext, componentName); intent != null; intent = NavUtils.getParentActivityIntent(this.mSourceContext, intent.getComponent())) {
                this.mIntents.add(size, intent);
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException((Throwable)ex);
        }
        return this;
    }
    
    @Override
    public Iterator<Intent> iterator() {
        return this.mIntents.iterator();
    }
    
    public void startActivities() {
        this.startActivities(null);
    }
    
    public void startActivities(final Bundle bundle) {
        if (this.mIntents.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        final Intent[] array = this.mIntents.toArray(new Intent[this.mIntents.size()]);
        array[0] = new Intent(array[0]).addFlags(268484608);
        if (!ContextCompat.startActivities(this.mSourceContext, array, bundle)) {
            final Intent intent = new Intent(array[array.length - 1]);
            intent.addFlags(268435456);
            this.mSourceContext.startActivity(intent);
        }
    }
}
