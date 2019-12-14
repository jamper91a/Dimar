package com.sbi.dimar.visitaoficialarribooffline.app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.to.PilotTransportBoatTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.PortInstallationTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.PracticalPilotTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.ReasonArrivalTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.RepresentantTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.TrainingPilotTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.TugboatTO;

import java.util.List;

/**
 * Created by Jenny Galindo on 8/5/2018
 * <p>
 * The class ValorDominioAdapter represents a generic object for the presentation of information of type id and name,
 * in this class the objects {@link PracticalPilotTO}, {@link TrainingPilotTO},
 * {@link PilotTransportBoatTO}, {@link TugboatTO}, { @link PortInstallationTO},
 * {@link RepresentantTO}, {@link ReasonArrivalTO}
 */
public class ValorDominioAdapter extends ArrayAdapter {
    private final List objects;

    public ValorDominioAdapter(@NonNull Context context, int resource, List objects) {
        super(context, resource, objects);
        this.objects = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Object object = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_items, parent, false);
        }
        // Lookup view for data population
        TextView tvName = convertView.findViewById(R.id.txtSpinner);
        // Populate the data into the template view using the data object
        if (object instanceof PracticalPilotTO) {
            PracticalPilotTO objectTO = (PracticalPilotTO) object;
            tvName.setText(objectTO.getNombrePiloto());
        }
        if (object instanceof TrainingPilotTO) {
            TrainingPilotTO objectTO = (TrainingPilotTO) object;
            tvName.setText(objectTO.getNombrePiloto());
        }
        if (object instanceof PilotTransportBoatTO) {
            PilotTransportBoatTO objectTO = (PilotTransportBoatTO) object;
            tvName.setText(objectTO.getNombreNave());
        }
        if (object instanceof TugboatTO) {
            TugboatTO objectTO = (TugboatTO) object;
            tvName.setText(objectTO.getNombreNave());
        }
        if (object instanceof PortInstallationTO) {
            PortInstallationTO objectTO = (PortInstallationTO) object;
            tvName.setText(objectTO.getMuelle());
        }
        if (object instanceof RepresentantTO) {
            RepresentantTO objectTO = (RepresentantTO) object;
            tvName.setText(objectTO.getNombre());
        }
        if (object instanceof ReasonArrivalTO) {
            ReasonArrivalTO objectTO = (ReasonArrivalTO) object;
            tvName.setText(objectTO.getDescripcion());
        }

        // Return the completed view to render on screen
        return convertView;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }
}
