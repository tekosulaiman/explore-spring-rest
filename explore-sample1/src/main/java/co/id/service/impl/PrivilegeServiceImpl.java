package co.id.service.impl;

import co.id.persistence.model.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.id.persistence.service.AbstractService;
import co.id.persistence.dao.IPrivilegeJpaDao;
import co.id.service.IPrivilegeService;

@Service
@Transactional
public class PrivilegeServiceImpl extends AbstractService<Privilege> implements IPrivilegeService {
    @Autowired
    private IPrivilegeJpaDao dao;

    public PrivilegeServiceImpl() {
        super();
    }

    // API
    // find
    @Override
    public Privilege findByName(final String name) {
        return getDao().findByName(name);
    }

    // Spring
    @Override
    protected final IPrivilegeJpaDao getDao() {
        return dao;
    }

    @Override
    protected JpaSpecificationExecutor<Privilege> getSpecificationExecutor() {
        return dao;
    }
}