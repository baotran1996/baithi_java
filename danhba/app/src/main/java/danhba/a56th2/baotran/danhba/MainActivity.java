package danhba.a56th2.baotran.danhba;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import danhba.a56th2.baotran.danhba.adapter.ContactAdapter;
import danhba.a56th2.baotran.danhba.model.Contact;

public class MainActivity extends AppCompatActivity {
    private List<Contact> arrayContact;
    private ContactAdapter adapter;
    private EditText editText4;
    private  EditText editText5;
    private RadioButton rbtnMale;
    private  RadioButton rbtnFemale;
    private Button button;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWidget();
        arrayContact = new ArrayList<>();
        adapter = new ContactAdapter(this,R.layout.item_contact_listview,arrayContact);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               showDialogConfirm(position);
            }
        });
    }
    public  void setWidget(){
        editText4=( EditText) findViewById(R.id.editText4);
        editText5=( EditText) findViewById(R.id.editText5);
        rbtnMale=(RadioButton) findViewById(R.id.rbtnmale);
        rbtnFemale=(RadioButton) findViewById(R.id.rbtnfemale);
        button=(Button) findViewById(R.id.button);
        lv= (ListView) findViewById(R.id.lv);
    }
    public void addContact(View view){
        if (view.getId()==R.id.button){
            String name=editText4.getText().toString().trim();
            String number=editText5.getText().toString().trim();
            boolean isMale= true;
            if(rbtnMale.isChecked()){
                isMale=true;
            }
            else
                isMale=false;
            if (TextUtils.isEmpty(name)|| TextUtils.isEmpty(number)){
                Toast.makeText(this, "Mời bạn xinh đẹp nhập vào tên hoặc số đện thoại", Toast.LENGTH_SHORT).show();
            }
            else {
                Contact contact= new Contact(isMale, name, number);
                arrayContact.add(contact);
            }
            adapter.notifyDataSetChanged();
        }
    }
    public void showDialogConfirm(final int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        Button btnCall = (Button) dialog.findViewById(R.id.btn_call);
        Button btnSendMessage = (Button) dialog.findViewById(R.id.btn_send_message);

    btnCall.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           interCall(position);
        }
    });
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSendMesseage(position);
            }
        });
    dialog.show();
    }
    private void intentSendMesseage(int position) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + arrayContact.get(position).getmNumber()));
        startActivity(intent);
    }
    private void interCall(int position) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + arrayContact.get(position).getmNumber()));
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && action != null && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
    }
}
