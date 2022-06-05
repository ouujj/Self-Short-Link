package com.example.SelfShortLink.Model;


import javax.persistence.Entity;
import javax.persistence.Id;
import org.apache.commons.lang3.RandomStringUtils;
import lombok.*;

@Entity
@Data
public class Link {


    @Id
    private String shortLink;
    private String originalLink;

    public Link(){

    }

    public Link(String OriginalLink){

        //String ShortLink =UUID.randomUUID().toString();
        int length = 5;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        System.out.println(generatedString);
        String ShortLink = generatedString;
        this.setShortLink(ShortLink);
        this.setOriginalLink(OriginalLink);
    }

    public void setOriginalLink(String OriginalLink){
        this.originalLink = OriginalLink;
    }

    public String getOriginalLink(){
        return this.originalLink;
    }

    public void setShortLink(String ShortLink){
        this.shortLink = ShortLink;
    }

    public String getShortLink(){
        return this.shortLink;
    }




}
