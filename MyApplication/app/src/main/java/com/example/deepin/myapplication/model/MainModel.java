package com.example.deepin.myapplication.model;

import android.util.Log;

import com.example.deepin.myapplication.http.Api;
import com.example.deepin.myapplication.http.helper.RetrofitHelper;
import com.example.deepin.myapplication.listener.MainListener;
import com.example.deepin.myapplication.model.bean.BookBean;
import com.example.deepin.myapplication.rxJava.BaseObserver;
import com.example.deepin.myapplication.rxJava.RxSchedulers;



import io.reactivex.Observable;


public class MainModel {
    public void getData(String id, final MainListener mainListener){

        RetrofitHelper retrofitHelper = new RetrofitHelper(Api.DOUBAN_BASE_HOST);

        Observable<BookBean> observable = retrofitHelper.getBook(id);
        observable.compose(RxSchedulers.<BookBean>compose()).subscribe(new BaseObserver<BookBean>() {
            @Override
            public void onHandleSuccess(BookBean bookBean) {
                mainListener.onSuccess(bookBean);
            }
        });

    }
}
