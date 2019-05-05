package com.reveyo.sondage;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;

public class ColonneLanguageAdapteur extends ArrayAdapter<ColonneLanguage> {

    public static boolean presentation = false;
    private LanguageViewHolder viewHolder = null;
    private ColonneLanguage ColonneLanguage = null;

    public ColonneLanguageAdapteur(Context context, List<ColonneLanguage> ColonneLanguages) {
        super(context, 0, ColonneLanguages);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ColonneLanguage = getItem(position);
        final ColonneLanguage local = ColonneLanguage;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.colonne_language,parent, false);
        }

        viewHolder = (LanguageViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new LanguageViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.languageicon);
            viewHolder.language = (TextView) convertView.findViewById(R.id.language);
            viewHolder.detail = (TextView) convertView.findViewById(R.id.detail);
            viewHolder.selectionne = (CheckBox) convertView.findViewById(R.id.selectionne);

            //viewHolder.Toast = (TextView) convertView.findViewById(R.id.Toast);
            convertView.setTag(viewHolder);
        }

        viewHolder.selectionne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                local.setSelection(!local.getSelection());
            }
        });

        //getItem(position) va récupérer l'item [position] de la List<ColonneLanguage> colonne_languages
        //ColonneLanguage ColonneLanguage = getItem(position);
        viewHolder.image.setImageResource(ColonneLanguage.getImage());
        viewHolder.language.setText((position +1) + ". " + ColonneLanguage.getLanguage());
        viewHolder.detail.setText(ColonneLanguage.getDetail());

        //if(ColonneLanguage.getPresentation()) {
        if(this.presentation) {
            //parent.removeView(viewHolder.selectionne);
            //viewHolder.selectionne = null;
            //ColonneLanguage.setSelection(viewHolder.selectionne.isChecked());
            ViewGroup tempLayout = (ViewGroup) viewHolder.selectionne.getParent();
            if(tempLayout!=null) {
                tempLayout.removeView(viewHolder.selectionne);
            }
            if (ColonneLanguage.getSelection()) {
                convertView.setBackgroundResource(R.color.Orange);
                viewHolder.detail.setTextColor(ContextCompat.getColor(this.getContext() , R.color.bleu));

                TextView maitrise = new TextView(this.getContext());
                maitrise.setText("Langaue matrisé");
                maitrise.setTextColor(ContextCompat.getColor(this.getContext() , R.color.vert));
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                maitrise.setLayoutParams(params);
                ViewGroup textLayout = (ViewGroup) viewHolder.detail.getParent();
                if(textLayout!=null) {
                    textLayout.addView(maitrise);
                }

            } else {
                convertView.setBackgroundResource(R.color.bleuClair);
                viewHolder.detail.setTextColor(ContextCompat.getColor(this.getContext(), R.color.OrangeVif));
            }


        } else {
            viewHolder.selectionne.setChecked(ColonneLanguage.getSelection());

            //convertView.setBackgroundColor(R.color.OrangeVif);
            if ((position % 2) == 0) {
                convertView.setBackgroundResource(R.color.Orange);
                viewHolder.detail.setTextColor(ContextCompat.getColor(this.getContext() , R.color.bleu));
            } else {
                convertView.setBackgroundResource(R.color.bleuClair);
                viewHolder.detail.setTextColor(ContextCompat.getColor(this.getContext(), R.color.OrangeVif));
            }
        }
        //viewHolder.Toast.setText(ColonneLanguage.getToast());

        return convertView;
    }


    private class LanguageViewHolder{
        public ImageView image;
        public TextView language;
        public TextView detail;
        public CheckBox selectionne;
        //public TextView Toast;

    }
}