package com.example.myinventory2201738251;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {
    private Context mContext;
    private Cursor mcursor = InvMain.IB.getDataAll();



    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick (int position);
    }
    public void setOnClickItemClickListener (OnItemClickListener listener ){
        mListener = listener;
    }




    @NonNull
    @Override
    public InventoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);

        return new ViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryAdapter.ViewHolder holder, int position) {
        Inventory inventory = InvMain.IB.InventVector.get(position);
//        String name = mcursor.getString(mcursor.getColumnIndex(DBHelper.FIELD_INVENTORY_NAME));
//        String quantity = mcursor.getString(mcursor.getColumnIndex(DBHelper.FIELD_INVENTORY_QUANTITY));
//        String desc = mcursor.getString(mcursor.getColumnIndex(DBHelper.FIELD_INVENTORY_DESC));
        holder.tv_name.setText(inventory.getName());
        holder.tv_quantity.setText(inventory.getQuantity());
        holder.tv_desc.setText(inventory.getDesc());

    }

    @Override
    public int getItemCount() {
        return InvMain.IB.InventVector.size();
    }
    public void swapCursor (Cursor newCursor){
        if (mcursor != null){
            mcursor.close();
        }
        mcursor = newCursor;
        if (newCursor != null){
            notifyDataSetChanged();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_quantity;
        TextView tv_desc;
        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_quantity = itemView.findViewById(R.id.tv_qty);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position=getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
