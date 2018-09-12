/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carbon.quatermain.data;

/**
 *
 * @author jum_M
 */
public enum TypeCase {
    PLAIN("plain"),
    MOUNTAIN("mountain");
   
  private String name = "";
   
  TypeCase(String name){
    this.name = name;
  }
   
  public String toString(){
    return name;
  }
}
