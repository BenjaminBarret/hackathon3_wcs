package fr.wcs.winnewshackathon3;

public class ArticleModel {

    private String urlVideo;
    private String tag;
    private String urlArticle;
    private String titreArticle;
    private String firstname;
    private String lastname;
    private String Employer;

    public ArticleModel(){}

    public ArticleModel(String urlVideo, String tag, String urlArticle, String titreArticle, String firstname, String lastname, String employer) {
        this.urlVideo = urlVideo;
        this.tag = tag;
        this.urlArticle = urlArticle;
        this.titreArticle = titreArticle;
        this.firstname = firstname;
        this.lastname = lastname;
        Employer = employer;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUrlArticle() {
        return urlArticle;
    }

    public void setUrlArticle(String urlArticle) {
        this.urlArticle = urlArticle;
    }

    public String getTitreArticle() {
        return titreArticle;
    }

    public void setTitreArticle(String titreArticle) {
        this.titreArticle = titreArticle;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmployer() {
        return Employer;
    }

    public void setEmployer(String employer) {
        Employer = employer;
    }
}
