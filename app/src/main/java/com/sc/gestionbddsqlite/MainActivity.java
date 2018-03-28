package com.sc.gestionbddsqlite;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MagasinDBSQLite magasinDB;
    private SQLiteDatabase bdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Insert --------------------------------------
        magasinDB = new MagasinDBSQLite(getApplicationContext(), MagasinDBSQLite.NOM_BDD, null, MagasinDBSQLite.VERSION);
        bdd = magasinDB.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put(MagasinDBSQLite.COL_NOM, "Lidl");
        content.put(MagasinDBSQLite.COL_ADR, "19 bis rue Général de Gaulle, 37000 Tours");
        bdd.insert(MagasinDBSQLite.TABLE_MAGASIN, null, content);
        bdd.close();
        magasinDB.close();

        createSimpleNotification("Création magasin", "magasin a été enregistré avec succès");

        // Update --------------------------------------

        bdd = magasinDB.getWritableDatabase();

        content = new ContentValues();
        content.put(MagasinDBSQLite.COL_NOM, "Carrefour");
        content.put(MagasinDBSQLite.COL_ADR, "adresse mise à jour");
        bdd.update(MagasinDBSQLite.TABLE_MAGASIN, content, MagasinDBSQLite.COL_ID + " = " + 1, null);
        bdd.close();


        // DELETE --------------------------------------

        // bdd.delete(MagasinDBSQLite.TABLE_MAGASIN, MagasinDBSQLite.COL_NOM + " = " + "Carrefour", null);


        // SELECT --------------------------------------

        bdd = magasinDB.getReadableDatabase();


        List<Magasin> listeMagasin = new ArrayList<>();

        // effectuer la requête

        Cursor c = bdd.query(MagasinDBSQLite.TABLE_MAGASIN
                , new String[]{
                        MagasinDBSQLite.COL_ID,
                        MagasinDBSQLite.COL_NOM,
                        MagasinDBSQLite.COL_ADR
                }
                , MagasinDBSQLite.COL_NOM + " LIKE \"" + "Carrefour" + "\"", null, null,
                null, MagasinDBSQLite.COL_NOM);
        // parcourir le résulat

        if (c != null) {
            try {
                c.moveToFirst();
                while (!c.isAfterLast()) {
                    Magasin magasin = new Magasin();
                    magasin.setId(c.getInt(0));
                    magasin.setNom(c.getString(1));
                    magasin.setAdresse(c.getString(2));

                    listeMagasin.add(magasin);


                    c.moveToNext();
                }
            } finally {
                c.close();
            }
        }
        bdd.close();


        ListView list = (ListView) findViewById(R.id.magasin_list);


        ArrayAdapter<Magasin> adapter = new ArrayAdapter<Magasin>(this, android.R.layout.simple_list_item_1, listeMagasin);
        list.setAdapter(adapter);
    }

    private void createSimpleNotification(String titre, String contenu) {


        final NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        final Intent notificationIntent = new
                Intent(this, MainActivity.class);

        final PendingIntent notificationPendingIntent =
                PendingIntent.getActivity(this,
                        1, notificationIntent,
                        PendingIntent.FLAG_CANCEL_CURRENT);

        Notification.Builder notificationBuilder =
                new Notification.Builder(this)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(titre)
                        .setContentText(contenu)
                        .setContentIntent(notificationPendingIntent);

        notificationManager.notify(1,
                notificationBuilder.getNotification());

    }
}
