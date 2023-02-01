package co.id.web.controller;

import java.util.List;

import co.id.persistence.model.IEntity;
import jakarta.servlet.http.HttpServletRequest;

public interface ISortingController<T extends IEntity> {
    public List<T> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder);
    public List<T> findAllPaginated(final int page, final int size);
    public List<T> findAllSorted(final String sortBy, final String sortOrder);
    public List<T> findAll(final HttpServletRequest request);
}