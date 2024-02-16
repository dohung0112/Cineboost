/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import com.org.app.entity.Phim;
import com.org.app.helper.DateHelper;
import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class PhimValidator extends Validator<Phim>{
    private Phim p;
    
    public PhimValidator (Phim p){
        this.p = p;
    }
    PhimValidator(){        
    }
    
    @Override
    public void check(Phim p) throws ValidatorException {
        this.p = p;
//        checkTenPhim();
    }
    
    public void checkTenPhim() throws ValidatorException {
        if(p.getTen().length() < 3 || p.getTen().length() > 50) 
            throw new ValidatorException("Tên phim phải từ 3 - 50 ký tự");
        else if(!p.getTen().matches("[\\p{L}]+(\\s\\p{L}+)*")) {
            throw new ValidatorException("Tên phim phải là chữ");
        }
    }
    
    
}
