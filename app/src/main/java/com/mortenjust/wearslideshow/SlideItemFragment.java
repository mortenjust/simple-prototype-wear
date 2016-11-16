package com.mortenjust.wearslideshow;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SlideItemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SlideItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SlideItemFragment extends Fragment {

    private static final String ARG_IMAGE = "imageId";
    private static final String ARG_SCROLL = "scrollPositionY";
    private static final String ARG_NAME = "name";
    int mImageId;
    int mScrollY;
    String mName;

    ImageView sbsImageView;
    ScrollView scrollView;
    ImageView bottomSheetImage;
    String TAG = "mj.sbsitemfrag";
    Handler bottomSheetDownHandler = new Handler();
    boolean wasScrolling = false;


    private OnFragmentInteractionListener mListener;

    public SlideItemFragment() {
        // Required empty public constructor
    }


    public static SlideItemFragment newInstance(int imageId, int scrollPositionY, String name) {
        SlideItemFragment fragment = new SlideItemFragment();
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
        Log.d(TAG, "onCreate: " + mName);

    }

    public void becameVisible(){
        setupScrollView(scrollView);
        resetBottomSheet();
//        startBottomSheetTimer();
    }

    void resetAndStartBottomSheetTimer(){
        resetBottomSheet();
        startBottomSheetTimer();
    }

    void resetBottomSheet() {
        Log.d(TAG, "resetBottomSheet: ");
        bottomSheetImage.setY(Util.getScreenSize(getActivity()).y + 50);

//        bottomSheetImage.setY(Util.getScreenSize(getActivity()).y - 50);
//
//        int targetY = Util.getScreenSize(getActivity()).y - 50;
//        bottomSheetImage.animate()
//                .y(targetY)
//                .setDuration(50)
//                .setInterpolator(new FastOutSlowInInterpolator())
//                .start();
    }

    void startBottomSheetTimer(){
//        Log.d(TAG, "startBottomSheetTimer: ");
//        // reset handler
//
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                Log.d(TAG, "run: ");
//                bottomSheetDownHandler.removeCallbacks(this);
//                animateBottomSheetDown();
//            }
//        };
//
//        bottomSheetDownHandler.removeCallbacks(runnable);
//        bottomSheetDownHandler.postDelayed(runnable, 1000);
    }

    void animateBottomSheetDown() {
        Log.d(TAG, "animateBottomSheetDown: ");

        if(bottomSheetImage != null && getActivity() != null){
        bottomSheetImage.animate()
                .y(Util.getScreenSize(getActivity()).y)
                .setDuration(300)
                .setInterpolator(new LinearOutSlowInInterpolator())
                .start();
        } else {
            Log.d(TAG, "animateBottomSheetDown: ButtomsheetImage was NULL");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_sbs_item, container, false);
        sbsImageView = (ImageView) v.findViewById(R.id.sbs_image);
        scrollView = (ScrollView) v.findViewById(R.id.scrollView);
//        bottomSheet = (BottomSheetFragment) v.findViewById(R.id.bottom_sheet);
//        bottomSheet = (BottomSheetFragment) getFragmentManager().findFragmentById(R.id.bottom_sheet);

        bottomSheetImage = (ImageView) v.findViewById(R.id.bottom_sheet_image);
        resetBottomSheet();
//        startBottomSheetTimer();

        setupScrollView(scrollView);
        setImage(mImageId, sbsImageView);
        setupViewListeners();

        return v;
    }

    void setupViewListeners(){
//  TODO: Fix this shit
//        bottomSheetImage.setY(100);
//        bottomSheetImage.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.d(TAG, "onTouch: picture touched");
//                switch (event.getAction()){
//
//                    case MotionEvent.ACTION_MOVE:
//                        Log.d(TAG, "onTouch: moved");
//                        bottomSheetImage.setY(event.getRawY());
//                        break;
//                }
//                return true;
//            }
//        });
    }

    void setupImageListeners(ImageView i) {
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
        Log.d(TAG, "setImage: resname " + resname);
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
                if (scrollY > oldScrollY) {
                    Log.d(TAG, "onScrollChange: scrolled down, so peek");
                    resetBottomSheet();
                    wasScrolling = true;
                }
            }
        });

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_UP:
                        if(wasScrolling){
                            startBottomSheetTimer();
                            wasScrolling=false;
                        }
                        break;
                    default:
                        break;

                }
                return false;
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
