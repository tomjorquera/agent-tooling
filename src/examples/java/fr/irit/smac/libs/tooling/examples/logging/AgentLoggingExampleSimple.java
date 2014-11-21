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
package fr.irit.smac.libs.tooling.examples.logging;

import org.slf4j.Logger;

import fr.irit.smac.libs.tooling.logging.AgentLog;

/**
 * 
 * TODO: document
 * 
 * @author jorquera
 *
 */
public class AgentLoggingExampleSimple {

	public static void main(String[] args) {
		// initialize the logging system
		AgentLog.initialise();

		MyLoggingAgent a = new MyLoggingAgent("A");
		MyLoggingAgent b = new MyLoggingAgent("B");

		// multi-agent system execution
		for (int stepNumber = 1; stepNumber <= 10; stepNumber++) {

			// set the step number to be displayed in the logs
			AgentLog.setAmasStepNumber(stepNumber);

			a.perceiveDecideAndAct();
			b.perceiveDecideAndAct();
		}
	}

	/**
	 * A Very simple Logging Agent
	 * 
	 * @author lemouzy
	 */
	public static class MyLoggingAgent {
		private final String name;

		// logging stuff
		private final Logger logger;

		public MyLoggingAgent(String name) {
			this.name = name;

			// Fetch the logger instance
			this.logger = AgentLog.getLogger(name);
		}

		public void perceiveDecideAndAct() {

			this.logger.info("My name is " + this.name
					+ " and I'm alive !!! \\o/");
			this.logger.debug("*** step end");
		}
	}

}
