package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.common.util.ArrayUtils;
import com.sbi.dimar.visitaoficialarribooffline.app.to.RepresentantTO;
import com.sbi.dimar.visitaoficialarribooffline.app.to.SignatureTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.RepresentantEntity.REPRESENTANT_BD;
import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.SignatureEntity.SIGNATURE_BD;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_INTERNACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.TYPE_AVISO_ARRIBO_NACIONAL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_CEDULA_FUNCIONARIO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_EMAIL;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_FIRMA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_ID;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_ID_REPRESENTANTE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_FIR_NOMBRE_FUNCIONARIO;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REP_ID;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants.COLUMN_DIM_OFF_REP_NOMBRE;

/**
 * Created by Jenny Galindo on 27/4/2018
 *
 * This class implements the interface {@link SignatureDAO}, is responsible for obtaining data from the database.
 */
public class SignatureDAOImpl extends AbstractFacade implements SignatureDAO {
    private static final String TAG = SignatureDAOImpl.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;

    @Override
    protected SQLiteDatabase getSQLiteDatabaseManeger() {
        return sqLiteDatabase;
    }

    public SignatureDAOImpl(SQLiteDatabase sqLiteDatabase, List<SignatureTO> signatureTOS, String tipoAviso) {
        super(SIGNATURE_BD.table(), SIGNATURE_BD.columnsWhitValues(signatureTOS, tipoAviso), SIGNATURE_BD.columnsNames());
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public List<SignatureTO> findAllRecords() {
        Cursor cursor = super.findAll();
        List<SignatureTO> signatureTOS = null;
        if (cursor != null && cursor.moveToFirst()) {
            signatureTOS = new ArrayList<>();
            do {
                SignatureTO signatureTO = cursorToRecordTO(cursor, null);
                if (signatureTO != null) {
                    signatureTOS.add(signatureTO);
                }
                Log.i(TAG, "findAllRecords");
            } while (cursor.moveToNext());
        }
        return signatureTOS;
    }

    /*@Override
    public List<SignatureTO> findSignaturesByAviso(long numeroAviso, String tipoAviso) {
        Log.i(TAG, "findSignaturesByAviso");
        StringBuilder whereClause = new StringBuilder();
        if (!TextUtils.isEmpty(tipoAviso) && tipoAviso.equals(TYPE_AVISO_ARRIBO_NACIONAL)) {
            whereClause.append(COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO).append(" = ? \n");
        } else {
            if (!TextUtils.isEmpty(tipoAviso) && tipoAviso.equals(TYPE_AVISO_ARRIBO_INTERNACIONAL)) {
                whereClause.append(COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO).append(" = ? \n");
            }
        }

        String[] whereArgs = {String.valueOf(numeroAviso)};

        Cursor cursor = super.findAllByArguments(whereClause.toString(), whereArgs);
        List<SignatureTO> signatureTOS = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                SignatureTO signatureTO = cursorToRecordTO(cursor, tipoAviso);
                if (signatureTO != null) {
                    signatureTOS.add(signatureTO);
                    return signatureTOS;
                }
            } while (cursor.moveToNext());
        }
        return signatureTOS;
    }*/

    @Override
    public List<SignatureTO> findSignaturesByAviso(long numeroAviso, String tipoAviso) {
        Log.i(TAG, "findSignaturesByAviso");

        String tables = SIGNATURE_BD.table().concat(" , ").concat(REPRESENTANT_BD.table());

        String[] columns = ArrayUtils.concat(SIGNATURE_BD.columnsNames(), REPRESENTANT_BD.columnsNames());

        StringBuilder whereClause = new StringBuilder();
        if (!TextUtils.isEmpty(tipoAviso) && tipoAviso.equals(TYPE_AVISO_ARRIBO_NACIONAL)) {
            whereClause.append(COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO).append(" = ? \n");
        } else {
            if (!TextUtils.isEmpty(tipoAviso) && tipoAviso.equals(TYPE_AVISO_ARRIBO_INTERNACIONAL)) {
                whereClause.append(COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO).append(" = ? \n");
            }
        }
        whereClause.append(" AND ").append(COLUMN_DIM_OFF_FIR_ID_REPRESENTANTE).append(" = ").append(COLUMN_DIM_OFF_REP_ID);

        String[] whereArgs = {String.valueOf(numeroAviso)};

        Cursor cursor = super.findAllByArgumentsWhitJoin(tables, columns, whereClause.toString(), whereArgs);
        List<SignatureTO> signatureTOS = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                SignatureTO signatureTO = cursorToRecordTO(cursor, tipoAviso);
                if (signatureTO != null) {
                    signatureTOS.add(signatureTO);
                }
            } while (cursor.moveToNext());
        }
        return signatureTOS;
    }

    @Override
    public SignatureTO cursorToRecordTO(Cursor cursor, String tipoAviso) {
        Log.i(TAG, "cursorToRecordTO");
        try {
            if (cursor != null && cursor.getCount() > 0) {
                SignatureTO signatureTO = new SignatureTO();
                signatureTO.setIdFirma(cursor.getInt(cursor.getColumnIndex(COLUMN_DIM_OFF_FIR_ID)));
                if (!TextUtils.isEmpty(tipoAviso)) {
                    if (tipoAviso.equals(TYPE_AVISO_ARRIBO_NACIONAL)) {
                        signatureTO.setNumeroAvisoArribo(cursor.getLong(cursor.getColumnIndex(COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_CAB_NUMERO_AVISO_ARRIBO)));
                    } else {
                        if (tipoAviso.equals(TYPE_AVISO_ARRIBO_INTERNACIONAL)) {
                            signatureTO.setNumeroAvisoArribo(cursor.getLong(cursor.getColumnIndex(COLUMN_DIM_OFF_FIR_DIM_OFF_AVA_INT_NUMERO_AVISO_ARRIBO)));
                        }
                    }
                }
                signatureTO.setNombreFuncionario(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_FIR_NOMBRE_FUNCIONARIO)));
                signatureTO.setEmailFuncionario(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_FIR_EMAIL)));
                signatureTO.setNumeroIdentificacionFuncionario(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_FIR_CEDULA_FUNCIONARIO)));
                signatureTO.setFirmaFuncionario(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_FIR_FIRMA)));

                //Columnas Representante
                RepresentantTO representantTO = new RepresentantTO(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_FIR_ID_REPRESENTANTE)));
                representantTO.setNombre(cursor.getString(cursor.getColumnIndex(COLUMN_DIM_OFF_REP_NOMBRE)));
                signatureTO.setRepresentantTO(representantTO);
                return signatureTO;
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
