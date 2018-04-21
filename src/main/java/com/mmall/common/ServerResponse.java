package com.mmall.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

//泛型代表响应的数据对象data是什么类型的
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) //如果数据为null则不会序列化给前端
public class ServerResponse<T> implements Serializable{

    private String msg;
    private int status;
    private T data;

    private ServerResponse(int status){
        this.status = status;
    }
    //测试：如果此处为public，那么当泛型为String的时候，不会调用此构造器，当泛型不为String的时候就调用这个
    //如果是想要在data里面放string类型的数据，那么需要下面的public方法createBySuccess(String msg,T data)
    private ServerResponse(int status , T data){
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status , String msg ,T data){
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    private ServerResponse(int status , String msg){
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore //序列化之后不会显示在json里面
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }
    //以下三个方法都会显示在序列化里面
    public int getStatus(){
        return status;
    }

    public T getData(){
        return data;
    }

    public String getMsg(){
        return msg;
    }

    //在public与返回值之间的<T>表明这是一个泛型方法
    //这个T可以出现在这个泛型方法的任意位置
    //静态方法无法访问类上定义的泛型；如果静态方法操作的引用数据类型不确定的时候，必须要将泛型定义在方法上。
    //也就是说，如果静态方法要使用泛型的话，必须将静态方法也定义成泛型方法 。如果将中间的<T>去掉将会报错
    public static <T> ServerResponse<T> createBySuccess(){//创建一个默认状态为成功的对象
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg){//创建一个默认状态为成功并且有msg信息的对象
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data){//创建一个带有data的成功的响应的对象
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    //由于带有两个参数的只有这个，所以即使传入的数据为String类型也可以创建一个T=String的对象了
    public static <T> ServerResponse<T> createBySuccess(String msg,T data){//创建一个带有data的成功的响应的对象
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    //此方法代表Enum的Error码可以对应不同的msg
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }




    /*public static void main(String[] args) {
        ServerResponse sr1 = new ServerResponse(1,new Object());
        ServerResponse sr2 = new ServerResponse(1,"55");
        System.out.println("console");
    }*/

}
