/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import com.org.app.ui.main.MainFrame;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author intfs
 */
public class FontUtil {
    static String path = "/font/";
    
    
    public static Font getFont(String fontName)  {
        String f = path+fontName;
        Font font = null;
        try {
//            System.out.println(FontUtil.class.getResource(f).getPath().toString());
//            font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(FontUtil.class.getResourceAsStream(path+fontName).toString()));
//            InputStream myStream = new BufferedInputStream(new FileInputStream(FontUtil.class.getResourceAsStream(f).toString()));
//        font = Font.createFont(Font.TRUETYPE_FONT, MainFrame.class.getResourceAsStream(f));   
String path = FontUtil.class.getResource("").toExternalForm();

File ff = new File(path);
            System.out.println("ff "+ ff.getAbsolutePath());
        font = Font.createFont(Font.TRUETYPE_FONT,ff );             
//font = Font.createFont(Font.TRUETYPE_FONT, myStream);

//            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//            ge.registerFont(font);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (FontFormatException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        return font;
    } 
    public static void main(String[] args) {
        System.out.println(MainFrame.class.getResourceAsStream("/resources"));
        java.awt.Font f = FontUtil.getFont("SVN-REX.ttf");
        f.deriveFont(Font.BOLD, 48f);
    }
    
    
    
}
