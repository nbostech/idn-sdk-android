package in.wavelabs.idn.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by vineelanalla on 03/03/16.
 */
public class LinkedInLogin extends Button  implements View.OnClickListener {

    public LinkedInLogin(Context context) {
        super(context);
        init();
    }

    public LinkedInLogin(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public LinkedInLogin(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LinkedInLogin(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private void init(){
        setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

    }
}
