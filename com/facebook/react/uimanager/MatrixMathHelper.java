// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import java.lang.reflect.Array;
import com.facebook.infer.annotation.Assertions;

public class MatrixMathHelper
{
    public static void applyPerspective(final double[] array, final double n) {
        array[11] = -1.0 / n;
    }
    
    public static void applyRotateX(final double[] array, final double n) {
        array[5] = Math.cos(n);
        array[6] = Math.sin(n);
        array[9] = -Math.sin(n);
        array[10] = Math.cos(n);
    }
    
    public static void applyRotateY(final double[] array, final double n) {
        array[0] = Math.cos(n);
        array[2] = -Math.sin(n);
        array[8] = Math.sin(n);
        array[10] = Math.cos(n);
    }
    
    public static void applyRotateZ(final double[] array, final double n) {
        array[0] = Math.cos(n);
        array[1] = Math.sin(n);
        array[4] = -Math.sin(n);
        array[5] = Math.cos(n);
    }
    
    public static void applyScaleX(final double[] array, final double n) {
        array[0] = n;
    }
    
    public static void applyScaleY(final double[] array, final double n) {
        array[5] = n;
    }
    
    public static void applySkewX(final double[] array, final double n) {
        array[4] = Math.tan(n);
    }
    
    public static void applySkewY(final double[] array, final double n) {
        array[1] = Math.tan(n);
    }
    
    public static void applyTranslate2D(final double[] array, final double n, final double n2) {
        array[12] = n;
        array[13] = n2;
    }
    
    public static void applyTranslate3D(final double[] array, final double n, final double n2, final double n3) {
        array[12] = n;
        array[13] = n2;
        array[14] = n3;
    }
    
    public static void decomposeMatrix(double[] transpose, final MatrixMathHelper$MatrixDecompositionContext matrixMathHelper$MatrixDecompositionContext) {
        Assertions.assertCondition(transpose.length == 16);
        final double[] perspective = matrixMathHelper$MatrixDecompositionContext.perspective;
        final double[] quaternion = matrixMathHelper$MatrixDecompositionContext.quaternion;
        final double[] scale = matrixMathHelper$MatrixDecompositionContext.scale;
        final double[] skew = matrixMathHelper$MatrixDecompositionContext.skew;
        final double[] translation = matrixMathHelper$MatrixDecompositionContext.translation;
        final double[] rotationDegrees = matrixMathHelper$MatrixDecompositionContext.rotationDegrees;
        if (!isZero(transpose[15])) {
            final double[][] array = (double[][])Array.newInstance(Double.TYPE, 4, 4);
            final double[] array2 = new double[16];
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    double n = transpose[i * 4 + j] / transpose[15];
                    array[i][j] = n;
                    if (j == 3) {
                        n = 0.0;
                    }
                    array2[i * 4 + j] = n;
                }
            }
            array2[15] = 1.0;
            if (!isZero(determinant(array2))) {
                if (!isZero(array[0][3]) || !isZero(array[1][3]) || !isZero(array[2][3])) {
                    final double n2 = array[0][3];
                    final double n3 = array[1][3];
                    final double n4 = array[2][3];
                    final double n5 = array[3][3];
                    transpose = transpose(inverse(array2));
                    multiplyVectorByMatrix(new double[] { n2, n3, n4, n5 }, transpose, perspective);
                }
                else {
                    perspective[2] = 0.0;
                    perspective[0] = (perspective[1] = 0.0);
                    perspective[3] = 1.0;
                }
                for (int k = 0; k < 3; ++k) {
                    translation[k] = array[3][k];
                }
                final double[][] array3 = (double[][])Array.newInstance(Double.TYPE, 3, 3);
                for (int l = 0; l < 3; ++l) {
                    array3[l][0] = array[l][0];
                    array3[l][1] = array[l][1];
                    array3[l][2] = array[l][2];
                }
                scale[0] = v3Length(array3[0]);
                array3[0] = v3Normalize(array3[0], scale[0]);
                skew[0] = v3Dot(array3[0], array3[1]);
                array3[1] = v3Combine(array3[1], array3[0], 1.0, -skew[0]);
                skew[0] = v3Dot(array3[0], array3[1]);
                array3[1] = v3Combine(array3[1], array3[0], 1.0, -skew[0]);
                scale[1] = v3Length(array3[1]);
                array3[1] = v3Normalize(array3[1], scale[1]);
                skew[0] /= scale[1];
                skew[1] = v3Dot(array3[0], array3[2]);
                array3[2] = v3Combine(array3[2], array3[0], 1.0, -skew[1]);
                skew[2] = v3Dot(array3[1], array3[2]);
                array3[2] = v3Combine(array3[2], array3[1], 1.0, -skew[2]);
                scale[2] = v3Length(array3[2]);
                array3[2] = v3Normalize(array3[2], scale[2]);
                skew[1] /= scale[2];
                skew[2] /= scale[2];
                if (v3Dot(array3[0], v3Cross(array3[1], array3[2])) < 0.0) {
                    for (int n6 = 0; n6 < 3; ++n6) {
                        scale[n6] *= -1.0;
                        final double[] array4 = array3[n6];
                        array4[0] *= -1.0;
                        final double[] array5 = array3[n6];
                        array5[1] *= -1.0;
                        final double[] array6 = array3[n6];
                        array6[2] *= -1.0;
                    }
                }
                quaternion[0] = 0.5 * Math.sqrt(Math.max(1.0 + array3[0][0] - array3[1][1] - array3[2][2], 0.0));
                quaternion[1] = 0.5 * Math.sqrt(Math.max(1.0 - array3[0][0] + array3[1][1] - array3[2][2], 0.0));
                quaternion[2] = 0.5 * Math.sqrt(Math.max(1.0 - array3[0][0] - array3[1][1] + array3[2][2], 0.0));
                quaternion[3] = 0.5 * Math.sqrt(Math.max(1.0 + array3[0][0] + array3[1][1] + array3[2][2], 0.0));
                if (array3[2][1] > array3[1][2]) {
                    quaternion[0] = -quaternion[0];
                }
                if (array3[0][2] > array3[2][0]) {
                    quaternion[1] = -quaternion[1];
                }
                if (array3[1][0] > array3[0][1]) {
                    quaternion[2] = -quaternion[2];
                }
                if (quaternion[0] < 0.001 && quaternion[0] >= 0.0 && quaternion[1] < 0.001 && quaternion[1] >= 0.0) {
                    rotationDegrees[0] = (rotationDegrees[1] = 0.0);
                    rotationDegrees[2] = roundTo3Places(Math.atan2(array3[0][1], array3[0][0]) * 180.0 / 3.141592653589793);
                    return;
                }
                quaternionToDegreesXYZ(quaternion, rotationDegrees);
            }
        }
    }
    
    public static double degreesToRadians(final double n) {
        return 3.141592653589793 * n / 180.0;
    }
    
    public static double determinant(final double[] array) {
        final double n = array[0];
        final double n2 = array[1];
        final double n3 = array[2];
        final double n4 = array[3];
        final double n5 = array[4];
        final double n6 = array[5];
        final double n7 = array[6];
        final double n8 = array[7];
        final double n9 = array[8];
        final double n10 = array[9];
        final double n11 = array[10];
        final double n12 = array[11];
        final double n13 = array[12];
        final double n14 = array[13];
        final double n15 = array[14];
        final double n16 = array[15];
        return n * n6 * n11 * n16 + (n3 * n5 * n10 * n16 + (n4 * n7 * n10 * n13 - n3 * n8 * n10 * n13 - n4 * n6 * n11 * n13 + n2 * n8 * n11 * n13 + n3 * n6 * n12 * n13 - n13 * (n2 * n7 * n12) - n4 * n7 * n9 * n14 + n3 * n8 * n9 * n14 + n4 * n5 * n11 * n14 - n * n8 * n11 * n14 - n3 * n5 * n12 * n14 + n14 * (n * n7 * n12) + n4 * n6 * n9 * n15 - n2 * n8 * n9 * n15 - n4 * n5 * n10 * n15 + n8 * n * n10 * n15 + n2 * n5 * n12 * n15 - n * n6 * n12 * n15 - n3 * n6 * n9 * n16 + n2 * n7 * n9 * n16) - n * n7 * n10 * n16 - n2 * n5 * n11 * n16);
    }
    
    public static double[] inverse(final double[] array) {
        final double determinant = determinant(array);
        if (isZero(determinant)) {
            return array;
        }
        final double n = array[0];
        final double n2 = array[1];
        final double n3 = array[2];
        final double n4 = array[3];
        final double n5 = array[4];
        final double n6 = array[5];
        final double n7 = array[6];
        final double n8 = array[7];
        final double n9 = array[8];
        final double n10 = array[9];
        final double n11 = array[10];
        final double n12 = array[11];
        final double n13 = array[12];
        final double n14 = array[13];
        final double n15 = array[14];
        final double n16 = array[15];
        return new double[] { (n7 * n12 * n14 - n8 * n11 * n14 + n8 * n10 * n15 - n6 * n12 * n15 - n7 * n10 * n16 + n6 * n11 * n16) / determinant, (n4 * n11 * n14 - n3 * n12 * n14 - n4 * n10 * n15 + n2 * n12 * n15 + n3 * n10 * n16 - n2 * n11 * n16) / determinant, (n3 * n8 * n14 - n4 * n7 * n14 + n4 * n6 * n15 - n2 * n8 * n15 - n3 * n6 * n16 + n2 * n7 * n16) / determinant, (n4 * n7 * n10 - n3 * n8 * n10 - n4 * n6 * n11 + n2 * n8 * n11 + n3 * n6 * n12 - n2 * n7 * n12) / determinant, (n8 * n11 * n13 - n7 * n12 * n13 - n8 * n9 * n15 + n5 * n12 * n15 + n7 * n9 * n16 - n5 * n11 * n16) / determinant, (n3 * n12 * n13 - n4 * n11 * n13 + n4 * n9 * n15 - n * n12 * n15 - n3 * n9 * n16 + n * n11 * n16) / determinant, (n4 * n7 * n13 - n3 * n8 * n13 - n4 * n5 * n15 + n * n8 * n15 + n3 * n5 * n16 - n * n7 * n16) / determinant, (n3 * n8 * n9 - n4 * n7 * n9 + n4 * n5 * n11 - n * n8 * n11 - n3 * n5 * n12 + n * n7 * n12) / determinant, (n6 * n12 * n13 - n8 * n10 * n13 + n8 * n9 * n14 - n5 * n12 * n14 - n6 * n9 * n16 + n5 * n10 * n16) / determinant, (n4 * n10 * n13 - n2 * n12 * n13 - n4 * n9 * n14 + n * n12 * n14 + n2 * n9 * n16 - n * n10 * n16) / determinant, (n16 * (n * n6) + (n2 * n8 * n13 - n4 * n6 * n13 + n4 * n5 * n14 - n * n8 * n14 - n2 * n5 * n16)) / determinant, (n4 * n6 * n9 - n2 * n8 * n9 - n4 * n5 * n10 + n8 * n * n10 + n2 * n5 * n12 - n * n6 * n12) / determinant, (n7 * n10 * n13 - n6 * n11 * n13 - n7 * n9 * n14 + n5 * n11 * n14 + n6 * n9 * n15 - n5 * n10 * n15) / determinant, (n2 * n11 * n13 - n3 * n10 * n13 + n3 * n9 * n14 - n * n11 * n14 - n2 * n9 * n15 + n * n10 * n15) / determinant, (n3 * n6 * n13 - n2 * n7 * n13 - n3 * n5 * n14 + n * n7 * n14 + n2 * n5 * n15 - n * n6 * n15) / determinant, (n * n6 * n11 + (n3 * n5 * n10 + (n2 * n7 * n9 - n9 * (n3 * n6)) - n7 * n * n10 - n2 * n5 * n11)) / determinant };
    }
    
    private static boolean isZero(final double n) {
        return !Double.isNaN(n) && Math.abs(n) < 1.0E-5;
    }
    
    public static void multiplyInto(final double[] array, final double[] array2, final double[] array3) {
        final double n = array2[0];
        final double n2 = array2[1];
        final double n3 = array2[2];
        final double n4 = array2[3];
        final double n5 = array2[4];
        final double n6 = array2[5];
        final double n7 = array2[6];
        final double n8 = array2[7];
        final double n9 = array2[8];
        final double n10 = array2[9];
        final double n11 = array2[10];
        final double n12 = array2[11];
        final double n13 = array2[12];
        final double n14 = array2[13];
        final double n15 = array2[14];
        final double n16 = array2[15];
        final double n17 = array3[0];
        final double n18 = array3[1];
        final double n19 = array3[2];
        final double n20 = array3[3];
        array[0] = n17 * n + n18 * n5 + n19 * n9 + n20 * n13;
        array[1] = n17 * n2 + n18 * n6 + n19 * n10 + n20 * n14;
        array[2] = n17 * n3 + n18 * n7 + n19 * n11 + n20 * n15;
        array[3] = n17 * n4 + n18 * n8 + n19 * n12 + n20 * n16;
        final double n21 = array3[4];
        final double n22 = array3[5];
        final double n23 = array3[6];
        final double n24 = array3[7];
        array[4] = n21 * n + n22 * n5 + n23 * n9 + n24 * n13;
        array[5] = n21 * n2 + n22 * n6 + n23 * n10 + n24 * n14;
        array[6] = n21 * n3 + n22 * n7 + n23 * n11 + n24 * n15;
        array[7] = n21 * n4 + n22 * n8 + n23 * n12 + n24 * n16;
        final double n25 = array3[8];
        final double n26 = array3[9];
        final double n27 = array3[10];
        final double n28 = array3[11];
        array[8] = n25 * n + n26 * n5 + n27 * n9 + n28 * n13;
        array[9] = n25 * n2 + n26 * n6 + n27 * n10 + n28 * n14;
        array[10] = n25 * n3 + n26 * n7 + n27 * n11 + n28 * n15;
        array[11] = n25 * n4 + n26 * n8 + n27 * n12 + n28 * n16;
        final double n29 = array3[12];
        final double n30 = array3[13];
        final double n31 = array3[14];
        final double n32 = array3[15];
        array[12] = n * n29 + n5 * n30 + n31 * n9 + n32 * n13;
        array[13] = n2 * n29 + n30 * n6 + n31 * n10 + n32 * n14;
        array[14] = n29 * n3 + n30 * n7 + n31 * n11 + n32 * n15;
        array[15] = n29 * n4 + n30 * n8 + n31 * n12 + n32 * n16;
    }
    
    public static void multiplyVectorByMatrix(final double[] array, final double[] array2, final double[] array3) {
        final double n = array[0];
        final double n2 = array[1];
        final double n3 = array[2];
        final double n4 = array[3];
        array3[0] = array2[0] * n + array2[4] * n2 + array2[8] * n3 + array2[12] * n4;
        array3[1] = array2[1] * n + array2[5] * n2 + array2[9] * n3 + array2[13] * n4;
        array3[2] = array2[2] * n + array2[6] * n2 + array2[10] * n3 + array2[14] * n4;
        array3[3] = n * array2[3] + n2 * array2[7] + array2[11] * n3 + array2[15] * n4;
    }
    
    public static void quaternionToDegreesXYZ(final double[] array, final double[] array2) {
        final double n = array[0];
        final double n2 = array[1];
        final double n3 = array[2];
        final double n4 = array[3];
        final double n5 = n * n;
        final double n6 = n2 * n2;
        final double n7 = n3 * n3;
        final double n8 = n * n2 + n3 * n4;
        final double n9 = n4 * n4 + n5 + n6 + n7;
        if (n8 > 0.49999 * n9) {
            array2[0] = 0.0;
            array2[1] = Math.atan2(n, n4) * 2.0 * 57.29577951308232;
            array2[2] = 90.0;
            return;
        }
        if (n8 < n9 * -0.49999) {
            array2[0] = 0.0;
            array2[1] = Math.atan2(n, n4) * -2.0 * 57.29577951308232;
            array2[2] = -90.0;
            return;
        }
        array2[0] = roundTo3Places(Math.atan2(2.0 * n * n4 - 2.0 * n2 * n3, 1.0 - n5 * 2.0 - 2.0 * n7) * 57.29577951308232);
        array2[1] = roundTo3Places(Math.atan2(2.0 * n2 * n4 - 2.0 * n * n3, 1.0 - n6 * 2.0 - n7 * 2.0) * 57.29577951308232);
        array2[2] = roundTo3Places(Math.asin(n * 2.0 * n2 + 2.0 * n3 * n4) * 57.29577951308232);
    }
    
    public static void resetIdentityMatrix(final double[] array) {
        array[13] = (array[14] = 0.0);
        array[11] = (array[12] = 0.0);
        array[8] = (array[9] = 0.0);
        array[6] = (array[7] = 0.0);
        array[3] = (array[4] = 0.0);
        array[1] = (array[2] = 0.0);
        array[10] = (array[15] = 1.0);
        array[0] = (array[5] = 1.0);
    }
    
    public static double roundTo3Places(final double n) {
        return Math.round(1000.0 * n) * 0.001;
    }
    
    public static double[] transpose(final double[] array) {
        return new double[] { array[0], array[4], array[8], array[12], array[1], array[5], array[9], array[13], array[2], array[6], array[10], array[14], array[3], array[7], array[11], array[15] };
    }
    
    public static double[] v3Combine(final double[] array, final double[] array2, final double n, final double n2) {
        return new double[] { array[0] * n + array2[0] * n2, array[1] * n + array2[1] * n2, array[2] * n + array2[2] * n2 };
    }
    
    public static double[] v3Cross(final double[] array, final double[] array2) {
        return new double[] { array[1] * array2[2] - array[2] * array2[1], array[2] * array2[0] - array[0] * array2[2], array[0] * array2[1] - array[1] * array2[0] };
    }
    
    public static double v3Dot(final double[] array, final double[] array2) {
        return array[0] * array2[0] + array[1] * array2[1] + array[2] * array2[2];
    }
    
    public static double v3Length(final double[] array) {
        return Math.sqrt(array[0] * array[0] + array[1] * array[1] + array[2] * array[2]);
    }
    
    public static double[] v3Normalize(final double[] array, double n) {
        double v3Length = n;
        if (isZero(n)) {
            v3Length = v3Length(array);
        }
        n = 1.0 / v3Length;
        return new double[] { array[0] * n, array[1] * n, n * array[2] };
    }
}
