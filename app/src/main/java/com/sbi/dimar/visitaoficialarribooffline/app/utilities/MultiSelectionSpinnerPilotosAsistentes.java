package com.sbi.dimar.visitaoficialarribooffline.app.utilities;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.sbi.dimar.visitaoficialarribooffline.app.to.PilotoAsistenteTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.PracticalPilotTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.RemolcadoresTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.TrainingPilotTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.TugboatTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiSelectionSpinnerPilotosAsistentes extends android.support.v7.widget.AppCompatSpinner implements
        DialogInterface.OnMultiChoiceClickListener {

    List<PracticalPilotTO> items = null;
    boolean[] selection = null;
    ArrayAdapter adapter;
    DialogInterface.OnClickListener listener;

    public MultiSelectionSpinnerPilotosAsistentes(Context context) {
        super(context);

        adapter = new ArrayAdapter(context,
                android.R.layout.simple_spinner_item);
        super.setAdapter(adapter);
    }

    public MultiSelectionSpinnerPilotosAsistentes(Context context, AttributeSet attrs) {
        super(context, attrs);

        adapter = new ArrayAdapter(context,
                android.R.layout.simple_spinner_item);
        super.setAdapter(adapter);
    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (selection != null && which < selection.length) {
            selection[which] = isChecked;

            adapter.clear();
            adapter.add(buildSelectedItemString());
        } else {
            throw new IllegalArgumentException(
                    "Argument 'which' is out of bounds.");
        }
    }

    @Override
    public boolean performClick() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        String[] itemNames = new String[items.size()];

        for (int i = 0; i < items.size(); i++) {
            itemNames[i] = items.get(i).getNombrePiloto();
        }

        builder.setMultiChoiceItems(itemNames, selection, this);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {
                listener.onClick(arg0, arg1);
            }
        });

        builder.show();

        return true;
    }

    public void clickListener(DialogInterface.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void setAdapter(SpinnerAdapter adapter) {
        throw new RuntimeException(
                "setAdapter is not supported by MultiSelectSpinner.");
    }

    public void setItems(ArrayList<PracticalPilotTO> items) {
        this.items = items;
        selection = new boolean[this.items.size()];
        adapter.clear();
        adapter.add("");
        Arrays.fill(selection, false);
    }

    public void setSelection(ArrayList<PilotoAsistenteTO> selection) {
        for (int i = 0; i < this.selection.length; i++) {
            this.selection[i] = false;
        }

        for (PilotoAsistenteTO sel : selection) {
            for (int j = 0; j < items.size(); ++j) {
                if (items.get(j).getIdPiloto().equals(sel.getIdPiloto())) {
                    this.selection[j] = true;
                }
            }
        }

        adapter.clear();
        adapter.add(buildSelectedItemString());
    }
    public void setSelectionPilotosAsistentes(List<PilotoAsistenteTO> selection) {
        for (int i = 0; i < this.selection.length; i++) {
            this.selection[i] = false;
        }

        for (PilotoAsistenteTO sel : selection) {
            for (int j = 0; j < items.size(); ++j) {
                if (items.get(j).getIdPiloto().equals(sel.getIdPiloto())) {
                    this.selection[j] = true;
                }
            }
        }

        adapter.clear();
        adapter.add(buildSelectedItemString());
    }

    private String buildSelectedItemString() {
        StringBuilder sb = new StringBuilder();
        boolean foundOne = false;

        for (int i = 0; i < items.size(); ++i) {
            if (selection[i]) {
                if (foundOne) {
                    sb.append("; ");
                }

                foundOne = true;

                sb.append(items.get(i).getNombrePiloto());
            }
        }

        return sb.toString();
    }

    public String selectedItemString() {
        StringBuilder sb = new StringBuilder();
        boolean foundOne = false;

        for (int i = 1; i < items.size(); ++i) {
            if (selection[i]) {
                if (foundOne) {
                    sb.append("; ");
                }

                foundOne = true;

                sb.append(items.get(i).getNombrePiloto());
            }
        }

        return sb.toString();
    }

    public ArrayList<PracticalPilotTO> getSelectedItems() {
        ArrayList<PracticalPilotTO> selectedItems = new ArrayList<>();

        for (int i = 0; i < items.size(); ++i) {
            if (selection[i]) {
                selectedItems.add(items.get(i));
            }
        }

        return selectedItems;
    }

    public List<PilotoAsistenteTO> getSelectedPilotosAsistentes(long numeroAvisoArrivo) {
        ArrayList<PilotoAsistenteTO> selectedItems = new ArrayList<>();

        for (int i = 0; i < items.size(); ++i) {
            if (selection[i]) {
                PilotoAsistenteTO pilotoAsistenteTO = new PilotoAsistenteTO();
                pilotoAsistenteTO.setIdPiloto(items.get(i).getIdPiloto());
                pilotoAsistenteTO.setNombrePiloto(items.get(i).getNombrePiloto());
                pilotoAsistenteTO.setNumeroAvisoArribo(numeroAvisoArrivo);
                pilotoAsistenteTO.setCodigoLicencia(items.get(i).getCodigoLicencia());
                selectedItems.add(pilotoAsistenteTO);
            }
        }
        Log.d("Pilotos Asistentes", selectedItems.toString());
        return selectedItems;
    }
}
