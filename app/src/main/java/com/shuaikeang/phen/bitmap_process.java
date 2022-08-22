package com.shuaikeang.phen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

public class bitmap_process {

    Bitmap bitmap_1;
    public int[][] point_color;

    public int width,height;


    public void rewrite(Bitmap bitmap){
        bitmap_1=bitmap;
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        point_color=new int[height+1][width+1];
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        one2Two(pixels,point_color);


    }
    /*
    public void UPDATARGB(){//更新RGB信息
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                int clr=point_color[j][i];
                RGB[j][i][0] = (byte)((clr & 0x00ff0000) >> 16);
                RGB[j][i][1] = (byte)((clr & 0x0000ff00) >> 8);
                RGB[j][i][2] = (byte)((clr & 0x000000ff));
            }
        }
    }
    public void UPDATACOLOR(){
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                int clr=point_color[j][i];
                RGB[j][i][0] = (byte)((clr & 0x00ff0000) >> 16);
                RGB[j][i][1] = (byte)((clr & 0x0000ff00) >> 8);
                RGB[j][i][2] = (byte)((clr & 0x000000ff));
            }
        }


    }

     */

    public int getp(int x,int y){
        //x为第几行，y为第几列，从1开始
       return point_color[x][y];
    }
    public void setp(int x,int y,int col){
        point_color[x][y]=col;
    }

    public int getr(int x, int y) {
        return (int)((point_color[x][y]&(0x00ff0000))>>16);
    }

    public void setr(int x, int y, int col) {
        int clr=point_color[x][y];
        int gb = (clr & 0x0000ffff);
        point_color[x][y]=gb+((int)(col)*65536);
    }
    public int getg(int x, int y) {
        return (int)((point_color[x][y]&(0x0000ff00))>>8);
    }

    public void setg(int x, int y, int col) {
        int clr=point_color[x][y];
        int rb = (clr & 0x00ff00ff);
        point_color[x][y]=rb+((int)(col)*256);
    }
    public int getb(int x, int y) {
        return (int)((point_color[x][y]&(0x000000ff)));
    }

    public void setb(int x, int y, int col) {
        int clr=point_color[x][y];
        int rg = (clr & 0x00ffff00);
        point_color[x][y]=rg+((int)(col));
    }
    public Bitmap rep(){
        //System.out.println("finish1");



/*
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
               bitmap_1.setPixel(j,i,point_color[j][i]+0xff000000);
            }

        }

 */

        width = bitmap_1.getWidth();
        height = bitmap_1.getHeight();
        int[] pixels = new int[width * height];
        two2One(point_color,pixels);
        bitmap_1.setPixels(pixels, 0, width, 0, 0, width, height);
        //System.out.println("finish1");

        return bitmap_1;
    }
    public Bitmap getBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int heigh = drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, width, heigh);
        Bitmap bitmap = Bitmap.createBitmap(width, heigh, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);
        return bitmap;
    }
    public static void one2Two(int []data,int [][] da) {
        int k=0;
        int hang=da.length;
        int lie=da[0].length;
        for(int i=1;i<hang;i++) {
            for(int j=1;j<lie;j++) {
                da[i][j]=data[k]& 0x00ffffff;
                k++;
            }
        }
    }
    public static void two2One(int [][] da,int []data) {
        int k=0;
        int hang=da.length;
        int lie=da[0].length;
        for(int i=1;i<hang;i++) {
            for(int j=1;j<lie;j++) {
                data[k]=da[i][j]+0xff000000;
                k++;
            }
        }
    }






}
