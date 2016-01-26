package com.mortenjust.chatappoidssbs;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BottomSheetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomSheetFragment extends Fragment {
    String TAG = "mj.bottom";

    public BottomSheetFragment() {
        // Required empty public constructor
    }

    public static BottomSheetFragment newInstance() {
        BottomSheetFragment fragment = new BottomSheetFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        //setStartPosition(v);
//        startDragListener(v);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    void startDragListener(View v){
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch: called");
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                        snapFromY(v, event.getRawY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        moveWithFingerToY(v, event.getRawY());
                        break;
                    default:
                        Log.d(TAG, "onTouch: DEFAULT");
                        break;
                }
                return true;
            }
        });
    }

    void snapFromY(View v, float y){
        int screen = Util.getScreenSize(getActivity()).y;
        Log.d(TAG, "snapFromY: "+y);
    }

    void moveWithFingerToY(View v, float y){
        Log.d(TAG, "moveWithFingerToY: "+y);
        v.setY(y);
    }

    void setStartPosition(View fragmentView){
        int height = Util.getScreenSize(getActivity()).y;
        fragmentView.setY(height - 20);

    }

}
