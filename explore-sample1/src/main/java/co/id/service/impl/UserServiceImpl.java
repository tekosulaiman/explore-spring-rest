package co.id.service.impl;

import co.id.persistence.dao.IUserJpaDao;
import co.id.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.id.persistence.service.AbstractService;
import co.id.service.IUserService;

@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements IUserService {
    @Autowired
    private IUserJpaDao dao;

    public UserServiceImpl() {
        super();
    }

    // API
    // find
    @Override
    @Transactional(readOnly = true)
    public User findByName(final String name) {
        return dao.findByName(name);
    }

    // other
    // Spring
    @Override
    protected final IUserJpaDao getDao() {
        return dao;
    }

    @Override
    protected JpaSpecificationExecutor<User> getSpecificationExecutor() {
        return dao;
    }
}
