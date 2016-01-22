package com.mortenjust.chatappoidssbs;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SbsItemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SbsItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SbsItemFragment extends Fragment {

    private static final String ARG_IMAGE = "imageId";
    private static final String ARG_SCROLL = "scrollPositionY";
    private static final String ARG_NAME = "name";
    private int mImageId;
    private int mScrollY;
    private String mName;

    private ImageView sbsImageView;
    private ScrollView scrollView;
    String TAG = "mj.sbsitemfrag";

    private OnFragmentInteractionListener mListener;

    public SbsItemFragment() {
        // Required empty public constructor
    }


    public static SbsItemFragment newInstance(int imageId, int scrollPositionY, String name) {
        SbsItemFragment fragment = new SbsItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE, imageId);
        args.putInt(ARG_SCROLL, scrollPositionY);
        args.putString(ARG_NAME, name);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImageId = getArguments().getInt(ARG_IMAGE);
            mName = getArguments().getString(ARG_NAME);
            mScrollY = getArguments().getInt(ARG_SCROLL);
        }
    }

    public void becameVisible(){
        // somhow this is called before onCreate, so not everything is available
        Log.d(TAG, "becameVisible: "+mName);
        Log.d(TAG, "becameVisible: "+getArguments().getString(ARG_NAME));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_sbs_item, container, false);
        sbsImageView = (ImageView) v.findViewById(R.id.sbs_image);
        scrollView = (ScrollView) v.findViewById(R.id.scrollView);
        setupScrollView(scrollView);

        setImage(mImageId, sbsImageView);

        setupViewListeners(v);
        return v;
    }

    void setupViewListeners(View v){
    }

    void setupImageListeners(ImageView i){
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: name is " + mName);
                Toast.makeText(getContext(), mName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    void setImage(int id, ImageView view){
        Drawable imageDrawable = getResources().getDrawable(id, getContext().getTheme());
        int imageHeight = imageDrawable.getMinimumHeight();
        imageHeight = (int) Util.pxToDp(imageHeight, getContext());

        Log.d(TAG, "setImage: height:"+imageHeight);
        view.getLayoutParams().height = imageHeight;
        view.setImageResource(id);
        String resname = getResources().getResourceName(id);
        Log.d(TAG, "setImage: resname "+resname);
        setupImageListeners(view);
    }

    void setupScrollView(final ScrollView scrollView){
        Log.d(TAG, "setupScrollView: scrolling to " + mScrollY);

        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0, mScrollY);
            }
        });

        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d(TAG, "onScrollChange: "+scrollY);
            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
