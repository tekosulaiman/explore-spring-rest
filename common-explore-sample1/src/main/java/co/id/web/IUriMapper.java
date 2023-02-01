package co.id.web;

import co.id.persistence.model.IEntity;

public interface IUriMapper {
    <T extends IEntity> String getUriBase(final Class<T> clazz);
}