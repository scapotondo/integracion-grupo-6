package com.integracion.grupo6.dto;

import com.integracion.grupo6.domain.ClaimOrigin;

public class ClaimDTO {

    private Long id;
    private ClaimOrigin origin;
    private ClaimTypeDTO type;
    private ClaimStatusDTO status;
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
    public ClaimStatusDTO getStatus() {
        return status;
    }

    /**
     * @param claimStatus the claimStatus to set
     */
    public void setStatus(ClaimStatusDTO claimStatus) {
        this.status = claimStatus;
    }

    /**
     * @return the claimType
     */
    public ClaimTypeDTO getType() {
        return type;
    }

    /**
     * @param claimType the claimType to set
     */
    public void setType(ClaimTypeDTO claimType) {
        this.type = claimType;
    }

    /**
     * @return the claimOrigin
     */
    public ClaimOrigin getOrigin() {
        return origin;
    }

    /**
     * @param claimOrigin the claimOrigin to set
     */
    public void setOrigin(ClaimOrigin claimOrigin) {
        this.origin = claimOrigin;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
