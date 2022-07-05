package com.example.universitasku.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universitasku.R;
import com.example.universitasku.model.Students;

public class StudentListAdapter extends ListAdapter<Students, StudentListAdapter.ItemRowHolder>
{
    //creating a variable for on item click listner.
    public OnItemClickListener listener;

    public StudentListAdapter(@NonNull DiffUtil.ItemCallback<Students> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new ItemRowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder holder, int position) {
        final Students students = getItem(position);
        holder.nama.setText(students.getFullName());
        holder.jurusan.setText(students.getJurusan());
        holder.alamat.setText(students.getAlamat());
        holder.prodi.setText(students.getProdi());
        holder.jk.setText(students.getJekel());

    }


    public Students getStudentAt(int position)
    {
        return getItem(position);
    }
    public  static class StudentDiff extends DiffUtil.ItemCallback<Students>
    {

        @Override
        public boolean areItemsTheSame(@NonNull Students oldItem, @NonNull Students newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Students oldItem, @NonNull Students newItem) {
            return oldItem.getFullName().equals(newItem.getFullName()) &&
                    oldItem.getAlamat().equals(newItem.getAlamat()) &&
                    oldItem.getJurusan().equals(newItem.getJurusan()) &&
                    oldItem.getJekel().equals(newItem.getJekel()) &&
                    oldItem.getProdi().equals(newItem.getProdi());

        }
    }
    public class ItemRowHolder extends RecyclerView.ViewHolder{
        private TextView nama,jurusan,alamat,jk,prodi;

        public ItemRowHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.idTVFullName);
            alamat = itemView.findViewById(R.id.idTVAlamat);
            jurusan = itemView.findViewById(R.id.idTVJurusan);
            jk = itemView.findViewById(R.id.idTVJekel);
            prodi = itemView.findViewById(R.id.idTVProdi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null  && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }



    }

    public interface OnItemClickListener {
        void onItemClick(Students model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
