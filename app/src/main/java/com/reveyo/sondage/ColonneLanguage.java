package com.reveyo.sondage;

public class ColonneLanguage {

    private String language;
    private String detail;
    private String Toast;
    private int image;
    private boolean selectionne = false;

    public ColonneLanguage(String language, String detail, String Toast, int image) {
        this.language = language;
        this.detail = detail;
        this.Toast = Toast;
        this.image = image;
    }

    public String getLanguage()
    {
        return this.language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getDetail()
    {
        return this.detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    public String getToast()
    {
        return this.Toast;
    }

    public void setToast(String Toast)
    {
        this.Toast = Toast;
    }

    public int getImage()
    {
        return this.image;
    }

    public void setImage(int image)
    {
        this.image = image;
    }

    public boolean getSelection() { return  this.selectionne; }

    public void setSelection(boolean selectionne) { this.selectionne = selectionne; }

}