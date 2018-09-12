package com.integracion.grupo6.dto;

public class ClaimStatusDTO {
    
    public ClaimStatusDTO () { }
    
    public ClaimStatusDTO (Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private Long id;
    private String name;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
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
