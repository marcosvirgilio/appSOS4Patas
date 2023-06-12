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

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonObjectRequest;
        import com.android.volley.toolbox.Volley;
        import com.google.android.material.snackbar.Snackbar;

        import org.json.JSONObject;

        import java.text.SimpleDateFormat;
        import java.util.Date;

        import br.dev.marcosvirgilio.mobile.sos4patas.R;
        import br.dev.marcosvirgilio.mobile.sos4patas.model.PedidoSOS;
        import br.dev.marcosvirgilio.mobile.sos4patas.model.Pessoa;

        /**
         * A simple {@link Fragment} subclass.
         * create an instance of this fragment.
         */
        public class CadPedidoSOSFragment extends Fragment implements View.OnClickListener , Response.ErrorListener,
                Response.Listener{
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

            //volley
            private RequestQueue requestQueue;
            private JsonObjectRequest jsonObjectReq;

            public CadPedidoSOSFragment() {
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
                this.etNome = (EditText) root.findViewById(R.id.etNm);
                this.etLocal = (EditText) root.findViewById(R.id.etLocal);
                this.etTelefone = (EditText) root.findViewById(R.id.etFone);
                this.cvData = (CalendarView) root.findViewById(R.id.cvDt);
                this.etHora = (EditText) root.findViewById(R.id.etHr);
                this.spTipoAnimal = (Spinner) root.findViewById(R.id.spTipo);
                this.spPorteAnimal = (Spinner) root.findViewById(R.id.spPorte);
                this.spSaudeAnimal = (Spinner) root.findViewById(R.id.spSaude);
                this.btSalvar = (Button) root.findViewById(R.id.btSvr);
                //definindo o listener do botão
                this.btSalvar.setOnClickListener(this);
                //
                //instanciando a fila de requests - caso o objeto seja o root
                this.requestQueue = Volley.newRequestQueue(root.getContext());
                //inicializando a fila de requests do SO
                this.requestQueue.start();
                //
                return root;
            }

            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    //verificando se é o botão salvar
                    case R.id.btSvr:
                        //instanciando classe de negócio
                        PedidoSOS pedido = new PedidoSOS();
                        // Dados do solicitantes
                        Pessoa solicitante = new Pessoa();
                        solicitante.setNomeSolicitante(etNome.getText().toString());
                        solicitante.setFoneSolicitante(etTelefone.getText().toString());
                        pedido.setSolicitante(solicitante);
                        //Pegando a Data do CalendarView
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String dataSelecionada = sdf.format(new Date(cvData.getDate()));
                        pedido.setData(dataSelecionada);
                        //pegando texto dos editTexts
                        pedido.setHora(etHora.getText().toString());
                        pedido.setLocal(etLocal.getText().toString());
                        //indice do item selecionado do Spinner tipo animal
                        pedido.setTipoAnimal((int)this.spTipoAnimal.getSelectedItemPosition());
                        //indice do item selecionado do Spinner porte animal
                        pedido.setPorteAnimal((int)this.spPorteAnimal.getSelectedItemPosition());
                        //indice do item selecionado do Spinner saude animal
                        pedido.setSaudeAnimal((int)this.spSaudeAnimal.getSelectedItemPosition());
                        //
                        String json = pedido.toJsonObject().toString();
                        //request para servidor REST
                        jsonObjectReq = new JsonObjectRequest(Request.Method.POST,
                                "http://10.0.2.2/cadpedidosos.php",
                                pedido.toJsonObject(), this, this);
                        requestQueue.add(jsonObjectReq);
                        break;
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar mensagem = Snackbar.make(root,
                        "Ops! Houve um problema ao realizar o cadastro: " +
                                error.toString(),Snackbar.LENGTH_LONG);
                mensagem.show();
            }

            @Override
            public void onResponse(Object response) {
                try {
                    String resposta = response.toString();
                    //convertendo resposta strin to json
                    JSONObject jor = new JSONObject(resposta);
                    String mensagem = jor.getString("message");
                    boolean sucesso = jor.getBoolean("success");
                    if(sucesso) {
                        //sucesso
                        //limpar campos da tela
                        this.etNome.setText("");
                        this.etTelefone.setText("");
                        this.etLocal.setText("");
                        this.etHora.setText("00:00");
                    }
                    //mostrar mensagem na tela
                    Snackbar snack = Snackbar.make(root,mensagem,Snackbar.LENGTH_LONG);
                    snack.show();
                } catch (Exception e) {  e.printStackTrace(); }
            }
        }