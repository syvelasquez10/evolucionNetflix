// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

public final class al
{
    public static final class a extends aj
    {
        private String a;
        
        private a(final String a) {
            this.a = a;
        }
        
        @Override
        public final String a() {
            return "http://www.amazon.com/gp/mas/dl/android?p=" + this.a;
        }
        
        public static final class a implements ak
        {
        }
    }
    
    public static final class b extends aj
    {
        private String a;
        
        private b(final String a) {
            this.a = a;
        }
        
        @Override
        public final String a() {
            return "market://details?id=" + this.a;
        }
        
        public static final class a implements ak
        {
        }
    }
}
