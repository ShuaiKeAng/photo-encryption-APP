package com.shuaikeang.phen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.library.PhotoView;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
     Button button_switch,button_open,button_save,button_open_one,button1,button_start,button_start2,button_setting;
     LinearLayout linearLayout_switch,button_changeheight,linearLayout_2,linearLayout_imagelist,linearLayout_main,linearLayout_ks,linearLayout_down;
     HorizontalScrollView horizontalScrollView_1;
     TextView textView_show_length,textView_show_detal,textView_show_time;
     EditText editText_key,editText_side_length,editText_out_length;
     ImageView imageView_main;
     ImageButton imageButton_delet_key,imageButton_cut,imageButton_back,imageButton_delet,imageButton_change,imageButton_rect;
     ProgressBar progressBar;
     ConstraintLayout constraintLayout_picture;
     ScrollView scrollView;
     Dialog dialog_communication;
     final int line_width=300;
     public Dialog dialog;
     Bitmap bitmap_trans;
     int i_trans;
     String s_trans;
     setting setting_1=new setting();
     public static MainActivity mainActivity=null;
     public ImageView imageView_example;
     public bitmap_process bitmap_process_1=new bitmap_process();
    public time_spend time_spend_1=new time_spend();
    int width_or_height=0;
     int select_num_image=0,num_sum=0;
    int model=0,ii=0;
    boolean rect_image=false,ax=false,cs=false,cson=false;
    Point s1,s2;
    public int finishi=0;
    Vibrator vibrator;
    public int h_raw=0, h=0,targetheight=300,lx,ly,rx,ry,rh,lh,rw,lw;
    int miyaotu=0;

    float downX,downY,upX,upY,scale=1f;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        init();
        mainActivity=this;

        vibrator = (Vibrator) MainActivity.this.getSystemService(MainActivity.VIBRATOR_SERVICE);
        lisenner();
        lisenner_2();
        setting_1.getsetting();

        if (firstRun()||setting_1.contract_user==false){
            dialog=new Dialog(MainActivity.mainActivity);
            dialog.setContentView(R.layout.contract_layout);//dialog布局
            WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();//获取dialog布局的参数
            dialog.getWindow().setBackgroundDrawableResource(R.color.color_trans);
            layoutParams.width=WindowManager.LayoutParams.MATCH_PARENT;//全屏
            layoutParams.height=WindowManager.LayoutParams.MATCH_PARENT;//全屏
            dialog.getWindow().setAttributes(layoutParams);
            dialog.show();
            dialog.setCancelable(false);
            dialog.getWindow().findViewById(R.id.button_change_true).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setting_1.contract();
                    dialog.dismiss();

                }
            });
            dialog.getWindow().findViewById(R.id.button_change_false).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainActivity.finish();
                }
            });

        }
        if (setting_1.show==false){
            scrollView.setVisibility(View.GONE);
        }

        {
            findViewById(R.id.linearLayout).setVisibility(View.INVISIBLE);
            try {
                Thread.sleep(400);
            }catch (Exception e){

            }
            findViewById(R.id.linearLayout).setVisibility(View.VISIBLE);
            TranslateAnimation ta = new TranslateAnimation(0,0,-300,0);
            // 设置动画时长
            ta.setDuration(500);
            // 启动动画
            findViewById(R.id.linearLayout).startAnimation(ta);
        }


    }
    public void init(){
        button_switch=findViewById(R.id.button5);
        linearLayout_switch=findViewById(R.id.linelay);
        button_changeheight=findViewById(R.id.l7);
        horizontalScrollView_1=findViewById(R.id.hoscr_1);
        linearLayout_2=findViewById(R.id.l2);
        button_open=findViewById(R.id.button9);
        imageView_main=findViewById(R.id.imageView2);
        linearLayout_imagelist=findViewById(R.id.L_image_list);
        imageView_example=findViewById(R.id.imageView3);
        button_save=findViewById(R.id.button2);
       // button_open_one=findViewById(R.id.button9);
        linearLayout_main=findViewById(R.id.linearlayout_main);
        button_start=findViewById(R.id.button3);
        button_start2=findViewById(R.id.button4);
        editText_key=findViewById(R.id.editText3);
        textView_show_length=findViewById(R.id.textView3);
        imageButton_delet_key=findViewById(R.id.imageButton);
        imageButton_cut=findViewById(R.id.imageButton3);
        constraintLayout_picture=findViewById(R.id.con2);
        textView_show_detal=findViewById(R.id.textView9);
        textView_show_time=findViewById(R.id.textView10);
        imageButton_back=findViewById(R.id.imageButton4);
        imageButton_delet=findViewById(R.id.imageButton6);
        imageButton_change=findViewById(R.id.imageButton7);
        imageButton_rect=findViewById(R.id.imageButton5);
        editText_out_length=findViewById(R.id.editText2);
        editText_side_length=findViewById(R.id.editText);
        linearLayout_ks=findViewById(R.id.linearlayout_ks);
         progressBar=findViewById(R.id.progressBar);
         button1=findViewById(R.id.button);
        // button_setting=findViewById(R.id.button);
         //linearLayout_more=findViewById(R.id.linearlayout_more);
        scrollView=findViewById(R.id.scro_1);
        linearLayout_down=findViewById(R.id.linearLayout2);


    }




    public void lisenner(){
        findViewById(R.id.button37).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setting_1.noshow();
                scrollView.setVisibility(View.GONE);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(MainActivity.mainActivity);
                dialog.setContentView(R.layout.setting_layout);//dialog布局
                WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();//获取dialog布局的参数
                dialog.getWindow().setBackgroundDrawableResource(R.color.color_trans);
                layoutParams.width=WindowManager.LayoutParams.MATCH_PARENT;//全屏
                layoutParams.height=WindowManager.LayoutParams.MATCH_PARENT;//全屏
                dialog.getWindow().setAttributes(layoutParams);

                {

                    TranslateAnimation ta = new TranslateAnimation(0,0,-100,0);
                    // 设置动画时长
                    ta.setDuration(250);
                    // 启动动画
                    dialog.getWindow().findViewById(R.id.conmore).startAnimation(ta);
                }



                dialog.show();
                setting_1.setnew(dialog);

                dialog.getWindow().findViewById(R.id.button_change_false).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().findViewById(R.id.button_change_true).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setting_1.sure();
                        setting_1.update();
                        if (setting_1.show==true){
                            scrollView.setVisibility(View.VISIBLE);
                        }else {
                            scrollView.setVisibility(View.GONE);
                        }
                        dialog.dismiss();
                    }
                });


                }
        });

        imageButton_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap =((BitmapDrawable) (imageView_main.getDrawable())).getBitmap();
                final int width=bitmap.getWidth();
                int height=bitmap.getHeight();
                final double q=(double)height/width;
                dialog=new Dialog(MainActivity.mainActivity);
                dialog.setContentView(R.layout.change_layout);//dialog布局
                WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();//获取dialog布局的参数
                dialog.getWindow().setBackgroundDrawableResource(R.color.color_trans);
                layoutParams.width=WindowManager.LayoutParams.MATCH_PARENT;//全屏
                layoutParams.height=WindowManager.LayoutParams.MATCH_PARENT;//全屏
                dialog.getWindow().setAttributes(layoutParams);
                dialog.show();
                //((BlurBGImageView)dialog.getWindow().findViewById(R.id.img_vague)).refreshBG(dialog.getWindow().findViewById(R.id.linearLayout));

                {

                    TranslateAnimation ta = new TranslateAnimation(0,0,100,0);
                    // 设置动画时长
                    ta.setDuration(250);
                    // 启动动画
                    dialog.getWindow().findViewById(R.id.linearLayout).startAnimation(ta);
                }

                ((EditText)(dialog.getWindow().findViewById(R.id.editText10))).setText(String.valueOf(width));
                ((EditText)(dialog.getWindow().findViewById(R.id.editText20))).setText(String.valueOf(height));
                final CheckBox checkBox=dialog.getWindow().findViewById(R.id.checkBox);
                dialog.getWindow().findViewById(R.id.button_change_false).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                final EditText editText=((EditText)dialog.getWindow().findViewById(R.id.editText10));
                final EditText editText1=(EditText)dialog.getWindow().findViewById(R.id.editText20);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }
                    @Override
                    public void afterTextChanged(Editable s) {
                        try {
                            if (checkBox.isChecked()&& width_or_height!=2) {
                                width_or_height = 1;
                                int i = (Integer.parseInt(editText.getText().toString()));
                                if (i>5000){
                                    editText.setText("5000");
                                    i=5000;
                                }
                                int now=(int) (i * q);
                                if (i != 0) {
                                    if (now<5000) {
                                        editText1.setText(String.valueOf(now));

                                        width_or_height=0;
                                    }else {

                                        width_or_height=2;
                                        editText1.setText(String.valueOf(5000));
                                       // width_or_height=0;
                                    }
                                }
                            }
                        }catch (Exception e){
                            width_or_height=0;
                        }
                    }
                });

                editText1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        try {
                            if (checkBox.isChecked()&&width_or_height!=1) {
                                width_or_height = 2;
                                int i = (Integer.parseInt(editText1.getText().toString()));
                                if (i>5000){
                                    editText1.setText("5000");
                                    i=5000;
                                }
                                int now=(int) (i / q);
                                if (i != 0) {
                                    if (now<5000) {
                                        editText.setText(String.valueOf(now));
                                        width_or_height=0;
                                    }else {
                                        width_or_height=1;
                                        editText.setText(String.valueOf(5000));
                                        //width_or_height=0;
                                    }
                                }
                            }
                        }catch (Exception e){
                            width_or_height=0;
                        }
                    }
                });

                dialog.getWindow().findViewById(R.id.button_change_true).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            int width=Integer.parseInt(editText.getText().toString());
                            int height=Integer.parseInt(editText1.getText().toString());
                            if (width>0&&width<=5000&height<=5000&&height>0) {
                                //Bitmap bitmap=Bitmap.createBitmap(((BitmapDrawable)imageView_main.getDrawable()).getBitmap(),0,0,rightbottom_image.x,rightbottom_image.y);
                                Bitmap newBitmap = Bitmap.createScaledBitmap((((BitmapDrawable) imageView_main.getDrawable()).getBitmap()), width, height, true);
                                imageView_main.setImageBitmap(newBitmap);
                                ((ImageView) linearLayout_imagelist.getChildAt(select_num_image)).setImageBitmap(newBitmap);
                                dialog.dismiss();
                            }else {
                             Toast.makeText(MainActivity.this, "尺寸不合适", Toast.LENGTH_SHORT).show();
                            }

                        }catch (Exception e){

                        }
                    }
                });
            }
        });
        imageButton_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupWindow popupWindow = new PopupWindow(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                View inflate = View.inflate(mainActivity, R.layout.morebutton, null);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
                //必须添加一个背景，任意颜色的背景都可以
                popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popu_back));
                popupWindow.setContentView(inflate);

                inflate.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                int height_of = inflate.getMeasuredHeight();
                int width_of=inflate.getMeasuredWidth();
                popupWindow.showAsDropDown(linearLayout_down,linearLayout_down.getWidth()-width_of,-height_of, Gravity.TOP);
               Button button_more_delet,button_more_back,button_more_rotate,button_more_mirror_r,button_more_mirror_top,button_more_try,button_back,button_next,button_sw,button_swn;
                button_more_delet=inflate.findViewById(R.id.button26);
                button_more_back=inflate.findViewById(R.id.button27);
                button_more_rotate=inflate.findViewById(R.id.button36);
                button_more_mirror_r=inflate.findViewById(R.id.button34);
                button_more_mirror_top=inflate.findViewById(R.id.button35);
                button_more_try=inflate.findViewById(R.id.button38);
                button_back=inflate.findViewById(R.id.button50);
                button_next=inflate.findViewById(R.id.button51);
                button_sw=inflate.findViewById(R.id.button52);
                button_swn=inflate.findViewById(R.id.button53);
                button_sw.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        miyaotu=select_num_image;
                        Toast.makeText(MainActivity.this, "已设置密钥图，可在无影模式中做表图", Toast.LENGTH_SHORT).show();
                    }
                });
                button_swn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bitmap_process bitmap_process_sw=new bitmap_process();
                        bitmap_process_sw.rewrite(bitmap_process_sw.getBitmap(imageView_main.getDrawable()));
                        int h=bitmap_process_sw.height;
                        int w=bitmap_process_sw.width;
                        for (int i=1;i<=h-1;i+=2){
                            for (int j=1;j<=w-1;j+=2){
                                bitmap_process_sw.setr(i+1,j,bitmap_process_sw.getr(i+1,j));
                                bitmap_process_sw.setg(i+1,j,255-bitmap_process_sw.getg(i+1,j));
                                bitmap_process_sw.setb(i+1,j,255-bitmap_process_sw.getb(i+1,j));
                                bitmap_process_sw.setr(i,j+1,255-bitmap_process_sw.getr(i,j+1));
                                bitmap_process_sw.setg(i,j+1,bitmap_process_sw.getg(i,j+1));
                                bitmap_process_sw.setb(i,j+1,255-bitmap_process_sw.getb(i,j+1));
                                bitmap_process_sw.setr(i+1,j+1,255-bitmap_process_sw.getr(i+1,j+1));
                                bitmap_process_sw.setg(i+1,j+1,255-bitmap_process_sw.getg(i+1,j+1));
                                bitmap_process_sw.setb(i+1,j+1,bitmap_process_sw.getb(i+1,j+1));
                            }
                        }
                        if (h%2==1){
                            for (int j=1;j<=w-1;j+=2){
                                bitmap_process_sw.setr(h,j+1,255-bitmap_process_sw.getr(h,j+1));
                                bitmap_process_sw.setg(h,j+1,bitmap_process_sw.getg(h,j+1));
                                bitmap_process_sw.setb(h,j+1,255-bitmap_process_sw.getb(h,j+1));
                            }
                        }
                        if(w%2==1){
                            for (int i=1;i<=h-1;i+=2){
                                bitmap_process_sw.setr(i+1,w,255-bitmap_process_sw.getr(i+1,w));
                                bitmap_process_sw.setg(i+1,w,bitmap_process_sw.getg(i+1,w));
                                bitmap_process_sw.setb(i+1,w,255-bitmap_process_sw.getb(i+1,w));
                            }
                        }
                        imageView_main.setImageBitmap(bitmap_process_sw.rep());
                        ((ImageView)linearLayout_imagelist.getChildAt(select_num_image)).setImageDrawable(imageView_main.getDrawable());
                    }
                });
                /*
                button_swn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bitmap_process bitmap_process_sw=new bitmap_process();
                        bitmap_process_sw.rewrite(bitmap_process_sw.getBitmap(imageView_main.getDrawable()));
                        int h=bitmap_process_sw.height;
                        int w=bitmap_process_sw.width;
                        for(int i=1;i<=h;i++){
                            for (int j=1;j<=w;j++){
                                bitmap_process_sw.setr(i,j,range(127+(bitmap_process_sw.getr(i,j)-127)*2));
                                bitmap_process_sw.setg(i,j,range(127+(bitmap_process_sw.getg(i,j)-127)*2));
                                bitmap_process_sw.setb(i,j,range(127+(bitmap_process_sw.getb(i,j)-127)*2));
                            }
                        }
                        imageView_main.setImageBitmap(bitmap_process_sw.rep());
                        ((ImageView)linearLayout_imagelist.getChildAt(select_num_image)).setImageDrawable(imageView_main.getDrawable());
                        popupWindow.dismiss();
                    }
                });
                button_sw.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bitmap_process bitmap_process_sw=new bitmap_process();
                        bitmap_process_sw.rewrite(bitmap_process_sw.getBitmap(imageView_main.getDrawable()));
                        int h=bitmap_process_sw.height;
                        int w=bitmap_process_sw.width;
                        for(int i=1;i<=h;i++){
                            for (int j=1;j<=w;j++){
                                bitmap_process_sw.setr(i,j,127+(bitmap_process_sw.getr(i,j)-127)/2);
                                bitmap_process_sw.setg(i,j,127+(bitmap_process_sw.getg(i,j)-127)/2);
                                bitmap_process_sw.setb(i,j,127+(bitmap_process_sw.getb(i,j)-127)/2);
                            }
                        }
                        imageView_main.setImageBitmap(bitmap_process_sw.rep());
                        ((ImageView)linearLayout_imagelist.getChildAt(select_num_image)).setImageDrawable(imageView_main.getDrawable());
                        popupWindow.dismiss();
                    }
                });


                 */
                button_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (select_num_image!=num_sum){
                            select_num_image++;
                            imageView_main.setImageDrawable(((ImageView)linearLayout_imagelist.getChildAt(select_num_image)).getDrawable());
                            imageView_example.setImageDrawable(imageView_main.getDrawable());
                            popupWindow.dismiss();

                        }else {
                            Toast.makeText(MainActivity.this, "已是最后一张", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                button_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                button_more_try.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*bitmap_process bitmap_process_2=new bitmap_process();
                        bitmap_process bitmap_process_3=new bitmap_process();
                        bitmap_process_2.rewrite(bitmap_process_2.getBitmap(imageView_main.getDrawable()));
                        bitmap_process_3.rewrite(bitmap_process_3.getBitmap(imageView_example.getDrawable()));
                        for (int i=1;i<=bitmap_process_2.height;i++){
                            for (int j=1;j<=bitmap_process_2.width;j++){
                                if (bitmap_process_2.getp(i,j)!=bitmap_process_3.getp(i,j)){
                                    System.out.println(i+","+j);
                                }
                            }
                        }

                         */
                       ImageProcessUtils imageProcessUtils=new ImageProcessUtils();
                       Bitmap bitmap=getBitmap(imageView_main.getDrawable());

                        imageView_main.setImageBitmap(imageProcessUtils.invertBitmap(bitmap));
                        ((ImageView)linearLayout_imagelist.getChildAt(select_num_image)).setImageDrawable(imageView_main.getDrawable());
                        popupWindow.dismiss();
                    }
                });

                button_more_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imageView_main.setImageDrawable(imageView_example.getDrawable());
                        ((ImageView)linearLayout_imagelist.getChildAt(select_num_image)).setImageDrawable(imageView_example.getDrawable());
                        //linearLayout_more.setVisibility(View.GONE);
                        popupWindow.dismiss();
                    }
                });
                button_more_delet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linearLayout_imagelist.removeView(((ImageView)linearLayout_imagelist.getChildAt(select_num_image)));
                        imageView_main.setImageDrawable(null);
                        num_sum--;
                        select_num_image=0;
                        show_picture();
                        popupWindow.dismiss();
                       // linearLayout_more.setVisibility(View.GONE);
                    }
                });
                button_more_rotate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bitmap bitmap=getBitmap(imageView_main.getDrawable());
                        imageView_main.setImageBitmap(adjustPhotoRotation(bitmap,90));
                        ((ImageView)linearLayout_imagelist.getChildAt(select_num_image)).setImageDrawable(imageView_main.getDrawable());
                        popupWindow.dismiss();
                    }
                });
                button_more_mirror_r.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Matrix matrix=new Matrix();
                        Bitmap oldMap=getBitmap(imageView_main.getDrawable());
                        matrix.setScale(-1.0f, 1.0f);
                        oldMap = Bitmap.createBitmap(oldMap, 0, 0, oldMap.getWidth(), oldMap.getHeight(), matrix, true);
                        imageView_main.setImageBitmap(oldMap);
                        ((ImageView)linearLayout_imagelist.getChildAt(select_num_image)).setImageDrawable(imageView_main.getDrawable());
                        popupWindow.dismiss();
                    }
                });
                button_more_mirror_top.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Matrix matrix=new Matrix();
                        Bitmap oldMap=getBitmap(imageView_main.getDrawable());
                        matrix.setScale(1.0f, -1.0f);
                        oldMap = Bitmap.createBitmap(oldMap, 0, 0, oldMap.getWidth(), oldMap.getHeight(), matrix, true);
                        imageView_main.setImageBitmap(oldMap);
                        ((ImageView)linearLayout_imagelist.getChildAt(select_num_image)).setImageDrawable(imageView_main.getDrawable());
                        popupWindow.dismiss();
                    }
                });
            }
        });

        imageButton_delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(MainActivity.mainActivity);
                dialog.setContentView(R.layout.draw_image);//dialog布局
                WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();//获取dialog布局的参数
                dialog.getWindow().setBackgroundDrawableResource(R.color.color_trans);
                layoutParams.width=WindowManager.LayoutParams.MATCH_PARENT;//全屏
                layoutParams.height=WindowManager.LayoutParams.MATCH_PARENT;//全屏
                dialog.getWindow().setAttributes(layoutParams);
                dialog.show();
                {

                    TranslateAnimation ta = new TranslateAnimation(0,0,200,0);
                    // 设置动画时长
                    ta.setDuration(250);
                    // 启动动画
                    dialog.getWindow().findViewById(R.id.con2).startAnimation(ta);
                }
                Bitmap bitmap=getBitmap(imageView_main.getDrawable());
                draw_image draw_image_1=new draw_image();
                draw_image_1.setnew(dialog,bitmap);
            }
        });
        imageView_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ax==false) {
                    Bitmap bitmap = null;
                    try {
                        bitmap = ((BitmapDrawable) (imageView_main.getDrawable())).getBitmap();
                    } catch (Exception e) {

                    }
                    dialog = new Dialog(MainActivity.mainActivity);
                    dialog.setContentView(R.layout.image_preview);//dialog布局
                    WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();//获取dialog布局的参数
                    dialog.getWindow().setBackgroundDrawableResource(R.color.color_trans2);
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;//全屏
                    layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;//全屏
                    dialog.getWindow().setAttributes(layoutParams);
                    dialog.show();
                    dialog.getWindow().findViewById(R.id.imageButton2).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    PhotoView mPhotoView;
                    mPhotoView = dialog.getWindow().findViewById(R.id.photoview);
                    if (bitmap != null) {
                        ((TextView) (dialog.getWindow().findViewById(R.id.textView2))).setText(bitmap.getWidth() + "X" + bitmap.getHeight());
                        mPhotoView.setImageBitmap(bitmap);
                        mPhotoView.setMaxScale(setting_1.default_max_scale);

                    }
                    if (setting_1.default_show == false) {
                        dialog.getWindow().findViewById(R.id.textView2).setVisibility(View.INVISIBLE);
                    } else {
                        dialog.getWindow().findViewById(R.id.textView2).setVisibility(View.VISIBLE);
                    }
                    mPhotoView.enable();
                }
            }
        });

        imageView_main.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int mouseaction=event.getAction();
                switch (mouseaction) {
                    case MotionEvent.ACTION_DOWN:
                        ax=false;
                        rx=(int)event.getX();
                        break;
                    case  MotionEvent.ACTION_MOVE:


                        break;
                    case MotionEvent.ACTION_UP:
                        lx=(int)event.getX();
                        if (Math.abs(lx-rx)>50){
                            ax=true;
                            if (lx<rx){
                                if (select_num_image!=num_sum){
                                    select_num_image++;

                                    imageView_main.setImageDrawable(((ImageView)linearLayout_imagelist.getChildAt(select_num_image)).getDrawable());

                                }else {
                                    Toast.makeText(MainActivity.this, "已是最后一张", Toast.LENGTH_SHORT).show();
                                }

                            }else {
                                if (select_num_image!=1){
                                    select_num_image--;
                                    imageView_main.setImageDrawable(((ImageView)linearLayout_imagelist.getChildAt(select_num_image)).getDrawable());
                                }else {
                                    Toast.makeText(MainActivity.this, "已是第一张", Toast.LENGTH_SHORT).show();
                                }
                            }

                            imageView_example.setImageDrawable(imageView_main.getDrawable());
                        }

                        break;

                }



                return false;
            }
        });

        button_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rect_image==false) {
                    model++;
                    switch (model) {

                        case 0://linearLayout_switch.setGravity(Gravity.LEFT);
                            button_switch.setText("常规");
                            linearLayout_ks.setVisibility(View.GONE);
                            textView_show_detal.setText("拥有36种加密算法，解密\n需图片为原始尺寸的原图");
                            break;
                        case 1://linearLayout_switch.setGravity(Gravity.CENTER);
                            button_switch.setText("批量");
                            linearLayout_ks.setVisibility(View.GONE);
                            textView_show_detal.setText("对列表中的图片批量使用\n同一个密钥进行常规加密");
                            break;
                        case 2:
                            button_switch.setText("无影");
                            linearLayout_ks.setVisibility(View.GONE);
                            textView_show_detal.setText("请先选择图片设置密钥图\n并且输入小于一的正小数");

                            break;
                        case 3://linearLayout_switch.setGravity(Gravity.RIGHT);
                            textView_show_detal.setText("拥有10种加密算法，可以\n将被压缩的加密图片还原");
                            editText_key.setText("");
                            editText_side_length.setText(String.valueOf(setting_1.default_rect_width));
                            editText_out_length.setText(String.valueOf(setting_1.default_rect_outwidth));
                            linearLayout_ks.setVisibility(View.VISIBLE);
                            button_switch.setText("抗损");
                            model = -1;
                            break;
                        default:
                            break;

                    }


                    if (setting_1.default_vibrator) vibrator.vibrate(100);


                }else {
                    Toast.makeText(MainActivity.this, "请先关闭选区再切换模式", Toast.LENGTH_SHORT).show();
                }
            }
        });



        final Toast toast_1=Toast.makeText(MainActivity.this, "关闭列表", Toast.LENGTH_SHORT);
        button_changeheight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //scrollView.setClickable(false);
                int minheight=100,maxheight=600;
                int mouseaction=event.getAction();
                if (horizontalScrollView_1.getVisibility()==View.GONE){
                    horizontalScrollView_1.setVisibility(View.VISIBLE);
                    linearLayout_2.setVisibility(View.VISIBLE);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) horizontalScrollView_1.getLayoutParams();
                    layoutParams.height =targetheight;

                    horizontalScrollView_1.setLayoutParams(layoutParams);
                   if (setting_1.default_vibrator) vibrator.vibrate(100);

                }
                switch (mouseaction){
                    case MotionEvent.ACTION_DOWN:
                        h=(int)event.getRawY();
                        h_raw=horizontalScrollView_1.getLayoutParams().height;

                        break;
                    case MotionEvent.ACTION_MOVE:
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) horizontalScrollView_1.getLayoutParams();
                        layoutParams.height = h_raw + (int) event.getRawY() - h;
                        if ( layoutParams.height <=minheight){
                            layoutParams.height=minheight;
                            toast_1.show();
                        }
                        if (layoutParams.height>=maxheight){
                            layoutParams.height=maxheight;
                        }
                        horizontalScrollView_1.setLayoutParams(layoutParams);
                        break;
                    case MotionEvent.ACTION_UP:
                        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) horizontalScrollView_1.getLayoutParams();
                        if (layoutParams1.height==minheight){
                            horizontalScrollView_1.setVisibility(View.GONE);
                            linearLayout_2.setVisibility(View.GONE);
                            if (setting_1.default_vibrator) vibrator.vibrate(100);
                            toast_1.cancel();
                        }
                        h=0;
                        break;
                }
                return false;


            }
        });

        button_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, 2);
                final Toast toast=Toast.makeText(MainActivity.this, "长按多选", Toast.LENGTH_SHORT);
                toast.show();
                //startActivityForResult(Intent.createChooser(intent,"Select Picture"), REQUEST_CODE_IMAGE);


            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(MainActivity.mainActivity);
                dialog.setContentView(R.layout.save_layout);//dialog布局
                WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();//获取dialog布局的参数
                dialog.getWindow().setBackgroundDrawableResource(R.color.color_trans);
                layoutParams.width=WindowManager.LayoutParams.MATCH_PARENT;//全屏
                layoutParams.height=WindowManager.LayoutParams.MATCH_PARENT;//全屏
                dialog.getWindow().setAttributes(layoutParams);
                dialog.show();
                {

                    TranslateAnimation ta = new TranslateAnimation(0,0,-100,0);
                    // 设置动画时长
                    ta.setDuration(250);
                    // 启动动画
                    dialog.getWindow().findViewById(R.id.linearLayout).startAnimation(ta);
                }

                ((EditText)dialog.getWindow().findViewById(R.id.editText_name1)).setText(setting_1.default_name);
                ((EditText)dialog.getWindow().findViewById(R.id.editText2_name2)).setText(setting_1.default_name);
                ((TextView)dialog.getWindow().findViewById(R.id.textView8)).setText("列表中一共有"+num_sum+"张图片，当前选中第"+select_num_image+"张");
                ((RadioButton)dialog.getWindow().findViewById(R.id.radioButton)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.getWindow().findViewById(R.id.linearlayout_single).setVisibility(View.VISIBLE);
                        dialog.getWindow().findViewById(R.id.linearlayout_group).setVisibility(View.GONE);
                    }
                });
                ((RadioButton)dialog.getWindow().findViewById(R.id.radioButton2)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.getWindow().findViewById(R.id.linearlayout_group).setVisibility(View.VISIBLE);
                        dialog.getWindow().findViewById(R.id.linearlayout_single).setVisibility(View.GONE);
                    }
                });
                dialog_communication=dialog;
                dialog.getWindow().findViewById(R.id.button_change_true).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (dialog.getWindow().findViewById(R.id.linearlayout_single).getVisibility()==View.VISIBLE){
                            saveBitmapPhoto(getBitmap(imageView_main.getDrawable()),mainActivity,((EditText)dialog.getWindow().findViewById(R.id.editText_name1)).getText().toString());
                            dialog.dismiss();
                        }else{
                            new Thread() {
                                @Override
                                public void run() {
                                    for (int i = 1; i <= num_sum; i++) {
                                        saveBitmapPhoto(getBitmap(((ImageView) linearLayout_imagelist.getChildAt(i)).getDrawable()), mainActivity, ((EditText) dialog.getWindow().findViewById(R.id.editText2_name2)).getText().toString() + "_" + i);

                                    }
                                    handler.sendEmptyMessage(7);//通知主线程更新控件
                                }
                            }.start();
                            dialog.getWindow().findViewById(R.id.progressBar2).setVisibility(View.VISIBLE);
                            dialog.getWindow().findViewById(R.id.button_change_true).setEnabled(false);
                        }
                       // dialog.dismiss();
                    }
                });
                dialog.getWindow().findViewById(R.id.button_change_false).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });
                dialog.getWindow().findViewById(R.id.imageButton8).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar=Calendar.getInstance();
                        ((EditText)dialog.getWindow().findViewById(R.id.editText_name1)).setText(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+" "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));
                    }
                });
                dialog.getWindow().findViewById(R.id.imageButton9).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar=Calendar.getInstance();
                        ((EditText)dialog.getWindow().findViewById(R.id.editText2_name2)).setText(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+" "+calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));
                    }
                });
            }
        });
        editText_key.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
               textView_show_length.setText(String.valueOf(editText_key.getText().toString().length()));
            }
        });
        imageButton_delet_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_key.setText("");
            }
        });



    }

    public void lisenner_2(){
        imageButton_rect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model=0;
                button_switch.setText("常规");
                linearLayout_ks.setVisibility(View.GONE);
                textView_show_detal.setText("拥有36种加密算法，解密\n需图片为原始尺寸的原图");

                Bitmap bitmap = null;
                try {
                    bitmap = ((BitmapDrawable) (imageView_main.getDrawable())).getBitmap();
                } catch (Exception e) {

                }
                dialog = new Dialog(MainActivity.mainActivity);
                dialog.setContentView(R.layout.image_cut2);//dialog布局
                final Button scan = dialog.getWindow().findViewById(R.id.button11);
                final ImageView photoView = dialog.getWindow().findViewById(R.id.imageView);
                // ConstraintLayout constraintLayout=  dialog.getWindow().findViewById(R.id.con_1);
                WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();//获取dialog布局的参数
                dialog.getWindow().setBackgroundDrawableResource(R.color.color_trans);
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;//全屏
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;//全屏
                dialog.getWindow().setAttributes(layoutParams);
                dialog.show();
                {

                    TranslateAnimation ta = new TranslateAnimation(0,0,100,0);
                    // 设置动画时长
                    ta.setDuration(250);
                    // 启动动画
                    dialog.getWindow().findViewById(R.id.linearLayout4).startAnimation(ta);
                }
                photoView.setImageBitmap(bitmap);
                {
                    dialog.getWindow().findViewById(R.id.imageButton11).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            dialog.getWindow().findViewById(R.id.imageButton11).getParent().requestDisallowInterceptTouchEvent(true);
                            ConstraintLayout constraintLayout = dialog.getWindow().findViewById(R.id.con_1);
                            ImageView photoView = dialog.getWindow().findViewById(R.id.imageView);
                            ImageButton imageButton = dialog.getWindow().findViewById(R.id.imageButton11);
                            int mouseaction = event.getAction();
                            switch (mouseaction) {
                                case MotionEvent.ACTION_DOWN:
                                    rh = (int) constraintLayout.getBottom();
                                    rw = (int) constraintLayout.getRight();

                                    //rw=(int)constraintLayout.getWidth();
                                    //lx=(int)constraintLayout.getX();
                                    //ly=(int)constraintLayout.getY();
                                    lw = (int) event.getRawX();
                                    lh = (int) event.getRawY();
                                    ly = (int) imageButton.getY();
                                    lx = (int) imageButton.getX();
                                    break;
                                case MotionEvent.ACTION_MOVE:

                                    //System.out.println(rh + (int) event.getRawY() - lh);
                                    if (imageButton.getHeight() <= rh + (int) event.getRawY() - lh - constraintLayout.getTop() && rh + (int) event.getRawY() - lh <= photoView.getHeight()) {
                                        constraintLayout.setBottom(rh + (int) event.getRawY() - lh);
                                        imageButton.setY(ly + (int) event.getRawY() - lh);
                                    } else if (rh + (int) event.getRawY() - lh - constraintLayout.getTop() < imageButton.getHeight()) {
                                        constraintLayout.setBottom(constraintLayout.getTop() + imageButton.getHeight());
                                        imageButton.setY(0);
                                    } else {
                                        constraintLayout.setBottom(photoView.getHeight());
                                        imageButton.setY(constraintLayout.getHeight() - imageButton.getHeight());

                                    }
                                    if (rw + (int) event.getRawX() - lw <= photoView.getWidth() && rw + (int) event.getRawX() - lw - constraintLayout.getLeft() >= imageButton.getWidth()) {
                                        constraintLayout.setRight(rw + (int) event.getRawX() - lw);

                                        imageButton.setX(lx + (int) event.getRawX() - lw);
                                    } else if (rw + (int) event.getRawX() - lw - constraintLayout.getLeft() < imageButton.getWidth()) {
                                        constraintLayout.setRight(imageButton.getWidth() + constraintLayout.getLeft());
                                        imageButton.setX(0);
                                    } else {
                                        constraintLayout.setRight(photoView.getWidth());
                                        imageButton.setX(constraintLayout.getWidth() - imageButton.getWidth());
                                    }


                                    // System.out.println(constraintLayout.getHeight());
                                    break;
                                case MotionEvent.ACTION_UP:
                                    break;
                            }
                            return false;
                        }
                    });
                }
                {
                    dialog.getWindow().findViewById(R.id.con_1).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {

                            ConstraintLayout constraintLayout = dialog.getWindow().findViewById(R.id.con_1);
                            ImageView photoView = dialog.getWindow().findViewById(R.id.imageView);
                            if (constraintLayout.getTop() != 0 || constraintLayout.getBottom() != photoView.getHeight()) {
                                constraintLayout.getParent().requestDisallowInterceptTouchEvent(true);
                            }


                            ImageButton imageButton = dialog.getWindow().findViewById(R.id.imageButton11);
                            int mouseaction = event.getAction();
                            switch (mouseaction) {
                                case MotionEvent.ACTION_DOWN:
                                    ly = (int) event.getRawY();
                                    lx = (int) event.getRawX();
                                    rx = (int) constraintLayout.getLeft();
                                    rw = (int) constraintLayout.getWidth();
                                    rh = (int) constraintLayout.getHeight();
                                    ry = (int) constraintLayout.getTop();
                                    break;
                                case MotionEvent.ACTION_MOVE:

                                    if (rx + (int) event.getRawX() - lx >= 0 && rx + (int) event.getRawX() - lx + constraintLayout.getWidth() <= photoView.getWidth()) {
                                        constraintLayout.setLeft(rx + (int) event.getRawX() - lx);
                                    } else if (rx + (int) event.getRawX() - lx < 0) {
                                        constraintLayout.setLeft(0);
                                    } else if (rx + (int) event.getRawX() - lx + constraintLayout.getWidth() > photoView.getWidth()) {
                                        constraintLayout.setLeft(photoView.getWidth() - constraintLayout.getWidth());
                                    }

                                    if (ry + (int) event.getRawY() - ly >= 0 && ry + (int) event.getRawY() - ly + constraintLayout.getHeight() <= photoView.getHeight()) {
                                        constraintLayout.setTop(ry + (int) event.getRawY() - ly);
                                    } else if (ry + (int) event.getRawY() - ly < 0) {
                                        constraintLayout.setTop(0);
                                    } else if (ry + (int) event.getRawY() - ly + constraintLayout.getHeight() > photoView.getHeight()) {
                                        constraintLayout.setTop(photoView.getHeight() - constraintLayout.getHeight());
                                    }
                                    constraintLayout.setBottom(constraintLayout.getTop() + rh);
                                    constraintLayout.setRight(constraintLayout.getLeft() + rw);
                                    imageButton.setX(constraintLayout.getWidth() - imageButton.getWidth());
                                    imageButton.setY(constraintLayout.getHeight() - imageButton.getHeight());
                                    break;
                                case MotionEvent.ACTION_UP:

                                    break;


                            }
                            // scrollView.setClickable(true);
                            return false;


                        }
                    });
                }
                final Button button_select_rect = dialog.getWindow().findViewById(R.id.button6);
                scan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Bitmap bitmap1 = getBitmap(photoView.getDrawable());
                        bitmap_process smp = new bitmap_process();
                        smp.rewrite(bitmap1);
                        int w = smp.width;
                        int h = smp.height;
                        int[][] rc = new int[h + 1][w + 1];
                        int[][] rc2 = new int[h + 1][w + 1];
                        int[] wmax = new int[w + 1];
                        int[] wwmax = new int[w + 1];
                        int[] hmax = new int[h + 1];
                        int[] hhmax = new int[h + 1];
                        int sw = 1, sh = 1;
                        int max = 1;
                        int max2 = 1;
                        int max3 = 1;
                        int max4 = 1;
                        int nnum = setting_1.default_scan_num;//阈值，无加密的区域一般为5，而加密的区域一般在200左右，因此阈值取在这之间
                        final ConstraintLayout constraintLayout = dialog.getWindow().findViewById(R.id.con_1);
                        final ImageView photoView = dialog.getWindow().findViewById(R.id.imageView);
                        Point lefttop = new Point((int) constraintLayout.getLeft(), (int) constraintLayout.getTop());
                        Point rightbottom = new Point((int) constraintLayout.getRight(), (int) constraintLayout.getBottom());
                        Point lefttop_image = onSingleTap(lefttop, photoView);
                        s1 = lefttop_image;
                        Point rightbottom_image = onSingleTap(rightbottom, photoView);
                        s2 = rightbottom_image;
                        w = s2.x;
                        h = s2.y;
                        sw = s1.x + 1;
                        ;
                        sh = s1.y + 1;
                        max = sw;
                        max2 = sw;
                        max3 = sh;
                        max4 = sh;
                        for (int i = sw; i <= w; i++) {
                            for (int j = sh; j <= h - 1; j++) {
                                rc[j][i] = Math.abs(smp.getr(j, i) - smp.getr(j + 1, i)) + Math.abs(smp.getg(j, i) - smp.getg(j + 1, i)) + Math.abs(smp.getb(j, i) - smp.getb(j + 1, i));
                            }
                        }
                        for (int i = sw; i <= w; i++) {
                            for (int j = sh; j <= h - 1; j++) {

                                if (rc[j][i] > nnum) {
                                    wmax[i]++;
                                }
                            }
                        }
                        for (int i = sw; i <= w - 1; i++) {
                            wwmax[i] = Math.abs(wmax[i] - wmax[i + 1]);
                        }
                        for (int i = sw; i <= w - 1; i++) {
                            if (wwmax[i] > wwmax[max]) max = i;
                        }
                        for (int i = sw; i <= w - 1; i++) {
                            if (wwmax[i] > wwmax[max2] && i != max) {
                                max2 = i;
                            }
                        }
                        if (max2 < max) {
                            int t;
                            t = max;
                            max = max2;
                            max2 = t;
                        }
                        max++;
                        for (int i = sw; i <= w - 1; i++) {
                            for (int j = sh; j <= h; j++) {
                                rc2[j][i] = Math.abs(smp.getr(j, i) - smp.getr(j, i + 1)) + Math.abs(smp.getg(j, i) - smp.getg(j, i + 1)) + Math.abs(smp.getb(j, i) - smp.getb(j, i + 1));
                            }
                        }
                        for (int i = sw; i <= w - 1; i++) {
                            for (int j = sh; j <= h; j++) {
                                if (rc2[j][i] > nnum) hmax[j]++;
                            }
                        }
                        for (int i = sh; i <= h - 1; i++) {
                            hhmax[i] = Math.abs(hmax[i] - hmax[i + 1]);
                        }
                        for (int i = sh; i <= h - 1; i++) {
                            if (hhmax[i] > hhmax[max3]) max3 = i;
                        }
                        for (int i = sh; i <= h - 1; i++) {
                            if (hhmax[i] > hhmax[max4] && i != max3) {
                                max4 = i;
                            }
                        }
                        if (max4 < max3) {
                            int t;
                            t = max3;
                            max3 = max4;
                            max4 = t;
                        }
                        max3++;
                        smp.rep();
                        s1.x = max;
                        s1.y = max3;
                        s2.x = max2;
                        s2.y = max4;
                        int side = which_side(constraintLayout, photoView);
                        switch (side) {
                            case 1:
                                s1.y = 1;
                                break;
                            case 2:
                                s2.x = onSingleTap(new Point(photoView.getWidth(), 0), photoView).x;
                                break;
                            case 3:
                                s2.y = onSingleTap(new Point(0, photoView.getHeight()), photoView).y;
                                break;
                            case 4:
                                s1.x = 1;
                                break;
                            default:
                        }

                        long sumrc1 = 0;
                        int sumpixel = 0;
                        for (int i = s1.x; i <= s2.x; i++) {
                            for (int j = s1.y; j <= s2.y - 1; j++) {
                                sumrc1 += rc[j][i];
                                sumpixel++;
                            }
                        }
                        for (int i = s1.x; i <= s2.x - 1; i++) {
                            for (int j = s1.y; j <= s2.y; j++) {
                                sumrc1 += rc2[j][i];
                            }
                        }
                        if (sumpixel == 0) sumpixel = 1;
                        int perrc = (int) (sumrc1 / (sumpixel * 2));

                        if (perrc < 120 || sumpixel < 25) {
                            s1.x = max;
                            s1.y = max3;
                            s2.x = max2;
                            s2.y = max4;
                            sumrc1 = 0;
                            sumpixel = 0;
                            for (int i = s1.x; i <= s2.x; i++) {
                                for (int j = s1.y; j <= s2.y - 1; j++) {
                                    sumrc1 += rc[j][i];
                                    sumpixel++;
                                }
                            }
                            for (int i = s1.x; i <= s2.x - 1; i++) {
                                for (int j = s1.y; j <= s2.y; j++) {
                                    sumrc1 += rc2[j][i];
                                }
                            }
                            if (sumpixel == 0) sumpixel = 1;
                            perrc = (int) (sumrc1 / (sumpixel * 2));
                        }
                        if (perrc > 200) perrc = 200;
                        System.out.println(perrc);
                        if (perrc > 25 && sumpixel > 25) {
                            if (perrc <= 80 || sumpixel < 100) {
                                final Toast toast_1 = Toast.makeText(MainActivity.this, "疑似未识别准确，可信度" + perrc * 100 / 200 + "%", Toast.LENGTH_SHORT);
                                toast_1.show();
                            } else {
                                final Toast toast_1 = Toast.makeText(MainActivity.this, "可信度" + perrc * 100 / 200 + "%", Toast.LENGTH_SHORT);
                                toast_1.show();
                            }
                            Canvas canvas = new Canvas(bitmap1);
                            Paint paint = new Paint();
                            paint.setColor(Color.RED);
                            paint.setStyle(Paint.Style.STROKE);//不填充
                            paint.setStrokeWidth((w + h) / line_width);  //线的宽度
                            canvas.drawRect(s1.x - 1, s1.y - 1, s2.x - 1, s2.y - 1, paint);
                            photoView.setImageBitmap(bitmap1);
                            dialog.getWindow().findViewById(R.id.linearlayout_rect).setVisibility(View.VISIBLE);
                            ((TextView) dialog.getWindow().findViewById(R.id.textView15)).setText(s1.x + "," + s1.y + "  " + s2.x + "," + s2.y);
                            button_select_rect.setEnabled(false);
                            scan.setEnabled(false);
                            dialog.getWindow().findViewById(R.id.button12).setEnabled(true);
                            dialog.getWindow().findViewById(R.id.con_1).setVisibility(View.GONE);
                            ImageButton imageButton = dialog.findViewById(R.id.imageButton11);
                            imageButton.setX(photoView.getWidth() - imageButton.getWidth());
                            imageButton.setY(photoView.getHeight() - imageButton.getHeight());
                        } else {
                            final Toast toast_1 = Toast.makeText(MainActivity.this, "未识别到可能选区", Toast.LENGTH_SHORT);
                            toast_1.show();
                        }
                    }
                });
                if (rect_image == true) {
                    Bitmap bitmap1 = getBitmap(photoView.getDrawable());
                    Canvas canvas = new Canvas(bitmap1);
                    Paint paint = new Paint();
                    paint.setColor(Color.RED);
                    paint.setStyle(Paint.Style.STROKE);//不填充
                    paint.setStrokeWidth((bitmap1.getWidth() + bitmap1.getHeight()) / line_width);  //线的宽度
                    canvas.drawRect(s1.x - 1, s1.y - 1, s2.x - 1, s2.y - 1, paint);
                    photoView.setImageBitmap(bitmap1);
                    dialog.getWindow().findViewById(R.id.linearlayout_rect).setVisibility(View.VISIBLE);
                    ((TextView) dialog.getWindow().findViewById(R.id.textView15)).setText(s1.x + "," + s1.y + "  " + s2.x + "," + s2.y);
                    button_select_rect.setEnabled(false);
                    scan.setEnabled(false);
                    dialog.getWindow().findViewById(R.id.button12).setEnabled(true);
                    dialog.getWindow().findViewById(R.id.con_1).setVisibility(View.GONE);
                    ImageButton imageButton = dialog.findViewById(R.id.imageButton11);
                    imageButton.setX(photoView.getWidth() - imageButton.getWidth());
                    imageButton.setY(photoView.getHeight() - imageButton.getHeight());
                }

                dialog.getWindow().findViewById(R.id.button12).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rect_image = true;
                        imageButton_rect.setBackgroundResource(R.drawable.button_back_5);
                        dialog.dismiss();
                    }
                });

                dialog.getWindow().findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rect_image = false;
                        imageButton_rect.setBackgroundResource(R.drawable.button_back_4);
                        dialog.dismiss();

                    }
                });

                button_select_rect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ConstraintLayout constraintLayout = dialog.getWindow().findViewById(R.id.con_1);
                        final ImageView photoView = dialog.getWindow().findViewById(R.id.imageView);
                        Point lefttop = new Point((int) constraintLayout.getX() + 1, (int) constraintLayout.getY() + 1);
                        Point rightbottom = new Point((int) constraintLayout.getRight(), (int) constraintLayout.getBottom());
                        Point lefttop_image = onSingleTap(lefttop, photoView);
                        s1 = lefttop_image;
                        Point rightbottom_image = onSingleTap(rightbottom, photoView);
                        s2 = rightbottom_image;
                        Bitmap bitmap1 = getBitmap(photoView.getDrawable());
                        Canvas canvas = new Canvas(bitmap1);
                        Paint paint = new Paint();
                        paint.setColor(Color.RED);
                        paint.setStyle(Paint.Style.STROKE);//不填充
                        paint.setStrokeWidth((bitmap1.getWidth() + bitmap1.getHeight()) / line_width);  //线的宽度
                        canvas.drawRect(lefttop_image.x - 1, lefttop_image.y - 1, rightbottom_image.x - 1, rightbottom_image.y - 1, paint);
                        photoView.setImageBitmap(bitmap1);
                        dialog.getWindow().findViewById(R.id.linearlayout_rect).setVisibility(View.VISIBLE);
                        ((TextView) dialog.getWindow().findViewById(R.id.textView15)).setText(lefttop_image.x + "," + lefttop_image.y + "  " + rightbottom_image.x + "," + rightbottom_image.y);
                        constraintLayout.setVisibility(View.GONE);
                        dialog.getWindow().findViewById(R.id.button12).setEnabled(true);
                        ImageButton imageButton = dialog.findViewById(R.id.imageButton11);
                        imageButton.setX(photoView.getWidth() - imageButton.getWidth());
                        imageButton.setY(photoView.getHeight() - imageButton.getHeight());
                        scan.setEnabled(false);
                        button_select_rect.setEnabled(false);
                    }
                });
                dialog.getWindow().findViewById(R.id.imageButton10).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        photoView.setImageBitmap(((BitmapDrawable) (imageView_main.getDrawable())).getBitmap());
                        dialog.getWindow().findViewById(R.id.linearlayout_rect).setVisibility(View.GONE);
                        dialog.getWindow().findViewById(R.id.con_1).setVisibility(View.VISIBLE);
                        dialog.getWindow().findViewById(R.id.button12).setEnabled(false);
                        scan.setEnabled(true);
                        button_select_rect.setEnabled(true);
                    }
                });

                {
                    /*

                //测试用代码
                    dialog.getWindow().findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int num_now=num_sum;
                            float average=0;
                            for (int image_num = 1; image_num <= num_now; image_num++) {
                                Bitmap bitmap1 = getBitmap(((ImageView) linearLayout_imagelist.getChildAt(image_num)).getDrawable());
                                System.out.println(bitmap1.getWidth()+","+bitmap1.getHeight()+"\n");
                                int numi=setting_1.default_scan_num;
                                    int sum_true = 100;
                                    for (int sumi = 0; sumi < 100; sumi++) {

                                            try{
                                            int min = 8;
                                            int max11 = 15;
                                            Random random = new Random();
                                            int num_length = random.nextInt(max11) % (max11 - min + 1) + min;
                                            String ss = "";
                                            for (int longi = 1; longi <= num_length; longi++) {
                                                int min1 = 1;
                                                int max1 = 26;
                                                Random random1 = new Random();
                                                int num_26 = random.nextInt(max1) % (max1 - min1 + 1) + min1;
                                                ss += (char) ('a' + num_26 - 1);

                                            }
                                            int x3 = random_numble(1, bitmap1.getWidth() - 40);
                                            int x4 = random_numble(1, bitmap1.getWidth() - 2);
                                            while (x4 <= x3 + 20) {
                                                x4 = random_numble(1, bitmap1.getWidth() - 2);
                                            }
                                            int y3 = random_numble(1, bitmap1.getHeight() - 40);
                                            int y4 = random_numble(1, bitmap1.getHeight() - 2);
                                            while (y4 <= y3 + 20) {
                                                y4 = random_numble(1, bitmap1.getHeight() - 2);
                                            }
                                            int xa_1, ya_1, xa_2, ya_2;


                                            s1 = new Point(x3, y3);
                                            s2 = new Point(x4 - x3 + 1, y4 - y3 + 1);

                                            xa_1 = s1.x;
                                            xa_2 = s2.x;
                                            ya_1 = s1.y;
                                            ya_2 = s2.y;


                                            jm jm_1 = new jm();
                                            jm_1.getraw2(((ImageView) linearLayout_imagelist.getChildAt(image_num)).getDrawable(), s1, s2);
                                            jm_1.runp(ss);

                                            bitmap1 = jm_1.setnew2(s1, s2);
                                            bitmap_process smp = new bitmap_process();
                                            smp.rewrite(bitmap1);
                                            int w = smp.width;
                                            int h = smp.height;
                                            int[][] rc = new int[h + 1][w + 1];
                                            int[][] rc2=new int[h+1][w+1];
                                            int[] wmax = new int[w + 1];
                                            int[] wwmax = new int[w + 1];
                                            int[] hmax = new int[h + 1];
                                            int[] hhmax = new int[h + 1];
                                            int sw = 1, sh = 1;
                                            int max = 1;
                                            int max2 = 1;
                                            int max3 = 1;
                                            int max4 = 1;

                                            int perrc=0;
                                            int num_try=0;
                                            int problem_num=0;
                                            while(perrc<150&&num_try<2) {

                                                int nnum = numi;//阈值，无加密的区域一般为5，而加密的区域一般在200左右，因此阈值取在这之间

                                                for (int i = sw; i <= w; i++) {
                                                    for (int j = sh; j <= h - 1; j++) {
                                                        rc[j][i] = Math.abs(smp.getr(j, i) - smp.getr(j + 1, i)) + Math.abs(smp.getg(j, i) - smp.getg(j + 1, i)) + Math.abs(smp.getb(j, i) - smp.getb(j + 1, i));
                                                    }
                                                }
                                                for (int i = sw; i <= w; i++) {
                                                    for (int j = sh; j <= h - 1; j++) {

                                                        if (rc[j][i] > nnum) {
                                                            wmax[i]++;
                                                        }
                                                    }
                                                }
                                                for (int i = sw; i <= w - 1; i++) {
                                                    wwmax[i] = Math.abs(wmax[i] - wmax[i + 1]);
                                                }
                                                for (int i = sw; i <= w - 1; i++) {
                                                    if (wwmax[i] > wwmax[max]) max = i;
                                                }
                                                for (int i = sw; i <= w - 1; i++) {
                                                    if (wwmax[i] > wwmax[max2] && i != max) {
                                                        max2 = i;
                                                    }
                                                }
                                                if (max2 < max) {
                                                    int t;
                                                    t = max;
                                                    max = max2;
                                                    max2 = t;
                                                }
                                                max++;

                                                for (int i = sw; i <= w - 1; i++) {
                                                    for (int j = sh; j <= h; j++) {
                                                        rc2[j][i] = Math.abs(smp.getr(j, i) - smp.getr(j, i + 1)) + Math.abs(smp.getg(j, i) - smp.getg(j, i + 1)) + Math.abs(smp.getb(j, i) - smp.getb(j, i + 1));
                                                    }
                                                }
                                                for (int i = sw; i <= w - 1; i++) {
                                                    for (int j = sh; j <= h; j++) {
                                                        if (rc2[j][i] > nnum) hmax[j]++;
                                                    }
                                                }
                                                for (int i = sh; i <= h - 1; i++) {
                                                    hhmax[i] = Math.abs(hmax[i] - hmax[i + 1]);
                                                }
                                                for (int i = sh; i <= h - 1; i++) {
                                                    if (hhmax[i] > hhmax[max3]) max3 = i;
                                                }
                                                for (int i = sh; i <= h - 1; i++) {
                                                    if (hhmax[i] > hhmax[max4] && i != max3) {
                                                        max4 = i;
                                                    }
                                                }
                                                if (max4 < max3) {
                                                    int t;
                                                    t = max3;
                                                    max3 = max4;
                                                    max4 = t;
                                                }
                                                max3++;
                                                smp.rep();
                                                s1.x = max;
                                                s1.y = max3;
                                                s2.x = max2 - max + 1;
                                                s2.y = max4 - max3 + 1;
                                                long sumrc1 = 0;
                                                int sumpixel = 0;
                                                for (int i = s1.x; i <= s1.x + s2.x; i++) {
                                                    for (int j = s1.y; j <= s1.y + s2.y - 1; j++) {
                                                        sumrc1 += rc[j][i];
                                                        sumpixel++;
                                                    }
                                                }
                                                for (int i = s1.x; i <= s1.x + s2.x - 1; i++) {
                                                    for (int j = s1.y; j <= s1.y + s2.y; j++) {
                                                        sumrc1 += rc2[j][i];
                                                    }
                                                }

                                                if (sumpixel == 0) sumpixel = 1;
                                                perrc = (int) (sumrc1 / (sumpixel * 2));
                                                if (perrc < 200) {
                                                    w = s2.x + s1.x+2;
                                                    h = s2.y + s1.y+2;
                                                    sw = s1.x-1;
                                                    sh = s1.y-1;
                                                    max = sw;
                                                    max2 = sw;
                                                    max3 = sh;
                                                    max4 = sh;
                                                    num_try++;
                                                    problem_num=1;
                                                    System.out.println("low");
                                                    if (xa_1 == s1.x && xa_2 == s2.x && ya_1 == s1.y && ya_2 == s2.y) {
                                                        //sum_true++;
                                                        System.out.println("useless");
                                                    }

                                                }else {
                                                    break;
                                                }

                                            }



                                            Canvas canvas = new Canvas(bitmap1);
                                            Paint paint = new Paint();
                                            paint.setColor(Color.RED);
                                            paint.setStyle(Paint.Style.STROKE);//不填充
                                            paint.setStrokeWidth(5);  //线的宽度
                                            canvas.drawRect(s1.x, s1.y, s2.x + s1.x, s2.y + s1.y, paint);
                                            //photoView.setImageBitmap(bitmap1);


                                            if (xa_1 == s1.x && xa_2 == s2.x && ya_1 == s1.y && ya_2 == s2.y) {
                                                //sum_true++;
                                                    if (problem_num==1)System.out.println("useful");
                                            }
                                            else {

                                                {
                                                    final ImageView imageView = new ImageView(mainActivity);
                                                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                                    imageView.setBackgroundResource(R.drawable.image_slect);
                                                    imageView.setAdjustViewBounds(true);
                                                    imageView.setLayoutParams(imageView_example.getLayoutParams());
                                                    imageView.setPadding(imageView_example.getPaddingLeft(), imageView_example.getPaddingTop(), imageView_example.getPaddingRight(), imageView_example.getPaddingBottom());
                                                    linearLayout_imagelist.addView(imageView);
                                                    imageView.setImageBitmap(bitmap1);
                                                    imageView.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            System.out.println(linearLayout_imagelist.indexOfChild(imageView));
                                                            select_num_image = linearLayout_imagelist.indexOfChild(imageView);
                                                            imageView_main.setImageBitmap(((BitmapDrawable) imageView.getDrawable()).getBitmap());
                                                            imageView_example.setImageDrawable(imageView_main.getDrawable());
                                                            show_picture();
                                                        }
                                                    });
                                                    imageView.setOnLongClickListener(new View.OnLongClickListener() {
                                                        @Override
                                                        public boolean onLongClick(View v) {
                                                            dialog = new Dialog(MainActivity.mainActivity);
                                                            dialog.setContentView(R.layout.dia_layout);//dialog布局
                                                            WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();//获取dialog布局的参数
                                                            dialog.getWindow().setBackgroundDrawableResource(R.color.color_trans);
                                                            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;//全屏
                                                            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;//全屏
                                                            dialog.getWindow().setAttributes(layoutParams);
                                                            dialog.show();
                                                            vibrator.vibrate(100);
                                                            dialog.getWindow().findViewById(R.id.button_change_false).setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    dialog.dismiss();
                                                                }
                                                            });
                                                            dialog.getWindow().findViewById(R.id.button_change_true).setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    linearLayout_imagelist.removeView(imageView);
                                                                    imageView_main.setImageDrawable(null);
                                                                    num_sum--;
                                                                    select_num_image = 0;
                                                                    show_picture();
                                                                    dialog.dismiss();
                                                                }
                                                            });
                                                            dialog.getWindow().findViewById(R.id.dia_button_3).setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    for (int i = 1; i <= num_sum; i++) {
                                                                        linearLayout_imagelist.removeView(linearLayout_imagelist.getChildAt(1));
                                                                    }
                                                                    imageView_main.setImageDrawable(null);
                                                                    num_sum = 0;
                                                                    select_num_image = 0;
                                                                    show_picture();
                                                                    dialog.dismiss();
                                                                }
                                                            });


                                                            return false;
                                                        }
                                                    });
                                                    num_sum++;
                                                    select_num_image = num_sum;
                                                    //imageView_main.setImageBitmap(bitmap);
                                                }





                                                sum_true--;
                                            }

                                    } catch (Exception e) {
                                   // sum_problem++;

                                }



                                        //
                                    }


                                    average+=sum_true ;
                                    System.out.println("[" + image_num + "]" + numi + ":" + sum_true + "problem:"+"\n");



                            } Toast.makeText(mainActivity, String.valueOf(average/num_now), Toast.LENGTH_SHORT).show();
                            System.out.println(average/num_now);


                        }


                    });

*/
                }




            }


        });
        button_start.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (cson) {
                    if (imageView_main.getDrawable() != null) {
                        if (setting_1.default_vibrator) vibrator.vibrate(100);
                        progressBar.setVisibility(View.VISIBLE);
                        textView_show_time.setVisibility(View.GONE);
                        time_spend_1.start_timering();
                        cs = true;
                        new Thread() {
                            @Override
                            public void run() {
                                while (cs == true) {
                                    try {
                                        Thread.sleep(10);
                                    }catch (Exception e){

                                    }

                                    switch (model) {
                                        case 0:
                                            if (rect_image == false) {

                                                jm jm_1 = new jm();
                                                jm_1.getraw(imageView_main.getDrawable());
                                                jm_1.runp(editText_key.getText().toString());
                                                Bitmap bitmap = jm_1.setnew();
                                                bitmap_trans = bitmap;
                                                handler.sendEmptyMessage(2);//通知主线程更新控件
                                                handler.sendEmptyMessage(3);//通知主线程更新控件
                                                //imageView_main.setImageBitmap(bitmap);//
                                                //((ImageView) linearLayout_imagelist.getChildAt(select_num_image)).setImageBitmap(bitmap);//

                                            } else {
                                                jm jm_1 = new jm();
                                                jm_1.getraw2(imageView_main.getDrawable(), s1, s2);
                                                jm_1.runp(editText_key.getText().toString());
                                                Bitmap bitmap = jm_1.setnew2(s1, s2);
                                                bitmap_trans = bitmap;
                                                handler.sendEmptyMessage(2);//通知主线程更新控件
                                                handler.sendEmptyMessage(3);//通知主线程更新控件
                                                // imageView_main.setImageBitmap(bitmap);//
                                                //((ImageView) linearLayout_imagelist.getChildAt(select_num_image)).setImageBitmap(bitmap);//
                                            }
                                            break;
                                        case 1:

                                            for (int i = 1; i <= num_sum; i++) {
                                                jm jm_1 = new jm();
                                                jm_1.getraw(((ImageView) linearLayout_imagelist.getChildAt(i)).getDrawable());
                                                jm_1.runp(editText_key.getText().toString());
                                                Bitmap bitmap = jm_1.setnew();
                                                bitmap_trans = bitmap;
                                                i_trans = i;
                                                handler.sendEmptyMessage(2);//通知主线程更新控件
                                                handler.sendEmptyMessage(4);//通知主线程更新控件
                                                select_num_image = i;
                                                //imageView_main.setImageBitmap(bitmap);
                                                //((ImageView) linearLayout_imagelist.getChildAt(i)).setImageBitmap(bitmap);
                                            }
                                            break;
                                        case 2:
                                            if (miyaotu > 0 && miyaotu <= num_sum) {
                                                double q;
                                                try {
                                                    q = Double.valueOf(editText_key.getText().toString());
                                                    if (q <= 0 || q > 1) {
                                                        q = 1.0;
                                                        handler.sendEmptyMessage(8);//通知主线程更新控件
                                                    }
                                                } catch (Exception e) {
                                                    q = 1.0;
                                                    handler.sendEmptyMessage(8);//通知主线程更新控件
                                                }

                                                bitmap_process bitmap_process_my = new bitmap_process();
                                                bitmap_process bitmap_process_my_raw = new bitmap_process();
                                                bitmap_process_my.rewrite(bitmap_process_my.getBitmap(imageView_main.getDrawable()));
                                                Bitmap newBitmap = Bitmap.createScaledBitmap(((BitmapDrawable) ((ImageView) linearLayout_imagelist.getChildAt(miyaotu)).getDrawable()).getBitmap(), bitmap_process_my.width, bitmap_process_my.height, true);
                                                bitmap_process_my_raw.rewrite(newBitmap);
                                                int w = bitmap_process_my.width;
                                                int h = bitmap_process_my.height;
                                                for (int i = 1; i <= h; i++) {
                                                    for (int j = 1; j <= w; j++) {
                                                        bitmap_process_my.setr(i, j, range(bitmap_process_my.getr(i, j) * q + bitmap_process_my_raw.getr(i, j) * (1 - q)));
                                                        bitmap_process_my.setg(i, j, range(bitmap_process_my.getg(i, j) * q + bitmap_process_my_raw.getg(i, j) * (1 - q)));
                                                        bitmap_process_my.setb(i, j, range(bitmap_process_my.getb(i, j) * q + bitmap_process_my_raw.getb(i, j) * (1 - q)));
                                                    }
                                                }
                                                bitmap_trans = bitmap_process_my.rep();
                                                handler.sendEmptyMessage(2);//通知主线程更新控件
                                                handler.sendEmptyMessage(3);//通知主线程更新控件

                                            } else {
                                                handler.sendEmptyMessage(9);//通知主线程更新控件
                                            }
                                            break;
                                        case -1:
                                            String s1 = editText_key.getText().toString();
                                            String s = "";
                                            int l = s1.length();
                                            char[] arr = s1.toCharArray();
                                            int cat = 0;
                                            String[] n = new String[5];
                                            for (int i = 0; i <= 4; i++) {
                                                n[i] = "";
                                            }
                                            int confirm = 0;
                                            for (int i = 0; i <= l - 1; i++) {
                                                switch (arr[i]) {
                                                    case '[':
                                                        cat = 0;
                                                        confirm++;
                                                        break;
                                                    case ']':
                                                        cat++;
                                                        confirm++;
                                                        break;
                                                    case ',':
                                                        cat++;
                                                        confirm++;
                                                        break;
                                                    default:
                                                        if (arr[i] >= '0' && arr[i] <= '9') {
                                                            n[cat] += arr[i];
                                                        }
                                                        break;
                                                }
                                            }
                                            Boolean could_do = false;
                                            if (confirm == 5) {
                                                could_do = true;
                                                s = n[4];
                                            } else if (confirm == 0) {
                                                could_do = true;
                                                s = editText_key.getText().toString();
                                            } else {
                                                handler.sendEmptyMessage(6);//通知主线程更新控件
                                            }

                                            if (could_do == true) {
                                                try {
                                                    jmjpg jmjpg1 = new jmjpg();
                                                    jmjpg1.getraw1(getBitmap(imageView_main.getDrawable()), Integer.parseInt(editText_side_length.getText().toString()), Integer.parseInt(editText_out_length.getText().toString()));
                                                    jmjpg1.jm(s);

                                                    Bitmap bitmap = jmjpg1.getnew();
                                                    bitmap_trans = bitmap;
                                                    handler.sendEmptyMessage(2);//通知主线程更新控件
                                                    handler.sendEmptyMessage(3);//通知主线程更新控件
                                                    //imageView_main.setImageBitmap(bitmap);
                                                    s_trans = "[" + bitmap.getWidth() + "," + bitmap.getHeight() + "," + editText_side_length.getText().toString() + "," + editText_out_length.getText().toString() + "]" + s;
                                                    handler.sendEmptyMessage(5);//通知主线程更新控件
                                                } catch (Exception e) {

                                                }
                                                //editText_key.setText("[" + bitmap.getWidth() + "," + bitmap.getHeight() + "," + editText_side_length.getText().toString() + "," + editText_out_length.getText().toString() + "]" + s);
                                                // ((ImageView) linearLayout_imagelist.getChildAt(select_num_image)).setImageBitmap(bitmap);
                                            }
                                            break;
                                        default:
                                    }
                                }

                                handler.sendEmptyMessage(1);//通知主线程更新控件
                            }
                        }.start();

                    } else {
                        final Toast toast_1 = Toast.makeText(MainActivity.this, "请导入或选择图片", Toast.LENGTH_SHORT);
                        toast_1.show();
                    }
                }
                return false;
            }
        });

        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cs=false;
                if (imageView_main.getDrawable() != null && could(imageView_main.getDrawable())) {
                    if (setting_1.default_vibrator) vibrator.vibrate(100);
                    progressBar.setVisibility(View.VISIBLE);
                    textView_show_time.setVisibility(View.GONE);
                    time_spend_1.start_timering();


                    new Thread() {
                        @Override
                        public void run() {

                            switch (model) {
                                case 0:
                                    if (rect_image == false) {

                                        jm jm_1 = new jm();
                                        jm_1.getraw(imageView_main.getDrawable());
                                        jm_1.runp(editText_key.getText().toString());
                                        Bitmap bitmap = jm_1.setnew();
                                        bitmap_trans = bitmap;
                                        handler.sendEmptyMessage(2);//通知主线程更新控件
                                        handler.sendEmptyMessage(3);//通知主线程更新控件
                                        //imageView_main.setImageBitmap(bitmap);//
                                        //((ImageView) linearLayout_imagelist.getChildAt(select_num_image)).setImageBitmap(bitmap);//

                                    } else {
                                        jm jm_1 = new jm();
                                        jm_1.getraw2(imageView_main.getDrawable(), s1, s2);
                                        jm_1.runp(editText_key.getText().toString());
                                        Bitmap bitmap = jm_1.setnew2(s1, s2);
                                        if (setting_1.default_sw==true){
                                            bitmap_trans = sw(bitmap,s1,s2);
                                        }else {
                                            bitmap_trans = bitmap;
                                        }


                                        handler.sendEmptyMessage(2);//通知主线程更新控件
                                        handler.sendEmptyMessage(3);//通知主线程更新控件
                                        // imageView_main.setImageBitmap(bitmap);//
                                        //((ImageView) linearLayout_imagelist.getChildAt(select_num_image)).setImageBitmap(bitmap);//
                                    }
                                    break;
                                case 1:

                                    for (int i = 1; i <= num_sum; i++) {
                                        jm jm_1 = new jm();
                                        jm_1.getraw(((ImageView) linearLayout_imagelist.getChildAt(i)).getDrawable());
                                        jm_1.runp(editText_key.getText().toString());
                                        Bitmap bitmap = jm_1.setnew();
                                        bitmap_trans = bitmap;
                                        i_trans = i;
                                        handler.sendEmptyMessage(2);//通知主线程更新控件
                                        handler.sendEmptyMessage(4);//通知主线程更新控件
                                        select_num_image=i;
                                        //imageView_main.setImageBitmap(bitmap);
                                        //((ImageView) linearLayout_imagelist.getChildAt(i)).setImageBitmap(bitmap);
                                    }
                                    break;
                                case 2:
                                    if(miyaotu>0&&miyaotu<=num_sum){
                                        double q;
                                        try {
                                            q= Double.valueOf(editText_key.getText().toString());
                                            if (q<=0||q>1){
                                                q=1.0;
                                                handler.sendEmptyMessage(8);//通知主线程更新控件
                                            }
                                        }catch (Exception e){
                                            q=1.0;
                                            handler.sendEmptyMessage(8);//通知主线程更新控件
                                        }

                                        bitmap_process bitmap_process_my=new bitmap_process();
                                        bitmap_process bitmap_process_my_raw=new bitmap_process();
                                        bitmap_process_my.rewrite(bitmap_process_my.getBitmap(imageView_main.getDrawable()));
                                        Bitmap newBitmap = Bitmap.createScaledBitmap(((BitmapDrawable)((ImageView) linearLayout_imagelist.getChildAt(miyaotu)).getDrawable()).getBitmap(),bitmap_process_my.width , bitmap_process_my.height, true);
                                        bitmap_process_my_raw.rewrite(newBitmap);
                                        int w=bitmap_process_my.width;
                                        int h=bitmap_process_my.height;
                                        for (int i=1;i<=h;i++){
                                            for (int j=1;j<=w;j++){
                                                bitmap_process_my.setr(i,j,range(bitmap_process_my.getr(i,j)*q+bitmap_process_my_raw.getr(i,j)*(1-q)));
                                                bitmap_process_my.setg(i,j,range(bitmap_process_my.getg(i,j)*q+bitmap_process_my_raw.getg(i,j)*(1-q)));
                                                bitmap_process_my.setb(i,j,range(bitmap_process_my.getb(i,j)*q+bitmap_process_my_raw.getb(i,j)*(1-q)));
                                            }
                                        }
                                        bitmap_trans = bitmap_process_my.rep();
                                        handler.sendEmptyMessage(2);//通知主线程更新控件
                                        handler.sendEmptyMessage(3);//通知主线程更新控件

                                    }else {
                                        handler.sendEmptyMessage(9);//通知主线程更新控件
                                    }
                                    break;
                                case -1:
                                    String s1 = editText_key.getText().toString();
                                    String s = "";
                                    int l = s1.length();
                                    char[] arr = s1.toCharArray();
                                    int cat = 0;
                                    String[] n = new String[5];
                                    for (int i = 0; i <= 4; i++) {
                                        n[i] = "";
                                    }
                                    int confirm = 0;
                                    for (int i = 0; i <= l - 1; i++) {
                                        switch (arr[i]) {
                                            case '[':
                                                cat = 0;
                                                confirm++;
                                                break;
                                            case ']':
                                                cat++;
                                                confirm++;
                                                break;
                                            case ',':
                                                cat++;
                                                confirm++;
                                                break;
                                            default:
                                                if (arr[i] >= '0' && arr[i] <= '9') {
                                                    n[cat] += arr[i];
                                                }
                                                break;
                                        }
                                    }
                                    Boolean could_do = false;
                                    if (confirm == 5) {
                                        could_do = true;
                                        s = n[4];
                                    } else if (confirm == 0) {
                                        could_do = true;
                                        s = editText_key.getText().toString();
                                    } else {
                                        handler.sendEmptyMessage(6);//通知主线程更新控件
                                    }

                                    if (could_do == true) {
                                        try {
                                            jmjpg jmjpg1 = new jmjpg();
                                            jmjpg1.getraw1(getBitmap(imageView_main.getDrawable()), Integer.parseInt(editText_side_length.getText().toString()), Integer.parseInt(editText_out_length.getText().toString()));
                                            jmjpg1.jm(s);

                                            Bitmap bitmap = jmjpg1.getnew();
                                            bitmap_trans = bitmap;
                                            handler.sendEmptyMessage(2);//通知主线程更新控件
                                            handler.sendEmptyMessage(3);//通知主线程更新控件

                                            s_trans = "[" + bitmap.getWidth() + "," + bitmap.getHeight() + "," + editText_side_length.getText().toString() + "," + editText_out_length.getText().toString() + "]" + s;
                                            handler.sendEmptyMessage(5);//通知主线程更新控件
                                        }catch (Exception e){

                                        }
                                    }
                                    break;
                                default:
                            }
                            handler.sendEmptyMessage(1);//通知主线程更新控件
                        }
                    }.start();

                }else {
                    final Toast toast_1 = Toast.makeText(MainActivity.this, "无图片或图片尺寸不合适", Toast.LENGTH_SHORT);
                    toast_1.show();
                }
            }
        });
        button_start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView_main.getDrawable() != null&& could(imageView_main.getDrawable())) {
                    if (setting_1.default_vibrator) vibrator.vibrate(100);
                    progressBar.setVisibility(View.VISIBLE);
                    textView_show_time.setVisibility(View.GONE);
                    time_spend_1.start_timering();
                    new Thread() {
                        @Override
                        public void run() {
                            switch (model) {
                                case 0:
                                    if (rect_image == false) {
                                        jm jm_1 = new jm();
                                        jm_1.getraw(imageView_main.getDrawable());
                                        jm_1.runq(editText_key.getText().toString());
                                        Bitmap bitmap = jm_1.setnew();
                                        bitmap_trans = bitmap;
                                        handler.sendEmptyMessage(2);//通知主线程更新控件
                                        handler.sendEmptyMessage(3);//通知主线程更新控件
                                    } else {
                                        jm jm_1 = new jm();
                                        Bitmap bitmap=getBitmap(imageView_main.getDrawable());
                                        if (setting_1.default_sw==true){
                                            bitmap_trans = sw(bitmap,s1,s2);
                                        }else {
                                            bitmap_trans = bitmap;
                                        }
                                        jm_1.getraw2(bitmap, s1, s2);
                                        jm_1.runq(editText_key.getText().toString());
                                        bitmap = jm_1.setnew2(s1, s2);

                                        bitmap_trans = bitmap;
                                        handler.sendEmptyMessage(2);//通知主线程更新控件
                                        handler.sendEmptyMessage(3);//通知主线程更新控件
                                    }
                                    break;
                                case 1:
                                    for (int i = 1; i <= num_sum; i++) {
                                        jm jm_1 = new jm();
                                        jm_1.getraw(((ImageView) linearLayout_imagelist.getChildAt(i)).getDrawable());
                                        jm_1.runq(editText_key.getText().toString());
                                        Bitmap bitmap = jm_1.setnew();
                                        bitmap_trans = bitmap;
                                        i_trans = i;
                                        handler.sendEmptyMessage(2);//通知主线程更新控件
                                        handler.sendEmptyMessage(4);//通知主线程更新控件
                                        select_num_image=i;
                                    }
                                    break;
                                case 2:
                                    if(miyaotu>0&&miyaotu<=num_sum){
                                        double q;
                                        try {
                                            q= Double.valueOf(editText_key.getText().toString());
                                            if (q<=0||q>1) {
                                                q = 1.0;
                                                handler.sendEmptyMessage(8);//通知主线程更新控件
                                            }//                                            }
                                        }catch (Exception e) {
                                            q = 1.0;
                                            handler.sendEmptyMessage(8);//通知主线程更新控件
                                        }//                                        }
                                        bitmap_process bitmap_process_my=new bitmap_process();
                                        bitmap_process bitmap_process_my_raw=new bitmap_process();
                                        bitmap_process_my.rewrite(bitmap_process_my.getBitmap(imageView_main.getDrawable()));
                                        Bitmap newBitmap = Bitmap.createScaledBitmap(((BitmapDrawable)((ImageView) linearLayout_imagelist.getChildAt(miyaotu)).getDrawable()).getBitmap(),bitmap_process_my.width , bitmap_process_my.height, true);
                                        bitmap_process_my_raw.rewrite(newBitmap);
                                        int w=bitmap_process_my.width;
                                        int h=bitmap_process_my.height;
                                        for (int i=1;i<=h;i++){
                                            for (int j=1;j<=w;j++){
                                                bitmap_process_my.setr(i,j,range((bitmap_process_my.getr(i,j)-bitmap_process_my_raw.getr(i,j)*(1-q))/q));
                                                bitmap_process_my.setg(i,j,range((bitmap_process_my.getg(i,j)-bitmap_process_my_raw.getg(i,j)*(1-q))/q));
                                                bitmap_process_my.setb(i,j,range((bitmap_process_my.getb(i,j)-bitmap_process_my_raw.getb(i,j)*(1-q))/q));
                                            }
                                        }
                                        bitmap_trans = bitmap_process_my.rep();
                                        handler.sendEmptyMessage(2);//通知主线程更新控件
                                        handler.sendEmptyMessage(3);//通知主线程更新控件

                                    }else {
                                        handler.sendEmptyMessage(9);//通知主线程更新控件
                                    }
                                    break;
                                case -1:
                                    String s1 = editText_key.getText().toString();
                                    int l = s1.length();
                                    char[] arr = s1.toCharArray();
                                    int cat = 0;
                                    String[] n = new String[5];
                                    for (int i = 0; i <= 4; i++) {
                                        n[i] = "";
                                    }
                                    int confirm = 0;
                                    for (int i = 0; i <= l - 1; i++) {
                                        switch (arr[i]) {
                                            case '[':
                                                cat = 0;
                                                confirm++;
                                                break;
                                            case ']':
                                                cat++;
                                                confirm++;
                                                break;
                                            case ',':
                                                cat++;
                                                confirm++;
                                                break;
                                            default:
                                                if (arr[i] >= '0' && arr[i] <= '9') {
                                                    n[cat] += arr[i];
                                                }
                                                break;
                                        }
                                    }
                                    if (confirm == 5) {
                                        jmjpg jmjpg1 = new jmjpg();
                                        Bitmap bitmap = ((BitmapDrawable) imageView_main.getDrawable()).getBitmap();
                                        Bitmap bitmap1;
                                        if (bitmap.getWidth() != Integer.parseInt(n[0]) || bitmap.getHeight() != Integer.parseInt(n[1])) {
                                            bitmap1 = jmjpg1.cgsize(bitmap, Integer.parseInt(n[0]), Integer.parseInt(n[1]));
                                            jmjpg1.getraw2(bitmap1, Integer.parseInt(n[2]), Integer.parseInt(n[3]));
                                        } else {
                                            jmjpg1.getraw2(bitmap, Integer.parseInt(n[2]), Integer.parseInt(n[3]));
                                        }
                                        jmjpg1.jm2(n[4]);
                                        bitmap_trans = jmjpg1.getnew2();
                                        handler.sendEmptyMessage(2);//通知主线程更新控件
                                        handler.sendEmptyMessage(3);//通知主线程更新控件
                                    } else if (confirm == 0) {
                                            jmjpg jmjpg1 = new jmjpg();
                                            Bitmap bitmap = ((BitmapDrawable) imageView_main.getDrawable()).getBitmap();
                                            jmjpg1.getraw2(bitmap, Integer.parseInt(editText_side_length.getText().toString()), Integer.parseInt(editText_out_length.getText().toString()));
                                            jmjpg1.jm2(editText_key.getText().toString());
                                            bitmap = jmjpg1.getnew2();
                                            bitmap_trans = bitmap;
                                            handler.sendEmptyMessage(2);//通知主线程更新控件
                                            handler.sendEmptyMessage(3);//通知主线程更新控件
                                         } else {
                                        handler.sendEmptyMessage(6);//通知主线程更新控件
                                    }
                                    break;
                                default:
                            }
                            handler.sendEmptyMessage(1);//通知主线程更新控件
                        }
                    }.start();
                }else {
                    final Toast toast_1 = Toast.makeText(MainActivity.this, "无图片或图片尺寸不合适", Toast.LENGTH_SHORT);
                    toast_1.show();
                }
            }
        });

        imageButton_cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = null;
                try {
                    bitmap = ((BitmapDrawable) (imageView_main.getDrawable())).getBitmap();
                } catch (Exception e) {

                }
                dialog = new Dialog(MainActivity.mainActivity);
                dialog.setContentView(R.layout.image_cut1);//dialog布局
                final ImageView imageView =dialog.getWindow().findViewById(R.id.imageView);
                WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();//获取dialog布局的参数
                dialog.getWindow().setBackgroundDrawableResource(R.color.color_trans);
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;//全屏
                layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;//全屏
                dialog.getWindow().setAttributes(layoutParams);
                dialog.show();
                {

                    TranslateAnimation ta = new TranslateAnimation(0,0,100,0);
                    // 设置动画时长
                    ta.setDuration(250);
                    // 启动动画
                    dialog.getWindow().findViewById(R.id.linearLayout).startAnimation(ta);
                }
                {
                dialog.getWindow().findViewById(R.id.imageButton11).setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        dialog.getWindow().findViewById(R.id.imageButton11).getParent().requestDisallowInterceptTouchEvent(true);
                        ConstraintLayout constraintLayout = dialog.getWindow().findViewById(R.id.con_1);
                        ImageView imageView = dialog.getWindow().findViewById(R.id.imageView);
                        ImageButton imageButton = dialog.getWindow().findViewById(R.id.imageButton11);
                        int mouseaction = event.getAction();
                        switch (mouseaction) {
                            case MotionEvent.ACTION_DOWN:
                                rh = (int) constraintLayout.getBottom();
                                rw = (int) constraintLayout.getRight();
                                //rw=(int)constraintLayout.getWidth();
                                //lx=(int)constraintLayout.getX();
                                //ly=(int)constraintLayout.getY();
                                lw = (int) event.getRawX();
                                lh = (int) event.getRawY();
                                ly = (int) imageButton.getY();
                                lx = (int) imageButton.getX();
                                break;
                            case MotionEvent.ACTION_MOVE:

                                //System.out.println(rh + (int) event.getRawY() - lh);
                                if (imageButton.getHeight()<=rh + (int) event.getRawY() - lh-constraintLayout.getTop()&&rh + (int) event.getRawY() - lh<=imageView.getHeight()) {
                                    constraintLayout.setBottom(rh + (int) event.getRawY() - lh);
                                    imageButton.setY(ly + (int) event.getRawY() - lh);
                                }else if(rh + (int) event.getRawY() - lh-constraintLayout.getTop()<imageButton.getHeight()){
                                    constraintLayout.setBottom(constraintLayout.getTop()+imageButton.getHeight());
                                    imageButton.setY(0);
                                }
                                else {
                                    constraintLayout.setBottom(imageView.getHeight());
                                    imageButton.setY(constraintLayout.getHeight()-imageButton.getHeight());

                                }
                                if (rw + (int) event.getRawX() - lw<=imageView.getWidth()&&rw + (int) event.getRawX() - lw-constraintLayout.getLeft()>=imageButton.getWidth()) {
                                    constraintLayout.setRight(rw + (int) event.getRawX() - lw);

                                    imageButton.setX(lx + (int) event.getRawX() - lw);
                                }else if(rw + (int) event.getRawX() - lw-constraintLayout.getLeft()<imageButton.getWidth()){
                                    constraintLayout.setRight(imageButton.getWidth()+constraintLayout.getLeft());
                                    imageButton.setX(0);
                                }
                                else {
                                    constraintLayout.setRight(imageView.getWidth());
                                    imageButton.setX(constraintLayout.getWidth()-imageButton.getWidth());
                                }

                               // System.out.println(constraintLayout.getHeight());
                                break;
                            case MotionEvent.ACTION_UP:
                                break;
                        }
                        return false;
                    }
                });
            }
                {
                    dialog.getWindow().findViewById(R.id.con_1).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            ImageView imageView = dialog.getWindow().findViewById(R.id.imageView);
                            ConstraintLayout constraintLayout = dialog.getWindow().findViewById(R.id.con_1);
                            if (constraintLayout.getTop()!=0||constraintLayout.getBottom()!=imageView.getHeight()){
                                constraintLayout.getParent().requestDisallowInterceptTouchEvent(true);
                            }
                            ImageButton imageButton=dialog.getWindow().findViewById(R.id.imageButton11);
                            int mouseaction = event.getAction();
                            switch (mouseaction) {
                                case MotionEvent.ACTION_DOWN:
                                    ly = (int) event.getRawY();
                                    lx = (int) event.getRawX();
                                    rx = (int) constraintLayout.getLeft();
                                    rw=(int)constraintLayout.getWidth();
                                    rh=(int)constraintLayout.getHeight();
                                    ry = (int) constraintLayout.getTop();
                                    break;
                                case MotionEvent.ACTION_MOVE:
                                    if (rx + (int) event.getRawX() - lx >= 0 && rx + (int) event.getRawX() - lx + constraintLayout.getWidth() <= imageView.getWidth()) {
                                        constraintLayout.setLeft(rx + (int) event.getRawX() - lx);
                                    } else if (rx + (int) event.getRawX() - lx < 0) {
                                        constraintLayout.setLeft(0);
                                    } else if (rx + (int) event.getRawX() - lx + constraintLayout.getWidth() > imageView.getWidth()) {
                                        constraintLayout.setLeft(imageView.getWidth() - constraintLayout.getWidth());
                                    }
                                    if (ry + (int) event.getRawY() - ly >= 0 && ry + (int) event.getRawY() - ly + constraintLayout.getHeight() <= imageView.getHeight()) {
                                        constraintLayout.setTop(ry + (int) event.getRawY() - ly);
                                    } else if (ry + (int) event.getRawY() - ly < 0) {
                                        constraintLayout.setTop(0);
                                    } else if (ry + (int) event.getRawY() - ly + constraintLayout.getHeight() > imageView.getHeight()) {
                                        constraintLayout.setTop(imageView.getHeight() - constraintLayout.getHeight());
                                    }
                                    constraintLayout.setBottom(constraintLayout.getTop()+rh);
                                    constraintLayout.setRight(constraintLayout.getLeft()+rw);
                                    imageButton.setX(constraintLayout.getWidth()-imageButton.getWidth());
                                    imageButton.setY(constraintLayout.getHeight()-imageButton.getHeight());
                                    break;
                                case MotionEvent.ACTION_UP:
                                    break;
                            }
                            return false;
                        }
                    });
                }

                dialog.getWindow().findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.getWindow().findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            ConstraintLayout constraintLayout = dialog.getWindow().findViewById(R.id.con_1);
                            ImageView imageView = dialog.getWindow().findViewById(R.id.imageView);
                            Point lefttop = new Point((int) constraintLayout.getLeft(), (int) constraintLayout.getTop());
                            Point rightbottom = new Point((int) constraintLayout.getWidth(), (int) constraintLayout.getHeight());
                            Point lefttop_image = onSingleTap(lefttop, imageView);
                            Point rightbottom_image = onSingleTap(rightbottom, imageView);
                            Bitmap bitmap = Bitmap.createBitmap(((BitmapDrawable) imageView.getDrawable()).getBitmap(), lefttop_image.x, lefttop_image.y, rightbottom_image.x, rightbottom_image.y);
                            imageView.setImageBitmap(bitmap);
                            ImageButton imageButton = dialog.getWindow().findViewById(R.id.imageButton11);
                            imageButton.setX(imageView.getWidth() - imageButton.getWidth());
                            imageButton.setY(imageView.getHeight() - imageButton.getHeight());
                        }catch (Exception e){
                            Toast.makeText(MainActivity.this, "裁剪过小", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.getWindow().findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ConstraintLayout constraintLayout = dialog.getWindow().findViewById(R.id.con_1);
                        constraintLayout.setLeft(0);
                        constraintLayout.setTop(0);
                        constraintLayout.setRight(imageView.getWidth());
                        constraintLayout.setBottom(imageView.getHeight());
                        ImageButton imageButton=dialog.getWindow().findViewById(R.id.imageButton11);
                        imageView.setImageDrawable(imageView_main.getDrawable());
                        imageButton.setX(imageView.getWidth()-imageButton.getWidth());
                        imageButton.setY(imageView.getHeight()-imageButton.getHeight());
                    }
                });

                dialog.getWindow().findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            ConstraintLayout constraintLayout = dialog.getWindow().findViewById(R.id.con_1);
                            ImageView imageView = dialog.getWindow().findViewById(R.id.imageView);
                            Point lefttop = new Point((int) constraintLayout.getX(), (int) constraintLayout.getY());
                            Point rightbottom = new Point((int) constraintLayout.getWidth(), (int) constraintLayout.getHeight());
                            Point lefttop_image = onSingleTap(lefttop, imageView);
                            Point rightbottom_image = onSingleTap(rightbottom, imageView);
                            Bitmap bitmap = Bitmap.createBitmap(((BitmapDrawable) imageView.getDrawable()).getBitmap(), lefttop_image.x, lefttop_image.y, rightbottom_image.x, rightbottom_image.y);
                            imageView_main.setImageBitmap(bitmap);
                            ((ImageView) linearLayout_imagelist.getChildAt(select_num_image)).setImageBitmap(bitmap);
                            dialog.dismiss();
                        }catch (Exception e){
                         Toast.makeText(MainActivity.this, "裁剪过小", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                if (bitmap!=null) {
                  imageView.setImageBitmap(bitmap);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        int max_sum=30;
        if (requestCode==2) {
            Uri uri;
            if (data != null) {
                if(num_sum<max_sum){
                ClipData imageNames = data.getClipData();
                System.out.println(imageNames);
                if (imageNames != null) {
                    int count=imageNames.getItemCount();
                    if (count+num_sum>max_sum){
                       count=max_sum-num_sum;
                        Toast.makeText(MainActivity.this, "列表中图片数量已达上限", Toast.LENGTH_SHORT).show();
                    }
                    num_sum +=count;//图片总数增加
                    select_num_image = num_sum;
                    for (int i = 0; i < count; i++) {
                        Uri imageUri = imageNames.getItemAt(i).getUri();
                        ContentResolver cr = this.getContentResolver();
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(imageUri));
                            final ImageView imageView = new ImageView(this);
                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            imageView.setBackgroundResource(R.drawable.image_slect);
                            imageView.setAdjustViewBounds(true);
                            imageView.setLayoutParams(imageView_example.getLayoutParams());
                            imageView.setPadding(imageView_example.getPaddingLeft(), imageView_example.getPaddingTop(), imageView_example.getPaddingRight(), imageView_example.getPaddingBottom());
                            linearLayout_imagelist.addView(imageView);
                            imageView.setImageBitmap(bitmap);
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    System.out.println(linearLayout_imagelist.indexOfChild(imageView));
                                    select_num_image = linearLayout_imagelist.indexOfChild(imageView);
                                    imageView_main.setImageBitmap(((BitmapDrawable) imageView.getDrawable()).getBitmap());
                                    imageView_example.setImageDrawable(imageView_main.getDrawable());
                                    Toast.makeText(MainActivity.this, "一共" + num_sum + "张,这是第" + select_num_image + "张", Toast.LENGTH_SHORT).show();
                                    show_picture();
                                }
                            });
                            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                                @Override
                                public boolean onLongClick(View v) {
                                    dialog = new Dialog(MainActivity.mainActivity);
                                    dialog.setContentView(R.layout.dia_layout);//dialog布局
                                    WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();//获取dialog布局的参数
                                    dialog.getWindow().setBackgroundDrawableResource(R.color.color_trans);
                                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;//全屏
                                    layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;//全屏
                                    dialog.getWindow().setAttributes(layoutParams);
                                    dialog.show();
                                    {

                                        TranslateAnimation ta = new TranslateAnimation(0,0,100,0);
                                        // 设置动画时长
                                        ta.setDuration(250);
                                        // 启动动画
                                        dialog.getWindow().findViewById(R.id.condel).startAnimation(ta);
                                    }
                                    if (setting_1.default_vibrator) vibrator.vibrate(100);
                                    dialog.getWindow().findViewById(R.id.button_change_false).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                        }
                                    });
                                    dialog.getWindow().findViewById(R.id.button_change_true).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            linearLayout_imagelist.removeView(imageView);
                                            imageView_main.setImageDrawable(null);
                                            num_sum--;
                                            select_num_image = 0;
                                            show_picture();
                                            dialog.dismiss();
                                        }
                                    });
                                    dialog.getWindow().findViewById(R.id.dia_button_3).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //linearLayout_imagelist.removeView(imageView);
                                            for (int i = 1; i <= num_sum; i++) {
                                                linearLayout_imagelist.removeView(linearLayout_imagelist.getChildAt(1));
                                            }
                                            //linearLayout_imagelist.removeView(imageView);
                                            imageView_main.setImageDrawable(null);
                                            num_sum = 0;
                                            select_num_image = 0;
                                            show_picture();
                                            dialog.dismiss();
                                        }
                                    });
                                    return false;
                                }
                            });
                        } catch (FileNotFoundException e) {
                            Log.e("Exception", e.getMessage(), e);
                        }
                    }
                    show_picture();
                    imageView_main.setImageDrawable(((ImageView) linearLayout_imagelist.getChildAt(num_sum)).getDrawable());
                    imageView_example.setImageDrawable(((ImageView) linearLayout_imagelist.getChildAt(num_sum)).getDrawable());
                    //uri = imageNames.getItemAt(0).getUri();
                } else {
                    uri = data.getData();
                    {
                        uri = data.getData();
                        Log.e("uri", uri.toString());
                        ContentResolver cr = this.getContentResolver();
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                            final ImageView imageView = new ImageView(this);
                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            imageView.setBackgroundResource(R.drawable.image_slect);
                            imageView.setAdjustViewBounds(true);

                            imageView.setLayoutParams(imageView_example.getLayoutParams());
                            imageView.setPadding(imageView_example.getPaddingLeft(), imageView_example.getPaddingTop(), imageView_example.getPaddingRight(), imageView_example.getPaddingBottom());
                            linearLayout_imagelist.addView(imageView);
                            imageView.setImageBitmap(bitmap);
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    System.out.println(linearLayout_imagelist.indexOfChild(imageView));
                                    select_num_image = linearLayout_imagelist.indexOfChild(imageView);
                                    imageView_main.setImageBitmap(((BitmapDrawable) imageView.getDrawable()).getBitmap());
                                    imageView_example.setImageDrawable(imageView_main.getDrawable());
                                    Toast.makeText(MainActivity.this, "一共" + num_sum + "张,这是第" + select_num_image + "张", Toast.LENGTH_SHORT).show();
                                    show_picture();
                                }
                            });
                            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                                @Override
                                public boolean onLongClick(View v) {
                                    dialog = new Dialog(MainActivity.mainActivity);
                                    dialog.setContentView(R.layout.dia_layout);//dialog布局
                                    WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();//获取dialog布局的参数
                                    dialog.getWindow().setBackgroundDrawableResource(R.color.color_trans);
                                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;//全屏
                                    layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;//全屏
                                    dialog.getWindow().setAttributes(layoutParams);
                                    dialog.show();
                                    {

                                        TranslateAnimation ta = new TranslateAnimation(0,0,100,0);
                                        // 设置动画时长
                                        ta.setDuration(250);
                                        // 启动动画
                                        dialog.getWindow().findViewById(R.id.condel).startAnimation(ta);
                                    }
                                    if (setting_1.default_vibrator) vibrator.vibrate(100);
                                    dialog.getWindow().findViewById(R.id.button_change_false).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                        }
                                    });
                                    dialog.getWindow().findViewById(R.id.button_change_true).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            linearLayout_imagelist.removeView(imageView);
                                            imageView_main.setImageDrawable(null);
                                            num_sum--;
                                            select_num_image = 0;
                                            show_picture();
                                            dialog.dismiss();
                                        }
                                    });
                                    dialog.getWindow().findViewById(R.id.dia_button_3).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            for (int i = 1; i <= num_sum; i++) {
                                                linearLayout_imagelist.removeView(linearLayout_imagelist.getChildAt(1));
                                            }
                                            imageView_main.setImageDrawable(null);
                                            num_sum = 0;
                                            select_num_image = 0;
                                            show_picture();
                                            dialog.dismiss();
                                        }
                                    });
                                    return false;
                                }
                            });
                            num_sum++;
                            select_num_image = num_sum;
                            imageView_main.setImageBitmap(bitmap);

                        } catch (FileNotFoundException e) {
                            Log.e("Exception", e.getMessage(), e);
                        }
                    }
                    imageView_example.setImageDrawable(imageView_main.getDrawable());
                }
            }else {
                    Toast.makeText(MainActivity.this, "列表中图片数量已达上限", Toast.LENGTH_SHORT).show();
                }
            } else {

            }
        }
        show_picture();
        }

    private void saveBitmapPhoto(Bitmap bm, Context context,String s) {
        ContentResolver contentResolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, s+".png");
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
        contentValues.put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis() / 1000);
        contentValues.put(MediaStore.MediaColumns.DATE_MODIFIED, System.currentTimeMillis() / 1000);
        Uri uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        if (uri != null) {
            FileOutputStream fos = null;
            try {
                fos = (FileOutputStream) contentResolver.openOutputStream(uri);
                bm.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
            } catch (IOException exception) {
                //Timber.e(exception);
            }
        }
    }

    public Point onSingleTap(Point e,ImageView imageView) {
        int width=imageView.getWidth();
        int height=imageView.getHeight();
        Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
        int width2=bitmap.getWidth();
        int height2=bitmap.getHeight();
        float x=e.x,y=e.y;
        int newx=Math.round(x/width*width2),newy=Math.round(y/height*height2);
        if (x == 1) {
            newx=1;
        }
        if (y==1){
            newy=1;
        }
        Point point=new Point(newx,newy);
        return point;
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

    public Bitmap getBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int heigh = drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, width, heigh);
        Bitmap bitmap = Bitmap.createBitmap(width, heigh, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);
        return bitmap;
    }
    public void show_picture(){
        if (select_num_image==0){
            {

                TranslateAnimation ta = new TranslateAnimation(0,0,0,constraintLayout_picture.getHeight());
                // 设置动画时长
                ta.setDuration(250);
                // 启动动画
                constraintLayout_picture.startAnimation(ta);
            }
            constraintLayout_picture.setVisibility(View.GONE);

        }else {



            if (constraintLayout_picture.getVisibility()!=View.VISIBLE){
                constraintLayout_picture.setVisibility(View.VISIBLE);
                TranslateAnimation ta = new TranslateAnimation(0,0,constraintLayout_picture.getHeight(),0);
                // 设置动画时长
                ta.setDuration(250);
                // 启动动画
                constraintLayout_picture.startAnimation(ta);
            }
        }
}
    public int random_numble(int min,int max){
    Random random = new Random();
    int num = random.nextInt(max)%(max-min+1) + min;
    return num;
}
    //handler为线程之间通信的桥梁
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 1:  //根据上面的提示，当Message为1，表示数据处理完了，可以通知主线程了
                    textView_show_time.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    textView_show_time.setText("所用时间\n" + time_spend_1.get_spend_time() + "ms");
                    break;
                case 2:
                    imageView_main.setImageBitmap(bitmap_trans);
                    break;
                case 3:
                    ((ImageView)linearLayout_imagelist.getChildAt(select_num_image)).setImageBitmap(bitmap_trans);
                    break;
                case 4:
                    ((ImageView)linearLayout_imagelist.getChildAt(i_trans)).setImageBitmap(bitmap_trans);
                    break;
                case 5:
                    editText_key.setText(s_trans);
                    break;
                case 6:
                    final Toast toast_1 = Toast.makeText(MainActivity.this, "密钥格式不正确", Toast.LENGTH_SHORT);
                    toast_1.show();
                    break;
                case 7:
                    dialog_communication.getWindow().findViewById(R.id.progressBar2).setVisibility(View.GONE);
                    dialog_communication.dismiss();
                case 8:
                    Toast.makeText(MainActivity.this, "请输入0-1的小数", Toast.LENGTH_SHORT).show();
                    break;
                case 9:
                    Toast.makeText(MainActivity.this, "密钥图未设置或已被删除", Toast.LENGTH_SHORT).show();
                    break;
                default :
                    break;
            }
        }

    };
    public int which_side(View view_1,View view_2) {
        int sum = 0, fin = 0;
        if (view_1.getTop() == 0) sum += 1;
        if (view_1.getLeft() == 0) sum += 2;
        if (view_1.getRight() == view_2.getWidth()) sum += 4;
        if (view_1.getBottom() == view_2.getHeight()) sum += 8;
        switch (sum) {
            case 1:
                fin = 1;
                break;
            case 2:
                fin = 4;
                break;
            case 4:
                fin = 2;
                break;
            case 8:
                fin = 3;
                break;
            default:
                fin = 0;
                break;
        }
        return fin;
    }
    private boolean firstRun() {
        SharedPreferences sharedPreferences = getSharedPreferences("FirstRun",0);
        Boolean first_run = sharedPreferences.getBoolean("First",true);
        if (first_run){
            sharedPreferences.edit().putBoolean("First",false).commit();
            System.out.println("dwfe");
            return true;
           // Toast.makeText(this,"第一次",Toast.LENGTH_LONG).show();
        }else {
            return false;
        }
    }

    public Bitmap adjustPhotoRotation(Bitmap bm, final int orientationDegree) {
        Matrix m = new Matrix();
        m.setRotate(orientationDegree, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        float targetX, targetY;
        if (orientationDegree == 90) {
            targetX = bm.getHeight();
            targetY = 0;
        } else {
            targetX = bm.getHeight();
            targetY = bm.getWidth();
        }
        final float[] values = new float[9];
        m.getValues(values);
        float x1 = values[Matrix.MTRANS_X];
        float y1 = values[Matrix.MTRANS_Y];
        m.postTranslate(targetX - x1, targetY - y1);
        Bitmap bm1 = Bitmap.createBitmap(bm.getHeight(), bm.getWidth(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        Canvas canvas = new Canvas(bm1);
        canvas.drawBitmap(bm, m, paint);
        return bm1;
    }
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }
private int range(double raw){
        if (raw>=0&&raw<=255){
            return (int)raw;
        }else if (raw <0){
            return 0;
        }else {
            return 255;
        }
}
private Bitmap sw(Bitmap bitmap,Point p1,Point p2){
    bitmap_process bitmap_process_sw=new bitmap_process();
    bitmap_process_sw.rewrite(bitmap);
    int h=p2.y-p1.y+1;
    int w=p2.x-p1.x+1;
    for (int i=p1.y;i<=p2.y-1;i+=2){
        for (int j=p1.x;j<=p2.x-1;j+=2){
            bitmap_process_sw.setr(i+1,j,bitmap_process_sw.getr(i+1,j));
            bitmap_process_sw.setg(i+1,j,255-bitmap_process_sw.getg(i+1,j));
            bitmap_process_sw.setb(i+1,j,255-bitmap_process_sw.getb(i+1,j));
            bitmap_process_sw.setr(i,j+1,255-bitmap_process_sw.getr(i,j+1));
            bitmap_process_sw.setg(i,j+1,bitmap_process_sw.getg(i,j+1));
            bitmap_process_sw.setb(i,j+1,255-bitmap_process_sw.getb(i,j+1));
            bitmap_process_sw.setr(i+1,j+1,255-bitmap_process_sw.getr(i+1,j+1));
            bitmap_process_sw.setg(i+1,j+1,255-bitmap_process_sw.getg(i+1,j+1));
            bitmap_process_sw.setb(i+1,j+1,bitmap_process_sw.getb(i+1,j+1));
        }
    }
    if (h%2==1){
        for (int j=p1.x;j<=p2.x-1;j+=2){
            bitmap_process_sw.setr(p2.y,j+1,255-bitmap_process_sw.getr(p2.y,j+1));
            bitmap_process_sw.setg(p2.y,j+1,bitmap_process_sw.getg(p2.y,j+1));
            bitmap_process_sw.setb(p2.y,j+1,255-bitmap_process_sw.getb(p2.y,j+1));
        }
    }
    if(w%2==1){
        for (int i=p1.y;i<=p2.y-1;i+=2){
            bitmap_process_sw.setr(i+1,p2.x,255-bitmap_process_sw.getr(i+1,p2.x));
            bitmap_process_sw.setg(i+1,p2.x,bitmap_process_sw.getg(i+1,p2.x));
            bitmap_process_sw.setb(i+1,p2.x,255-bitmap_process_sw.getb(i+1,p2.x));
        }
    }
    return bitmap_process_sw.rep();

}
private boolean could(Drawable drawable){
        Bitmap bitmap=getBitmap(drawable);
        Boolean can=false;
        if (bitmap.getWidth()>150&&bitmap.getHeight()>150&&bitmap.getHeight()<5000&&bitmap.getWidth()<5000){
            can=true;
        }
        return can;

}



}


