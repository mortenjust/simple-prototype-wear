package com.mortenjust.chatappoidssbs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.wearable.view.GridViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SideBySideFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SideBySideFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SideBySideFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String TAG = "mj.sidebysidefragment";

    GridViewPager sbsPager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SideBySideFragment() {
        // Required empty public constructor
    }

    public static SideBySideFragment newInstance() {
        SideBySideFragment fragment = new SideBySideFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        v = inflater.inflate(R.layout.fragment_side_by_side, container, false);

        sbsPager = (GridViewPager) v.findViewById(R.id.sbs_pager);
        setupPager(sbsPager);


        return v;
    }

    void setupPager(GridViewPager pager){
        final SbsPagerAdapter adapter = new SbsPagerAdapter(getFragmentManager());
        pager.setOnPageChangeListener(new GridViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, int i1, float v, float v1, int i2, int i3) {
             //   Log.d(TAG, "onPageScrolled: "+i1);
            }

            @Override
            public void onPageSelected(int i, int i1) {
                Log.d(TAG, "onPageSelected: " + i1);
                SbsItemFragment f = (SbsItemFragment) adapter.getFragment(i, i1);
                f.becameVisible();
            }

            @Override
            public void onPageScrollStateChanged(int i) {
              //  Log.d(TAG, "onPageScrollStateChanged: "+i);

            }
        });
        pager.setAdapter(adapter);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
//
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
