/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.interfaceView;

/**
 *
 * @author THAITHANG
 */
public interface iMessageView {
    int NONE = 0;
    int SUCCESS = 1;
    int FAIL = -1;
    void showMessageAndReloadData(String message, int type);
}
