package com.integracion.grupo6.dto;

public class ClaimTypeDTO {
    
    public ClaimTypeDTO () { }

    public ClaimTypeDTO (Long id, String name, Boolean isLogistics) {
        this.id = id;
        this.name = name;
        this.isLogistics = isLogistics;
    }

    private Long id;
    private String name;
    private Boolean isLogistics;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the isLogistics
     */
    public Boolean getIsLogistics() {
        return isLogistics;
    }

    /**
     * @param isLogistics the isLogistics to set
     */
    public void setIsLogistics(Boolean isLogistics) {
        this.isLogistics = isLogistics;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

}
