package com.selfish.gene.annotation.customize_annotation.demo_listener;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Administrator on 2017/6/25.
 */
public class AnnotationTest {
    private JFrame mainWin = new JFrame("使用注解绑定事件监听器");

    @ActionListenFor(listener=OkListener.class)
    private JButton ok = new JButton("确定");

    @ActionListenFor(listener=CancelListener.class)
    private JButton cancel = new JButton("取消");

    public void init(){
        JPanel jp = new JPanel();
        jp.add(ok);
        jp.add(cancel);
        mainWin.add(jp);
        ActionListenInstaller.processAnnotations(this);
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.pack();
        mainWin.setVisible(true);
    }

    public static void main(String[] args) {
        new AnnotationTest().init();
    }
}

class OkListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,"单击了确认按钮");
    }
}
class CancelListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,"单击了取消按钮");
    }
}
