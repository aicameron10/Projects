package com.pub.projects.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pub.projects.R;
import com.pub.projects.activities.MainActivity;
import com.pub.projects.adapters.CommentAdapter;
import com.pub.projects.adapters.TaskAdapter;
import com.pub.projects.model.Comments;
import com.pub.projects.model.NavItem;
import com.pub.projects.model.Projects;
import com.pub.projects.model.Tasks;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class DetailFragment extends Fragment {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    private List<Comments> searchList;
    View rootView;

    private EditText inputTitle, inputDesc, inputDate;
    private TextInputLayout inputLayoutTitle, inputLayoutDesc, inputLayoutDate;
    ProgressBar pg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.form_elements_details, container, false);


        ((MainActivity) getActivity()).loadDrawsHide();
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        final Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/webfont.ttf");

        TextView title = (TextView) getActivity().findViewById(R.id.toolbar_title);
        title.setTypeface(face, Typeface.BOLD);
        title.setTextSize(18);
        title.setText("Details");

        NavItem nav = new NavItem();
        nav.setPage("Task");

        inputLayoutTitle = (TextInputLayout) rootView.findViewById(R.id.input_layout_title);
        inputLayoutDesc = (TextInputLayout) rootView.findViewById(R.id.input_layout_desc);
        inputLayoutDate = (TextInputLayout) rootView.findViewById(R.id.input_layout_date);


        inputTitle = (EditText) rootView.findViewById(R.id.input_title);
        inputDesc = (EditText) rootView.findViewById(R.id.input_desc);
        inputDate = (EditText) rootView.findViewById(R.id.input_date);

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        inputDate.setText(formattedDate);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        pg = (ProgressBar) rootView.findViewById(R.id.progressBar);

        searchList = new ArrayList<>();
        adapter = new CommentAdapter(getActivity(), searchList);

        recyclerView.setAdapter(adapter);

        final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ViewGroup nullParent = null;
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View formElementsView = inflater.inflate(R.layout.form_elements,
                        nullParent, false);

                TextInputLayout inputLayoutName = (TextInputLayout) formElementsView.findViewById(R.id.input_layout_name);


                final EditText inputName = (EditText) formElementsView.findViewById(R.id.input_name);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity()).setView(formElementsView);
                final ViewGroup nullParent1 = null;
                LayoutInflater inflater1 = getActivity().getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.tool_bar_dialog, nullParent1);

                TextView header = (TextView) view1
                        .findViewById(R.id.toolbar_title);

                header.setText(getResources().getString(R.string.comment));
                header.setTypeface(face, Typeface.BOLD);
                header.setTextSize(20);
                alertDialogBuilder.setCustomTitle(view1);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Continue", null)
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();


                            }
                        });


                // create alert dialog
                final AlertDialog alertDialog = alertDialogBuilder.create();


                alertDialog.show();

                Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View onClick) {

                        searchList.add(new Comments(inputName.getText().toString()));

                        adapter.notifyDataSetChanged();

                        alertDialog.dismiss();

                    }
                });


            }
        });


        return rootView;
    }


}
