package com.shuaikeang.phen;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

public class jm
{ bitmap_process p2=new bitmap_process();

    Bitmap raw;
    int rw, rh;

    int[][]  rpoint;
    int[][] rpoint2;
    public int sc = 0;
    public void getraw(Drawable bitp_dra)
    {

        Bitmap bitp=p2.getBitmap(bitp_dra);
        raw = bitp;
        rw = raw.getWidth();
        rh = raw.getHeight();
        rpoint = new int[rh+1][rw+1];
        rpoint2 = new int[rh + 1][ rw + 1];
        p2.rewrite(bitp);

        rpoint=p2.point_color;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {

                // rpoint[i][j] = p2.getp(i, j);
               rpoint2[i][j] = rpoint[i][j];
            }
        }

    }
    public void getraw2(Drawable bitp_dra,Point ss1,Point ss3)
{
    Point ss2=new Point(ss3.x-ss1.x+1,ss3.y-ss1.y+1);


    Bitmap bitp=p2.getBitmap(bitp_dra);
    raw = bitp;


    rw = ss2.x ;
    rh = ss2.y;
    rpoint = new int[rh + 1][ rw + 1];
    rpoint2 = new int[rh + 1][ rw + 1];
    p2.rewrite(bitp);
    for (int i = 1; i <= rh; i++)
    {
        for (int j = 1; j <= rw; j++)
        {
            rpoint[i][j] = p2.getp(i+ss1.y-1, j+ss1.x-1);
            rpoint2[i][j]=rpoint[i][j];
        }
    }
}

    public Bitmap setnew()
    {
        /*
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                p2.setp(i,j,rpoint[i][j]);

            }
        }

         */
        p2.point_color=rpoint;
        return p2.rep();

    }
    public void getraw2(Bitmap bitp,Point ss1,Point ss3)
    {
        Point ss2=new Point(ss3.x-ss1.x+1,ss3.y-ss1.y+1);



        raw = bitp;


        rw = ss2.x ;
        rh = ss2.y;
        rpoint = new int[rh + 1][ rw + 1];
        rpoint2 = new int[rh + 1][ rw + 1];
        p2.rewrite(bitp);
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                rpoint[i][j] = p2.getp(i+ss1.y-1, j+ss1.x-1);
                rpoint2[i][j]=rpoint[i][j];
            }
        }
    }

    public Bitmap setnew2(Point ss1,Point ss3)
    {
        Point ss2=new Point(ss3.x-ss1.x+1,ss3.y-ss1.y+1);
        rw = ss2.x ;
        rh = ss2.y;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                p2.setp(i + ss1.y - 1, j + ss1.x - 1, rpoint[i][j]);
                //rpoint2[i, j] = rpoint[i, j];
            }
        }
        return p2.rep();
    }


    public void runp(String s)
    {

        int l = s.length();
        s.toLowerCase();
        char[] arr = s.toCharArray();
        int i;

        for (i = 0; i <= l - 1; i++)
        {

            //Form1.form.progressBar1.Value = (i + 1) * 100 / l;
            // Application.DoEvents();
            if (arr[i] >= 'a' && arr[i] <= 'z'||arr[i]>='0'&&arr[i]<='9')
            {
                switch (arr[i]){
                    case 'a':pa();
                        break;
                    case 'b':
                        pb();
                        break;
                    case 'c':
                        pc();
                        break;
                    case 'd':
                        pd();
                        break;
                    case 'e':
                        pe();
                        break;
                    case 'f':
                        pf();
                        break;
                    case 'g':
                        pg();
                        break;
                    case 'h':
                        ph();
                        break;
                    case 'i':
                        pi();
                        break;
                    case 'j':
                        pj();
                        break;
                    case 'k':
                        pk();
                        break;
                    case 'l':
                        pl();
                        break;
                    case 'm':
                        pm();
                        break;
                    case 'n':
                        pn();
                        break;
                    case 'o':
                        po();
                        break;
                    case 'p':
                        pp();
                        break;
                    case 'q':
                        pq();
                        break;
                    case 'r':
                        pr();
                        break;
                    case 's':
                        ps();
                        break;
                    case 't':
                        pt();
                        break;
                    case 'u':
                        pu();
                        break;
                    case 'v':
                        pv();
                        break;
                    case 'w':
                        pw();
                        break;
                    case 'x':
                        px();
                        break;
                    case 'y':
                        py();
                        break;
                    case 'z':
                        pz();
                        break;
                    case '1':
                        p1();
                        break;
                    case '2':
                        p2();
                        break;
                    case '3':
                        p3();
                        break;
                    case '4':
                        p4();
                        break;
                    case '5':
                        p5();
                        break;
                    case '6':
                        p6();
                        break;
                    case '7':
                        p7();
                        break;
                    case '8':
                        p8();
                        break;
                    case '9':
                        p9();
                        break;
                    case '0':
                        p0();
                        break;
                }




                for (int ii = 1; ii <= rh; ii++)
                {
                    for (int j = 1; j <= rw; j++)
                    {
                        rpoint[ii][j] = rpoint2[ii][j];
                    }
                }




                //Array.Copy(rpoint2, rpoint, (rh + 1) * (rw + 1));


            }
        }
        //Form1.form.progressBar1.Value = 100;



    }
    public void runq(String s)
    {
        int l = s.length();
        s.toLowerCase();
        char[] arr = s.toCharArray();
        int i;

        for (i = l-1; i >=  0; i--)
        {
            if (arr[i] >= 'a' && arr[i] <= 'z'||arr[i]>='0'&&arr[i]<='9')
            {
               // Form1.form.progressBar1.Value = (l-i) * 100 / l;

                switch (arr[i])
                {
                    case 'a':
                        qa();
                        break;
                    case 'b':
                        qb();
                        break;
                    case 'c':
                        qc();
                        break;
                    case 'd':
                        qd();
                        break;
                    case 'e':
                        qe();
                        break;
                    case 'f':
                        qf();
                        break;
                    case 'g':
                        qg();
                        break;
                    case 'h':
                        qh();
                        break;
                    case 'i':
                        qi();
                        break;
                    case 'j':
                        qj();
                        break;
                    case 'k':
                        qk();
                        break;
                    case 'l':
                        ql();
                        break;
                    case 'm':
                        qm();
                        break;
                    case 'n':
                        qn();
                        break;
                    case 'o':
                        qo();
                        break;
                    case 'p':
                        qp();
                        break;
                    case 'q':
                        qq();
                        break;
                    case 'r':
                        qr();
                        break;
                    case 's':
                        qs();
                        break;
                    case 't':
                        qt();
                        break;
                    case 'u':
                        qu();
                        break;
                    case 'v':
                        qv();
                        break;
                    case 'w':
                        qw();
                        break;
                    case 'x':
                        qx();
                        break;
                    case 'y':
                        qy();
                        break;
                    case 'z':
                        qz();
                        break;
                    case '1':
                        q1();
                        break;
                    case '2':
                        q2();
                        break;
                    case '3':
                        q3();
                        break;
                    case '4':
                        q4();
                        break;
                    case '5':
                        q5();
                        break;
                    case '6':
                        q6();
                        break;
                    case '7':
                        q7();
                        break;
                    case '8':
                        q8();
                        break;
                    case '9':
                        q9();
                        break;
                    case '0':
                        q0();
                        break;

                }
                for (int ii = 1; ii <= rh; ii++)
                {
                    for (int j = 1; j <= rw; j++)
                    {
                        rpoint[ii][j] = rpoint2[ii][j];
                    }
                }
                //Array.Copy(rpoint2, rpoint, (rh + 1) * (rw + 1));


            }
        }

       // Form1.form.progressBar1.Value = 100;


    }
    public void pa()
    {
        for (int i = 1; i <= rh; i++)
        {
            for(int j=1; j <= rw; j++)
            {
                rpoint2[i][ j] = rpoint[((j- 1) * rh + i-1) / rw+1][ ((j - 1) * rh + i- 1) % rw + 1];

            }
        }

    }
    public void qa()
    {
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                rpoint2[i][ j] = rpoint[((i - 1) * rw + j - 1) % rh + 1][ ((i - 1) * rw + j - 1) / rh + 1];

            }
        }

    }
    public void pb()
    {
        int w = rw - 1;int h = rh - 1;int j,num=1;
        int small;
        small = rw < rh ? rw : rh;
        for (int i = 1; i <= small/2; i++)
        {
            for (j = i; j <= rw - i; j++)
            {
                rpoint2[i][ j] = rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                num++;
            }
            for (j = i; j <= rh - i; j++)
            {
                rpoint2[j][ rw - i + 1] = rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                num++;
            }
            for (j = rw - i + 1; j >= i + 1; j--)
            {
                rpoint2[rh - i + 1][ j] = rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                num++;
            }
            for (j = rh - i + 1; j >= i + 1; j--)
            {
                rpoint2[j][ i] = rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                num++;
            }

        }
        if(small % 2 == 1)
        {
            if (rh > rw)
            { int io;
                io = small / 2 + 1;
                for (int i = io; i <= rh - io+1; i++)
                {
                    rpoint2[i][io]= rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                    num++;
                }


            }
            else
            {
                int io;
                io = small / 2 + 1;
                for (int i = io; i <= rw - io + 1; i++)
                {
                    rpoint2[io][ i] = rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                    num++;
                }
            }
        }



    }
    public void qb()
    {
        int w = rw - 1; int h = rh - 1; int j, num = 1;
        int small;
        small = rw < rh ? rw : rh;
        for (int i = 1; i <= small / 2; i++)
        {
            for (j = i; j <= rw - i; j++)
            {
                rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1] = rpoint[i][ j] ;
                num++;
            }
            for (j = i; j <= rh - i; j++)
            {
                rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1]= rpoint[j][ rw - i + 1] ;
                num++;
            }
            for (j = rw - i + 1; j >= i + 1; j--)
            {
                rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1]=rpoint[rh - i + 1][ j] ;
                num++;
            }
            for (j = rh - i + 1; j >= i + 1; j--)
            {
                rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1]= rpoint[j][ i] ;
                num++;
            }

        }
        if (small % 2 == 1)
        {
            if (rh > rw)
            {
                int io;
                io = small / 2 + 1;
                for (int i = io; i <= rh - io + 1; i++)
                {
                    rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1]= rpoint[i][ io] ;
                    num++;
                }


            }
            else
            {
                int io;
                io = small / 2 + 1;
                for (int i = io; i <= rw - io + 1; i++)
                {
                    rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1] = rpoint[io][ i] ;
                    num++;
                }
            }
        }


    }
    public void pc()
    {
        int w = rw - 1; int h = rh - 1; int j, num = 1;
        int small;
        small = rw < rh ? rw : rh;
        for (int i = 1; i <= small / 2; i++)
        {
            for (j = i; j <= rh - i; j++)
            {
                rpoint2[j][ i] = rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                num++;
            }
            for (j = i; j <= rw - i; j++)
            {
                rpoint2[rh - i + 1][ j] = rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                num++;
            }
            for (j = rh - i + 1; j >= i + 1; j--)
            {
                rpoint2[j][ rw - i + 1] = rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                num++;
            }
            for (j = rw - i + 1; j >= i + 1; j--)
            {
                rpoint2[i][ j] = rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                num++;
            }


        }
        if (small % 2 == 1)
        {
            if (rh > rw)
            {
                int io;
                io = small / 2 + 1;
                for (int i = io; i <= rh - io + 1; i++)
                {
                    rpoint2[i][ io] = rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                    num++;
                }


            }
            else
            {
                int io;
                io = small / 2 + 1;
                for (int i = io; i <= rw - io + 1; i++)
                {
                    rpoint2[io][ i] = rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                    num++;
                }
            }
        }
    }
    public void qc()
    {
        int w = rw - 1; int h = rh - 1; int j, num = 1;
        int small;
        small = rw < rh ? rw : rh;
        for (int i = 1; i <= small / 2; i++)
        {
            for (j = i; j <= rh - i; j++)
            {
                rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1]= rpoint[j][ i] ;
                num++;
            }
            for (j = i; j <= rw - i; j++)
            {
                rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1]= rpoint[rh - i + 1][ j] ;
                num++;
            }
            for (j = rh - i + 1; j >= i + 1; j--)
            {
                rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1]=rpoint[j][ rw - i + 1] ;
                num++;
            }
            for (j = rw - i + 1; j >= i + 1; j--)
            {
                rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1]= rpoint[i][ j] ;
                num++;
            }


        }
        if (small % 2 == 1)
        {
            if (rh > rw)
            {
                int io;
                io = small / 2 + 1;
                for (int i = io; i <= rh - io + 1; i++)
                {
                    rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1] = rpoint[i][ io];
                    num++;
                }


            }
            else
            {
                int io;
                io = small / 2 + 1;
                for (int i = io; i <= rw - io + 1; i++)
                {
                    rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1] = rpoint[io][ i];
                    num++;
                }
            }
        }
    }

    public void pd()
    { int num = 1;
        if (rw >= rh)
        {
            for (int i = 1; i <= rh; i++)
            {
                for (int j = 1; j <= i; j++)
                {
                    rpoint2[i-j+1][j]= rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                    num++;

                }
            }
            for (int i = 1; i <= rw - rh ; i++)
            {
                for (int j = 1; j <= rh; j++)
                {
                    rpoint2[rh - j + 1][ i + j] = rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                    num++;

                }
            }
            for (int i = 1; i <= rh - 1; i++)
            {
                for (int j = 1; j <= rh - i; j++)
                {
                    rpoint2[rh+1-j][rw-rh+i+j]= rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                    num++;
                }
            }


        }
        else
        {
            for (int i = 1; i <= rw; i++)
            {
                for (int j = 1; j <= i; j++)
                {
                    rpoint2[i - j + 1][ j] = rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                    num++;

                }
            }
            for (int i = 1; i <= rh - rw; i++)
            {
                for (int j = 1; j <= rw; j++)
                {
                    rpoint2[rw - j + i + 1][ j] = rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                    num++;

                }
            }
            for (int i = 1; i <= rw - 1; i++)
            {
                for (int j = 1; j <= rw - i; j++)
                {
                    rpoint2[rh-j+1][ i + j ] = rpoint[(num - 1) / rw + 1][ (num - 1) % rw + 1];
                    num++;
                }
            }


        }

    }
    public void qd()
    {
        int num = 1;
        if (rw >= rh)
        {
            for (int i = 1; i <= rh; i++)
            {
                for (int j = 1; j <= i; j++)
                {
                    rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1] = rpoint[i - j + 1][ j] ;
                    num++;

                }
            }
            for (int i = 1; i <= rw - rh; i++)
            {
                for (int j = 1; j <= rh; j++)
                {
                    rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1]=rpoint[rh - j + 1][ i + j]  ;
                    num++;

                }
            }
            for (int i = 1; i <= rh - 1; i++)
            {
                for (int j = 1; j <= rh - i; j++)
                {
                    rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1]=rpoint[rh + 1 - j][ rw - rh + i + j ]  ;
                    num++;
                }
            }


        }
        else
        {
            for (int i = 1; i <= rw; i++)
            {
                for (int j = 1; j <= i; j++)
                {
                    rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1]=rpoint[i - j + 1][ j]  ;
                    num++;

                }
            }
            for (int i = 1; i <= rh - rw; i++)
            {
                for (int j = 1; j <= rw; j++)
                {
                    rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1]= rpoint[rw - j + i + 1][ j] ;
                    num++;

                }
            }
            for (int i = 1; i <= rw - 1; i++)
            {
                for (int j = 1; j <= rw - i; j++)
                {
                    rpoint2[(num - 1) / rw + 1][ (num - 1) % rw + 1] = rpoint[rh - j + 1][ i + j] ;
                    num++;
                }
            }


        }

    }
    public void pe()
    {
        int m = rw / 20;
       // int cen=0;
        if (m != 0)
        {
            for (int j = 1; j <= rh; j++)
            {
                for (int i = 1; i <= rw; i++)
                {
                    //cen=(j + i % m - 1) % rh + 1;
                    rpoint2[(j + i % m - 1) % rh + 1][ i] = rpoint[j][ i];


                }
            }
        }
    }
    public void qe()
    {
        int m = rw / 20;
        if (m != 0)
        {
            for (int j = 1; j <= rh; j++)
            {
                for (int i = 1; i <= rw; i++)
                {
                    rpoint2[j][ i] = rpoint[(j + i % m - 1) % rh + 1][ i];


                }
            }
        }

    }
    public void pf()
    {
        int m = rw / 20;
        if (m != 0)
        {
            for (int i = 1; i <= rh; i++)
            {
                for (int j = 1; j <= rw; j++)
                {
                    rpoint2[i][ (j + i % m - 1) % rw + 1] = rpoint[i][ j];


                }
            }
        }

    }
    public void qf()
    {
        int m = rw / 20;
        if (m != 0)
        {
            for (int i = 1; i <= rh; i++)
            {
                for (int j = 1; j <= rw; j++)
                {
                    rpoint2[i][ j] = rpoint[i][ (j + i % m - 1) % rw + 1];


                }
            }
        }

    }

    public void p0()
    {
        int num = rw * rh;
        int c1,c2;
        for (int i = 2; i <= num; i++)
        { c1 = rpoint[(i - 1) / rw + 1][ (i - 1) % rw + 1];
            c2 = rpoint2[(i- 2) / rw + 1][ (i - 2) % rw + 1];

            c1-=c2 ;
            if (c1 < 0)
            {
                c1+= 16777216;
            }
            rpoint2[(i - 1) / rw + 1][ (i - 1) % rw + 1] = c1 ;
        }


    }
    public void q0()
    {
        int num = rw * rh;
        int c1, c2;
        for (int i = num; i >= 2; i--)
        {
            c1 = rpoint[(i - 1) / rw + 1][ (i - 1) % rw + 1];
            c2 = rpoint[(i - 2) / rw + 1][ (i - 2) % rw + 1];

            c1 += c2;
            c1 = c1 % 16777216;
            rpoint2[(i - 1) / rw + 1][ (i - 1) % rw + 1] = c1;
        }


    }
    public void p1()
    {
        int c1, c2;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 2; j <= rw; j++)
            {
                c1 = rpoint[i][j];
                c2 = rpoint2[i-1][j];
                c1 -= c2;
                if (c1 < 0)
                {
                    c1 += 16777216;
                }
                rpoint2[i][ j] = c1;
            }

        }
    }
    public void q1()
    {

        int c1, c2;
        for (int i = rh; i>=1; i--)
        {
            for (int j = 2; j <= rw; j++)
            {
                c1 = rpoint[i][ j];
                c2 = rpoint2[i - 1][ j];
                c1 += c2;
                c1 = c1 % 16777216;
                rpoint2[i][ j] = c1;
            }

        }
    }
    public void p2()
    {
        int c1, c2;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 2; j <= rw; j++)
            {
                c1 = rpoint[i][ j];
                c2 = rpoint2[i ][ j-1];
                c1 -= c2;
                if (c1 < 0)
                {
                    c1 += 16777216;
                }
                rpoint2[i][ j] = c1;
            }

        }
    }
    public void q2()
    {
        int c1, c2;
        for (int i=1;i<=rh ;i++)
        {
            for (int j= rw; j>=2; j--)
            {
                c1 = rpoint[i][ j];
                c2 = rpoint2[i ][ j-1];
                c1 += c2;
                c1 = c1 % 16777216;
                rpoint2[i][ j] = c1;
            }

        }
    }
    public void p3()
    {
        int num;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                num = rpoint[i][ j] - j * 5000 + i * 5000;
                if (num < 0) num += 16777216;
                num = num % 16777216;
                rpoint2[i][ j] = num;
            }
        }


    }
    public void q3()
    {
        int num;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                num = rpoint[i][ j] + j * 5000 - i * 5000;
                if (num < 0) num += 16777216;
                num = num % 16777216;
                rpoint2[i][ j] = num;
            }
        }


    }
    public void p4()
    {
        int num;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                num = rpoint[i][ j] + j * 5000 +i * 5000;
                if (num < 0) num += 16777216;
                num = num % 16777216;
                rpoint2[i][ j] = num;
            }
        }


    }
    public void q4()
    {
        int num;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                num = rpoint[i][ j] - j * 5000 - i * 5000;
                if (num < 0) num += 16777216;
                num = num % 16777216;
                rpoint2[i][ j] = num;
            }
        }


    }
    public void p5()
    {
        int num;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                num = rpoint[i][ j] + (i % 201 - 100) * (i % 201 - 100) * 100 + (j % 201 - 100) * (j % 201 - 100) * 100;
                if (num < 0) num += 16777216;
                num = num % 16777216;
                rpoint2[i][ j] = num;
            }
        }


    }
    public void q5()
    {
        int num;

        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                num = rpoint[i][ j] -(i % 201 - 100) * (i % 201 - 100) * 100 - (j % 201 - 100) * (j % 201 - 100) * 100;
                if (num < 0) num += 16777216;
                num = num % 16777216;
                rpoint2[i][ j] = num;
            }
        }


    }
    public void p6()
    {
        int num;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                num = rpoint[i][ j] + (i % 201 - 100) * (i % 201 - 100) * 100 - (j % 201 - 100) * (j % 201 - 100) * 100;
                if (num < 0) num += 16777216;
                num = num % 16777216;
                rpoint2[i][ j] = num;
            }
        }


    }
    public void q6()
    {
        int num;

        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                num = rpoint[i][ j] - (i % 201 - 100) * (i % 201 - 100) * 100 + (j % 201 - 100) * (j % 201 - 100) * 100;
                if (num < 0) num += 16777216;
                num = num % 16777216;
                rpoint2[i][ j] = num;
            }
        }


    }
    public void pn()
    {
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                if (j % 2 == 1)
                {
                    rpoint2[i][ j] = rpoint[i][ rw - j / 2];
                }
                else
                {
                    rpoint2[i][ j] = rpoint[i][ j / 2];

                }


            }
        }


    }
    public void qn()
    {
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                if (j % 2 == 1)
                {
                    rpoint2[i][ rw - j / 2] = rpoint[i][ j];
                }
                else
                {
                    rpoint2[i][ j / 2] = rpoint[i][ j];

                }

            }
        }

    }
    public void po()
    {
        for (int i = 1; i <= rh; i++)
        {
            if (i % 2 == 1)
            {
                for (int j = 1; j <= rw; j++)
                {
                    rpoint2[i][ j] = rpoint[rh-i/2][  j ];
                }

            }
            else
            {
                for (int j = 1; j <= rw; j++)
                {
                    rpoint2[i][ j] = rpoint[i/2][ j];
                }

            }

        }


    }
    public void qo()
    {
        for (int i = 1; i <= rh; i++)
        {
            if (i % 2 == 1)
            {
                for (int j = 1; j <= rw; j++)
                {
                    rpoint2[rh - i / 2][ j] = rpoint[i][ j] ;
                }

            }
            else
            {
                for (int j = 1; j <= rw; j++)
                {
                    rpoint2[i / 2][ j]= rpoint[i][ j] ;
                }

            }

        }


    }
    public void pp()
    {

        for (int j = 1; j <= rh; j++)
        {
            for (int i = 1; i <= rw; i++)
            {
                rpoint2[(j + i  - 1) % rh + 1][ i] = rpoint[j][ i];


            }
        }

    }
    public void qp()
    {
        for (int j = 1; j <= rh; j++)
        {
            for (int i = 1; i <= rw; i++)
            {
                rpoint2[j][ i]= rpoint[(j + i - 1) % rh + 1][ i] ;


            }
        }


    }
    public void pq()
    {

        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                rpoint2[i][ (j + i  - 1) % rw + 1] = rpoint[i][ j];


            }
        }

    }
    public void qq()
    {

        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                rpoint2[i][ j]= rpoint[i][ (j + i - 1) % rw + 1] ;


            }
        }

    }
    public void p7()
    {


        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                rpoint2[i][ j] += i * j;

                rpoint2[i][j]%= 16777216;


            }
        }
    }
    public void q7()
    {
        int num = rw / 2;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                rpoint2[i][ j] -= i * j;

                while (rpoint2[i][ j] < 0)
                { rpoint2[i][ j] += 16777216; }

            }
        }
    }
    public void ps()
    {
        int num;
        for (int i = 1; i <= rh; i++)
        {if (i % 2 == 1)
        {
            for (int j = 1; j <= rw; j++)
            {
                rpoint2[i][ j] = rpoint[i][ (j+rw/100)%rw+1];


            }
        }
        else
        {
            for (int j = 1; j <= rw; j++)
            {
                num = j - rw / 100;
                if (num < 1) num =num+rw;
                rpoint2[i][ j] = rpoint[i][ num];


            }
        }
        }
    }
    public void qs()
    {
        int num;
        for (int i = 1; i <= rh; i++)
        {
            if (i % 2 == 1)
            {
                for (int j = 1; j <= rw; j++)
                {
                    rpoint2[i][ (j + rw / 100) % rw + 1]= rpoint[i][ j] ;


                }
            }
            else
            {
                for (int j = 1; j <= rw; j++)
                {
                    num = j - rw / 100;
                    if (num < 1) num = num + rw ;
                    rpoint2[i][ num] = rpoint[i][ j] ;


                }
            }
        }
    }
    public void pt()
    {
        int num;
        for (int i = 1; i<= rh; i++)
        {

                for (int j = 1; j <= rw; j++)
                {
                    if (j%2==1) {
                        rpoint2[i][j] = rpoint[(i + rh / 100) % rh + 1][j];
                    }
                    else {
                        num = i - rh / 100;
                        if (num < 1) num = num + rh;
                        rpoint2[i][ j] = rpoint[ num][ j];
                    }


                }


        }
    }
    public void qt()
    {
        int num;
        for (int i = 1; i<= rh; i++)
        {

            for (int j = 1; j <= rw; j++)
            {
                if (j%2==1) {
                    rpoint2[(i + rh / 100) % rh + 1][j] = rpoint[i][j];
                }
                else {
                    num = i - rh / 100;
                    if (num < 1) num = num + rh;
                    rpoint2[num][ j] = rpoint[i][j];
                }


            }


        }
    }
    public void pu()
    {
        double pp=Math.PI;
        int ni;double ww = rw *0.05 ;
        double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            { ni = i + (int)(Math.sin((j - 1) *wp)*ww);
                while (ni < 1) ni += rh;
                ni = (ni - 1) % rh+1;
                rpoint2[ni][j]= rpoint[i][ j];
            }
        }
    }
    public void qu()
    {
        double pp=Math.PI;
        int ni;double ww = rw *0.05;
        double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            { ni = i + (int)(Math.sin((j - 1) *wp)*ww);
                while (ni < 1) ni += rh;
                ni = (ni - 1) % rh+1;
                rpoint2[i][j]= rpoint[ni][ j];
            }
        }
    }
    public void pv()
    {
        int ni; double ww = rh* 0.05;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j<= rw; j++)
            {
                ni = j + (int)(Math.sin((i - 1) / ww * Math.PI) * ww);
                while (ni < 1) ni += rw;
                ni = (ni - 1) % rw + 1;
                rpoint2[i][ ni] = rpoint[i][ j];
            }
        }
    }
    public void qv()
    {
        int ni; double ww = rh *0.05;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                ni = j + (int)(Math.sin((i - 1) / ww * Math.PI) * ww);
                while (ni < 1) ni += rw;
                ni = (ni - 1) % rw + 1;
                rpoint2[i][ j]= rpoint[i][ ni] ;
            }
        }
    }
    public void pw()
    {
        int ni; double ww = rw / 10.0;
        int []nj=new int[rw+1];
        for (int j= 1; j <= rw; j++) {
            nj[j]=(int)Math.pow((j % ww), 2);
        }

        for (int i= 1; i <= rh; i++)
        {

            for (int j= 1; j<= rw; j++)
            {
                ni = i + nj[j];
                while (ni < 1) ni += rh;
                ni = (ni - 1) % rh + 1;
                rpoint2[ni][ j] = rpoint[i][ j];
            }
        }
    }
    public void qw()
    {
        int ni; double ww = rw / 10.0;
        int []nj=new int[rw+1];
        for (int j= 1; j <= rw; j++) {
            nj[j]=(int)Math.pow((j % ww), 2);
        }
        for (int i= 1; i <= rh; i++)
        {

            for (int j= 1; j<= rw; j++)
            {
                ni = i + nj[j];
                while (ni < 1) ni += rh;
                ni = (ni - 1) % rh + 1;
                rpoint2[i][ j] = rpoint[ni][ j];
            }
        }
    }
    public void px()
    {
        int ni; double ww = rh * 0.05;

        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                ni = j + (int)Math.pow((i % ww), 2);
                while (ni < 1) ni += rw;
                ni = (ni - 1) % rw + 1;
                rpoint2[i][ ni] = rpoint[i][ j];
            }
        }
    }
    public void qx()
    {
        int ni; double ww = rh *0.05;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                ni = j + (int)Math.pow((i % ww), 2);
                while (ni < 1) ni += rw;
                ni = (ni - 1) % rw + 1;
                rpoint2[i][ j] = rpoint[i][ ni];
            }
        }
    }
    public void p8()
    {
        double nn = rw / 10.0;
        int num;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                num = rpoint[i][ j] + (int)(Math.sin(j/nn*Math.PI)*50000);
                if (num < 0) num += 16777216;
                num = num % 16777216;
                rpoint2[i][ j] = num;
            }
        }


    }
    public void q8()
    {
        int num; double nn = rw / 10.0;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                num = rpoint[i][ j] - (int)(Math.sin(j / nn * Math.PI) * 50000);
                if (num < 0) num += 16777216;
                num = num % 16777216;
                rpoint2[i][ j] = num;
            }
        }


    }
    public void p9()
    {
        double nn = rh / 10.0;
        int num;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                num = rpoint[i][ j] + (int)(Math.sin(i / nn * Math.PI) * 50000);
                if (num < 0) num += 16777216;
                num = num % 16777216;
                rpoint2[i][ j] = num;
            }
        }

    }
    public void q9()
    {
        double nn = rh / 10.0;
        int num;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                num = rpoint[i][ j] - (int)(Math.sin(i / nn * Math.PI) * 50000);
                if (num < 0) num += 16777216;
                num = num % 16777216;
                rpoint2[i][ j] = num;
            }
        }

    }
    public void pg(){
           // double pp=Math.PI;
            int ni;int ww = (int)(rw *0.2) ;

            //double wp=pp/ww;
            for (int i = 1; i <= rh; i++)
            {
                for (int j = 1; j <= rw; j++)
                { ni = i +j%(ww-j%ww);
                    ni = (ni - 1) % rh+1;
                    rpoint2[ni][j]= rpoint[i][ j];
                }
            }

    }
    public void qg(){
        // double pp=Math.PI;
        int ni;int ww = (int)(rw *0.2) ;
        //double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            { ni = i +j%(ww-j%ww);
                ni = (ni - 1) % rh+1;
                rpoint2[i][j]= rpoint[ni][ j];
            }
        }

    }
    public void ph(){
        // double pp=Math.PI;
        int nj;int hh = (int)(rh *0.2) ;
        int raw;
        //double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {
           raw = i%(hh-i%hh);
            for (int j = 1; j <= rw; j++)
            {
                nj =j+raw ;
                //while (nj < 1) nj += rw;
                nj = (nj - 1) % rw+1;
                rpoint2[i][nj]= rpoint[i][ j];
            }
        }

    }
    public void qh(){
        // double pp=Math.PI;
        int nj;int hh = (int)(rh *0.2) ;
        int raw;
        //double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {
            raw = i%(hh-i%hh);
            for (int j = 1; j <= rw; j++)
            {
                nj =j+raw ;
               // while (nj < 1) nj += rw;
                nj = (nj - 1) % rw+1;
                rpoint2[i][j]= rpoint[i][ nj];
            }
        }

    }
    public void pi(){
        // double pp=Math.PI;
        int ni;int ww = (int)(rw *0.02) ;
        //double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            { ni = i +(j/ww)*(j%ww);
                //while (ni < 1) ni += rh;
                ni = (ni - 1) % rh+1;
                rpoint2[ni][j]= rpoint[i][ j];
            }
        }

    }

    public void qi(){
        // double pp=Math.PI;
        int ni;int ww = (int)(rw *0.02) ;

        //double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {ni = i +(j/ww)*(j%ww);
                //while (ni < 1) ni += rh;
                ni = (ni - 1) % rh+1;
                rpoint2[i][j]= rpoint[ni][ j];
            }
        }
    }

    public void pj(){
        // double pp=Math.PI;
        int nj;int hh = (int)(rh *0.02) ;
        //double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            { nj = j +(i/hh)*(i%hh);
               // while (nj < 1) nj += rw;
                nj = (nj- 1) % rw+1;
                rpoint2[i][nj]= rpoint[i][ j];
            }
        }

    }
    public void qj(){
        // double pp=Math.PI;
        int nj;int hh = (int)(rh *0.02) ;
        //double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            { nj = j +(i/hh)*(i%hh);
                //while (nj < 1) nj += rw;
                nj = (nj- 1) % rw+1;
                rpoint2[i][j]= rpoint[i][ nj];
            }
        }

    }

    public void qk(){
        int sum=rh*rw-1;
        for (int i = 1; i <= rh; i++)
        {
            for(int j=1; j <= rw; j++)
            {
                int raw=((i- 1) * rw+ j-1);
                if (raw <=sum/2){
                    rpoint2[i][ j] = rpoint[(raw*2) % rh+1][ (raw*2) / rh +1];
                }else if (raw >sum/2){
                    raw=((sum-raw)*2+1);
                    rpoint2[i][ j] = rpoint[(raw % rh)+1][(raw / rh )+1];
                }
                //rpoint2[i][ j] = rpoint[((j- 1) * rh + i-1) / rw+1][ ((j - 1) * rh + i- 1) % rw + 1];

            }
        }

    }
    public void pk(){
        int sum=rh*rw-1;
        for (int i = 1; i <= rh; i++)
        {
            for(int j=1; j <= rw; j++)
            {
                int raw=((i- 1) * rw+ j-1);
                if (raw <=sum/2){
                     rpoint2[(raw*2) % rh+1][ (raw*2) / rh +1]=rpoint[i][ j] ;
                }else if (raw >sum/2){
                    raw=((sum-raw)*2+1);
                    rpoint2[(raw % rh)+1][(raw/ rh )+1]=rpoint[i][ j] ;
                }
                //rpoint2[i][ j] = rpoint[((j- 1) * rh + i-1) / rw+1][ ((j - 1) * rh + i- 1) % rw + 1];

            }
        }

    }
    public void ql(){
        int sum=rh*rw-1;
        for (int i = 1; i <= rh; i++)
        {
            for(int j=1; j <= rw; j++)
            {
                int raw=((j- 1) * rh + i-1);
                if (raw <=sum/2){
                    rpoint2[i][ j] = rpoint[(raw*2) / rw+1][ (raw*2) % rw +1];
                }else if (raw >sum/2){
                    raw=((sum-raw)*2+1);
                    rpoint2[i][ j] = rpoint[(raw / rw)+1][(raw % rw )+1];
                }
                //rpoint2[i][ j] = rpoint[((j- 1) * rh + i-1) / rw+1][ ((j - 1) * rh + i- 1) % rw + 1];

            }
        }



    }
    public void pl(){
        int sum=rh*rw-1;
        for (int i = 1; i <= rh; i++)
        {
            for(int j=1; j <= rw; j++)
            {
                int raw=((j- 1) * rh + i-1);
                if (raw <=sum/2){
                   rpoint2[raw*2 / rw+1][ raw*2 % rw +1]= rpoint[i][ j]  ;
                }else if (raw >sum/2){
                    raw=((sum-raw)*2+1);
                     rpoint2[(raw / rw)+1][(raw % rw)+1]=rpoint[i][ j] ;
                }
                //rpoint2[i][ j] = rpoint[((j- 1) * rh + i-1) / rw+1][ ((j - 1) * rh + i- 1) % rw + 1];

            }
        }



    }
    public void pm(){

        int ni;int ww = (int)(rw *0.1) ;
        //double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            { ni = i +((rw-j+1)/ww)*((rw-j+1)%ww);
                while (ni < 1) ni += rh;
                ni = (ni - 1) % rh+1;
                rpoint2[ni][j]= rpoint[i][ j];
            }
        }




    }
    public void qm(){

        int ni;int ww = (int)(rw *0.1) ;
        //double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            { ni = i +((rw-j+1)/ww)*((rw-j+1)%ww);
                while (ni < 1) ni += rh;
                ni = (ni - 1) % rh+1;
                rpoint2[i][j]= rpoint[ni][ j];
            }
        }




    }
    public void pr(){

        int nj;int hh = (int)(rh *0.1) ;
        //double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            { nj = j +((rh-i+1)/hh)*((rh-i+1)%hh);
                while (nj < 1) nj += rw;
                nj = (nj - 1) % rw+1;
                rpoint2[i][nj]= rpoint[i][ j];
            }
        }




    }
    public void qr(){

        int nj;int hh = (int)(rh *0.1) ;
        //double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            { nj = j +((rh-i+1)/hh)*((rh-i+1)%hh);
                while (nj < 1) nj += rw;
                nj = (nj - 1) % rw+1;
                rpoint2[i][j]= rpoint[i][ nj];
            }
        }
    }
    public void py(){

        int nj;int hh = (int)(rh *0.1) ;
        //double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {
            if (i%2==0) {
                for (int j = 1; j <= rw; j++) {
                    nj = j + i;
                    while (nj < 1) nj += rw;
                    nj = (nj - 1) % rw + 1;
                    rpoint2[i][nj] = rpoint[i][j];
                }
            }else {
                for (int j = 1; j <= rw; j++) {
                    nj = j - i;
                    while (nj < 1) nj += rw;
                    nj = (nj - 1) % rw + 1;
                    rpoint2[i][nj] = rpoint[i][j];
                }
            }
        }




    }
    public void qy(){

        int nj;int hh = (int)(rh *0.1) ;
        //double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {
            if (i%2==0) {
                for (int j = 1; j <= rw; j++) {
                    nj = j + i;
                    while (nj < 1) nj += rw;
                    nj = (nj - 1) % rw + 1;
                    rpoint2[i][j] = rpoint[i][nj];
                }
            }else {
                for (int j = 1; j <= rw; j++) {
                    nj = j - i;
                    while (nj < 1) nj += rw;
                    nj = (nj - 1) % rw + 1;
                    rpoint2[i][j] = rpoint[i][nj];
                }
            }
        }




    }
    public void pz(){

        int ni;int ww = (int)(rw *0.1) ;
        //double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {

                for (int j = 1; j <= rw; j++) {
                    if (j%2==0) {
                        ni = i + j;
                        while (ni< 1) ni += rh;
                        ni = (ni - 1) % rh + 1;
                        rpoint2[ni][j] = rpoint[i][j];
                    }else {
                        ni = i - j;
                        while (ni< 1) ni += rh;
                        ni = (ni - 1) % rh + 1;
                        rpoint2[ni][j] = rpoint[i][j];
                    }
                }
        }
    }
    public void qz(){

        int ni;int ww = (int)(rw *0.1) ;
        //double wp=pp/ww;
        for (int i = 1; i <= rh; i++)
        {

            for (int j = 1; j <= rw; j++) {
                if (j%2==0) {
                    ni = i + j;
                    while (ni< 1) ni += rh;
                    ni = (ni - 1) % rh + 1;
                    rpoint2[i][j] = rpoint[ni][j];
                }else {
                    ni = i - j;
                    while (ni< 1) ni += rh;
                    ni = (ni - 1) % rh + 1;
                    rpoint2[i][j] = rpoint[ni][j];
                }
            }
        }
    }

}
