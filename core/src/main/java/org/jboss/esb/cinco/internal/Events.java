/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.esb.cinco.internal;

import org.jboss.esb.cinco.Context;
import org.jboss.esb.cinco.Direction;
import org.jboss.esb.cinco.Exchange;
import org.jboss.esb.cinco.ExchangeEvent;
import org.jboss.esb.cinco.Scope;
import org.jboss.esb.cinco.internal.event.ExchangeEventImpl;
import org.jboss.esb.cinco.internal.event.ExchangeFaultEventImpl;
import org.jboss.esb.cinco.internal.event.ExchangeInEventImpl;
import org.jboss.esb.cinco.internal.event.ExchangeOutEventImpl;
import org.jboss.esb.cinco.internal.event.ExchangeState;

public class Events {
	
	public static ExchangeEvent createEvent(
			Exchange exchange, Direction direction) {
		
		String msgName = (String)exchange.getContext(Scope.MESSAGE).
			getProperty(Context.MESSAGE_NAME);
		
		switch (ExchangeState.fromString(msgName)) {
		case IN :
			return new ExchangeInEventImpl(exchange, direction);
		case OUT :
			return new ExchangeOutEventImpl(exchange, direction);
		case FAULT :
			return new ExchangeFaultEventImpl(exchange, direction);
		default :
			return new ExchangeEventImpl(exchange, msgName, direction);
		}
	}
}
