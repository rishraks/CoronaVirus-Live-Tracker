/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19;

/**
 *
 * @author HP
 */
public class Case {
    
    private Integer sno;
    private String state;
    private Integer activeCases;
    private Integer cured;
    private Integer deaths;
    private Integer confirmedCases;

    public Case(Integer sno, String state, Integer activeCases, Integer cured, Integer deaths, Integer confirmedCases) {
        this.sno = sno;
        this.state = state;
        this.activeCases = activeCases;
        this.cured = cured;
        this.deaths = deaths;
        this.confirmedCases = confirmedCases;
    }

    public Integer getSno() {
        return sno;
    }

    public String getState() {
        return state;
    }

    public Integer getActiveCases() {
        return activeCases;
    }

    public Integer getCured() {
        return cured;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public Integer getConfirmedCases() {
        return confirmedCases;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setActiveCases(Integer activeCases) {
        this.activeCases = activeCases;
    }

    public void setCured(Integer cured) {
        this.cured = cured;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public void setConfirmedCases(Integer confirmedCases) {
        this.confirmedCases = confirmedCases;
    }

    @Override
    public String toString() {
        return this.getState();
    }
    
    
    
 }
