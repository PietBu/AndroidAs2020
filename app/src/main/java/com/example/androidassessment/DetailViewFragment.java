package com.example.androidassessment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.      there was a # before newinstance, caused error
 * Use the {@link DetailViewFragment newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NAME = "name";
    private static final String ARG_PHONE = "phone";
    private static final String ARG_DESC = "description";

    // TODO: Rename and change types of parameters
    private Contact _contact;


    private OnFragmentInteractionListener mListener;

    public DetailViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *      were @s before param1 & 2, caused errors
     * param param1 Parameter 1.
     * param param2 Parameter 2.
     * @return A new instance of fragment DetailViewFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static DetailViewFragment newInstance(String param1, String param2) {
//        DetailViewFragment fragment = new DetailViewFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_NAME, param1);
//        args.putString(ARG_PHONE, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            _contact.name = getArguments().getString(ARG_NAME);
//            _contact.phoneNumber = getArguments().getString(ARG_PHONE);
//            _contact.description = getArguments().getString(ARG_DESC);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_detail_view, container, false);

        final TextView contactName = view.findViewById(R.id.detailName);
        final EditText phoneNum = view.findViewById(R.id.detailPhone);
        final TextView contactDesc = view.findViewById(R.id.detailDescription);

        if (_contact != null)
        {
            contactName.setText(_contact.name);
            phoneNum.setText(_contact.phoneNumber);
            phoneNum.setVisibility(View.VISIBLE);
            if (_contact.description != null && !_contact.description.equals(""))
            {
                contactDesc.setText(_contact.description);
            }
        }
        else
        {
            contactName.setText("Select a contact.");
            phoneNum.setVisibility(View.INVISIBLE);
            contactDesc.setVisibility(View.INVISIBLE);
        }
        //TODO: add handling for the selecting of a description (& the loading of descriptions?)

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public Contact getContact()
    {
        return _contact;
    }

    public void setContact(Contact c)
    {
        final View view = this.getView();

        _contact = c;
        if (view != null)
        {

        }
    }
}
