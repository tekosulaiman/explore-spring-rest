package co.id.persistence.service;

import co.id.persistence.model.INameableEntity;

public abstract class AbstractService<T extends INameableEntity> extends AbstractRawService<T> implements IService<T>{
    public AbstractService(){
        super();
    }
}