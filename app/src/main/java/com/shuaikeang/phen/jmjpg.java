package com.shuaikeang.phen;

import android.graphics.Bitmap;

public class jmjpg
{
    Bitmap b1;
    int[][] rp;
    int[][] rp2;
    int[][] rp3;
    int rw, rh;
    int nw, nh;
    int dx;
    int jg;
    public Bitmap cgsize(Bitmap b1, int w, int h)
    {
        Bitmap newBitmap = Bitmap.createScaledBitmap(b1,w,h,true);
        return newBitmap;
        /*
        int w1 = b1.getWidth();
        int h1 = b1.getHeight();
        double q1 = (double)w1 / w, q2 = (double)h1 / h;
        Bitmap b2 = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        bitmap_process np = new bitmap_process();
        bitmap_process np2 = new bitmap_process();

        np.rewrite(b1);
        np2.rewrite(b2);
        for (int i = 1; i <= h; i++)
        {
            for (int j = 1; j <= w; j++)
            {
                np2.setp(i, j, np.getp((int)((i * q2+h1-1) % h1 + 1), (int)((j * q1+w1-1) % w1 + 1)));
            }
        }
        np.rep();
        return np2.rep();

         */

    }
    public void getraw1(Bitmap b,int x,int y)
    {


        b1=cgsize(b,b.getWidth()-b.getWidth()%x,b.getHeight()-b.getHeight()%x);rw = b1.getWidth();rh = b1.getHeight();

        bitmap_process p1 = new bitmap_process();

        p1.rewrite(b1);
        rp = new int[rh+1][ rw+1];
        rp2 = new int[rh + 1][ rw + 1];
        dx = x;
        jg = y;

        nw = rw / dx;
        nh = rh / dx;
        rp3 = new int[rh + 1 + jg * (nh + 1)][ rw + 1 + jg * (nw + 1)];
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                rp[i][ j] = p1.getp(i,j);
                rp2[i][ j] = rp[i][j];
            }
        }
        //p1.rep();
    }
    public void getraw2(Bitmap b, int x, int y)
    {
        b1 = b; rw = b1.getWidth(); rh = b1.getHeight();
        dx = x;
        jg = y;
        nw = (rw - y) / (dx + jg);
        nh = (rh - y) / (dx + jg);
        rw = nw * dx;
        rh = nh * dx;
       bitmap_process p1 = new bitmap_process();

        p1.rewrite(b1);


        rp = new int[nh * dx + 1][ nw * dx + 1];
        rp2 = new int[nh * dx + 1][ nw * dx + 1];

        // rp3 = new int[rh + 1 + jg * (nh + 1), rw + 1 + jg * (nw + 1)];
        for (int i = 1; i <= nh; i++)
        {
            for (int j = 1; j <= nw; j++)
            {

                for (int ii = 1; ii <= dx; ii++)
                {
                    for (int jj = 1; jj <= dx; jj++)
                    {
                        rp[(i-1)*dx+ii][ (j-1)*dx+jj] = p1.getp((i-1)*(dx+jg)+jg+ii, (j - 1) * (dx + jg) + jg + jj);
                    }
                }

            }
        }

        for (int ii = 1; ii <= rh; ii++)
        {
            for (int j = 1; j <= rw; j++)
            {
                rp2[ii][j] = rp[ii][j];
            }
        }

      //  Array.Copy(rp, rp2, (rh + 1) * (rw + 1));
      //  p1.rep();
    }
    public  void movep(int x1, int y1, int x2, int y2)
    {
        for (int i = 1; i <= dx ; i++)
        {
            for (int j = 1; j <= dx ; j++)
            {
                rp2[(x2-1)*dx + i][ (y2-1)*dx + j] = rp[(x1-1)*dx + i][ (y1-1)*dx + j];
            }
        }
    }
    public void moveq(int x2, int y2, int x1, int y1)
    {
        for (int i = 1; i <= dx; i++)
        {
            for (int j = 1; j <= dx; j++)
            {
                rp2[(x2 - 1) * dx + i][ (y2 - 1) * dx + j] = rp[(x1 - 1) * dx + i][ (y1 - 1) * dx + j];
            }
        }
    }
    public void jm(String s)
    {
        int l = s.length();
        s.toLowerCase();
        char[] arr = s.toCharArray();
        int i;

        for (i = 0; i <= l - 1; i++)
        {

            if (arr[i] >= '0' && arr[i] <= '9')
            {

                switch (arr[i])
                {
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
                        rp[ii][j] = rp2[ii][j];
                    }
                }
              //  Array.Copy(rp2, rp, (rh + 1) * (rw + 1));
            }
        }




    }
    public void jm2(String s)
    {
        if (s != null){

            int l = s.length();
            s.toLowerCase();
            char[] arr = s.toCharArray();
            int i;

            for (i = l - 1; i >= 0; i--)
            {

                if (arr[i] >= '0' && arr[i] <= '9')
                {

                    switch (arr[i])
                    {
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
                            rp[ii][j] = rp2[ii][j];
                        }
                    }
                    //Array.Copy(rp2, rp, (rh + 1) * (rw + 1));
                }
            }


        }

    }
    public Bitmap getnew()
    {
        Bitmap b3 = Bitmap.createBitmap(rw + jg * (nw + 1), rh + jg * (nh + 1), Bitmap.Config.ARGB_8888);
       bitmap_process p3 = new bitmap_process();
        p3.rewrite(b3);
        for (int i = 1; i <= nh; i++)
        {
            for (int j = 1; j <= nw; j++)
            {
                int pzsx = (j - 1) * (dx + jg) + jg + 1;
                int pzsy = (i - 1) * (dx + jg) + jg + 1;

                for (int ii = 0; ii <= dx-1 ; ii++)
                {
                    for (int jj = 1; jj <= jg/2; jj++)
                    {
                        rp3[pzsy - jj][ pzsx + ii] = rp2[(i - 1) * dx + 1][ (j - 1) * dx + ii+1];
                    }
                }
                for (int ii = 0; ii <= dx-1; ii++)
                {
                    for (int jj = 1; jj <= jg / 2; jj++)
                    {
                        rp3[pzsy +dx+ jj-1][ pzsx + ii] = rp2[i  * dx ][ (j - 1) * dx + ii+1];
                    }
                }
                for (int ii = 1; ii <= jg/2; ii++)
                {
                    for (int jj = 0; jj <= dx-1; jj++)
                    {
                        rp3[pzsy + jj][ pzsx - ii] = rp2[(i-1)*dx+jj+1][ (j - 1) * dx + 1];
                    }
                }
                for (int ii = 1; ii <= jg/2; ii++)
                {
                    for (int jj = 0; jj <= dx  -1; jj++)
                    {
                        rp3[pzsy + jj][ pzsx + dx-1+ii] = rp2[(i - 1) * dx + jj+1][ j  * dx];
                    }
                }
                for (int ii = 0; ii < dx; ii++)
                {
                    for (int jj = 0; jj < dx; jj++)
                    {
                        rp3[pzsy + jj][ pzsx + ii] = rp2[(i - 1) * dx + jj + 1][ (j - 1) * dx + ii + 1];
                    }
                }

                for (int ii = 1; ii <= jg / 2; ii++)
                {
                    for (int jj = 1; jj <= jg / 2; jj++)
                    {
                        rp3[pzsy - ii][ pzsx - jj] = rp3[pzsy][ pzsx];
                        rp3[pzsy +dx-1+ ii][ pzsx+dx-1 + jj] = rp3[pzsy+dx-1][ pzsx+dx-1];
                        rp3[pzsy + dx - 1 + ii][ pzsx - jj] = rp3[pzsy + dx - 1][ pzsx ];
                        rp3[pzsy - ii][ pzsx + dx - 1 + jj] = rp3[pzsy ][ pzsx + dx - 1];
                    }
                }



            }
        }
        for (int i=1;i<= rw + jg * (nw + 1); i++)
        {
            for (int j=1;j<= rh + jg * (nh + 1); j++)
            {
                p3.setp(j, i, rp3[j][ i]);
            }
        }
        return p3.rep();





    }
    public Bitmap getnew2()
    {

        Bitmap b2 = Bitmap.createBitmap(rw, rh, Bitmap.Config.ARGB_8888);
        bitmap_process mp = new bitmap_process();
        mp.rewrite(b2);
        for (int i = 1; i <= rh; i++)
        {
            for (int j = 1; j <= rw; j++)
            {
                mp.setp(i, j, rp[i][ j]);
            }
        }
        return mp.rep();

    }
    void p1()
    {
        for (int i = 1; i <= nh; i++)
        {
            for (int j = 1; j <= nw; j++)
            {
                //rp2[i, j] = rp[((j - 1) * rh + i - 1) / rw + 1, ((j - 1) * rh + i - 1) % rw + 1];
                movep(((j - 1) * nh + i - 1) / nw + 1, ((j - 1) * nh + i - 1) % nw + 1, i, j);

            }
        }
    }
    void q1()
    {
        for (int i = 1; i <= nh; i++)
        {
            for (int j = 1; j <= nw; j++)
            {
                //rp2[i, j] = rp[((j - 1) * rh + i - 1) / rw + 1, ((j - 1) * rh + i - 1) % rw + 1];
                movep(i, j, ((j - 1) * nh + i - 1) / nw + 1, ((j - 1) * nh + i - 1) % nw + 1);

            }
        }
    }
    void p2()
    {
        int w = nw - 1; int h = nh - 1; int j, num = 1;
        int small;
        small = nw < nh ? nw : nh;
        for (int i = 1; i <= small / 2; i++)
        {
            for (j = i; j <= nw - i; j++)
            {
                //rpoint2[i, j] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                movep((num - 1) / nw + 1, (num - 1) % nw + 1, i, j);
                num++;
            }
            for (j = i; j <= nh - i; j++)
            {
                // rpoint2[j, nw - i + 1] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                movep((num - 1) / nw + 1, (num - 1) % nw + 1, j, nw - i + 1);
                num++;
            }
            for (j = nw - i + 1; j >= i + 1; j--)
            {
                //rpoint2[nh - i + 1, j] = rpoint[(num - 1) / nw + 1, (num - 1) %nw + 1];
                movep((num - 1) / nw + 1, (num - 1) % nw + 1, nh - i + 1, j);
                num++;
            }
            for (j = nh - i + 1; j >= i + 1; j--)
            {
                //rpoint2[j, i] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                movep((num - 1) / nw + 1, (num - 1) % nw + 1, j, i);
                num++;
            }

        }
        if (small % 2 == 1)
        {
            if (nh > nw)
            {
                int io;
                io = small / 2 + 1;
                for (int i = io; i <= nh - io + 1; i++)
                {
                    //rpoint2[i, io] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                    movep((num - 1) / nw + 1, (num - 1) % nw + 1, i, io);
                    num++;
                }


            }
            else
            {
                int io;
                io = small / 2 + 1;
                for (int i = io; i <= nw - io + 1; i++)
                {
                    //rpoint2[io, i] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                    movep((num - 1) / nw + 1, (num - 1) % nw + 1, io, i);
                    num++;
                }
            }
        }



    }
    void q2()
    {
        int w = nw - 1; int h = nh - 1; int j, num = 1;
        int small;
        small = nw < nh ? nw : nh;
        for (int i = 1; i <= small / 2; i++)
        {
            for (j = i; j <= nw - i; j++)
            {
                //rpoint2[i, j] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                moveq((num - 1) / nw + 1, (num - 1) % nw + 1, i, j);
                num++;
            }
            for (j = i; j <= nh - i; j++)
            {
                // rpoint2[j, nw - i + 1] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                moveq((num - 1) / nw + 1, (num - 1) % nw + 1, j, nw - i + 1);
                num++;
            }
            for (j = nw - i + 1; j >= i + 1; j--)
            {
                //rpoint2[nh - i + 1, j] = rpoint[(num - 1) / nw + 1, (num - 1) %nw + 1];
                moveq((num - 1) / nw + 1, (num - 1) % nw + 1, nh - i + 1, j);
                num++;
            }
            for (j = nh - i + 1; j >= i + 1; j--)
            {
                //rpoint2[j, i] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                moveq((num - 1) / nw + 1, (num - 1) % nw + 1, j, i);
                num++;
            }

        }
        if (small % 2 == 1)
        {
            if (nh > nw)
            {
                int io;
                io = small / 2 + 1;
                for (int i = io; i <= nh - io + 1; i++)
                {
                    //rpoint2[i, io] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                    moveq((num - 1) / nw + 1, (num - 1) % nw + 1, i, io);
                    num++;
                }


            }
            else
            {
                int io;
                io = small / 2 + 1;
                for (int i = io; i <= nw - io + 1; i++)
                {
                    //rpoint2[io, i] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                    moveq((num - 1) / nw + 1, (num - 1) % nw + 1, io, i);
                    num++;
                }
            }
        }



    }
    void p3()
    {
        int w = nw - 1; int h = nh - 1; int j, num = 1;
        int small;
        small = nw < nh ? nw : nh;
        for (int i = 1; i <= small / 2; i++)
        {
            for (j = i; j <= nh - i; j++)
            {
                //rpoint2[j, i] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                movep((num - 1) / nw + 1, (num - 1) % nw + 1, j, i);
                num++;
            }
            for (j = i; j <= nw - i; j++)
            {
                //rpoint2[nh - i + 1, j] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                movep((num - 1) / nw + 1, (num - 1) % nw + 1, nh - i + 1, j);
                num++;
            }
            for (j = nh - i + 1; j >= i + 1; j--)
            {
                //rpoint2[j, nw - i + 1] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                movep((num - 1) / nw + 1, (num - 1) % nw + 1, j, nw - i + 1);
                num++;
            }
            for (j = nw - i + 1; j >= i + 1; j--)
            {
                // rpoint2[i, j] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                movep((num - 1) / nw + 1, (num - 1) % nw + 1, i, j);
                num++;
            }


        }
        if (small % 2 == 1)
        {
            if (nh > nw)
            {
                int io;
                io = small / 2 + 1;
                for (int i = io; i <= nh - io + 1; i++)
                {
                    //rpoint2[i, io] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                    movep((num - 1) / nw + 1, (num - 1) % nw + 1, i, io);
                    num++;
                }


            }
            else
            {
                int io;
                io = small / 2 + 1;
                for (int i = io; i <= nw - io + 1; i++)
                {
                    //rpoint2[io, i] = rpoint[(num - 1) / nw + 1, (num - 1) %nw + 1];
                    movep((num - 1) / nw + 1, (num - 1) % nw + 1, io, i);
                    num++;
                }
            }
        }
    }
    void q3()
    {
        int w = nw - 1; int h = nh - 1; int j, num = 1;
        int small;
        small = nw < nh ? nw : nh;
        for (int i = 1; i <= small / 2; i++)
        {
            for (j = i; j <= nh - i; j++)
            {
                //rpoint2[j, i] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                moveq((num - 1) / nw + 1, (num - 1) % nw + 1, j, i);
                num++;
            }
            for (j = i; j <= nw - i; j++)
            {
                //rpoint2[nh - i + 1, j] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                moveq((num - 1) / nw + 1, (num - 1) % nw + 1, nh - i + 1, j);
                num++;
            }
            for (j = nh - i + 1; j >= i + 1; j--)
            {
                //rpoint2[j, nw - i + 1] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                moveq((num - 1) / nw + 1, (num - 1) % nw + 1, j, nw - i + 1);
                num++;
            }
            for (j = nw - i + 1; j >= i + 1; j--)
            {
                // rpoint2[i, j] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                moveq((num - 1) / nw + 1, (num - 1) % nw + 1, i, j);
                num++;
            }


        }
        if (small % 2 == 1)
        {
            if (nh > nw)
            {
                int io;
                io = small / 2 + 1;
                for (int i = io; i <= nh - io + 1; i++)
                {
                    //rpoint2[i, io] = rpoint[(num - 1) / nw + 1, (num - 1) % nw + 1];
                    moveq((num - 1) / nw + 1, (num - 1) % nw + 1, i, io);
                    num++;
                }


            }
            else
            {
                int io;
                io = small / 2 + 1;
                for (int i = io; i <= nw - io + 1; i++)
                {
                    //rpoint2[io, i] = rpoint[(num - 1) / nw + 1, (num - 1) %nw + 1];
                    moveq((num - 1) / nw + 1, (num - 1) % nw + 1, io, i);
                    num++;
                }
            }
        }
    }
    void p4()
    {
        for (int i = 1; i <= nh; i++)
        {
            for (int j = 1; j <= nw; j++)
            {
                if (j % 2 == 1)
                {
                    // rpoint2[i, j] = rpoint[i, nw - j / 2];
                    movep(i, nw - j / 2, i, j);
                }
                else
                {
                    // rpoint2[i, j] = rpoint[i, j / 2];
                    movep(i, j / 2, i, j);

                }


            }
        }
    }
    void q4()
    {
        for (int i = 1; i <= nh; i++)
        {
            for (int j = 1; j <= nw; j++)
            {
                if (j % 2 == 1)
                {
                    // rpoint2[i, j] = rpoint[i, nw - j / 2];
                    moveq(i, nw - j / 2, i, j);
                }
                else
                {
                    // rpoint2[i, j] = rpoint[i, j / 2];
                    moveq(i, j / 2, i, j);

                }


            }
        }
    }
    void p5()
    {
        for (int i = 1; i <= nh; i++)
        {
            if (i % 2 == 1)
            {
                for (int j = 1; j <= nw; j++)
                {
                    // rpoint2[i, j] = rpoint[nh - i / 2, j];
                    movep(nh - i / 2, j, i, j);
                }

            }
            else
            {
                for (int j = 1; j <= nw; j++)
                {
                    //rpoint2[i, j] = rpoint[i / 2, j];
                    movep(i / 2, j, i, j);
                }

            }

        }

    }
    void q5()
    {
        for (int i = 1; i <= nh; i++)
        {
            if (i % 2 == 1)
            {
                for (int j = 1; j <= nw; j++)
                {
                    // rpoint2[i, j] = rpoint[nh - i / 2, j];
                    moveq(nh - i / 2, j, i, j);
                }

            }
            else
            {
                for (int j = 1; j <= nw; j++)
                {
                    //rpoint2[i, j] = rpoint[i / 2, j];
                    moveq(i / 2, j, i, j);
                }

            }

        }

    }
    void p6()
    {
        for (int i = 1; i <= nw; i++)
        {
            for (int j = 1; j <= nh; j++)
            {
                // rpoint2[(j + i - 1) % nh + 1, i] = rpoint[j, i];
                movep(j, i, (j + i - 1) % nh + 1, i);


            }
        }

    }
    void q6()
    {
        for (int i = 1; i <= nw; i++)
        {
            for (int j = 1; j <= nh; j++)
            {
                // rpoint2[(j + i - 1) % nh + 1, i] = rpoint[j, i];
                moveq(j, i, (j + i - 1) % nh + 1, i);


            }
        }

    }
    void p7()
    {
        for (int i = 1; i <= nh; i++)
        {
            for (int j = 1; j <= nw; j++)
            {
                //rpoint2[i, (j + i - 1) % nw + 1] = rpoint[i, j];
                movep(i, j, i, (j + i - 1) % nw + 1);


            }
        }
    }
    void q7()
    {
        for (int i = 1; i <= nh; i++)
        {
            for (int j = 1; j <= nw; j++)
            {
                //rpoint2[i, (j + i - 1) % nw + 1] = rpoint[i, j];
                moveq(i, j, i, (j + i - 1) % nw + 1);


            }
        }
    }
    void p8()
    {
        int ni; double ww = nw / 5.0;
        for (int j = 1; j <= nw; j++)
        {
            for (int i = 1; i <= nh; i++)
            {
                ni = i + (int)(Math.sin((j - 1) / ww * Math.PI) * ww);
                while (ni < 1) ni += nh;
                ni = (ni - 1) % nh + 1;
                //rpoint2[ni, j] = rpoint[i, j];
                movep(i, j, ni, j);
            }
        }
    }
    void q8()
    {
        int ni; double ww = nw / 5.0;
        for (int j = 1; j <= nw; j++)
        {
            for (int i = 1; i <= nh; i++)
            {
                ni = i + (int)(Math.sin((j - 1) / ww * Math.PI) * ww);
                while (ni < 1) ni += nh;
                ni = (ni - 1) % nh + 1;
                //rpoint2[ni, j] = rpoint[i, j];
                moveq(i, j, ni, j);
            }
        }
    }
    void p9()
    {
        int ni; double ww = nh / 5.0;
        for (int i = 1; i <= nh; i++)
        {
            for (int j = 1; j <= nw; j++)
            {
                ni = j + (int)(Math.sin((i - 1) / ww * Math.PI) * ww);
                while (ni < 1) ni += nw;
                ni = (ni - 1) % nw + 1;
                //rpoint2[i, ni] = rpoint[i, j];
                movep(i, j, i, ni);
            }
        }

    }
    void q9()
    {
        int ni; double ww = nh / 5.0;
        for (int i = 1; i <= nh; i++)
        {
            for (int j = 1; j <= nw; j++)
            {
                ni = j + (int)(Math.sin((i - 1) / ww * Math.PI) * ww);
                while (ni < 1) ni += nw;
                ni = (ni - 1) % nw + 1;
                //rpoint2[i, ni] = rpoint[i, j];
                moveq(i, j, i, ni);
            }
        }

    }
    void p0()
    {
        int ni; double ww = nh / 5.0;
        for (int i = 1; i <= nh; i++)
        {
            for (int j = 1; j <= nw; j++)
            {
                ni = j + (int)Math.pow((i % ww), 2.0);
                while (ni < 1) ni += nw;
                ni = (ni - 1) % nw + 1;
                //rpoint2[i, ni] = rpoint[i, j];
                movep(i, j, i, ni);
            }
        }
    }
    void q0()
    {
        int ni; double ww = nh / 5.0;
        for (int i = 1; i <= nh; i++)
        {
            for (int j = 1; j <= nw; j++)
            {
                ni = j + (int)Math.pow((i % ww), 2.0);
                while (ni < 1) ni += nw;
                ni = (ni - 1) % nw + 1;
                //rpoint2[i, ni] = rpoint[i, j];
                moveq(i, j, i, ni);
            }
        }
    }
}
