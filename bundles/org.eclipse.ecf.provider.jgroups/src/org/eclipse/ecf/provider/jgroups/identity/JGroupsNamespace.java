/*******************************************************************************
 * Copyright (c) 2007 Composent, Inc. and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Composent, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.ecf.provider.jgroups.identity;

import java.net.URI;

import org.eclipse.ecf.core.identity.ID;
import org.eclipse.ecf.core.identity.IDCreateException;
import org.eclipse.ecf.core.identity.Namespace;
import org.eclipse.osgi.util.NLS;

public class JGroupsNamespace extends Namespace {

	private static final long serialVersionUID = 1235788855435011811L;
	public static final String SCHEME = "jgroups";
	public static final String NAME = "ecf.namespace.jgroupsid";

	public static JGroupsNamespace INSTANCE;

	public JGroupsNamespace() {
		super(NAME, "JGroups namespace");
		INSTANCE = this;
	}

	private JGroupsID createID(URI uri) throws IDCreateException {
		String uriScheme = uri.getScheme();
		if (uriScheme != null && !getScheme().equals(uriScheme))
			throw new IDCreateException("scheme is not jgroups:");
		else
			uriScheme = getScheme();
		URI idURI = URI.create(uriScheme + ":" + uri.getSchemeSpecificPart());
		return new JGroupsID(this, idURI);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ecf.core.identity.Namespace#createInstance(java.lang.Object
	 * [])
	 */
	public ID createInstance(Object[] parameters) throws IDCreateException {
		try {
			if (parameters != null && parameters.length > 0) {
				if (parameters[0] instanceof URI)
					return createID((URI) parameters[0]);
				else if (parameters[0] instanceof String)
					return createID(URI.create((String) parameters[0]));
			}
			throw new IDCreateException("JGroupsID cannot be created with given parameters");
		} catch (final Exception e) {
			if (e instanceof IDCreateException)
				throw (IDCreateException) e;
			throw new IDCreateException(NLS.bind("{0} createInstance()", getName()), e); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ecf.core.identity.Namespace#getScheme()
	 */
	public String getScheme() {
		return SCHEME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ecf.core.identity.Namespace#getSupportedParameterTypes()
	 */
	public Class[][] getSupportedParameterTypes() {
		return new Class[][] { { String.class, URI.class } };
	}

}
