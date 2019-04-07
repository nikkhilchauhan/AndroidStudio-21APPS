package nikhilchauhan.com.dotme;

/**
 * Created by Shade on 5/9/2016.
 */

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    String m = "what";

    private String[] titles = {"DBMS",
            "MP",
            "CN",
            "TCS",
            "AOS",m};


    private int[] images = {
            R.drawable.ic_book_black_24dp,
            R.drawable.ic_book_black_24dp,
            R.drawable.ic_book_black_24dp,
            R.drawable.ic_book_black_24dp,
            R.drawable.ic_book_black_24dp,
            R.drawable.ic_book_black_24dp};

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView itemImage;
        public TextView itemTitle;

        public ViewHolder(final View itemView) {
            super(itemView);
            itemImage = (ImageView)itemView.findViewById(R.id.item_image);
            itemTitle = (TextView)itemView.findViewById(R.id.item_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                   int position = getAdapterPosition();
//                    Snackbar.make(v, "Click detected on item " + position,
//                            Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemImage.setImageResource(images[i]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

}
