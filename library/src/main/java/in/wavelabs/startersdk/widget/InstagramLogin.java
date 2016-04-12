package in.wavelabs.startersdk.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by vineelanalla on 03/03/16.
 */
public class InstagramLogin extends Button  implements View.OnClickListener {
    public InstagramLogin(Context context) {
        super(context);
    }

    public InstagramLogin(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InstagramLogin(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public InstagramLogin(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onClick(View v) {

    }
}
