/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.leqienglish.entity;

/**
 *
 * @author guona
 */
public class RelationShip extends Entity{

    private Long sourceId;
    private Long targetId;

    private Integer relationShipType;


    /**
     * @return the sourceId
     */
    public Long getSourceId() {
        return sourceId;
    }

    /**
     * @param sourceId the sourceId to set
     */
    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * @return the targetId
     */
    public Long getTargetId() {
        return targetId;
    }

    /**
     * @param targetId the targetId to set
     */
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }


    /**
     * @return the relationShipType
     */
    public Integer getRelationShipType() {
        return relationShipType;
    }

    /**
     * @param relationShipType the relationShipType to set
     */
    public void setRelationShipType(Integer relationShipType) {
        this.relationShipType = relationShipType;
    }

  
}
