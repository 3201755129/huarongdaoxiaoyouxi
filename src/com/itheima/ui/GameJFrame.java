package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //常量部分
    //
    //创建二维数组,用来管理数据,会根据二维数组中的数字加载图片
    int[][] data = new int[4][4];
    //定义16号图片(空格)的坐标
    int x = 0;
    int y = 0;
    //定义图片文件的上层文件夹
    String path = "im58YearsOld";
    //定义胜利条件的二维数组
    int endData[][] = { {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 16}};
//定义计步器步数
    int count=0;
    //创建选项中条目的对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reloginItem = new JMenuItem("重新登录");
    JMenuItem exitItem = new JMenuItem("关闭游戏");

    JMenuItem aboutItem = new JMenuItem("LSPCA");
    JMenuItem valentineItem = new JMenuItem("情人节");
    JMenuItem xieItem = new JMenuItem("屑");
    JMenuItem numsItem = new JMenuItem("数字");


    //方法部分
    //
    public GameJFrame() {
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenu();
        //初始化数据(随机打乱)
        initData();
        //初始化图片
        initImage();


        //使界面显示出来(建议写在最后)
        setVisible(true);

    }

    private void initData() {

        //创建数组
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};


        //将一维数组赋值给二维数组
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 16) {
                y = i / 4;
                x = i % 4;
            }
            data[i / 4][i % 4] = arr[i];

        }
        //打乱数组
        for (int i = 0; i < 100; i++) {

            Random r = new Random();
            int rIndex = r.nextInt(4);
            move(37 + rIndex);

        }
        //打乱完毕,步数初始化为0
        count=0;
    }

    private void initImage() {
        this.getContentPane().removeAll();
        //创建计步器
        //创建一个JLable对象(管理容器)
        JLabel jLabelTip= new JLabel("已经走了"+count+"步了喔");
        //指定tip位置
        jLabelTip.setBounds( 0,  0, 201, 172);
        //把管理容器添加到页面中
        this.getContentPane().add(jLabelTip);

        //胜利时执行
        if (victory()) {
            //显示胜利图标
            //创建一个图片的对象
            //创建一个JLable对象(管理容器)
            JLabel jLabelVic = new JLabel(new ImageIcon("images/" + path + "/vic.png"));
            //指定图片位置
            jLabelVic.setBounds( 220,  440, 201, 172);
            //把管理容器添加到页面中
            this.getContentPane().add(jLabelVic);
        }

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                //获取文件在二维数组中的随机索引
                int index = data[j][i];
                //创建一个图片的对象
                //创建一个JLable对象(管理容器)
                JLabel jLabel = new JLabel(new ImageIcon("images/" + path + "/(" + (index) + ").jpg"));
                //指定图片位置
                jLabel.setBounds(105 * i + 91, j * 123 + 110, 105, 123);
                //给图片添加边框
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //把管理容器添加到页面中
                this.getContentPane().add(jLabel);
                //文件索引+1;
            }
        }
        //添加背景图片
        ImageIcon bg = new ImageIcon("images/" + path + "/bg.jpg");
        JLabel background = new JLabel(bg);
        background.setBounds(0, 0, 602, 680);
        this.getContentPane().add(background);

        this.getContentPane().repaint();
    }

    private void initJMenu() {

        //创建菜单栏对象
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单中选项的对象(功能,关于我们)
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        JMenu changPicJMenu = new JMenu("更换图片");

        //给条目绑定事件
        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        exitItem.addActionListener(this);
        aboutItem.addActionListener(this);
        valentineItem.addActionListener(this);
        xieItem.addActionListener(this);
        numsItem.addActionListener(this);
        //将每个选项对应的条目添加到选项中
        functionJMenu.add(replayItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(exitItem);
        changPicJMenu.add(valentineItem);
        changPicJMenu.add(xieItem);
        changPicJMenu.add(numsItem);
        aboutJMenu.add(aboutItem);

        //将菜单对应的选项添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);
        //将选项对应的内选项添加到选项中
        functionJMenu.add(changPicJMenu);


        //给界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面宽高
        setSize(602, 740);
        //设置界面标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中布局,便于按照XY轴的方式添加组件
        setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //按住A不放显示全图,释放回到游戏中
        int code = e.getKeyCode();
        seeAll(code);
    }

    private void seeAll(int code) {
        if (code == KeyEvent.VK_A) {
            //添加全图
            this.getContentPane().removeAll();
            JLabel allPic = new JLabel(new ImageIcon("images/" + path + "/all.jpg"));
            allPic.setBounds(91, 110, 420, 492);
            this.getContentPane().add(allPic);
            //添加背景图片
            ImageIcon bg = new ImageIcon("images/" + path + "/bg.jpg");
            JLabel background = new JLabel(bg);
            background.setBounds(0, 0, 602, 680);
            this.getContentPane().add(background);

            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()) {
            return;
        }
        int code = e.getKeyCode();
        //按键事件:上下左右移动及作弊
        move(code);


        initImage();

    }

    private boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != endData[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void move(int code) {
        if (code == 37) {
            if (x == 3) {
                //说明空格在最右边,其右边没有方块能够向左移动(避免x大于3产生异常)
                return;
            }
            count++;
            data[y][x] = data[y][x + 1];
            data[y][x + 1] = 16;
            x++;
        } else if (code == 38) {
            if (y == 3) {
                //说明空格在最下边,其下边没有方块能够向上移动(避免y大于3产生异常)
                return;
            }
            count++;
            data[y][x] = data[y + 1][x];
            data[y + 1][x] = 16;
            y++;
        } else if (code == 39) {
            if (x == 0) {
                //说明空格在最左边,其左边没有方块能够向右移动(避免x小于0产生异常)
                return;
            }
            count++;
            data[y][x] = data[y][x - 1];
            data[y][x - 1] = 16;
            x--;
        } else if (code == 40) {
            if (y == 0) {
                //说明空格在最上边,其上边没有方块能够向下移动(避免y小于0产生异常)
                return;
            }
            count++;
            data[y][x] = data[y - 1][x];
            data[y - 1][x] = 16;
            y--;
        } else if (code == KeyEvent.VK_W) {

//将通关数据赋值给二维数组
            for (int i = 0; i < 16; i++) {
                data[i / 4][i % 4] =i+1;
            }
            //显示通关效果
            initImage();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //读取点击对象
        Object clickObj = e.getSource();
        //判断
        if (clickObj ==replayItem) {
            //初始化
            initData();
            initImage();
        }else if (clickObj ==reloginItem) {
            //关闭游戏界面
this.setVisible(false);
//打开登录界面
            new LoginJFrame();
        }else if (clickObj == exitItem) {
//关闭虚拟机
            System.exit(0);
        }else if (clickObj ==aboutItem) {
//创建一个弹框对象
            JDialog jDialog = new JDialog();
            //创建一个容器对象(管理图片)
            JLabel jLabel = new JLabel(new ImageIcon("images/"+path+"/about.gif"));
            //设置位置和宽高
            jLabel.setBounds(0,0,320,240);
            //把图片放入弹框
            jDialog.getContentPane().add(jLabel);
            //弹框设置大小,图层性置顶,居中
            jDialog.setSize(320,240);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            //设置弹框不关闭则无法操作下层窗口
            jDialog.setModal(true);
            //让弹框显示出来(建议写在最后)
            jDialog.setVisible(true);
        }
        else if (clickObj == valentineItem) {
            path="im58YearsOld";
            initData();
            initImage();
        }else if (clickObj == xieItem) {
            path = "eatAPacketofFrenchFriesandTwoPacketsofKetchup";
            initData();
            initImage();
        }else if (clickObj == numsItem) {
            path = "nums";
            initData();
            initImage();
        }
    }
}
