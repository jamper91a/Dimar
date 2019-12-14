package com.sbi.dimar.visitaoficialarribooffline.app.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.database.entities.EstadoEntity;
import com.sbi.dimar.visitaoficialarribooffline.app.to.SignatureTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 10/5/2018
 * <p>
 * The SignatureAdapter class represents an object {@link SignatureTO}, which presents the important information
 * about each of the previously completed civil servant signatures and is displayed in {@linkAdministrativoAbotajeFragment}
 */
public class SignatureAdapter extends RecyclerView.Adapter<SignatureAdapter.SignatureHolder> {
    private final static String TAG = SignatureAdapter.class.getSimpleName();
    private final List<SignatureTO> signatures;
    private final int estadoAviso;

    public SignatureAdapter(List<SignatureTO> signatures, int estadoAviso) {
        this.signatures = signatures;
        this.estadoAviso = estadoAviso;
    }

    @NonNull
    @Override
    public SignatureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_signature, parent, false);
        return new SignatureAdapter.SignatureHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SignatureHolder holder, final int position) {
        final SignatureTO signatureTO = signatures.get(position);
        if (signatureTO != null) {
            holder.txtRepresentantSign.setText(signatureTO.getRepresentantTO().getNombre());
            holder.txtNameRepresentantSign.setText(signatureTO.getNombreFuncionario());
            if (estadoAviso == EstadoEntity.ESTADO_VISITADO.getValue()) {
                holder.btnDeleteSign.setEnabled(false);
            }
            holder.btnDeleteSign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!signatures.isEmpty()) {
                        if (position <= signatures.size() - 1) {
                            signatures.remove(position);
                        }
                        notifyItemRemoved(position);
                    }
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(signatures.get(position).getRepresentantTO().getId());
    }

    @Override
    public int getItemCount() {
        return signatures.size();
    }

    class SignatureHolder extends RecyclerView.ViewHolder {
        private final TextView txtNameRepresentantSign, txtRepresentantSign;
        private final ImageButton btnDeleteSign;

        private SignatureHolder(View view) {
            super(view);
            txtNameRepresentantSign = view.findViewById(R.id.txtNameRepresentantSign);
            txtRepresentantSign = view.findViewById(R.id.txtRepresentantSign);
            btnDeleteSign = view.findViewById(R.id.btnDeleteSign);
        }
    }

}
