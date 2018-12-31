package home.stanislavpoliakov.meet8_practice;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHoler> {
    private List<Integer> idList;

    public MyAdapter(List<Integer> idList) {
        this.idList = idList;
    }

    @NonNull
    @Override
    public MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view, parent, false);
        return new MyViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoler holder, int position) {
        holder.imageView.setImageResource(idList.get(position));
    }

    @Override
    public int getItemCount() {
        return idList.size();
    }

    class MyViewHoler extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public MyViewHoler(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}