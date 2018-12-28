package com.bwei.day03.persenter;

import com.bwei.day03.callback.MyCallBack;
import com.bwei.day03.model.IMdelImpl;
import com.bwei.day03.view.IView;

import java.util.Map;

public class IPersenterImpl implements IPersenter {

    private IView iView;
    private IMdelImpl iMdel;

    public IPersenterImpl(IView iView){
        this.iView=iView;
        iMdel=new IMdelImpl();
    }

    @Override
    public void getRequest(String dataUrl, Map<String, String> params, Class clazz) {
        iMdel.getRequest(dataUrl, params, clazz, new MyCallBack() {
            @Override
            public void CallBack(Object data) {
                iView.onSuccess(data);
            }
        });
    }

    public void deatch(){
        iMdel=null;
        iView=null;
    }
}
