/*
 * #%L
 * agent-tooling
 * %%
 * Copyright (C) 2014 IRIT - SMAC Team
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
package fr.irit.smac.libs.tooling.persistency;

import java.io.Serializable;
import java.util.List;

/**
 * This interface exposes a simple API for sending persistent messages.
 * <p>
 * Note: this API will probably be deprecated at one point in favor of the more
 * general API defined by the agent messaging package. For this reason it is
 * should not be relied on to heavily.
 * 
 * @author jorquera
 *
 * @param <AgentId>
 *            unique ID of an agent
 * @param <Message>
 *            the type of messages to send
 */
public interface MsgPersistency<AgentId extends Serializable, Message extends Serializable> {

	public void sendMsg(AgentId id, Message msg);

	public List<Message> getMsgs();

}
