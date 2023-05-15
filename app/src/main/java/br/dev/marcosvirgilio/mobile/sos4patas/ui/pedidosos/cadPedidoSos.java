package br.dev.marcosvirgilio.mobile.sos4patas.ui.pedidosos;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import br.dev.marcosvirgilio.mobile.sos4patas.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class cadPedidoSos extends Fragment {
    //atributos

    private EditText etNome;
    private EditText etTelefone;
    private EditText etLocal;
    private CalendarView cvData;
    private EditText etHora;
    private Spinner spTipoAnimal;
    private Spinner spPorteAnimal;
    private Spinner spSaudeAnimal;
    private Button btSalvar;
    private View root;

    public cadPedidoSos() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar()
                .setDisplayShowCustomEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.root =  inflater.inflate(R.layout.fragment_cad_pedido_sos, container, false);
        //
        this.etNome = (EditText) root.findViewById(R.id.etNome);
        this.etTelefone = (EditText) root.findViewById(R.id.etTelefone);
        this.cvData = (CalendarView) root.findViewById(R.id.cvData);
        this.etHora = (EditText) root.findViewById(R.id.etHora);
        this.spTipoAnimal = (Spinner) root.findViewById(R.id.spTipoAnimal);
        this.spPorteAnimal = (Spinner) root.findViewById(R.id.spPorteAnimal);
        this.spSaudeAnimal = (Spinner) root.findViewById(R.id.spSaudeAnimal);
        this.btSalvar = (Button) root.findViewById(R.id.btSalvar);
        //
        return root;
    }
}