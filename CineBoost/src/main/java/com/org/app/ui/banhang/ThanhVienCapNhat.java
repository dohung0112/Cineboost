/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.banhang;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.org.app.controller.ThanhVienDao;
import com.org.app.entity.ThanhVien;
import com.org.app.helper.InputValidlHelper;
import com.org.app.util.ValidationUtil;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 *
 * @author intfs
 */
public class ThanhVienCapNhat extends javax.swing.JDialog {
    ThanhVienDao tvdao = new ThanhVienDao();
    ThanhVien tv = null;
    List<ThanhVien> tvList = new ArrayList<>();
    int index = -1;
    boolean isSearch = true;

    private static String ADD_PANEL = "them";
    private static String SEARCH_PANEL = "update";
    private static String BTN_STATE_ADD = "Đăng ký thành viên";
    private static String BTN_STATE_SEARCH = "Nhập mã thành viên";
    private static String BTN_GET_ADD = "Đăng ký";
    private static String BTN_GET_SEARCH = "Thêm mã Thành Viên";
    
    CardLayout l;
    int xy, xx;
    /**
     * Creates new form ThanhVienCapNhat
     */
    public ThanhVienCapNhat() {
        FlatIntelliJLaf.setup();
        initComponents();
        l = (CardLayout)pnlContent.getLayout();
        JTextFieldDateEditor txtFld = (JTextFieldDateEditor) dcNgaySinh.getDateEditor();
         txtFld.setBackground(pnlAdd.getBackground());
         txtFld.setHorizontalAlignment(SwingConstants.CENTER);
         txtFld.setBorder(txtTen.getBorderO());
         loadThanhVienList();
         rdoTen.setSelected(true);
         setState();
         btnThem.setVisible(false);
         setUpCards();
         showCard();
    }
    
    public ThanhVienCapNhat(JFrame parent) {
        this();
        setLocationRelativeTo(parent);
    }
    
    private void setUpCards() {
        pnlContent.add(pnlAdd, ADD_PANEL);
        pnlContent.add(pnlSearch, SEARCH_PANEL);
    }
    
    private String getCardName() {
        return isSearch? SEARCH_PANEL : ADD_PANEL;
    }

    private void showCard() {
        l.show(pnlContent, getCardName());
        btnThem.setText(!isSearch? BTN_STATE_SEARCH : BTN_STATE_ADD);
        btnGet.setText(!isSearch? BTN_GET_ADD : BTN_GET_SEARCH);
    }
    
    private List<ThanhVien> loadThanhVienList() {
        try {
            tvList = tvdao.selectAll();
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboTen.getModel();
            model.removeAllElements();
            for (int i = 0; i < tvList.size(); i++) {
                model.addElement(tvList.get(i));
            }
            if(tvList.size() > 0) index = 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    private void showSelectedThanhVien() {
        ThanhVien t = null;
        if(cboTen.isEnabled()) {
            t = (ThanhVien)cboTen.getSelectedItem();
            tv = t;
        }
        else {
            t = ThanhVienSearchByDienThoai(txtTimDienThoai.getText().trim());
            tv = t;
        }
        String ma = t == null? "" : t.getMakh();
        String id = t == null? "" : t.getID();
        txtMa.setText(ma);
        lblId.setText(id);
    }
    
    private ThanhVien ThanhVienSearchByDienThoai(String dt) {
        for(ThanhVien tvi : tvList) {
            if(tvi.getSoDT().equals(dt)) {
                System.out.println("found");
              return tvi;
            }
        }
       return null;
    }
    
    private void btnStateListener() {
        isSearch = !isSearch;
    }
    
    
    public void setColorEntered_Minimize() {
        pnlMinimize.setBackground(Color.decode("#999999"));
    }

    public void setColorEntered_Maximize() {
        pnlMaximize.setBackground(Color.decode("#999999"));
    }

    public void setColorEntered_Close() {
        pnlClose.setBackground(Color.decode("#999999"));
    }

    public void setColorExit_Minimize() {
        pnlMinimize.setBackground(Color.decode("#000000"));
    }

    public void setColorExit__Maximize() {
        pnlMaximize.setBackground(Color.decode("#000000"));
    }

    public void setColorExit__Close() {
        pnlClose.setBackground(Color.decode("#000000"));
    }
    
    public ThanhVien getThanhVien() {
        return this.tv;
    }
    
    public void setState() {
       
        txtTimDienThoai.setEnabled( rdoDienThoai.isSelected());
        cboTen.setEnabled(rdoTen.isSelected());
        showSelectedThanhVien();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgGioiTinh = new javax.swing.ButtonGroup();
        bgLoc = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        pnlContent = new javax.swing.JPanel();
        pnlAdd = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDienThoai = new javax.swing.JFormattedTextField();
        txtTen = new com.org.app.weblaf.InputTextField();
        jLabel3 = new javax.swing.JLabel();
        rdoNu = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new com.org.app.weblaf.InputTextField();
        jLabel2 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        dcNgaySinh = new com.toedter.calendar.JDateChooser();
        pnlSearch = new javax.swing.JPanel();
        txtTimDienThoai = new javax.swing.JFormattedTextField();
        cboTen = new com.org.app.weblaf.ComboBoxSuggestion();
        txtMa = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rdoTen = new javax.swing.JRadioButton();
        rdoDienThoai = new javax.swing.JRadioButton();
        lblId = new javax.swing.JLabel();
        btnThem = new com.org.app.weblaf.ButtonGradient();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        btnThem1 = new com.org.app.weblaf.ButtonGradient();
        btnGet = new com.org.app.weblaf.ButtonGradient();
        pnlHome = new javax.swing.JPanel();
        pnlActions = new javax.swing.JPanel();
        pnlMinimize = new javax.swing.JPanel();
        lblMinimize = new javax.swing.JLabel();
        pnlMaximize = new javax.swing.JPanel();
        lblMaximize = new javax.swing.JLabel();
        pnlClose = new javax.swing.JPanel();
        lblClose = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        pnlContent.setOpaque(false);
        pnlContent.setLayout(new java.awt.CardLayout());

        pnlAdd.setBackground(new java.awt.Color(235, 238, 200));

        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel1.setText("Họ tên:");

        txtDienThoai.setBackground(new java.awt.Color(235, 238, 200));
        txtDienThoai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(153, 152, 141)));
        try {
            txtDienThoai.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDienThoai.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        txtDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDienThoaiKeyReleased(evt);
            }
        });

        txtTen.setBackground(new java.awt.Color(235, 238, 200));
        txtTen.setFocus(new java.awt.Color(204, 204, 204));
        txtTen.setPreferredSize(new java.awt.Dimension(168, 25));
        txtTen.setUnderlineColor(new java.awt.Color(153, 152, 141));
        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel3.setText("Ngày sinh:");

        rdoNu.setText("Nữ");
        rdoNu.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel5.setText("Giới tính:");

        txtEmail.setBackground(new java.awt.Color(235, 238, 200));
        txtEmail.setFocus(new java.awt.Color(204, 204, 204));
        txtEmail.setUnderlineColor(new java.awt.Color(153, 152, 141));

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel2.setText("Điện thoại:");

        rdoNam.setText("Nam");
        rdoNam.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel4.setText("Email:");

        dcNgaySinh.setDateFormatString("dd/MM/yy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(dcNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlAddLayout = new javax.swing.GroupLayout(pnlAdd);
        pnlAdd.setLayout(pnlAddLayout);
        pnlAddLayout.setHorizontalGroup(
            pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlAddLayout.setVerticalGroup(
            pnlAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAddLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        pnlContent.add(pnlAdd, "card2");

        pnlSearch.setOpaque(false);

        txtTimDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimDienThoaiKeyReleased(evt);
            }
        });

        cboTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenActionPerformed(evt);
            }
        });

        txtMa.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 102)));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Mã thành viên:");

        bgLoc.add(rdoTen);
        rdoTen.setText("Tìm theo tên:");
        rdoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTenActionPerformed(evt);
            }
        });

        bgLoc.add(rdoDienThoai);
        rdoDienThoai.setText("Tìm theo số điện thoại:");
        rdoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDienThoaiActionPerformed(evt);
            }
        });

        lblId.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSearchLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtTimDienThoai)
                        .addComponent(cboTen, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                        .addComponent(txtMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(rdoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSearchLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(rdoTen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlSearchLayout.createSequentialGroup()
                        .addComponent(cboTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(rdoDienThoai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel9))
                    .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        pnlContent.add(pnlSearch, "card3");

        btnThem.setBackground(new java.awt.Color(204, 204, 204));
        btnThem.setText("Đăng ký thành viên");
        btnThem.setColor1(new java.awt.Color(191, 210, 167));
        btnThem.setColor2(new java.awt.Color(179, 200, 167));
        btnThem.setFont(new java.awt.Font("Leelawadee UI", 1, 10)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnThem1.setBackground(new java.awt.Color(204, 204, 204));
        btnThem1.setText("Hủy");
        btnThem1.setColor1(new java.awt.Color(233, 115, 139));
        btnThem1.setColor2(new java.awt.Color(217, 123, 123));
        btnThem1.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnGet.setBackground(new java.awt.Color(204, 204, 204));
        btnGet.setText("Thêm mã Thành Viên");
        btnGet.setColor1(new java.awt.Color(168, 207, 230));
        btnGet.setColor2(new java.awt.Color(146, 202, 213));
        btnGet.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        btnGet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(btnThem1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnGet, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(btnGet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLayeredPane1)
                        .addGap(26, 26, 26))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlHome.setBackground(new java.awt.Color(51, 51, 51));
        pnlHome.setMaximumSize(new java.awt.Dimension(890, 25));
        pnlHome.setMinimumSize(new java.awt.Dimension(890, 25));
        pnlHome.setPreferredSize(new java.awt.Dimension(890, 30));
        pnlHome.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlHomeMouseDragged(evt);
            }
        });
        pnlHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlHomeMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlHomeMousePressed(evt);
            }
        });
        pnlHome.setLayout(new java.awt.BorderLayout());

        pnlActions.setBackground(new java.awt.Color(51, 51, 51));
        pnlActions.setMinimumSize(new java.awt.Dimension(90, 28));
        pnlActions.setPreferredSize(new java.awt.Dimension(90, 28));
        pnlActions.setLayout(new java.awt.GridLayout(1, 0));

        pnlMinimize.setBackground(new java.awt.Color(51, 51, 51));

        lblMinimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_minus_18px_1.png"))); // NOI18N
        lblMinimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblMinimizeMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnlMinimizeLayout = new javax.swing.GroupLayout(pnlMinimize);
        pnlMinimize.setLayout(pnlMinimizeLayout);
        pnlMinimizeLayout.setHorizontalGroup(
            pnlMinimizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pnlMinimizeLayout.setVerticalGroup(
            pnlMinimizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMinimize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

        pnlActions.add(pnlMinimize);

        pnlMaximize.setBackground(new java.awt.Color(51, 51, 51));

        lblMaximize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_rectangle_stroked_18px.png"))); // NOI18N
        lblMaximize.setEnabled(false);

        javax.swing.GroupLayout pnlMaximizeLayout = new javax.swing.GroupLayout(pnlMaximize);
        pnlMaximize.setLayout(pnlMaximizeLayout);
        pnlMaximizeLayout.setHorizontalGroup(
            pnlMaximizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMaximize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pnlMaximizeLayout.setVerticalGroup(
            pnlMaximizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMaximize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

        pnlActions.add(pnlMaximize);

        pnlClose.setBackground(new java.awt.Color(51, 51, 51));

        lblClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_multiply_18px_1.png"))); // NOI18N
        lblClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCloseMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblCloseMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnlCloseLayout = new javax.swing.GroupLayout(pnlClose);
        pnlClose.setLayout(pnlCloseLayout);
        pnlCloseLayout.setHorizontalGroup(
            pnlCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pnlCloseLayout.setVerticalGroup(
            pnlCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

        pnlActions.add(pnlClose);

        pnlHome.add(pnlActions, java.awt.BorderLayout.LINE_END);

        lblTitle.setForeground(new java.awt.Color(204, 204, 204));
        lblTitle.setText("Thêm mã thành viên");
        lblTitle.setPreferredSize(new java.awt.Dimension(200, 21));
        pnlHome.add(lblTitle, java.awt.BorderLayout.WEST);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pnlHome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDienThoaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDienThoaiKeyReleased
//        String phone = txtDienThoai.getText().replaceAll("\\s+", "").trim();
//        phone = phone.replaceAll("(.+?)(-)$", "$1");
//        try {
//            System.out.println("phone = "+phone);
//            if(ValidationUtil.dienThoai(phone))
//        } catch (ValidatorException ex) {
//            lblPhone.setForeground(Color.red);
//        }
    }//GEN-LAST:event_txtDienThoaiKeyReleased

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        btnStateListener();
        showCard();
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void lblMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseEntered
        // TODO add your handling code here:
        setColorEntered_Minimize();
    }//GEN-LAST:event_lblMinimizeMouseEntered

    private void lblMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseExited
        // TODO add your handling code here:
        setColorExit_Minimize();
    }//GEN-LAST:event_lblMinimizeMouseExited

    private void lblMinimizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMousePressed
       
    }//GEN-LAST:event_lblMinimizeMousePressed

    private void lblCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseEntered
        // TODO add your handling code here:
        setColorEntered_Close();
    }//GEN-LAST:event_lblCloseMouseEntered

    private void lblCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseExited
        // TODO add your handling code here:
        setColorExit__Close();
    }//GEN-LAST:event_lblCloseMouseExited

    private void lblCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMousePressed
        this.dispose();
    }//GEN-LAST:event_lblCloseMousePressed

    private void pnlHomeMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHomeMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_pnlHomeMouseDragged

    private void pnlHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHomeMouseClicked
//        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
//            if (ThanhVienCapNhat.this.getExtendedState() == MAXIMIZED_BOTH) {
//                MainFrame.this.setExtendedState(JFrame.NORMAL);
//            } else {
//                MainFrame.this.setExtendedState(MAXIMIZED_BOTH);
//            }
//        }
    }//GEN-LAST:event_pnlHomeMouseClicked

    private void pnlHomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHomeMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_pnlHomeMousePressed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void cboTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenActionPerformed
        showSelectedThanhVien();
    }//GEN-LAST:event_cboTenActionPerformed

    private void rdoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTenActionPerformed
       setState();
    }//GEN-LAST:event_rdoTenActionPerformed

    private void rdoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDienThoaiActionPerformed
       setState();
    }//GEN-LAST:event_rdoDienThoaiActionPerformed

    private void btnGetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnGetActionPerformed

    private void txtTimDienThoaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimDienThoaiKeyReleased
            showSelectedThanhVien();        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimDienThoaiKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThanhVienCapNhat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThanhVienCapNhat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThanhVienCapNhat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThanhVienCapNhat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThanhVienCapNhat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgGioiTinh;
    private javax.swing.ButtonGroup bgLoc;
    private com.org.app.weblaf.ButtonGradient btnGet;
    private com.org.app.weblaf.ButtonGradient btnThem;
    private com.org.app.weblaf.ButtonGradient btnThem1;
    private com.org.app.weblaf.ComboBoxSuggestion cboTen;
    private com.toedter.calendar.JDateChooser dcNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblMaximize;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlActions;
    private javax.swing.JPanel pnlAdd;
    private javax.swing.JPanel pnlClose;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlHome;
    private javax.swing.JPanel pnlMaximize;
    private javax.swing.JPanel pnlMinimize;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JRadioButton rdoDienThoai;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JRadioButton rdoTen;
    private javax.swing.JFormattedTextField txtDienThoai;
    private com.org.app.weblaf.InputTextField txtEmail;
    private javax.swing.JLabel txtMa;
    private com.org.app.weblaf.InputTextField txtTen;
    private javax.swing.JFormattedTextField txtTimDienThoai;
    // End of variables declaration//GEN-END:variables
}
