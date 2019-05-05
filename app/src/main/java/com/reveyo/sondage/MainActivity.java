package com.reveyo.sondage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /** Affichage de la liste des sexes **/
    private ListView mListSexe = null;
    /** Affichage de la liste des langages connus **/
    private ListView mListProg = null;
    /** Bouton pour envoyer le sondage **/
    private Button mSend = null;

    private TextView messageGeneral = null;
    private TextView sChoix = null;
    private TextView pChoix = null;

    /** Contient les deux sexes **/
    private String[] mSexes = {"Masculin", "Feminin", "Neutre"};
    /** Contient différents langages de programmation **/
    private String[][] mLangages = new String[][]{
            {"Assembleur","Iteratif de niveau bas","Developpement machine", Integer.toString(R.drawable.assembleur50)},
            {"Bash", "Iteratif Script serveur", "Language serveur", Integer.toString(R.drawable.mod50)},
            {"C", "Iteratif compilié forte portabilité", "Developpement avancé allocation mémoire", Integer.toString(R.drawable.c50)},
            {"Fortran", "Iteratif compilié language humain", "Developpement avancé allocation mémoire", Integer.toString(R.drawable.ftn50)},
            {"Lisp", "Recursif profond", "Lambda calcul", Integer.toString(R.drawable.mod50)},
            {"Java", "Objet pur objectif embarqué", "Local ou embarqué", Integer.toString(R.drawable.java50)},
            {"JavaScipt", "Objet & Script web usage", "Web client et serveur", Integer.toString(R.drawable.js50)},
            {"COBOL", "Iteratif compilié obsolete", "Developpement avancé allocation mémoire", Integer.toString(R.drawable.mod50)},
            {"Pascal", "Iteratif compilié structuré", "Developpement avancé allocation mémoire", Integer.toString(R.drawable.vs50)},
            {"PHP", "Objet & itératif web serveur", "Language dédier au web", Integer.toString(R.drawable.php50)},
            {"Perl", "Objet & Script dédier au parsage", "Gestion de donnée et parsage essentiel serveur", Integer.toString(R.drawable.perl50)},
            {"Python", "Objet & Script", "Toute usage script compilé, client, serveur et embarqué", Integer.toString(R.drawable.python50)},
    };

    private String selectLanguage = new String("Java");
    private ColonneLanguage selectLanguageColonne = new ColonneLanguage("Java", "Objet pur", "Local ou embarqué", R.drawable.java50);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //On récupère les trois vues définies dans notre layout
        mListSexe = (ListView) findViewById(R.id.listSexe);
        mListProg = (ListView) findViewById(R.id.listProg);
        mSend = (Button) findViewById(R.id.send);

        messageGeneral = (TextView) findViewById(R.id.textSondage);
        sChoix = (TextView) findViewById(R.id.textSexe);
        pChoix = (TextView) findViewById(R.id.textProg);

        ArrayAdapter sexeArray = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_single_choice, mSexes);
        mListSexe.setAdapter(sexeArray);
        mListSexe.setItemChecked(0, true);

        mListSexe.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			    // When clicked, show a toast with the TextView text
                String choix = (((TextView) view).getText()).toString();
                if (choix.equals("Masculin")) {
                    choix = new String("Masculins, vous avez donc un pénis!");
                } else if (choix.equals("Feminin")) {
                    choix = new String("Fémins, quel bonnet pour vos seins?");
                } else if (choix.equals("Neutre")) {
                    choix = new String("Neutre est un choix Suisse?");
                }
			    Toast.makeText(getApplicationContext(), choix, Toast.LENGTH_SHORT).show();
			}
        });

        //afficherLanguage();
        afficherListeColonneLanguages();

        mListProg.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                ColonneLanguage element = (ColonneLanguage) parent.getAdapter().getItem(position);
                System.out.println(element.getToast());
                String ChainePosition = Integer.toString(position);
                Toast.makeText(getApplicationContext(), " Pour l'index " + (++position) + ",\n " + element.getLanguage() +" est " + element.getToast(), Toast.LENGTH_SHORT).show();
            }
        });

        mSend.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {

                 Toast.makeText(MainActivity.this, "Merci! Les donnès ont été envoyé", Toast.LENGTH_LONG).show();

                 messageGeneral.setText("Voici les résultats du sondages:");
                 sChoix.setText("Votre sexe est:");
                 pChoix.setText("Et vous matrisez les languages:");
                 String[] rSexe = { mSexes[mListSexe.getCheckedItemPosition()] };
                 mListSexe.setChoiceMode(ListView.CHOICE_MODE_NONE);
                 mListSexe.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,rSexe));

                 ColonneLanguageAdapteur adpater = (ColonneLanguageAdapteur) mListProg.getAdapter();
                 adpater.presentation = true;
                 /*
                 for (int index = 0; index < mListProg.getCount(); index++) {
                     //long element = adpater.getItemId(index);
                     ColonneLanguage element = (ColonneLanguage) adpater.getItem(index);
                     System.out.println(element.getLanguage() + " " + element.getSelection());
                     element.setPresentation(true);
                 }*/


                 //mListProg.setChoiceMode(ListView.CHOICE_MODE_NONE);
                 //mListProg.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, ProgListeSimple(mLangages, 0)));
                 ViewGroup layout = (ViewGroup) mSend.getParent();
                 if(null!=layout) {
                     layout.removeView(mSend);
                 }

             }
        });
    }

    private String[] ProgListeSimple(String[][] TableauChaine, int rang) {
        String[] Language = new String[TableauChaine.length];
        for (int i = 0; i < TableauChaine.length; i++) {
            Language[i] = TableauChaine[i][rang];
        }
        return Language;
    }

    private void afficherLanguage() {
        ArrayAdapter mListProgAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, ProgListeSimple(mLangages, 0));

        mListProg.setAdapter(mListProgAdapter);

        int position = mListProgAdapter.getPosition(selectLanguage);
        mListProg.setItemChecked(position, true);
    }

    private List<ColonneLanguage> genererColonneLanguages(){
        List<ColonneLanguage> colonneLanguages = new ArrayList<ColonneLanguage>();
        for (int index = 0; index < mLangages.length; index++) {
            String[] element = mLangages[index];
            colonneLanguages.add(new ColonneLanguage(element[0], element[1], element[2], Integer.parseInt(element[3])));
        }
        return colonneLanguages;
    }

    private void afficherListeColonneLanguages(){
        List<ColonneLanguage> ColonneLanguages = genererColonneLanguages();

        ColonneLanguageAdapteur adapter = new ColonneLanguageAdapteur(MainActivity.this, ColonneLanguages);
        mListProg.setAdapter(adapter);

        int position = adapter.getPosition(selectLanguageColonne);
        mListProg.setItemChecked(position, true);
    }

}
