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

/**
 * This interface exposes the methods for simple agent state persistence.
 * <p>
 * The state should be serializable.
 * <p>
 * Implementations should respect the assumption that attempts to retrieve
 * non-existent state should return null values.
 * 
 * @author jorquera
 *
 * @param <AgentState>
 *            the type of state to persist
 */
public interface StatePersistency<AgentState extends Serializable> {

	/**
	 * Requests the persistence of a state. This state will replace the last
	 * persisted state.
	 * 
	 * @param state
	 *            the state to persist
	 */
	public void saveState(AgentState state);

	/**
	 * Retrieve the last persisted state. The default assumption is that the
	 * persisted state should still be accessible for for future retrieval
	 * (until replaced or deleted).
	 * 
	 * @return the last persisted state, or null if no state was persisted.
	 */
	public AgentState retrieveState();

	/**
	 * Return the persisted state and remove it. The state will no longer be
	 * accessible for future retrieval.
	 * 
	 * @return the last persisted state, or null if no state was persisted
	 */
	public AgentState getAndDeleteState();

}
