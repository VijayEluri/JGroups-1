/*******************************************************************************
 * Copyright (c) 2015 Composent, Inc. and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Composent, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.ecf.provider.jgroups.container;

import org.eclipse.ecf.provider.jgroups.identity.JGroupsID;

public class SyncMessage extends AbstractMessage {

	private static final long serialVersionUID = 2713842114615368704L;

	public SyncMessage(JGroupsID fromID, JGroupsID targetID, byte[] data) {
		super(fromID, targetID, data);
	}

}
