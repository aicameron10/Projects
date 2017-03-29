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
import com.pub.projects.adapters.ProjectAdapter;
import com.pub.projects.adapters.TaskAdapter;
import com.pub.projects.model.NavItem;
import com.pub.projects.model.Projects;
import com.pub.projects.model.Tasks;

import java.util.ArrayList;
import java.util.List;


public class TaskFragment extends Fragment {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<Tasks> searchList;
    View rootView;


    ProgressBar pg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_tasks, container, false);


        ((MainActivity) getActivity()).loadDrawsHide();
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        final Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/webfont.ttf");

        TextView title = (TextView) getActivity().findViewById(R.id.toolbar_title);
        title.setTypeface(face, Typeface.BOLD);
        title.setTextSize(18);
        title.setText("Tasks");

        NavItem nav = new NavItem();
        nav.setPage("Home");


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

                header.setText(getResources().getString(R.string.task));
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

                        searchList.add(new Tasks(null, 0, inputName.getText().toString()));

                        adapter.notifyDataSetChanged();

                        alertDialog.dismiss();

                    }
                });


            }
        });

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && fab.isShown()) {
                    fab.hide();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fab.show();
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        pg = (ProgressBar) rootView.findViewById(R.id.progressBar);

        searchList = new ArrayList<>();
        adapter = new TaskAdapter(getActivity(), searchList);

        recyclerView.setAdapter(adapter);


        return rootView;
    }


}
