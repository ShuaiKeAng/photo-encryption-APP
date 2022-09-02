package com.shuaikeang.phen;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class setting {
    LinearLayout linearLayout_default_setting,linearLayout_others,linearLayout_contract,linearLayout_about;
    Button button_default_setting,button_others,button_contract,button_about;
    EditText editText_scan_num,editText_rect_width,editText_rect_outwidth,editText_max_scale,editText_name;
    CheckBox checkBox1,checkBox2,checkBox4,checkBox5;
    Dialog dialog;
    SharedPreferences sharedPreferences;
    public int default_scan_num=100,default_rect_width=20,default_rect_outwidth=5,default_max_scale=10;
    public String default_name="image";
    public boolean default_show,default_vibrator,contract_user,show,default_sw;
    int num_try=0;

    public void getsetting(){
        if (firstRun()==false) {
            try {
                sharedPreferences = MainActivity.mainActivity.getSharedPreferences("user", MainActivity.mainActivity.MODE_PRIVATE);

                default_scan_num = sharedPreferences.getInt("default_scan_num", 100);
                default_rect_width = sharedPreferences.getInt("default_rect_width", 20);
                default_rect_outwidth = sharedPreferences.getInt("default_rect_outwidth", 5);
                default_max_scale = sharedPreferences.getInt("default_max_scale", 10);
                default_name = sharedPreferences.getString("default_name", "image");
                default_vibrator = sharedPreferences.getBoolean("default_vibrator", true);
                default_show = sharedPreferences.getBoolean("show", true);
                contract_user= sharedPreferences.getBoolean("contract_user", false);
                show=sharedPreferences.getBoolean("show2",true);
                default_sw=sharedPreferences.getBoolean("sw",false);
            }catch (Exception e){

            }
        }else {
            default_scan_num = 100;
            default_rect_width = 20;
            default_rect_outwidth = 5;
            default_max_scale = 10;
            default_name = "image";
            default_vibrator = true;
            default_show = true;
            show=true;
            default_sw=false;
            //contract_user=false;
        }
    }

    public void setnew(Dialog dialog1){
        dialog=dialog1;
        linearLayout_default_setting=dialog.getWindow().findViewById(R.id.linearlayout_1);
        linearLayout_others=dialog.getWindow().findViewById(R.id.linearlayout_2);
        linearLayout_contract=dialog.getWindow().findViewById(R.id.linearlayout_3);
        linearLayout_about=dialog.getWindow().findViewById(R.id.linearlayout_4);
        button_default_setting=dialog.getWindow().findViewById(R.id.button39);
        button_others=dialog.getWindow().findViewById(R.id.button47);
        button_contract=dialog.getWindow().findViewById(R.id.button48);
        button_about=dialog.getWindow().findViewById(R.id.button49);
        editText_scan_num=dialog.getWindow().findViewById(R.id.editText4);
        editText_rect_width=dialog.getWindow().findViewById(R.id.editText5);
        editText_rect_outwidth=dialog.getWindow().findViewById(R.id.editText6);
        editText_max_scale=dialog.getWindow().findViewById(R.id.editText8);
        editText_name=dialog.getWindow().findViewById(R.id.editText7);
        checkBox1=dialog.getWindow().findViewById(R.id.checkBox2);
        checkBox2=dialog.getWindow().findViewById(R.id.checkBox3);
        checkBox4=dialog.getWindow().findViewById(R.id.checkBox4);
        checkBox5=dialog.getWindow().findViewById(R.id.checkBox5);
        ((TextView)dialog.getWindow().findViewById(R.id.textView43)).setText("当前版本\n"+MainActivity.getVerName(MainActivity.mainActivity));
        editText_scan_num.setText(String.valueOf(default_scan_num));
        editText_rect_width.setText(String.valueOf(default_rect_width));
        editText_rect_outwidth.setText(String.valueOf(default_rect_outwidth));
        editText_max_scale.setText(String.valueOf(default_max_scale));
        editText_name.setText(default_name);


        checkBox1.setChecked(default_vibrator);
        checkBox2.setChecked(default_show);
        checkBox4.setChecked(show);
        checkBox5.setChecked(default_sw);

        listener();

    }
    public void listener(){
        dialog.getWindow().findViewById(R.id.textView26).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num_try++;
                if (num_try==5){
                    MainActivity.mainActivity.cson=true;
                    num_try=0;
                }
            }
        });


        button_default_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout_default_setting.getVisibility()==View.GONE){
                    linearLayout_default_setting.setVisibility(View.VISIBLE);
                }else {
                    linearLayout_default_setting.setVisibility(View.GONE);
                }
            }
        });
        button_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout_others.getVisibility()==View.GONE){
                    linearLayout_others.setVisibility(View.VISIBLE);
                }else {
                    linearLayout_others.setVisibility(View.GONE);
                }
            }
        });
        button_contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout_contract.getVisibility()==View.GONE){
                    linearLayout_contract.setVisibility(View.VISIBLE);
                }else {
                    linearLayout_contract.setVisibility(View.GONE);
                }
            }
        });
        button_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout_about.getVisibility()==View.GONE){
                    linearLayout_about.setVisibility(View.VISIBLE);
                }else {
                    linearLayout_about.setVisibility(View.GONE);
                }
            }
        });
    }


    public void sure(){
        default_scan_num=Integer.parseInt(editText_scan_num.getText().toString());
        default_rect_width=Integer.parseInt(editText_rect_width.getText().toString());
        default_rect_outwidth=Integer.parseInt(editText_rect_outwidth.getText().toString());
        default_max_scale=Integer.parseInt(editText_max_scale.getText().toString());
        default_name=editText_name.getText().toString();
        default_vibrator=checkBox1.isChecked();
        default_show=checkBox2.isChecked();
        show=checkBox4.isChecked();
        default_sw=checkBox5.isChecked();
    }

    public void update(){
         sharedPreferences= MainActivity.mainActivity.getSharedPreferences("user",MainActivity.mainActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //将获取过来的值放入文件

        editor.putInt("default_scan_num", default_scan_num);
        editor.putInt("default_rect_width", default_rect_width);
        editor.putInt("default_rect_outwidth", default_rect_outwidth);
        editor.putInt("default_max_scale", default_max_scale);


        editor.putString("default_name", default_name);

        editor.putBoolean("show",default_show);
        editor.putBoolean("default_vibrator",default_vibrator);
        editor.putBoolean("show2",show);
        editor.putBoolean("sw",default_sw);
        // 提交数据
        editor.commit();


    }
    private boolean  firstRun() {
        SharedPreferences sharedPreferences = MainActivity.mainActivity.getSharedPreferences("FirstRun",0);
        Boolean first_run = sharedPreferences.getBoolean("First",true);
        return first_run;
    }


    public void contract(){
        sharedPreferences= MainActivity.mainActivity.getSharedPreferences("user",MainActivity.mainActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("contract_user",true);
        editor.commit();
    }

    public void noshow(){
        sharedPreferences= MainActivity.mainActivity.getSharedPreferences("user",MainActivity.mainActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("show2",false);
        editor.commit();
    }


}
