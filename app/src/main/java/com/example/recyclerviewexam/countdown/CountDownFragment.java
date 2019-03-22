package com.example.recyclerviewexam.countdown;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerviewexam.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnCountDownFragmentListener} interface
 * to handle interaction events.
 */
public class CountDownFragment extends Fragment {

    private OnCountDownFragmentListener mListener;
    private TextView mCountTextView;

    public CountDownFragment() {
        // Required empty public constructor
    }

    public void setCount(int count) {
        mCountTextView.setText(count + "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_count_down, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCountTextView = view.findViewById(R.id.count_text_view);

        view.findViewById(R.id.start_button)
                .setOnClickListener(v -> mListener.onStartButtonClicked());
        view.findViewById(R.id.init_button)
                .setOnClickListener(v -> mListener.onInitButtonClicked());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCountDownFragmentListener) {
            mListener = (OnCountDownFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnCountDownFragmentListener {
        void onStartButtonClicked();
        void onInitButtonClicked();
    }
}
