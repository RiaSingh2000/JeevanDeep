package com.example.jeevandeepassociates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<SelectedItems> {
 private Context context;
 private ArrayList<SelectedItems> selItems;
    public CustomAdapter(Context context, int resource, ArrayList<SelectedItems> objects) {
        super(context, resource, objects);
        this.context=context;
        selItems=objects;
    }

    @Nullable
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
            convertView= LayoutInflater.from(context).inflate(R.layout.selected_item_list,parent,false);

           SelectedItems obj=selItems.get(position);

        TextView prd=convertView.findViewById(R.id.prd);
        TextView qty=convertView.findViewById(R.id.qty);
        TextView amt=convertView.findViewById(R.id.amt);
        prd.setText(obj.getProduct());
        qty.setText(Integer.toString(obj.getQty()));
        amt.setText(Double.toString(obj.getAmt()));
        return convertView;
    }
}
