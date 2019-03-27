package com.example.jeevandeepassociates;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> implements Filterable {
    private List<Items> items;
    private List<Items> itemsCopy;

    private Context context;

    public ProgrammingAdapter(List<Items> items, Context context) {
        this.items = items;
        this.context = context;
        itemsCopy = new ArrayList<>(items);

    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_view, viewGroup, false);
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingViewHolder holder, int i) {
        Items item = items.get(i);
        final String product = item.getProduct();
        final String packing = item.getPacking();
        final int qty = item.getQty();
        final int netRate = item.getNetRate();

        holder.txt.setText(item.getProduct());
        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductSelectionActivity.class);
                intent.putExtra("product", product);
                intent.putExtra("packing", packing);
                intent.putExtra("qty", qty);
                intent.putExtra("netRate", netRate);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        public LinearLayout linear;

        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt);
            linear = itemView.findViewById(R.id.linear);
        }
    }

    @Override
    public Filter getFilter() {
        return itemsFilter;
    }

    private Filter itemsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Items> filteredItems = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredItems.addAll(itemsCopy);
            } else {
                String filterPattern = constraint.toString().toUpperCase().trim();
                for (Items item : itemsCopy) {
                    if (item.getProduct().toUpperCase().contains(filterPattern)) {
                        filteredItems.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredItems;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            items.clear();
            items.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };
}
