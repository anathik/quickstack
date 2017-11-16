package org.quickstack.sports.rest.osgi;

import java.util.Properties;

import org.amdatu.web.rest.jaxrs.AmdatuWebRestConstants;
import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;
import org.quickstack.sports.api.dao.ITeamDAOService;
import org.quickstack.sports.rest.PlayerResource;
import org.quickstack.sports.rest.TeamResource;

public class Activator extends DependencyActivatorBase {
	@Override
	public synchronized void init(BundleContext context,
			DependencyManager manager) throws Exception {
		Properties serviceProperties = new Properties();
	      serviceProperties.put(AmdatuWebRestConstants.JAX_RS_RESOURCE_BASE, "/");
		manager.add(createComponent()
				.setInterface(Object.class.getName(), serviceProperties)
				.setImplementation(TeamResource.class)
				.add(createServiceDependency().setService(ITeamDAOService.class)
						.setRequired(true))
				.add(createServiceDependency().setService(LogService.class)
						.setRequired(false)));
		manager.add(createComponent()
				.setInterface(Object.class.getName(), serviceProperties)
				.setImplementation(PlayerResource.class)
				.add(createServiceDependency().setService(ITeamDAOService.class)
						.setRequired(true))
				.add(createServiceDependency().setService(LogService.class)
						.setRequired(false)));
	}

	@Override
	public synchronized void destroy(BundleContext context,
			DependencyManager manager) throws Exception {
	}
}