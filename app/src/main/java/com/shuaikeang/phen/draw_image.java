package com.shuaikeang.phen;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;



public class draw_image {
    Paint paint;
    Canvas canvas;
    ImageView imageView;
    Button button_color_main,button_clear,button_true,button_false,button_cancel,button_last;
    LinearLayout constraintLayout;
    ImageButton button_size_main;
    LinearLayout linearLayout_size;
    LinearLayout linearLayout_color;
    Boolean gd=false,can_cancel=false;
    int size_line;
    int color;
    float downX,downY,upX,upY,scale=1f;
    public int i;
    public void setnew(final Dialog dialog, Bitmap bitmap){
        size_line=80;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        button_color_main= dialog.getWindow().findViewById(R.id.button15);
        constraintLayout=dialog.getWindow().findViewById(R.id.con2);
        button_size_main=dialog.getWindow().findViewById(R.id.imageButton18);
        linearLayout_size=dialog.findViewById(R.id.linearlayout_size);
        linearLayout_color=dialog.findViewById(R.id.linearlayout_color);
        button_clear=dialog.getWindow().findViewById(R.id.button40);
        button_true=dialog.getWindow().findViewById(R.id.button42);
        button_false=dialog.getWindow().findViewById(R.id.button41);
        button_last=dialog.getWindow().findViewById(R.id.button13);
        button_cancel=dialog.getWindow().findViewById(R.id.button14);
        ColorDrawable colorDrawable=(ColorDrawable)button_color_main.getBackground();
        color=colorDrawable.getColor();
        paint.setColor(color);
        for ( i=0;i<20;i++){
            final Button button=((Button) linearLayout_color.getChildAt(i));
                button .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ColorDrawable colorDrawable=(ColorDrawable)button.getBackground();
                   button_color_main.setBackgroundColor(colorDrawable.getColor());
                    colorDrawable=(ColorDrawable)button_color_main.getBackground();
                    color=colorDrawable.getColor();
                    paint.setColor(color);
                }
            });
        }
        for ( i=0;i<6;i++){
            final ImageButton button=((ImageButton) linearLayout_size.getChildAt(i));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button_size_main.setPadding(button.getPaddingLeft(),button.getPaddingTop(),button.getPaddingRight(),button.getPaddingBottom());
                    //System.out.println(i);
                    //System.out.println(button==null);
                    size_line=(linearLayout_size.indexOfChild(button)+1)*20;
                    paint.setStrokeWidth(size_line);
                }
            });
        }

        imageView=dialog.getWindow().findViewById(R.id.imageView5);
         canvas=new Canvas(bitmap);
        imageView.setImageBitmap(bitmap);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(size_line);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Paint paint=new Paint();
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_CANCEL:
                        break;
                    case MotionEvent.ACTION_DOWN:
                        if (gd==false){
                            can_cancel=true;
                            imageView.getParent().requestDisallowInterceptTouchEvent(true);
                            Bitmap bitmap2=MainActivity.mainActivity.getBitmap(imageView.getDrawable());
                            MainActivity.mainActivity.imageView_example.setImageBitmap(bitmap2);
                        }else {
                            imageView.getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        downX = event.getX() ;
                        downY = event.getY() ;
                        break;
                    case MotionEvent.ACTION_UP:
                        upX = event.getX() ;
                        upY = event.getY() ;
                        imageView.invalidate();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (gd==false) {
                            upX = event.getX();
                            upY = event.getY();
                            Paint paint1 = new Paint();
                            paint1.setColor(color);
                            canvas.drawCircle(onSingleTap(downX, imageView), onSingleTap(downY, imageView), size_line / 2, paint1);
                            canvas.drawCircle(onSingleTap(upX, imageView), onSingleTap(upY, imageView), size_line / 2, paint1);
                            canvas.drawLine(onSingleTap(downX, imageView), onSingleTap(downY, imageView), onSingleTap(upX, imageView), onSingleTap(upY, imageView), paint);
                            imageView.invalidate();
                            downX = upX;
                            downY = upY;
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        button_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (can_cancel=true) {
                    imageView.setImageDrawable(MainActivity.mainActivity.imageView_example.getDrawable());
                    Bitmap bitmap1 = MainActivity.mainActivity.getBitmap(MainActivity.mainActivity.imageView_example.getDrawable());
                    canvas = new Canvas(bitmap1);
                    imageView.setImageBitmap(bitmap1);
                }
            }
        });
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainActivity.imageView_example.setImageDrawable(MainActivity.mainActivity.imageView_main.getDrawable());
                dialog.dismiss();
            }
        });
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageDrawable(MainActivity.mainActivity.imageView_main.getDrawable());
                Bitmap bitmap1=MainActivity.mainActivity.getBitmap(MainActivity.mainActivity.imageView_main.getDrawable());
                canvas=new Canvas(bitmap1);
                imageView.setImageBitmap(bitmap1);
            }
        });
        button_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mainActivity.imageView_main.setImageDrawable(imageView.getDrawable());
                ((ImageView)MainActivity.mainActivity.linearLayout_imagelist.getChildAt(MainActivity.mainActivity.select_num_image)).setImageDrawable(imageView.getDrawable());
                dialog.dismiss();
            }
        });
        button_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gd=!gd;
                if (gd==false){
                    button_false.setText("滚动");
                }else {
                    button_false.setText("绘制");
                }
            }
        });
    }

    public int onSingleTap(float x,ImageView imageView) {
        int width=imageView.getWidth();
        int height=imageView.getHeight();
        Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
        int width2=bitmap.getWidth();
        int height2=bitmap.getHeight();
        int newx=Math.round(x/width*width2);
        if (x == 1) {
            newx=1;
        }
        return newx;
    }
}
