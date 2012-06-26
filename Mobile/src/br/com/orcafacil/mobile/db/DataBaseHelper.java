package br.com.orcafacil.mobile.db;

import br.com.orcafacil.mobile.util.FileUtil;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class DataBaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "OrcaFacil.db";
	private static final int DATABASE_VERSION = 1;

	private static DataBaseHelper instance;

	private final String scriptPath = "/br/com/orcafacil/mobile/db/scripts"; 
	
	public static synchronized DataBaseHelper getHelper(Context context) {
		if (instance == null)
			instance = new DataBaseHelper(context);

		return instance;
	}

	private DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String scriptCreate = new FileUtil()
				.getFileContent(scriptPath + "/create.sql");
		String[] instrucoes = scriptCreate.split(";");
		for (int i = 0; i < instrucoes.length; i++) {
			if (instrucoes[i] != null && !instrucoes[i].trim().equals("")) {
				db.execSQL(instrucoes[i]);
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String scriptDrop = new FileUtil()
				.getFileContent(scriptPath + "/create.sql");
		db.execSQL(scriptDrop);
		this.onCreate(db);
	}
}