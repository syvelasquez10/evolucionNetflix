// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public abstract class dj implements Runnable
{
    public abstract void a();
    
    @Override
    public final void run() {
        try {
            this.a();
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
        }
    }
}
