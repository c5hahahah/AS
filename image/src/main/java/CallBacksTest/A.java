package CallBacksTest;

import android.telecom.Call;

/**
 * Created by my on 2016/6/27.
 */
public class A {

    public String a(final String url, final Callback callback){
        new Thread(){
            @Override
        public void run() {
            super.run();
        callback.get(url);

        }
        }.start();
        return null;
        }
public interface Callback{
    void get(String str);
}
}
