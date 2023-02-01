package co.id.service.impl;

import co.id.persistence.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.id.persistence.service.AbstractService;
import co.id.persistence.dao.IRoleJpaDao;
import co.id.service.IRoleService;

@Service
@Transactional
public class RoleServiceImpl extends AbstractService<Role> implements IRoleService {
    @Autowired
    private IRoleJpaDao dao;

    public RoleServiceImpl() {
        super();
    }

    // API
    // get/find
    @Override
    public Role findByName(final String name) {
        return getDao().findByName(name);
    }

    // create
    @Override
    public Role create(final Role entity) {
        return super.create(entity);
    }

    // Spring
    @Override
    protected final IRoleJpaDao getDao() {
        return dao;
    }

    @Override
    protected JpaSpecificationExecutor<Role> getSpecificationExecutor() {
        return dao;
    }
}