package com.example.physicwalademo.Pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

public class ClientModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("subjects")
    @Expose
    private List<String> subjects = null;
    @SerializedName("qualification")
    @Expose
    private List<String> qualification = null;
    @SerializedName("profileImage")
    @Expose
    private String profileImage;


    public ClientModel() {
    }


    public ClientModel(Integer id, String name, List<String> subjects, List<String> qualification, String profileImage) {
        super();
        this.id = id;
        this.name = name;
        this.subjects = subjects;
        this.qualification = qualification;
        this.profileImage = profileImage;
    }

    public ClientModel(String name, String profileImage, int id) {
        this.id = id;
        this.name = name;
        this.profileImage = profileImage;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientModel withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientModel withName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public ClientModel withSubjects(List<String> subjects) {
        this.subjects = subjects;
        return this;
    }

    public List<String> getQualification() {
        return qualification;
    }

    public void setQualification(List<String> qualification) {
        this.qualification = qualification;
    }

    public ClientModel withQualification(List<String> qualification) {
        this.qualification = qualification;
        return this;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public ClientModel withProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

}