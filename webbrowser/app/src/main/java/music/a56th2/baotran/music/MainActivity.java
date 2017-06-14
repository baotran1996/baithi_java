package music.a56th2.baotran.music;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public Button btngo;
    public Button btnback;
    public Button btnfoward;
    public Button btnreload;
    public EditText inputUrl;
    public WebView mainview ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainview = (WebView) findViewById(R.id.mainview);
        mainview.setWebViewClient(new myBrowser());
        btngo = (Button) findViewById(R.id.btngo);
        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUrl();
            }
        });
        btnback = (Button) findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        btnfoward = (Button) findViewById(R.id.btnfoward);
        btnfoward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foward();
            }
        });
        btnreload = (Button) findViewById(R.id.btnreload);
        btnreload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reload();
            }
        });
    }
    private String getinputUrl()
    {
        inputUrl =(EditText) findViewById(R.id.txtinputurl);
        String url;
        url = inputUrl.getText().toString();
        return url;
    }
    private void back(){
        mainview.goBack();
    }
    private void foward(){
        mainview.goForward();
    }
    private void reload (){
        mainview.reload();
    }
    private void showUrl()
    {
        String url;
        url= getinputUrl();
        mainview.getSettings().setLoadsImagesAutomatically(true);
        mainview.getSettings().setJavaScriptEnabled(true);
        mainview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mainview.loadUrl(url);}

   private class myBrowser extends WebViewClient{
       @Override
       public boolean shouldOverrideUrlLoading(WebView view, String Url)
       {
           view.loadUrl(Url);
           inputUrl.setText(Url);
           return true;
       }
   }
}
