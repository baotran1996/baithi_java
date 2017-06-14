package danhba.a56th2.baotran.danhba.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import danhba.a56th2.baotran.danhba.R;
import danhba.a56th2.baotran.danhba.model.Contact;

/**
 * Created by BaoTran on 6/14/2017.
 */

public class ContactAdapter extends ArrayAdapter<Contact>{
    private Context context;
    private int resource;
    private List<Contact> arrayContact;
    public ContactAdapter(Context context , int resource, List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.arrayContact = objects;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView , ViewGroup parent) {
        ViewHoler viewHoler;
        if (convertView== null){
            viewHoler = new ViewHoler();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_contact_listview,parent,false);
            viewHoler.imgAvatar=(ImageView)convertView.findViewById(R.id.img_avatar);
            viewHoler.tvName=(TextView) convertView.findViewById(R.id.tv_name);
            viewHoler.tvNumber=(TextView) convertView.findViewById(R.id.tv_number);
            convertView.setTag(viewHoler);
        }
        else
        {
            viewHoler=(ViewHoler)   convertView.getTag();
        }
        Contact contact = arrayContact.get(position);
        viewHoler.tvName.setText(contact.getmName());
        viewHoler.tvNumber.setText(contact.getmNumber());
        if(contact.isMale()){
            viewHoler.imgAvatar.setBackgroundResource(R.drawable.male);
        }
        else {
            viewHoler.imgAvatar.setBackgroundResource(R.drawable.female);
        }
        return convertView;
    }
    public class ViewHoler{
        ImageView imgAvatar;
        TextView tvName;
        TextView tvNumber;
    }
}

