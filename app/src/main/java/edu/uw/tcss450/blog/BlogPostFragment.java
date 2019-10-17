package edu.uw.tcss450.blog;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import edu.uw.tcss450.R;
import model.Credentials;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogPostFragment extends Fragment {


    public BlogPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog_post, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle args) {
        if (getArguments() != null) {
            Serializable blogpost = getArguments().getSerializable("BlogPost");
            BlogPost post = (BlogPost) blogpost;

            TextView title = view.findViewById(R.id.blog_title);
            TextView pubDate = view.findViewById(R.id.blog_pubDate);
            TextView postText = view.findViewById(R.id.blog_postText);

            title.setText(post.getTitle());
            pubDate.setText(post.getPubDate());
            postText.setText(post.getTeaser());

            String url = post.getUrl();
            Button fullPostButton = view.findViewById(R.id.button_fullPost);
            fullPostButton.setOnClickListener(v -> {
                String url1 = post.getUrl();

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url1));
                startActivity(i);
            });

        }
    }

}
