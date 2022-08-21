package com.shuaikeang.phen;

public class time_spend {
    long starttime=0,finishtime=0;
    public long getTimeStame() {
        //获取当前的毫秒值
        long time = System.currentTimeMillis();
        //将毫秒值转换为String类型数据
        //String time_stamp = String.valueOf(time);
        //返回出去
        return time;
    }

    public void start_timering(){
        starttime=getTimeStame();

    }
    public int get_spend_time(){

        finishtime=getTimeStame();
        int spend=(int)(finishtime-starttime);
        return spend;
    }


}
