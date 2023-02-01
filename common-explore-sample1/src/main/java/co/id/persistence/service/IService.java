package co.id.persistence.service;

import co.id.interfaces.IByNameApi;
import co.id.interfaces.IWithName;

public interface IService <T extends IWithName> extends IRawService<T>, IByNameApi<T> {

}