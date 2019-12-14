package com.sbi.dimar.visitaoficialarribooffline.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.activities.ArriboCabotajeActivity;
import com.sbi.dimar.visitaoficialarribooffline.app.activities.ArriboInternacionalActivity;
import com.sbi.dimar.visitaoficialarribooffline.app.activities.AvisosArriboActivity;
import com.sbi.dimar.visitaoficialarribooffline.app.database.entities.EstadoEntity;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AvisosArriboTO;

import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.NUMBER_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_INTERNACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_NACIONAL;

/**
 * Created by Jenny Galindo on 2/5/2018
 * <p>
 * The class AnnounceArriboAdapter represents an object {@link AvisosArriboTO},
 * which presents important information about each of the cabotage and international notices and is shown in the class
 * list {@link AvisosArriboActivity}
 */
public class AvisoArriboAdapter extends RecyclerView.Adapter<AvisoArriboAdapter.AvisoArriboHolder> {
    private final Context context;
    private final List<AvisosArriboTO> avisosArribo;

    public AvisoArriboAdapter(Context context, List<AvisosArriboTO> avisosArribo) {
        this.avisosArribo = avisosArribo;
        this.context = context;

    }

    @Override
    public AvisoArriboHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_avisos_arribo, parent, false);

        return new AvisoArriboHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AvisoArriboHolder avisoArriboHolder, int position) {
        final AvisosArriboTO avisosArriboTO = avisosArribo.get(position);
        avisoArriboHolder.txtAvisoArriboNumber.setText(String.valueOf(avisosArriboTO.getNumeroAvisoArribo()));
        avisoArriboHolder.txtOmi.setText(avisosArriboTO.getOmiMatricula());
        avisoArriboHolder.txtTipoAviso.setText(avisosArriboTO.getTipoAviso());
        avisoArriboHolder.txtNombreNave.setText(avisosArriboTO.getNombreNave());
        if (avisosArriboTO.getEstado() == EstadoEntity.ESTADO_VISITADO.getValue()) {
            avisoArriboHolder.imgEstadoArribo.setImageResource(android.R.drawable.ic_menu_save);
            avisoArriboHolder.imgEstadoArribo.setImageTintList(ContextCompat.getColorStateList(context, android.R.color.holo_green_dark));
            avisoArriboHolder.txtGuardado.setText(R.string.saved);
        } else {
            avisoArriboHolder.imgEstadoArribo.setImageResource(android.R.drawable.ic_menu_save);
            avisoArriboHolder.imgEstadoArribo.setImageTintList(ContextCompat.getColorStateList(context, android.R.color.holo_red_dark));
            avisoArriboHolder.txtGuardado.setText(R.string.dont_save);
        }

        avisoArriboHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AvisosArriboTO avisosArriboTO = getItem(avisoArriboHolder.getAdapterPosition());
                Intent intentArribo = null;

                if (avisosArriboTO.getTipoAviso().equals(TYPE_AVISO_ARRIBO_NACIONAL)) {
                    intentArribo = new Intent(context, ArriboCabotajeActivity.class);
                    intentArribo.putExtra(NUMBER_AVISO_ARRIBO, avisosArriboTO.getNumeroAvisoArribo());
                    intentArribo.putExtra(TYPE_AVISO_ARRIBO, avisosArriboTO.getTipoAviso());
                } else {
                    if (avisosArriboTO.getTipoAviso().equals(TYPE_AVISO_ARRIBO_INTERNACIONAL)) {
                        intentArribo = new Intent(context, ArriboInternacionalActivity.class);
                        intentArribo.putExtra(NUMBER_AVISO_ARRIBO, avisosArriboTO.getNumeroAvisoArribo());
                        intentArribo.putExtra(TYPE_AVISO_ARRIBO, avisosArriboTO.getTipoAviso());
                    }
                }
                context.startActivity(intentArribo);
            }
        });
    }

    private AvisosArriboTO getItem(int position) {
        return avisosArribo.get(position);
    }


    @Override
    public long getItemId(int position) {
        return avisosArribo.get(position).getNumeroAvisoArribo();
    }

    @Override
    public int getItemCount() {
        return avisosArribo.size();
    }

    class AvisoArriboHolder extends RecyclerView.ViewHolder {
        private final TextView txtAvisoArriboNumber;
        private final TextView txtOmi;
        private final TextView txtTipoAviso;
        private final TextView txtNombreNave;
        private final TextView txtGuardado;
        private final ImageView imgEstadoArribo;

        private AvisoArriboHolder(View view) {
            super(view);
            txtAvisoArriboNumber = view.findViewById(R.id.txtAvisoArriboNumber);
            txtOmi = view.findViewById(R.id.txtOmi);
            txtTipoAviso = view.findViewById(R.id.txtTipoAviso);
            txtNombreNave = view.findViewById(R.id.txtNombreNave);
            txtGuardado = view.findViewById(R.id.txtGuardado);
            imgEstadoArribo = view.findViewById(R.id.imgEstadoArribo);
        }
    }
}
