package com.integracion.grupo6.dto;

import com.integracion.grupo6.domain.ClaimOrigin;

public class ClaimDTO {

    private Long id;
    private ClaimOrigin claimOrigin;
    private ClaimTypeDTO claimType;
    private ClaimStatusDTO claimStatus;
    private String description;
    private Long orderId;
    private String clientIdentification;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the clientIdentification
     */
    public String getClientIdentification() {
        return clientIdentification;
    }

    /**
     * @param clientIdentification the clientIdentification to set
     */
    public void setClientIdentification(String clientIdentification) {
        this.clientIdentification = clientIdentification;
    }

    /**
     * @return the orderId
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the claimStatus
     */
    public ClaimStatusDTO getClaimStatus() {
        return claimStatus;
    }

    /**
     * @param claimStatus the claimStatus to set
     */
    public void setClaimStatus(ClaimStatusDTO claimStatus) {
        this.claimStatus = claimStatus;
    }

    /**
     * @return the claimType
     */
    public ClaimTypeDTO getClaimType() {
        return claimType;
    }

    /**
     * @param claimType the claimType to set
     */
    public void setClaimType(ClaimTypeDTO claimType) {
        this.claimType = claimType;
    }

    /**
     * @return the claimOrigin
     */
    public ClaimOrigin getClaimOrigin() {
        return claimOrigin;
    }

    /**
     * @param claimOrigin the claimOrigin to set
     */
    public void setClaimOrigin(ClaimOrigin claimOrigin) {
        this.claimOrigin = claimOrigin;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
