package com.example.inmobiliaria.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.adapters.MyInmueblesRecyclerViewAdapter;
import com.example.inmobiliaria.models.Inmueble;
import com.example.inmobiliaria.responses.ResponseContainer;
import com.example.inmobiliaria.retrofit.generator.ServiceGenerator;
import com.example.inmobiliaria.retrofit.generator.TipoAutenticacion;
import com.example.inmobiliaria.retrofit.services.InmuebleInteractionListener;
import com.example.inmobiliaria.retrofit.services.InmuebleService;
import com.example.inmobiliaria.util.UtilToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link InmuebleInteractionListener}
 * interface.
 */
public class InmueblesFavsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private InmuebleInteractionListener mListener;
    private Context ctx;
    private List<Inmueble> inmueblesList;
    private MyInmueblesRecyclerViewAdapter adapter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public InmueblesFavsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static InmueblesFavsFragment newInstance(int columnCount) {
        InmueblesFavsFragment fragment = new InmueblesFavsFragment();
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
        View view = inflater.inflate(R.layout.fragment_inmueblesfavs_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            inmueblesList = new ArrayList<>();
            InmuebleService service = ServiceGenerator.createService(InmuebleService.class, UtilToken.getToken(ctx), TipoAutenticacion.JWT);
            //---------------
            Call<ResponseContainer<Inmueble>> call = service.getFavoritos();

            call.enqueue(new Callback<ResponseContainer<Inmueble>>() {


                @Override
                public void onResponse(Call<ResponseContainer<Inmueble>> call, Response<ResponseContainer<Inmueble>> response) {
                    if (response.isSuccessful()) {
                        // error
                        Log.e("RequestSuccessful", response.message());
                        inmueblesList = response.body().getRows();

                        adapter = new MyInmueblesRecyclerViewAdapter(
                                ctx,
                                R.layout.fragment_inmuebles,
                                inmueblesList,
                                mListener

                        );
                        recyclerView.setAdapter(adapter);
                        //}

                    } else {
                        Log.e("RequestError", response.message());
                    }
                }

                @Override
                public void onFailure(Call<ResponseContainer<Inmueble>> call, Throwable t) {
                    Log.e("NetworkFailure", t.getMessage());

                }
            });
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ctx=context;
        if (context instanceof InmuebleInteractionListener) {
            mListener = (InmuebleInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
