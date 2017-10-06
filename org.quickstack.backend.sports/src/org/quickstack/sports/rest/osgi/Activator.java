package org.quickstack.sports.rest.osgi;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogService;
import org.quickstack.sports.api.dao.ITeamDAOService;
import org.quickstack.sports.rest.TeamResource;

public class Activator extends DependencyActivatorBase {
	@Override
	public synchronized void init(BundleContext context,
			DependencyManager manager) throws Exception {
		manager.add(createComponent()
				.setInterface(Object.class.getName(), null)
				.setImplementation(TeamResource.class)
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