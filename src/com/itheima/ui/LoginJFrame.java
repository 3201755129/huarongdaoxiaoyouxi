package com.itheima.ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class LoginJFrame extends JFrame implements MouseListener {
    //创建集合储存正确账户
    static ArrayList<User> list = new ArrayList<>();

    static {
        list.add(new User("bika", "555"));
        list.add(new User("壁纸娘", "233"));
    }

    JButton login = new JButton();
    JButton signup = new JButton();
    //创建真验证码
    String verifCodeR = verifCode();
    JLabel jLabelverifCodeValue = new JLabel(verifCodeR);
    //创建用户名输入框
    JTextField userName = new JTextField();
    //创建密码输入框
    JPasswordField password = new JPasswordField();
    //创建验证码输入框
    JTextField verifCode = new JTextField();

    public LoginJFrame() {
        //初始化界面
        initeFrame();

        //使界面显示出来(建议写在最后)
        setVisible(true);

    }

    private void initeFrame() {
        this.getContentPane().removeAll();


        //设置界面宽高
        this.setSize(488, 430);
        //设置界面标题
        this.setTitle("拼图 登录");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中布局,便于按照XY轴的方式添加组件
        setLayout(null);

        //创建用户名标签
        JLabel jLabelUsername = new JLabel("用户名:");
        //指定tip位置
        jLabelUsername.setBounds(10, 0, 70, 15);
        //把管理容器添加到页面中
        this.getContentPane().add(jLabelUsername);

        //创建密码标签
        JLabel jLabelPassword = new JLabel("密码:");
        //指定tip位置
        jLabelPassword.setBounds(10, 30, 70, 20);
        //把管理容器添加到页面中
        this.getContentPane().add(jLabelPassword);

        //创建验证码标签
        JLabel jLabelverifCode = new JLabel("验证码:");
        //指定位置
        jLabelverifCode.setBounds(10, 60, 70, 20);
        //把管理容器添加到页面中
        this.getContentPane().add(jLabelverifCode);

        //用户名输入框
        //指定输入框位置
        userName.setBounds(70, 0, 100, 30);
        //把管理容器添加到页面中
        this.getContentPane().add(userName);

        //密码输入框
        //指定输入框位置
        password.setBounds(70, 30, 100, 30);
        //把管理容器添加到页面中
        this.getContentPane().add(password);

        //验证码输入框
        //指定输入框位置
        verifCode.setBounds(70, 60, 100, 30);
        //把管理容器添加到页面中
        this.getContentPane().add(verifCode);

        //验证码真值显示tip
        //指定tip位置
        jLabelverifCodeValue.setBounds(180, 60, 70, 20);
        //把管理容器添加到页面中
        this.getContentPane().add(jLabelverifCodeValue);
        //给验证码tip绑定鼠标监听事件
        jLabelverifCodeValue.addMouseListener(this);


        //添加登录按钮
        login.setBounds(70, 100, 60, 30);
        login.setText("登录");
        this.getContentPane().add(login);
        //给登录按钮绑定鼠标监听事件
        login.addMouseListener(this);


        //添加注册按钮
        signup.setBounds(130, 100, 60, 30);
        signup.setText("注册");
        this.getContentPane().add(signup);
        //给注册按钮绑定鼠标监听事件
        signup.addMouseListener(this);


        //添加背景图片
        ImageIcon bg = new ImageIcon("images/nums/bg.jpg");
        JLabel background = new JLabel(bg);
        background.setBounds(0, 0, 602, 680);
        this.getContentPane().add(background);
        this.getContentPane().repaint();
    }


    //创建验证码
    private String verifCode() {
        ArrayList<Character> chars = new ArrayList<>();
        StringBuilder codes = new StringBuilder();
        ArrayList<Character> nums = new ArrayList<>();
        //创建字母数组
        for (int i = 0; i < 26; i++) {
            chars.add((char) ('a' + i));
            chars.add((char) ('A' + i));
        }
        //随机添加4个字母到验证码中
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int rindex = r.nextInt(chars.size());
            codes.append(chars.get(rindex));
        }
        //随机添加一个数字到验证码中
        int rnum = r.nextInt(10);
        codes.append(rnum);
        //打乱验证码中字符顺序
        for (int i = 0; i < codes.length(); i++) {
            int rindex1 = r.nextInt(5);
            char temp = codes.charAt(i);
            codes.setCharAt(i, codes.charAt(rindex1));
            codes.setCharAt(rindex1, temp);
        }
        return codes.toString();


    }

    //输入错误弹出弹框
    private void showJDialog(String content) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(200, 100);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);
        //创建文字对象添加到弹框中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);
        //让弹框显示
        jDialog.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (login == e.getSource()) {
            String userNameText = userName.getText();
            String passwordText = password.getText();
            System.out.println(passwordText);
            String verifCodeText = verifCode.getText();
            if (!(verifCodeText.equalsIgnoreCase(verifCodeR))) {
                showJDialog("验证码输入错误,请重新输入");
                verifCodeR = verifCode();
                jLabelverifCodeValue.setText(verifCodeR);
                return;
            }
            if (userNameText.equals("") || passwordText.equals("")) {
                showJDialog("密码或用户名输入为空,请重新输入");
                new LoginJFrame();
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                if (userNameText.equals(list.get(i).getUsername())) {
                    if (passwordText.equals(list.get(i).getPassword())) {
                        new GameJFrame();
                        return;
                    } else {
                        showJDialog("密码输入错误,请重新输入");
                        new LoginJFrame();
                        return;
                    }
                }
            }

            showJDialog("用户名输入错误,请重新输入");
            new LoginJFrame();


        } else if (e.getSource() == jLabelverifCodeValue) {
            verifCodeR = verifCode();
            jLabelverifCodeValue.setText(verifCodeR);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
