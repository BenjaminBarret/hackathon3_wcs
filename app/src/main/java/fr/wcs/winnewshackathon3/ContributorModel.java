package fr.wcs.winnewshackathon3;

public class ContributorModel {

    private String firstname;
    private String lastname;
    private String employer;

    public ContributorModel(){}


    public ContributorModel(String firstname, String lastname, String employer) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.employer = employer;
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
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }
}
