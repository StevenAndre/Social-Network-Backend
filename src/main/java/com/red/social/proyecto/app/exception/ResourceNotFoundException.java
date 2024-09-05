package com.red.social.proyecto.app.exception;

public class ResourceNotFoundException extends RuntimeException{



    private String nameResource;
    private String fieldResouce;

    public ResourceNotFoundException(String nameResource, String fieldResouce) {
        super(String.format("El recurso: %s que estas buscando por: %s  no se encuentra disponible o no existe",nameResource,fieldResouce));
        this.nameResource = nameResource;
        this.fieldResouce = fieldResouce;
    }

    public String getNameResource() {
        return nameResource;
    }

    public void setNameResource(String nameResource) {
        this.nameResource = nameResource;
    }

    public String getFieldResouce() {
        return fieldResouce;
    }

    public void setFieldResouce(String fieldResouce) {
        this.fieldResouce = fieldResouce;
    }
}
