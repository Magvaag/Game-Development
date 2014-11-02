package net.scratchforfun.gamedev;

import java.awt.*;
import java.util.Random;

/**
 * Created by Magnus on 13/10/2014.
 */
public class SimplexNoiseOctave {

    int numberOfSwaps = 400;

    private Point[] points = {new Point(1,1), new Point(-1,1), new Point(1,-1), new Point(-1,-1)};

    private static short p_supply[] = {42, 222, 46, 158, 150, 17, 224, 11,
            252, 133, 220, 190, 93, 151, 161, 243, 54, 48,78, 56, 20, 9, 66, 250, 219, 25, 189, 31,
            111, 14, 235, 131, 34, 170, 232, 101, 156, 159, 174, 88, 247, 41, 5, 122, 163, 23, 107,
            12, 230, 91, 50, 173, 135, 94, 248, 209, 92, 229, 40, 144, 241, 74, 134, 187, 194, 118,
            64, 114, 75, 36, 251, 160, 183, 130, 197, 200,205, 6,191, 166, 70, 188, 45,83, 60, 132,
            154, 147, 215, 53,227, 202, 245, 238, 13, 87, 223, 225, 103, 162, 72, 152, 63,120, 171,
            168, 108, 115, 117, 180, 110, 7,  121, 82, 15, 116, 149, 139, 207, 97, 3, 213, 112, 39,
            196, 172, 126, 30, 206, 179, 10, 102, 28, 164, 138, 236, 231, 59, 193, 24, 32, 226, 73,
            68, 182, 192, 100, 2, 181, 255, 167, 175, 155, 153,157, 246, 4, 214, 141, 137, 29, 234,
            89, 18, 19, 212, 142, 44, 47, 169, 1, 71, 37, 113, 104, 204, 210, 27, 186, 216, 95, 62,
            185, 16, 105, 61, 76,184, 218, 58, 239, 177, 22, 176,96, 195, 199,86, 143, 99, 49, 146,
            254, 203, 69, 51, 79, 217, 240, 21, 8, 211, 140, 57, 109, 145, 221, 35, 38, 26, 0, 136,
            124, 237, 85, 81, 242, 129, 77, 123, 80, 178, 106, 201, 84, 43, 119, 127, 125, 128, 65,
            228, 253, 67, 148, 90, 55, 52, 233, 198, 98, 249, 244, 208, 33, 165};
    private short p[];

    private short perm[] = new short[512];
    private short permMod12[] = new short[512];

    public SimplexNoiseOctave(int seed){
        p = p_supply.clone();

        Random random = new Random(seed);
        //
        for(int i = 0; i < numberOfSwaps; i++){
            int from = random.nextInt(p.length);
            int to = random.nextInt(p.length);

            short temp_p = p[from];
            p[from] = p[to];
            p[to] = temp_p;
        }

        // 8 bit system
        // 00000101 (5)
        // 00001011 (11)
        // 00000001 (1)
        // 5 & 11

        // 00000000 11111111 (255)
        // 00000001 10101100 (428)
        // 00000000 10101100

        // 27/6=4
        //-24
        //  3

        // 27
        // 21
        // 15
        //  9
        //  3

        for(int i = 0; i < perm.length; i++){
            perm[i] = p[i & 255];
            permMod12[i] = (short)(perm[i] % 4);
        }
    }

    private double dot(Point point, double x, double y){
        return point.x*x + point.y*y;
    }

    final double F2 = 0.5*(Math.sqrt(3.0)-1.0);
    final double G2 = (3.0-Math.sqrt(3.0))/6.0;

    // 2D Simplex Noise
    public double noise(double xin, double yin){
        double s = (xin+yin)*F2;
        int i = Game.floor(xin+s);
        int j = Game.floor(yin+s);
        double t = (i+j)*G2;
        double X0 = i-t;
        double Y0 = j-t;
        double x0 = xin-X0;
        double y0 = yin-Y0;

        int i1, j1;
        if(x0>y0){
            i1 = 1;
            j1 = 0;
        }else{
            i1 = 0;
            j1 = 1;
        }
        double x1 = x0 - i1 + G2;
        double y1 = y0 - j1 + G2;
        double x2 = x0 - 1.0 + 2.0*G2;
        double y2 = y0 - 1.0 + 2.0*G2;

        int ii = i & 255;
        int jj = j & 255;

        int gi0 = permMod12[ii+perm[jj]];
        int gi1 = permMod12[ii+i1+perm[jj+j1]];
        int gi2 = permMod12[ii+1+perm[jj+1]];

        double n0, n1, n2;
        double t0 = 0.5 - x0*x0-y0*y0;
        if(t0 < 0)
            n0 = 0;
        else{
            t0 *= t0;
            n0 = t0 * t0 * dot(points[gi0], x0, y0);
        }

        double t1 = 0.5 - x1*x1-y1*y1;
        if(t1 < 0)
            n1 = 0;
        else {
            t1 *= t1;
            n1 = t1 * t1 * dot(points[gi1], x1, y1);
        }

        double t2 = 0.5 - x2*x2-y2*y2;
        if(t2 < 0)
            n2 = 0;
        else {
            t2 *= t2;
            n2 = t2 * t2 * dot(points[gi2], x2, y2);
        }

        return 70*(n0 + n1 + n2);
    }
}
