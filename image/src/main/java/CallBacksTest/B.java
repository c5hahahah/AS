package CallBacksTest;

import android.util.Log;

/**
 * Created by my on 2016/6/27.
 */
public class B {
    public void savetoFile(String str){
        A a = new A();
        a.a(str, new A.Callback() {
            @Override
            public void get(String str) {
                Log.i("12345",str);
            }
        });
        //Log.i();

    }

}
