package com.example.applicationanalylstic.Model;




public class notes {

    String id;
    String header;
    String text;
    String image ;

    private  notes(){}
    public notes(String id,  String  header, String text  ,String image  ) {
        this.id = id;
        this.header = header  ;
        this.text = text;
        this.image = image;


    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public String getHeader() {
        return header;
    }

    public String getText() {
        return text;
    }
    public String getImage() {
        return image;
    }


}
