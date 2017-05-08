// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.text;

class TextDirectionHeuristicsCompat$AnyStrong implements TextDirectionHeuristicsCompat$TextDirectionAlgorithm
{
    public static final TextDirectionHeuristicsCompat$AnyStrong INSTANCE_LTR;
    public static final TextDirectionHeuristicsCompat$AnyStrong INSTANCE_RTL;
    private final boolean mLookForRtl;
    
    static {
        INSTANCE_RTL = new TextDirectionHeuristicsCompat$AnyStrong(true);
        INSTANCE_LTR = new TextDirectionHeuristicsCompat$AnyStrong(false);
    }
    
    private TextDirectionHeuristicsCompat$AnyStrong(final boolean mLookForRtl) {
        this.mLookForRtl = mLookForRtl;
    }
    
    @Override
    public int checkRtl(final CharSequence charSequence, final int n, final int n2) {
        final boolean b = true;
        int n3 = 0;
        int n4 = n;
        Label_0113: {
            boolean b2 = false;
        Label_0087:
            while (true) {
                final int n5 = n4;
                if (n5 >= n + n2) {
                    break Label_0113;
                }
                int n6 = 0;
                switch (TextDirectionHeuristicsCompat.isRtlText(Character.getDirectionality(charSequence.charAt(n5)))) {
                    default: {
                        n6 = n3;
                        break;
                    }
                    case 0: {
                        if (this.mLookForRtl) {
                            b2 = false;
                            break Label_0087;
                        }
                        n6 = 1;
                        break;
                    }
                    case 1: {
                        b2 = b;
                        if (this.mLookForRtl) {
                            n6 = 1;
                            break;
                        }
                        break Label_0087;
                    }
                }
                final int n7 = n5 + 1;
                n3 = n6;
                n4 = n7;
            }
            return b2 ? 1 : 0;
        }
        if (n3 == 0) {
            return 2;
        }
        boolean b2 = b;
        if (!this.mLookForRtl) {
            return 0;
        }
        return b2 ? 1 : 0;
    }
}
