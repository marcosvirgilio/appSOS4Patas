package br.dev.marcosvirgilio.mobile.sos4patas.ui.pedidosos;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import br.dev.marcosvirgilio.mobile.sos4patas.databinding.FragmentConPedidoSosBinding;
import br.dev.marcosvirgilio.mobile.sos4patas.model.PedidoSOS;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link br.dev.marcosvirgilio.mobile.sos4patas.model.PedidoSOS}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ConPedidoSOSRecyclerViewAdapter extends RecyclerView.Adapter<ConPedidoSOSRecyclerViewAdapter.ViewHolder> {

    private final List<PedidoSOS> mValues;

    public ConPedidoSOSRecyclerViewAdapter(List<PedidoSOS> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentConPedidoSosBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getData());
        holder.mContentView.setText(mValues.get(position).getLocal());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public PedidoSOS mItem;

        public ViewHolder(FragmentConPedidoSosBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}