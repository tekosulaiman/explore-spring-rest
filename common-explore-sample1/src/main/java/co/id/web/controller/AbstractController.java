package co.id.web.controller;

import co.id.persistence.model.INameableEntity;
import co.id.web.RestPreconditions;

public abstract class AbstractController<T extends INameableEntity> extends AbstractReadOnlyController<T> {

    // save/create/persist
    protected final void createInternal(final T resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestState(resource.getId() == null);
        getService().create(resource);
    }

    // update
    protected final void updateInternal(final long id, final T resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestElementNotNull(resource.getId());
        RestPreconditions.checkRequestState(resource.getId() == id);
        RestPreconditions.checkNotNull(getService().findOne(resource.getId()));

        getService().update(resource);
    }

    // delete/remove
    protected final void deleteByIdInternal(final long id) {
        getService().delete(id);
    }
}