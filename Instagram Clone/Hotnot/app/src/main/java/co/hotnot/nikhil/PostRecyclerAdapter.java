package co.hotnot.nikhil;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {

    public List<Post> post_list;
    public Context context;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    public PostRecyclerAdapter(List<Post> post_list){

        this.post_list = post_list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent, false);
        context = parent.getContext();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        //final String postId = post_list.get(position).PostId;
        final String currentUserId = firebaseAuth.getCurrentUser().getUid();

        String desc_data = post_list.get(position).getDesc();
        holder.setDescText(desc_data);

        String image_url = post_list.get(position).getImage_url();
        String thumbUri = post_list.get(position).getImage_thumb();
        holder.setPostImage(image_url, thumbUri);

        String user_id = post_list.get(position).getUser_id();
        //User Data will be retrieved here...
        firebaseFirestore.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){

                    String userName = task.getResult().getString("name");
                    String userImage = task.getResult().getString("image");

                    holder.setUserData(userName, userImage);


                } else {

                    //Firebase Exception

                }

            }
        });

        try {
            long millisecond = post_list.get(position).getTimestamp().getTime();
            String dateString = DateFormat.format("dd/MM/yyyy", new Date(millisecond)).toString();
            holder.setTime(dateString);
        } catch (Exception e) {

            Toast.makeText(context, "Exception : " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

//        //Get Likes Count
//        firebaseFirestore.collection("Posts/" + postId + "/Likes").addSnapshotListener( new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
//
//                if(!documentSnapshots.isEmpty()){
//
//                    int count = documentSnapshots.size();
//
//                    holder.updateLikesCount(count);
//
//                } else {
//
//                    holder.updateLikesCount(0);
//
//                }
//
//            }
//        });
//
//
//        //Get Likes
//        firebaseFirestore.collection("Posts/" + postId + "/Likes").document(currentUserId).addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
//
//                if(documentSnapshot.exists()){
//
//                    holder.postLikeBtn.setImageDrawable(context.getDrawable(R.mipmap.action_like_accent));
//
//                } else {
//
//                    holder.postLikeBtn.setImageDrawable(context.getDrawable(R.mipmap.action_like_gray));
//
//                }
//
//            }
//        });
//
//        //Likes Feature
//        holder.postLikeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                firebaseFirestore.collection("Posts/" + postId + "/Likes").document(currentUserId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//                        if(!task.getResult().exists()){
//
//                            Map<String, Object> likesMap = new HashMap<>();
//                            likesMap.put("timestamp", FieldValue.serverTimestamp());
//
//                            firebaseFirestore.collection("Posts/" + postId + "/Likes").document(currentUserId).set(likesMap);
//
//                        } else {
//
//                            firebaseFirestore.collection("Posts/" + postId + "/Likes").document(currentUserId).delete();
//
//                        }
//
//                    }
//                });
//            }
//        });

//        holder.postCommentBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent commentIntent = new Intent(context, CommentsActivity.class);
//                commentIntent.putExtra("post_post_id", postId);
//                context.startActivity(commentIntent);
//
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return post_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View mView;

        private TextView descView;
        private ImageView postImageView;
        private TextView postDate;

        private TextView postUserName;
        private CircleImageView postUserImage;

        private ImageView postLikeBtn;
        private TextView postLikeCount;

        private ImageView postCommentBtn;


        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            postLikeBtn = mView.findViewById(R.id.post_like_btn);
            postCommentBtn = mView.findViewById(R.id.post_comment_icon);

        }

        public void setDescText(String descText){

            descView = mView.findViewById(R.id.post_desc);
            descView.setText(descText);

        }

        public void setPostImage(String downloadUri, String thumbUri){

            postImageView = mView.findViewById(R.id.post_image);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.common_google_signin_btn_icon_dark);

            Glide.with(context).applyDefaultRequestOptions(requestOptions).load(downloadUri).thumbnail(
                    Glide.with(context).load(thumbUri)
            ).into(postImageView);

        }

        public void setTime(String date) {

            postDate = mView.findViewById(R.id.post_date);
            postDate.setText(date);

        }

        public void setUserData(String name, String image){

            postUserImage = mView.findViewById(R.id.post_user_image);
            postUserName = mView.findViewById(R.id.post_user_name);

            postUserName.setText(name);

            RequestOptions placeholderOption = new RequestOptions();
            placeholderOption.placeholder(R.drawable.common_google_signin_btn_icon_dark);

            Glide.with(context).applyDefaultRequestOptions(placeholderOption).load(image).into(postUserImage);

        }

        public void updateLikesCount(int count){

            postLikeCount = mView.findViewById(R.id.post_like_count);
            postLikeCount.setText(count + " Likes");

        }

    }

}

