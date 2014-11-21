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
/**
 * <h1>Agent Logging</h1>
 *
 * A package for simple agent persistence.
 *
 * <h2>Description</h2>
 * 
 * <p>Agent log is a package especially designed
 * to satisfy simple agent logging needs. Actually,
 * it uses the logback library.</p>
 * 
 * <p>Agent log comes preconfigured and will produce log
 * files in the folder named "log". Each log files 
 * will contains the messages coming from one specific agent.</p>
 * 
 * <p>If your system contains and agent "A" and and agent "B" then
 * the log system will produce the following files :</p>
 * 
 * <pre>current_directory +
 *                  |- log +
 *                          |- A.log // log messages of the agent A
 *                          |- B.log // log messages of the agent B
 * </pre>
 *                          
 * <h2>Use</h2>
 * 
 * <p>You can refer to the examples java source files situated in the 
 * src/example/java directory</p>
 *  
 * <ol>
 * <li>First agent-log needs to be initialized at the start of the application. Here
 * is an example of all the method that can be called in order to configure
 * the logging system
 * 
 * <pre>
 * {@code
 * // initialization of the logging system
 * AgentLog.initializer() // all the above lines are optional except the last one : .initialize();

 * 	.logFolderName("log")          // the name of the folder containing log files (default "log")
 * 	.clearLogFolderOnStartup(true) // if true, all the content of the log folder  (default true)
 * 								   // will be cleared at each initialization
 * //	.customLogbackConfigurationFile("./custom-conf-logback.xml") // if not set, the default configuration is set (default null)
 * 													             // if not null then override all the following configuration lines
 * 													             // an example of custom configuration is available at example/custom-conf-logback.xml
 * 	.logEnabled(true)    // enable or disable logging (if disabled the log folder will not be cleared even if clearLogFolderOnStartup = true)
 * 	.logLevel(Level.ALL) // sets the min log level to display
 * 	                     // Level.OFF to disable logging
 * 	                     // Level.ALL for all traces
 * 	                     // by default Level.DEBUG
 * //	.logPatternLayout("%5property{amasStepNumber} > [%-6.6marker] %-7([%level]) %msg%n") // sets the output format of log lines (default value : null)
 * 																				         // if set to a non null value then override the
 * 																				         // behind configuration lines
 * 																				         // for more informations see http://logback.qos.ch/manual/layouts.html#ClassicPatternLayout
 * 	.logItemSeparator("...") // the separator between following elements
 * 	.stepNumberDisplay()
 * 		.minSize(6)
 * 		.align(Side.RIGHT) // justification side if the string length is lower than minSize
 * 		.displayed(true)   // whether it is displayed or not
 * 		.prefix("=>")      // sets the prefix to be displayed before the log element (by default prefix and suffix are empty strings)
 * 		.order(2)          // the order priority in the line (lower values are displayed first)
 * 		.parent()		   // used to come back to AgentLog.initializer() instance and configure other things
 * 		
 * 	.logLevelDisplay()
 * 		.align(Side.LEFT)
 * 		.displayed(true)
 * 		.minSize(5)
 * 		.order(1)
 * 		.prefix("(") // sets the prefix to be displayed before the log element
 * 		.suffix(")") // sets the suffix to be displayed after the log element
 * 		.parent()
 * 		
 * 	.markerDisplay() // markers are not displayed by default,
 * 			         // a marker is used to add more information, or a kind of log message type
 * 		.align(Side.LEFT)
 * 		.displayed(true)
 * 		.minSize(12)
 * 		.maxSize(12)
 * 		.truncatedSide(Side.LEFT) // truncation side when the string length to display is greater than maxSize
 * 		.prefix("{")
 * 		.suffix("}")
 * 		.order(3)
 * 		.parent()
 * 		
 * 	.messageDisplay()
 * 		.align(Side.LEFT)
 * 		.maxSize(70)
 * 		.truncatedSide(Side.RIGHT)
 * 		.order(4)
 * 		.parent()
 * 		
 * 	.initialize(); // initialize the logging system
 * }</pre>
 * </li>	
 * 
 * 	<li>Set the amasStepNumber to be displayed in log files 
 * 
 * <pre>
 * {@code
 * // set the step number to be displayed in the logs
 * AgentLog.setAmasStepNumber(stepNumber);
 * }</pre>
 * </li>
 * 
 * 	<li>Then in the agent behavior :
 * 	<ol>
 * 	<li>Fetch the logger instance from the agent name or id :
 * 
 * <pre>
 * {@code
 * Logger logger = AgentLog.getLogger("The agent name");
 * }</pre>
 * </li>
 * 
 * 	<li>Log messages 
 * 
 * - Simple messages :
 * 
 * <pre>
 * {@code
 * this.logger.info("New step =======================================================");
 * }</pre>
 * </li>
 * 
 * - Messages containing expressions to be rendered at runtime :
 * 
 * <pre>
 * {@code
 * logger.error("Trophoblastic malformed error in class {}", this.getClass());
 * }</pre>
 * 
 * The "{}" marks the place the expression this.getClass() will be inserted in the string.
 * 
 * - The logger can take 2 parameters...
 * 
 * <pre>
 * {@code
 * logger.debug("The new entry is {}. It replaces {}.", entry, oldEntry);
 * }</pre>
 * 
 * - ...And more with the help of an array of objects :
 * 
 * <pre>
 * {@code
 * Object[] paramArray = {newVal, below, above};
 * logger.debug("Value {} was inserted between {} and {}.", paramArray);
 * }</pre>
 * 
 * - In addition we can add markers to add custom log messages types :
 * 
 * <pre>
 * {@code
 * // Fetching a marker
 * Marker SNC1 = AgentLog.getMarker("SNC1");
 * // Using a marker
 * this.logger.info(SNC1, "Not nominal !!!");
 * }</pre>
 * </li>
 * 	</ol>
 * 	</li>
 * </ol>
 * 
 * @author lemouzy
 *
 */
package fr.irit.smac.libs.tooling.logging;