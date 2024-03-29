package edu.uw.tcss450.blog;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uw.tcss450.MyblogRecyclerViewAdapter;
import edu.uw.tcss450.R;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class blogFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public blogFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static blogFragment newInstance(int columnCount) {
        blogFragment fragment = new blogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyblogRecyclerViewAdapter(BlogGenerator.BLOGS, this::displayBlogPost));
        }
        return view;
    }

    private void displayBlogPost(final BlogPost post) {
        final Bundle args = new Bundle();
        //Log.d("student", "R.string.key_student_view");
        args.putSerializable(getString(R.string.key_BlogPost), post);

        NavController nc = Navigation.findNavController(getView());
        if (nc.getCurrentDestination().getId() != R.id.nav_blog) {
            nc.navigateUp();
        }
        nc.navigate(R.id.action_nav_blog_to_nav_blogPost, args);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(BlogPost post);
    }
}
