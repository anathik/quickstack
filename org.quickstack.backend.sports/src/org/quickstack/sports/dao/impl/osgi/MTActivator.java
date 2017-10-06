package org.quickstack.sports.dao.impl.osgi;

import java.util.Properties;

import javax.persistence.EntityManager;

import org.amdatu.jta.ManagedTransactional;
import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;
import org.quickstack.sports.api.dao.ITeamDAOService;
import org.quickstack.sports.api.model.Constants;
import org.quickstack.sports.dao.impl.TeamDAOImpl;

public class MTActivator extends DependencyActivatorBase {

	@Override
	public void destroy(BundleContext arg0, DependencyManager arg1) throws Exception {

	}

	@Override
	public void init(BundleContext arg0, DependencyManager dm) throws Exception {
		String entityManagerModuleFilter = String.format("(%s=%s)",org.osgi.service.jpa.EntityManagerFactoryBuilder.JPA_UNIT_NAME,Constants.PA_MODULE);
		
		Properties props;
		
		props = new Properties();
		props.put(ManagedTransactional.SERVICE_PROPERTY, ITeamDAOService.class.getName());
		dm.add(createComponent().setInterface(Object.class.getName(), props)
				.setImplementation(TeamDAOImpl.class)
				.add(createServiceDependency().setService(EntityManager.class,entityManagerModuleFilter).setRequired(true))
				.add(createServiceDependency().setService(LogService.class).setRequired(false)));
	}
}
