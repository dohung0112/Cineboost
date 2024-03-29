/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import com.org.app.entity.DoAnChiTiet;
import com.org.app.entity.KichCoDoAn;
import com.org.app.helper.InputValidlHelper;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author Admin
 */
public class QLDoAnKemValidator extends Validator<KichCoDoAn> {
    
    private KichCoDoAn kcda;
    

    public enum QLDoAnKemValidatorError {
        DOAN_TRUNG("Đồ ăn với kích cỡ này đã tồn tại trong menu!"),
        GIA_MIN("Giá phải lớn hơn 0");
        
        private String mess; 
        
        QLDoAnKemValidatorError(String mess) {
            this.mess = mess;
        }
        
        public String getMess() {return mess;}
    }
    
    
    public QLDoAnKemValidator(KichCoDoAn kcda) {
        this.kcda = kcda;
    }

    QLDoAnKemValidator() {

    }

    @Override
    public void check(KichCoDoAn kcda) throws ValidatorException {
        this.kcda = kcda;
        checkGia();
    }
    
    public void checkGia() throws ValidatorException {        
        if (kcda.getGia() <= 0) {
            throw new ValidatorException("Giá phải lớn hơn 0");
        }
    }
    
    public void checkDoAn(List<KichCoDoAn> list, KichCoDoAn current, String tenDAMoi, String kcMoi) throws ValidatorException {
        Integer currentID = current.getId();
        System.out.println("curID " + currentID);
        String tenda = current.getDoAn().getTen();
        String id_kichCo = current.getKichco().getId();
        boolean isSameDoAn = (currentID.toString() != "") && (tenda.equals(tenDAMoi)) && (id_kichCo.equals(kcMoi));
        System.out.println("isSame " + isSameDoAn);
        if(currentID.toString() == "" && !isSameDoAn) {
            for(KichCoDoAn n : list){
                String ten = n.getDoAn().getTen();
                String kichCo = n.getKichco().getId();
                if(ten.equals(tenDAMoi) && kichCo.equals(kcMoi)) throw new ValidatorException(QLDoAnKemValidatorError.DOAN_TRUNG.getMess());
            }
        }else if (!isSameDoAn){
            for(KichCoDoAn n : list){
                String ten = n.getDoAn().getTen();
                String kichCo = n.getKichco().getId();
                if(ten.equals(tenDAMoi) && kichCo.equals(kcMoi)) throw new ValidatorException(QLDoAnKemValidatorError.DOAN_TRUNG.getMess());
            }
        }
    }
}
